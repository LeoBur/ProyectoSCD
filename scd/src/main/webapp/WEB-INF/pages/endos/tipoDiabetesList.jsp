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
		$('#tipoBusqueda').autocomplete({
			serviceUrl : '/getTipoDiabetes',
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

    	  if(window.confirm('Esta seguro de querer eliminar este Tipo ?')){
              var url = 'delete/'+bookId;
              window.location.href = url;
        }
    } */
</script>
</head>
<body class="home">
<div class="container-fluid">
<div class="col-md-2">
    <h3></h3>
</div>

<div class="col-md-8">
    <div class="well">
		<form:form commandName="tipoDiabetesList" method="post" action="tipoDiabetesList" enctype="multipart/form-data" id="mainSearchBox">
			<appfuse:label key="tipoDiabetesList.nombreToSearch" styleClass="control-label" />
			<div class="form-group">
				<div class="row">
					<div class="col-sm-6 form-group">
					<form:input cssClass="form-control" path="tipoDiab" id="tipoBusqueda"/>
					</div>
					<div id="actions" class="btn-group">
					<span>
					<button id="button-id" type="submit" onclick="bCancel=false" class="btn btn-primary"><fmt:message key="button.search" /></button>
					</span>
					</div>
				</div>
			</div>
			<div id="actions" class="btn-group">
				<a class="btn btn-primary" href="<c:url value='/endos/tipoDiabetesForm'/>">
					<i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>
			    </a>
			</div>

			<display:table  name="tipoDiabetesList" cellspacing="0" cellpadding="0" requestURI=""
						   defaultsort="1" id="tipoDiabetesList" pagesize="25"
						   class="table table-condensed table-striped table-hover" export="false" style="width: 100%">
				    <display:setProperty name="decorator.media.pdf" value="com.bcpv.webapp.displaytag.decorators.ItextTotalWrapper"/>
					<display:caption><h3>Tipos de Diabetes</h3></display:caption>
					<display:caption media="pdf">Tipos de Diabetes</display:caption>
					<display:column property="tipoDiab" escapeXml="true" sortable="true" titleKey="user.adminMedicamento.nombreGenerico"/>
					<display:column property="Caract" escapeXml="true" sortable="true" titleKey="user.adminMedicamento.nombreComercial">
					</display:column>

					<display:column titleKey="activeEndos.acciones" sortable="true">
								<a href="${pageContext.request.contextPath}/endos/tipoDiabetesForm?id=${tipoDiabetesList.id_tipo}">Editar</a>
								<%-- <a href="${pageContext.request.contextPath}/adminMedicamento.jsp">Nuevo</a>
								<a href="${pageContext.request.contextPath}/endos/adminMedicamento/${tipoDiabetesList.idMedicamento}.jsp">Editar</a>
								<a onclick="javascript:deleteBook(${tipoDiabetesList.idMedicamento})" href="edit/${tipoDiabetesList.idMedicamento}">Probar esto ahora</a>
								<a onclick="javascript:deleteBook(${tipoDiabetesList.idMedicamento})" >Y que hacemo</a>

								<a href="javascript:deleteBook(${tipoDiabetesList.idMedicamento})">Enlace</a>   <!-- Este creo es el que mejor se ajusta leo -->

								<a href="deletMedicamento.jsp">Eliminar</a> --%>
					</display:column>
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





