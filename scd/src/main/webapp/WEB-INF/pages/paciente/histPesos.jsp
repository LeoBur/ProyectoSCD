<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>



<div class="col-sm-10">
    <h2><fmt:message key="userList.ultimoPeso"/></h2>

    <display:table name="pesosList" defaultsort="0" id="pesos" pagesize="12"
        class="table table-condensed table-striped table-hover" export="false">
        <display:caption><h3>Mis pesos</h3></display:caption>
        <display:caption media="pdf">Mis pesos</display:caption>
        <display:column property="fechaHora" escapeXml="true" sortable="true" titleKey="active.peso.fecha"
                        style="width: 50%"/>
        <display:column property="peso" escapeXml="true" sortable="true" titleKey="active.peso"
                        style="width: 50%"/>
        <display:setProperty name="export.pdf" value="true" />
        <display:setProperty name="export.excel" value="false" />
        <display:setProperty name="export.xml" value="false" />
        <display:setProperty name="export.csv" value="false" />
    </display:table>
</div>