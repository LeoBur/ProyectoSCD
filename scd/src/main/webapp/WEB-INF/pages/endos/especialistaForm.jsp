<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userProfile.title"/></title>
    <meta name="menu" content="UserMenu"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="userList.user"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-4">
    <h2><fmt:message key="user.specialist.title"/></h2>
    <c:choose>
        <c:when test="${param.from == 'list'}">
            <p><fmt:message key="userProfile.admin.message"/></p>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="userProfile.message.especialista"/></p>
        </c:otherwise>
    </c:choose>
</div>
<%-- Acá comienzan los formularios --%>

<div class="col-sm-7">
            <spring:bind path="especialista.*">
                <c:if test="${not empty status.errorMessages}">
                    <div class="alert alert-danger alert-dismissable">
                        <a href="#" data-dismiss="alert" class="close">&times;</a>
                        <c:forEach var="error" items="${status.errorMessages}">
                            <c:out value="${error}" escapeXml="false"/><br/>
                        </c:forEach>
                    </div>
                </c:if>
            </spring:bind>

    <form:form commandName="especialista" method="post" action="especialistaForm" id="especialistaForm" autocomplete="off"
               cssClass="well" onsubmit="return validateUser(this)">
                <form:hidden path="id"/>
                
                         	
        		
                                        <%-- nombre, apellido,telefono,email,dni sexo, observaciones--%>        
        
                            <div class="row">
                                    <spring:bind path="especialista.nombre">
                                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    </spring:bind>
                                            <appfuse:label styleClass="control-label" key="user.firstName"/>
                                            <form:input cssClass="form-control" path="nombre" id="nombre" maxlength="50"/>
                                            <form:errors path="nombre" cssClass="help-block"/>
                                        </div>
                                    <spring:bind path="especialista.apellido">
                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    </spring:bind>
                                        <appfuse:label styleClass="control-label" key="user.lastName"/>
                                        <form:input cssClass="form-control" path="apellido" id="apellido" maxlength="50"/>
                                        <form:errors path="apellido" cssClass="help-block"/>
                                    </div>
                            </div>


                            <div class="row">
                                <spring:bind path="especialista.telefono">
                                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    </spring:bind>
                                        <appfuse:label styleClass="control-label" key="user.phoneNumber"/>
                                        <form:input cssClass="form-control" path="telefono" id="telefono"/>
                                        </div>
                                    <spring:bind path="especialista.email">
                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    </spring:bind>
                                        <appfuse:label styleClass="control-label" key="user.email"/>
                                        <form:input cssClass="form-control" path="email" id="email"/>
                                        <form:errors path="email" cssClass="help-block"/>
                                    </div>
                            </div>                                
                            <div class="row">
                            	<spring:bind path="especialista.tipo_esp">
                                	<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                </spring:bind>
	                                    <appfuse:label styleClass="control-label" key="user.dni"/>
	                                    <form:input cssClass="form-control" path="dni" id="dni"/>
	                                	</div>
	                                <spring:bind path="especialista.email">
	                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
	                                </spring:bind>
	                                	<appfuse:label styleClass="control-label" key="user.tipo_esp"/>
		                                <form:select cssClass="form-control" path="tipo_esp">
			                    			<form:option value="nutricionista" label="Nutricionista"/>
			                    			<form:option value="entrenadorPersonal" label="Entrenador Personal"/>
			                    		</form:select>
		                    		</div>
                        </div>
            
            <%-- Probamos con usuario y contraseña--%>
		            <%-- <spring:bind path="user.username">
                    	<div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                    </spring:bind>
                    	<appfuse:label styleClass="control-label" key="user.username"/>
                    		<form:input cssClass="form-control" path="" id="username"/>
                    		<form:errors path="username" cssClass="help-block"/>
                    
         
                    <spring:bind path="user.passwordHint">
                    	<div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                    </spring:bind>
                        <appfuse:label styleClass="control-label" key="user.passwordHint"/>
                            <form:input cssClass="form-control" path="" id="passwordHint"/>
                            <form:errors path="passwordHint" cssClass="help-block"/>
                            
                    <spring:bind path="user.passwordHint">
                    	<div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                    </spring:bind>
                    	<appfuse:label styleClass="control-label" key="user.tipo"/>
                    		 --%>

                            <br></br>



        <div class="form-group">
            <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            <c:if test="${not empty especialista.id}">
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

<v:javascript formName="especialista" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>

