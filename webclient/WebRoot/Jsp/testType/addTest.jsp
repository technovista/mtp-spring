<%@ include file="/Jsp/taglib.jsp"%>
<script type="text/javascript">
<!--
	function validateAdd(form)
	{
	if(addTest.testName.value=='' || addTest.testName.value==null)
	{
		alert("TEST TYPE  IS REQURIED FIELD");
		addTest.testName='';
		addTest.testName.focus();
		return false;
	}
	
	if(!confirm("ARE YOU SURE?")){
		return false;
	}
	return true;
	}
//-->
</script>
<style type="text/css">
<!--
.style6 {
	color: #FF0000
}
-->
</style>

<html>

	<head>
		<title>Add New Test</title>
		<link rel="stylesheet" type="text/css" href="CSS/styles.css" />
	</head>

	<body onLoad="addTest.testName.focus();">
		<table class="contentPageHeader">
			<tr>
				<td>
					&nbsp;Test </td>
			</tr>
		</table>

		<form id="addTest" name="addTest" method="post" action=""
			onsubmit="return validateAdd(this);">

			<div class="tableHeader" id="tableHeader">
				<p align="center">
					Add New Test Type 
				</p>
			</div>
			<table width="26%" class="table" cellpadding="0" cellspacing="0"
				align="center">
				<tr class="even">
					<Spring:bind path="test.testName">
						<td>
							<label>
								<span class="style6">*</span>Test:
							</label>
						</td>
						<td>
							<label>
							</label>

							<div align="left">
							<input name="testName" id="testName"" value="<c:out value='${status.value}'/>" /> 
								
							</div>
							<div class="errorMessage">
								<c:out value="${status.errorMessage}" />
							</div>

						</td>
					</Spring:bind>
				</tr>
				<tr class="last">
					<td colspan="2" align="left" height="101">
						<p>
							&nbsp;
						</p>
						
						<input type="submit" class="submitButton" value="<%=request.getParameter("action") %>"
							name="action">
						&nbsp;&nbsp;
						<input type="reset" class="submitButton" value="Reset"
							name="reset">
							
					
						<p>
							&nbsp;
						</p>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>



