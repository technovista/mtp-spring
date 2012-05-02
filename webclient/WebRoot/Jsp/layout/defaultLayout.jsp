<%@ taglib prefix="tiles"
    uri="/WEB-INF/tags/struts-tiles.tld"%>

<html>
    <head>
        <title>Techno Vista inc</title>
    </head>

    <body>

        <table border="0" cellpadding="0" cellspacing="0"
            style="border-collapse: collapse" bordercolor="#111111" width="100%"
            id="AutoNumber1" height="640">
            <tr>
                <td width="103%" height="85" colspan="2">
                    <div id="wb_RollOver2"
                        style="position: absolute; left: 5px; top: 11px; z-index: 1"
                        align="left">
                        <script language="JavaScript" type="text/javascript">

            RollOver2 = new Image();
            RollOver2.src = "images/logo rollover.jpg";

            </script>
                        <img src="images/logo.jpg" id="RollOver2" alt="" border="0"
                            onMouseOver="this.src='images/logo rollover.jpg'"
                            onMouseOut="this.src='images/logo.jpg'">
                    </div>
                </td>
            </tr>
            <tr>
                <td width="153%" height="48" colspan="2" style="witdth: 153%; background-color:#4F4F4F">
                    <div align="center">
                    <tiles:insert attribute="defaultHeader" ></tiles:insert>
                        
                    </div>
                </td>
            </tr>
            <tr>
                <td width="18%" height="496" rowspan="2">
                    <div align="left"></div>
                </td>
                <td width="82%" height="447">
                    <div align="center">
                        <tiles:insert attribute="contentPanel"  />
                    </div>
                </td>
            </tr>
            <tr>
                <td width="82%" height="49"></td>
            </tr>
        </table>

    </body>

</html>

