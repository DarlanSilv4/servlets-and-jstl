<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Contact List</title>
	</head>
	<body>
		<jsp:useBean id="dao" class="br.com.agenda.dao.ContactDao"/>
		<table>
		 	<tr>
			    <th>Name</th>
			    <th>Email</th>
			    <th>Address</th>
			    <th>Birthday</th>
	  		</tr>
			<c:forEach var="contact" items="${dao.list}">
				<tr>
					<td>${contact.name}</td>
					<td>${contact.email}</td>
					<td>${contact.address}</td>
					<td>${contact.birthday.time}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>