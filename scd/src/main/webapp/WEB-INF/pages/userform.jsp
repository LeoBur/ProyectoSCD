<%-- <%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userProfile.title"/></title>
    <meta name="menu" content="UserMenu"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="userList.user"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="userProfile.heading"/></h2>
    <c:choose>
        <c:when test="${param.from == 'list'}">
            <p><fmt:message key="userProfile.admin.message"/></p>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="userProfile.message"/></p>
        </c:otherwise>
    </c:choose>
</div>
Acá comienzan los formularios

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

        <spring:bind path="user.username">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
            <appfuse:label styleClass="control-label" key="user.username"/>
            <form:input cssClass="form-control" path="username" id="username"/>
            <form:errors path="username" cssClass="help-block"/>
            <c:if test="${pageContext.request.remoteUser == user.username}">
                <span class="help-block">
                    <a href="<c:url value="/updatePassword" />"><fmt:message key='updatePassword.changePasswordLink'/></a>
                </span>
            </c:if>
        </div>

        <spring:bind path="user.passwordHint">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
            <appfuse:label styleClass="control-label" key="user.passwordHint"/>
            <form:input cssClass="form-control" path="passwordHint" id="passwordHint"/>
            <form:errors path="passwordHint" cssClass="help-block"/>
        </div>
        <div class="row">
            <spring:bind path="user.firstName">
            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
                <appfuse:label styleClass="control-label" key="user.firstName"/>
                <form:input cssClass="form-control" path="firstName" id="firstName" maxlength="50"/>
                <form:errors path="firstName" cssClass="help-block"/>
            </div>
            <spring:bind path="user.lastName">
            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
                <appfuse:label styleClass="control-label" key="user.lastName"/>
                <form:input cssClass="form-control" path="lastName" id="lastName" maxlength="50"/>
                <form:errors path="lastName" cssClass="help-block"/>
            </div>
        </div>
        <div class="row">
            <spring:bind path="user.email">
            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
                <appfuse:label styleClass="control-label" key="user.email"/>
                <form:input cssClass="form-control" path="email" id="email"/>
                <form:errors path="email" cssClass="help-block"/>
            </div>
            <div class="col-sm-6 form-group">
                <appfuse:label styleClass="control-label" key="user.phoneNumber"/>
                <form:input cssClass="form-control" path="phoneNumber" id="phoneNumber"/>
            </div>
        </div>
        <div class="form-group">
            <appfuse:label styleClass="control-label" key="user.website"/>
            <form:input cssClass="form-control" path="website" id="website"/>
        </div>
        <div>
            <legend class="accordion-heading">
                <a data-toggle="collapse" href="#collapse-address"><fmt:message key="user.address.address"/></a>
            </legend>
            <div id="collapse-address" class="accordion-body collapse">
                <div class="form-group">
                    <appfuse:label styleClass="control-label" key="user.address.address"/>
                    <form:input cssClass="form-control" path="address.address" id="address.address"/>
                </div>
                <div class="row">
                    <div class="col-sm-7 form-group">
                        <appfuse:label styleClass="control-label" key="user.address.city"/>
                        <form:input cssClass="form-control" path="address.city" id="address.city"/>
                    </div>
                    <div class="col-sm-2 form-group">
                        <appfuse:label styleClass="control-label" key="user.address.province"/>
                        <form:input cssClass="form-control" path="address.province" id="address.province"/>
                    </div>
                    <div class="col-sm-3 form-group">
                        <appfuse:label styleClass="control-label" key="user.address.postalCode"/>
                        <form:input cssClass="form-control" path="address.postalCode" id="address.postalCode"/>
                    </div>
                </div>
                <div class="form-group">
                    <appfuse:label styleClass="control-label" key="user.address.country"/>
                    <appfuse:country name="address.country" prompt="" default="${user.address.country}"/>
                </div>
            </div>
        </div>
