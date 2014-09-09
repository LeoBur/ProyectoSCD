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
	<script type="text/javascript">
		function CancelFormButton(button) {
			onsubmit: false;
	  	window.location.href = "http://localhost:8080/endos/endo";
	  	};
	</script>
	<script src="/scripts/jquery.1.10.2.min.js" type="text/javascript"></script>
    <script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>
    <script type="text/javascript">
    	//javascript para el autocomplete de provincia
    	$(document).ready(function() {
    		$('#provincia').autocomplete({
    			serviceUrl : '/getTags',
    			paramName : "tagName",
    			delimiter : ",",
    			transformResult : function(response) {
    				return {
    					suggestions : $.map($.parseJSON(response), function(item) {
    						return {
    							value : item.tagName,
    							data : item.id
    						};
    					})
    				};
    			}
    		});
    	});
    </script>
    <script type="text/javascript">
        	//javascript para el autocomplete de localidad
        	$(document).ready(function() {
        		$('#localidad').autocomplete({
        			serviceUrl : '/getTags',
        			paramName : "tagName",
        			delimiter : ",",
        			transformResult : function(response) {
        				return {
        					suggestions : $.map($.parseJSON(response), function(item) {
        						return {
        							value : item.tagName,
        							data : item.id
        						};
        					})
        				};
        			}
        		});
        	});
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
  			<input type="hidden"/>
  		</spring:bind>
  		<spring:bind path="username">
  			<input type="hidden"/>                                             
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
					<button type="submit" name="search" class="btn btn-primary" formaction="buscar" formmethod="get"
						formnovalidate="formnovalidate" onclick="bCancel=false" tabindex="2">
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
	        	<input type="text" name="dia" id="dia" class="form-control"
	           	placeholder="<fmt:message key="user.dia"/>" value="${status.value}" maxlength="50"
	           	 tabindex="4">
	          </spring:bind>
	          		<label for="dia" generated="true" class="error"></label>
		   	  <form:errors path="dia" cssClass="help-block"/>
		   	</div>
            <div class="col-sm-2">
	          <spring:bind path="endocrinologoForm.mes">
	            <appfuse:label styleClass="control-label" key="user.mes"/>
	        	<input type="text" name="mes" id="mes" class="form-control"
	           	placeholder="<fmt:message key="user.mes"/>" value="${status.value}" maxlength="50"
	           	 tabindex="5">
	          </spring:bind>
	          		<label for="mes" generated="true" class="error"></label>
		   	  <form:errors path="mes" cssClass="help-block"/>
		   	</div>
            <div class="col-sm-2">
	          <spring:bind path="endocrinologoForm.anio">
	            <appfuse:label styleClass="control-label" key="user.anio"/>
	        	<input type="text" name="anio" id="anio" class="form-control"
	           	placeholder="<fmt:message key="user.anio"/>" value="${status.value}" maxlength="50"
	           	 tabindex="6">
	          </spring:bind>
	          		<label for="anio" generated="true" class="error"></label>
		   	  <form:errors path="anio" cssClass="help-block"/>
		   	</div>
		</div>

		<%-- Este estaba primero pero ahora queda el que se encuentra arriba
		<div class="row">
			<spring:bind path="endocrinologoForm.sexo">
				<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					<appfuse:label styleClass="control-label" key="user.sexo" /><br>
					<div class="form-control">
					<input type="radio" name="sex" value="F" >Femenino
					<input type="radio" name="sex" value="M" >Masculino
					</div>
					<form:errors path="sexo" cssClass="help-block" />
					
					<div cssClass="form-control">
					  <form:radiobutton path="sexo" value="F" label="Femenino"/>
						&nbsp;
					  <form:radiobutton path="sexo" value="M" label= />
					  <form:errors path="sexo" cssClass="help-block" />
					</div>
				</div>
			</spring:bind>
		</div> --%>
		
		<div class="row">
		<div>
		    <spring:bind path="endocrinologoForm.sexo">
				<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					<appfuse:label styleClass="control-label" key="user.sexo" />
						<div class="form-control">
						<input type="radio" name="sexo" value="F" value="${status.value}" tabindex="6"/>  Femenino &nbsp; &nbsp; &nbsp;
						<input type="radio" name="sexo" value="M" value="${status.value}" tabindex="7"/>  Masculino
						</div>
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
		  		placeholder="<fmt:message key="user.phoneNumber"/>" value="${status.value}" tabindex="8"/>
		  	</div>
		  </spring:bind>
		 </div>
		 <div>
		  <spring:bind path="endocrinologoForm.email">
			<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
				<appfuse:label styleClass="control-label" key="user.email" />
				<input type="email" id="email" name="email" class="form-control"
				placeholder="<fmt:message key="user.emailExample"/>" value="${status.value}" tabindex="9"/>
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
							<input type="text" id="provincia" name="provincia" class="form-control"
							placeholder="<fmt:message key="user.address.province"/>" value="${status.value}" tabindex="10"/>
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
								<input id="localidad" name="localidad" class="form-control"
								placeholder="<fmt:message key="user.address.localidad"/>" value="${status.value}" tabindex="11"/>
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
							placeholder="<fmt:message key="user.address.address"/>" value="${status.value}" tabindex="12"/>
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
							placeholder="<fmt:message key="user.address.numero"/>" value="${status.value}" tabindex="13"/>
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
							placeholder="<fmt:message key="user.address.dpto"/>" value="${status.value}" tabindex="14"/>
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
							placeholder="<fmt:message key="user.address.piso"/>" value="${status.value}" tabindex="15"/>
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
		  		placeholder="<fmt:message key="user.password"/>" value="${status.value}" tabindex="16"/>
		  	</div>
		  </spring:bind>
		 </div>
		 <div>
		  <spring:bind path="endocrinologoForm.confirmPassword">
			<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
				<appfuse:label styleClass="control-label" key="user.confirmPassword" />
				<input type="password" id="confirmPassword" name="confirmPassword" class="form-control"
				placeholder="<fmt:message key="user.confirmPassword"/>" value="${status.value}" tabindex="17"/>
				<form:errors path="confirmPassword" cssClass="help-block" />
			</div>
		  </spring:bind>
		 </div>

		 <div>
           <spring:bind path="endocrinologoForm.matricula">
         	<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
         	  <appfuse:label styleClass="control-label" key="user.endocrinologist.registration" />
         	  <input type="text" id="matricula" name="matricula" class="form-control"
              value="${status.value}" tabindex="18"/>
         	</div>
           </spring:bind>
         </div>
		</div>
		
       	<div class="form-group">
			<button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false" tabindex="19">
				<i class="icon-ok icon-white"></i>
				<fmt:message key="button.save" />
			</button>
			<c:if test="${not empty endocrinologoForm.dni}">
				<button type="submit" class="btn btn-default" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)" tabindex="20">
					<i class="icon-trash"></i>
					<fmt:message key="button.delete" />
				</button>
			</c:if>
			<button type="submit" class="btn btn-default" name="cancel" onclick="CancelFormButton(this);" tabindex="21">
				<i class="icon-remove"></i>
				<fmt:message key="button.cancel" />
			</button>
		</div>
	</form:form>
</div>

<c:set var="scripts" scope="request">
	<script type="text/javascript">
		// This is here so we can exclude the selectAll call when roles is hidden
		$('button[name="search"]').click(function(e){
			e.preventDefault();
			var dni = $('#dni').val();
			//console.log($.get('http://localhost:8080/admin/buscar', { dni: dni}));
			console.log($.get('http://localhost:8080/admin/buscar', dni));
			$.get('http://localhost:8080/admin/buscar', {dni: dni});
			//window.location.href = "http://localhost:8080/admin/buscar";
			console.log(e);
		});
		function onFormSubmit(theForm) {
			return validateUser(theForm);
		}
	</script>
</c:set>

<v:javascript formName="endocrinologoForm" staticJavascript="false" />
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>