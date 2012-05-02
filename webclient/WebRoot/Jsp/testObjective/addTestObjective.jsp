<%@ include file="/Jsp/taglib.jsp"%>
<script type="text/javascript">
<!--
	function validateAdd(form)
	{
	if(addTestObjective.testObjectiveType.value=='' || addtestObjective.testObjectiveType.value==null)
	{
		alert("QUESTION IS REQURIED FIELD");
		addTestObjective.testObjectiveType='';
		addTestObjective.testObjectiveType.focus();
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
		<title>Add New Question Type</title>
		<link rel="stylesheet" type="text/css" href="CSS/styles.css" />
	</head>

	<body onLoad="addTestObjective.testObjectiveType.focus();">
		<table class="contentPageHeader">
			<tr>
				<td>
					&nbsp;TestObjective
				</td>
			</tr>
		</table>

		<form id="addTestObjective" name="addTestObjective" method="post" action=""
			onsubmit="return validateAdd(this);">

			<div class="tableHeader" id="tableHeader">
				<p align="center">
					Add New TestObjective
				</p>
			</div>
			<table width="26%" class="table" cellpadding="0" cellspacing="0"
				align="center">
				<tr class="even">
					<Spring:bind path="testObjective.testObjectiveType">
						<td>
							<label>
								<span class="style6">*</span>TestObjective:
							</label>
						</td>
						<td>
							<label>
							</label>

							<div align="left">
							
							<textarea rows="5" cols="80" name="testObjectiveType" id="testObjectiveType">
							
								<c:out value='${status.value}'/> 
								</textarea>
							</div>
							<div class="errorMessage">
								<c:out value="${status.errorMessage}" />
							</div>

						</td>
					</Spring:bind>
				</tr>

				<tr class="odd">
					<Spring:bind path="testObjective.testId">
						<td>
							<label>
								<span class="style6">*</span>Test: 
							</label>
						</td>
						<td>
							<label>
							</label>

							<div align="left">
								<select name="testId">
									<c:forEach var="con" items="${testType}">
									<%System.out.print("In loop"); %>
										<option value="<c:out value='${con.testId}'/>"
											selected="selected">
											<c:out value="${con.testName}" />
										</option>

									</c:forEach>
								</select>
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



