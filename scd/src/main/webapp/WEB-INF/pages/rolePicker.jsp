<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<head>
    <link rel="stylesheet" href="/styles/style.css">
    <link rel="stylesheet" type="text/css" href="${base}/styles/style.css"/>
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.1/css/font-awesome.css" rel="stylesheet">

    <title><fmt:message key="userProfile.title"/></title>
</head>
<div class="fluid-container">
    <div class="col-md-2">
        <h3>Seleccionar Rol</h3>
    </div>
    <div class="col-md-8">
        <div class="form-group">
                <form:form commandName="homeForm" method="post" action="rolePicker" autocomplete="off" id="formulario" modelAttribute="homeForm"
                    cssClass="well" onsubmit="return validateUser(this)">
                  <div class="row">
                    <div>
                        <spring:bind path="roles">
                            <div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                <appfuse:label styleClass="control-label" key="user.roles" />
                                <div cssClass="form-control">
                                    <form:select id="roles" name="roles" class="form-control"
                                      path="roles" value="${status.value}" tabindex="1">
                                      <form:option value="NONE" label="--- Seleccione Rol ---"/>
                                      <form:options items="${roleList}" itemValue="name" itemLabel="description"></form:options>
                                    </form:select>
                                    <form:errors path="roles" cssClass="help-block" />
                                </div>
                            </div>
                        </spring:bind>
                    </div>
                    <br>
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false" tabindex="2">
                            <i class="icon-ok icon-white"></i>
                            <fmt:message key="button.save" />
                        </button>
                    </div>
                  </div>
                </form:form>
        </div>
    </div>
</div>