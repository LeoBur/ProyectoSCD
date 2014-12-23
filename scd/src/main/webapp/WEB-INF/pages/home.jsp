<%@ include file="/common/taglibs.jsp"%>

<head>
    <!--<title><fmt:message key="home.title"/></title>-->
    <meta name="menu" content="Home"/>
</head>
<body class="home">

<!--<h2><fmt:message key="home.heading"/></h2>
<p><fmt:message key="home.message"/></p>-->

    <% if (request.isUserInRole("ROLE_ADMIN")) { %>
        <c:redirect url="/admin/endocrinologoList"/>
    <% } %>

    <% if (request.isUserInRole("ROLE_ENDO")) { %>
        <c:redirect url="/endos/pacienteList"/>
    <% } %>

    <%--
    <% if (request.isUserInRole("ROLE_NUTRI")) { %>
        <c:redirect url="/nutricionista/"/>
    <% } %>

    <% if (request.isUserInRole("ROLE_PTRAI")) { %>
        <c:redirect url="/endos/newPaciente"/>
    <% } %>
    --%>

    <% if (request.isUserInRole("ROLE_USER")) { %>
            <c:redirect url="/paciente/registrar"/>
    <% } %>
</body>
