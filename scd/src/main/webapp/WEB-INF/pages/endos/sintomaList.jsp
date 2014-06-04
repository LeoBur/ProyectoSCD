<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="home.title" /></title>
<meta name="menu" content="Endo" />
<link href="<c:url value="/scripts/main.css" />" rel="stylesheet">
<script src="/scripts/jquery.1.10.2.min.js" type="text/javascript"></script>
<script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>
<script type="text/javascript">
	//javascript para el autocomplete de ciudades
	$(document).ready(function() {
		$('#pacienteToSearch').autocomplete({
			serviceUrl : '/getTags',
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
</script>
</head>
<body class="home">
	<h2>
		<fmt:message key="home.heading" />
	</h2>

<div class="col-sm-7">

	<form:form commandName="endoSearch" method="post" action="endo"
		enctype="multipart/form-data" id="mainSearchBox">
		<spring:bind path="endoSearch.pacienteToSearch">
			<div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
		</spring:bind>
			<appfuse:label key="endo.pacienteToSearch" styleClass="control-label" />
			<form:input cssClass="form-control" path="pacienteToSearch" placeHolder="${mainPlaceHolder}" id="pacienteToSearch" />
			<form:errors path="pacienteToSearch" cssClass="help-block" />
		
		<div class="form-group">
			<button type="submit" name="upload" class="btn btn-primary"
				onclick="bCancel=false">
				<i class="icon-upload icon-white"></i>
				<fmt:message key="button.upload" />
			</button>
		</div>
    	
	 	<display:table  name="sintomaList" cellspacing="0" cellpadding="0" requestURI=""
	                   defaultsort="1" id="sintomaList" pagesize="25" class="table table-condensed table-striped table-hover" export="false"><!-- export en false te desabilita la exportacion -->
	        	<display:column property="idSintoma" escapeXml="true" sortable="true" titleKey="user.adminMedicamento.nombreGenerico" style="width: 25%"/>
	        	<display:column property="nombre" escapeXml="true" sortable="true" titleKey="user.adminMedicamento.nombreComercial"
	                        style="width: 34%">
	            </display:column>
	            
	            <display:column titleKey="activeEndos.acciones" sortable="true">
                    		<a href="newSintoma.jsp">Nuevo</a>
            				<a href="editSintoma.jsp">Editar</a>
            				<a href="deletSintoma.jsp">Eliminar</a>
                </display:column>
	            
		</display:table>
	</form:form>
</div>
</body>
<c:set var="scripts" scope="request">
<v:javascript formName="endoSearch" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
</c:set>