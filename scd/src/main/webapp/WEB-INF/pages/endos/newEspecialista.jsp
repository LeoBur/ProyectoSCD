<%@ include file="/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="userProfile.title"/></title>
    <meta name="menu" content="UserMenu"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <link rel="stylesheet" href="/styles/style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>

    <script src="jquery-1.4.2.min.js"></script>
    <script src="jquery-ui-1.8.6.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
    <script src="jquery.ui.datepicker-es.js"></script>
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
  	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js" type="text/javascript"></script>
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/localization/messages_es.js" type="text/javascript"></script>
	<script src="http://jquery.bassistance.de/validate/additional-methods.js" type="text/javascript"></script>
	<script src="/scripts/validator.js" type="text/javascript"></script>
    <script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>
    <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

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
    <h3>Administraci&oacuten</h2>
    <h3>de Especialistas</h2>
</div>
<!-- Ac� comienzan los formularios -->

<div class="col-sm-7">
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

	<form:form commandName="especialistaForm" method="post" action="newEspecialista" id="formulario" autocomplete="off" name="miFormulario"
           cssClass="well" onsubmit="return validateUser(this)">
  		<spring:bind path="id">
  			<input type="hidden" name="id" id="id" class="form-control" value="${status.value}"/>
  		</spring:bind>
  		<spring:bind path="username">
  			<input type="hidden" id="username" class="form-control" value="${status.value}"/>
  		</spring:bind>
        <spring:bind path="dni">
        	<input type="hidden" name="dniposta" id="dniposta" class="form-control" value="${status.value}"/>
        </spring:bind>
        <spring:bind path="dni">
			<input type="hidden" name="idEspecialista" id="idEspecialista" class="form-control" value="${status.value}"/>
		</spring:bind>
			
		<div class="form-group">
			<div class="row">
				<div>
					<spring:bind path="dni">
						<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
							<appfuse:label styleClass="control-label" key="user.dni"/>
							<%-- si es nuevo, habilita la edicion. Si no es nuevo, la deshabilita --%>
							<c:choose>
								<c:when test="${especialistaForm.nuevaPersona}">
									<span class="required">*</span>
									<input type="text" name="dni" id="dni" class="form-control"
									placeholder="<fmt:message key="user.dni"/>" value="${status.value}" autofocus="autofocus" tabindex="1">
								</c:when>
								<c:otherwise>
									<input type="text" name="dni" id="dni" class="form-control" readonly
									placeholder="<fmt:message key="user.dni"/>" value="${status.value}" autofocus="autofocus" tabindex="1">
								</c:otherwise>
							</c:choose>
						</div>
					</spring:bind>
				</div>
				<div class="col-sm-6 form-group">
					<br>
					<div>
						<%-- si es nuevo, habilita el boton--%>
						<c:if test="${especialistaForm.nuevaPersona}">
							<button type="submit" name="search" class="btn btn-primary" formmethod="get"
								formnovalidate="formnovalidate" onclick="bCancel=false" tabindex="2" value="search">
								<i class="icon-upload icon-white"></i>
								<fmt:message key="button.search" />
							</button>
						</c:if>
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
            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
				<spring:bind path="especialistaForm.dia">
						<appfuse:label styleClass="control-label" key="user.fecha.nacimiento"/>
						<span class="required">*</span>
						<input type="text" name="dia" id="dia" class="form-control"
						placeholder="<fmt:message key="user.fecha.nacimiento"/>" value="${status.value}" maxlength="50"
						tabindex="5">
				</spring:bind>
				<label for="dia" generated="true" class="error"></label>
		   		<form:errors path="dia" cssClass="help-block"/>
		   	</div>

		    <spring:bind path="especialistaForm.sexo">
				<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					<appfuse:label styleClass="control-label" key="user.sexo" />
					<span class="required">*</span>
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
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.phoneNumber" />
						<input type="text" name="phoneNumber" id="phoneNumber" class="form-control"
						placeholder="<fmt:message key="user.phoneNumber"/>" value="${status.value}" tabindex="12"/>
					</div>
				</spring:bind>
			</div>
			<div>
				<spring:bind path="especialistaForm.email">
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.email" />
						<input type="email" id="email" name="email" class="form-control"
						placeholder="<fmt:message key="user.emailExample"/>" value="${status.value}" tabindex="13"/>
						<label for="email" generated="true" class="error"></label>
						<form:errors path="email" cssClass="help-block" />
					</div>
				</spring:bind>
			</div>
		</div>

		<a><fmt:message key="user.address.address1" /></a>
		<div class="row">
			<div>
				<spring:bind path="especialistaForm.provincia">
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
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
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
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
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.address.address" />
						<span class="required">*</span>
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
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.address.numero" />
						<span class="required">*</span>
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
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
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
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
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
        	<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            	<spring:bind path="especialistaForm.tipoEspecialista">
                    <appfuse:label styleClass="control-label" key="user.tipo_esp"/>
                    <input list="tiposEsp" name="tipoEspecialista" id="tipoEspecialista" class="form-control"
                      value="${status.value}" tabindex="20">
				</spring:bind>

				<datalist id="tiposEsp">
            		  <option value="NUTRICIONISTA">
            		  <option value="ENTRENADOR_PERSONAL">
				</datalist>
			</div>
			<div>
           		<spring:bind path="especialistaForm.matricula">
         			<div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.endocrinologist.registration" />
						<span class="required">*</span>
						<input type="text" id="matricula" name="matricula" class="form-control"
						value="${status.value}" tabindex="22"/>
						<label for="matricula" generated="true" class="error"></label>
					</div>
           		</spring:bind>
         	</div>
         	<div>
         		<spring:bind path="especialistaForm.enabled">
         			<div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
         				<appfuse:label styleClass="control-label" key="user.enabled" />
         				<c:choose>
							<c:when test="${especialistaForm.enabled == 'true'}">
								<input type="checkbox" path="enabled" id="enabled" name="enabled" class="form-control" value="true"  checked="true"
									value="${status.value}" tabindex="23"/>
							</c:when>
							<c:when test="${especialistaForm.enabled == 'false'}">
								<input type="checkbox" path="enabled" id="enabled" name="enabled" class="form-control" value="true"
									value="${status.value}" tabindex="23"/>
							</c:when>
						</c:choose>
         				<label for="enabled" generated="true" class="error"></label>
         				<form:errors path="enabled" cssClass="help-block" />
         			</div>
         		</spring:bind>
         	</div>
		</div>
		
       	<div class="form-group">
			<button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false" tabindex="24">
				<i class="icon-ok icon-white"></i>
				<fmt:message key="button.save" />
			</button>
			<c:if test="${not empty especialistaForm.dni}">
				<button type="submit" class="btn btn-default" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)" tabindex="25">
					<i class="icon-trash"></i>
					<fmt:message key="button.delete" />
				</button>
			</c:if>
			<button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true" tabindex="26">
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
				window.location.href = "http://localhost:8080/endos/especialistaList";
			});
	</script>
	<script type="text/javascript">
    $(document).ready(function (e) {
	$(function() {
	    $("#dia").datepicker({dateFormat: 'dd/mm/yy', changeMonth: true, changeYear: true, minDate: -27010, maxDate:0, yearRange: '-100:+0'});
        });
    });
	</script>
	<script type="text/javascript">
    		 $('button[name="search"]').click(function(e){
    			  	e.preventDefault();
    				//var dni = document.getElementById("dni").value; Con cualquiera de las 2 formas anda!!!
    				var dni = $('input[name=dni]').val();
    				window.location.href = "http://localhost:8080/endos/newEspecialista?search=search&dni="+dni;
    			});
    	</script>

    <script>
        $(function() {
            $('#dia').blur(function(){
                var txtVal =  $('#dia').val();
                if(isDate(txtVal)){
                    ;
                }
                else{
                    $(this).parent().find(".error").text("La fecha ingresada es incorrecta");
                    document.miFormulario.dia.value = "";
                    }
            });

        function isDate(txtDate)
        {
            var currVal = txtDate;
            if(currVal == '')
                return false;

            var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/; //Declare Regex
            var dtArray = currVal.match(rxDatePattern); // is format OK?

            if (dtArray == null)
                return false;

            //Checks for mm/dd/yyyy format.
            dtMonth = dtArray[3];
            dtDay= dtArray[1];
            dtYear = dtArray[5];

            if (dtMonth < 1 || dtMonth > 12)
                return false;
            else if (dtDay < 1 || dtDay> 31)
                return false;
            else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31)
                return false;
            else if (dtMonth == 2)
            {
                var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
                if (dtDay> 29 || (dtDay ==29 && !isleap))
                        return false;
            }
            return true;
        }

        });
    </script>
	
</c:set>

<v:javascript formName="especialistaForm" staticJavascript="false" />
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>