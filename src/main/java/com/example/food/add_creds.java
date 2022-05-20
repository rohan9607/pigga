package com.example.food;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

@WebServlet(name = "add_creds", value = "/add_creds")
public class add_creds extends HttpServlet {
  private String name;
  private int i;
  @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException{
      PrintWriter out = res.getWriter();
      String email = req.getParameter("email");
      String password = req.getParameter("pass");
      String phone_no = req.getParameter("mob_no");
      String hash = convertHex(email);
      Connection conn = null;
      Statement pmt = null;
      Statement pmt2 = null;
      try{
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://ec2-52-48-159-67.eu-west-1.compute.amazonaws.com/d71hu77blfn20a", "otnubxbygoaoqu", "e1575c7eaa8e030ca77951e5be982a002a31e8046bd0fda2841fb17a41063f4a");

        String sql = "insert into user_creds values('"+hash+"','"+email+"','"+password+"','"+phone_no+"')";
        pmt = conn.createStatement();
        i = pmt.executeUpdate(sql);
        String sql2 = "create table "+hash+" (item_id text primary key , name text, cost numeric(8,2), quantity int DEFAULT 1)";
        pmt2 = conn.createStatement();
        pmt2.executeUpdate(sql2);
        res.sendRedirect("sign_up/signup.jsp?flag=inserted");
      }catch (Exception e){
        res.sendRedirect("sign_up/signup.jsp?flag=duplicate");
//        out.println("already Registered!");
//        out.println(e);
      }

  }

  public String convertHex(String name){
    this.name = name;
    StringBuffer sb = new StringBuffer();
    char[] ch = name.toCharArray();
    for (int i = 0; i < ch.length; i++) {
      String hexString  = Integer.toHexString(ch[i]);
      sb.append(hexString);
    }

    String final_str = sb.toString();
    char[] ch1 = final_str.toCharArray();
    String result = "";
    for (int i=0;i<ch1.length;i++){
      if (!Character.isDigit(ch1[i])) {
        result = result + ch1[i];
      }
    }
    return result;
  }
}
