<%@ include file="/Jsp/taglib.jsp"%>
<script type="text/javascript">
<!--
	function validateAdd(form)
	{
	if(addAnswer.choice.value=='' || addAnswer.choice.value==null)
	{
		alert("QUESTION IS REQURIED FIELD");
		addAnswer.choice='';
		addAnswer.choice.focus();
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
		<title>Add New Category</title>
		<link rel="stylesheet" type="text/css" href="CSS/styles.css" />
	</head>

	<body onLoad="addAnswer.choice.focus();">
		<table class="contentPageHeader">
			<tr>
				<td>
					&nbsp;Answer
				</td>
			</tr>
		</table>

		<form id="addAnswer" name="addAnswer" method="post" action=""
			onsubmit="return validateAdd(this);">

			<div class="tableHeader" id="tableHeader">
				<p align="center">
					Add New Choice
				</p>
			</div>
			<table width="26%" class="table" cellpadding="0" cellspacing="0"
				align="center">
				<tr class="even">
					<Spring:bind path="answerBank.choice">
						<td>
							<label>
								<span class="style6">*</span>Choice:
							</label>
						</td>
						<td>
							<label>
							</label>

							<div align="left">
							
							<textarea rows="5" cols="80" name="choice" id="choice">
							
								<c:out value='${status.value}'/> 
								</textarea>
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
						&nbsp;&nbsp;&nbsp;&nbsp;
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


