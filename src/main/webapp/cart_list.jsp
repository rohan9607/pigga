<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Map.Entry" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String user = (String) session.getAttribute("user_mail");

%>
<html lang="en">
<head>


  <meta charset="utf-8">
  <!--  This file has been downloaded from bootdey.com @bootdey on twitter -->
  <!--  All snippets are MIT license http://bootdey.com/license -->
  <title>cart</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container px-3 my-5 clearfix">
  <!-- Shopping cart table -->
  <div class="main_card">
    <div class="card-header">
      <h2>Food Cart</h2>
        <p><%=user%></p>
    </div>
    <div class="card-body">
      <div class="table-responsive">
        <table class="table table-bordered m-0">
          <thead>
          <tr>
            <!-- Set columns width -->
            <th class="text-center py-3 px-4" style="min-width: 400px;">Food Items &amp; Details</th>
            <th class="text-right py-3 px-4" style="width: 100px;">Price</th>
            <th class="text-center py-3 px-4" style="width: 120px;">Quantity</th>
            <th class="text-right py-3 px-4" style="width: 100px;">Total</th>
            <th class="text-center align-middle py-3 px-0" style="width: 40px;"><a href="#" class="shop-tooltip float-none text-light" title="" data-original-title="Clear cart"><i class="ino ion-md-trash"></i></a></th>
          </tr>
          </thead>
          <tbody>


<%

  response.setContentType("text/html");
  String table_name = (String) session.getAttribute("userId");
  int total = 0;
  try {
    Class.forName("org.postgresql.Driver");
      Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-52-48-159-67.eu-west-1.compute.amazonaws.com/d71hu77blfn20a", "otnubxbygoaoqu", "e1575c7eaa8e030ca77951e5be982a002a31e8046bd0fda2841fb17a41063f4a");
    Statement stmt = conn.createStatement();



    String sql = "select * from "+table_name+" order by item_id ASC ";
    ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
          out.println("<tr>");
          total = rs.getInt(3)*rs.getInt(4) + total;

%>
<td class="p-4"><a href="#" class="d-block font-weight-bold text-dark"><%=rs.getString(2)%></a></td>
<td class="text-right font-weight-bold align-middle p-4" ><%=rs.getInt(3)%></td>
<td class="align-middle  p-4"><button onclick="window.location.href='action?id=<%=rs.getString(1)%>&action=append'">+</button>&nbsp;<%=rs.getInt(4)%>&nbsp;<button onclick="window.location.href='action?id=<%=rs.getString(1)%>&action=minus'">-</button></td>
<td class="text-right  align-middle p-4" ><%=rs.getInt(4)*rs.getInt(3)%></td>
<td class="text-center font-weight-bold align-middle px-0"><a href="remove_item?name=<%=rs.getString(2)%>" class="shop-tooltip close float-none text-danger" title="" data-original-title="Remove">×</a></td>
<%
      out.println("</tr>");
    }
  }
  catch (Exception e){
//
  }

%>

          </tr>
          </tbody>
        </table>
      </div>
      <!-- / Shopping cart table -->
        <%
        if (total>0){
        %>
        <form action="success.html" method="post">
        <div class="container mt-5 d-flex">
            <div class="card p-4">
                <div class="d-flex justify-content-between align-items-center">
                    <h5 class="total-amount">Total amount</h5>
                    <div class="amount-container"><span class="amount-text"><span class="dollar-sign">Total:</span><%=total%>&#x20B9;</span></div>
                </div>
                <div class="pt-4"> <label class="d-flex justify-content-between"> <span class="label-text label-text-cc-number">CARD NUMBER</span><img src="https://img.icons8.com/dusk/64/000000/visa.png" width="30" class="visa-icon" /></label> <input type="tel" name="credit-number" class="form-control credit-card-number" maxlength="19" placeholder="Card number" required> </div>
                <div class="d-flex justify-content-between pt-4">
                    <div> <label><span class="label-text">EXPIRY</span> </label> <input type="date" name="expiry-date" class="form-control expiry-class" placeholder="MM / YY" required> </div>
                    <div> <label><span class="label-text">CVV</span></label> <input type="tel" name="cvv-number" class="form-control cvv-class" maxlength="4" pattern="\d*" required> </div>
                </div>
                <div class="d-flex justify-content-between pt-5 align-items-center"> <input type="submit" class="btn payment-btn" value="Order"></input> </div>
            </div>
        </div>
        </form>
        <%
            }else{
        %>
        <strong>Cart is empty</strong>
        <%
            }
        %>
            <div class="float-right ">
        <button type="button" onclick="window.location.href='index.jsp'" class="btn btn-lg btn-default md-btn-flat mt-5 mr-3">Back to Order</button>
      </div>

    </div>
  </div>
