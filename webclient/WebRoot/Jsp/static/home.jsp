<%@ include file="/Jsp/taglib.jsp"%>
<%@page import="com.array.testprep.util.Constants"%>

<%
	boolean msgStatus = true;
	//Boolean.parseBoolean((String)session.getAttribute("msgStatus"));
%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/styles.css" />
<script type="text/javascript" src="js/swapimg.js"></script>

</head>
<body>
<table class="contentPageHeader">
	<tr>
		<td>&nbsp;Home</td>
	</tr>
</table>

<form action="" method="POST">
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>

Welcome , <c:out
	value="${testTaker.emailId}"></c:out> 
	<br>
	<p>&nbsp;</p>
	 <a
			href="allTest.html?action=list"><img id="startTest" src="images/startTest.gif"
			alt="" align="top" border="0" width="100" height="23"
			onMouseOver="SwapImage(1,0,'startTest','images/startTest_over.gif')"
			onMouseOut="SwapImage(0,0,'startTest','images/startTest.gif')"></a>
	
	<%
 session.getAttribute("emailId");
 %> <%
 	if (request.getParameter(Constants.STATUS_MSG) != null) {
 %>
 

 
<table class="statusTable">

	<tr>
		<td>
		<p align="left"><%=request.getParameter(Constants.STATUS_MSG)%>
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
