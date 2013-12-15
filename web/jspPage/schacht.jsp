<HTML>
  <HEAD>
  <TITLE>Steurung des Bergwerk </TITLE>
  <LINK href="../main.css" rel=stylesheet type=text/css>
  </HEAD>

<BODY>
	<%@ page import="java.util.*"%>
	<%@ page language="java" import="java.sql.*"%>

	<%
		try{ 
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){out.println("Driver error!");}
		try{
	    		Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.33.49/Bergwerk", "admin", "12345");
	    		Statement statement=connection.createStatement();
	    		String query ="SELECT * from Schaechte";
			ResultSet result=statement.executeQuery(query);
	    		Statement statement1=connection.createStatement();
			ResultSet z=statement1.executeQuery("SELECT * from Zeit where Schaechte_id=1");
			Statement statement2=connection.createStatement();
			ResultSet ang=statement2.executeQuery("SELECT * from Zeit where Schaechte_id=1");
			//Statement statement3=connection.createStatement();
			//ResultSet color=statement3.executeQuery("SELECT * from Zeit where Schaechte_id=1");
			String color="#FFFFFF";
			
	%>

	<%
			while(result.next()) {
	%>
	<h1>
				<%=result.getString(2)%> 
	</h1>
	<table border="1">

	<%
				z=statement1.executeQuery("SELECT * from Zeit where Schaechte_id="+result.getString(1));
				while (z.next()){
					if (result.getString(3).equals(z.getString(1)))
						color="#D3EDF6";
					else color="#FFFFFF";
	%>
	<tr  bgcolor= 
		<%=color%>
	>
	<td>
	<%				ang=statement2.executeQuery("SELECT name FROM Angestellte WHERE id="+z.getString(1));
					ang.next();
					
	%>
					 <%=ang.getString(1)%> 
	</td>     
	<td>
					 <%=z.getString(2)%> 
	</td> 
	</tr>
	<%  				
				}
	%> 
	</table>
	<li>naaaaaaaab</li> 

	<%
			}
	%> 
	
	<%
			ang.close();
			statement2.close();
			z.close();
			statement1.close();
			result.close();
			statement.close();
			connection.close();
		}
		catch(Exception e){out.println("Error: "+e);}
	%>
</BODY>
</HTML>
