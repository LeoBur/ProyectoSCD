<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="userList.title"/></title>

</head>

<div class="container">
<meta name="menu" content="UserMenu"/>
    <div class="col-sm-10">
        <h2><fmt:message key="userList.ultimaMedicion"/></h2>

         <display:table name="medicionesList" cellspacing="0" cellpadding="0" requestURI=""
                       defaultsort="0" id="mediciones" pagesize="12" class="table table-condensed table-striped table-hover" export="true">
            <display:column property="f_medicion" escapeXml="true" sortable="true" titleKey="active.fecha_Medicion"
                            style="width: 40%"/>
            <display:column property="valor" escapeXml="true" sortable="true" titleKey="active.valorMedicion"
                            style="width: 40%"/>
            <display:column property="unidad" escapeXml="true" sortable="true" titleKey="active.unidad"
                            style="width: 20%"/>
         </display:table>
    </div>
</div>