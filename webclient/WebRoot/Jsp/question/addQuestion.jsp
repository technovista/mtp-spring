<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.List"%>
<%@ include file="/Jsp/taglib.jsp"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.array.testprep.model.AnswerBank"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Untitled Document</title>
		<link rel="stylesheet" type="text/css" href="CSS/styles.css" />
		<script type="text/javascript">
function addElement() {
  var ni = document.getElementById('myDiv');
  var numi = document.getElementById('theValue');
  var num = (document.getElementById("theValue").value -1)+ 2;
  numi.value = num;
  
  var divIdName = "my"+num+"Div";
  var newdiv = document.createElement('div');
  newdiv.setAttribute("id",divIdName);
  
   var newdivlabel = document.createElement('div');
  newdivlabel.setAttribute("id",divIdName);
    // New Label
  var newLabel =document.createElement('div');  
  newLabel.setAttribute("id",divIdName);
  newdivlabel.innerHTML = "Choice "  ;
  newLabel.innerHTML =  " <a href=\"javascript:;\" onclick=\"removeElement(\'"+divIdName+"\')\">  Remove the choice </a>";
  // New Text Area
  var newTextareaName = "choices"+num;
  var newTextarea = document.createElement('textarea');
  newTextarea.setAttribute("id",newTextareaName);
  
  // New Check Box
  var newCheckBoxName = "isCorrectAnswer"+num;
  var newCheckBox = document.createElement('checkbox');
   newCheckBox.setAttribute("id",newCheckBoxName);
   
    ni.appendChild(newdiv);
	newdiv.appendChild(newdivlabel);
	newdiv.appendChild(newCheckBox);
	  newdiv.appendChild(newTextarea);
	  newdiv.appendChild(newLabel);
	  
}

function removeElement(divNum) {
  var d = document.getElementById('myDiv');
  var olddiv = document.getElementById(divNum);
  d.removeChild(olddiv);
}
</script>
		<style type="text/css" media="screen">
.updated {
	background: #FFE2AF;
	color: #000;
	border: 2px solid #ccc;
	padding: 1em;
}
</style>
	</head>

	<body>
		<form id="form1" name="form1" method="post" action="">
			<table width="882" border="0" cellpadding="2">
				<tr>
					<th width="79" scope="col">
						&nbsp;
					</th>
					<th width="286" rowspan="5" scope="col">
						<Spring:bind path="questionBank.question">
							<textarea name="question" id="question" cols="45" rows="5"></textarea>
						</Spring:bind>
					</th>
					<th width="76" scope="col">
					<span class="style6">*</span>Test Objective: 
					</th>
					<th width="415" scope="col">
						Choices
					</th>
				</tr>
				<tr>
					<th scope="col">
						Question
					</th>
					<th rowspan="4" scope="col">					
						
						<Spring:bind path="questionBank.testObjectiveId">						
											

							<div align="left">
								<select name="testObjectiveId">
								
									<c:forEach var="con" items="${testObjective}">
									
										<option value="<c:out value='${con.testObjectiveId}'/>"
											>
											<c:out value="${con.testObjectiveType}" />
										</option>
										
									</c:forEach>
									
								</select>
							</div>
							<div class="errorMessage">
								<c:out value="${status.errorMessage}" />
							</div>

						
					</Spring:bind>
						
						
					</th>
					<th rowspan="7" scope="col">
						<div align="left">
							<input type="hidden" value="0" id="theValue" />
						</div>
						<div id="myDiv">
						</div>
							<Spring:bind path="questionBank.choices">
						
						<div align="left">
							<a href="javascript:;" onclick="addElement();">Add Choice</a>
						</div>
						</Spring:bind>
					</th>
				</tr>
				<tr>
					<th scope="col">
						&nbsp;
					</th>
				</tr>
				<tr>
					<th scope="col">
						&nbsp;
					</th>
				</tr>
				<tr>
					<th scope="col">
						&nbsp;
					</th>
				</tr>
				<tr>
					<th scope="col">
						Explanation
					</th>
					<th scope="col">
					<Spring:bind path="questionBank.explanation">
						<textarea name="explanation" id="explanation" cols="45" rows="5"></textarea>
						</Spring:bind>
					</th>
					<th scope="col">
						&nbsp;
					</th>
				</tr>
				<tr>
					<th scope="col">
						&nbsp;
					</th>
					<th scope="col">
						&nbsp;
					</th>
					<th scope="col">
						&nbsp;
					</th>
				</tr>
				<tr>
					<th scope="col">
						Reference
					</th>
					<th scope="col">
					<Spring:bind path="questionBank.reference">
						<textarea name="reference" id="reference" cols="45" rows="5"></textarea>
						</Spring:bind>
					</th>
					<th scope="col">
						&nbsp;
					</th>
				</tr>
				<tr>
					<th scope="col">
						&nbsp;
					</th>
					<th colspan="3" scope="col">
						&nbsp;
					</th>
				</tr>
				<tr>
					<th height="61" scope="col">
						<p>
							Challenge Level
						</p>
					</th>
					<th scope="col">
					<Spring:bind path="questionBank.challengeLevel">
						<select name="challengeLevel" id="challengeLevel">
						<option>1</option>
						<option>2</option>
						</select>
						</Spring:bind>
					</th>
					<th scope="col">
						&nbsp;
					</th>
					<th scope="col">
						&nbsp;
					</th>
				</tr>
				<tr>
				
					
						<Spring:bind path="questionBank.questionTypeId">
						<td>
							<label>
								<span class="style6">*</span>questionType: 
							</label>
						</td>
						<td>
							<label>
							</label>

							<div align="left">
								<select name="questionTypeId">
									<c:forEach var="con" items="${questionType}">
									<%System.out.print("In loop"); %>
										<option value="<c:out value='${con.questionTypeId}'/>"
											>
											<c:out value="${con.questionType}" />
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
				<tr>
				<th>
					<input type="submit" class="submitButton" value="<%=request.getParameter("action") %>"
							name="action"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" class="submitButton" value="Reset"
							name="reset"/>
				</th>
				</tr>
			</table>
		</form>
	</body>
</html>
