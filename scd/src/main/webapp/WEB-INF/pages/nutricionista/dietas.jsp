<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<head>
<title>Dietas</title>
<meta name="menu" content="Endo" />
<link href="<c:url value="/scripts/main.css" />" rel="stylesheet">
<script src="/scripts/jquery.1.10.2.min.js" type="text/javascript"></script>
<script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>
</head>
<body class="home">

<div class="container-fluid">
<div class="col-md-2">
    <h3>Dietas</h3>
</div>
<div class="col-md-6">

	<div class="well">
	  <div class="row">
	    <legend class="accordion-heading" style="text-align:center">
            <a data-toggle="collapse" href="#Lunes"><fmt:message key="Lunes"/></a>
        </legend>
        <div id="Lunes" class="accordion-body collapse">
	    <display:table name="lunesList" id="table" defaultsort="0" pagesize="12"
	        class="table table-condensed table-striped table-hover" export="false">

	          <display:setProperty name="decorator.media.pdf" value="com.bcpv.webapp.displaytag.decorators.ItextTotalWrapper"/>
	          <display:caption media="pdf">Dieta ${paciente} ${fechaAlta}</display:caption>
              <display:column property="momento" title="Momento del Dia"/>
              <display:column property="comidaDia" title="Nombre de la comida"/>
              <display:setProperty name="export.pdf" value="true" />
              <display:setProperty name="export.excel" value="false" />
              <display:setProperty name="export.xml" value="false" />
              <display:setProperty name="export.csv" value="false" />
    	</display:table>
    	</div>
        <legend class="accordion-heading" style="text-align:center">
            <a data-toggle="collapse" href="#Martes"><fmt:message key="Martes"/></a>
        </legend>
        <div id="Martes" class="accordion-body collapse">
    	        <display:table name="martesList" id="table" defaultsort="0" pagesize="12"
        	        class="table table-condensed table-striped table-hover" export="false">

        	          <display:setProperty name="decorator.media.pdf" value="com.bcpv.webapp.displaytag.decorators.ItextTotalWrapper"/>

        	          <display:caption media="pdf">Dieta ${paciente} ${fechaAlta}</display:caption>
                      <display:column property="momento" title="Momento del Dia"/>
                      <display:column property="comidaDia" title="Nombre de la comida"/>
                      <display:setProperty name="export.pdf" value="true" />
                      <display:setProperty name="export.excel" value="false" />
                      <display:setProperty name="export.xml" value="false" />
                      <display:setProperty name="export.csv" value="false" />
            	</display:table>
        </div>
        <legend class="accordion-heading" style="text-align:center">
            <a data-toggle="collapse" href="#Miercoles"><fmt:message key="Miercoles"/></a>
        </legend>
        <div id="Miercoles" class="accordion-body collapse">
        <display:table name="miercolesList" id="table" defaultsort="0" pagesize="12"
	        class="table table-condensed table-striped table-hover" export="false">

	          <display:setProperty name="decorator.media.pdf" value="com.bcpv.webapp.displaytag.decorators.ItextTotalWrapper"/>

	          <display:caption media="pdf">Dieta ${paciente} ${fechaAlta}</display:caption>
              <display:column property="momento" title="Momento del Dia"/>
              <display:column property="comidaDia" title="Nombre de la comida"/>
              <display:setProperty name="export.pdf" value="true" />
              <display:setProperty name="export.excel" value="false" />
              <display:setProperty name="export.xml" value="false" />
              <display:setProperty name="export.csv" value="false" />
    	</display:table>
    	</div>
    	<legend class="accordion-heading" style="text-align:center">
            <a data-toggle="collapse" href="#Jueves"><fmt:message key="Jueves"/></a>
        </legend>
        <div id="Jueves" class="accordion-body collapse">
            <display:table name="juevesList" id="table" defaultsort="0" pagesize="12"
                class="table table-condensed table-striped table-hover" export="false">

                  <display:setProperty name="decorator.media.pdf" value="com.bcpv.webapp.displaytag.decorators.ItextTotalWrapper"/>

                  <display:caption media="pdf">Dieta ${paciente} ${fechaAlta}</display:caption>
                  <display:column property="momento" title="Momento del Dia"/>
                  <display:column property="comidaDia" title="Nombre de la comida"/>
                  <display:setProperty name="export.pdf" value="true" />
                  <display:setProperty name="export.excel" value="false" />
                  <display:setProperty name="export.xml" value="false" />
                  <display:setProperty name="export.csv" value="false" />
            </display:table>
    	</div>
        <legend class="accordion-heading" style="text-align:center">
            <a data-toggle="collapse" href="#Viernes"><fmt:message key="Viernes"/></a>
        </legend>
        <div id="Viernes" class="accordion-body collapse">
        <display:table name="viernesList" id="table" defaultsort="0" pagesize="12"
	        class="table table-condensed table-striped table-hover" export="false">

	          <display:setProperty name="decorator.media.pdf" value="com.bcpv.webapp.displaytag.decorators.ItextTotalWrapper"/>

	          <display:caption media="pdf">Dieta ${paciente} ${fechaAlta}</display:caption>
              <display:column property="momento" title="Momento del Dia"/>
              <display:column property="comidaDia" title="Nombre de la comida"/>
              <display:setProperty name="export.pdf" value="true" />
              <display:setProperty name="export.excel" value="false" />
              <display:setProperty name="export.xml" value="false" />
              <display:setProperty name="export.csv" value="false" />
    	</display:table>
    	</div>
    	<legend class="accordion-heading" style="text-align:center">
            <a data-toggle="collapse" href="#Sabado"><fmt:message key="Sabado"/></a>
        </legend>
        <div id="Sabado" class="accordion-body collapse">
            <display:table name="sabadoList" id="table" defaultsort="0" pagesize="12"
                class="table table-condensed table-striped table-hover" export="false">

                  <display:setProperty name="decorator.media.pdf" value="com.bcpv.webapp.displaytag.decorators.ItextTotalWrapper"/>

                  <display:caption media="pdf">Dieta ${paciente} ${fechaAlta}</display:caption>
                  <display:column property="momento" title="Momento del Dia"/>
                  <display:column property="comidaDia" title="Nombre de la comida"/>
                  <display:setProperty name="export.pdf" value="true" />
                  <display:setProperty name="export.excel" value="false" />
                  <display:setProperty name="export.xml" value="false" />
                  <display:setProperty name="export.csv" value="false" />
            </display:table>
        </div>
      </div>
      <div class="row">
        <div class="col-md-2 col-md-offset-10">
            <div>
                <div class="form-group">
                    <div class="row">
                        <div id="actions" class="btn-group">
                            <a class="btn btn-primary" href="<c:url value='/nutricionista/dietaList?search=${idDieta}'/>">
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