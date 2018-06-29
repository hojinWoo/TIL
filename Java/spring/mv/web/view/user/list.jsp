<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#main_center {
	margin: 0 20px;
	width: 760px;
	height: 480px;
	background: white;
}
</style>
<div id="main_center">
	<h1>User List</h1>
	<table border="1">
	<thead>
	<tr>
		<th>아이디</th>
		<th>비번</th>
		<th>이름</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="u" items="${userlist}">
			<tr>
				<td>${u.id}</td>
				<td>${u.pwd}</td>
				<td>${u.name}</td>
			</tr>                                           
	</c:forEach>
	</tbody>
	</table>
</div>

