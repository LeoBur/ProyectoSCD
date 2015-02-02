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

		<div class="well">
                <div>
                    <div class="form-group">
                        <div class="row">
        	                <div id="actions" class="btn-group">
        	                    <span>
        	                        <button id="button-id" type="button" class="btn btn-primary"><fmt:message key="button.search" /></button>
        	                    </span>
        	                </div>
        	                <div id="actions" class="btn-group">
                                <a class="btn btn-primary" href="<c:url value='/endos/tratamiento?search=${dni}'/>">
                                <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
                            </div>
                        </div>
                    </div>
        	    </div>

		<display:table name="tratamientoList" id="parent" pagesize="12" defaultsort="1"
		    class="table table-condensed table-striped table-hover">
		  <display:column property="fechaTratamiento" title="Fecha del Tratamiento" href="prescripciones.jsp"
		        paramId="id" paramProperty="idTratamiento" />
		</display:table>

	</div>
</div>
</body>
<c:set var="scripts" scope="request">
<v:javascript formName="endoSearch" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
</c:set>