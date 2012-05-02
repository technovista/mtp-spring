<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/Jsp/taglib.jsp"%>
<%@page import="com.array.testprep.model.TestTaker"%>
<%@page import="com.array.testprep.util.Constants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>View Profile</title>
<link rel="stylesheet" type="text/css" href="CSS/styles.css" />
<style type="text/css">

</style>
</head>

<body>
<table class="contentPageHeader">
	<tr>
		<td>&nbsp;Profile</td>
	</tr>
</table>

<%
TestTaker testTaker = (TestTaker) session.getAttribute("testTaker");
%>

<form id="form1" name="form1" method="post">
<div class="tableHeader" id="tableHeader">
<p align="center">Profile</p>
</div>
<table border="0" class="table" cellpadding="3" cellspacing="0"
	align="center" width="30%">


	<Spring:bind path="testTaker.emailId">
		<tr class="odd">
			<td width="59%" align="right">EmailId:</td>
			<td width="41%">

			<div align="left"><strong> </strong> <strong><c:out
				value="${status.value}"></c:out> </strong></div>

			</td>
		</tr>
	</Spring:bind>
	<tr>
		<th colspan="2">Your Personal Details...</th>
	</tr>
	<Spring:bind path="testTaker.firstName">
		<tr class="odd">
			<td align="right">First Name:</td>
			<td>
			<div align="left"><strong> </strong> <strong><c:out
				value="${status.value}"></c:out> </strong></div>
			</td>
		</tr>
	</Spring:bind>
	<Spring:bind path="testTaker.lastName">
		<tr class="even">
			<td align="right">Last Name:</td>
			<td>
			<div align="left"><strong> </strong> <strong><c:out
				value="${status.value}"></c:out> </strong></div>
			</td>
		</tr>
	</Spring:bind>
		
	<tr class="odd">
		<td height="38" colspan="2"><Spring:bind path="testTaker.testTakerId">
			<p align="center"><a
				href='updateProfile.html?action=update&id=<c:out value='${status.value}'/>'>
			Update</a></p>
		</Spring:bind><Spring:bind path="testTaker.testTakerId"></Spring:bind></td>
		</tr>
	<tr class="last">
		<%
			if (request.getAttribute(Constants.OPERATION_DELETE_USER) != null) {

				if (request.getAttribute(Constants.OPERATION_DELETE_USER)
				.equals("delete")) {
		%>
		<td>
		<div align="center"><input type="submit" class="submitButton"
			value="Delete" name="action" /></div>
		</td>

		<%
			}
			}
		%>
	</tr>
</table>
</form>
</body>
</html>
