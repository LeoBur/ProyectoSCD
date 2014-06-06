<%@ include file="/common/taglibs.jsp"%>
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

<div class="col-sm-4">
    <h2><fmt:message key="user.medicine.title"/></h2>
    <c:choose>
        <c:when test="${param.from == 'list'}">
            <p><fmt:message key="user.medicine.title"/></p>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="userProfile.message.medicamento"/></p>
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
        <form:hidden path="idMedicamento"/>
        <%-- <form:hidden path="version"/> --%>
        <input type="hidden" name="from" value="<c:out value="${param.from}"/>"/>
        
          
        
        
        <div class="form-group">
                <appfuse:label styleClass="control-label" key="user.adminMedicamento.nombreGenerico"/>
                <form:input cssClass="form-control" path="nombreGenerico" id="nombreGenerico"/>
        </div>
        
        <div class="form-group">
                <appfuse:label styleClass="control-label" key="user.adminMedicamento.nombreComercial"/>
                <form:input cssClass="form-control" path="nombreComercial" id="nombreComercial"/>
        </div>
        
        <div class="form-group">
                <appfuse:label styleClass="control-label" key="user.adminMedicamento.presentacion"/>
                <form:input cssClass="form-control" path="presentacion" id="presentacion"/>
        </div>
        
        <div class="form-group">
        	<appfuse:label styleClass="control-label" key="user.adminMedicamento.grupoMedicamento"/>
        	<form:select cssClass="form-control" path="grupoMedicamento">
        		<form:option value="INHIBIDOR_DE_ALFA_GLUCOSIDASA" label="INHIBIDOR DE ALFA GLUCOSIDASA"/>
        		<form:option value="SULFONILUREA" label="SULFONILUREA" selected="selected"/>
        		<form:option value="BIGUANIDA" label="BIGUANIDA"/>
        		<form:option value="GLINIDA" label="GLINIDA"/>
        		<form:option value="MEGLITINIDA" label="MEGLITINIDA"/>
        		<form:option value="TZD" label="TZD"/>
        		<form:option value="INSULINA" label="INSULINA"/>
        		<form:option value="GLP_1" label="GLP_1"/>
        		<form:option value="IAPP" label="IAPP"/>
        		<form:option value="DPP_4" label="DPP_4"/>
        	</form:select>
        </div>
        
        <%-- <form:select path="variable a la que va enlazada" items="${iterar la lista }"/> <form:options/>  --%>
               
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