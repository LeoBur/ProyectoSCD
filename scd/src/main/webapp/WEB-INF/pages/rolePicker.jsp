<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<head>
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
						<div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
							<appfuse:label styleClass="control-label" key="user.medicine.title" />
							<div cssClass="form-control">
								<form:select id="roles"
								name="roles" class="form-control"
								path="roles" value="${status.value}" tabindex="14">
								<form:option value="NONE" label="--- Seleccione Rol ---"/>
								<form:options items="${roleList}" itemValue="name" itemLabel="name"></form:options>
							</form:select>
							<form:errors path="roles" cssClass="help-block" />
						</div>
					</div>
				</spring:bind>
			</div>
			<div>
				<button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false" tabindex="24">
					<i class="icon-ok icon-white"></i>
					<fmt:message key="button.save" />
				</button>
			</div>
		</div>
	</form:form>
</div>
</div>
</div>
</head>