<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<head>
    <title><fmt:message key="userPaciente.title"/></title>
    <meta name="menu" content="UserMenu"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <link rel="stylesheet" href="/styles/style.css">
    <link href="<c:url value="/scripts/main.css" />" rel="stylesheet">
	  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
	  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
	  <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js" type="text/javascript"></script>
	  <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/localization/messages_es.js" type="text/javascript"></script>
      <script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>
	  <script src="http://jquery.bassistance.de/validate/additional-methods.js" type="text/javascript"></script>
	  <script src="/scripts/validator.js" type="text/javascript"></script>
	  <script type="text/javascript">
	  	function CancelFormButton(button) {
	  		onsubmit: false;
	  	window.location.href = "http://localhost:8080/endos/medicamentoList";
	  	};
	  	
	  	/* $('button[name="cancel"]').click(function(e){
			e.preventDefault();
			console.log($.get('http://localhost:8080/endos/medicamentoList', function(data) { 
				console.log(data);	
				//$()
			}));
		}); */
	  </script>

	  <script type="text/javascript">
      	$(document).ready(function() {
      		$('#grupoMedicamentoBusqueda').autocomplete({
      			serviceUrl : '/getGrupos',
      			paramName : "tagName",
      			delimiter : ",",
      			transformResult : function(response) {
      				return {
      					suggestions : $.map($.parseJSON(response), function(item) {
      						return {
      							value : item.tagName,
      							data : item.id
      						};
      					})
      				};
      			}
      		});
      	});
          /* function deleteBook(bookId){

          	  if(window.confirm('Esta seguro de querer eliminar este Medicamento ?')){
                    var url = 'delete/'+bookId;
                    window.location.href = url;
              }
          } */
      </script>
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

    <form:form commandName="medicamento" method="post" action="medicamentoForm" id="formulario" autocomplete="off"
               cssClass="well" onsubmit="return validateUser(this)">
        <form:hidden path="idMedicamento"/>
        <%-- <form:hidden path="version"/> --%>
        <input type="hidden" name="from" value="<c:out value="${param.from}"/>"/>
        <div>
        <spring:bind path="nombreGenerico">
        <appfuse:label styleClass="control-label" key="user.adminMedicamento.nombreGenerico"/>
        <input type="text" name="nombreGenerico" id="nombreGenerico" class="form-control"
           placeholder="<fmt:message key="user.adminMedicamento.nombreGenerico"/>" value="${status.value}" tabindex="1">
        </spring:bind>
        	<label for="nombreGenerico" generated="true" class="error"></label>
        </div>
        <div>
        <spring:bind path="nombreComercial"> 
        <appfuse:label styleClass="control-label" key="user.adminMedicamento.nombreComercial"/>  
		<input type="text" name="nombreComercial" id="nombreComercial" class="form-control"
           placeholder="<fmt:message key="user.adminMedicamento.nombreComercial"/>" value="${status.value}" tabindex="2">  
        </spring:bind>
        	<label for="nombreComercial" generated="true" class="error"></label>
        </div>
        <div>
        <spring:bind path="presentacion">
        <appfuse:label styleClass="control-label" key="user.adminMedicamento.presentacion"/>
        <input type="text" name="presentacion" id="presentacion" class="form-control"
           placeholder="<fmt:message key="user.adminMedicamento.presentacion"/>" value="${status.value}" tabindex="3">
        </spring:bind>
        	<label for="presentacion" generated="true" class="error"></label>
        </div>
        <div>
        <appfuse:label styleClass="control-label" key="user.adminMedicamento.grupoMedicamento"/>
        <form:input cssClass="form-control" path="grupoMedicamento" id="grupoMedicamentoBusqueda"/>

		<label for="grupoMedicamento" generated="true" class="error"></label>
        </div>
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
		
		<%-- <button type="submit" class="btn btn-warning " name="cancel" onclick="CancelFormButton(this);">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button> --%>
         <button type="submit" class="btn btn-warning " name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button> 
    </form:form>
</div>

<c:set var="scripts" scope="request">
	<script type="text/javascript">
		// This is here so we can exclude the selectAll call when roles is hidden
		 $('button[name="cancel"]').click(function(e){
			e.preventDefault();
			window.location.href = "http://localhost:8080/endos/medicamentoList";
			
		}); 
		
	</script>
</c:set>

</body>
</html>