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
<%-- Acá comienzan los formularios --%>

<div class="col-sm-7">
            <spring:bind path="user.*">
                <c:if test="${not empty status.errorMessages}">
                    <div class="alert alert-danger alert-dismissable">
                        <a href="#" data-dismiss="alert" class="close">&times;</a>
                        <c:forEach var="error" items="${status.errorMessages}">
                            <c:out value="${error}" escapeXml="false"/><br/>
                        </c:forEach>
                    </div>
                </c:if>
            </spring:bind>

    <form:form commandName="user" method="post" action="userform" id="userForm" autocomplete="off"
               cssClass="well" onsubmit="return validateUser(this)">
                <form:hidden path="id"/>
                <form:hidden path="version"/>
                <input type="hidden" name="from" value="<c:out value="${param.from}"/>"/>
        
        
        
            	
        		
        
                                        <%-- nombre, apellido,telefono,email,dni sexo, observaciones--%>        
        
                            <div class="row">
                                    <spring:bind path="user.firstName">
                                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    </spring:bind>
                                            <appfuse:label styleClass="control-label" key="user.firstName"/>
                                            <form:input cssClass="form-control" path="" id="firstName" maxlength="50"/>
                                            <form:errors path="firstName" cssClass="help-block"/>
                                        </div>
                                    <spring:bind path="user.lastName">
                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    </spring:bind>
                                        <appfuse:label styleClass="control-label" key="user.lastName"/>
                                        <form:input cssClass="form-control" path="" id="lastName" maxlength="50"/>
                                        <form:errors path="lastName" cssClass="help-block"/>
                                    </div>
                            </div>


                            <div class="row">
                                <spring:bind path="user.phoneNumber">
                                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    </spring:bind>
                                        <appfuse:label styleClass="control-label" key="user.phoneNumber"/>
                                        <form:input cssClass="form-control" path="phoneNumber" id="phoneNumber"/>
                                        </div>
                                    <spring:bind path="user.email">
                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    </spring:bind>
                                        <appfuse:label styleClass="control-label" key="user.email"/>
                                        <form:input cssClass="form-control" path="" id="email"/>
                                        <form:errors path="email" cssClass="help-block"/>
                                    </div>
                            </div>                                
                            <div class="row">
                                <div class="col-sm-6 form-group">
                                    <appfuse:label styleClass="control-label" key="user.dni"/>
                                    <form:input cssClass="form-control" path="" id="dni"/>
                                </div>
                                <spring:bind path="user.email">
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                </spring:bind>
                                    <appfuse:label styleClass="control-label" key="user.sexo"/>
                                    <div cssClass="form-control">
                                        <form:radiobutton  path="email" label="Femenino"/>&nbsp;
                                        <form:radiobutton path="email" label="Masculino" />
                                        <form:errors path="email" cssClass="help-block"/>
                                    </div>
                            </div>
                        </div>
            
            <%-- Probamos con usuario y contraseña--%>
		                  <spring:bind path="user.username">
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

                            <br></br>



        <div class="form-group">
            <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
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

<v:javascript formName="user" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>


