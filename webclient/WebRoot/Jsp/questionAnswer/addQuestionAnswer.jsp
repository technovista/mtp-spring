<%@ include file="/Jsp/taglib.jsp"%>
<script type="text/javascript">
<!--


	function onChangeQuestion()
	{
		
	}
	function validateAdd(form)
	{
	if(addQuestionAnswer.question.value=='' || addQuestionAnswer.question.value==null)
	{
		alert("QUESTION IS REQURIED FIELD");
		addQuestionAnswer.question='';
		addQuestionAnswer.question.focus();
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

	<body onLoad="addQuestionAnswer.question.focus();">
		<table class="contentPageHeader">
			<tr>
				<td>
					&nbsp;Question
				</td>
			</tr>
		</table>

		<form id="addQuestionAnswer" name="addQuestionAnswer" method="post" action=""
			onsubmit="return validateAdd(this);">

			<div class="tableHeader" id="tableHeader">
				<p align="center">
					 Question-Answer
				</p>
			</div>
			<table width="26%" class="table" cellpadding="0" cellspacing="0"
				align="center">
				<tr class="even">
					<Spring:bind path="questionAnswerRelationship.questionId">
						<td>
							<label>
								<span class="style6">*</span>Question:
							</label>
						</td>
						<td>
							<label>
							</label>

							<div align="left">
							<select name="questionId" size="15"  onchange="onChangeQuestion();" >
									<c:forEach var="con" items="${questionBank}">
										<option value="<c:out value='${con.questionId}'/>"
											>
											<c:out value="${con.question}" />
										</option>
											
											<c:set var="index" value="<c:out value='${con.numOfChoice}'/>"></c:set>
									</c:forEach>
								</select>
								
								
							
							</div>
							<div class="errorMessage">
								<c:out value="${status.errorMessage}" />
							</div>

						</td>
					</Spring:bind>
				</tr>

				
						
					
				
				<tr class="odd">
					<Spring:bind path="questionAnswerRelationship.answerIds">
						<td>
							<label>
								<span class="style6">*</span>Correct Answer:
							</label>
						</td>
						<td>
							<label>
							</label>

							<div align="left">
							
							<% for(int i = 0 ; i<4;i++) {%>
								<select name="answerIds">
									<c:forEach var="con" items="${answerBank}">
										<option value="<c:out value='${con.answerId}'/>"
											selected="selected">
											<c:out value="${con.choice}" />
										</option>

									</c:forEach>
								</select>
								<br>
								<%} %>
								
							</div>
							<div class="errorMessage">
								<c:out value="${status.errorMessage}" />
							</div>

						</td>
						
						
					</Spring:bind>
				</tr>
				
				
	<tr class="even">
					<Spring:bind path="questionAnswerRelationship.testObjectiveId">
						<td>
							<label>
								<span class="style6">*</span>Question Type:
							</label>
						</td>
						<td>
							<label>
							</label>

							<div align="left">
								<select name="testObjectiveId">
									<c:forEach var="con" items="${testObjective}">
										<option value="<c:out value='${con.testObjectiveId}'/>"
											selected="selected">
											<c:out value="${con.testObjective}" />
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



