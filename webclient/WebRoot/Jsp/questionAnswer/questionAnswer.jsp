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
			TestTaker testTaker = (TestTaker) session.getAttribute("testTaker");
			System.out.print("\n\n\t\t emailId =" + testTaker.getTestTakerId());
			System.out.print("\n\n\t\t isAdmin =" + testTaker.getIsAdmin());
		%>
		<%
			String url;
			String id;
			if (request.getParameter("ID") != null) {
				url = "questionAnswer.html?action_search=search&ID="
						+ request.getParameter("ID");
			} else if (request.getParameter("ISAPPROVED") != null) {
				url = "questionAnswer.html?action_search=search&ISAPPROVED="
						+ request.getParameter("ISAPPROVED");
			} else {
				url = "questionAnswer.html";
			}
		%>
		<form method="POST" name="searchForm">



			<c:choose>
				<c:when test="${!empty myTestList}">


					<aa:zone name="myTable">
							
							
						<display:table name="myTestList"  
							requestURI="<%=url%>" id="myTestList" export="false"
							excludedParams="*" pagesize="1" sort="list">
							<display:setProperty name="paging.banner.item_name">
							Question
							</display:setProperty>
							<display:setProperty name="paging.banner.items_name">
							Questions
							</display:setProperty>
							<display:setProperty name="paging.banner.some_items_found">
								<span class="pagebanner"> {0} {1} found, displaying {2}
									to {3}. </span>
							</display:setProperty>

							<display:setProperty name="paging.banner.full">
								<span class="pagelinks"> [<a href="{2}">Prev</a> ] [<a
									href="{3}">Next</a> ] </span>
							</display:setProperty>

							<display:setProperty name="paging.banner.first">
								<span class="pagelinks"> [<a href="{3}">Next</a>] </span>
							</display:setProperty>
							<display:setProperty name="paging.banner.last">
								<span class="pagelinks"> [<a href="{2}">Prev</a>] </span>
							</display:setProperty>
						
							<display:column title="Question">
							
								<input type="hidden" name="index"  value="<c:out value="${myTestList.customeTestQuestionId}"/>" /> 
								
									<pre  width="50"><c:out value="${myTestList.question}"></c:out>				</pre>
								
								<Spring:bind path="customeTest.selectedAnswer">
									<c:forEach var="i" begin="0" end="${myTestList.numOfChoice}">
										<br>
										<c:out value="${i+1}" />.&nbsp;
           						 
           						  <input type="radio" name="answer" id="choice"
											value="<c:out value="${myTestList.choice[i].answerId}"/>" />



										<c:out value="${myTestList.choice[i].choice}">
										</c:out>

									</c:forEach>
								</Spring:bind>
								
							</display:column>
							
						</display:table>

					</aa:zone>
<p>
</p>

				<div align="right">	<input   type="submit" name="action" value="END" /></div>
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
        var sortLinks = $('myTestList').getElementsByTagName('thead')[0]
                                     .getElementsByTagName('a');
        ajaxifyLinks(sortLinks);
        if (document.getElementsByClassName('pagelinks').length > 0) {
            var pagelinks = document.getElementsByClassName('pagelinks')[0]
                                    .getElementsByTagName('a');
            ajaxifyLinks(pagelinks);
        }
        highlightTableRows("myTestList");
        var editable = document.getElementsByClassName('editable');
        for (i=0; i < editable.length; i++) {
            var userId = editable[i].parentNode.parentNode.getElementsByTagName("a")[0].innerHTML;
            new Ajax.InPlaceEditor(editable[i].id, 'user-ajax.html?id=' + userId);
        }
    }
    function ajaxifyLinks(links) {
        for (i=0; i < links.length; i++) {
            links[i].onclick = function() {
           for(var j=0;j<document.searchForm.elements.answer.length;j++)
           	{
           		if(document.searchForm.answer[j].checked)
           			{
           				  this.href+='&ans='+document.searchForm.answer[j].value;
           			}
           	}
        
           
           this.href+='&id='+document.searchForm.index.value;
           
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
