<%@ include file="/Jsp/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.array.testprep.util.Constants"%>
<html>
<head>
<script type="text/javascript" src="js/aa.js"></script>
<script type="text/javascript" src="js/prototype.js"></script>
<link type="text/css" rel="stylesheet" href="CSS/displaytag.css" />

<style type="text/css"> <!-- A:link { text-decoration: none; color:#00008B
} A:visited { text-decoration: none; color:#700000  } A:hover { text-decoration: 
underline; color:#06AEFF } --> </style>
</head>
<body>
<table class="contentPageHeader">
	<tr>
		<td>&nbsp;User</td>
	</tr>
</table>

<form action="" method="POST"><aa:zone name="loginTable">
on page

	<display:table name="model.allLoginList" class="table"
		requestURI="users.html?action=list" id="allLoginList" export="true"
		excludedParams="*" pagesize="10" sort="list">

		<display:column property="testTakerId" media="csv excel xml pdf"
			titleKey="testTaker.testTakerId" />

		<display:column sortProperty="emailId" sortable="true" media="html"
			titleKey="testTaker.emailId">
			
					<c:out value='${allLoginList.emailId}' />				
		</display:column>

		<display:column sortable="false" titleKey="testTaker.delete">
			<c:choose>
				<c:when test="${allLoginList.isAdmin=='n'}">
					<a
						href='viewProfile.html?action=delete&id=<c:out value="${allLoginList.testTakerId}"/>'>
					<img src="images/delete.png" border="0"> </a>
				</c:when>
			</c:choose>
		</display:column>

	</display:table>

</aa:zone> <%
 	if (request.getParameter(Constants.STATUS_MSG) != null) {
 %>
<table width="435" height="138" align="center">

	<tr>
		<td bgcolor="#ACE9FD">
		<p align="left"><font color="green"> <b> User <%=request.getParameter(Constants.STATUS_MSG)%>
		successful </b> </font></p>
		</td>
	</tr>

</table>
<%
}
%>
</form>
</body>
</html>
