

<%@page import="com.array.testprep.model.TestObjective"%>
<%@page import="com.array.testprep.model.TestTaker"%>
<%@ include file="/Jsp/taglib.jsp"%>

<%@page import="java.util.StringTokenizer"%>
<%@page import="java.io.*"%>
<%@page import="java.util.Properties"%>
<%@page import="com.array.testprep.util.ContextProvider"%>
<%@page import="org.springframework.context.ApplicationContext"%>

<%@page import="com.array.testprep.util.Constants"%>
<html>
	<head>
		<title>Question Objectives</title>
		<script type="text/javascript" src="js/aa.js"></script>
		<script type="text/javascript" src="js/prototype.js"></script>
		<link type="text/css" rel="stylesheet" href="CSS/displaytag.css" />

	</head>
	<body>
		<%
		TestTaker testTaker = (TestTaker)session.getAttribute("testTaker");
			
		
		%>
		<%
			String url;
			String id;
			if (request.getParameter("ID") != null) {
				url = "viewAllQuestions.html?action_search=search&ID="
						+ request.getParameter("ID");
			} else if (request.getParameter("ISAPPROVED") != null) {
				url = "viewAllQuestions.html?action_search=search&ISAPPROVED="
						+ request.getParameter("ISAPPROVED");
			} else {
				url = "viewAllQuestions.html";
			}
		%>
		<form method="POST" name="testForm">


<%System.out.println("In test"); %>
			<c:choose>
				<c:when test="${!empty model.allQuestionBankList}">

					<%System.out.println("In loop"); %>

					<aa:zone name="myTable">
<%System.out.println("In aa:zone"); %>

						

						<display:table name="model.allQuestionBankList" class="table"
							requestURI="viewAllQuestions.html?action=list" id="questionBank" 
							excludedParams="*" pagesize="10" sort="list">
<%System.out.println("In table view QuestionBank"); try{%>
							<display:column  title="QuestionId" >
							<a href="question.html?id=<c:out value="${questionBank.questionId}"/>">
							<c:out value="${questionBank.questionId}"/>
							</a>
						</display:column>
							<display:column sortProperty="questionId" sortable="true"
								 title="Question">

								<c:out value='${questionBank.question}' />
							</display:column>							
							<display:column  
								 title="Correct Answer">
										
							<div align="left">
									<c:forEach var="con" items="${questionBank.questionAnswerRelationship}">
										<c:if test="${con.isCorrectAnswer=='y'}">
											<c:out value="${con.answerBank.choice}" />
										</c:if>										
									</c:forEach>									
							</div>
							<div class="errorMessage">
								<c:out value="${status.errorMessage}" />
							</div>

						
					
									
							
							</display:column>

				<display:column  title="Delete" >
							<a href="addQuestion.html?action=Delete&id=<c:out value="${questionBank.questionId}"/>">
							<img id="startTest" src="images/delete.png"	>
							</a>


						</display:column>
						
						<display:column  title="Update" >
							<a href="addQuestion.html?action=Update&id=<c:out value="${questionBank.questionId}"/>">
							<img id="startTest" src="images/edit.jpg"	>
							</a>


						</display:column>

<%}catch(Exception e){
e.printStackTrace();
} %>
						</display:table>

					</aa:zone>
						
				</c:when>
				<c:otherwise>
					<br>
					<br>
    No results to display.Please try searching again. <a
						href="advancedSearch.html">Click me</a>
				</c:otherwise>
			</c:choose>

			<script type="text/javascript">

    ajaxAnywhere.getZonesToReload = function() { return "allQuestionBankList" }
    ajaxAnywhere.onAfterResponseProcessing = function() {
        replaceLinks();
       initialize(); // re-initialize lightbox
    }
    function replaceLinks() {
        // replace all the links in <thead> with onclick's that call AjaxAnywhere
        var sortLinks = $('viewAllQuestionsList').getElementsByTagName('thead')[0]
                                     .getElementsByTagName('a');
        ajaxifyLinks(sortLinks);
        if (document.getElementsByClassName('pagelinks').length > 0) {
            var pagelinks = document.getElementsByClassName('pagelinks')[0]
                                    .getElementsByTagName('a');
            ajaxifyLinks(pagelinks);
        }
        //highlightTableRows("resultList");
        var editable = document.getElementsByClassName('editable');
        for (i=0; i < editable.length; i++) {
            var userId = editable[i].parentNode.parentNode.getElementsByTagName("a")[0].innerHTML;
            new Ajax.InPlaceEditor(editable[i].id, 'user-ajax.html?id=' + userId);
        }
    }
    function ajaxifyLinks(links) {
        for (i=0; i < links.length; i++) {
            links[i].onclick = function() {
           
           
           
           
                ajaxAnywhere.getAJAX(this.href); 
                return false;
            }
        }
    }
    replaceLinks();
</script>
		</form>
	</body>
</html>




















