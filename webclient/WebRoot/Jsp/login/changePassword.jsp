<%@ include file="/Jsp/taglib.jsp"%>
<script type="text/javascript">
<!--
	function validatePassword(form)
	{

        if(form1.password.value==null || form1.password.value=='')       
        {
            alert(" Current Password is requried field.");
            form1.password.value='';            
            form1.password.focus();
            return false;
        }
		if(form1.newPassword.value==null || form1.newPassword.value=='')       
        {
            alert("New Password is requried field.");
            form1.newPassword.value='';            
            form1.newPassword.focus();
            return false;
        }
        password=form1.newPassword.value;
        if(password.length<6)
        {
            alert("<Spring:message code="alert3registration"/>");
            form1.newPassword.value='';            
            form1.newPassword.focus();
            return false;
        }
			
        if(form1.newPassword.value!=form1.reEnterPassword.value)       
        {
            alert("Your new password entries did not match.");
            form1.newPassword.value='';
            form1.reEnterPassword.value='';
            form1.newPassword.focus();
            return false;
        }        
 	return true;
	}
//--> 
</script>
<style type="text/css">
<!--
.style12 {font-size: 16px; font-weight: bold; }
.style6 {color: #FF0000}
-->
</style>
<html>
<head>
<title>Upload Resources</title>
<link rel="stylesheet" type="text/css" href="CSS/styles.css" />
</head>
<body onLoad="form1.cpassword.focus();">

<table class="contentPageHeader">	
	<tr>
		<td>&nbsp;Profile</td>
	</tr>
</table>
<div align="center" style="width: 828; height: 308">
<p>&nbsp;</p>
<form id="form1" name="form1" method="post"	onsubmit="return validatePassword(this)">
<div class="tableHeader" id="tableHeader">
<p><b>Change Password</b></p>
</div>

<table border="0" cellpadding="3" cellspacing="0" class="table">
	<tr class="even">
		<Spring:bind path="testTaker.firstName">
			<td colspan="2"><strong><font size="5">Hello </font> </strong> <Spring:bind
				path="testTaker.firstName"><b><c:out value='${status.value}' /></b>
			</Spring:bind></td>
		</Spring:bind>
	</tr>

	<tr class="odd">
		<td colspan="2"><b>Change Your
		Password</b></td>
	</tr>
	<tr class="even">
		<td  colspan="2" align="center">Enter your current password and then choose your
		new password.</td>
	</tr>
	<tr class="even">
		<td width="531" colspan="2" height="28" bgcolor="#ACE9FD"
			align="center">Click Save when you're done.</td>
	</tr>

	<tr>
		<td width="231" align="left" bgcolor="#FFFFFF" height="13">&nbsp;		</td>
		<td width="241" bgcolor="#FFFFFF" height="13">&nbsp;</td>
	</tr>

	<tr class="odd">		
		<td >
		<div align="right"><span class="style6">*</span>Enter your <strong>Current
		Password:</strong></div>		</td>
		<Spring:bind path="testTaker.password">
		<td ><div align="left"><b> 
		  <input
			type="password" name="password" id="password" size="23" value=""/> 
		 <span class="style6"><c:out value="${status.errorMessage}" /></span>
		  </b></div></td>
			</Spring:bind>
	</tr>

	<tr class="even"><Spring:bind path="testTaker.newPassword">
		<td >
		<div align="right"><span class="style6">*</span>Choose a <strong>
		New Password:</strong></div>		</td>
		<td ><div align="left"><b> 
		  <input
			type="password" name="newPassword" id="newPassword" size="23" /> 
		  </b></div></td>
			</Spring:bind>
	</tr>
	<tr class="odd">
		<td>
		<div align="right"><span class="style6">*</span>Confirm your <strong>New
		Password:</strong></div>		</td>
		<td ><div align="left"><b> 
		  <input
			type="password" name="reEnterPassword" id="reEnterPassword" size="23" />
	    </b></div></td>
	</tr>
	<tr>
		<td colspan="2">
		<p align="center">        
		<p align="center">
		  <input type="submit" value="Save" name="action"  class="submitButton"/>
		  &nbsp; &nbsp;&nbsp;
		  <input
			type="reset" value="reset" name="Reset"  class="submitButton"/>
		
		<p align="center"></td>
	</tr>
</table>

</form>
</div>
</body>
</html>