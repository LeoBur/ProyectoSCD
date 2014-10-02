<%@ include file="/common/taglibs.jsp"%>
<%@ page import = "java.util.*"%>
<% int a = 5; %>
<head>
    <title><fmt:message key="userPaciente.title"/></title>
    <meta name="menu" content="UserMenu"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <link rel="stylesheet" href="/styles/style.css">
    	  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    	  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    	  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
    	  <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js" type="text/javascript"></script>
    	  <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/localization/messages_es.js" type="text/javascript"></script>
    	  <script src="http://jquery.bassistance.de/validate/additional-methods.js" type="text/javascript"></script>
    	  <script src="/scripts/validator.js" type="text/javascript"></script>


</head>
<body>


<c:set var="delObject" scope="request"><fmt:message key="userList.user"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="signup.title"/></h2>
    <c:choose>
        <c:when test="${param.from == 'list'}">
            <p><fmt:message key="userProfile.admin.message"/></p>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="userProfile.message"/></p>
        </c:otherwise>
    </c:choose>
</div>


<div class="col-sm-7">
    <spring:bind path="pacienteForm.*">
        <c:if test="${not empty status.errorMessages}">
            <div class="alert alert-danger alert-dismissable">
                <a href="#" data-dismiss="alert" class="close">&times;</a>
                <c:forEach var="error" items="${status.errorMessages}">
                    <c:out value="${error}" escapeXml="false"/><br/>
                </c:forEach>
            </div>
        </c:if>
    </spring:bind>

    <form:form commandName="pacienteForm" method="post" action="registrar" autocomplete="off" id="formulario" modelAttribute="pacienteForm"
               cssClass="well" onsubmit="return validateUser(this)">
        	<form:hidden path="username"/>
        
        
		        <div class="form-group">
	                <appfuse:label styleClass="control-label" key="user.paciente.fecha"/>	                
		            <form:input type="date" cssClass="form-control" path="fechaHora" id="fechaHora" size="12" onblur="dateValidate(this)"/>
		        </div>   
		        
		        
		        <div class="form-group">
		        	<%-- <spring:bind path="paciente.medicion">
                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                    </spring:bind> --%>	
                    
		                <appfuse:label styleClass="control-label" key="user.paciente.medicion"/>
		                <form:input cssClass="form-control" path="medicion" id="medicion"/>
		                <label for="medicion" generated="true" class="error"></label>
                        <form:errors path="medicion" cssClass="help-block"/>
                        <form:errors path="medicion" cssClass="help-block"/>
		                <%-- <form:errors path="medicion" cssClass="help-block"/> --%>
		        </div>
		        
		        <div class="form-group">
		                <appfuse:label styleClass="control-label" key="user.paciente.peso"/>
		                <form:input cssClass="form-control" path="peso" id="peso"/>
		        </div>
		        <%-- 
		        <div class="form-group">
		        		<appfuse:label styleClass="control-label" key="user.food.title"></appfuse:label>
		                <form:select path="medicamento">
		                	<form:options items="${medicamentoList}"  itemLabel="nombreComercial"/>
						</form:select>		                
		        </div>
		         --%> 
		        <div class="form-group">
		        	<appfuse:label styleClass="control-label" key="user.medicine.title"/>
		        	<form:select cssClass="form-control" path="medicamento">
		        		<form:options items="${medicamentoList}" itemLabel="nombreComercial"></form:options>
		        	</form:select>
		        	<appfuse:label styleClass="control-label" key="user.oservacion.title"></appfuse:label>
		        	<form:textarea cssClass="form-control" path="observacionesMedicamento"/>
		        </div> 
		        
		        <div class="form-group">
		        	<appfuse:label styleClass="control-label" key="user.symptom.title"/>
		        	<form:select cssClass="form-control" path="sintoma">
		        		
		        		<form:options items="${sintomaList}"  itemLabel="nombre"></form:options>
		        	</form:select>
		        	<appfuse:label styleClass="control-label" key="user.oservacion.title"></appfuse:label>
		        	<form:textarea cssClass="form-control" path="observacionesSintoma"/>
		        </div> 
		        
		        <div class="form-group">
		        	<appfuse:label styleClass="control-label" key="active.momentodia"/>
		        	<form:select cssClass="form-control" path="momento">
		        		<form:option value="DESAYUNO" label="Desayuno"/>
		        		<form:option value="MEDIA_MANIANA" label="Media Ma�ana" selected="selected"></form:option>
		        		<form:option value="ALMUERZO" label="Almuerzo"/>
		        		<form:option value="MEDIA_TARDE" label="Media Tarde"/>
		        		<form:option value="CENA" label="Cena"/>
		        		<form:option value="ANTES_DE_ACOSTARSE" label="Antes de Acostarse"/>
		        	</form:select>
		        </div>
		        
		        <display:table name="<%= a %>" cellspacing="0" cellpadding="0" requestURI=""
                   defaultsort="1" id="users" pagesize="25" class="table table-condensed table-striped table-hover" export="false">
				        
				        <display:column titleKey="user.paciente.alimentoIng" sortable="false">
				            <form:select path="${pacienteForm.comidas[0].alimento}" cssClass="form-control">
				            	<form:options items="${alimentoList}" itemLabel="idAlimento"/>
				            </form:select>
				            <form:select path="${pacienteForm.comidas[1].alimento}" cssClass="form-control">
				            	<form:options items="${alimentoList}" itemLabel="idAlimento"/>
				            </form:select>
				            <form:select path="${pacienteForm.comidas[2].alimento}" cssClass="form-control">
				            	<form:options items="${alimentoList}" itemLabel="idAlimento"/>
				            </form:select>
				            <form:select path="${pacienteForm.comidas[3].alimento}" cssClass="form-control">
				            	<form:options items="${alimentoList}" itemLabel="idAlimento"/>
				            </form:select>
				            <form:select path="${pacienteForm.comidas[4].alimento}" cssClass="form-control">
				            	<form:options items="${alimentoList}" itemLabel="idAlimento"/>
				            </form:select>
				        </display:column>
				        
				        <display:column titleKey="user.food.cant" sortable="false">
				            <form:input path="${pacienteForm.comidas[0].cantidad}" cssClass="form-control"/>
				            <form:input path="${pacienteForm.comidas[1].cantidad}" cssClass="form-control"/>
				            <form:input path="${pacienteForm.comidas[2].cantidad}" cssClass="form-control"/>
				            <form:input path="${pacienteForm.comidas[3].cantidad}" cssClass="form-control"/>
				            <form:input path="${pacienteForm.comidas[4].cantidad}" cssClass="form-control"/>
				        </display:column>
				        <display:column titleKey="user.oservacion.title" sortable="false">
				            <form:input path="${pacienteForm.comidas[0].observaciones}" cssClass="form-control"/>
				            <form:input path="${pacienteForm.comidas[1].observaciones}" cssClass="form-control"/>
				            <form:input path="${pacienteForm.comidas[2].observaciones}" cssClass="form-control"/>
				            <form:input path="${pacienteForm.comidas[3].observaciones}" cssClass="form-control"/>
				            <form:input path="${pacienteForm.comidas[4].observaciones}" cssClass="form-control"/>
				        </display:column>
				       
								        
				    </display:table>    
						        
		        <%--
		         <table>
		        	<tr>
		        		<th>Alimento</th>
		        		<th>Cantidad</th>
		        		<th>Observaciones</th>
		        	</tr>
		        	<tr>
		        		<td><form:input path=""/></td>
		        		<td><form:input path=""/></td>
		        		<td><form:textarea path=""/></td>
		        	</tr>
		        	<tr>
		        		<td><form:input path=""/></td>
		        		<td><form:input path=""/></td>
						<td><form:textarea path=""/></td>
		        		
		        	</tr>
		        	<tr>
		        		<td><form:input path=""/></td>
		        		<td><form:input path=""/></td>
		        		<td><form:textarea path=""/></td>
		        	</tr>
		        	<tr>
		        		<td><form:input path=""/></td>
		        		<td><form:input path=""/></td>
		        		<td><form:textarea path=""/></td>
		        	</tr>
		        	<tr>
		        		<td><form:input path=""/></td>
		        		<td><form:input path=""/></td>
		        		<td><form:textarea path=""/></td>
		        	</tr>
		        </table>	    --%>     
		
		        <div class="form-group">
		            <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
		                <i class="icon-ok icon-white"></i> <fmt:message key="button.registrer"/>
		            </button>
		
		            <c:if test="${param.from == 'list' and param.method != 'Add'}">
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

