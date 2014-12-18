<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>



<div class="col-sm-10">
    <h2><fmt:message key="userList.ultimoPeso"/></h2>

    <display:table name="pesosList" cellspacing="0" cellpadding="0" requestURI=""
                           defaultsort="0" id="pesos" pagesize="12" class="table table-condensed table-striped table-hover" export="true">
        <display:column property="fechaHora" escapeXml="true" sortable="true" titleKey="active.peso.fecha"
                        style="width: 50%"/>
        <display:column property="peso" escapeXml="true" sortable="true" titleKey="active.peso"
                        style="width: 50%"/>
    </display:table>
</div>