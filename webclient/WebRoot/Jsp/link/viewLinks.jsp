<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/Jsp/taglib.jsp"%>
<%@ page import="com.array.testprep.model.TestTaker"%>
<%@page import="com.array.testprep.util.Constants"%>
<html>
<head>

<script type="text/javascript" src="js/aa.js"></script>
<script type="text/javascript" src="js/prototype.js"></script>
<link type="text/css" rel="stylesheet" href="CSS/displaytag.css" />

</head>
<body>
<table class="contentPageHeader">
	<tr>
		<td> Link</td>
	</tr>
</table>

<%
TestTaker testTaker = (TestTaker) session.getAttribute("testTaker");%>

<form action="" method="POST"><aa:zone name="linkTable">
	<display:table name="model.linkList" class="table"
		requestURI="viewLinks.html?action=list" id="linkList" export="true"
		excludedParams="*" pagesize="10" sort="list">

		<display:column property="linkId" media="csv excel xml pdf"
			titleKey="link.linkId" />

		<display:column sortProperty="link" sortable="true" media="html"
			titleKey="link.link">
			<a
				href='http://<c:out value="${linkList.link}"/> '>
				<c:out value="${linkList.link}"/> </a>	

		</display:column>
		<display:column sortable="true" media="html" titleKey="link.testType">
			<c:out value='${linkList.testType.testTypeName}' />
		</display:column>
<%
	if (testTaker.getIsAdmin().equalsIgnoreCase(Constants.ISADMIN)) {
%>
		<display:column sortable="false" titleKey="link.update">
			<a
				href='editUsefulLinks.html?action=update&id=<c:out value="${linkList.linkId}"/>'>
			<img src="images/edit.jpg" border="0"></a>
		</display:column>

		<display:column sortable="false" titleKey="link.delete">
			<a
				href='editUsefulLinks.html?action=delete&id=<c:out value="${linkList.linkId}"/>'>
			<img src="images/delete.png" border="0"> </a>
		</display:column>
		<%
			}
		%>

	</display:table>
</aa:zone> <%
 	if (request.getParameter(Constants.STATUS_MSG) != null) {
 %>
<table class="statusTable">

	<tr>
		<td>
		<p align="left">Link <%=request.getParameter(Constants.STATUS_MSG)%>
		successful</p>
		</td>
	</tr>

</table>
<%
}
%>
</form>
</body>
</html>
