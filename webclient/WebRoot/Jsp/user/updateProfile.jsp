<%@ include file="/Jsp/taglib.jsp"%>
<style type="text/css">
<!--
.style12 {
	font-size: 16px;
	font-weight: bold;
}

.style6 {
	color: #FF0000
}
-->
</style>
<html>
	<head>
		<Script>
  function validationCheck(form)
   {
   		 firstName=form1.firstName.value;
        if(firstName.length==0)
        {
            alert("Please Enter a valid first name");
            form1.firstName.focus();
            return false;
        }
        
        	v2='qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM';
                tempNumber=0;
                while(tempNumber<firstName.length)
                {
                    if(v2.indexOf(firstName.charAt(tempNumber))==-1)
                    {
                        alert("Please Enter a valid First Name");
                        form1.firstName.value='';
                        form1.firstName.focus();
                        return false;
                    }
                    tempNumber++;
                }
        
         lastName=form1.lastName.value;
        if(lastName.length==0)
        {
            alert("Please Enter a valid Last Name");
            form1.lastName.focus();
            return false;
        }
        
        
        	 with (form1.email)
		{
			apos=value.indexOf("@");
			dotpos=value.lastIndexOf(".");
			if (apos<1||dotpos-apos<2) 
			 {
			  alert("Please Enter a valid E-mail");
			  return false;
			 }
	  	}
		  contactNo = form1.contactNo.value;
			v3='1234567890';
                tempNumber1=0;
                while(tempNumber1<contactNo.length)
                {
                    if(v3.indexOf(contactNo.charAt(tempNumber1))==-1)
                    {
                        alert("Please enter a valid Contact Number");
                        form1.contactNo.value='';
                        form1.contactNo.focus();
                        return false;
                    }
                    tempNumber1++;
                }  
		
		   city = form1.city.value;
			v4='qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM';
                tempNumber2=0;
                while(tempNumber2<city.length)
                {
                    if(v4.indexOf(city.charAt(tempNumber2))==-1)
                    {
                        alert("Please Enter a valid city");
                        form1.city.value='';
                        form1.city.focus();
                        return false;
                    }
                    tempNumber2++;
                }  
		
		
		 zipcode = form1.zipcode.value;
			v8='1234567890';
                tempNumber8=0;
                while(tempNumber8<zipcode.length)
                {
                    if(v8.indexOf(zipcode.charAt(tempNumber8))==-1)
                    {
                        alert("Please enter a valid zip code");
                        form1.zipcode.value='';
                        form1.zipcode.focus();
                        return false;
                    }
                    tempNumber8++;
                }  
		
        
        
     return true ;  
   }
   </Script>
		<link rel="stylesheet" type="text/css" href="CSS/styles.css" />

	</head>
	<body>
		<table class="contentPageHeader">
			<tr>
				<td>
					&nbsp;Help
				</td>
			</tr>
		</table>

		<div align="center">
			<p>
				&nbsp;
			</p>
			<form id="form1" name="form1" method="post" action=""
				onsubmit="return validationCheck(this)">
				<div class="tableHeader" id="tableHeader">
					<p align="center">
						Update Profile
					</p>
				</div>
				<table width="44%" height="700" cellpadding="3" cellspacing="0"
					bordercolor="#111111" class="table"
					style="border-collapse: collapse">
					<tr class="even">
						<Spring:bind path="testTaler.emailId">
							<td>
								<div align="right">
									<span class="style6">*</span>EmailId:
								</div>
							</td>
							<td>
								<div align="left">
									<input type="text" name="emailId" id="emailId"
										value="<c:out value='${status.value}'/>" disabled="disabled"
										size="20" />
								</div>
							</td>
						</Spring:bind>
					</tr>

					<tr>
						<td colspan="2" align="center">
							<span class="style12">Tell us about yourself...</span>
						</td>
					</tr>
					<tr class="even">
						<Spring:bind path="testTaker.firstName">
							<td height="47">
								<div align="right">
									<span class="style6">*</span>First Name:
								</div>
							</td>
							<td>
								<div align="left">
									<input type="text" name="firstName" id="firstName"
										value="<c:out value='${status.value}'/>" size="20" />
								</div>
							</td>
						</Spring:bind>
					</tr>
					<tr class="odd">
						<Spring:bind path="testTaker.lastName">
							<td>
								<div align="right">
									<span class="style6">*</span>Last Name:
								</div>
							</td>
							<td>
								<div align="left">
									<input type="text" name="lastName" id="lastName"
										value="<c:out value='${status.value}'/>" size="20" />
								</div>
							</td>
						</Spring:bind>
					</tr>



					<tr class="last">
						<td height="107" colspan="2" align="left">
							<div align="right">
								<p align="center"></p>
								<p align="center">
									<input type="submit" value="Update" name="action"
										class="submitButton" />
									&nbsp;&nbsp;&nbsp;
									<input type="reset" value="Reset" class="submitButton"
										name="reset" />
								</p>
								<p>
									&nbsp;
								</p>
							</div>
						</td>
					</tr>
				</table>
				<p>
					&nbsp;
				</p>
			</form>
		</div>
	</body>
</html>
