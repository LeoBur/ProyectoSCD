<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>

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

</head>

<div class="container">
<meta name="menu" content="UserMenu"/>

<c:set var="delObject" scope="request"><fmt:message key="userList.user"/></c:set>
<script type="text/javascript">var msgDelConfirm =
		"<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h3>Cargar</h2>
    <h3>Alimento</h2>
</div>
<!-- Ac� comienzan los formularios -->

<div class="col-sm-7">
	<spring:bind path="alimentoForm.*">
  		<c:if test="${not empty status.errorMessages}">
      		<div class="alert alert-danger alert-dismissable">
          	<a href="#" data-dismiss="alert" class="close">&times;</a>
          	<c:forEach var="error" items="${status.errorMessages}">
            	<c:out value="${error}" escapeXml="false"/><br/>
          	</c:forEach>
      		</div>
  		</c:if>
	</spring:bind>

	<form:form commandName="alimentoForm" method="post" action="cargarAlimento" id="formulario" autocomplete="off" name="miFormulario"
           cssClass="well" onsubmit="return validateUser(this)">
        <spring:bind path="idAlimento">
        	<input type="hidden" name="idAlimento" id="idAlimento" class="form-control" value="${status.value}"/>
        </spring:bind>
        <div class="row">
            <div>
                <spring:bind path="alimentoForm.nombre">
                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento" />
                            <input type="text" name="nombre" id="nombre" class="form-control"
                            placeholder="<fmt:message key="user.nutritionist.nombreAlimento"/>" value="${status.value}" maxlength="50"
                            tabindex="3">
                    </div>
                </spring:bind>
            </div>
            <div>
                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                    <spring:bind path="alimentoForm.cantGlucosaX100">
                        <appfuse:label styleClass="control-label" key="user.food.cantGlucosaX100"/>
                        <input type="text" name="cantGlucosaX100" id="cantGlucosaX100" class="form-control"
                        placeholder="<fmt:message key="user.food.cantGlucosaX100"/>" value="${status.value}" maxlength="50"
                        tabindex="4">
                    </spring:bind>
                </div>
                <label for="cantGlucosaX100" generated="true" class="error"></label>
                <form:errors path="cantGlucosaX100" cssClass="help-block"/>
            </div>
		</div>

		<div class="row">
             <div>
                  <spring:bind path="alimentoForm.cantGrasasX100">
                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        <appfuse:label styleClass="control-label" key="user.food.cantGrasasX100" />
                        <input type="text" name="cantGrasasX100" id="cantGrasasX100" class="form-control"
                        placeholder="<fmt:message key="user.food.cantGrasasX100"/>" value="${status.value}" tabindex="12"/>
                    </div>
                  </spring:bind>
             </div>
             <div>
                <spring:bind path="alimentoForm.cantProteinasX100">
                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                    <appfuse:label styleClass="control-label" key="user.food.cantProteinasX100" />
                    <span class="required">*</span>
                    <div cssClass="form-control">
                        <input type="text" id="cantProteinasX100" name="cantProteinasX100" class="form-control"
                        placeholder="<fmt:message key="user.food.cantProteinasX100"/>" value="${status.value}" tabindex="17"/>
                        <label for="cantProteinasX100" generated="true" class="error"></label>
                        <form:errors path="cantProteinasX100" cssClass="help-block" />
                    </div>
                </div>
                </spring:bind>
             </div>
		</div>
		<div class="row">
                     <div>
                          <spring:bind path="alimentoForm.cantCarbohidratosX100">
                            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                <appfuse:label styleClass="control-label" key="user.food.cantCarbohidratosX100" />
                                <input type="text" name="cantCarbohidratosX100" id="cantCarbohidratosX100" class="form-control"
                                placeholder="<fmt:message key="user.food.cantCarbohidratosX100"/>" value="${status.value}" tabindex="12"/>
                            </div>
                          </spring:bind>
                     </div>
                     <div>
                        <spring:bind path="alimentoForm.cantCaloriasX100">
                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                            <appfuse:label styleClass="control-label" key="user.food.cantCaloriasX100" />
                            <span class="required">*</span>
                            <div cssClass="form-control">
                                <input type="text" id="cantCaloriasX100" name="cantCaloriasX100" class="form-control"
                                placeholder="<fmt:message key="user.food.cantCaloriasX100"/>" value="${status.value}" tabindex="17"/>
                                <label for="cantCaloriasX100" generated="true" class="error"></label>
                                <form:errors path="cantCaloriasX100" cssClass="help-block" />
                            </div>
                        </div>
                        </spring:bind>
                     </div>
        </div>


        <br>
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
				window.location.href = "http://localhost:8080/admin/endocrinologoList";
			});
	</script>


</c:set>

<v:javascript formName="endocrinologoForm" staticJavascript="false" />
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>