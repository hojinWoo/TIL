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
	<h1>Item List</h1>
	<table border="1">
	<thead>
	<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>가격</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="u" items="${itemlist}">
			<tr>
				<td>${u.id}</td>
				<td>${u.name}</td>
				<td>${u.price}</td>
			</tr>                                           
	</c:forEach>
	</tbody>
	</table>
</div>

