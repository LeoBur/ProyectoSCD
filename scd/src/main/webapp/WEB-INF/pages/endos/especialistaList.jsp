<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>
<c:set var="lista" value="${especialistaList}"/>

<head>
<title><fmt:message key="home.title" /></title>
<meta name="menu" content="Especialista" />
<link href="<c:url value="/scripts/main.css" />" rel="stylesheet">
<script src="/scripts/jquery.1.10.2.min.js" type="text/javascript"></script>
<script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>
<script>
    $(document).ready(function() {
        $('#especialista-input-search').autocomplete({
            serviceUrl: '${ctx}/endos/getTags',
            paramName: "tagName",
            delimiter: "," ,
            transformResult: function(response) {
            return {
            suggestions: $.map($.parseJSON(response), function(item) {
                return {value: item.tagName, data: item.id };
            })
            };
            }
        });

        $('#button-id').click(function(e) {
           var search = $('input[name=especialista-input-search]').val();
           window.location.href = "${ctx}/endos/especialistaList?search=search&dni="+search;
        });
    });
</script>
</head>
<div class="container-fluid">
    <div class="col-md-2">
        <h3>Especialistas</h2>
    </div>

    <div class="col-md-9">
        <div class="well">
            <div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-6 form-group">
                            <input type="text" id="especialista-input-search" name="especialista-input-search" class="form-control">
                        </div>
                        <div id="actions" class="btn-group">
                            <span>
                                <button id="button-id" type="button" class="btn btn-primary"><fmt:message key="button.search" /></button>
                            </span>
                        </div>
                        <div id="actions" class="btn-group">
                            <a class="btn btn-primary" href="<c:url value='/endos/newEspecialista'/>">
                            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
                        </div>
                    </div>
                </div>
            </div>
            <br></br>
            <c:if test="${not empty lista}">
                <table class="table table-condensed table-striped table-hover" style="width: 100%">
                    <tr>
                        <th class="sortable sorted order1">
                            <fmt:message key="user.dni" />
                        </th>
                        <th class="sortable sorted order1">
                            <fmt:message key="user.lastName" />
                        </th>
                        <th class="sortable sorted order1"><fmt:message key="user.firstName" /></th>
                        <th class="sortable sorted order1"><fmt:message key="user.tipo_esp" /></th>
                        <th class="sortable sorted order1" align="center"><fmt:message key="user.enabled" /></th>
                        <th class="sortable sorted order1"><fmt:message key="activeEndos.acciones" /></th>
                    </tr>
                    <c:forEach var="especialista" items="${especialistaList}" varStatus="index" >
                                <tr>
                                    <td>
                                        <c:out value="${especialista.persona.dni}" />
                                    </td>
                                    <td>
                                        <c:out value="${especialista.persona.lastName}" />
                                    </td>
                                    <td>
                                        <c:out value="${especialista.persona.firstName}" />
                                    </td>
                                    <td>
                                        <c:out value="${especialista.tipo_esp}" />
                                    </td>
                                    <td align="center">
                                        <c:choose>
                                            <c:when test="${especialista.persona.enabled == 'true'}">
                                                <input type="checkbox" checked="true" disabled="true"/>
                                            </c:when>
                                            <c:when test="${especialista.persona.enabled == 'false'}">
                                                <input type="checkbox" disabled="false"/>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="${ctx}/endos/newEspecialista?search=search&dni=${especialista.persona.dni}">Editar</a>
                                    </td>
                                </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </div>
</div>
</body>
<c:set var="scripts" scope="request">
<v:javascript formName="endoSearch" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
</c:set>