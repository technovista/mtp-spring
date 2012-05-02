<%@ include file="/Jsp/taglib.jsp"%>

<html>

	<head>
		<title>Login</title>
		<link rel="stylesheet" type="text/css" href="CSS/styles.css" />
		
	</head>

	<body onload="login.emailId.focus();">
		<br>
		<br>
		<br>

		<form name="login" method="post">
			<table width="438" border="0" align="center">
			<%try{ %>
				<tr>
					<td width="45" height="51" bordercolor="#FFFFFF">

						<div align="left">
							<font><b><strong><Spring:message
											code="e-mailId" />
								</strong>
							</b>
							</font>
						</div>
					</td>
					<Spring:bind path="testTaker.emailId">
						<td width="162" bordercolor="#FFFFFF">
							<input type="text" name="emailId" value="" size="20" />
						</td>
						<td width="205" bordercolor="#FFFFFF">
							<font color="red"> <b> <c:out
										value="${status.errorMessage}" />
							</b> </font>
						</td>
					</Spring:bind>
				</tr>
				<tr>
					<td colspan="2" align="center" width="214"></td>
				</tr>

				<tr>
					<td width="45" height="49" bordercolor="#FFFFFF">
						<div align="left">
							<font><b><strong><Spring:message
											code="password"></Spring:message>
								</strong>
							</b>
							</font>
						</div>
					</td>
					<Spring:bind path="testTaker.password">
						<td width="162" bordercolor="#FFFFFF">
							<div align="center">
								<input type="password" name="password" size="20"
									style="float: left" value="" />
							</div>
						</td>
						<td width="205" bordercolor="#FFFFFF">
							<font color="red"> <b> <c:out
										value="${status.errorMessage}" />
							</b> </font>
						</td>
					</Spring:bind>

				</tr>
				<tr>

					<td colspan="2" bordercolor="#FFFFFF">
						<input type="submit" class="submitButton" name="action"
							value="<Spring:message code="login"></Spring:message>" />
						&nbsp;&nbsp;
						<input type="reset" value="Reset" name="Reset"
							class="submitButton" />
					</td>
				</tr>
				<tr>
					<td height="21" colspan="2" width="214" bordercolor="#FFFFFF">
						<p align="right" class="links">
							<a href="registration.html"><font><Spring:message
										code="signup" />
							</font>
							</a>
						</p>
						<div align="right"></div>
					</td>
				</tr>
				<tr>
					<td height="21" colspan="2" width="214" bordercolor="#FFFFFF">
						<div align="right" class="links">
							<a href="forgotPassword.html"><font> <Spring:message
										code="forgotpassword" />
							</font>
							</a>
						</div>
					</td>
				</tr>
				<%}catch(Exception e ){ e.printStackTrace();
				System.out.print("exception : "+e.toString());} %>
			</table>

		</form>
	</body>
</html>
