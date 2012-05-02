

<%@page import="com.array.testprep.model.Test"%>
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
		<title>Test Type</title>
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
				url = "viewAlltest.html?action_search=search&ID="
						+ request.getParameter("ID");
			} else if (request.getParameter("ISAPPROVED") != null) {
				url = "viewAlltest.html?action_search=search&ISAPPROVED="
						+ request.getParameter("ISAPPROVED");
			} else {
				url = "viewAlltest.html";
			}
		%>
		<form method="POST" name="searchForm">			
		
			<c:choose>
				<c:when test="${!empty model.testList}" >
					<aa:zone name="testTable">

						<display:table name="model.testList" class="table"
							requestURI="<%=url%>" id="test" export="false"
							excludedParams="*" pagesize="20" sort="list">


							
							<display:column sortProperty="testObjective" sortable="true"
								media="html" title="test">
								<%
									if (testTaker.getIsAdmin().equalsIgnoreCase("y")) {
								%>
								<a
									href='editResources.html?Add=search&id=<c:out value="${test.testId}"/>'>
									<c:out value="${test.testName}" /> </a>
								<%
									} else {
								%>
								<c:out value="${test.testName}" />
								<%
									}
								%>

							</display:column>
							<display:column  title="Delete" >
							<a href="addTest.html?action=Delete&id=<c:out value="${test.testId}"/>">
							<img id="startTest" src="images/delete.png"	>
							</a>


						</display:column>
						
						<display:column  title="Update" >
							<a href="addTest.html?action=Update&id=<c:out value="${test.testId}"/>">
							<img id="startTest" src="images/edit.jpg"	>
							</a>


						</display:column>

							

						</display:table>
					</aa:zone>
					
					<%
											if (request.getParameter(Constants.STATUS_MSG) != null) {
										%>
					<table class="statusTable">
						<tr>
							<td>
								<p align="left">
									test <%=request.getParameter(Constants.STATUS_MSG)%>
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
						href="advancedSearch.html">Click me</a>
				</c:otherwise>
			</c:choose>
			<script type="text/javascript">

    ajaxAnywhere.getZonesToReload = function() { return "testTable" }
    ajaxAnywhere.onAfterResponseProcessing = function() {
        replaceLinks();
       initialize(); // re-initialize lightbox
    }
    function replaceLinks() {
        // replace all the links in <thead> with onclick's that call AjaxAnywhere
        var sortLinks = $('test').getElementsByTagName('thead')[0]
                                     .getElementsByTagName('a');
        ajaxifyLinks(sortLinks);
        if (document.getElementsByClassName('pagelinks').length > 0) {
            var pagelinks = document.getElementsByClassName('pagelinks')[0]
                                    .getElementsByTagName('a');
            ajaxifyLinks(pagelinks);
        }
        //highlightTableRows("testObjective");
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
