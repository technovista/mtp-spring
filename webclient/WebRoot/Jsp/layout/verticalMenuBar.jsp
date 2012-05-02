
<%@include file="/Jsp/taglib.jsp"%>
<%@page import="com.array.testprep.model.TestTaker,com.array.testprep.util.*"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

	<head>

		<script type="text/javascript" src="js/jquery-1.2.6.pack.js"></script>

		<script type="text/javascript" src="js/ddaccordion.js"></script>

		<script type="text/javascript">
	ddaccordion.init( {
		headerclass :"silverheader", //Shared CSS class name of headers group
		contentclass :"submenu", //Shared CSS class name of contents group
		revealtype :"mouseover", //Reveal content when user clicks or onmouseover the header? Valid value: "click" or "mouseover
		mouseoverdelay :200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
		collapseprev :true, //Collapse previous content (so only one open at any time)? true/false
		defaultexpanded : [ 0 ], //index of content(s) open by default [index1, index2, etc] [] denotes no content
		onemustopen :true, //Specify whether at least one header should be open always (so never all headers closed)
		animatedefault :false, //Should contents open by default be animated into view?
		persiststate :true, //persist state of opened contents within browser session?
		toggleclass : [ "", "selected" ], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
		togglehtml : [ "", "", "" ], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
		animatespeed :"fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
		oninit : function(headers, expandedindices) { //custom code to run when headers have initalized
			//do nothing
		},
		onopenclose : function(header, index, state, isuseractivated) { //custom code to run whenever a header is opened or closed
			//do nothing
		}
	})
</script>


		<link href="CSS/SpryAccordion.css" rel="stylesheet" type="text/css" />

	</head>

	<body>
		<%
			//System.out.print("\n\n\t\t before object =");

			TestTaker testTaker = (TestTaker) session.getAttribute("testTaker");
			//System.out.print("\n\n\t\t testtakerId ="
			//		+ testTaker.getTestTakerId());
			//System.out.print("\n\n\t\t testtakerId =" + testTaker.getIsAdmin());
		%>

		<div class="applemenu">
			
			<div class="silverheader">
				Test
			</div>
			<div class="submenu">
				<a href="viewAllTest.html">All Test</a>
				<br />
			</div>
			<div class="silverheader">
				User
			</div>
			<div class="submenu">
				<a href="users.html" class="style1">Delete</a>
				<br />
			</div>

			
			<div class="silverheader">
				Profile
			</div>

			<div class="submenu">
				<a
					href='updateProfile.html?action=update&id=<%=testTaker.getTestTakerId()%>'>
					Edit</a>
				<br />


				<a
					href='viewProfile.html?action=view&id=<%=testTaker.getTestTakerId()%>'>
					View</a>
				<br />
			</div>

			<div class="silverheader">
				Test Type(SCJP,SAP etc)
			</div>

			<div class="submenu">
				<a href='addTestType.html?action=Add'> Add</a>
				<br />

				<a href='viewAllTestType.html'> Edit</a>
				<br />
			</div>

			
			<div class="silverheader">
				SubType(Jsp, Thread, etc)
			</div>

			<div class="submenu">
				<a href='addQuestionType.html?action=Add'> Add</a>
				<br />

				<a href='viewAllQuestionType.html'> Edit</a>
				<br />
			</div>

			<div class="silverheader">
				Question
			</div>

			<div class="submenu">
				<a href='addQuestion.html?action=Add'> Add</a>
				<br />

				<a href='viewAllQuestions.html'> Edit</a>
				<br />
			</div>

			<div class="silverheader">
				Answer
			</div>

			<div class="submenu">
				<a href='addAnswer.html?action=Add'> Add</a>
				<br />

				<a href='viewAllAnswers.html'> Edit</a>
				<br />
			</div>

			<div class="silverheader">
				QuestionAnswer
			</div>

			<div class="submenu">
				<a href='addQuestionAnswer.html?action=Add'> Add</a>
				<br />

				<a href='viewAllQuestionAnswers.html'> Edit</a>
				<br />
			</div>


			<div class="silverheader">
				Link
			</div>

			<div class="submenu">
				<a
					href='updateProfile.html?action=update&id=<%=testTaker.getTestTakerId()%>'>
					ADD </a>
				<br />

				<a
					href='viewProfile.html?action=view&id=<%=testTaker.getTestTakerId()%>'>
					Edit</a>
				<br />
			</div>
		</div>


	</body>
</html>