<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="userList.title"/></title>

</head>

<div class="container-fluid">
<meta name="menu" content="UserMenu"/>
    <div class="col-md-10">
        <h2><fmt:message key="userList.ultimaMedicion"/></h2>

         <display:table name="medicionesList" defaultsort="0" id="mediciones" pagesize="12"
            class="table table-condensed table-striped table-hover" export="false">

            <display:setProperty name="decorator.media.pdf" value="com.bcpv.webapp.displaytag.decorators.ItextTotalWrapper"/>
            <display:caption><h3>Mis mediciones</h3></display:caption>
            <display:caption media="pdf">Mis mediciones</display:caption>
            <display:column property="f_medicion" escapeXml="true" sortable="false" titleKey="active.fecha_Medicion"
                            style="width: 40%"/>
            <display:column property="valor" escapeXml="true" sortable="false" titleKey="active.valorMedicion"
                            style="width: 40%"/>
            <display:column property="unidad" escapeXml="true" sortable="false" titleKey="active.unidad"
                            style="width: 20%"/>
            <display:setProperty name="export.pdf" value="true" />
         </display:table>
    </div>
</div>