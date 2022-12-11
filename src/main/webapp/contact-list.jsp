<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
					<td>
						<c:if test="${not empty contact.email}">
							<a href="mailto:${contact.email}">${contact.email}</a>
						</c:if>
						
						<c:if test="${empty contact.email}">
							Email not informed
						</c:if>
					</td>
					<td>${contact.address}</td>
					<td><fmt:formatDate value="${contact.birthday.time }" pattern="dd/MM/yyyy"/></td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>