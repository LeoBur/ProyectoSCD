<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="userPaciente.title"/></title>
    <meta name="menu" content="UserMenu"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
</head>
<body>


<c:set var="delObject" scope="request"><fmt:message key="userList.user"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-md-2">
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


<div class="col-md-4">
    <spring:bind path="medicamento.*">
        <c:if test="${not empty status.errorMessages}">
            <div class="alert alert-danger alert-dismissable">
                <a href="#" data-dismiss="alert" class="close">&times;</a>
                <c:forEach var="error" items="${status.errorMessages}">
                    <c:out value="${error}" escapeXml="false"/><br/>
                </c:forEach>
            </div>
        </c:if>
    </spring:bind>

    <form:form commandName="medicamento" method="post" action="medicamentoForm" id="medicamentoForm" autocomplete="off"
               cssClass="well" onsubmit="return validateUser(this)">
        <form:hidden path="idMedicamento"/>
        <%-- <form:hidden path="version"/> --%>
        <input type="hidden" name="from" value="<c:out value="${param.from}"/>"/>
        
        <spring:bind path="nombreGenerico">
        <appfuse:label styleClass="control-label" key="user.adminMedicamento.nombreGenerico"/>
        <input type="text" name="nombreGenerico" id="nombreGenerico" class="form-control"
           placeholder="<fmt:message key="user.adminMedicamento.nombreGenerico"/>" value="${status.value}" tabindex="1">
        </spring:bind>
        
        <spring:bind path="nombreComercial"> 
        <appfuse:label styleClass="control-label" key="user.adminMedicamento.nombreComercial"/>  
		<input type="text" name="nombreComercial" id="nombreComercial" class="form-control"
           placeholder="<fmt:message key="user.adminMedicamento.nombreComercial"/>" value="${status.value}" tabindex="2">  
        </spring:bind>
        
        <spring:bind path="presentacion">
        <appfuse:label styleClass="control-label" key="user.adminMedicamento.presentacion"/>
        <input type="text" name="presentacion" id="presentacion" class="form-control"
           placeholder="<fmt:message key="user.adminMedicamento.presentacion"/>" value="${status.value}" tabindex="3">
        </spring:bind>
        
        <spring:bind path="grupoMedicamento">
        <appfuse:label styleClass="control-label" key="user.adminMedicamento.grupoMedicamento"/>
        <input list="drogas" name="grupoMedicamento" id="grupoMedicamento" class="form-control"
          value="${status.value}" tabindex="4">
		</spring:bind>
		
		<datalist id="drogas">
		  <option value="INHIBIDOR DE ALFA GLUCOSIDASA">
		  <option value="SULFONILUREA">
		  <option value="BIGUANIDA">
		  <option value="GLINIDA">
		  <option value="MEGLITINIDA">
		  <option value="TZD">
		  <option value="INSULINA">
		  <option value="GLP_1">
		  <option value="IAPP">
		  <option value="DPP_4">
		</datalist>
        
        <%-- <form:select path="variable a la que va enlazada" items="${iterar la lista }"/> <form:options/>  --%>
        <br>               
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.registrer"/>
        </button>

        <c:if test="${not empty medicamento.idMedicamento}">
          <button type="submit" class="btn btn-danger" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
              <i class="icon-trash"></i> <fmt:message key="button.delete"/>
          </button>
        </c:if>

        <button type="submit" class="btn btn-warning " name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </form:form>
</div>

</body>
</html>