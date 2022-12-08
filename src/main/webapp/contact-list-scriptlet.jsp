<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*, br.com.agenda.dao.*, br.com.agenda.model.*"
	language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Contact List</title>
	</head>
	<body>
		<table>
			 <tr>
			    <th>Name</th>
			    <th>Email</th>
			    <th>Address</th>
			    <th>Birthday</th>
	  		</tr>
			<%
				ContactDao dao = new ContactDao();
				List<Contact> contacts = dao.getList();
				
				for(Contact contact: contacts){
					
				String pattern = "dd/MM/yyyy";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

				Date date = contact.getBirthday().getTime();
				String formattedDate = simpleDateFormat.format(date);
			%>
				<tr>
					<td><%=contact.getName() %></td>
					<td><%=contact.getEmail() %></td>
					<td><%=contact.getAddress() %></td>
					<td><%=formattedDate%></td>
				</tr>
			<%
				}
			%>
		</table>
	</body>
</html>