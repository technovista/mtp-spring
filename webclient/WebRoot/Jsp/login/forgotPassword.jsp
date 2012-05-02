<%@ include file="/Jsp/taglib.jsp"%>


<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>Forgot Password</title>
<link rel="stylesheet" type="text/css" href="CSS/styles.css" />
<script>
        function validationCheck(form)
        {
        		userName=form1.userName.value;
        		email=form1.email.value;
        	if(userName.length==0&&email.length==0)
        	{
            	alert("<Spring:message code="alert1forgotpassword"/>");
            	form1.userName.focus();
            	return false;
        	}
        	if(userName.length!=0&&email.length!=0)
        	{
        		alert("<Spring:message code="alert2forgotpassword"/>");
        		form1.email.value='';
        		form1.userName.value='';
        		return false;
        	}
        return true;
        }
        
        
        </script>
<style type="text/css">
<!--
.style2 {font-size: small}
-->
</style>
</head>

<body onLoad="testTaker.emailId.focus();">

<form name="form1" id="form1" method="post"
	onsubmit="return validationCheck(this)">
<div class="tableHeader" id="tableHeader">
<p><b>Forgot your Password?</b></p>
</div>

<b><Spring:message code="labelforgotpassword" /></b> <br>
<br>
<div align="right"><br>
    <a href="login.html"><strong> Already Registered???</strong></a>
</div>
<br>
<table width="26%" class="table" align="center" cellpadding=0
	cellspacing=0>
	<tr class="odd">
		<td>
		<div align="left"><label><Spring:message code="emailId" /></label></div>		</td>
		<Spring:bind path="testTaker.emailId">
			<td><input type="text" name="emailId"
				value="<c:out value='${status.value}'/>" size="20" />
			<div class="errorMessage"><c:out value="${status.errorMessage}" /></div>			</td>
		</Spring:bind>
	</tr>
	<tr>
		<td colspan="2" align="center" width="214" height="1"></td>
	</tr>

	
	
	<tr>
		<td height="27" width="87">

		<div align="center">&nbsp;</div>		</td>
		<td height="27" width="127">

		
		<input type="submit"
			class="submitButton" name="action"
			value="<Spring:message code="submit">
		</Spring:message>
	  " /></td>
	</tr>
</table>
</form>
</body>
</html>
