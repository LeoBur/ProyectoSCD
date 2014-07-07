<%@ include file="/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="userProfile.title"/></title>
    <meta name="menu" content="UserMenu"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  	<link rel="stylesheet" href="/resources/demos/style.css">
</head>

<c:set var="delObject" scope="request"><fmt:message key="userList.user"/></c:set>
<script type="text/javascript">var msgDelConfirm =
		"<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="user.endocrinologist.new"/></h2>
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
	<spring:bind path="endocrinologo.*">
  		<c:if test="${not empty status.errorMessages}">
      		<div class="alert alert-danger alert-dismissable">
          	<a href="#" data-dismiss="alert" class="close">&times;</a>
          	<c:forEach var="error" items="${status.errorMessages}">
            	<c:out value="${error}" escapeXml="false"/><br/>
          	</c:forEach>
      		</div>
  		</c:if>
	</spring:bind>

	<form:form commandName="endocrinologo" method="post" action="endocrinologoForm" id="endocrinologoForm" autocomplete="off"
           cssClass="well" onsubmit="return validateUser(this)">
  		<spring:bind path="id">
  			<input type="hidden"/>
  		</spring:bind>
  		<spring:bind path="username">
  			<input type="hidden"/>                                             
  		</spring:bind>
  
  		<div class="col-sm-6 form-group">
  	 	  <spring:bind path="dni">
        	<appfuse:label styleClass="control-label" key="user.dni"/>
        	<input type="number" name="dni" id="dni" class="form-control"
           	placeholder="<fmt:message key="user.dni"/>" value="${status.value}" tabindex="1">
          </spring:bind>
		</div>
		
        <div class="row">
          <spring:bind path="endocrinologo.nombre">
            <appfuse:label styleClass="control-label" key="user.firstName"/>
        	<input type="text" name="nombre" id="nombre" class="form-control"
           	placeholder="<fmt:message key="user.firstName"/>" value="${status.value}" maxlength="50"
           	autofocus="autofocus" tabindex="2">
          </spring:bind>
          <form:errors path="nombre" cssClass="help-block"/>
        
          <spring:bind path="endocrinologo.apellido">
            <appfuse:label styleClass="control-label" key="user.lastName"/>
        	<input type="text" name="apellido" id="apellido" class="form-control"
           	placeholder="<fmt:message key="user.lastName"/>" value="${status.value}" maxlength="50"
           	autofocus="autofocus" tabindex="3">
          </spring:bind>
	   	  <form:errors path="apellido" cssClass="help-block"/>
        </div>
        
		<div class="form-group">
			<appfuse:label styleClass="control-label" key="user.fecha.nacimiento" />
			<spring:bind path="endocrinologo.fch_nac">
<%-- 				<div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}"> --%>
			
