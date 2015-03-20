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

<c:if test="${empty dietaList}">
  <c:redirect url="/nutricionista/dieta?username=${username}"/>
</c:if>

<div class="container-fluid">
<div class="col-md-2">
    <h3>Dietas de</h3>
    <h3>${paciente}</h3>
</div>
<div class="col-md-6">

	<div class="well">
        <div class="row">
		<display:table name="dietaList" id="parent" pagesize="12" defaultsort="1" defaultorder="descending"
		    class="table table-condensed table-striped table-hover" export="false">
		  <display:caption><h3>${paciente}</h3></display:caption>
		  <display:caption media="pdf">${paciente}</display:caption>
		  <display:column property="fechaAlta" title="Fecha de la Dieta"/>
		  <display:column href="dietas.jsp" paramId="id" paramProperty="idDieta">
		    Ver Dieta
		  </display:column>
		  <display:column href="editDietajsp" paramId="search" paramProperty="idDieta">
          	Editar Dieta
          </display:column>
		</display:table>
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
                    <a class="btn btn-primary" href="<c:url value='/nutricionista/dieta?username=${username}'/>">
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