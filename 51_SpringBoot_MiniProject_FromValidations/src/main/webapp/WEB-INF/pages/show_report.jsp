<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${!empty emplist}">
		<table bgcolor="cyan" border=1 align="center">
			<tr bgcolor="yellow">
				<th>EMPNO</th>
				<th>ENAME</th>
				<th>JOB</th>
				<th>SALARY</th>
				<th>DEPTNO</th>
				
				<th>OPERATIONS</th>
			</tr>
			<c:forEach var="emp" items="${emplist}">
				<tr bgcolor="white">
					<td align="center">${emp.empno}</td>
					<td align="center">${emp.ename}</td>
					<td align="center">${emp.job}</td>
					<td align="center">${emp.sal}</td>
					<td align="center">${emp.deptno}</td>
					

					<td align="center"><a href="edit?no=${emp.empno}"><image
								src="images/edit.jpeg" width="40" height="40"></a> 
								
								<a href="delete?no=${emp.empno}"><image src="images/delete.jpeg"
								width="40" height="40"  onclick="return confirm('are you sure to delete?')" ></a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<h1 style="color: red; text-align: center">Record Not Found</h1>
	</c:otherwise>
</c:choose>

<h2 style="color:blue;text-align:center">${resultMsg}</h2>

<h1 style="color: red; text-align: center">
	<a href="register"><img src="images/add.png" width="50px"
		height="50px"></a>&nbsp;&nbsp; <a href="./"><img
		src="images/home.jpeg" width="50px" height="50px"></a>
</h1>