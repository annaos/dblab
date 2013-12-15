<HTML>
  <HEAD>
  <TITLE>My first JSP-search page! </TITLE>
  
     <LINK href="../main.css" rel=stylesheet type=text/css>
  
  </HEAD>

<BODY>

<p class=header> Database Web Search</p>


<FORM method="POST" action="db_search.jsp">


   <table>
 
     <tr>
      <td class=menu_col> Enter your search string:</td>
	  <select name="column" size="3">
      <option selected value="VORNAME">Vorname</option>
      <option value="NACHNAME">Nachname</option>
      <option value="GEHALT">Gehalt</option>
    </select>
      <td class=menu_col><INPUT type="text" name="name" value=''> </td>
     </tr>
   
   </table>

  
  <INPUT type="submit" value="Search">
  <INPUT type="Reset" value="Clear">
</FORM>



<!--// Importing the packages:-->
<%@ page import="java.util.*"%>
<%@ page language="java" import="java.sql.*"%>

<%
// Getting the "name" parameter from the form:
  String name=request.getParameter("name");
  String col=request.getParameter("column");
 
// just a security precaution:
  if (name==null) {name="";}

try 
 { // Typical java "try-catch" loop

// Loading the driver:
Class.forName("oracle.jdbc.driver.OracleDriver");

// Creating a connection:
  Connection connection = DriverManager.getConnection ("jdbc:oracle:thin:@bert.mi.fh-offenburg.de:1521:orcl", "dblab13", "dblab13");

// Creating a statement object:
  Statement statement=connection.createStatement();

// Getting the "ResultSet" Object:
  //-- Prepare the query as a string (just for simplicity):
  //-- Searching all the entries in "FirstName" column,
  // which contain a string, passed from JSP:

String query ="SELECT * FROM AGENT WHERE " +col+ " LIKE '%" +name+ "%'";

//-- Execute the query:
  ResultSet result_search=statement.executeQuery(query);

%>


<br>

<font class=bg_row> Search Results: </font>

  <ul>

<%

  while(result_search.next())
  
    {
    
%>


     <li><% for (int i=0;i<result_search.getMetaData().getColumnCount();i++)
	 {%>
	 <%=result_search.getString(i+1)%> 
     
      <!-- "1" refers to the first column of the result set 
      in the result of the first attribute in "SELECT" clause-->
      
       || 
       
       
     <%}%>
	 
     </li>
    
    
     
<%

    }

%>

  </ul>
  
<%

// Closing the ResultSets:
  result_search.close();

// Closing the Statement object:
  statement.close();

// Closing the connection:
  connection.close();

 } catch(Exception e){out.println("Error!"+e);}

//back to HTML :-)

%>
</BODY>
</HTML>
