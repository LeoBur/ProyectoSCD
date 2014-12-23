<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="userProfile.title"/></title>

    <link rel="stylesheet" href="/resources/demos/style.css">
    <link rel="stylesheet" href="/styles/style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base}/styles/style.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/master/build/css/bootstrap-datetimepicker.min.css">
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.1/css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen" href="content/pygments-manni.css">


    <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

  	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js" type="text/javascript"></script>
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/localization/messages_es.js" type="text/javascript"></script>
	<script src="http://jquery.bassistance.de/validate/additional-methods.js" type="text/javascript"></script>
	<script src="/scripts/validator.js" type="text/javascript"></script>
    <script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>

    <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.4/moment.min.js"></script>

    <script type="text/javascript" src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/master/src/js/bootstrap-datetimepicker.js"></script>
    <script>
      	$(function() {
        	$( "#tabs" ).tabs();
      	});
    </script>
</head>

<div class="container">
<meta name="menu" content="UserMenu"/>

<c:set var="delObject" scope="request"><fmt:message key="userList.user"/></c:set>
<script type="text/javascript">var msgDelConfirm =
		"<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h3>Dieta a</h2>
    <h3>Recomendadar</h2>
</div>
<!-- Ac� comienzan los formularios -->

<div class="col-sm-7">
	<spring:bind path="dietaRecomendadaForm.*">
  		<c:if test="${not empty status.errorMessages}">
      		<div class="alert alert-danger alert-dismissable">
          	<a href="#" data-dismiss="alert" class="close">&times;</a>
          	<c:forEach var="error" items="${status.errorMessages}">
            	<c:out value="${error}" escapeXml="false"/><br/>
          	</c:forEach>
      		</div>
  		</c:if>
	</spring:bind>

	<form:form commandName="dietaRecomendadaForm" method="post" action="dieta" id="formulario" autocomplete="off" name="miFormulario"
           cssClass="well" onsubmit="return validateUser(this)">
        <spring:bind path="dni">
        	<input type="hidden" name="dni" id="dni" class="form-control" value="${status.value}"/>
        </spring:bind>
    <div id="tabs">
      		<ul>
        		<li><a href="#tabs-1">Lunes</a></li>
    		    <li><a href="#tabs-2">Martes</a></li>
    		    <li><a href="#tabs-3">Miercoles</a></li>
    		    <li><a href="#tabs-4">Jueves</a></li>
    		    <li><a href="#tabs-5">Viernes</a></li>
    		    <li><a href="#tabs-6">Sabado</a></li>
    		</ul>
        <div id="tabs-1">
            <spring:bind path="momentoLunes1">
                <input type="hidden" name="momentoLunes1" id="momentoLunes1" class="form-control" value="DESAYUNO"/>
            </spring:bind>
            <spring:bind path="diaDietaLunes">
                <input type="hidden" name="diaDietaLunes" id="diaDietaLunes" class="form-control" value="LUNES"/>
            </spring:bind>
            <div class="form-group">
                <div>
                    <spring:bind path="dietaRecomendadaForm.nombreAlimentoLunes1">
                        <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                        <form:select path="nombreAlimentoLunes1" cssClass="form-control">
                        	<form:option value="NONE" label="--- Seleccione ---"/>
                            <form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
                        </form:select>
                    </spring:bind>
                    <label for="nombreAlimentoLunes1" generated="true" class="error"></label>
                    <form:errors path="nombreAlimentoLunes1" cssClass="help-block"/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                    <spring:bind path="dietaRecomendadaForm.cantidadLunes1">
                        <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                        <input type="text" name="cantidadLunes1" id="cantidadLunes1" class="form-control"
                        placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="${status.value}" maxlength="50"
                        tabindex="4">
                    </spring:bind>
                <label for="cantidadLunes1" generated="true" class="error"></label>
                <form:errors path="cantidadLunes1" cssClass="help-block"/>
                </div>
                 <div>
                      <spring:bind path="dietaRecomendadaForm.observacionesLunes1">
                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                            <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                            <input type="text" name="observacionesLunes1" id="observacionesLunes1" class="form-control"
                            placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="${status.value}" tabindex="12"/>
                        </div>
                      </spring:bind>
                 </div>
            </div>

            <%-- Segundo Alimento--%>

            <div class="form-group">
                            <div>
                                <spring:bind path="dietaRecomendadaForm.nombreAlimentoLunes2">
                                    <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                    <form:select path="nombreAlimentoLunes2" cssClass="form-control">
                                    	<form:option value="NONE" label="--- Seleccione ---"/>
                                        <form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
                                    </form:select>
                                </spring:bind>
                                <label for="nombreAlimentoLunes2" generated="true" class="error"></label>
                                <form:errors path="nombreAlimentoLunes2" cssClass="help-block"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                <spring:bind path="dietaRecomendadaForm.cantidadLunes2">
                                    <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                    <input type="text" name="cantidadLunes2" id="cantidadLunes2" class="form-control"
                                    placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="${status.value}" maxlength="50"
                                    tabindex="4">
                                </spring:bind>
                            <label for="cantidadLunes2" generated="true" class="error"></label>
                            <form:errors path="cantidadLunes2" cssClass="help-block"/>
                            </div>
                             <div>
                                  <spring:bind path="dietaRecomendadaForm.observacionesLunes2">
                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                        <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                        <input type="text" name="observacionesLunes2" id="observacionesLunes2" class="form-control"
                                        placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="${status.value}" tabindex="12"/>
                                    </div>
                                  </spring:bind>
                             </div>
                        </div>
        </div>
        <%-- Fin del tabs1 --%>

        <div id="tabs-2">
                    <spring:bind path="momentoMartes1">
                        <input type="hidden" name="momentoMartes1" id="momentoMartes1" class="form-control" value="DESAYUNO"/>
                    </spring:bind>
                    <spring:bind path="diaDietaMartes">
                        <input type="hidden" name="diaDietaMartes" id="diaDietaMartes" class="form-control" value="MARTES"/>
                    </spring:bind>
                    <div class="form-group">
                        <div>
                            <spring:bind path="dietaRecomendadaForm.nombreAlimentoMartes1">
                                <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                <form:select path="nombreAlimentoMartes1" cssClass="form-control">
                                	<form:option value="NONE" label="--- Seleccione ---"/>
                                    <form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
                                </form:select>
                            </spring:bind>
                            <label for="nombreAlimentoMartes1" generated="true" class="error"></label>
                            <form:errors path="nombreAlimentoMartes1" cssClass="help-block"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                            <spring:bind path="dietaRecomendadaForm.cantidadMartes1">
                                <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                <input type="text" name="cantidadMartes1" id="cantidadMartes1" class="form-control"
                                placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="${status.value}" maxlength="50"
                                tabindex="4">
                            </spring:bind>
                        <label for="cantidadMartes1" generated="true" class="error"></label>
                        <form:errors path="cantidadMartes1" cssClass="help-block"/>
                        </div>
                         <div>
                              <spring:bind path="dietaRecomendadaForm.observacionesMartes1">
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                    <input type="text" name="observacionesMartes1" id="observacionesMartes1" class="form-control"
                                    placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="${status.value}" tabindex="12"/>
                                </div>
                              </spring:bind>
                         </div>
                    </div>
                    <%-- Segundo Alimento--%>
                    <div class="form-group">
                                            <div>
                                                <spring:bind path="dietaRecomendadaForm.nombreAlimentoMartes2">
                                                    <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                                    <form:select path="nombreAlimentoMartes2" cssClass="form-control">
                                                    	<form:option value="NONE" label="--- Seleccione ---"/>
                                                        <form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
                                                    </form:select>
                                                </spring:bind>
                                                <label for="nombreAlimentoMartes2" generated="true" class="error"></label>
                                                <form:errors path="nombreAlimentoMartes2" cssClass="help-block"/>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                <spring:bind path="dietaRecomendadaForm.cantidadMartes2">
                                                    <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                                    <input type="text" name="cantidadMartes2" id="cantidadMartes2" class="form-control"
                                                    placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="${status.value}" maxlength="50"
                                                    tabindex="4">
                                                </spring:bind>
                                            <label for="cantidadMartes2" generated="true" class="error"></label>
                                            <form:errors path="cantidadMartes2" cssClass="help-block"/>
                                            </div>
                                             <div>
                                                  <spring:bind path="dietaRecomendadaForm.observacionesMartes2">
                                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                        <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                                        <input type="text" name="observacionesMartes2" id="observacionesMartes2" class="form-control"
                                                        placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="${status.value}" tabindex="12"/>
                                                    </div>
                                                  </spring:bind>
                                             </div>
                                        </div>
        </div>
        <%-- Fin del tabs2 --%>

        <div id="tabs-3">
                            <spring:bind path="momentoMiercoles1">
                                <input type="hidden" name="momentoMiercoles1" id="momentoMiercoles1" class="form-control" value="DESAYUNO"/>
                            </spring:bind>
                            <spring:bind path="diaDietaMiercoles">
                                <input type="hidden" name="diaDietaMiercoles" id="diaDietaMiercoles" class="form-control" value="MIERCOLES"/>
                            </spring:bind>
                            <div class="form-group">
                                <div>
                                    <spring:bind path="dietaRecomendadaForm.nombreAlimentoMiercoles1">
                                        <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                        <form:select path="nombreAlimentoMiercoles1" cssClass="form-control">
                                        	<form:option value="NONE" label="--- Seleccione ---"/>
                                            <form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
                                        </form:select>
                                    </spring:bind>
                                    <label for="nombreAlimentoMiercoles1" generated="true" class="error"></label>
                                    <form:errors path="nombreAlimentoMiercoles1" cssClass="help-block"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    <spring:bind path="dietaRecomendadaForm.cantidadMiercoles1">
                                        <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                        <input type="text" name="cantidadMiercoles1" id="cantidadMiercoles1" class="form-control"
                                        placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="${status.value}" maxlength="50"
                                        tabindex="4">
                                    </spring:bind>
                                <label for="cantidadMiercoles1" generated="true" class="error"></label>
                                <form:errors path="cantidadMiercoles1" cssClass="help-block"/>
                                </div>
                                 <div>
                                      <spring:bind path="dietaRecomendadaForm.observacionesMiercoles1">
                                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                            <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                            <input type="text" name="observacionesMiercoles1" id="observacionesMiercoles1" class="form-control"
                                            placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="${status.value}" tabindex="12"/>
                                        </div>
                                      </spring:bind>
                                 </div>
                            </div>
                            <%-- Segundo Alimento--%>
                            <div class="form-group">
                                                            <div>
                                                                <spring:bind path="dietaRecomendadaForm.nombreAlimentoMiercoles2">
                                                                    <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                                                    <form:select path="nombreAlimentoMiercoles2" cssClass="form-control">
                                                                    	<form:option value="NONE" label="--- Seleccione ---"/>
                                                                        <form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
                                                                    </form:select>
                                                                </spring:bind>
                                                                <label for="nombreAlimentoMiercoles2" generated="true" class="error"></label>
                                                                <form:errors path="nombreAlimentoMiercoles2" cssClass="help-block"/>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                <spring:bind path="dietaRecomendadaForm.cantidadMiercoles2">
                                                                    <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                                                    <input type="text" name="cantidadMiercoles2" id="cantidadMiercoles2" class="form-control"
                                                                    placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="${status.value}" maxlength="50"
                                                                    tabindex="4">
                                                                </spring:bind>
                                                            <label for="cantidadMiercoles2" generated="true" class="error"></label>
                                                            <form:errors path="cantidadMiercoles2" cssClass="help-block"/>
                                                            </div>
                                                             <div>
                                                                  <spring:bind path="dietaRecomendadaForm.observacionesMiercoles2">
                                                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                        <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                                                        <input type="text" name="observacionesMiercoles2" id="observacionesMiercoles2" class="form-control"
                                                                        placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="${status.value}" tabindex="12"/>
                                                                    </div>
                                                                  </spring:bind>
                                                             </div>
                                                        </div>
                </div>
                <%-- Fin del tabs3 --%>

        <div id="tabs-4">
                            <spring:bind path="momentoJueves1">
                                <input type="hidden" name="momentoJueves1" id="momentoJueves1" class="form-control" value="DESAYUNO"/>
                            </spring:bind>
                            <spring:bind path="diaDietaJueves">
                                <input type="hidden" name="diaDietaJueves" id="diaDietaJueves" class="form-control" value="JUEVES"/>
                            </spring:bind>
                            <div class="form-group">
                                <div>
                                    <spring:bind path="dietaRecomendadaForm.nombreAlimentoJueves1">
                                        <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                        <form:select path="nombreAlimentoJueves1" cssClass="form-control">
                                        	<form:option value="NONE" label="--- Seleccione ---"/>
                                            <form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
                                        </form:select>
                                    </spring:bind>
                                    <label for="nombreAlimentoJueves1" generated="true" class="error"></label>
                                    <form:errors path="nombreAlimentoJueves1" cssClass="help-block"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    <spring:bind path="dietaRecomendadaForm.cantidadJueves1">
                                        <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                        <input type="text" name="cantidadJueves1" id="cantidadJueves1" class="form-control"
                                        placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="${status.value}" maxlength="50"
                                        tabindex="4">
                                    </spring:bind>
                                <label for="cantidadJueves1" generated="true" class="error"></label>
                                <form:errors path="cantidadJueves1" cssClass="help-block"/>
                                </div>
                                 <div>
                                      <spring:bind path="dietaRecomendadaForm.observacionesJueves1">
                                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                            <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                            <input type="text" name="observacionesJueves1" id="observacionesJueves1" class="form-control"
                                            placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="${status.value}" tabindex="12"/>
                                        </div>
                                      </spring:bind>
                                 </div>
                            </div>
                            <%-- Segundo Alimento--%>
                            <div class="form-group">
                                                            <div>
                                                                <spring:bind path="dietaRecomendadaForm.nombreAlimentoJueves2">
                                                                    <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                                                    <form:select path="nombreAlimentoJueves2" cssClass="form-control">
                                                                    	<form:option value="NONE" label="--- Seleccione ---"/>
                                                                        <form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
                                                                    </form:select>
                                                                </spring:bind>
                                                                <label for="nombreAlimentoJueves2" generated="true" class="error"></label>
                                                                <form:errors path="nombreAlimentoJueves2" cssClass="help-block"/>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                <spring:bind path="dietaRecomendadaForm.cantidadJueves2">
                                                                    <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                                                    <input type="text" name="cantidadJueves2" id="cantidadJueves2" class="form-control"
                                                                    placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="${status.value}" maxlength="50"
                                                                    tabindex="4">
                                                                </spring:bind>
                                                            <label for="cantidadJueves2" generated="true" class="error"></label>
                                                            <form:errors path="cantidadJueves2" cssClass="help-block"/>
                                                            </div>
                                                             <div>
                                                                  <spring:bind path="dietaRecomendadaForm.observacionesJueves2">
                                                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                        <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                                                        <input type="text" name="observacionesJueves2" id="observacionesJueves2" class="form-control"
                                                                        placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="${status.value}" tabindex="12"/>
                                                                    </div>
                                                                  </spring:bind>
                                                             </div>
                                                        </div>
                </div>
                <%-- Fin del tabs4 --%>

        <div id="tabs-5">
                            <spring:bind path="momentoViernes1">
                                <input type="hidden" name="momentoViernes1" id="momentoViernes1" class="form-control" value="DESAYUNO"/>
                            </spring:bind>
                            <spring:bind path="diaDietaViernes">
                                <input type="hidden" name="diaDietaViernes" id="diaDietaViernes" class="form-control" value="VIERNES"/>
                            </spring:bind>
                            <div class="form-group">
                                <div>
                                    <spring:bind path="dietaRecomendadaForm.nombreAlimentoViernes1">
                                        <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                        <form:select path="nombreAlimentoViernes1" cssClass="form-control">
                                        	<form:option value="NONE" label="--- Seleccione ---"/>
                                            <form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
                                        </form:select>
                                    </spring:bind>
                                    <label for="nombreAlimentoViernes1" generated="true" class="error"></label>
                                    <form:errors path="nombreAlimentoViernes1" cssClass="help-block"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    <spring:bind path="dietaRecomendadaForm.cantidadViernes1">
                                        <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                        <input type="text" name="cantidadViernes1" id="cantidadViernes1" class="form-control"
                                        placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="${status.value}" maxlength="50"
                                        tabindex="4">
                                    </spring:bind>
                                <label for="cantidadViernes1" generated="true" class="error"></label>
                                <form:errors path="cantidadViernes1" cssClass="help-block"/>
                                </div>
                                 <div>
                                      <spring:bind path="dietaRecomendadaForm.observacionesViernes1">
                                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                            <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                            <input type="text" name="observacionesViernes1" id="observacionesViernes1" class="form-control"
                                            placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="${status.value}" tabindex="12"/>
                                        </div>
                                      </spring:bind>
                                 </div>
                            </div>
                            <%-- Segundo Alimento--%>
                            <div class="form-group">
                                                            <div>
                                                                <spring:bind path="dietaRecomendadaForm.nombreAlimentoViernes2">
                                                                    <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                                                    <form:select path="nombreAlimentoViernes2" cssClass="form-control">
                                                                    	<form:option value="NONE" label="--- Seleccione ---"/>
                                                                        <form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
                                                                    </form:select>
                                                                </spring:bind>
                                                                <label for="nombreAlimentoViernes2" generated="true" class="error"></label>
                                                                <form:errors path="nombreAlimentoViernes2" cssClass="help-block"/>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                <spring:bind path="dietaRecomendadaForm.cantidadViernes2">
                                                                    <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                                                    <input type="text" name="cantidadViernes2" id="cantidadViernes2" class="form-control"
                                                                    placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="${status.value}" maxlength="50"
                                                                    tabindex="4">
                                                                </spring:bind>
                                                            <label for="cantidadViernes2" generated="true" class="error"></label>
                                                            <form:errors path="cantidadViernes2" cssClass="help-block"/>
                                                            </div>
                                                             <div>
                                                                  <spring:bind path="dietaRecomendadaForm.observacionesViernes2">
                                                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                        <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                                                        <input type="text" name="observacionesViernes2" id="observacionesViernes2" class="form-control"
                                                                        placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="${status.value}" tabindex="12"/>
                                                                    </div>
                                                                  </spring:bind>
                                                             </div>
                                                        </div>
                </div>
                <%-- Fin del tabs5 --%>

        <div id="tabs-6">
                            <spring:bind path="momentoSabado1">
                                <input type="hidden" name="momentoSabado1" id="momentoSabado1" class="form-control" value="DESAYUNO"/>
                            </spring:bind>
                            <spring:bind path="diaDietaSabado">
                                <input type="hidden" name="diaDietaSabado" id="diaDietaSabado" class="form-control" value="SABADO"/>
                            </spring:bind>
                            <div class="form-group">
                                <div>
                                    <spring:bind path="dietaRecomendadaForm.nombreAlimentoSabado1">
                                        <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                        <form:select path="nombreAlimentoSabado1" cssClass="form-control">
                                        	<form:option value="NONE" label="--- Seleccione ---"/>
                                            <form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
                                        </form:select>
                                    </spring:bind>
                                    <label for="nombreAlimentoSabado1" generated="true" class="error"></label>
                                    <form:errors path="nombreAlimentoSabado1" cssClass="help-block"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    <spring:bind path="dietaRecomendadaForm.cantidadSabado1">
                                        <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                        <input type="text" name="cantidadSabado1" id="cantidadSabado1" class="form-control"
                                        placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="${status.value}" maxlength="50"
                                        tabindex="4">
                                    </spring:bind>
                                <label for="cantidadSabado1" generated="true" class="error"></label>
                                <form:errors path="cantidadSabado1" cssClass="help-block"/>
                                </div>
                                 <div>
                                      <spring:bind path="dietaRecomendadaForm.observacionesSabado1">
                                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                            <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                            <input type="text" name="observacionesSabado1" id="observacionesSabado1" class="form-control"
                                            placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="${status.value}" tabindex="12"/>
                                        </div>
                                      </spring:bind>
                                 </div>
                            </div>
                            <%-- Segundo Alimento--%>
                            <div class="form-group">
                                                            <div>
                                                                <spring:bind path="dietaRecomendadaForm.nombreAlimentoSabado2">
                                                                    <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                                                    <form:select path="nombreAlimentoSabado2" cssClass="form-control">
                                                                    	<form:option value="NONE" label="--- Seleccione ---"/>
                                                                        <form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
                                                                    </form:select>
                                                                </spring:bind>
                                                                <label for="nombreAlimentoSabado2" generated="true" class="error"></label>
                                                                <form:errors path="nombreAlimentoSabado2" cssClass="help-block"/>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                <spring:bind path="dietaRecomendadaForm.cantidadSabado2">
                                                                    <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                                                    <input type="text" name="cantidadSabado2" id="cantidadSabado2" class="form-control"
                                                                    placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="${status.value}" maxlength="50"
                                                                    tabindex="4">
                                                                </spring:bind>
                                                            <label for="cantidadSabado2" generated="true" class="error"></label>
                                                            <form:errors path="cantidadSabado2" cssClass="help-block"/>
                                                            </div>
                                                             <div>
                                                                  <spring:bind path="dietaRecomendadaForm.observacionesSabado2">
                                                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                        <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                                                        <input type="text" name="observacionesSabado2" id="observacionesSabado2" class="form-control"
                                                                        placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="${status.value}" tabindex="12"/>
                                                                    </div>
                                                                  </spring:bind>
                                                             </div>
                                                        </div>
                </div>
                <%-- Fin del tabs6 --%>
        <%-- Fin del div tabs --%>
    </div>
            <br>
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

