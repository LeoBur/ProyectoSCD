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
		$('#alimentoBusqueda').autocomplete({
			serviceUrl : '/getAlimento',
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
<div class="container-fluid">
<div class="col-md-2">
    <h3></h3>
</div>
<div class="col-md-8">
	<div class="well">
		<form:form commandName="alimento" method="post" action="alimentoList" enctype="multipart/form-data" id="mainSearchBox">
                			<appfuse:label key="user.food.nombreToSearch" styleClass="control-label" />
                    <div class="form-group">
                    	<div class="row">
                            <div class="col-sm-6 form-group">
                            	<form:input cssClass="form-control" path="nombre" id="alimentoBusqueda"/>
                            </div>
                            <div id="actions" class="btn-group">
                            	<span>
                                	<button id="button-id" type="submit" onclick="bCancel=false" class="btn btn-primary"><fmt:message key="button.search" /></button>
                                </span>
                            </div>
                    	</div>
                    </div>
        			<div id="actions" class="btn-group">
        				<a class="btn btn-primary" href="<c:url value='/nutricionista/cargarAlimento'/>">
        					<i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        				<!--<a class="btn btn-default" href="<c:url value='/endos/endo'/>">
        					<i class="icon-ok"></i> <fmt:message key="button.done"/></a>-->
        			</div>


        			<display:table  name="alimentoList" cellspacing="0" cellpadding="0" requestURI=""
        						   defaultsort="1" id="alimentoList" pagesize="25"
        						   class="table table-condensed table-striped table-hover" export="false">
                            <display:caption><h3>Alimentos</h3></display:caption>
                            <display:caption media="pdf">Alimentos</display:caption>
        					<display:column property="nombre" escapeXml="true" sortable="true" titleKey="food.descripcion"
        								style="width: 34%">
        					</display:column>

        					<display:column titleKey="activeEndos.acciones" sortable="true">
        								<a href="${ctx}/nutricionista/cargarAlimento?id=${alimentoList.idAlimento}">Editar</a>
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