<c:choose>
    <c:when test="${param.from == 'list' or param.method == 'Add'}">
        <div class="form-group">
            <label class="control-label"><fmt:message key="userProfile.accountSettings"/></label>
            <label class="checkbox-inline">
                <form:checkbox path="enabled" id="enabled"/>
                <fmt:message key="user.enabled"/>
            </label>

            <label class="checkbox-inline">
                <form:checkbox path="accountExpired" id="accountExpired"/>
                <fmt:message key="user.accountExpired"/>
            </label>

            <label class="checkbox-inline">
                <form:checkbox path="accountLocked" id="accountLocked"/>
                <fmt:message key="user.accountLocked"/>
            </label>

            <label class="checkbox-inline">
                <form:checkbox path="credentialsExpired" id="credentialsExpired"/>
                <fmt:message key="user.credentialsExpired"/>
            </label>
        </div>
        <div class="form-group">
            <label for="userRoles" class="control-label"><fmt:message key="userProfile.assignRoles"/></label>
            <select id="userRoles" name="userRoles" multiple="true" class="form-control">
                <c:forEach items="${availableRoles}" var="role">
                <option value="${role.value}" ${fn:contains(user.roles, role.label) ? 'selected' : ''}>${role.label}</option>
                </c:forEach>
            </select>
        </div>
    </c:when>
    <c:when test="${not empty user.username}">
        <div class="form-group">
            <label class="control-label"><fmt:message key="user.roles"/>:</label>
            <div class="readonly">
                <c:forEach var="role" items="${user.roleList}" varStatus="status">
                    <c:out value="${role.label}"/><c:if test="${!status.last}">,</c:if>
                    <input type="hidden" name="userRoles" value="<c:out value="${role.label}"/>"/>
                </c:forEach>
            </div>
            <form:hidden path="enabled"/>
            <form:hidden path="accountExpired"/>
            <form:hidden path="accountLocked"/>
            <form:hidden path="credentialsExpired"/>
        </div>
    </c:when>
</c:choose>
        <div class="form-group">
            <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>

            <c:if test="${param.from == 'list' and param.method != 'Add'}">
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

<v:javascript formName="user" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
 --%>
 
 <%@ include file="/common/taglibs.jsp"%>
<%@page import ="java.util.*" %>
<%@page import ="java.text.SimpleDateFormat" %>
<%
	Date dnow = new Date();
	SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
	String currentDate = ft.format(dnow);
	
%>
<head>
    <title><fmt:message key="userPaciente.title"/></title>
    <meta name="menu" content="UserMenu"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>
</head>
<body>


<c:set var="delObject" scope="request"><fmt:message key="userList.user"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="userEspecialista.heading"/></h2>
    <c:choose>
        <c:when test="${param.from == 'list'}">
            <p><fmt:message key="userProfile.admin.message"/></p>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="userProfile.message"/></p>
        </c:otherwise>
    </c:choose>
