<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<head>
<title><fmt:message key="home.title" /></title>
<meta name="menu" content="Endo" />
<link href="<c:url value="/scripts/main.css" />" rel="stylesheet">
<script src="/scripts/jquery.1.10.2.min.js" type="text/javascript"></script>
<script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#medicamentoBusqueda').autocomplete({
			serviceUrl : '/getMedicamento',
			paramName : "tagName",
			delimiter : ",",
			transformResult : function(response) {
				return {
					suggestions : $.map($.parseJSON(response), function(item) {
						return {
							value : item.tagName,
							data : item.id
						};
					})
				};
			}
		});
	});
    /* function deleteBook(bookId){
    	
    	  if(window.confirm('Esta seguro de querer eliminar este Medicamento ?')){
              var url = 'delete/'+bookId;
              window.location.href = url;     
        }
    } */
</script>
</head>
<body class="home">
<div class="container-fluid">
<div class="col-md-2">
    <h3>Medicamentos</h3>
</div>

<div class="col-md-9">
    <div class="well">
		<form:form commandName="medicamento" method="post" action="medicamentoList" enctype="multipart/form-data" id="mainSearchBox">
			<appfuse:label key="medicamento.nombreToSearch" styleClass="control-label" />
			<div class="form-group">
				<div class="row">
					<div class="col-md-6 form-group">
					<form:input cssClass="form-control" path="nombreComercial" id="medicamentoBusqueda"/>
					</div>
					<div id="actions" class="btn-group">
					<span>
					<button id="button-id" type="submit" onclick="bCancel=false" class="btn btn-primary"><fmt:message key="button.search" /></button>
					</span>
					</div>
                    <div id="actions" class="btn-group">
                        <a class="btn btn-primary" href="<c:url value='/endos/medicamentoForm'/>">
                            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>
                        </a>
                    </div>
				</div>
			</div>

			<display:table  name="medicamentoList" cellspacing="0" cellpadding="0" requestURI=""
						   defaultsort="1" id="medicamentoList" pagesize="25"
						   class="table table-condensed table-striped table-hover" export="false">
				    <display:setProperty name="decorator.media.pdf" value="com.bcpv.webapp.displaytag.decorators.ItextTotalWrapper"/>
					<display:column property="nombreGenerico" escapeXml="true" sortable="true" titleKey="user.adminMedicamento.nombreGenerico" style="width: 25%" />
					<display:column property="nombreComercial" escapeXml="true" sortable="true" titleKey="user.adminMedicamento.nombreComercial"
								style="width: 34%">
					</display:column>



					<display:column titleKey="activeEndos.acciones" sortable="true">
								<a href="${pageContext.request.contextPath}/endos/medicamentoForm?id=${medicamentoList.idMedicamento}">Editar</a>
								<%-- <a href="${pageContext.request.contextPath}/adminMedicamento.jsp">Nuevo</a>
								<a href="${pageContext.request.contextPath}/endos/adminMedicamento/${medicamentoList.idMedicamento}.jsp">Editar</a>
								<a onclick="javascript:deleteBook(${medicamentoList.idMedicamento})" href="edit/${medicamentoList.idMedicamento}">Probar esto ahora</a>
								<a onclick="javascript:deleteBook(${medicamentoList.idMedicamento})" >Y que hacemo</a>

								<a href="javascript:deleteBook(${medicamentoList.idMedicamento})">Enlace</a>   <!-- Este creo es el que mejor se ajusta leo -->

								<a href="deletMedicamento.jsp">Eliminar</a> --%>
					</display:column>
                    <display:setProperty name="export.pdf" value="true" />
                    <display:setProperty name="export.excel" value="false" />
                    <display:setProperty name="export.xml" value="false" />
                    <display:setProperty name="export.csv" value="false" />
			</display:table>

		</form:form>
	</div>
</div>
</div>
</body>
<c:set var="scripts" scope="request">
<v:javascript formName="endoSearch" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
</c:set>