</div>

<style type="text/css">
    .text-large{
        font-size: 1.2rem;
    }
  body{
    margin-top:20px;
    background:url("assets/imgs/wood.jpg") no-repeat center center/cover;
  }
  .ui-w-40 {
    width: 40px !important;
    height: auto;
  }

  .card{
    box-shadow: 0 1px 15px 1px rgba(52,40,104,.08);
      width: 100%;
  }
  .main_card{
      background: white;
      width: 100%;

  }

  .ui-product-color {
    display: inline-block;
    overflow: hidden;
    margin: .144em;
    width: .875rem;
    height: .875rem;
    border-radius: 10rem;
    -webkit-box-shadow: 0 0 0 1px rgba(0,0,0,0.15) inset;
    box-shadow: 0 0 0 1px rgba(0,0,0,0.15) inset;
    vertical-align: middle;
  }

    .total-amount {
        font-size: 22px;
        font-weight: 700;
        color: #383737
    }

    .amount-container {
        background-color: #e9eaeb;
        padding: 6px;
        padding-left: 15px;
        padding-right: 15px;
        border-radius: 8px
    }

    .amount-text {
        font-size: 20px;
        font-weight: 700;
        color: #673AB7
    }

    .dollar-sign {
        font-size: 20px;
        font-weight: 700;
        color: #000
    }

    .label-text {
        font-size: 16px;
        font-weight: 600;
        color: #b2b2b2
    }

    .credit-card-number {
        width: 290px;
        z-index: 28;
        border: 2px solid #ced4da;
        border-radius: 6px;
        font-weight: 600
    }

    .credit-card-number:focus {
        box-shadow: none;
        border: 2px solid #673AB7
    }

    .visa-icon {
        position: relative;
        top: 42px;
        right: 14px;
        z-index: 30
    }

    .expiry-class {
        width: 120px;
        border: 2px solid #ced4da;
        font-weight: 600;
        font-size: 12px;
        height: 48px
    }

    .expiry-class:focus {
        box-shadow: none;
        border: 2px solid #673AB7
    }

    .cvv-class {
        width: 120px;
        border: 2px solid #ced4da;
        font-weight: 600;
        font-size: 12px;
        height: 48px
    }

    .cvv-class:focus {
        box-shadow: none;
        border: 2px solid #673AB7
    }

    .payment-btn {
        background-color: #673AB7;
        padding: 15px;
        padding-left: 25px;
        padding-right: 25px;
        color: #fff;
        font-weight: 600;
        border-radius: 12px
    }

    .payment-btn:hover {
        box-shadow: none;
        color: #fff
    }

    .cancel-btn {
        background-color: #fff;
        color: #b2b2b2;
        padding: 0px;
        padding-top: 3px;
        padding-bottom: 3px;
        font-weight: 600;
        border-radius: 6px
    }

    .cancel-btn:hover {
        border: 2px solid #b2b2b2;
        color: #b2b2b2
    }

    .cancel-btn:focus {
        box-shadow: none
    }

    .label-text-cc-number {
        position: relative;
        top: 4px
    }
</style>
</body>
</html>