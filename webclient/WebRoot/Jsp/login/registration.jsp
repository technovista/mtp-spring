<%@ include file="/Jsp/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>Registration Form</title>
<style type="text/css">
            <!--
.style2 {font-size: 18px; font-weight: bold; }
.style6 {color: #FF0000}
.style12 {font-size: 16px; font-weight: bold; }
.style14 {font-size: 16px}
            -->
        </style>
<script language="javascript" type="text/javascript"><!--   
            var minpwlength = 4;
    var fairpwlength = 7;
    
    var STRENGTH_SHORT = 0;  // less than minpwlength 
    var STRENGTH_WEAK = 1;  // less than fairpwlength
    var STRENGTH_FAIR = 2;  // fairpwlength or over, no numbers
    var STRENGTH_STRONG = 3; // fairpwlength or over with at least one number
    
    img0 = new Image(); 
    img1 = new Image();
    img2 = new Image();
    img3 = new Image();
    
    img0.src = 'images/tooshort.jpg';
    img1.src = 'images/fair.jpg';
    img2.src = 'images/medium.jpg';
    img3.src = 'images/strong.jpg';
    
    var strengthlevel = 0;
    
    var strengthimages = Array( img0.src,
                                img1.src,
                                img2.src,
                                img3.src );
     function updatestrength( pw ) {
    
        if( istoosmall( pw ) ) {
    
            strengthlevel = STRENGTH_SHORT;
    
        }
        else if( !isfair( pw ) ) { 
    
            strengthlevel = STRENGTH_WEAK;
    
        }    
        else if( hasnum( pw ) ) {
    
            strengthlevel = STRENGTH_STRONG;
    
        }
        else {
    
            strengthlevel = STRENGTH_FAIR;
    
        }
    
        document.getElementById( 'strength' ).src = strengthimages[ strengthlevel ];
    
    }
    
    function isfair( pw ) {
    
        if( pw.length < fairpwlength ) {
    
            return false;
    
        }
        else { 
    
            return true;
    
        }
    
    }
    
    function istoosmall( pw ) {
    
        if( pw.length < minpwlength ) {
    
            return true;
    
        }
        else {
    
            return false;

        }
    
    }
    
    function hasnum( pw ) {
    
        var hasnum = false;
    
        for( var counter = 0; counter < pw.length; counter ++ ) {
    
            if( !isNaN( pw.charAt( counter ) ) ) {
    
                hasnum = true;
    
            }
    
        }
    
    
        return hasnum;
    
    }
    
    function ajaxFunction()
	{
		var xmlHttp;
		try
 			 {
 				 // Firefox, Opera 8.0+, Safari
				  xmlHttp=new XMLHttpRequest();
				  }
				catch (e)
				  {
				  // Internet Explorer
				  try
				    {
				    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
				    }
				  catch (e)
				    {
				    try
				      {
				      xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
				      }
				    catch (e)
				      {
				      alert("<Spring:message code="alert11registration"/>");
				      return false;
				      }
    }
  }
  xmlHttp.onreadystatechange=function()
    {
    if(xmlHttp.readyState==4)
      {
      	 result = xmlHttp.responseText;
      	if(result=="failure")
      	{
      alert("<Spring:message code="alert10registration"/>");
      form1.emailId.value="";
      form1.emailId.focus();
      }
      
      }
    }
    var url = "http://97.74.92.128:9080/techno/registration.do?"; 
    url = url + "emailId="+form1.emailId.value;
               
    
  xmlHttp.open("GET",url,true);
  xmlHttp.send(null);
  }
    
    
   function validationCheck(form)
   {
   	name=form1.emailId.value;
        if(name.length==0)
        {
            alert("<Spring:message code="alert1registration"/>");
            form1.emailId.focus();
            return false;
        }
        
        
          name=form1.user.value;
	  	v='qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890';
                tempNumber=0;
                while(tempNumber<name.length)
                {
                    if(v.indexOf(name.charAt(tempNumber))==-1)
                    {
                        alert("<Spring:message code="alert2registration"/>");
                        form1.emailId.value='';
                        form1.emailId.focus();
                        return false;
                    }
                    tempNumber++;
                }
        
        
        
        password=form1.password.value;
        if(password.length<6)
        {
            alert("<Spring:message code="alert3registration"/>");
            form1.password.focus();
            return false;
        }
        reEnterPassword=form1.reEnterPassword.value;
   
        if(reEnterPassword!=password)
        {
            alert("<Spring:message code="alert4registration"/>");
            form1.reEnterPassword.value='';
            form1.reEnterPassword.focus();
            return false;
        }
     	
     	  firstName=form1.firstName.value;
        if(firstName.length==0)
        {
            alert("<Spring:message code="alert5registration"/>");
            form1.firstName.focus();
            return false;
        }
        
        
         
	  	v2='qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM';
                tempNumber=0;
                while(tempNumber<firstName.length)
                {
                    if(v2.indexOf(firstName.charAt(tempNumber))==-1)
                    {
                        alert("<Spring:message code="alert5registration"/>");
                        form1.firstName.value='';
                        form1.firstName.focus();
                        return false;
                    }
                    tempNumber++;
                }
        
        
        
        
        lastName=form1.lastName.value;
        if(lastName.length==0)
        {
            alert("<Spring:message code="alert6registration"/>");
            form1.lastName.focus();
            return false;
        }
     	
     	 with (form1.emailId)
		{
			apos=value.indexOf("@");
			dotpos=value.lastIndexOf(".");
			if (apos<1||dotpos-apos<2) 
			 {
			  alert("<Spring:message code="alert7registration"/>");
			   form1.email.focus();
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
                        alert("<Spring:message code="alert8registration"/>");
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
                        alert("<Spring:message code="alert9registration"/>");
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
		
		  
		  
		  	
        return true;
   } 
    
    
        --></script>



<link rel="stylesheet" type="text/css" href="CSS/styles.css" />
</head>

<body onLoad="form1.emailId.focus();">
<table class="contentPageHeader">
	<tr>
		<td>&nbsp;<Spring:message code="registrationform" />
		</td>
	</tr>
</table>
<p>&nbsp;</p>
<div align="center">
<form id="form1" name="form1" method="post"
	onsubmit="return validationCheck(this)">

<div class="tableHeader" id="tableHeader">
<p><Spring:message code="registrationform" /> 
</p>
</div>
<div align="right"><br>
    <a href="login.html"><strong> Already Registered???</strong></a>
</div>
<br>
<table cellpadding="3" cellspacing="0" class="table"
	style="border-collapse: collapse" width="45%">
	<tr class="even">
		<td colspan="2" align="left">
		<div align="left" class="style12"><Spring:message
			code="label1registration" /></div>		</td>
	</tr>
	<tr class="odd">

		<td width="24%">
		<div align="right"><span class="style6">*</span><Spring:message
			code="emailId" /></div>		</td>
		<Spring:bind path="testTaker.emailId">
		  <td width="76%"><label>
		  <div align="left">
		    <input type="text" name="emailId"
				id="emailId" size="20" onBlur="ajaxFunction();"
				value="<c:out value='${status.value}'/>" /> 
		    </div>
		  </label> <div align="left"><font color="red">
			  <c:out value="${status.errorMessage}" /> 
		    </font></div></td>
		</Spring:bind>
	</tr>
	<tr class="even">
		<td>
		<div align="right"><span class="style6">*</span> <Spring:message
			code="password" /></div>		</td>
		<td>
		<div>
		  <div align="left">
		    <input maxlength="15"
			onKeyUp="updatestrength( this.value );" type="password"
			name="password" id="password" value="" size="20" />
		    <img
			src="images/tooshort.jpg" alt="" width="115" height="30" id="strength" /></div>
		</div>		</td>
	</tr>
	<tr class="odd">
		<td>
		<div align="right"><span class="style6">*</span> <Spring:message
			code="reenterpassword" /></div>		</td>
		<td><div align="left"><input type="password" name="reEnterPassword"
			id="reEnterPassword" size="20" />
	      </div>		</td>
	</tr>
	<tr class="even">
		<td colspan="2" align="left">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2" align="left"><span class="style12"><Spring:message
			code="label2registration" /></span></td>
	</tr>
	<tr class="odd">
		<td>
		<div align="right"><span class="style6">*</span> <Spring:message
			code="firstname" /></div>		</td>
		<td><Spring:bind path="testTaker.firstName">
		
			<div align="left">
			  <input type="text" name="firstName" id="firstName"
				size="20" value="<c:out value='${status.value}'/>" />
		    </div>
			
		  
		</Spring:bind></td>
	</tr>
	<tr class="even">
		<td>
		<div align="right"> <span class="style6">*</span> <Spring:message
			code="lastname" /> </div>		</td>
		<td><Spring:bind path="testTaker.lastName">
	
			<div align="left">
			  <input type="text" name="lastName" id="lastName"
				size="20" value="<c:out value='${status.value}'/>" />
		    </div>
		
		   
		</Spring:bind></td>
	</tr>
	
	
	
	
	<tr class="odd">
		<td colspan="2" align="left">&nbsp;</td>
	</tr>
	<tr class="even">
		<td colspan="2" align="left"><span class="style12"><Spring:message
			code="label12registration" /></span></td>
	</tr>
	
	
	<tr class="last">
		<td colspan="2" align="left">
		<div align="right">
		<p align="center">&nbsp;</p>
		<p align="center"><input type="submit" value="Add" name="action"
			class="submitButton" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
			type="reset" value="Reset" class="submitButton" name="reset" /></p>
		<p>&nbsp;</p>
		</div>		</td>
	</tr>
</table>
<p>&nbsp;</p>
</form>
</div>
</body>
</html>

