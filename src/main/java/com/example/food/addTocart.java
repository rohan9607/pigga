package com.example.food;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "addTocart", value = "/addTocart")
public class addTocart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int price = Integer.parseInt(req.getParameter("price"));
        HttpSession session = req.getSession();
        String email = (String)session.getAttribute("user_mail");
        String table_name = (String) session.getAttribute(("userId"));
        PrintWriter out = resp.getWriter();
        String name= "";
        try {

            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-52-48-159-67.eu-west-1.compute.amazonaws.com/d71hu77blfn20a", "otnubxbygoaoqu", "e1575c7eaa8e030ca77951e5be982a002a31e8046bd0fda2841fb17a41063f4a");
            String sql2 = "select name from content where item_id=?";
            PreparedStatement pmt2 = conn.prepareStatement(sql2);
            pmt2.setString(1,id);
            ResultSet rs = pmt2.executeQuery();
            while (rs.next()){
                name = rs.getString(1);
            }
            String sql = "insert into "+table_name+"(item_id, name, cost) values (?,?,?)";
            PreparedStatement pmt = conn.prepareStatement(sql);
            pmt.setString(1, id);
            pmt.setString(2,name);
            pmt.setInt(3,price);
            pmt.executeQuery();
        }
        catch (Exception e){
//
        }
        resp.sendRedirect("index.jsp?user_mail="+email);

    }
}
