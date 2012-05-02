<%@ include file="/Jsp/taglib.jsp"%>
<%@page import="com.array.testprep.model.TestTaker"%>

<html>

<head>

<title><Spring:message code="label1sitemap" /></title>
<link rel="stylesheet" type="text/css" href="CSS/styles.css" />
<%
TestTaker testTaker = (TestTaker) session.getAttribute("testTaker");
%>

</head>

<body>
<table class="contentPageHeader">
	<tr>
		<td>&nbsp;SiteMap</td>
	</tr>
</table>
<div class="links">

<p><font size="5"><b><font color="#FF0000"><Spring:message
	code="label2sitemap" /></font><Spring:message code="label3sitemap" /></b></font></p>
<p><a href="/ilib/home.html"><Spring:message
	code="label4sitemap" /></a></p>
<p><a href="/ilib/aboutUs.html"><Spring:message
	code="label5sitemap" /></a></p>
<p><a href="/ilib/contactUs.html"><Spring:message
	code="label6sitemap" /></a></p>
<p><a href="/ilib/help.html"><Spring:message
	code="label7sitemap" /></a></p>
<p><a href="/ilib/advancedSearch.html"><Spring:message
	code="label8sitemap" /></a></p>
<p><font size="5"><b><Spring:message code="label9sitemap" /></b></font></p>
<p><a href="/ilib/viewProfile.html"><Spring:message
	code="label10sitemap" /></a></p>
<p><a
	href="/ilib/updateProfile.html?action=update&id=<%=testTaker.getTestTakerId()%>"><Spring:message
	code="label11sitemap" /></a></p>
<p><a href="/ilib/changePassword.html"><Spring:message
	code="label12sitemap" /></a></p>
<%
if (testTaker.getIsAdmin().equalsIgnoreCase("n")) {
%>
<p><b><font size="5"><Spring:message
	code="label20sitemap" /></font></b></p>
<p><a href="/ilib/uploadResources.html"><Spring:message
	code="label13sitemap" /></a></p>
<%
} else {
%>
<p><b><font size="5"><Spring:message
	code="label14sitemap" /></font></b></p>
<p><a href="/ilib/addUsefulLinks.html"><Spring:message
	code="label15sitemap" /></a></p>
<p><a href="/ilib/viewLinks.html"><Spring:message
	code="label16sitemap" /></a></p>
<p><b><font size="5"><Spring:message
	code="label17sitemap" /></font></b></p>
<p><a href="/ilib/addNewTestType.html"><Spring:message
	code="label18sitemap" /></a></p>
<p><a href="/ilib/updateDeleteTestType.html"><Spring:message
	code="label19sitemap" /></a></p>
<p><b><font size="5"><Spring:message
	code="label20sitemap" /></font></b></p>
<p><a href="/ilib/uploadResources.html"><Spring:message
	code="label13sitemap" /></a></p>
<p><a href="/ilib/editResources.html"><Spring:message
	code="label21sitemap" /></a></p>
<p><a
	href="/ilib/searchResults.html?action_search=search&ISAPPROVED=n"><Spring:message
	code="label22sitemap" /></a></p>
<%
}
%>
</div>
</body>

</html>
