

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
		<title>Search Results</title>
		<script type="text/javascript" src="js/aa.js"></script>
		<script type="text/javascript" src="js/prototype.js"></script>
		<link type="text/css" rel="stylesheet" href="CSS/displaytag.css" />

	</head>
	<body>
		<%
			TestTaker testTaker = (TestTaker) session.getAttribute("testTaker");
			System.out.print("\n\n\t\t UserId =" + testTaker.getTestTakerId());
			System.out.print("\n\n\t\t isAdmin =" + testTaker.getIsAdmin());
		%>
		<%
			String url;
			String id;
			if (request.getParameter("ID") != null) {
				url = "searchResults.html?action_search=search&ID="
						+ request.getParameter("ID");
			} else if (request.getParameter("ISAPPROVED") != null) {
				url = "searchResults.html?action_search=search&ISAPPROVED="
						+ request.getParameter("ISAPPROVED");
			} else {
				url = "searchBookResults.html";
			}
		%>
		<form method="POST" name="searchForm">
			<c:choose>
				<c:when test="${!empty model.testObjectiveList}">
					<aa:zone name="testObjectiveTable">

						<display:table name="model.testObjectiveList" class="table"
							requestURI="<%=url%>" id="testObjectiveList" export="false"
							excludedParams="*" pagesize="10" sort="list">



							<display:column title="Select">
							<Spring:bind path="testObjective.testObjectiveId">
								<input name="testObjectiveId" type="checkbox" id="testObjectiveId" 
									value="<c:out value="${testObjectiveList.testObjectiveId}"  />">
							</Spring:bind>
							</display:column>
							<display:column sortProperty="testObjective" sortable="true"
								media="html" title="TestObjective">
								<%
									if (testTaker.getIsAdmin().equalsIgnoreCase("y")) {
								%>
								<a
									href='editResources.html?Add=search&id=<c:out value="${testObjectiveList.testObjectiveId}"/>'>
									<c:out value="${testObjectiveList.testObjective}" /> </a>
								<%
									} else {
								%>
								<c:out value="${testObjectiveList.testObjective}" />
								<%
									}
								%>

							</display:column>

							<display:column sortable="true" title="TestType">
								<c:out value='${testObjectiveList.testType.testTypeName}' />
							</display:column>

						</display:table>
					</aa:zone>
					<table>
						<tr>
							<td>
							
								<a href='questionAnswer.html?Add=<%%>'>
								 <Spring:message
										code="starttest"></Spring:message> </a>
								<input  type="submit" class="submitButton" name="action"
									value="<Spring:message code="starttest"></Spring:message>" />
							</td>
						</tr>
					</table>
					<%
						if (request.getParameter(Constants.STATUS_MSG) != null) {
					%>
					<table class="statusTable">
						<tr>
							<td>
								<p align="left">
									TestObjective<%=request.getParameter(Constants.STATUS_MSG)%>
									successful
								</p>
							</td>
						</tr>
					</table>
					<%
						}
					%>
				</c:when>
				<c:otherwise>
					<br>
					<br>
    No results to display.Please try searching again. <a
						href=advancedSearch.html>Click me</a>
				</c:otherwise>
			</c:choose>
			<script type="text/javascript">

    ajaxAnywhere.getZonesToReload = function() { return "testObjectiveTable" }
    ajaxAnywhere.onAfterResponseProcessing = function() {
        replaceLinks();
       initialize(); // re-initialize lightbox
    }
    function replaceLinks() {
        // replace all the links in <thead> with onclick's that call AjaxAnywhere
        var sortLinks = $('testObjectiveList').getElementsByTagName('thead')[0]
                                     .getElementsByTagName('a');
        ajaxifyLinks(sortLinks);
        if (document.getElementsByClassName('pagelinks').length > 0) {
            var pagelinks = document.getElementsByClassName('pagelinks')[0]
                                    .getElementsByTagName('a');
            ajaxifyLinks(pagelinks);
        }
        //highlightTableRows("testObjectiveList");
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
