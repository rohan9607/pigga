package com.example.food;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "validate" , value = "/validate")
public class validate extends HttpServlet {
     private String username, password,name;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        response.setContentType("text/html");
        String user = request.getParameter("user_mail");
        String pwd = request.getParameter("password");
        String user_hex = convertToHex(user);
        PrintWriter out = response.getWriter();
//        out.println(user+"<br>"+pwd+"<br>"+user_hex);
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-52-48-159-67.eu-west-1.compute.amazonaws.com/d71hu77blfn20a", "otnubxbygoaoqu", "e1575c7eaa8e030ca77951e5be982a002a31e8046bd0fda2841fb17a41063f4a");
            String sql = "select * from user_creds where hash=?";
            PreparedStatement pmt = conn.prepareStatement(sql);
            pmt.setString(1, user_hex);
            ResultSet rs = pmt.executeQuery();
            while (rs.next()){
                username = rs.getString(2);
                password = rs.getString(3);
            }
//            out.println("Creds" + username + password);
//        response.sendRedirect("index.jsp?username="+user);
            if (username.equals(user)) {
                if (password.equals(pwd)) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("userId", user_hex);
                    session.setAttribute("user_mail", username);
                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.forward(request, response);
                    response.sendRedirect("index.jsp?userId=" + user_hex);
                } else {
                    response.sendRedirect("sign_in/login.jsp?error=invalid_password");
                }
            } else {
                response.sendRedirect("sign_in/login.jsp?error=invalid_username");
            }
        }
        catch(Exception e){
            //
        }
    }


    public String convertToHex(String name){
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