</body>
</html>




<%--  <div>
		            <legend class="accordion-heading">
		                <a data-toggle="collapse" href="#collapse-address"><fmt:message key="user.paciente.alimentoIng"/></a>
		            </legend>
		            <div id="collapse-address" class="accordion-body collapse">
		                <div class="col-sm-10">
		    				
		        		<display:table name="applicationScope.userNames" id="user" cellspacing="0" cellpadding="0"
		                		   defaultsort="1" class="table table-condensed table-striped table-hover" pagesize="50" requestURI="">
		        			<display:column property="username" escapeXml="true" style="width: 30%" titleKey="user.username"
		                        sortable="true"/>
		        			<display:column titleKey="activeUsers.fullName" sortable="true">
		            			<c:out value="${user.firstName} ${user.lastName}" escapeXml="true"/>
		            			<c:if test="${not empty user.email}">
		                			<a href="mailto:<c:out value="${user.email}"/>">
		                    			<img src="<c:url value="/images/iconEmail.gif"/>"
		                        	 alt="<fmt:message key="icon.email"/>" class="icon"/></a>
		            			</c:if>
		        			</display:column>
		
		        			<display:setProperty name="paging.banner.item_name" value="user"/>
		        			<display:setProperty name="paging.banner.items_name" value="users"/>
		    			</display:table> 
		    			<display:table name="applicationScope.userNames" id="user" cellspacing="0" cellpadding="0"
		                		   defaultsort="1" class="table table-condensed table-striped table-hover" pagesize="50" requestURI="">
		                	<display:column property="username" escapeXml="true" style="width: 30%" titleKey="user.paciente.momentoDia"
		                        sortable="true"/>
		                    <display:column titleKey="activePaiente.comidas" sortable="true">
		                    	<c:out value="${user.firstName} ${user.lastName}" escapeXml="true"/>
		                    	<c:if test="${not empty user.email}">
		                			<a href="mailto:<c:out value="${user.email}"/>">
		                    			<img src="<c:url value="/images/iconEmail.gif"/>"
		                        	 alt="<fmt:message key="icon.email"/>" class="icon"/></a>
		            			</c:if>
		            				<a href="editar.html">Editar</a>
		                    </display:column>
		    			</display:table>
						</div>
		            </div>
		        </div> --%>
		        
		        
		        <%-- <div class="form-group">
		        	<input type="checkbox" name="cv1" value="1" checked> Opcion 1 <br>
		        	<input type="checkbox" name="cv2" value="2" > Opcion 2 <br>
		        	<input type="checkbox" name="cv3" value="3" > Opcion 3 <br>
		        </div>
		        
		        <div class="form-group">
		        	<form:select path="phoneNumber">
		        		<form:option value="Enfermedod" label="Enfermo"/>
		        		<form:option value="Dolor" label="Dolor de Cabeza" selected="selected"/>
		        	</form:select> 
		        </div>--%>
		        <%-- <form:select path="variable a la que va enlazada" items="${iterar la lista }"/> --%>
		        
		        
		        
		        <%-- <div>
		            <legend class="accordion-heading">
		                <a data-toggle="collapse" href="#collapse-address"><fmt:message key="user.address.address"/></a>
		            </legend>
		            <div id="collapse-address" class="accordion-body collapse">
		                <div class="form-group">
		                    <appfuse:label styleClass="control-label" key="user.address.address"/>
		                    <form:input cssClass="form-control" path="address.address" id="address.address"/>
		                </div>
		                <div class="row">
		                    <div class="col-sm-7 form-group">
		                        <appfuse:label styleClass="control-label" key="user.address.city"/>
		                        <form:input cssClass="form-control" path="address.city" id="address.city"/>
		                    </div>
		                    <div class="col-sm-2 form-group">
		                        <appfuse:label styleClass="control-label" key="user.address.province"/>
		                        <form:input cssClass="form-control" path="address.province" id="address.province"/>
		                    </div>
		                    <div class="col-sm-3 form-group">
		                        <appfuse:label styleClass="control-label" key="user.address.postalCode"/>
		                        <form:input cssClass="form-control" path="address.postalCode" id="address.postalCode"/>
		                    </div>
		                </div>
		                <div class="form-group">
		                    <appfuse:label styleClass="control-label" key="user.address.country"/>
		                    <appfuse:country name="address.country" prompt="" default="${user.address.country}"/>
		                    <appfuse:country/>
		                    
		                </div>
		            </div>
		        </div> --%>
		        
		        
		        <%-- <div class="form-group">
		        	<appfuse:label styleClass="control-label" key="user.medicine.title"/>
		        	<form:select cssClass="form-control" path="phoneNumber">
		        		<form:option value="BayasC" label="Bayaspirina"/>
		        		<form:option value="Plus" label="Cura Plus" selected="selected"/>
		        	</form:select>
		        </div>
		        
		        
		        <div  class="form-group">
		        	<appfuse:label styleClass="control-label" key="user.oservacion.title"/>
		        	<form:textarea cssClass="form-control" path="phoneNumber" />
		        </div>
		        
		        <div class="form-group">
		        	<appfuse:label styleClass="control-label" key="user.symptom.title"/>
		        	<form:select cssClass="form-control" path="phoneNumber">
		        		<form:option value="Enfermedod" label="Enfermo"/>
		        		<form:option value="Dolor" label="Dolor de Cabeza" selected="selected"/>
		        	</form:select>
		        </div>
		        --%>