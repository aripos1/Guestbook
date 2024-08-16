<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.javaex.vo.GuestVo"%>

<%
GuestVo guestVo = (GuestVo) request.getAttribute("guestVo");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/guestbook/gbc" method="get">
		<table>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password" required></td>

				<td><input type="hidden" name="no" value="<%=guestVo.getNo()%>">
					<input type="hidden" name="action" value="delete">
					<button type="submit">삭제</button></td>
			</tr>
		</table>
	</form>

	<br>
	<br>
	<a href="">메인으로 돌아가기</a>
</body>
</html>