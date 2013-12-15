<HTML xmlns:h="http://java.sun.com/jsf/html" xmlns:f="http://java.sun.com/jsf/core">
  <HEAD>
	  <TITLE>Steurung des Bergwerk</TITLE>
	  
  </HEAD>
<BODY>

<%@ page import="java.util.*"%>
<%@ page language="java" import="java.sql.*"%>


<%
try
{   // Class.forName("oracle.jdbc.driver.OracleDriver")
       Class.forName("com.mysql.jdbc.Driver");
}
	catch(Exception e){out.println("Driver error!");
}

try
{
    //Connection connection = DriverManager.getConnection ("jdbc:oracle:thin:@bert.mi.fh-offenburg.de:1521:orcl", "dblab13", "dblab13");
    Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.33.49/Bergwerk", "admin", "12345");
        
    // Creating a statement object:
    Statement statement=connection.createStatement();
    
    // Getting the "ResultSet" Object:
    //-- Prepare the query as a string (just for simplicity):
    String query ="SELECT * from Angestellte";

    //-- Execute the query:
    ResultSet result=statement.executeQuery(query);



    // Process the results:
    %>
    
    <p class=header> Database Data on the Web</p>

    <br>
	<table border="2" cellpadding="0" cellspacing="4">
    <%


     while(result.next())
  
    {
	%><tr><%

    
    for (int i=0;i<result.getMetaData().getColumnCount();i++)
	{
		%>
	<td align="right"><%=result.getString(i+1)%></td>
     		<%
	}
		%> 
	</tr>
		<%
	}

    // Closing the ResultSet:
    result.close();

    // Closing the Statement object:
    statement.close();

    // Closing the connection:
    connection.close();

}
catch(Exception e){out.println("Error: "+e);}


//back to HTML :-)
%>
</BODY>
</HTML>
