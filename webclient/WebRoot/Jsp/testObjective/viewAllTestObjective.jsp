

<%@page import="com.array.testprep.model.TestObjective"%>

<%@page import = "com.array.testprep.model.TestTaker" %>
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
			System.out.print("\n\n\t\t emailId =" + testTaker.getTestTakerId());
			System.out.print("\n\n\t\t isAdmin =" + testTaker.getIsAdmin());
		%>
		<%
			String url;
			String id;
			if (request.getParameter("ID") != null) {
				url = "viewAlltestObjective.html?action_search=search&ID="
						+ request.getParameter("ID");
			} else if (request.getParameter("ISAPPROVED") != null) {
				url = "viewAlltestObjective.html?action_search=search&ISAPPROVED="
						+ request.getParameter("ISAPPROVED");
			} else {
				url = "viewAlltestObjective.html";
			}
		%>
		<form method="POST" name="testObjectiveForm">
		
		<%System.out.print("before loop"); %>
		
			<c:choose>
				<c:when test="${!empty model.testObjectiveList}" >
					<aa:zone name="testObjectiveTable">
<%System.out.print("In loop"); %>
						<display:table name="model.testObjectiveList" class="table"
							requestURI="<%=url%>" id="testObjective" export="false"
							excludedParams="*" pagesize="20" sort="list">


							
							<display:column sortProperty="testObjectiveType" sortable="true"
								media="html" title="TestObjective">
								<%
									if (testTaker.getIsAdmin().equalsIgnoreCase("y")) {
								%>
								<a
									href='editResources.html?Add=search&id=<c:out value="${testObjective.testObjectiveId}"/>'>
									<c:out value="${testObjective.testObjectiveType}" /> </a>
								<%
									} else {
								%>
								<c:out value="${testObjective.testObjectiveType}" />
								<%
									}
								%>

							</display:column>

							<display:column sortable="true" title="Test">
								<c:out value='${testObjective.test.testName}' />
							</display:column>

						<display:column  title="Delete" >
							<a href="addTestObjective.html?action=Delete&id=<c:out value="${testObjective.testObjectiveId}"/>">
							<img id="startTest" src="images/delete.png"	>
							</a>


						</display:column>
						
						<display:column  title="Update" >
							<a href="addTestObjective.html?action=Update&id=<c:out value="${testObjective.testObjectiveId}"/>">
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
						href="advancedSearch.html">Click me</a>
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
        var sortLinks = $('testObjective').getElementsByTagName('thead')[0]
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
