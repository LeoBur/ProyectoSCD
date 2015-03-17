<%@ include file="/common/taglibs.jsp"%>
<c:set var="lista" value="${especialistasListPaciente}"/>

<head>
<title><fmt:message key="home.title" /></title>
<meta name="menu" content="Especialista" />
<link href="<c:url value="/scripts/main.css" />" rel="stylesheet">
<script src="/scripts/jquery.1.10.2.min.js" type="text/javascript"></script>
<script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>
<script>
    $(document).ready(function() {
        $('#especialista-input-search').autocomplete({
            serviceUrl: 'http://localhost:8080/endos/getTags',
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
           window.location.href = "http://localhost:8080/endos/especialistaList?search=search&dni="+search;
        });
    });
</script>
</head>

<div class="col-sm-2">
    <h3>Especialistas de</h3>
    <h3>${pacienteFullName}</h3>
</div>

<div class="col-sm-7">
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
	                <div id="actions" class="btn-group">
                        <a class="btn btn-primary" href="<c:url value='/endos/newEspecialista'/>">
                        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
                    </div>
                </div>
            </div>
	    </div>
        <br></br>
        <c:if test="${not empty lista}">
            <table class="table table-condensed table-striped table-hover">
                <tr>
                    <th style="width: 30%" class="sortable sorted order1">
                        <fmt:message key="user.lastName" />
                    </th>
                    <th style="width: 30%" class="sortable sorted order1"><fmt:message key="user.firstName" /></th>
                    <th style="width: 30%" class="sortable sorted order1"><fmt:message key="user.tipo_esp" /></th>
                    <th style="width: 30%" class="sortable sorted order1"><fmt:message key="activeEndos.eliminar" /></th>
                </tr>
            </table>
        </c:if>
        <c:forEach var="especialista" items="${especialistaList}" varStatus="index" >
                <table class="table table-condensed table-striped table-hover">
                    <tr>
                        <td style="width: 28%">
                            <c:out value="${especialista.persona.lastName}" />
                        </td>
                        <td style="width: 34%">
                            <c:out value="${especialista.persona.firstName}" />
                        </td>
                        <td style="width: 34%">
                            <c:out value="${especialista.tipo_esp}" />
                        </td>
                        <td style="width: 34%">
                            <a href="${ctx}/endos/newEspecialista?search=search&dni=${especialista.persona.dni}">Desvincular</a>
                        </td>
                    </tr>
                </table>
                </table>
        </c:forEach>
    </div>
</div>
</body>
<c:set var="scripts" scope="request">
<v:javascript formName="endoSearch" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
</c:set>