<%@ include file="/common/taglibs.jsp"%>

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
        window.location.href = "${ctx}/endos/tratamiento";
        };
    </script>

    <script type="text/javascript">
        $(document).ready(function(){
                    var next = $("#total").val() - 1;
                    $(".add-more").click(function(e){
                        e.preventDefault();
                        var options = $("#options").val();
                        var addto = "#fields" + next;
                        next = next + 1;
                        var addRemove = "#fields" + (next);
                        var newIn = '<div id="fields'+ next +'"><div class="form-group"><div class="row"><div><div class="col-sm-6 form-group"><label for="medicine.title" class="control-label">Medicamento</label><div cssclass="form-control"><select id="prescripciones[].medicamento.nombreComercial" name="prescripciones[].medicamento.nombreComercial" class="form-control"><option value="NONE" selected="selected">--- Seleccione ---</option>'+options+'</select></div></div></div><div><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Observaciones</label><span class="required">*</span><div cssclass="form-control"><input type="text" id="prescripciones[].descripcion" name="prescripciones[].descripcion" class="form-control"><label for="prescripciones[].descripcion" generated="true" class="error"></label></div></div></div></div></div><button id="remove' + (next) + '" class="btn btn-danger remove-me" >-</button></div><div id="fields'+ (next) +'"></div>';
                        var newInput = $(newIn);
                        var removeBtn = '<button id="remove' + (next) + '" class="btn btn-danger remove-me" >-</button></div><div id="fields'+ (next) +'">';
                        var removeButton = $(removeBtn);
                        $(addto).after(newInput);
                        $("#fields" + next).attr('data-source',$(addto).attr('data-source'));
                        $("#count").val(next);

                            $('.remove-me').click(function(e){
                                e.preventDefault();
                                var fieldNum = this.id.substr(this.id.lastIndexOf("e")+1);
                                var fieldID = "#fields" + fieldNum;
                                $(this).remove();
                                $(fieldID).remove();
                            });
                    });
                    $('.remove-another').click(function(e){
                        e.preventDefault();
                        var fieldNum = this.id.substr(this.id.lastIndexOf("e")+1);
                        var fieldID = "#fields" + fieldNum;
                        $(this).remove();
                        $(fieldID).remove();
                    });
        });
    </script>

</head>

<div class="container-fluid">
    <div class="col-md-2">
        <h3>Editar Tratamiento</h3>
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
        <form:form commandName="tratamientoForm" method="post" action="editTratamiento" autocomplete="off" id="formulario" modelAttribute="tratamientoForm"
               cssClass="well" onsubmit="return validateUser(this)">

            <input type="hidden" name="options" id="options" value="${options}"/>
            <input type="hidden" name="total" id="total" value="${total}"/>
            <div class="form-group">
                    <div>
                        <spring:bind path="idTratamiento">
                            <input type="hidden" id="idTratamiento" name="idTratamiento" value="${status.value}">
                        </spring:bind>
                    </div>
                <div class="row">
                      <div>
                        <spring:bind path="paciente.persona.dni">
                            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                <appfuse:label styleClass="control-label" key="user.dni"/>
                                    <span class="required">*</span>
                                <input type="text" name="dni" id="dni" class="form-control" readonly
                                placeholder="<fmt:message key="user.dni"/>" value="${status.value}" autofocus="autofocus" tabindex="1">
                            </div>
                        </spring:bind>
                      </div>
                      <div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        <spring:bind path="fechaTratamiento">
                            <appfuse:label styleClass="control-label" key="user.paciente.fecha"/>
                            <span class="required">*</span>
                            <div class='input-group date' id='datetimepicker1'>
                                <input type="text" name="fechaTratamiento" id="fechaTratamiento" class="form-control" readonly="readonly"
                                    placeholder="<fmt:message key="user.paciente.fecha"/>" value="${status.value}" maxlength="50"
                                    tabindex="5" data-date-format="DD/MM/YYYY">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </spring:bind>
                        <label for="fechaTratamiento" generated="true" class="error"></label>
                        <form:errors path="fechaTratamiento" cssClass="help-block"/>
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
            <div class="controls" id="profs">
                <form class="input-append">
                    <div class="form-group">
                        <div>
                        <c:set var="count" value="0" scope="page" />
                            <c:forEach items="${tratamientoForm.prescripciones}" varStatus="gridRow">
                                <c:set var="count" value="${count + 1}" scope="page"/>
                                <div id="fields${gridRow.index}">
                                        <div class="row">
                                            <div>
                                                <spring:bind path="prescripciones[${gridRow.index}].medicamento.nombreComercial">
                                                  <div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                    <appfuse:label styleClass="control-label" key="user.medicine.title" />
                                                    <div cssClass="form-control">
                                                      <form:select id="prescripciones[${gridRow.index}].medicamento.nombreComercial"
                                                        name="prescripciones[${gridRow.index}].medicamento.nombreComercial" class="form-control"
                                                        path="prescripciones[${gridRow.index}].medicamento.nombreComercial" value="${status.value}" tabindex="14">
                                                        <form:option value="NONE" label="--- Seleccione ---"/>
                                                        <form:options items="${medicamentoList}" itemValue="nombreComercial" itemLabel="nombreComercial"></form:options>
                                                      </form:select>
                                                      <form:errors path="prescripciones[${gridRow.index}].medicamento.nombreComercial" cssClass="help-block" />
                                                    </div>
                                                  </div>
                                                </spring:bind>
                                            </div>
                                            <div>
                                                <spring:bind path="prescripciones[${gridRow.index}].descripcion">
                                                  <div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                    <appfuse:label styleClass="control-label" key="active.observacion"/>
                                                    <span class="required">*</span>
                                                    <div cssClass="form-control">
                                                      <input type="text" id="prescripciones[${gridRow.index}].descripcion"
                                                        name="prescripciones[${gridRow.index}].descripcion" class="form-control"
                                                        value="${status.value}" tabindex="15"/>
                                                      <label for="prescripciones[${gridRow.index}].descripcion"
                                                        generated="true" class="error"></label>
                                                      <form:errors path="prescripciones[${gridRow.index}].descripcion" cssClass="help-block" />
                                                    </div>
                                                  </div>
                                                </spring:bind>
                                            </div>
                                        </div>
                                        <c:if test="${gridRow.index > 0}">
                                            <button id="remove${gridRow.index}" class="btn btn-danger remove-another" >-</button></div>
                                        </c:if>
                                    <div>
                                </div>
                            </c:forEach>
                            <button id="b1" class="btn add-more" type="button">+</button>
                            <div class="form-group">
                            <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false" tabindex="24">
                                <i class="icon-ok icon-white"></i>
                                <fmt:message key="button.save" />
                            </button>
                            <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true" href="<c:url value='/endos/tratamiento?search=${dni}'/>" tabindex="26">
                                <i class="icon-remove"></i>
                                <fmt:message key="button.cancel" />
                            </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

        </form:form>
    </div>
</div>

<v:javascript formName="tratamientoForm" staticJavascript="false" />
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>