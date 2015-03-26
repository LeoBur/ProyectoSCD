<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>

<c:set var="lista" value="${especialistasActivos}"/>

<head>
<title><fmt:message key="home.title" /></title>
<meta name="menu" content="Endo" />
<link href="<c:url value="/scripts/main.css" />" rel="stylesheet">
<script src="/scripts/jquery.1.10.2.min.js" type="text/javascript"></script>
<script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>
<script>
    $(document).ready(function() {
        $('#endo-input-search').autocomplete({
            serviceUrl: '${ctx}/endos/asignarEspecialista/getTags',
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
    <h3>Asignar Especialista</h3>
  </div>
</div>

<div class="col-md-8">
    <div class="well">
        <div>
            <div class="form-group">
                <div class="row">
                    <div class="col-sm-6 form-group">
	                    <input type="text" id="endo-input-search" name="endo-input-search" class="form-control">
                    </div>
	                <div id="actions" class="btn-group">
	                    <span>
	                        <button id="button-id" type="button" class="btn btn-primary"><fmt:message key="button.search" /></button>
	                    </span>
	                </div>
                </div>
            </div>
	    </div>
        <br></br>
        <c:if test="${not empty lista}">
            <table class="table table-condensed table-striped table-hover">
                <tr>
                    <th style="width: 20%" class="sortable sorted order1">
                        <fmt:message key="user.lastName" />
                    </th>
                    <th style="width: 20%" class="sortable sorted order1"><fmt:message key="user.firstName" /></th>
                    <th style="width: 10%" class="sortable sorted order1"><fmt:message key="user.tipo_esp" /></th>
                    <th style="width: 15%" class="sortable sorted order1"><fmt:message key="activeEndos.asignar" /></th>
                </tr>
            </table>
        </c:if>
        <c:forEach var="especialista" items="${especialistasActivos}" varStatus="index" >
                <table class="table table-condensed table-striped table-hover">
                    <tr>
                        <td style="width: 20%">
                            <c:out value="${especialista.persona.lastName}" />
                        </td>
                        <td style="width: 20%">
                            <c:out value="${especialista.persona.firstName}" />
                        </td>
                        <td style="width: 20%">
                            <c:out value="${especialista.tipo_esp}" />
                        </td>
                        </td>
                        <td style="width: 15%">
                            <a href="${ctx}/endos/asignarUnEspecialistaAPaciente?idPacienteTratamiento=${idPacienteTratamiento}&idEspecialista=${especialista.id}">Asignar</a>
                        </td>
                    </tr>
                </table>
        </c:forEach>
    </div>
</div>
</div>
</body>
<c:set var="scripts" scope="request">
<v:javascript formName="endoSearch" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
</c:set>