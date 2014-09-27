<%@ include file="/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="userProfile.title"/></title>
    <meta name="menu" content="UserMenu"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <link rel="stylesheet" href="/styles/style.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
  	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js" type="text/javascript"></script>
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/localization/messages_es.js" type="text/javascript"></script>
	<script src="http://jquery.bassistance.de/validate/additional-methods.js" type="text/javascript"></script>
	<script src="/scripts/validator.js" type="text/javascript"></script>
    <script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		function CancelFormButton(button) {
			onsubmit: false;
	  	window.location.href = "http://localhost:8080/endos/endo";
	  	};
	</script>

	<script type="text/javascript" charset="utf-8">
    $(function(){
      $("select#provincia").change(function(){
        $.getJSON("/getLocalidades?provincia="+$(this).val(), function(j){
          var options = '';
          for (var i = 0; i < j.length; i++) {
            options += '<option value="' + j[i].optionValue + '">' + j[i].optionDisplay + '</option>';
          }
          $("select#localidad").html(options);
        })
      })
    })
    </script>
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
			<p><fmt:message key="userProfile.message.endocrinologo"/></p>
		</c:otherwise>
	</c:choose>
</div>
<!-- Ac� comienzan los formularios -->

<div class="col-sm-7">
	<spring:bind path="endocrinologoForm.*">
  		<c:if test="${not empty status.errorMessages}">
      		<div class="alert alert-danger alert-dismissable">
          	<a href="#" data-dismiss="alert" class="close">&times;</a>
          	<c:forEach var="error" items="${status.errorMessages}">
            	<c:out value="${error}" escapeXml="false"/><br/>
          	</c:forEach>
      		</div>
  		</c:if>
	</spring:bind>

	<form:form commandName="endocrinologoForm" method="post" action="newEndocrinologo" id="formulario" autocomplete="off"
           cssClass="well" onsubmit="return validateUser(this)">
  		<spring:bind path="id">
  			<input type="hidden" name="id" id="id" class="form-control" value="${status.value}"/>
  		</spring:bind>
  		<spring:bind path="username">
  			<input type="hidden" id="username" class="form-control" value="${status.value}"/>
  		</spring:bind>
			
			<div class="form-group">
		  		<div class="row">
					  <div>
					  	<spring:bind path="dni">
						  	<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					        	<appfuse:label styleClass="control-label" key="user.dni"/>
					        	<input type="text" name="dni" id="dni" class="form-control"
					           	placeholder="<fmt:message key="user.dni"/>" value="${status.value}" autofocus="autofocus" tabindex="1">
					        </div>
					    </spring:bind>
					    	
					  </div>
					  
					  <div class="col-sm-6 form-group">
					  <br>
					  <div>
						<button type="submit" name="search" class="btn btn-primary" formmethod="get"
							formnovalidate="formnovalidate" onclick="bCancel=false" tabindex="2" value="search">
							<i class="icon-upload icon-white"></i>
							<fmt:message key="button.search" />
						</button>
						</div>
					</div>
					  
				</div>			
			
			</div>
        
        <div class="form-group">
	        <div>
	          <spring:bind path="endocrinologoForm.firstName">
	            <appfuse:label styleClass="control-label" key="user.firstName"/>
	        	<input type="text" name="firstName" id="firstName" class="form-control"
	           	placeholder="<fmt:message key="user.firstName"/>" value="${status.value}" maxlength="50"
	           	 tabindex="3">
	          </spring:bind>
	          		<label for="firstName" generated="true" class="error"></label>
	          <form:errors path="firstName" cssClass="help-block"/>
	        </div>
	        <div>
	          <spring:bind path="endocrinologoForm.lastName">
	            <appfuse:label styleClass="control-label" key="user.lastName"/>
	        	<input type="text" name="lastName" id="lastName" class="form-control"
	           	placeholder="<fmt:message key="user.lastName"/>" value="${status.value}" maxlength="50"
	           	 tabindex="4">
	          </spring:bind>
	          		<label for="lastName" generated="true" class="error"></label>
		   	  <form:errors path="lastName" cssClass="help-block"/>
		   	</div>
        </div>
        
		<div class="form_group">
            <div class="col-sm-2">
	          <spring:bind path="endocrinologoForm.dia">
	            <appfuse:label styleClass="control-label" key="user.dia"/>
	        	<input type="number" name="dia" id="dia" class="form-control"
	           	placeholder="<fmt:message key="user.dia"/>" value="${status.value}" maxlength="50"
	           	 tabindex="5">
	          </spring:bind>
	          		<label for="dia" generated="true" class="error"></label>
		   	  <form:errors path="dia" cssClass="help-block"/>
		   	</div>
            <div class="col-sm-2">
	          <spring:bind path="endocrinologoForm.mes">
	            <appfuse:label styleClass="control-label" key="user.mes"/>
	        	<input type="number" name="mes" id="mes" class="form-control"
	           	placeholder="<fmt:message key="user.mes"/>" value="${status.value}" maxlength="50"
	           	 tabindex="6">
	          </spring:bind>
	          		<label for="mes" generated="true" class="error"></label>
		   	  <form:errors path="mes" cssClass="help-block"/>
		   	</div>
            <div class="col-sm-2">
	          <spring:bind path="endocrinologoForm.anio">
	            <appfuse:label styleClass="control-label" key="user.anio"/>
	        	<input type="number" name="anio" id="anio" class="form-control"
	           	placeholder="<fmt:message key="user.anio"/>" value="${status.value}" maxlength="50"
	           	 tabindex="7">
	          </spring:bind>
	          		<label for="anio" generated="true" class="error"></label>
		   	  <form:errors path="anio" cssClass="help-block"/>
		   	</div>
		</div>
		
		<div class="row">
		<div>
		    <spring:bind path="endocrinologoForm.sexo">
				<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					<appfuse:label styleClass="control-label" key="user.sexo" />
						<div class="form-control">
						<input type="radio" name="sexo" value="F" value="${status.value}" tabindex="8"/>  Femenino &nbsp; &nbsp; &nbsp;
						<input type="radio" name="sexo" value="M" value="${status.value}"/>  Masculino
						</div>
						<label for="sexo" generated="true" class="error"></label>
					<form:errors path="sexo" cssClass="help-block" />
				</div>
			</spring:bind>
		  </div>
		</div>
		
		<div class="row">
		 <div>
		  <spring:bind path="endocrinologoForm.phoneNumber">
			<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
		  		<appfuse:label styleClass="control-label" key="user.phoneNumber" />
		  		<input type="text" name="phoneNumber" id="phoneNumber" class="form-control"
		  		placeholder="<fmt:message key="user.phoneNumber"/>" value="${status.value}" tabindex="10"/>
		  	</div>
		  </spring:bind>
		 </div>
		 <div>
		  <spring:bind path="endocrinologoForm.email">
			<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
				<appfuse:label styleClass="control-label" key="user.email" />
				<input type="email" id="email" name="email" class="form-control"
				placeholder="<fmt:message key="user.emailExample"/>" value="${status.value}" tabindex="11"/>
				<label for="email" generated="true" class="error"></label>
				<form:errors path="email" cssClass="help-block" />
			</div>
		  </spring:bind>
		  		
		 </div>
		</div>
		
			<a><fmt:message key="user.address.address1" /></a>
				<div class="row">
				  <div>
					<spring:bind path="endocrinologoForm.provincia">
						<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						  <appfuse:label styleClass="control-label" key="user.address.province" />
						  <div cssClass="form-control">
							<form:select id="provincia" name="provincia" class="form-control" path="provincia" value="${status.value}" tabindex="12">
							  <form:options items="${provinciaList}"/>
							</form:select>
							<form:errors path="provincia" cssClass="help-block" />
						  </div>
						</div>
					</spring:bind>
				  </div>
				  <div>
					<spring:bind path="endocrinologoForm.localidad">
						<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
							<appfuse:label styleClass="control-label" key="user.address.localidad" />
							<div cssClass="form-control">
								<form:select id="localidad" name="localidad" class="form-control"
								 path="localidad" tabindex="13" value="${status.value}">
								 <form:options items="${localidadList}"/>
								</form:select>
								<form:errors path="localidad" cssClass="help-block" />
							</div>
						</div>
					</spring:bind>
				  </div>
			</div>

			<div class="row">
			  <div>
				<spring:bind path="endocrinologoForm.calle">
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.address.address" />
						<div cssClass="form-control">
							<input type="text" id="calle" name="calle" class="form-control"
							placeholder="<fmt:message key="user.address.address"/>" value="${status.value}" tabindex="14"/>
							<label for="calle" generated="true" class="error"></label>
							<form:errors path="calle" cssClass="help-block" />
						</div>
					</div>
				</spring:bind>
			  </div>
			  <div>
				<spring:bind path="endocrinologoForm.numero">
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.address.numero" />
						<div cssClass="form-control">
							<input id="numero" name="numero" class="form-control"
							placeholder="<fmt:message key="user.address.numero"/>" value="${status.value}" tabindex="15"/>
							<label for="numero" generated="true" class="error"></label>
							<form:errors path="numero" cssClass="help-block" />
						</div>
					</div>
				</spring:bind>
			  </div>
			</div>

			<div class="row">
			  <div>
				<spring:bind path="endocrinologoForm.dpto">
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.address.dpto" />
						<div cssClass="form-control">
							<input id="dpto" name="dpto" class="form-control"
							placeholder="<fmt:message key="user.address.dpto"/>" value="${status.value}" tabindex="16"/>
							<form:errors path="dpto" cssClass="help-block" />
						</div>
					</div>	
				</spring:bind>
			 </div>
			 <div>
				<spring:bind path="endocrinologoForm.piso">
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.address.piso" />
						<div cssClass="form-control">
							<input id="piso" name="piso" class="form-control"
							placeholder="<fmt:message key="user.address.piso"/>" value="${status.value}" autocomplete="off" tabindex="17"/>
							<form:errors path="piso" cssClass="help-block" />
						</div>
					</div>
				</spring:bind>
			 </div>
			</div>



		<div class="row">
		 <div>
		  <spring:bind path="endocrinologoForm.password">
			<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
		  		<appfuse:label styleClass="control-label" key="user.password" />
		  		<input type="password" id="password" name="password" class="form-control"
		  		placeholder="<fmt:message key="user.password"/>" value="${status.value}" autocomplete="off" tabindex="18"/>
		  		<label for="password" generated="true" class="error"></label>
		  	</div>
		  </spring:bind>
		 </div>
		 <div>
		  <spring:bind path="endocrinologoForm.confirmPassword">
			<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
				<appfuse:label styleClass="control-label" key="user.confirmPassword" />
				<input type="password" id="confirmPassword" name="confirmPassword" class="form-control"
				placeholder="<fmt:message key="user.confirmPassword"/>" value="${status.value}" tabindex="19"/>
				<label for="confirmPassword" generated="true" class="error"></label>
				<form:errors path="confirmPassword" cssClass="help-block" />
			</div>
		  </spring:bind>
		 </div>
         </div>
         <div class="row">
		 <div>
           <spring:bind path="endocrinologoForm.matricula">
         	<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
         	  <appfuse:label styleClass="control-label" key="user.endocrinologist.registration" />
         	  <input type="text" id="matricula" name="matricula" class="form-control"
              value="${status.value}" tabindex="20"/>
              <label for="matricula" generated="true" class="error"></label>
         	</div>
           </spring:bind>
         </div>
		</div>
		
       	<div class="form-group">
			<button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false" tabindex="21">
				<i class="icon-ok icon-white"></i>
				<fmt:message key="button.save" />
			</button>
			<c:if test="${not empty endocrinologoForm.dni}">
				<button type="submit" class="btn btn-default" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)" tabindex="22">
					<i class="icon-trash"></i>
					<fmt:message key="button.delete" />
				</button>
			</c:if>
			<button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true" tabindex="23">

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
		};
	</script>
	<script type="text/javascript">
		 $('button[name="cancel"]').click(function(e){
				e.preventDefault();
				window.location.href = "http://localhost:8080/admin/newEndocrinologo";
			});
	</script>
	<script type="text/javascript">
		 $('button[name="search"]').click(function(e){
			  	e.preventDefault();
				//var dni = document.getElementById("dni").value; Con cualquiera de las 2 formas anda!!!
				var dni = $('input[name=dni]').val();
				window.location.href = "http://localhost:8080/admin/newEndocrinologo?search=search&dni="+dni;
			});
	</script>
	
	
</c:set>

<v:javascript formName="endocrinologoForm" staticJavascript="false" />
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>