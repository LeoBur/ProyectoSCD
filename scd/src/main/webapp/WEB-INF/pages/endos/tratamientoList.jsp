<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<head>
<title>Tratamientos</title>
<meta name="menu" content="Endo" />
<link href="<c:url value="/scripts/main.css" />" rel="stylesheet">
<script src="/scripts/jquery.1.10.2.min.js" type="text/javascript"></script>
<script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>
</head>
<body class="home">

<c:if test="${empty tratamientoList}">
  <c:redirect url="/endos/tratamiento?search=${dni}"/>
</c:if>

<div class="container-fluid">
<div class="col-md-2">
    <h3>Tratamientos de</h3>
    <h3>${paciente}</h3>
</div>
<div class="col-md-6">

	<div class="well">
        <div class="row">

        <c:choose>
              <c:when test="${enabled=='true'}">
                <display:table name="tratamientoList" id="parent" pagesize="12" defaultsort="1" defaultorder="descending"
                    class="table table-condensed table-striped table-hover" export="false" >
                  <display:caption><h3>${paciente}</h3></display:caption>
                  <display:caption media="pdf">${paciente}</display:caption>
                  <display:column property="fechaTratamiento" title="Fecha del Tratamiento" format="{0,date,dd-MM-yyyy}"/>
                  <display:column href="prescripciones.jsp" paramId="id" paramProperty="idTratamiento">
                    Ver Prescripcion
                  </display:column>
                  <display:column href="editTratamiento.jsp" paramId="search" paramProperty="idTratamiento">
                    Editar Tratamiento
                  </display:column>
                </display:table>
              </c:when>
              <c:otherwise>
                <display:table name="tratamientoList" id="parent" pagesize="12" defaultsort="1" defaultorder="descending"
                    class="table table-condensed table-striped table-hover" export="false">
                  <display:caption><h3>${paciente}</h3></display:caption>
                  <display:caption media="pdf">${paciente}</display:caption>
                  <display:column property="fechaTratamiento" title="Fecha del Tratamiento"/>
                  <display:column href="prescripciones.jsp" paramId="id" paramProperty="idTratamiento">
                    Ver Prescripcion
                  </display:column>
                </display:table>
              </c:otherwise>
        </c:choose>
		</div>
        <br>
        <div class="row">
            <div class="col-md-4 col-md-offset-8">
              <div class="form-group">
                <div class="row">
                  <div id="actions" class="btn-group">
                    <span>
                      <a class="btn btn-primary" href="<c:url value='/endos/pacienteList'/>">
                      <i class="icon-plus icon-white"></i> <fmt:message key="button.back"/></a>
                    </span>
                  </div>
                  <div id="actions" class="btn-group">
                    <a class="btn btn-primary" href="<c:url value='/endos/tratamiento?search=${dni}'/>">
                    <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
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