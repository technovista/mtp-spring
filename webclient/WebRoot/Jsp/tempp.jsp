<%@ include file="/Jsp/taglib.jsp"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/styles.css" />
</head>
<body>
<form  action="" method="POST">

<p>&nbsp;</p>
	<table  >
	<% try{ %>
	<c:forEach items="${model.testTypeList}" var="testType">
		<tr>
				<% System.out.println("In loop");
		%>
			<td align="left" height="24">
			<div align="right">
			<Spring:bind path="testType.testTypeId">
			<input type="radio" name="testTypeId" value="<c:out value="${testType.testTypeId}" />"/>
           </Spring:bind>
           </div>
           </td>
           <td align="left">
            <c:out value="${testType.testTypeName}"/>		
			
			</td>
		</tr>
		</c:forEach>	
		<%}catch(Exception ex) {
		
		System.out.println("error=" +ex.getMessage());
		
		
		}%>
		
		
<tr>
<td></td>
<td align="left"> 
<input name="action" type="submit" value="Submit">

</td>
</tr>
	

</table>

</form>
</body>
</html>
