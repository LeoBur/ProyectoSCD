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
            class="table table-condensed table-striped table-hover" export="false" style="width: 100%">

            <display:setProperty name="decorator.media.pdf" value="com.bcpv.webapp.displaytag.decorators.ItextTotalWrapper"/>
            <display:caption><h3>Mediciones de ${namePaciente}</h3></display:caption>
            <display:caption media="pdf">Mediciones de ${namePaciente}</display:caption>
            <display:column property="f_medicion" escapeXml="true" sortable="false" titleKey="active.fecha_Medicion"/>
            <display:column property="valor" escapeXml="true" sortable="false" titleKey="active.valorMedicion"/>
            <display:column property="unidad" escapeXml="true" sortable="false" titleKey="active.unidad"/>
            <display:setProperty name="export.pdf" value="true" />
         </display:table>
    </div>
</div>