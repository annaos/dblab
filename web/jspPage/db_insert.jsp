<HTML>
<HEAD>
   
   <TITLE>My first JSP-insert page!</TITLE>
   
     <LINK href="../main.css" rel=stylesheet type=text/css>
   
</HEAD>
  <BODY>

<FORM method="POST" action="db_insert.jsp">

<p class=header> Database Insert through the web</p>


<font class=textheading>Enter New Agent</font>

<p>

 <table>
 
   <tr>
    <td class=menu_col> Agent number:</td>
    <td class=menu_col><INPUT type="text" name="AgentNr" value=''> </td>
   </tr>
   
   <tr>
    <td class=menu_col> First name:</td>
    <td class=menu_col><INPUT type="text" name="FirstName" value=''> </td>
   </tr>
   
   <tr>
    <td class=menu_col> Last name:</td>
    <td class=menu_col><INPUT type="text" name="LastName" value=''> </td>
   </tr>
   
   <tr>
    <td class=menu_col> Salary:</td>
    <td class=menu_col><INPUT type="text" name="Salary" value=''> </td>
   </tr>

   <tr>
    <td class=menu_col> Boss:</td>
    <td class=menu_col><INPUT type="text" name="Boss" value=''> </td>
   </tr>

 </table>
 
 <p>

 <INPUT type="submit" value="Post">
 <INPUT type="Reset" value="Clear">
 
</FORM>


<!--// Importing the packages:-->
<%@ page import="java.util.*"%>
<%@ page language="java" import="java.sql.*"%>

<%
// Getting the "name" parameter from the form:
  String AgentNr=request.getParameter("AgentNr");
  String FirstName=request.getParameter("FirstName");
  String LastName=request.getParameter("LastName");
  String Salary=request.getParameter("Salary");
  String Boss=request.getParameter("Boss");  


try

 { // Typical java "try-catch" loop

// Loading the driver:
  Class.forName("oracle.jdbc.driver.OracleDriver");

// Creating a connection:
Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@bert.mi.fh-offenburg.de:1521:orcl", "dblab13", "dblab13");



// Creating a statement object:
Statement statement=connection.createStatement();

// just a security precaution:
  if (AgentNr==null) // first time (no parameters passed)
    {;} // skip the insert procedure

  else // otherwise insert valid parameters:
    {

// Getting the "ResultSet" Object:

//-- Prepare the query as a string (just for simplicity):
    String query ="INSERT INTO AGENT VALUES ('"+AgentNr+ "', '"+FirstName+"', '"+LastName+"','"+Salary+"', '"+Boss+"')";

//-- Execute the query:
    ResultSet result=statement.executeQuery(query);
// Closing the ResultSet:
    result.close();
    }



//Let’s now see what is written in the database:
  //-- Execute the query:

  ResultSet result_select=statement.executeQuery("select * from AGENT");

// Process the results:

%>
<p>


<font class=bg_row> Agent Number || FirstName || LastName || Salary || Boss || </font>



  <ul>


<%

  while(result_select.next())
  
    {
    
    
    
	
%>


     <li><% for (int i=0;i<result_select.getMetaData().getColumnCount();i++)
	 {%>
	 <%=result_select.getString(i+1)%> 
     
      <!-- "1" refers to the first column of the result set 
      in the result of the first attribute in "SELECT" clause-->
      
       || 
       
       
     <%}%>
	 
     </li>
     
      <p> <!-- just to get a neat output -->
<%

    }

%>

  </ul>
  
<%


// Closing the result set:
  result_select.close();

// Closing the Statement object:
  statement.close();

// Closing the connection:
  connection.close();

 } catch(Exception e){out.println("Error: "+e);}

//back to HTML :-)

%>
</BODY>
</HTML>

