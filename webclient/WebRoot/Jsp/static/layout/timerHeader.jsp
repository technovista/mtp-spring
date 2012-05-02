<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Header</title>
<script type="text/javascript" src="js/timer.js"></script>
</head>
<body bgcolor="#FFFFFF" text="#000000">
<form name="counter"><input type="text" size="8" 
name="d2">
<div id="wb_NavigationBar1"
	style="position:absolute; left:440px; top:113px; width:655px; height:23px; z-index:2"
	align="left">
	
<table border="0" cellpadding="0" cellspacing="0" id="NavigationBar1">
	<tr>

	</tr>
</table>
</div></form>
<script type="text/javascript">


 


<!-- 
// 
 var milisec=9
 var seconds=30 
 var minutes=1
 var hours =1
 document.counter.d2.value='1 : 2 : 30' 

function display(){ 
 if (milisec<=0){ 
    milisec=9 
    seconds-=1 
 }
  if(seconds<=0)
  {
   seconds=59
   minutes-=1
  }
  if(minutes<=0)
  {
   minutes=59
   hours-=1
  }
 if (seconds<=-1){ 
     
    seconds=59 
 } 
 if(minutes<=-1)
 {
  
    seconds=0
    minutes=59
 }
 if(hours<=-1)
 {
  seconds=0
  minutes=0
  hours+=1
 }
 else 
    seconds-=1 
    document.counter.d2.value=hours+ " : "+minutes+" : "+seconds 
    setTimeout("display()",1000) 
} 
display() 
--> 


</script>


</body>
</html>
