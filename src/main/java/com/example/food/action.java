package com.example.food;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "action", value = "/action")
public class action extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        String table_name = (String) session.getAttribute("userId");
        Boolean flag= null;
        int pre_quant = 0;
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-52-48-159-67.eu-west-1.compute.amazonaws.com/d71hu77blfn20a", "otnubxbygoaoqu", "e1575c7eaa8e030ca77951e5be982a002a31e8046bd0fda2841fb17a41063f4a");
            String sql1 = "select quantity from "+table_name+" where item_id=?";
            PreparedStatement pmt1 = conn.prepareStatement(sql1);
            pmt1.setString(1, id);
            ResultSet rs1 = pmt1.executeQuery();
            while (rs1.next()){
                pre_quant = rs1.getInt(1);
            }


            if(pre_quant >= 2){
                if(action.equals("append")){
                    String sql2 = "update "+table_name+" set quantity=? where item_id=?";
                    PreparedStatement pmt2 = conn.prepareStatement(sql2);
                    pmt2.setInt(1, pre_quant+1);
                    pmt2.setString(2, id);
                    pmt2.executeQuery();
                }else {
                    String sql2= "update "+table_name+" set quantity=? where item_id=?";
                    PreparedStatement pmt2 = conn.prepareStatement(sql2);
                    pmt2.setInt(1, pre_quant-1);
                    pmt2.setString(2, id);
                    pmt2.executeQuery();

                }
            }
            else if (pre_quant == 1 ){
                if(action.equals("append")){
                    String sql2 = "update "+table_name+" set quantity=? where item_id=?";
                    PreparedStatement pmt2 = conn.prepareStatement(sql2);
                    pmt2.setInt(1, pre_quant+1);
                    pmt2.setString(2, id);
                    pmt2.executeQuery();
                }
            }
        }
        catch(Exception e){
//
        }
        resp.sendRedirect("cart_list.jsp");
    }
}
