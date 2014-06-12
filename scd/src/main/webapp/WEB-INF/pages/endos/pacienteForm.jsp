<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userProfile.title"/></title>
    <meta name="menu" content="UserMenu"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="userList.user"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="user.paciente.nuevo"/></h2>
    <c:choose>
        <c:when test="${param.from == 'list'}">
            <p><fmt:message key="userProfile.admin.message"/></p>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="userProfile.message.cliente"/></p>
        </c:otherwise>
    </c:choose>
</div>
<!-- Acá comienzan los formularios -->

<div class="col-sm-7">
            <spring:bind path="paciente.*">
                <c:if test="${not empty status.errorMessages}">
                    <div class="alert alert-danger alert-dismissable">
                        <a href="#" data-dismiss="alert" class="close">&times;</a>
                        <c:forEach var="error" items="${status.errorMessages}">
                            <c:out value="${error}" escapeXml="false"/><br/>
                        </c:forEach>
                    </div>
                </c:if>
            </spring:bind>

    <form:form commandName="paciente" method="post" action="pacienteForm" id="pacienteForm" autocomplete="off"
               cssClass="well" onsubmit="return validateUser(this)">
                <form:hidden path="id"/>
                
                <input type="hidden" name="from" value="<c:out value="${param.from}"/>"/>
                
                                                
        
                            <div class="row">
                                    <spring:bind path="paciente.nombre">
                                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    </spring:bind>
                                            <appfuse:label styleClass="control-label" key="user.firstName"/>
                                            <form:input cssClass="form-control" path="nombre" id="nombre" maxlength="50" autofocus="true"/>
                                            <form:errors path="nombre" cssClass="help-block"/>
                                        </div>
                                    <spring:bind path="paciente.apellido">
                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    </spring:bind>
                                        <appfuse:label styleClass="control-label" key="user.lastName"/>
                                        <form:input cssClass="form-control" path="apellido" id="apellido" maxlength="50"/>
                                        <form:errors path="apellido" cssClass="help-block"/>
                                    </div>
                            </div>


                             <div class="row">
                                <spring:bind path="paciente.telefono">
                                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    </spring:bind>
                                        <appfuse:label styleClass="control-label" key="user.phoneNumber"/>
                                        <form:input cssClass="form-control" path="telefono" id="telefono"/>
                                        </div>
                                    <spring:bind path="paciente.email">
                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    </spring:bind>
                                        <appfuse:label styleClass="control-label" key="user.email"/>
                                        <form:input cssClass="form-control" path="email" id="email"/>
                                        <form:errors path="email" cssClass="help-block"/>
                                    </div>
                            </div>
                                                            
                            <div class="row">
                                <div class="col-sm-6 form-group">
                                    <appfuse:label styleClass="control-label" key="user.dni"/>
                                    <form:input cssClass="form-control" path="dni" id="dni"/>
                                </div>
                                <spring:bind path="paciente.sexo">
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                </spring:bind>
                                    <appfuse:label styleClass="control-label" key="user.sexo"/>
                                    <div cssClass="form-control">
                                        <form:radiobutton  path="sexo" value="F" label="Femenino"/>&nbsp;
                                        <form:radiobutton path="sexo" value="M" label="Masculino" />
                                        <form:errors path="sexo" cssClass="help-block"/>
                                    </div>
                            </div>
                        </div>
                        
			        <c:if test="${empty paciente.id}">
			        
				        <div class="form-group">
		                    <appfuse:label styleClass="control-label" key="user.endocrinologist.title"/>
		                    <form:input cssClass="form-control" path="endocrinologo" id="endocrinologo" type="hidden"/>
	                	</div>
	                	<div class="form-group">
		                    <appfuse:label styleClass="control-label" key="user.tipo"/>
		                    <form:input cssClass="form-control" path="tipo" id="tipo"/>
	                	</div>
	                	
	                	<%-- <div class="form-group">
		                    <appfuse:label styleClass="control-label" key="user.paciente.medicion"/>
		                    <form:input cssClass="form-control" path="mediciones" id="medicion"/>
	                	</div> --%>
	                	<div class="form-group">
		                    <appfuse:label styleClass="control-label" key="active.peso"/>
		                    <form:input cssClass="form-control" path="pesos" id="peso"/>
	                	</div>
	                	<%-- <div class="form-group">
	                		<appfuse:label styleClass="control-label" key="user.paciente.diet"/>
	                		<form:input cssClass="form-control" path="dietas" id="dietas"/>
	                	</div>
	                	<div class="form-group">
	                		<appfuse:label styleClass="control-label" key="pacienteList.registroComida"/>
	                		<form:input cssClass="form-control" path="registroComidas" id="registroComidas"/>
	                	</div> --%>
                	</c:if>
			        
			        
			        
			        
			        
			        
			        <div>
				            <legend class="accordion-heading">
				                <a data-toggle="collapse" href="#collapse-address"><fmt:message key="user.address.address1"/></a>
				            </legend>
				            <div id="collapse-address" class="accordion-body collapse">
				            	
				            	<spring:bind path="paciente.domicilio.localidad.provincia.nombre">
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                </spring:bind>
                                    <appfuse:label styleClass="control-label" key="user.address.province"/>
                                    <div cssClass="form-control">
                                        <form:input cssClass="form-control" path="domicilio.localidad.provincia.nombre" id="localidad"/>
                                        <form:errors path="domicilio.localidad.provincia.nombre" cssClass="help-block"/>
                                	</div>
                                </div>
				            	
				            	<spring:bind path="paciente.domicilio.localidad.nombre">
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                </spring:bind>
                                    <appfuse:label styleClass="control-label" key="user.address.localidad"/>
                                    <div cssClass="form-control">
                                        <form:input cssClass="form-control" path="domicilio.localidad.nombre" id="provincia"/>
                                        <form:errors path="domicilio.localidad.nombre" cssClass="help-block"/>
                                	</div>
                                </div>
                                
				                <div class="row">
				                
					                <spring:bind path="paciente.domicilio.calle">
	                                	<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
	                                </spring:bind>
	                                    <appfuse:label styleClass="control-label" key="user.address.address"/>
	                                    <div cssClass="form-control">
	                                        <form:input cssClass="form-control" path="domicilio.calle" id="calle"/>
	                                        <form:errors path="domicilio.calle" cssClass="help-block"/>
	                                	</div>
	                                </div>
				                	
					                <spring:bind path="paciente.domicilio.numero">
	                                	<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
	                                </spring:bind>
	                                    <appfuse:label styleClass="control-label" key="user.address.numero"/>
	                                    <div cssClass="form-control">
	                                        <form:input cssClass="form-control" path="domicilio.numero" id="numero"/>
	                                        <form:errors path="domicilio.numero" cssClass="help-block"/>
	                                	</div>
	                                </div>	
	
					            </div>
					            
				                <div class="row">
			                    	<spring:bind path="paciente.domicilio.dpto">
	                                	<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
	                                </spring:bind>
	                                    <appfuse:label styleClass="control-label" key="user.address.dpto"/>
	                                    <div cssClass="form-control">
	                                        <form:input cssClass="form-control" path="domicilio.dpto" id="dpto"/>
	                                        <form:errors path="domicilio.dpto" cssClass="help-block"/>
	                                	</div>
	                                </div>
				                    
			                    	<spring:bind path="paciente.domicilio.piso">
	                                	<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
	                                </spring:bind>
	                                    <appfuse:label styleClass="control-label" key="user.address.piso"/>
	                                    <div cssClass="form-control">
	                                        <form:input cssClass="form-control" path="domicilio.piso" id="piso"/>
	                                        <form:errors path="domicilio.piso" cssClass="help-block"/>
	                                	</div>
	                                </div>
				                </div>
				            </div>
				     </div>
                	
             <!-- Probamos con usuario y contraseña -->
		               <%--   <spring:bind path="user.username">
                    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                    </spring:bind>
                    <appfuse:label styleClass="control-label" key="user.username"/>
                    <form:input cssClass="form-control" path="" id="username"/>
                    <form:errors path="username" cssClass="help-block"/>
                    
         
                    <spring:bind path="user.passwordHint">
                    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        </spring:bind>
                        <appfuse:label styleClass="control-label" key="label.password"/>
                            <form:input cssClass="form-control" path="" id="passwordHint"/>
                            <form:errors path="passwordHint" cssClass="help-block"/>
 					--%>
                            <br></br>



        <div class="form-group">
            <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            <c:if test="${not empty paciente.id}">
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


<c:set var="scripts" scope="request">
<script type="text/javascript">
// This is here so we can exclude the selectAll call when roles is hidden
function onFormSubmit(theForm) {
    return validateUser(theForm);
}
</script>
</c:set>

<%-- <v:javascript formName="user" staticJavascript="false"/> --%>
<v:javascript formName="paciente" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>





