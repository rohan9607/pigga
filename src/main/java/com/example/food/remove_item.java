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

@WebServlet(name = "remove_item", value = "/remove_item")

public class remove_item extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        HttpSession session  = req.getSession();
        String table_name = (String) session.getAttribute("userId");

        try {
            String [] strset = new String[1];
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-52-48-159-67.eu-west-1.compute.amazonaws.com/d71hu77blfn20a", "otnubxbygoaoqu", "e1575c7eaa8e030ca77951e5be982a002a31e8046bd0fda2841fb17a41063f4a");
            String fname= req.getParameter("name");
            String sql = "select item_id from content where name=?";
            PreparedStatement pmt = conn.prepareStatement(sql);
            pmt.setString(1,fname);
            ResultSet rs = pmt.executeQuery();
            while (rs.next()){
                strset[0] = rs.getString(1);
            }
            String entry = strset[0];
            String sql2 = "delete from "+table_name+" where item_id=?";
            PreparedStatement pmt2 = conn.prepareStatement(sql2);
            pmt2.setString(1,entry);
            pmt2.executeQuery();


        }
        catch (Exception e){
//
        }
        resp.sendRedirect("cart_list.jsp");

    }
}
