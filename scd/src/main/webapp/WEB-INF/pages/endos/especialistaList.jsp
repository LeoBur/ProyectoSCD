<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="home.title" /></title>
<meta name="menu" content="Endo" />
<link href="<c:url value="/scripts/main.css" />" rel="stylesheet">
<script src="/scripts/jquery.1.10.2.min.js" type="text/javascript"></script>
<script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>
</head>
<body class="home">
	<h2>
		<fmt:message key="home.heading" />
	</h2>

<div class="col-sm-7">
		
	
    	
	 	<display:table  name="especialistaList" cellspacing="0" cellpadding="0" requestURI=""
	                   defaultsort="1" id="especialistaList" pagesize="25" class="table table-condensed table-striped table-hover" export="false"><!-- export en false te desabilita la exportacion -->
	        	<display:column property="dni" escapeXml="true" sortable="true" titleKey="user.adminMedicamento.nombreGenerico" style="width: 25%"/>
	        	<display:column property="nombre" escapeXml="true" sortable="true" titleKey="user.adminMedicamento.nombreComercial"
	                        style="width: 34%">
	            </display:column>
	            
	            <display:column property="apellido" escapeXml="true" sortable="true" titleKey="user.adminMedicamento.nombreComercial"
	                        style="width: 34%">
	            </display:column>
	            <display:column property="domicilio" escapeXml="true" sortable="true" titleKey="user.adminMedicamento.nombreComercial"
	                        style="width: 34%">
	            </display:column>
	            <display:column property="telefono" escapeXml="true" sortable="true" titleKey="user.adminMedicamento.nombreComercial"
	                        style="width: 34%">
	            </display:column>
	            <display:column property="email" escapeXml="true" sortable="true" titleKey="user.adminMedicamento.nombreComercial"
	                        style="width: 34%">
	            </display:column>
	            
	            <display:column titleKey="activeEndos.acciones" sortable="true">
                    		<a href="${pageContext.request.contextPath}/adminMedicamento.jsp">Nuevo</a>
            				<a href="${pageContext.request.contextPath}/endos/adminMedicamento/${medicamentoList.idMedicamento}.jsp">Editar</a>
            				<a href="deletMedicamento.jsp">Eliminar</a>
                </display:column>
	            
		</display:table>
</div>
</body>
<c:set var="scripts" scope="request">
<v:javascript formName="endoSearch" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
</c:set>