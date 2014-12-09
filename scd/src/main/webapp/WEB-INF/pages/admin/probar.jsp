<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userPaciente.title"/></title>
    <meta name="menu" content="UserMenu"/>

    <script src="${pageScope.bootstrapJavascriptUrl}"></script>
    <script src="${pageScope.datetimepickerJavaScriptUrl}"></script>
    <link rel="stylesheet" href="${pageScope.bootstrapStylesheetUrl}"/>
    <link rel="stylesheet" href="${pageScope.datetimepickerStyleSheet}"/>

</head>
<spring:url scope="page" var="datetimepickerJavaScriptUrl" value="/scripts/bootstrap-datetimepicker.min.js"/>
<spring:url scope="page" var="datetimepickerStyleSheet" value="/styles/bootstrap-datetimepicker.min.css"/>
<spring:url scope="page" var="bootstrapStyleSheetUrl" value="/styles/bootstrap.css"/>
<spring:url scope="page" var="bootstrapJavaScriptUrl" value="/scripts/bootstrap.min.js"/>

<form:form commandName="probar" method="post" action="probar" autocomplete="off" id="formulario" modelAttribute="probar"
               cssClass="well" onsubmit="return validateUser(this)">
    <div class="container">
      <div class="row">
        <div class='col-sm-6'>
          <div class="form-group">
            <div class='input-group date' id='hora'>
              <input type='text' class="form-control" path="hora"/>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
              </span>
            </div>
          </div>
        </div>
        <script type="text/javascript">
          $(function () {
          $('#hora').datetimepicker();
          });
        </script>
      </div>
    </div>
</form>