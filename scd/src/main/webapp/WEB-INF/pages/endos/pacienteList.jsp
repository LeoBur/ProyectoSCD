<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>

<c:set var="lista" value="${pacienteList}"/>

<head>
<title><fmt:message key="home.title" /></title>
<meta name="menu" content="Endo" />
<link href="<c:url value="/scripts/main.css" />" rel="stylesheet">
<script src="/scripts/jquery.1.10.2.min.js" type="text/javascript"></script>
<script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>
<script>
    $(document).ready(function() {
        $('#endo-input-search').autocomplete({
            serviceUrl: '${ctx}/endos/pacienteList/getTags',
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
           var search = $('input[name=endo-input-search]').val();
           window.location.href = "${ctx}/endos/pacienteList?search=search&dni="+search;
        });
    });
</script>
</head>

<div class="container-fluid">
<div class="col-md-2">
  <div class='text-center'>
    <h3>Pacientes</h3>
  </div>
</div>

<div class="col-md-9">
    <div class="well">
        <div>
            <div class="form-group">
                <div class="row">
                    <div class="col-md-6 form-group">
	                    <input type="text" id="endo-input-search" name="endo-input-search" class="form-control" placeholder="Busque por DNI o apellido">
                    </div>
	                <div id="actions" class="btn-group">
	                    <span>
	                        <button id="button-id" type="button" class="btn btn-primary"><fmt:message key="button.search" /></button>
	                    </span>
	                </div>
	                <div id="actions" class="btn-group">
                        <a class="btn btn-primary" href="<c:url value='/endos/newPaciente'/>">
                        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
                    </div>
                </div>
            </div>
	    </div>
        <br></br>
        <c:if test="${not empty lista}">
            <table class="table table-condensed table-striped table-hover" style="width: 100%">
                <tr>
                    <th align="left" class="sortable sorted order1"><fmt:message key="user.dni" /></th>
                    <th class="sortable sorted order1"><fmt:message key="user.lastName" /></th>
                    <th class="sortable sorted order1"><fmt:message key="user.firstName" /></th>
                    <th class="sortable sorted order1" align="center"><fmt:message key="user.enabled" /></th>
                    <th class="sortable sorted order1" align="center"><fmt:message key="activeEndos.acciones" /></th>
                    <th class="sortable sorted order1" style="text-align:center">Detalles</th>
                    <th class="sortable sorted order1" align="center">Especialistas</th>
                </tr>
                <c:forEach var="endo" items="${pacienteList}" varStatus="index" >
                    <tr>
                        <td><c:out value="${endo.paciente.persona.dni}" /></td>
                        <td><c:out value="${endo.paciente.persona.lastName}" /></td>
                        <td><c:out value="${endo.paciente.persona.firstName}" /></td>
                        <td align="center">
                            <c:choose>
                                <c:when test="${endo.paciente.persona.enabled == 'true'}">
                                    <input type="checkbox" checked="true" disabled="true"/>
                                </c:when>
                                <c:when test="${endo.paciente.persona.enabled == 'false'}">
                                    <input type="checkbox" disabled="false"/>
                                </c:when>
                            </c:choose>
                        </td>
                        <td align="center"><a href="${ctx}/endos/newPaciente?search=search&dni=${endo.paciente.persona.dni}">Editar</a></td>
                        <td align="center">
                            <a href="${ctx}/endos/tratamientoList?search=${endo.paciente.persona.dni}">Tratamientos</a>
                            |
                            <a href="${ctx}/endos/medicionesPaciente?idPaciente=${endo.paciente.id}">Mediciones</a>
                            |
                            <a href="${ctx}/endos/pesosPaciente?idPaciente=${endo.paciente.id}">Pesos</a>
                        </td>
                        <td align="center"><a href="${ctx}/endos/especialistaListPaciente?search=${endo.paciente.persona.dni}">Ver</a></td>
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