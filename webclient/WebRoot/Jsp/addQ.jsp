%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.array.testprep.model.AnswerBank"%><html>
<head >
- Show quoted text -


<script language="javaScript">
function CreateTable(colheader,noofrow,parentTag)
{
alert("QUESTION IS REQURIED FIELD");
var newEl = document.createElement('TABLE');
var c=0;
noofrow = f1.noOfChoice.value;
newEl.setAttribute('cellPadding',4);
newEl.setAttribute('ID','tbl');
var tmp = document.createElement('TBODY');
colhd=colheader.split(";");
var noofcol=colhd.length;
newEl.appendChild(tmp);
var rowh=document.createElement('TR');
var srno=document.createElement('TH');
var srcol=document.createTextNode("SrNo");
srno.appendChild(srcol);
rowh.appendChild(srno);
for(k=0;k<noofcol;k++)
{
var thd=document.createElement('TH');
var theData = document.createTextNode(colhd[k]);
thd.appendChild(theData);
rowh.appendChild(thd);
}
tmp.appendChild(rowh);
for(i=0;i<noofrow;i++)
{
var row = document.createElement('TR');
var rowno=document.createElement('TH');
var colno=document.createTextNode(i+1);
rowno.appendChild(colno);
row.appendChild(rowno);
for(j=0;j<noofcol;j++)
{
var container = document.createElement('TD');
//var theTextBox = document.createElement('INPUT');
//theTextBox.setAttribute('TYPE','textarea');
//theTextBox.setAttribute('ID',c);


//theTextBox.cols = 60;
//theTextBox.rows = 6;

var theTextBox = document.createElement('textarea');
theTextBox.setAttribute('ID',c);


theTextBox.cols = 30;
theTextBox.rows = 6;
theTextBox.name=c;

container.appendChild(theTextBox);
row.appendChild(container);
//txtid.add(theTextBox);
c=c+1;

}
tmp.appendChild(row);
}
newEl.appendChild(tmp);
document.getElementById(parentTag).appendChild(newEl);

}

function CreateTable(colheader,noofrow,parentTag)
{

var newEl = document.createElement('TABLE');
var c=0;
noofrow = f1.noOfChoice.value;
newEl.setAttribute('cellPadding',4);
newEl.setAttribute('ID','tbl');
var tmp = document.createElement('TBODY');
colhd=colheader.split(";");
var noofcol=colhd.length;
newEl.appendChild(tmp);
var rowh=document.createElement('TR');
var srno=document.createElement('TH');
var srcol=document.createTextNode("SrNo");
srno.appendChild(srcol);
rowh.appendChild(srno);
for(k=0;k<noofcol;k++)
{
var thd=document.createElement('TH');
var theData = document.createTextNode(colhd[k]);
thd.appendChild(theData);
rowh.appendChild(thd);
}
tmp.appendChild(rowh);
for(i=0;i<noofrow;i++)
{
var row = document.createElement('TR');
var rowno=document.createElement('TH');
var colno=document.createTextNode(i+1);
rowno.appendChild(colno);
row.appendChild(rowno);
for(j=0;j<noofcol;j++)
{
var container = document.createElement('TD');
//var theTextBox = document.createElement('INPUT');
//theTextBox.setAttribute('TYPE','textarea');
//theTextBox.setAttribute('ID',c);


//theTextBox.cols = 60;
//theTextBox.rows = 6;

var theTextBox = document.createElement('textarea');
theTextBox.setAttribute('ID',c);


theTextBox.cols = 30;
theTextBox.rows = 6;
theTextBox.name=c;

container.appendChild(theTextBox);
row.appendChild(container);
//txtid.add(theTextBox);
c=c+1;

}
tmp.appendChild(row);
}
newEl.appendChild(tmp);
document.getElementById(parentTag).appendChild(newEl);

}
</script>
<body>
<form name="f1" method="post">
<table>

</table>

<input type = "text" name="noOfChoice">

<input type="button" id="Ck" value="Create Text Box " onClick="CreateTable('CHOICE',3,'f1');">


<input type="submit" name="action" value="submit">
</form>

//To Test this u can call this function like

</body>


</html>