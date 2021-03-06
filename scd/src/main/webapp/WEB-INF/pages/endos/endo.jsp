<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="home.title" /></title>
<meta name="menu" content="Endo" />
<link href="<c:url value="/scripts/main.css" />" rel="stylesheet">
<script src="/scripts/jquery.1.10.2.min.js" type="text/javascript"></script>
<script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>
<script type="text/javascript">
	//javascript para el autocomplete de apellidoPaciente
	$(document).ready(function() {
		$('#apellidoPaciente').autocomplete({
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
    <spring:bind path="endoSearch.*">
        <c:if test="${not empty status.errorMessages}">
        <div class="alert alert-danger alert-dismissable">
            <a href="#" data-dismiss="alert" class="close">&times;</a>
            <c:forEach var="error" items="${status.errorMessages}">
                <c:out value="${error}" escapeXml="false"/><br />
            </c:forEach>
        </div>
        </c:if>
    </spring:bind>
    
    <%-- <div id="actions" class="btn-group">
        <a class="btn btn-primary" href="<c:url value='/endos/pacienteForm'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a class="btn btn-default" href="<c:url value='/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div> --%>
    
	<form:form commandName="endoSearch" method="post" action="endo" enctype="multipart/form-data" id="mainSearchBox">
		<spring:bind path="endoSearch.apellidoPaciente">
			<div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
		</spring:bind>
				<appfuse:label key="endo.apellidoToSearch" styleClass="control-label" />
				<form:input cssClass="form-control" path="apellidoPaciente" placeHolder="${apellidoPlaceHolder}" id="apellidoPaciente"/>
				<form:errors path="apellidoPaciente" cssClass="help-block" />
			</div>
		
		<spring:bind path="endoSearch.dniPaciente">
			<div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
		</spring:bind>
				<appfuse:label key="endo.dniToSearch" styleClass="control-label" />
				<form:input cssClass="form-control" path="dniPaciente" placeHolder="${dniPlaceHolder}" id="dniPaciente"/>
				<form:errors path="dniPaciente" cssClass="help-block" />
			</div>
		
		<div class="form-group">
			<button type="submit" name="upload" class="btn btn-primary"
				onclick="bCancel=false">
				<i class="icon-upload icon-white"></i>
				<fmt:message key="button.search" />
			</button>
		</div>
		
		<display:table  name="pacienteList" cellspacing="0" cellpadding="0" requestURI=""
	                   defaultsort="1" id="pacienteList" pagesize="25" class="table table-condensed table-striped table-hover" export="false">
	        	<display:column  property="nombre" escapeXml="true" sortable="true" titleKey="user.firstName" style="width: 25%"/>
	        	<display:column property="apellido" escapeXml="true" sortable="true" titleKey="user.lastName"
	                        style="width: 34%">
	                        <a href="endos/adminMedicamento:<c:out value="Editar Medicamentos"/>"></a>
	            </display:column>
	            <display:column  property="email" escapeXml="true" sortable="true" titleKey="user.email" style="width: 25%"/>
	           <%-- <display:column  property="tipo" escapeXml="true" sortable="true" titleKey="active.momentodia" style="width: 25%"/> --%> 
	            
	            <display:column titleKey="activeEndos.acciones" sortable="true">
            				<a href="${pageContext.request.contextPath}/endos/pacienteForm?id=${pacienteList.id}">Editar</a>            				
            				<%-- <a href="${pageContext.request.contextPath}/endos/pacienteForm" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">Eliminar Tengo que ver para que elimine directo</a> --%>
                </display:column>
	</display:table>
	</form:form>
	
</div>
</body>
<c:set var="scripts" scope="request">
<v:javascript formName="endoSearch" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
</c:set>


<!-- De aca para abajo pruebo las paginas hasta que anden las vistas para mostrar e ir probando -->

