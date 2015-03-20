<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>

<head>
	<title><fmt:message key="userProfile.title"/></title>
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
	  	window.location.href = "http://localhost:8080/endos/newPaciente";
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

    <script>
        $(document).ready(function() {
            $('#dni').autocomplete({
                serviceUrl: 'http://localhost:8080/endos/getDNITags',
                paramName: "tagName",
                delimiter: "," ,
                transformResult: function(response) {
                return {
                suggestions: $.map($.parseJSON(response), function(item) {
                    return {value: item.tagName, data: item.id };
                })
                };
                }
            });
            $('#dni').blur(function(e) {
                var search = $('input[name=dni]').val();
                window.location.href = "http://localhost:8080/endos/newPaciente?search=search&dni="+search;
            });
        });
    </script>
</head>

<div class="container-fluid">
<meta name="menu" content="UserMenu"/>

<c:set var="delObject" scope="request"><fmt:message key="userList.user"/></c:set>
<script type="text/javascript">var msgDelConfirm =
		"<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-md-2">
  <div class='text-center'>
    <h3>Administraci&oacuten de Pacientes</h3>
  </div>
</div>
<!-- Ac� comienzan los formularios -->

<div class="col-md-9">
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

	<form:form commandName="pacienteForm" method="post" action="newPaciente" id="formulario" autocomplete="off" name="miFormulario"
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
					        	<c:choose>
                                	<c:when test="${pacienteForm.nuevaPersona}">
					        	    	<span class="required">*</span>
										<input type="text" name="dni" id="dni" class="form-control"
										placeholder="<fmt:message key="user.dni"/>" value="${status.value}"
										tabindex="1" maxlength="8" minlength="7">
									</c:when>
									<c:otherwise>
										<input type="text" name="dni" id="dni" class="form-control" readonly
										placeholder="<fmt:message key="user.dni"/>" value="${status.value}"
										tabindex="1" maxlength="8" minlength="7">
									</c:otherwise>
                                </c:choose>
					        </div>
					    </spring:bind>
					  </div>
					  
				</div>			
			
			</div>

			<c:choose>
                <c:when test="${pacienteForm.enabled=='true'}">
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
        
        <div class="form-group">
        <div class="row">
	        <div class="col-md-6">
	          <spring:bind path="pacienteForm.firstName">
	            <appfuse:label styleClass="control-label" key="user.firstName"/>
	            <c:choose>
                    <c:when test="${pacienteForm.enabled=='true'}">
                        <input type="text" name="firstName" id="firstName" class="form-control"
                           placeholder="<fmt:message key="user.firstName"/>" value="${status.value}" maxlength="50"
                           tabindex="3">
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="firstName" id="firstName" class="form-control"
                           placeholder="<fmt:message key="user.firstName"/>" value="${status.value}" maxlength="50"
                           tabindex="3" readonly>
                    </c:otherwise>
                </c:choose>
	          </spring:bind>
	          		<label for="firstName" generated="true" class="error"></label>
	          <form:errors path="firstName" cssClass="help-block"/>
	        </div>
	        <div class="col-md-6">
	          <spring:bind path="pacienteForm.lastName">
	            <appfuse:label styleClass="control-label" key="user.lastName"/>
	            <c:choose>
                    <c:when test="${pacienteForm.enabled=='true'}">
                        <input type="text" name="lastName" id="lastName" class="form-control"
                            placeholder="<fmt:message key="user.lastName"/>" value="${status.value}" maxlength="50"
                            tabindex="4">
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="lastName" id="lastName" class="form-control"
                            placeholder="<fmt:message key="user.lastName"/>" value="${status.value}" maxlength="50"
                            tabindex="4" readonly>
                    </c:otherwise>
                </c:choose>
	          </spring:bind>
	          		<label for="lastName" generated="true" class="error"></label>
		   	  <form:errors path="lastName" cssClass="help-block"/>
		   	</div>
		</div>
        </div>
        
		<div class="row">
            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
	          <spring:bind path="pacienteForm.dia">
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

              <c:choose>
                  <c:when test="${pacienteForm.enabled=='true'}">
                      <script type="text/javascript">
                        $(function () {
                          $('#datetimepicker1').datetimepicker({
                            language: 'pt-BR',
                            showToday: true,
                            pickTime: false,
                            maxDate: new Date()
                          });
                        });
                      </script>
                  </c:when>
                  <c:otherwise>

                  </c:otherwise>
              </c:choose>

		   	</div>

		    <spring:bind path="pacienteForm.sexo">
				<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					<appfuse:label styleClass="control-label" key="user.sexo" />
					  <span class="required">*</span>
						<div class="form-control">
						<c:choose>
						    <c:when test="${pacienteForm.enabled=='true'}">
                                <c:choose>
                                    <c:when test="${pacienteForm.sexo == null || pacienteForm.sexo == 'M'}">
                                        <input type="radio" name="sexo" value="M" checked="checked" tabindex="8"/>Masculino  &nbsp; &nbsp; &nbsp;
                                        <input type="radio" name="sexo" value="F" tabindex="9"/>Femenino
                                    </c:when>
                                    <c:when test="${pacienteForm.sexo != null && pacienteForm.sexo == 'F'}">
                                        <input type="radio" name="sexo" value="M" tabindex="10"/>Masculino &nbsp; &nbsp; &nbsp;
                                        <input type="radio" name="sexo" value="F" checked="checked" tabindex="11"/>Femenino
                                    </c:when>
                                </c:choose>
						    </c:when>
						    <c:otherwise>
                                <c:choose>
                                    <c:when test="${pacienteForm.sexo == null || pacienteForm.sexo == 'M'}">
                                        <input type="radio" name="sexo" value="M" checked="checked" tabindex="8" disabled/>Masculino  &nbsp; &nbsp; &nbsp;
                                        <input type="radio" name="sexo" value="F" tabindex="9" disabled/>Femenino
                                    </c:when>
                                    <c:when test="${pacienteForm.sexo != null && pacienteForm.sexo == 'F'}">
                                        <input type="radio" name="sexo" value="M" tabindex="10" disabled/>Masculino &nbsp; &nbsp; &nbsp;
                                        <input type="radio" name="sexo" value="F" checked="checked" tabindex="11" disabled/>Femenino
                                    </c:when>
                                </c:choose>
						    </c:otherwise>
						</c:choose>
						</div>
						<label for="sexo" generated="true" class="error"></label>
					<form:errors path="sexo" cssClass="help-block" />
				</div>
			</spring:bind>
		</div>
		
		<div class="row">
		 <div>
		  <spring:bind path="pacienteForm.phoneNumber">
			<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
		  		<appfuse:label styleClass="control-label" key="user.phoneNumber" />
                <c:choose>
		  		    <c:when test="${pacienteForm.enabled=='true'}">
		  		        <input type="text" name="phoneNumber" id="phoneNumber" class="form-control"
		  		            placeholder="<fmt:message key="user.phoneNumber"/>" value="${status.value}" tabindex="12"/>
		  		    </c:when>
		  		    <c:otherwise>
		  		        <input type="text" name="phoneNumber" id="phoneNumber" class="form-control" readonly
		  		            placeholder="<fmt:message key="user.phoneNumber"/>" value="${status.value}" tabindex="12"/>
		  		    </c:otherwise>
		  		</c:choose>
		  	</div>
		  </spring:bind>
		 </div>
		 <div>
		  <spring:bind path="pacienteForm.email">
			<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
				<appfuse:label styleClass="control-label" key="user.email" />
				<c:choose>
		  		    <c:when test="${pacienteForm.enabled=='true'}">
		  		        <input type="email" id="email" name="email" class="form-control"
		  		            placeholder="<fmt:message key="user.emailExample"/>" value="${status.value}" tabindex="13"/>
		  		    </c:when>
		  		    <c:otherwise>
                        <input type="email" id="email" name="email" class="form-control" readonly
                            placeholder="<fmt:message key="user.emailExample"/>" value="${status.value}" tabindex="13"/>
		  		    </c:otherwise>
		  		</c:choose>
				<label for="email" generated="true" class="error"></label>
				<form:errors path="email" cssClass="help-block" />
			</div>
		  </spring:bind>
		  		
		 </div>
		</div>
		
				<div class="row">
				  <div>
					<spring:bind path="pacienteForm.provincia">
						<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						  <appfuse:label styleClass="control-label" key="user.address.province" />
						  <div cssClass="form-control">
                          <c:choose>
                            <c:when test="${pacienteForm.enabled=='true'}">
                                <form:select id="provincia" name="provincia" class="form-control" path="provincia" value="${status.value}" tabindex="14">
                                    <form:options items="${provinciaList}"/>
                                </form:select>
                            </c:when>
                            <c:otherwise>
                                <form:select id="provincia" name="provincia" class="form-control" path="provincia" value="${status.value}" tabindex="14" readonly="readonly">
                                    <form:option value="${pacienteForm.provincia}" label="${pacienteForm.provincia}"/>
                                </form:select>
                            </c:otherwise>
                          </c:choose>

							<form:errors path="provincia" cssClass="help-block" />
						  </div>
						</div>
					</spring:bind>
				  </div>
				  <div>
					<spring:bind path="pacienteForm.localidad">
						<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
							<appfuse:label styleClass="control-label" key="user.address.localidad" />
							<div cssClass="form-control">
                                <c:choose>
                                    <c:when test="${pacienteForm.enabled=='true'}">
                                        <form:select id="localidad" name="localidad" class="form-control"
                                                path="localidad" tabindex="15" value="${status.value}">
                                            <form:options items="${localidadList}"/>
                                        </form:select>
                                    </c:when>
                                    <c:otherwise>
                                        <form:select id="localidad" name="localidad" class="form-control" readonly="readonly"
                                                path="localidad" tabindex="15" value="${status.value}">
                                          <form:option value="${pacienteForm.localidad}" label="${pacienteForm.localidad}"/>
                                        </form:select>
                                    </c:otherwise>
                                </c:choose>

								<form:errors path="localidad" cssClass="help-block" />
							</div>
						</div>
					</spring:bind>
				  </div>
			</div>

			<div class="row">
			  <div>
				<spring:bind path="pacienteForm.calle">
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.address.address" />
						<span class="required">*</span>
						<div cssClass="form-control">
                            <c:choose>
                                <c:when test="${pacienteForm.enabled=='true'}">
                                    <input type="text" id="calle" name="calle" class="form-control"
                                	    placeholder="<fmt:message key="user.address.address"/>" value="${status.value}" tabindex="16"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="text" id="calle" name="calle" class="form-control" readonly
                                        placeholder="<fmt:message key="user.address.address"/>" value="${status.value}" tabindex="16"/>
                                </c:otherwise>
                            </c:choose>

							<label for="calle" generated="true" class="error"></label>
							<form:errors path="calle" cssClass="help-block" />
						</div>
					</div>
				</spring:bind>
			  </div>
			  <div>
				<spring:bind path="pacienteForm.numero">
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.address.numero" />
						<span class="required">*</span>
						<div cssClass="form-control">
                            <c:choose>
                                <c:when test="${pacienteForm.enabled=='true'}">
                                    <input id="numero" name="numero" class="form-control"
                                        placeholder="<fmt:message key="user.address.numero"/>" value="${status.value}" tabindex="17"/>
                                </c:when>
                                <c:otherwise>
                                <input id="numero" name="numero" class="form-control" readonly
                                    placeholder="<fmt:message key="user.address.numero"/>" value="${status.value}" tabindex="17"/>
                                </c:otherwise>
                            </c:choose>
							<label for="numero" generated="true" class="error"></label>
							<form:errors path="numero" cssClass="help-block" />
						</div>
					</div>
				</spring:bind>
			  </div>
			</div>

			<div class="row">
			  <div>
				<spring:bind path="pacienteForm.dpto">
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.address.dpto" />
						<div cssClass="form-control">
						<c:choose>
                            <c:when test="${pacienteForm.enabled=='true'}">
                            <input id="dpto" name="dpto" class="form-control"
                                placeholder="<fmt:message key="user.address.dpto"/>" value="${status.value}" tabindex="18"/>
                            </c:when>
                            <c:otherwise>
                                <input id="dpto" name="dpto" class="form-control" readonly
                                    placeholder="<fmt:message key="user.address.dpto"/>" value="${status.value}" tabindex="18"/>
                            </c:otherwise>
                        </c:choose>
							<form:errors path="dpto" cssClass="help-block" />
						</div>
					</div>	
				</spring:bind>
			 </div>
			 <div>
				<spring:bind path="pacienteForm.piso">
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.address.piso" />
						<div cssClass="form-control">
							<c:choose>
                                <c:when test="${pacienteForm.enabled=='true'}">
                                    <input id="piso" name="piso" class="form-control"
                                        placeholder="<fmt:message key="user.address.piso"/>" value="${status.value}" autocomplete="off" tabindex="19"/>
                                </c:when>
                                <c:otherwise>
                                    <input id="piso" name="piso" class="form-control" readonly
                                        placeholder="<fmt:message key="user.address.piso"/>" value="${status.value}" autocomplete="off" tabindex="19"/>
                                </c:otherwise>
                            </c:choose>
							<form:errors path="piso" cssClass="help-block" />
						</div>
					</div>
				</spring:bind>
			 </div>
			</div>
		 <div class="row">
           <div>
				<spring:bind path="pacienteForm.limiteInferior">
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.paciente.limit.inferior" />
						<div cssClass="form-control">
						    <c:choose>
                                <c:when test="${pacienteForm.enabled=='true'}">
                                    <input id="limiteInferior" name="limiteInferior" class="form-control"
                                        placeholder="<fmt:message key="user.paciente.limit.inferior"/>" value="${status.value}" tabindex="20"/>
                                </c:when>
                                <c:otherwise>
                                    <input id="limiteInferior" name="limiteInferior" class="form-control" readonly
                                        placeholder="<fmt:message key="user.paciente.limit.inferior"/>" value="${status.value}" tabindex="20"/>
                                </c:otherwise>
                            </c:choose>
							<form:errors path="limiteInferior" cssClass="help-block" />
						</div>
					</div>
				</spring:bind>
			 </div>
			 <div>
				<spring:bind path="pacienteForm.limiteSuperior">
					<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						<appfuse:label styleClass="control-label" key="user.paciente.limit.superior" />
						<div cssClass="form-control">
						    <c:choose>
                                <c:when test="${pacienteForm.enabled=='true'}">
                                    <input id="limiteSuperior" name="limiteSuperior" class="form-control"
                                        placeholder="<fmt:message key="user.paciente.limit.superior"/>" value="${status.value}" autocomplete="off" tabindex="21"/>
                                </c:when>
                                <c:otherwise>
                                    <input id="limiteSuperior" name="limiteSuperior" class="form-control" readonly
                                    placeholder="<fmt:message key="user.paciente.limit.superior"/>" value="${status.value}" autocomplete="off" tabindex="21"/>
                                </c:otherwise>
                            </c:choose>
							<form:errors path="limiteSuperior" cssClass="help-block" />
						</div>
					</div>
				</spring:bind>
			 </div>
		 </div>

         <div class="row">
         <div>
           <spring:bind path="pacienteForm.tipoDiabetes">
             <div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
               <appfuse:label styleClass="control-label" key="user.paciente.tipoDiabetes" />
               <div cssClass="form-control">
                   <c:choose>
                       <c:when test="${pacienteForm.enabled=='true'}">
                           <form:select id="tipoDiabetes" name="tipoDiabetes" class="form-control" path="tipoDiabetes" value="${status.value}" tabindex="22">
                                <form:options items="${tipoDiabetesList}"/>
                           </form:select>
                       </c:when>
                       <c:otherwise>
                           <form:select id="tipoDiabetes" name="tipoDiabetes" class="form-control" path="tipoDiabetes"
                              value="${status.value}" tabindex="22" readonly="readonly">
                                <form:option value="${pacienteForm.tipoDiabetes}" label="${pacienteForm.tipoDiabetes}"/>
                           </form:select>
                       </c:otherwise>
                   </c:choose>
                 <form:errors path="tipoDiabetes" cssClass="help-block" />
               </div>
             </div>
           </spring:bind>
         </div>
         <div>
         		  <spring:bind path="pacienteForm.enabled">
         			<div class="col-md-2 col-md-offset-2 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
         				<appfuse:label styleClass="control-label" key="user.enabled" />
         				<c:choose>
                                                    <c:when test="${pacienteForm.enabled == 'true'}">
                                                        <input type="checkbox" path="enabled" id="enabled" name="enabled" class="form-control" value="true"  checked="true"
                                                        value="${status.value}" tabindex="23"/>
                                                    </c:when>
                                                    <c:when test="${pacienteForm.enabled == 'false'}">
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
			
			<button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true" tabindex="26">

				<i class="icon-remove"></i>
				<fmt:message key="button.cancel" />
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
				window.location.href = "http://localhost:8080/endos/pacienteList";
			});
	</script>
	</script>
	<script type="text/javascript">
    		 $('button[name="search"]').click(function(e){
    			  	e.preventDefault();
    				//var dni = document.getElementById("dni").value; Con cualquiera de las 2 formas anda!!!
    				var dni = $('input[name=dni]').val();
    				window.location.href = "http://localhost:8080/endos/newPaciente?search=search&dni="+dni;
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

<v:javascript formName="pacienteForm" staticJavascript="false" />
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>