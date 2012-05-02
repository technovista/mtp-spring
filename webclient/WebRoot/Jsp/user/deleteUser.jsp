<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/Jsp/taglib.jsp"%>
<%@page import="com.array.testprep.model.TestTaker"%>

<html>
<head>

<title>Insert title here</title>
</head>
<body>

<table class="contentPageHeader">
	<tr>
		<td>&nbsp;User</td>
	</tr>
</table>

<form id="form1" name="form1" method="post">
<p><strong>Profile</strong></p>
<table border="0" cellpadding="0" cellspacing="2"
	style="border-collapse: collapse" bordercolor="#111111" width="61%"
	id="AutoNumber1" height="813">
	<tr>
		<td colspan="2" align="left" height="37">
		<div align="left" class="style12">Your emailID &amp;
		Password...</div>
		</td>
	</tr>
	<Spring:bind path="testTaker.emailID">
		<tr>
			<td width="45%" bgcolor="#ACE9FD" align="left" height="36">
			<div align="right"><strong>EmailID:</strong></div>
			</td>
			<td width="55%" bgcolor="#ACE9FD" height="36" align="left">
			<div align="left"><strong> <c:out
				value="${status.value}"></c:out> </strong></div>
			</td>
		</tr>
	</Spring:bind>
	<tr>
		<td colspan="2" align="left" height="32"><span class="style12">Your
		Details...</span></td>
	</tr>
	<Spring:bind path="testTaker.firstName">
		<tr>
			<td width="45%" bgcolor="#ACE9FD" align="left" height="1">
			<div align="right"><strong>First Name:</strong></div>
			</td>
			<td width="55%" bgcolor="#ACE9FD" height="1" align="left">&nbsp;
			<div align="left"><strong><c:out
				value="${status.value}"></c:out></strong></div>
			</td>
		</tr>
	</Spring:bind>
	<Spring:bind path="testTaker.lastName">
		<tr>
			<td width="45%" align="left" bgcolor="#66CCFF" height="36">
			<div align="right"><font
				style="font-size: 13px; font-weight: 700" color="#000000"
				face="Arial"><strong>Last Name:</strong></font></div>
			</td>
			<td width="55%" bgcolor="#66CCFF" height="1" align="left">&nbsp;
			<div align="left"><strong><c:out
				value="${status.value}"></c:out></strong></div>
			</td>
		</tr>
	</Spring:bind>
	
	
	
	
	
	
	
	
	<tr>
		<td colspan="2" align="left" height="19">&nbsp;</td>
	</tr>
	
	

	
	
	
	<tr>
		<td align="left" bgcolor="#ACE9FD" height="38">
		<td width="55%" bgcolor="#ACE9FD" height="38">
	</tr>


	<tr>
		<Spring:bind path="testTaker.testTakerId">
			<input type="hidden" name="id"
				value=" <c:out value='${status.value}'/> ">
		</Spring:bind>
	</tr>
	<tr>
		<td colspan="2" align="left" height="102">
		<div align="right">
		<p align="center">&nbsp;</p>
		<p align="center"><input type="submit" value="Delete"
			name="action" /> &nbsp;&nbsp;&nbsp;&nbsp;</p>
		<p>&nbsp;</p>
		</div>
		</td>
	</tr>

</table>
<p>&nbsp;</p>
</form>

</body>
</html>