<%-- 					<fmt:formatDate value="${fecha}" pattern="dd-MM-yyyy" var="fecha" /> --%>
					<input type="date" name="fch_nac" id="fch_nac" class="form-control"
					placeholder="<fmt:message key="date.format"/>" value="${status.value}" tabindex="4" size="12" />
			</spring:bind>
		</div>

		<div class="row">
			<spring:bind path="endocrinologo.sexo">
				<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					<appfuse:label styleClass="control-label" key="user.sexo" />
					<input type="radio" name="sex" value="F" class="form-control">Femenino<br>
					<input type="radio" name="sex" value="M" class="form-control">Masculino
					<form:errors path="sexo" cssClass="help-block" />
					
					<%-- <div cssClass="form-control">
					  <form:radiobutton path="sexo" value="F" label="Femenino" />
						&nbsp;
					  <form:radiobutton path="sexo" value="M" label= />
					  <form:errors path="sexo" cssClass="help-block" />
					</div> --%>
				</div>
			</spring:bind>
		</div>
		
		<div class="row">
		  <spring:bind path="endocrinologo.telefono">
			<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
		  		<appfuse:label styleClass="control-label" key="user.phoneNumber" />
		  		<input type="number" name="telefono" id="telefono" class="form-control"
		  		placeholder="<fmt:message key="user.phoneNumber"/>" value="${status.value}" tabindex="5"/>
		  	</div>
		  </spring:bind>
		  <spring:bind path="endocrinologo.email">
			<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
				<appfuse:label styleClass="control-label" key="user.email" />
				<input type="email" id="email" name="email" class="form-control"
				placeholder="<fmt:message key="user.emailExample"/>" value="${status.value}" tabindex="6"/>
				<form:errors path="email" cssClass="help-block" />
			</div>
		  </spring:bind>
		</div>
		
		<div>
			<legend class="accordion-heading">
				<a data-toggle="collapse" href="#collapse-address"><fmt:message key="user.address.address1" /></a>
			</legend>
			<div id="collapse-address" class="accordion-body collapse">
				<div class="row">
					<spring:bind path="endocrinologo.domicilio.localidad.provincia.nombre">
						<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						  <appfuse:label styleClass="control-label" key="user.address.province" />
						  <div cssClass="form-control">
							<input type="text" id="provincia" name="provincia" class="form-control"
							placeholder="<fmt:message key="user.address.province"/>" value="${status.value}" tabindex="7"/>
							<form:errors path="domicilio.localidad.provincia.nombre" cssClass="help-block" />
						  </div>
						</div>
					</spring:bind>
					<spring:bind path="endocrinologo.domicilio.localidad.nombre">
						<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
							<appfuse:label styleClass="control-label" key="user.address.localidad" />
							<div cssClass="form-control">
								<input id="localidad" name="localidad" class="form-control"
								placeholder="<fmt:message key="user.address.localidad"/>" value="${status.value}" tabindex="8"/>
								<form:errors path="domicilio.localidad.nombre" cssClass="help-block" />
							</div>
						</div>
					</spring:bind>
			</div>

			<div class="row">
				<spring:bind path="endocrinologo.domicilio.calle">
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.address.address" />
						<div cssClass="form-control">
							<input type="text" id="calle" name="calle" class="form-control"
							placeholder="<fmt:message key="user.address.address"/>" value="${status.value}" tabindex="9"/>
							<form:errors path="domicilio.calle" cssClass="help-block" />
						</div>
					</div>
				</spring:bind>
				<spring:bind path="endocrinologo.domicilio.numero">
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.address.numero" />
						<div cssClass="form-control">
							<input id="numero" name="numero" class="form-control"
							placeholder="<fmt:message key="user.address.numero"/>" value="${status.value}" tabindex="10"/>
							<form:errors path="domicilio.numero" cssClass="help-block" />
						</div>
					</div>
				</spring:bind>
			</div>

			<div class="row">
				<spring:bind path="endocrinologo.domicilio.dpto">
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.address.dpto" />
						<div cssClass="form-control">
							<input id="dpto" name="dpto" class="form-control"
							placeholder="<fmt:message key="user.address.dpto"/>" value="${status.value}" tabindex="11"/>
							<form:errors path="domicilio.dpto" cssClass="help-block" />
						</div>
					</div>	
				</spring:bind>
				<spring:bind path="endocrinologo.domicilio.piso">
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.address.piso" />
						<div cssClass="form-control">
							<input id="piso" name="piso" class="form-control"
							placeholder="<fmt:message key="user.address.piso"/>" value="${status.value}" tabindex="12"/>
							<form:errors path="domicilio.piso" cssClass="help-block" />
						</div>
					</div>
				</spring:bind>
			</div>
		</div>
		
		<div class="row">
		  <spring:bind path="endocrinologo.password">
			<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
		  		<appfuse:label styleClass="control-label" key="user.password" />
		  		<input type="text" id="password" name="password" class="form-control"
		  		placeholder="<fmt:message key="user.password"/>" value="${status.value}" tabindex="13"/>
		  	</div>
		  </spring:bind>
		  <spring:bind path="endocrinologo.confirmPassword">
			<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
				<appfuse:label styleClass="control-label" key="user.confirmPassword" />
				<input type="text" id="confirmPassword" name="confirmPassword" class="form-control"
				placeholder="<fmt:message key="user.confirmPassword"/>" value="${status.value}" tabindex="14"/>
				<form:errors path="confirmPassword" cssClass="help-block" />
			</div>
		  </spring:bind>
		</div>
		
		<spring:bind path="endocrinologo.matricula">
			<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
				<appfuse:label styleClass="control-label" key="user.endocrinologist.registration" />
				<input type="number" id="matricula" name="matricula" class="form-control"
				value="${status.value}" tabindex="15"/>
			</div>
		</spring:bind>

       	<div class="form-group">
			<button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
				<i class="icon-ok icon-white"></i>
				<fmt:message key="button.save" />
			</button>
			<c:if test="${not empty paciente.id}">
				<button type="submit" class="btn btn-default" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
					<i class="icon-trash"></i>
					<fmt:message key="button.delete" />
				</button>
			</c:if>
			<button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
				<i class="icon-remove"></i>
				<fmt:message key="button.cancel" />
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

<v:javascript formName="paciente" staticJavascript="false" />
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>