<%@ include file="/common/taglibs.jsp"%>
<%@ page import = "java.util.*"%>
<% int a = 5; %>
<head>
    <title><fmt:message key="userPaciente.title"/></title>

    <link rel="stylesheet" href="/resources/demos/style.css">
    <link rel="stylesheet" href="/styles/style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">

    <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js" type="text/javascript"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/localization/messages_es.js" type="text/javascript"></script>
    <script src="http://jquery.bassistance.de/validate/additional-methods.js" type="text/javascript"></script>
    <script src="/scripts/validator.js" type="text/javascript"></script>
    <script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>

    <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.4/moment.min.js"></script>

    <link rel="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/3.1.3/css/bootstrap-datetimepicker.min.css"/>
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/3.1.3/js/bootstrap-datetimepicker.min.js"></script>

</head>

<div class="container">
<meta name="menu" content="UserMenu"/>

<c:set var="delObject" scope="request"><fmt:message key="userList.user"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="signup.title"/></h2>
    <c:choose>
        <c:when test="${param.from == 'list'}">
            <p><fmt:message key="userProfile.admin.message"/></p>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="userProfile.message"/></p>
        </c:otherwise>
    </c:choose>
</div>

<div class="col-sm-7">
    <spring:bind path="pacienteForm.*">
        <c:if test="${not empty status.errorMessages}">
            <div class="alert alert-danger alert-dismissable">
                <a href="#" data-dismiss="alert" class="close">&times;</a>
                <c:forEach var="error" items="${status.errorMessages}">
                    <c:out value="${error}" escapeXml="false"/><br/>
                </c:forEach>
            </div>
        </c:if>
    </spring:bind>

    <form:form commandName="pacienteForm" method="post" action="registrar" autocomplete="off" id="formulario" modelAttribute="pacienteForm"
               cssClass="well" onsubmit="return validateUser(this)">
        	<form:hidden path="username"/>

        	  <div class="row">
		        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                  <spring:bind path="fechaHora">
                    <appfuse:label styleClass="control-label" key="user.paciente.fechaHora"/>
                	<span class="required">*</span>
                	</br>
                	<div>
	                	<input type="text" name="fechaHora" id="fechaHora" class="form-control"
 	                	placeholder="<fmt:message key="user.paciente.fechaHora"/>" value="${status.value}" maxlength="50"
	                    tabindex="1" data-date-format="DD/MM/YYYY hh:mm A">
                    </div>
                  </spring:bind>
                  <label for="fechaHora" generated="true" class="error"></label>
                  <form:errors path="fechaHora" cssClass="help-block"/>
                  <script type="text/javascript">
            		$(function () {
                	$('#fechaHora').datetimepicker({
                	    language: 'pt-BR',
                	    showToday: true,
                	});
            		});
        	      </script>
                </div>
		      </div>
		        <div class="form-group">
		                <appfuse:label styleClass="control-label" key="user.paciente.medicion"/>
		                <form:input cssClass="form-control" path="medicion" id="medicion"/>
		                <label for="medicion" generated="true" class="error"></label>
                        <form:errors path="medicion" cssClass="help-block"/>
		        </div>
		        
		        <div class="form-group">
		                <appfuse:label styleClass="control-label" key="user.paciente.peso"/>
		                <form:input cssClass="form-control" path="peso" id="peso"/>
		        </div>
		        <div class="form-group">
		        	<appfuse:label styleClass="control-label" key="user.medicine.title"/>
		        	<form:select cssClass="form-control" path="medicamento">
		        	    <form:option value="NONE" label="--- Seleccione ---"/>
		        		<form:options items="${medicamentoList}" itemValue="nombreComercial" itemLabel="nombreComercial"></form:options>
		        	</form:select>
		        	<appfuse:label styleClass="control-label" key="user.observation.title"></appfuse:label>
		        	<form:textarea cssClass="form-control" path="observacionesMedicamento"/>
		        </div> 
		        
		        <div class="form-group">
		        	<appfuse:label styleClass="control-label" key="user.symptom.title"/>
		        	<form:select cssClass="form-control" path="sintoma">
		        	    <form:option value="NONE" label="--- Seleccione ---"/>
		        		<form:options items="${sintomaList}" itemValue="nombre" itemLabel="nombre"></form:options>
		        	</form:select>
		        	<appfuse:label styleClass="control-label" key="user.observation.title"></appfuse:label>
		        	<form:textarea cssClass="form-control" path="observacionesSintoma"/>
		        </div> 
		        
		        <div class="form-group">
		        	<appfuse:label styleClass="control-label" key="active.momentodia"/>
		        	<form:select cssClass="form-control" path="momento">
		        		<form:option value="DESAYUNO" label="DESAYUNO"/>
		        		<form:option value="MEDIA_MANIANA" selected="selected">MEDIA MA&Ntilde;ANA</form:option>
		        		<form:option value="ALMUERZO" label="ALMUERZO"/>
		        		<form:option value="MEDIA_TARDE" label="MEDIA TARDE"/>
		        		<form:option value="CENA" label="CENA"/>
		        		<form:option value="ANTES_DE_ACOSTARSE" label="ANTES DE ACOSTARSE"/>
		        	</form:select>
		        </div>
		        
		        <display:table name="<%= a %>" cellspacing="0" cellpadding="0" requestURI=""
                   defaultsort="1" id="users" pagesize="25" class="table table-condensed table-striped table-hover" export="false">
				        
				        <display:column titleKey="user.paciente.alimentoIng" sortable="false">
				            <form:select path="alimento1" cssClass="form-control">
				                <form:option value="NONE" label="--- Seleccione ---"/>
				            	<form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
				            </form:select>
				            <form:select path="alimento2" cssClass="form-control">
				                <form:option value="NONE" label="--- Seleccione ---"/>
				            	<form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
				            </form:select>
				            <form:select path="alimento3" cssClass="form-control">
				                <form:option value="NONE" label="--- Seleccione ---"/>
				            	<form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
				            </form:select>
				            <form:select path="alimento4" cssClass="form-control">
				                <form:option value="NONE" label="--- Seleccione ---"/>
				            	<form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
				            </form:select>
				            <form:select path="alimento5" cssClass="form-control">
				                <form:option value="NONE" label="--- Seleccione ---"/>
				            	<form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
				            </form:select>
				        </display:column>
				        
				        <display:column titleKey="user.food.cant" sortable="false">
				            <form:input path="cantidad1" cssClass="form-control"/>
				            <form:input path="cantidad2" cssClass="form-control"/>
				            <form:input path="cantidad3" cssClass="form-control"/>
				            <form:input path="cantidad4" cssClass="form-control"/>
				            <form:input path="cantidad5" cssClass="form-control"/>
				        </display:column>
				        <display:column titleKey="user.observation.title" sortable="false">
				            <form:input path="observacion1" cssClass="form-control" value="${status.value}"/>
				            <form:input path="observacion2" cssClass="form-control" value="${status.value}"/>
				            <form:input path="observacion3" cssClass="form-control" value="${status.value}"/>
				            <form:input path="observacion4" cssClass="form-control" value="${status.value}"/>
				            <form:input path="observacion5" cssClass="form-control" value="${status.value}"/>
				        </display:column>
				       
								        
				    </display:table>    
						        
		        <%--
		         <table>
		        	<tr>
		        		<th>Alimento</th>
		        		<th>Cantidad</th>
		        		<th>Observaciones</th>
		        	</tr>
		        	<tr>
		        		<td><form:input path=""/></td>
		        		<td><form:input path=""/></td>
		        		<td><form:textarea path=""/></td>
		        	</tr>
		        	<tr>
		        		<td><form:input path=""/></td>
		        		<td><form:input path=""/></td>
						<td><form:textarea path=""/></td>
		        		
		        	</tr>
		        	<tr>
		        		<td><form:input path=""/></td>
		        		<td><form:input path=""/></td>
		        		<td><form:textarea path=""/></td>
		        	</tr>
		        	<tr>
		        		<td><form:input path=""/></td>
		        		<td><form:input path=""/></td>
		        		<td><form:textarea path=""/></td>
		        	</tr>
		        	<tr>
		        		<td><form:input path=""/></td>
		        		<td><form:input path=""/></td>
		        		<td><form:textarea path=""/></td>
		        	</tr>
		        </table>	    --%>     
		
		        <div class="form-group">
		            <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
		                <i class="icon-ok icon-white"></i> <fmt:message key="button.registrer"/>
		            </button>
		
		            <c:if test="${param.from == 'list' and param.method != 'Add'}">
		              <button type="submit" class="btn btn-default" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
		                  <i class="icon-trash"></i> <fmt:message key="button.delete"/>
		              </button>
		            </c:if>
		
		            <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
		                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
		            </button>
		        </div>
    </form:form>
</div>
</div>

<c:set var="scripts" scope="request">
	<script type="text/javascript">
		// This is here so we can exclude the selectAll call when roles is hidden

		function onFormSubmit(theForm) {
			return validateUser(theForm);
		};
	</script>
	<script type="text/javascript">
		 $('button[name="cancel"]').click(function(e){
				e.preventDefault();
				window.location.href = "http://localhost:8080/paciente/registrar";
			});
	</script>

	<script type="text/javascript">
    		 $('button[name="search"]').click(function(e){
    			  	e.preventDefault();
    				//var dni = document.getElementById("dni").value; Con cualquiera de las 2 formas anda!!!
    				var dni = $('input[name=dni]').val();
    				window.location.href = "http://localhost:8080/admin/newPaciente?search=search&dni="+dni;
    			});
    	</script>
</c:set>

<v:javascript formName="pacienteForm" staticJavascript="false" />
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>