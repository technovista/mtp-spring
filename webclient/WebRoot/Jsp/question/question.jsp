<%@ include file="/Jsp/taglib.jsp"%>

<html>
	<body>

		<form action="post">
			<table  border="1" cellpadding="1" cellspacing="1" width="580">
				<tr>
<td align="left">
					<Spring:bind path="question.question">	
	<pre><c:out value="${status.value}"></c:out>	
	</pre>				
					</Spring:bind>
</td>
				</tr>
				</table>
				<br>				
				<Table width="277" height="176">
				<tr>
				<Spring:bind path="question.testObjective.testObjectiveType">
					<td>
					Test OBjective: &nbsp; 
					</td>
					<td>						
							<c:out value="${status.value}">
							</c:out>						
					</td>
					</Spring:bind>
				</tr>				
				<tr>				<td>
				Choices:
				</td>
				</tr>
				<tr>
				<td>
				<table   border="1" cellpadding="1" cellspacing="1"  width="580">
				
				<Spring:bind path="question.questionAnswerRelationship">
				<%System.out.print("before loop"); %>
				<c:forEach var="con" items="${status.value}">
				<%System.out.print("In ar loop"); %>
										<tr background="#330066">					
										<td>										
												
																			
										<c:if test="${con.isCorrectAnswer=='y'}">
										
											<c:out value="${con.answerBank.choice}" />											
										</c:if>													
										
										<c:if test="${con.isCorrectAnswer=='n'}">
											<c:out value="${con.answerBank.choice}" />
											
										</c:if>	
																	
											
										</td>
										</tr>
													
									</c:forEach>
				
				</Spring:bind>
			</Table>
			</td>
				</tr>
				
				<tr>
					<Spring:bind path="question.explanation">
						<td>
							EXPLANATION: &nbsp;</td>
							<td>
								<c:out value="${status.value}">
				
							</c:out>
							
						</td>
					</Spring:bind>
				</tr>
				<tr>
					<td>
					<input type="button" value="Back" onClick="history.go(-1)">
				

					</td>
				</tr>
			</table>
		</form>
	</body>
</html>