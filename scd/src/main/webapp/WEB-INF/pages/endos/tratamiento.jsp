<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<head>
    <title><fmt:message key="userProfile.title"/></title>
    <link rel="stylesheet" href="/styles/style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base}/styles/style.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/master/build/css/bootstrap-datetimepicker.min.css">
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.1/css/font-awesome.css" rel="stylesheet">

    <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js" type="text/javascript"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/localization/messages_es.js" type="text/javascript"></script>
    <script src="http://jquery.bassistance.de/validate/additional-methods.js" type="text/javascript"></script>
    <script src="/scripts/validator.js" type="text/javascript"></script>
    <script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>

    <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.4/moment.min.js"></script>

    <script type="text/javascript" src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/master/src/js/bootstrap-datetimepicker.js"></script>

    <script type="text/javascript">
        function CancelFormButton(button) {
            onsubmit: false;
        window.location.href = "http://localhost:8080/endos/tratamiento";
        };
    </script>
</head>

<div class="container-fluid">
    <div class="col-md-2">
        <h3>Crear Tratamiento</h3>
    </div>
    <div class="col-md-8">
        <spring:bind path="tratamientoForm.*">
            <c:if test="${not empty status.errorMessages}">
                <div class="alert alert-danger alert-dismissable">
                    <a href="#" data-dismiss="alert" class="close">&times;</a>
                    <c:forEach var="error" items="${status.errorMessages}">
                        <c:out value="${error}" escapeXml="false"/><br/>
                    </c:forEach>
                </div>
            </c:if>
        </spring:bind>
        <form:form commandName="tratamientoForm" method="post" action="tratamiento" autocomplete="off" id="formulario" modelAttribute="tratamientoForm"
               cssClass="well" onsubmit="return validateUser(this)">

            <div class="form-group">
                <div class="row">
                      <div>
                        <spring:bind path="paciente">
                            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                <appfuse:label styleClass="control-label" key="user.dni"/>
                                    <span class="required">*</span>
                                <input type="text" name="paciente" id="paciente" class="form-control" readonly
                                placeholder="<fmt:message key="user.dni"/>" value="${status.value}" autofocus="autofocus" tabindex="1">
                            </div>
                        </spring:bind>
                      </div>
                      <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        <spring:bind path="fecha">
                            <appfuse:label styleClass="control-label" key="user.paciente.fecha"/>
                            <span class="required">*</span>
                            <div class='input-group date' id='datetimepicker1'>
                                <input type="text" name="fecha" id="fecha" class="form-control" readonly="readonly"
                                    placeholder="<fmt:message key="user.paciente.fecha"/>" value="${status.value}" maxlength="50"
                                    tabindex="5" data-date-format="DD/MM/YYYY">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </spring:bind>
                        <label for="fecha" generated="true" class="error"></label>
                        <form:errors path="fecha" cssClass="help-block"/>
                        <script type="text/javascript">
                            $(function () {
                              $('#datetimepicker1').datetimepicker({
                                language: 'pt-BR',
                                showToday: true,
                                pickTime: false
                              });
                            });
                          </script>
                      </div>
                </div>          
            </div>

            <div class="form-group">
                <div class="row">
                    <div>
                        <spring:bind path="medicamento1">
                          <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                            <appfuse:label styleClass="control-label" key="user.medicine.title" />
                            <div cssClass="form-control">
                              <form:select id="medicamento1" name="medicamento1" class="form-control"
                                path="medicamento1" value="${status.value}" tabindex="14">
                                <form:option value="NONE" label="--- Seleccione ---"/>
                                <form:options items="${medicamentoList}" itemValue="nombreComercial" itemLabel="nombreComercial"></form:options>
                              </form:select>
                              <form:errors path="medicamento1" cssClass="help-block" />
                            </div>
                          </div>
                        </spring:bind>
                    </div>

                    <div>
                        <spring:bind path="tratamientoForm.obsPrescripcion1">
                          <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                            <appfuse:label styleClass="control-label" key="active.observacion"/>
                            <span class="required">*</span>
                            <div cssClass="form-control">
                              <input type="text" id="obsPrescripcion1" name="obsPrescripcion1" class="form-control"
                                value="${status.value}" tabindex="15"/>
                              <label for="obsPrescripcion1" generated="true" class="error"></label>
                              <form:errors path="obsPrescripcion1" cssClass="help-block" />
                            </div>
                          </div>
                        </spring:bind>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="row">
                    <div>
                        <spring:bind path="medicamento2">
                          <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                            <appfuse:label styleClass="control-label" key="user.medicine.title" />
                            <div cssClass="form-control">
                              <form:select id="medicamento2" name="medicamento2" class="form-control"
                                path="medicamento2" value="${status.value}" tabindex="16">
                                <form:option value="NONE" label="--- Seleccione ---"/>
                                <form:options items="${medicamentoList}" itemValue="nombreComercial" itemLabel="nombreComercial"></form:options>
                              </form:select>
                              <form:errors path="medicamento2" cssClass="help-block" />
                            </div>
                          </div>
                        </spring:bind>
                    </div>

                    <div>
                        <spring:bind path="tratamientoForm.obsPrescripcion2">
                          <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                            <appfuse:label styleClass="control-label" key="active.observacion"/>
                            <span class="required">*</span>
                            <div cssClass="form-control">
                              <input type="text" id="obsPrescripcion2" name="obsPrescripcion2" class="form-control"
                                value="${status.value}" tabindex="17"/>
                              <label for="obsPrescripcion2" generated="true" class="error"></label>
                              <form:errors path="obsPrescripcion2" cssClass="help-block" />
                            </div>
                          </div>
                        </spring:bind>
                    </div>
                </div>
            </div>

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

<v:javascript formName="tratamientoForm" staticJavascript="false" />
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>