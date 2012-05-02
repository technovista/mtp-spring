



<%@ include file="/Jsp/taglib.jsp"%>

<%@page import="java.util.StringTokenizer"%>
<%@page import="java.io.*"%>
<%@page import="java.util.Properties"%>
<%@page import = "com.array.testprep.util.ContextProvider" %>
<%@page import= "com.array.testprep.model.TestTaker" %>

<%@page import="org.springframework.context.ApplicationContext"%>

<%@page import="com.array.testprep.util.Constants"%>
<html>
	<head>
		<title>Answer Objectives</title>
		<script type="text/javascript" src="js/aa.js"></script>
		<script type="text/javascript" src="js/prototype.js"></script>
		<link type="text/css" rel="stylesheet" href="CSS/displaytag.css" />

	</head>
	<body>
		<%
		 TestTaker testTaker = (TestTaker) session.getAttribute("testTaker");
			
			System.out.print("\n\n\t\t emailId =" + testTaker.getTestTakerId());
			System.out.print("\n\n\t\t isAdmin =" + testTaker.getIsAdmin());
		%>
		<%
			String url;
			String id;
			if (request.getParameter("ID") != null) {
				url = "viewAllAnswers.html?action_search=search&ID="
						+ request.getParameter("ID");
			} else if (request.getParameter("ISAPPROVED") != null) {
				url = "viewAllAnswers.html?action_search=search&ISAPPROVED="
						+ request.getParameter("ISAPPROVED");
			} else {
				url = "viewAllAnswers.html";
			}
		%>
		<form method="POST" name="testForm">


			<%
				System.out.println("In test");
			%>
			<c:choose>
				<c:when test="${!empty model.allAnswerBankList}">

					<%
						System.out.println("In loop");
					%>

					<aa:zone name="myTable">
						<%
							System.out.println("In aa:zone");
						%>



						<display:table name="model.allAnswerBankList" class="table"
							requestURI="viewAllAnswers.html?action=list" id="answerBank"
							excludedParams="*" pagesize="10" sort="list">
							<%
								System.out.println("In table view AnswerBank");
							%>
							<display:column title="AnswerId">
								<a
									href="addAnswer.html?id=<c:out value="${answerBank.answerId}"/>">
									<c:out value="${answerBank.answerId}" /> </a>


							</display:column>
							<display:column sortProperty="answerId" sortable="true"
								title="Answer">

								<c:out value='${answerBank.choice}' />
							</display:column>




							<display:column title="Delete">
								<a
									href="addAnswer.html?action=Delete&id=<c:out value="${answerBank.answerId}"/>">
									<img id="startTest" src="images/delete.png"> </a>


							</display:column>

							<display:column title="Update">
								<a
									href="addAnswer.html?action=Update&id=<c:out value="${answerBank.answerId}"/>">
									<img id="startTest" src="images/edit.jpg"> </a>


							</display:column>


						</display:table>

					</aa:zone>

				</c:when>
				<c:otherwise>
					<br>
					<br>
    No results to display.Please try searching again. <a
						href=advancedSearch.html>Click me</a>
				</c:otherwise>
			</c:choose>

			<script type="text/javascript">
	ajaxAnywhere.getZonesToReload = function() {
		return "allAnswerBankList"
	}
	ajaxAnywhere.onAfterResponseProcessing = function() {
		replaceLinks();
		initialize(); // re-initialize lightbox
	}
	function replaceLinks() {
		// replace all the links in <thead> with onclick's that call AjaxAnywhere
		var sortLinks = $('viewAllAnswersList').getElementsByTagName('thead')[0]
				.getElementsByTagName('a');
		ajaxifyLinks(sortLinks);
		if (document.getElementsByClassName('pagelinks').length > 0) {
			var pagelinks = document.getElementsByClassName('pagelinks')[0]
					.getElementsByTagName('a');
			ajaxifyLinks(pagelinks);
		}
		//highlightTableRows("resultList");
		var editable = document.getElementsByClassName('editable');
		for (i = 0; i < editable.length; i++) {
			var userId = editable[i].parentNode.parentNode
					.getElementsByTagName("a")[0].innerHTML;
			new Ajax.InPlaceEditor(editable[i].id,
					'user-ajax.html?id=' + userId);
		}
	}
	function ajaxifyLinks(links) {
		for (i = 0; i < links.length; i++) {
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




















