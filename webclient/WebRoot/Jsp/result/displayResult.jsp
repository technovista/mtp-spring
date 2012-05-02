


<%@ include file="/Jsp/taglib.jsp"%>
<%@page import="com.array.testprep.model.TestObjective"%>
<%@page import="com.array.testprep.model.TestTaker"%>
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
			TestTaker testTaker = (TestTaker) session.getAttribute("testTaker");
			System.out.print("\n\n\t\t emailId =" + testTaker.getTestTakerId());
			System.out.print("\n\n\t\t isAdmin =" + testTaker.getIsAdmin());
		%>
		<%
			String url;
			String id;
			if (request.getParameter("ID") != null) {
				url = "result.html?action_search=search&ID="
						+ request.getParameter("ID");
			} else if (request.getParameter("ISAPPROVED") != null) {
				url = "result.html?action_search=search&ISAPPROVED="
						+ request.getParameter("ISAPPROVED");
			} else {
				url = "result.html";
			}
		%>
		<form method="POST" name="resultForm">



			<c:choose>
				<c:when test="${!empty model.resultList}">


					<aa:zone name="myTable">
					<c:set var="i" value="0"></c:set>
					
						<display:table name="model.resultList" class="table"
							requestURI="<%=url%>" id="resultList" export="false"
							excludedParams="*" pagesize="10" sort="list" >

							<display:column title="Index">
								<c:out value="${i+1}"/>
							</display:column>
							<c:set var="i" value="${i+1}"></c:set>
							
							<display:column title="True/False">
							<a href='question.html?id=<c:out value="${resultList.questionId}"/>'>
							 <c:out value="${resultList.selectedAnswer}"></c:out>
							 </a>
							</display:column>
								<display:column title="CorrectAnswer"><c:out value="${resultList.questionBank.answerBank.choice}"></c:out></display:column>
							
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

    ajaxAnywhere.getZonesToReload = function() { return "myTable" }
    ajaxAnywhere.onAfterResponseProcessing = function() {
        replaceLinks();
       initialize(); // re-initialize lightbox
    }
    function replaceLinks() {
        // replace all the links in <thead> with onclick's that call AjaxAnywhere
        var sortLinks = $('resultList').getElementsByTagName('thead')[0]
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