</div>


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
        
        <div class="form-group">
                <appfuse:label styleClass="control-label" key="user.paciente.fecha"/>
                <form:input cssClass="form-control" path="" id="datepicker" value="<%= currentDate %>"  size="12"/>
                <%--<input type="text" name="datepicker" id="datepicker" readonly="readonly" size="12" /> --%>
        </div>   
        
        
        <div class="form-group">
                <appfuse:label styleClass="control-label" key="user.paciente.medicion"/>
                <form:input cssClass="form-control" path="phoneNumber" id="medicion"/>
        </div>
        
        <div class="form-group">
                <appfuse:label styleClass="control-label" key="user.paciente.peso"/>
                <form:input cssClass="form-control" path="phoneNumber" id="peso"/>
        </div>
        
        <div>
            <legend class="accordion-heading">
                <a data-toggle="collapse" href="#collapse-address"><fmt:message key="user.paciente.alimentoIng"/></a>
            </legend>
            <div id="collapse-address" class="accordion-body collapse">
                <div class="col-sm-10">
    				
        		<%--<display:table name="applicationScope.userNames" id="user" cellspacing="0" cellpadding="0"
                		   defaultsort="1" class="table table-condensed table-striped table-hover" pagesize="50" requestURI="">
        			<display:column property="username" escapeXml="true" style="width: 30%" titleKey="user.username"
                        sortable="true"/>
        			<display:column titleKey="activeUsers.fullName" sortable="true">
            			<c:out value="${user.firstName} ${user.lastName}" escapeXml="true"/>
            			<c:if test="${not empty user.email}">
                			<a href="mailto:<c:out value="${user.email}"/>">
                    			<img src="<c:url value="/images/iconEmail.gif"/>"
                        	 alt="<fmt:message key="icon.email"/>" class="icon"/></a>
            			</c:if>
        			</display:column>

        			<display:setProperty name="paging.banner.item_name" value="user"/>
        			<display:setProperty name="paging.banner.items_name" value="users"/>
    			</display:table>  --%>
    			<display:table name="applicationScope.userNames" id="user" cellspacing="0" cellpadding="0"
                		   defaultsort="1" class="table table-condensed table-striped table-hover" pagesize="50" requestURI="">
                	<display:column property="username" escapeXml="true" style="width: 30%" titleKey="user.paciente.momentoDia"
                        sortable="true"/>
                    <display:column titleKey="activePaiente.comidas" sortable="true">
                    	<c:out value="${user.firstName} ${user.lastName}" escapeXml="true"/>
                    	<c:if test="${not empty user.email}">
                			<a href="mailto:<c:out value="${user.email}"/>">
                    			<img src="<c:url value="/images/iconEmail.gif"/>"
                        	 alt="<fmt:message key="icon.email"/>" class="icon"/></a>
            			</c:if>
            				<a href="${pageContext.request.contextPath}/editar/${user.id}">Editar</a>
                    </display:column>
    			</display:table>
				</div>
            </div>
        </div>
        
        <%--
        <div class="form-group">
        	<input type="checkbox" name="cv1" value="1" checked> Opcion 1 <br>
        	<input type="checkbox" name="cv2" value="2" > Opcion 2 <br>
        	<input type="checkbox" name="cv3" value="3" > Opcion 3 <br>
        </div>
        
        <div class="form-group">
        	<form:select path="phoneNumber">
        		<form:option value="Enfermedod" label="Enfermo"/>
        		<form:option value="Dolor" label="Dolor de Cabeza" selected="selected"/>
        	</form:select>
        </div> --%>
        <%-- <form:select path="variable a la que va enlazada" items="${iterar la lista }"/> --%>
        
        
        
        <%-- <div>
            <legend class="accordion-heading">
                <a data-toggle="collapse" href="#collapse-address"><fmt:message key="user.address.address"/></a>
            </legend>
            <div id="collapse-address" class="accordion-body collapse">
                <div class="form-group">
                    <appfuse:label styleClass="control-label" key="user.address.address"/>
                    <form:input cssClass="form-control" path="address.address" id="address.address"/>
                </div>
                <div class="row">
                    <div class="col-sm-7 form-group">
                        <appfuse:label styleClass="control-label" key="user.address.city"/>
                        <form:input cssClass="form-control" path="address.city" id="address.city"/>
                    </div>
                    <div class="col-sm-2 form-group">
                        <appfuse:label styleClass="control-label" key="user.address.province"/>
                        <form:input cssClass="form-control" path="address.province" id="address.province"/>
                    </div>
                    <div class="col-sm-3 form-group">
                        <appfuse:label styleClass="control-label" key="user.address.postalCode"/>
                        <form:input cssClass="form-control" path="address.postalCode" id="address.postalCode"/>
                    </div>
                </div>
                <div class="form-group">
                    <appfuse:label styleClass="control-label" key="user.address.country"/>
                    <appfuse:country name="address.country" prompt="" default="${user.address.country}"/>
                    <appfuse:country/>
                    
                </div>
            </div>
        </div> --%>
        
        
        
        
        
        <div  class="form-group">
        	<appfuse:label styleClass="control-label" key="user.oservacion.title"/>
        	<form:textarea cssClass="form-control" path="phoneNumber" />
        </div>
        
        <div class="form-group">
        	<appfuse:label styleClass="control-label" key="user.symptom.title"/>
        	<form:select cssClass="form-control" path="phoneNumber">
        		<form:option value="Enfermedod" label="Enfermo"/>
        		<form:option value="Dolor" label="Dolor de Cabeza" selected="selected"/>
        	</form:select>
        </div>
        
        <div class="form-group">
        	<appfuse:label styleClass="control-label" key="user.medicine.title"/>
        	<form:select cssClass="form-control" path="phoneNumber">
        		<form:option value="BayasC" label="Bayaspirina"/>
        		<form:option value="Plus" label="Cura Plus" selected="selected"/>
        	</form:select>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.registrer"/>
            </button>

            <c:if test="${param.from == 'list' and param.method != 'Add'}">
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

</body>
</html>




