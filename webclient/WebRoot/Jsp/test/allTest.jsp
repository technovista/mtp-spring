

<%@page import="com.array.testprep.model.TestObjective"%>
<%@page import="com.array.testprep.model.TestTaker"%>
<%@page import="com.array.testprep.model.Test"%>
<%@ include file="/Jsp/taglib.jsp"%>

<%@page import="java.util.StringTokenizer"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*" %>
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
		<script type="text/javascript">
	
	</script>

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
				url = "allTest.html";
			} else if (request.getParameter("ISAPPROVED") != null) {
				url = "allTest.html?ISAPPROVED="
						+ request.getParameter("ISAPPROVED");
			} else {
			}
		%>
		<form method="POST" name="testForm">	
			
					
			
			<c:choose>
				<c:when test="${!empty model.testList}">


					<aa:zone name="myTable">

						<%try{ %>
						<display:table name="model.testList" class="table"
							requestURI="allTest.html?action=list" id="test" 
							excludedParams="*" pagesize="10" sort="list">

							
							

							<display:column sortProperty="testName" sortable="true"
								media="html" title="Test">
								<%
									if (testTaker.getIsAdmin().equalsIgnoreCase("y")) {
								%>
								<a
									href='testObjective.html?testId=<c:out value="${test.testId}"/>'>
									<c:out value="${test.testName}" /> </a>
								<%
									} else {
								%>
							<a
									href='testObjective.html?testId=<c:out value="${test.testId}"/>'>
									<c:out value="${test.testName}" /> </a>
								<%
									}
								%>

							</display:column >
							
							<display:column sortProperty="testType" sortable="true"  title="Type">
							<c:out value="${test.testType}" ></c:out>
							</display:column >
							<display:column sortProperty="passingPercentage"  title="Passing%">
						<c:out value="${test.passingPercentage}" ></c:out>
							
							</display:column>
							<display:column sortProperty="totalNumberOfQuestion"  title="TotalQuestion">
							<c:out value="${test.totalNumberOfQuestion}" ></c:out>
							</display:column>

							





						</display:table>

<%}catch(Exception e) {
e.printStackTrace();
}%>
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




















