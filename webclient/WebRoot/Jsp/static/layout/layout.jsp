<%@ taglib prefix="tiles"
	uri="http://jakarta.apache.org/struts/tags-tiles"%>

<html>
<head>
<title>Techno Vista inc</title>
<link type="text/css" rel="stylesheet" href="CSS/links.css">
</head>

<body>
<%//System.out.println("In layout"); %>
<table border="0" cellpadding="0" cellspacing="0"
	style="border-collapse: collapse" bordercolor="#111111" width="981"
	id="AutoNumber1" height="998" class="links">
<tr>
		<td  height="85" colspan="2">
		<div id="wb_RollOver2"
			style="position:absolute;left:5px;top:11px;z-index:1"
			align="left"><script language="JavaScript"
			type="text/javascript">

			RollOver2 = new Image();
			RollOver2.src = "images/logo rollover.jpg";

            </script>
		  <img src="images/logo.jpg" id="RollOver2" 
			 alt="" border="0"
			onMouseOver="this.src='images/logo rollover.jpg'"
			onMouseOut="this.src='images/logo.jpg'"></div>
		<font style="font-size:13px" color="#000000"
			face="Arial">&nbsp;</font></td>
			<% //System.out.println("before header");%>
</tr>
	<tr>


		<td  height="48" colspan="2"
			
			style="witdth:153%;">
		<div align="left"><tiles:insert attribute="header"
			ignore="true" /></div>	  </td>
	</tr>
	<%//System.out.println("After header"); %>
	<tr>
		<td width="5%" height="447" rowspan="2">
<div align="left" style=" width:180px; height:850px; overflow:auto;">
		<tiles:insert attribute="verticalMenuBar" ignore="true" /></div>	
		<%
//System.out.print("\n\n\t\t after verticalMenuBar in layout =");
%>	
		
		
			</td>
		<td  height="447">
		<div align="left"
			style=" width:800px; height:800px; overflow:auto;"><tiles:insert
			attribute="contentPanel" ignore="true" /></div>	
			<%
//System.out.print("\n\n\t\t after contentPanel in layout =");
%>	
				</td>
	</tr>
	
	<tr>
		<td  height="49" >
		<div align="left"><tiles:insert attribute="footer"
			ignore="true" /></div>		</td>
	</tr>
</table>
</body>

</html>


