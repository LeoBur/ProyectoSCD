<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="menu" content="AdminMenu"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  	<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  	<link rel="stylesheet" href="/resources/demos/style.css">
  	<script>
  	$(function() {
    	$( "#tabs" ).tabs();
  	});
  	</script>
</head>



<div>

<div class="container-fluid">

<div class="col-sm-10">
	<h2><fmt:message key="userList.dieta"/></h2>
        <h3>${paciente}</h3>
    <!-- Ac� comienzan los formularios -->
	<div class="well">
		<div id="tabs">
			<ul>
				<li><a href="#tabs-1">Lunes</a></li>
				<li><a href="#tabs-2">Martes</a></li>
				<li><a href="#tabs-3">Miercoles</a></li>
				<li><a href="#tabs-4">Jueves</a></li>
				<li><a href="#tabs-5">Viernes</a></li>
				<li><a href="#tabs-6">Sabado</a></li>
			</ul>
			<div id="tabs-1">
			  <display:table  name="lunesList" cellspacing="0" cellpadding="0" requestURI=""
					   defaultsort="1" id="users" pagesize="25" class="table table-condensed table-striped table-hover" export="true">
				<display:column property="momento" title="MOMENTO" />
				<display:column property="comidaDia" title="COMIDA" />
			  </display:table>
			</div>
			<div id="tabs-2">
				<display:table  name="martesList" cellspacing="0" cellpadding="0" requestURI=""
					defaultsort="1" id="users" pagesize="25" class="table table-condensed table-striped table-hover" export="true">
					<display:column property="momento" title="MOMENTO" />
					<display:column property="comidaDia" title="COMIDA" />
				</display:table>
			</div>
			<div id="tabs-3">
				<display:table  name="miercolesList" cellspacing="0" cellpadding="0" requestURI=""
					defaultsort="1" id="users" pagesize="25" class="table table-condensed table-striped table-hover" export="true">
					<display:column property="momento" title="MOMENTO" />
					<display:column property="comidaDia" title="COMIDA" />
				</display:table>
			</div>
			<div id="tabs-4">
				<display:table  name="juevesList" cellspacing="0" cellpadding="0" requestURI=""
					defaultsort="1" id="users" pagesize="25" class="table table-condensed table-striped table-hover" export="true">
					<display:column property="momento" title="MOMENTO" />
					<display:column property="comidaDia" title="COMIDA" />
				</display:table>
			</div>
			<div id="tabs-5">
				<display:table  name="viernesList" cellspacing="0" cellpadding="0" requestURI=""
					defaultsort="1" id="users" pagesize="25" class="table table-condensed table-striped table-hover" export="true">
					<display:column property="momento" title="MOMENTO"  />
					<display:column property="comidaDia" title="COMIDA" />
				</display:table>
			</div>
			<div id="tabs-6">
				<display:table  name="sabadoList" cellspacing="0" cellpadding="0" requestURI=""
					defaultsort="1" id="users" pagesize="25" class="table table-condensed table-striped table-hover" export="true">
					<display:column property="momento" title="MOMENTO" />
					<display:column property="comidaDia" title="COMIDA" />
				</display:table>
			</div>
		</div>
	</div>
</div>