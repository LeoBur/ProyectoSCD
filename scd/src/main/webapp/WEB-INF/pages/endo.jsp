<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="home.title" /></title>
<meta name="menu" content="Home" />
<link href="<c:url value="/scripts/main.css" />" rel="stylesheet">
<script src="/scripts/jquery.1.10.2.min.js" type="text/javascript"></script>
<script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>
<script type="text/javascript">
	//javascript para el autocomplete de ciudades
	$(document).ready(function() {
		$('#pacienteToSearch').autocomplete({
			serviceUrl : '/getTags',
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
</script>
</head>
<body class="home">
	<h2>
		<fmt:message key="home.heading" />
	</h2>

<div class="col-sm-7">
    <spring:bind path="endoSearch.*">
        <c:if test="${not empty status.errorMessages}">
        <div class="alert alert-danger alert-dismissable">
            <a href="#" data-dismiss="alert" class="close">&times;</a>
            <c:forEach var="error" items="${status.errorMessages}">
                <c:out value="${error}" escapeXml="false"/><br />
            </c:forEach>
        </div>
        </c:if>
    </spring:bind>
	<form:form commandName="endoSearch" method="post" action="home"
		enctype="multipart/form-data" id="mainSearchBox">
		<spring:bind path="endoSearch.pacienteToSearch">
			<div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
		</spring:bind>
			<appfuse:label key="endo.pacienteToSearch" styleClass="control-label" />
			<form:input cssClass="form-control" path="pacienteToSearch" placeHolder="${mainPlaceHolder}" id="pacienteToSearch" />
			<form:errors path="pacienteToSearch" cssClass="help-block" />
		</div>
		
		<div class="form-group">
			<button type="submit" name="upload" class="btn btn-primary"
				onclick="bCancel=false">
				<i class="icon-upload icon-white"></i>
				<fmt:message key="button.upload" />
			</button>
		</div>
	</form:form>
</div>
</body>
<c:set var="scripts" scope="request">
<v:javascript formName="endoSearch" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
</c:set>