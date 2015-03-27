<%@ include file="/common/taglibs.jsp"%>

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

<div class="col-md-2">
    <h3>Especialistas de</h3>
    <h3>${pacienteFullName}</h3>
</div>

<div class="col-md-9">
    <div class="well">
        <div>
            <div class="form-group">
                <div class="row">
                    <div class="col-sm-6 form-group">
	                    <input type="text" id="especialista-input-search" name="especialista-input-search" class="form-control">
                    </div>
	                <div id="actions" class="btn-group">
	                    <span>
	                        <button id="button-id" type="button" class="btn btn-primary"><fmt:message key="button.search" /></button>
	                    </span>
	                </div>
	                <c:if test="${empty especialistaPaciente}">
                        <div id="actions" class="btn-group">
                            <a class="btn btn-primary" href="<c:url value='/endos/asignarEspecialista?idPacienteTratamiento=${idPacienteTratamiento}'/>">
                            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
                        </div>
                    </c:if>
                </div>
            </div>
	    </div>
        <br></br>
        <c:if test="${not empty especialistaPaciente}">
            <table class="table table-condensed table-striped table-hover">
                <tr>
                    <th style="width: 30%" class="sortable sorted order1"><fmt:message key="user.lastName" /></th>
                    <th style="width: 30%" class="sortable sorted order1"><fmt:message key="user.firstName" /></th>
                    <th style="width: 30%" class="sortable sorted order1"><fmt:message key="user.tipo_esp" /></th>
                    <th style="width: 30%" class="sortable sorted order1"><fmt:message key="activeEndos.eliminar" /></th>
                </tr>
                    <tr>
                        <td style="width: 30%">
                            <c:out value="${especialistaPaciente.persona.lastName}" />
                        </td>
                        <td style="width: 30%">
                            <c:out value="${especialistaPaciente.persona.firstName}" />
                        </td>
                        <td style="width: 30%">
                            <c:out value="${especialistaPaciente.tipo_esp}" />
                        </td>
                        <td style="width: 30%">
                            <a href="${ctx}/endos/desvincularEspecialista?idPacienteTratamiento=${idPacienteTratamiento}&idEspecialista=${especialistaPaciente.id}">Desvincular</a>
                        </td>
                    </tr>
            </table>
        </c:if>
    </div>
</div>
</body>
<c:set var="scripts" scope="request">
<v:javascript formName="endoSearch" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
</c:set>