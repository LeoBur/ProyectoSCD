<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<head>
<title>Prescripciones</title>
<meta name="menu" content="Endo" />
<link href="<c:url value="/scripts/main.css" />" rel="stylesheet">
<script src="/scripts/jquery.1.10.2.min.js" type="text/javascript"></script>
<script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>
</head>
<body class="home">

<div class="container-fluid">
<div class="col-md-2">
    <h3>Prescripciones</h3>
</div>
<div class="col-md-6">

	<div class="well">
	  <div class="row">
	    <display:table name="prescripcionesList" id="table" defaultsort="0" pagesize="12"
	        class="table table-condensed table-striped table-hover" export="false">

	          <display:setProperty name="decorator.media.pdf" value="com.bcpv.webapp.displaytag.decorators.ItextTotalWrapper"/>
	          <display:caption><h3>Tratamiento ${paciente}</h3></display:caption>
	          <display:caption media="pdf">Tratamiento ${paciente} ${fechaTratamiento}</display:caption>
    		  <display:column property="medicamento.nombreComercial" title="Nombre Comercial"/>
    		  <display:column property="medicamento.presentacion" title="Presentacion"/>
    		  <display:column property="descripcion" title="Indicaciones"/>
              <display:setProperty name="export.pdf" value="true" />
              <display:setProperty name="export.excel" value="false" />
              <display:setProperty name="export.xml" value="false" />
              <display:setProperty name="export.csv" value="false" />
    	</display:table>
      </div>
      <div class="row">
        <div class="col-md-2 col-md-offset-10">
            <div>
                <div class="form-group">
                    <div class="row">
                        <div id="actions" class="btn-group">
                            <a class="btn btn-primary" href="<c:url value='/endos/tratamientoList?search=${idTratamiento}'/>">
                            <i class="icon-plus icon-white"></i> <fmt:message key="button.back"/></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
      </div>
	</div>
</div>
</div>
</body>
<c:set var="scripts" scope="request">
<v:javascript formName="endoSearch" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
</c:set>