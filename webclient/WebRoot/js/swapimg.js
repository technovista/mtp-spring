

function SwapImage()
{
   var doc=document, args=arguments;
   doc.$imgSwaps = new Array();
   for(var i=2; i<args.length; i+=2)
   {
      var elem=FindObject(args[i]);
      if(elem)
      {
         doc.$imgSwaps[doc.$imgSwaps.length]=elem;
         elem.$src=elem.src;
         elem.src=args[i+1];
      }
   }
}

                                 

function FindObject(id, doc)
{
   var child, elem;
   if(!doc)
      doc=document;
   if(doc.getElementById)
      elem=doc.getElementById(id);
   else
   if(doc.layers)
      child=doc.layers;
   else
   if(doc.all)
      elem=doc.all[id];
   if(elem)
      return elem;
   if(doc.id==id || doc.name==id)
      return doc;
   if(doc.childNodes)
      child=doc.childNodes;
   if(child)
   {
      for(var i=0; i<child.length; i++)
      {
         elem=FindObject(id,child[i]);
         if(elem)
            return elem;
      }
   }
   var frm=doc.forms;
   if(frm)
   {
      for(var i=0; i<frm.length; i++)
      {
         var elems=frm[i].elements;
         for(var j=0; j<elems.length; j++)
         {
            elem=FindObject(id,elems[i]);
            if(elem) return elem;
         }
      }
   }
   return null;
}

                                   

function OnGoMenuFormLink(GoList)
{
   var url = GoList.options[GoList.selectedIndex].value;
   var target = GoList.options[GoList.selectedIndex].className;
   GoList.selectedIndex=0;
   GoList.blur();
   if (url)
   {
      NewWin=window.open(url,target);
      window['NewWin'].focus()
   }
}

       