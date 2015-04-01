<%@ include file="/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="userProfile.title"/></title>
    <meta name="menu" content="UserMenu"/>
    <link rel="stylesheet" href="/resources/demos/style.css">
        <link rel="stylesheet" href="/styles/style.css">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="${base}/styles/style.css"/>
        <link rel="stylesheet" type="text/css" media="screen" href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/master/build/css/bootstrap-datetimepicker.min.css">
        <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.1/css/font-awesome.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" media="screen" href="content/pygments-manni.css">

        <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js" type="text/javascript"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/localization/messages_es.js" type="text/javascript"></script>
        <script src="http://jquery.bassistance.de/validate/additional-methods.js" type="text/javascript"></script>
        <script src="/scripts/validator.js" type="text/javascript"></script>
        <script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>

        <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.4/moment.min.js"></script>

        <script type="text/javascript" src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/master/src/js/bootstrap-datetimepicker.js"></script>

	<script type="text/javascript">
		function CancelFormButton(button) {
			onsubmit: false;
	  	window.location.href = "${ctx}/nutricionista/editProfile";
	  	};
	</script>

	<script type="text/javascript" charset="utf-8">
    $(function(){
      $("select#provincia").change(function(){
        $.getJSON("${ctx}/getLocalidades?provincia="+$(this).val(), function(j){
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

<div class="col-md-2">
    <h3>Editar Perfil</h2>
</div>
<!-- Ac� comienzan los formularios -->

<div class="col-md-9">
	<spring:bind path="especialistaForm.*">
  		<c:if test="${not empty status.errorMessages}">
      		<div class="alert alert-danger alert-dismissable">
          	<a href="#" data-dismiss="alert" class="close">&times;</a>
          	<c:forEach var="error" items="${status.errorMessages}">
            	<c:out value="${error}" escapeXml="false"/><br/>
          	</c:forEach>
      		</div>
  		</c:if>
	</spring:bind>

	<form:form commandName="especialistaForm" method="post" action="editProfile" id="formulario" autocomplete="off"
           cssClass="well" onsubmit="return validateUser(this)">
  		<spring:bind path="especialistaForm.id">
  			<input type="hidden" name="id" id="id" class="form-control" value="${status.value}"/>
  		</spring:bind>
  		<spring:bind path="especialistaForm.idEspecialista">
        	<input type="hidden" id="idEspecialista" name="idEspecialista" class="form-control" value="${status.value}"/>
        </spring:bind>
  		<spring:bind path="especialistaForm.username">
  			<input type="hidden" id="username" name="username" class="form-control" value="${status.value}"/>
  		</spring:bind>
  		<spring:bind path="dni">
        	<input type="hidden" name="dniposta" id="dniposta" class="form-control" value="${status.value}"/>
        </spring:bind>
        <spring:bind path="enabled">
        	<input type="hidden" name="enabled" id="enabled" class="form-control" value="${status.value}"/>
        </spring:bind>
        <spring:bind path="tipoEspecialista">
                	<input type="hidden" name="tipoEspecialista" id="tipoEspecialista" class="form-control" value="${status.value}"/>
        </spring:bind>

			<div class="form-group">
		  		<div class="row">
					  <div>
					  	<spring:bind path="dni">
						  	<div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					        	<appfuse:label styleClass="control-label" key="user.dni"/>
					        	<input type="text" name="dni" id="dni" class="form-control" disabled
					           	placeholder="<fmt:message key="user.dni"/>" value="${status.value}" autofocus="autofocus" tabindex="1">
					        </div>
					    </spring:bind>

					  </div>

					  <div class="col-md-6 form-group">
					  <br>
					  <br>
					  <div>
                        <a href="${ctx}/updatePassword"><fmt:message key="user.changePassword"/></a>
                      </div>
					</div>
				</div>

			</div>

        <div class="form-group">
	        <div>
	          <spring:bind path="especialistaForm.firstName">
	            <appfuse:label styleClass="control-label" key="user.firstName"/>
	        	<input type="text" name="firstName" id="firstName" class="form-control"
	           	placeholder="<fmt:message key="user.firstName"/>" value="${status.value}" maxlength="50"
	           	 tabindex="3">
	          </spring:bind>
	          		<label for="firstName" generated="true" class="error"></label>
	          <form:errors path="firstName" cssClass="help-block"/>
	        </div>
	        <div>
	          <spring:bind path="especialistaForm.lastName">
	            <appfuse:label styleClass="control-label" key="user.lastName"/>
	        	<input type="text" name="lastName" id="lastName" class="form-control"
	           	placeholder="<fmt:message key="user.lastName"/>" value="${status.value}" maxlength="50"
	           	 tabindex="4">
	          </spring:bind>
	          		<label for="lastName" generated="true" class="error"></label>
		   	  <form:errors path="lastName" cssClass="help-block"/>
		   	</div>
        </div>

		<div class="row">
            <div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
	          <spring:bind path="especialistaForm.dia">
	            <appfuse:label styleClass="control-label" key="user.fecha.nacimiento"/>
                	            <span class="required">*</span>
                	            <div class='input-group date' id='datetimepicker1'>
                	        	  <input type="text" name="dia" id="dia" class="form-control" readonly="readonly"
                	           	    placeholder="<fmt:message key="user.fecha.nacimiento"/>" value="${status.value}" maxlength="50"
                	           	    tabindex="5" data-date-format="DD/MM/YYYY">
                	           	  <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                  </span>
                	           	</div>
	          </spring:bind>
	          		<label for="dia" generated="true" class="error"></label>
		   	  <form:errors path="dia" cssClass="help-block"/>
		   	  <script type="text/javascript">
                              $(function () {
                                $('#datetimepicker1').datetimepicker({
                                  language: 'pt-BR',
                                  showToday: true,
                                  pickTime: false
                                });
                              });
                            </script>
		   	</div>

		    <spring:bind path="especialistaForm.sexo">
				<div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					<appfuse:label styleClass="control-label" key="user.sexo" />
						<div class="form-control">
                        <c:choose>
                            <c:when test="${especialistaForm.sexo == null || especialistaForm.sexo == 'M'}">
                                <input type="radio" name="sexo" value="M" checked="checked" tabindex="8"/>Masculino  &nbsp; &nbsp; &nbsp;
                                <input type="radio" name="sexo" value="F" tabindex="9"/>Femenino
                            </c:when>
                            <c:when test="${especialistaForm.sexo != null && especialistaForm.sexo == 'F'}">
                                <input type="radio" name="sexo" value="M" tabindex="10"/>Masculino &nbsp; &nbsp; &nbsp;
                                <input type="radio" name="sexo" value="F" checked="checked" tabindex="11"/>Femenino
                            </c:when>
                        </c:choose>
						</div>
						<label for="sexo" generated="true" class="error"></label>
					<form:errors path="sexo" cssClass="help-block" />
				</div>
			</spring:bind>
		</div>

		<div class="row">
		 <div>
		  <spring:bind path="especialistaForm.phoneNumber">
			<div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
		  		<appfuse:label styleClass="control-label" key="user.phoneNumber" />
		  		<input type="text" name="phoneNumber" id="phoneNumber" class="form-control"
		  		placeholder="<fmt:message key="user.phoneNumber"/>" value="${status.value}" tabindex="12"/>
		  	</div>
		  </spring:bind>
		 </div>
		 <div>
		  <spring:bind path="especialistaForm.email">
			<div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
				<appfuse:label styleClass="control-label" key="user.email" />
				<input type="email" id="email" name="email" class="form-control"
				placeholder="<fmt:message key="user.emailExample"/>" value="${status.value}" tabindex="13"/>
				<label for="email" generated="true" class="error"></label>
				<form:errors path="email" cssClass="help-block" />
			</div>
		  </spring:bind>

		 </div>
		</div>
				<div class="row">
				  <div>
					<spring:bind path="especialistaForm.provincia">
						<div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						  <appfuse:label styleClass="control-label" key="user.address.province" />
						  <div cssClass="form-control">
							<form:select id="provincia" name="provincia" class="form-control" path="provincia" value="${status.value}" tabindex="14">
							  <form:options items="${provinciaList}"/>
							</form:select>
							<form:errors path="provincia" cssClass="help-block" />
						  </div>
						</div>
					</spring:bind>
				  </div>
				  <div>
					<spring:bind path="especialistaForm.localidad">
						<div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
							<appfuse:label styleClass="control-label" key="user.address.localidad" />
							<div cssClass="form-control">
								<form:select id="localidad" name="localidad" class="form-control"
								 path="localidad" tabindex="15" value="${status.value}">
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
				<spring:bind path="especialistaForm.calle">
					<div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.address.address" />
						<div cssClass="form-control">
							<input type="text" id="calle" name="calle" class="form-control"
							placeholder="<fmt:message key="user.address.address"/>" value="${status.value}" tabindex="16"/>
							<label for="calle" generated="true" class="error"></label>
							<form:errors path="calle" cssClass="help-block" />
						</div>
					</div>
				</spring:bind>
			  </div>
			  <div>
				<spring:bind path="especialistaForm.numero">
					<div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.address.numero" />
						<div cssClass="form-control">
							<input id="numero" name="numero" class="form-control"
							placeholder="<fmt:message key="user.address.numero"/>" value="${status.value}" tabindex="17"/>
							<label for="numero" generated="true" class="error"></label>
							<form:errors path="numero" cssClass="help-block" />
						</div>
					</div>
				</spring:bind>
			  </div>
			</div>

			<div class="row">
			  <div>
				<spring:bind path="especialistaForm.dpto">
					<div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.address.dpto" />
						<div cssClass="form-control">
							<input id="dpto" name="dpto" class="form-control"
							placeholder="<fmt:message key="user.address.dpto"/>" value="${status.value}" tabindex="18"/>
							<form:errors path="dpto" cssClass="help-block" />
						</div>
					</div>
				</spring:bind>
			 </div>
			 <div>
				<spring:bind path="especialistaForm.piso">
					<div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.address.piso" />
						<div cssClass="form-control">
							<input id="piso" name="piso" class="form-control"
							placeholder="<fmt:message key="user.address.piso"/>" value="${status.value}" autocomplete="off" tabindex="19"/>
							<form:errors path="piso" cssClass="help-block" />
						</div>
					</div>
				</spring:bind>
			 </div>
			</div>

         <div class="row">
		 <div>
           <spring:bind path="especialistaForm.matricula">
         	<div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
         	  <appfuse:label styleClass="control-label" key="user.endocrinologist.registration" />
         	  <input type="text" id="matricula" name="matricula" class="form-control"
              value="${status.value}" tabindex="22"/>
              <label for="matricula" generated="true" class="error"></label>
         	</div>
           </spring:bind>
         </div>
		</div>

       	<div class="form-group">
			<button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false" tabindex="23">
				<i class="icon-ok icon-white"></i>
				<fmt:message key="button.save" />
			</button>
			<button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true" tabindex="25">

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
				window.location.href = "${ctx}/home";
			});
	</script>
</c:set>

<v:javascript formName="especialistaForm" staticJavascript="false" />
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>