<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<head>
    <title><fmt:message key="userPaciente.title"/></title>
    <meta name="menu" content="UserMenu"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
  <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js" type="text/javascript"></script>
  <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/localization/messages_es.js" type="text/javascript"></script>
  <script src="http://jquery.bassistance.de/validate/additional-methods.js" type="text/javascript"></script>
  <script src="/scripts/validator.js" type="text/javascript"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>
</head>
<body>


<c:set var="delObject" scope="request"><fmt:message key="sintoma.title"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-md-2">
    <h2><fmt:message key="user.symptom.title"/></h2>
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
    <spring:bind path="sintoma.*">
        <c:if test="${not empty status.errorMessages}">
            <div class="alert alert-danger alert-dismissable">
                <a href="#" data-dismiss="alert" class="close">&times;</a>
                <c:forEach var="error" items="${status.errorMessages}">
                    <c:out value="${error}" escapeXml="false"/><br/>
                </c:forEach>
            </div>
        </c:if>
    </spring:bind>

    <form:form commandName="sintoma" method="post" action="sintomaForm" id="formulario" autocomplete="off"
               cssClass="well" onsubmit="return validateUser(this)">
        <form:hidden path="idSintoma"/>
        
        <%-- <input type="hidden" name="from" value="<c:out value="${param.from}"/>"/> --%>

        

        <div class="form-group">
                        <appfuse:label styleClass="control-label" key="user.adminSintoma.nombre"/>
                        <form:input cssClass="form-control" path="nombre" id="nombreSintoma" name="nombreSintoma" />
                        <label for="nombreSintoma" generated="true" class="error"></label>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.registrer"/>
            </button>

            <c:if test="${not empty sintoma.idSintoma}">
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
		 $('button[name="cancel"]').click(function(e){
			e.preventDefault();
			window.location.href = "${ctx}/endos/sintomaList";

		});

	</script>
</c:set>

</body>
</html>