<%@ include file="/common/taglibs.jsp"%>




<menu:useMenuDisplayer name="Velocity" config="navbarMenu.vm" permissions="rolesAdapter">
<div class="contenido">
<div class="collapse navbar-collapse" id="navbar">
<ul class="nav navbar-nav">
    <c:if test="${empty pageContext.request.remoteUser}">
        <li class="active">
            <a href="<c:url value="/login"/>"><fmt:message key="login.title"/></a>
        </li>
    </c:if>
    <%--<menu:displayMenu name="Home"/> --%>
    <%--<menu:displayMenu name="UserMenu"/> --%>
    <%--<menu:displayMenu name="AdminMenu"/> --%>
    
    

    
    <menu:displayMenu name="Paciente"/>
    <menu:displayMenu name="Ajuste"/>
    
    
    <menu:displayMenu name="PacienteMenu"/>
    <menu:displayMenu name="Consulta"/>
    <menu:displayMenu name="Historial"/>
    <menu:displayMenu name="Dato"/>
    
    
        
    
    
    <%--<menu:displayMenu name="Endocrinologist"/>  --%>
    <%--<menu:displayMenu name="Especialist"/> --%>
    <%--<menu:displayMenu name="Treatment"/> --%>
    
    
    <%--<menu:displayMenu name="Food"/>
    <menu:displayMenu name="Symptom"/>
    <menu:displayMenu name="Diet"/>
    <menu:displayMenu name="Medicine"/>
    <menu:displayMenu name="Activity"/>  --%>
    <menu:displayMenu name="Logout"/>
    
    

</ul>
</div>
</div>
</menu:useMenuDisplayer>