<c:set var="scripts" scope="request">
	<script type="text/javascript">
		// This is here so we can exclude the selectAll call when roles is hidden

		function onFormSubmit(theForm) {
			return validateUser(theForm);
		};
	</script>
	<script type="text/javascript">
		 $('button[name="cancel"]').click(function(e){
				e.preventDefault();
				window.location.href = "http://localhost:8080/admin/endocrinologoList";
			});
	</script>
	</script>

    <script>
        $(function() {
            $('#dia').blur(function(){
                var txtVal =  $('#dia').val();
                if(isDate(txtVal)){
                    ;
                }
                else{
                    $(this).parent().find(".error").text("La fecha ingresada es incorrecta");
                    document.miFormulario.dia.value = "";
                    }
            });

        function isDate(txtDate)
        {
            var currVal = txtDate;
            if(currVal == '')
                return false;

            var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/; //Declare Regex
            var dtArray = currVal.match(rxDatePattern); // is format OK?

            if (dtArray == null)
                return false;

            //Checks for mm/dd/yyyy format.
            dtMonth = dtArray[3];
            dtDay= dtArray[1];
            dtYear = dtArray[5];

            if (dtMonth < 1 || dtMonth > 12)
                return false;
            else if (dtDay < 1 || dtDay> 31)
                return false;
            else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31)
                return false;
            else if (dtMonth == 2)
            {
                var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
                if (dtDay> 29 || (dtDay ==29 && !isleap))
                        return false;
            }
            return true;
        }

        });
    </script>

</c:set>

<v:javascript formName="endocrinologoForm" staticJavascript="false" />
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>