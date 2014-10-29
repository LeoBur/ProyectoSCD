<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="home.title" /></title>
<meta name="menu" content="Endo" />
<link href="<c:url value="/scripts/main.css" />" rel="stylesheet">
<script src="/scripts/jquery.1.10.2.min.js" type="text/javascript"></script>
<script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>
</head>
<body class="home">
	<h2>
		<fmt:message key="home.Endo.Lista" />
	</h2>

<div class="col-sm-7">

        <!--
		<div id="actions" class="btn-group">
	        <a class="btn btn-primary" href="<c:url value='/endos/sintomaForm'/>">
	            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
	        <a class="btn btn-default" href="<c:url value='/endos/endo'/>">
	            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
	    </div>
	    -->

        <table class="table table-condensed table-striped table-hover">
            <tr>
                <th style="width: 30%" class="sortable sorted order1">
                    <fmt:message key="user.endocrinologist.registration" />
                </th>
                <th style="width: 30%" class="sortable sorted order1">
                    <fmt:message key="user.lastName" />
                </th>
                <th style="width: 30%" class="sortable sorted order1"><fmt:message key="user.firstName" /></th>
                <th style="width: 30%" class="sortable sorted order1"><fmt:message key="user.enabled" /></th>
                <th style="width: 30%" class="sortable sorted order1"><fmt:message key="activeEndos.acciones" /></th>
            </tr>
        </table>

        <c:forEach var="endo" items="${endocrinologoList}" varStatus="index" >
            <%--<c:if test="${endo.persona.firstName=='MARCELO'}">--%>
                <table class="table table-condensed table-striped table-hover">
                    <tr>
                        <td style="width: 28%">
                            <c:out value="${endo.matricula}" />
                        </td>
                        <td style="width: 28%">
                            <c:out value="${endo.persona.firstName}" />
                        </td>
                        <td style="width: 34%">
                            <c:out value="${endo.persona.lastName}" />
                        </td>
                        <td style="width: 28%">
                            <c:choose>
                                <c:when test="${endo.persona.enabled == 'true'}">
                                    <input type="checkbox" checked="true" disabled="true"/>
                                </c:when>
                                <c:when test="${endo.persona.enabled == 'false'}">
                                    <input type="checkbox" disabled="false"/>
                                </c:when>
                            </c:choose>
                        </td>
                        <td style="width: 34%">
                            <a href="${ctx}/admin/newEndocrinologo?search=search&dni=${endo.persona.dni}">Editar</a>
                        </td>
                    </tr>
                </table>
            <%--</c:if>--%>
        </c:forEach>

        <%--
	 	<display:table  name="endocrinologoList" cellspacing="0" cellpadding="0" requestURI=""
	                   defaultsort="1" id="endocrinologoList" pagesize="25" class="table table-condensed table-striped table-hover" export="false"><!-- export en false te desabilita la exportacion -->
	        	<display:column property="persona.firstName" escapeXml="true" sortable="true" titleKey="user.firstName"
	                        style="width: 34%">
	            </display:column>
	            <display:column property="persona.lastName" escapeXml="true" sortable="true" titleKey="user.lastName"
                            style="width: 34%">
                </display:column>
                <display:column property="matricula" escapeXml="true" sortable="true" titleKey="user.endocrinologist.registration"
                              style="width: 34%">
                </display:column>

	            <display:column titleKey="activeEndos.acciones" sortable="true">
            				<a href="${ctx}/endos/sintomaForm?id=${endocrinologoList.id}">Editar</a>
                </display:column>

		</display:table>
		--%>

</div>
</body>
<c:set var="scripts" scope="request">
<v:javascript formName="endoSearch" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
</c:set>