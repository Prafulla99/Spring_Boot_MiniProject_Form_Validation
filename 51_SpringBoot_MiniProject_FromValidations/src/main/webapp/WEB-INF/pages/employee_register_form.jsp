<%@ page isELIgnored="false" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>

<h1 style="color:blue;text-align:center">Employee Register Form</h1>

<frm:form modelAttribute="emp">
   <%--  <p style="color:red;text-align:center">
		<frm:errors path="*"/>
	</p> --%>

     <table bgcolor="cyan" border="0" align="center">
  <tr>
    <td>Employee Name</td>
    <td><frm:input path="ename"/><frm:errors path="ename" cssStyle="color:red"/> </td>
  </tr>
  <tr>
    <td>Employee Job</td>
    <td><frm:input path="job"/><frm:errors path="job" cssStyle="color:red"/> </td>
  </tr>
  <tr>
    <td>Employee Salary</td>
    <td><frm:input path="sal"/> <frm:errors path="sal" cssStyle="color:red"/></td>
  </tr>
  <tr>
    <td>Department Number</td>
    <td> <frm:select path="deptno">
      <frm:options items="${dnoList}"/>
    </frm:select><frm:errors path="deptno" cssStyle="color:red"/></td>
  </tr>
   <tr>
    
    <td><input type="submit" value="register"/> </td>
  </tr>
  
  
</table>    


</frm:form>




