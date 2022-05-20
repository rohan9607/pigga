<%@ page language="java" import="java.sql.*" %>
<%      
    String username = request.getParameter("username");
    String email = request.getParameter("email"); 
    String password = request.getParameter("password"); 
    //String Querry = "INSERT INTO user_login VALUES('" + username + "','" + email + "','" + password
    //+ "')";
    try{ 
        Class.forName("org.postgresql.Driver");
        //out.println("DRIVER LOADED...");
        Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo", "postgres","rohan123");
        //out.println("connection established...");
        Statement st=con.createStatement(); 
        ResultSet rs=st.executeQuery("INSERT INTO user_login VALUES('" + username + "','" + email + "','" + password
                + "')");
        
%>           
<script>
    alert("registration successfully");
</script>
<%
       
     }
     catch(SQLException e){
         //out.println("THE ERROR IS"+e); 
        }
%>