<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>



<div class="col-sm-10">
    <h2><fmt:message key="userList.ultimoPeso"/></h2>

    

     
     <display:table name="userList" cellspacing="0" cellpadding="0" requestURI=""
                   defaultsort="1" id="users" pagesize="25" class="table table-condensed table-striped table-hover" export="true">
        <display:column property="fullName" escapeXml="true" sortable="true" titleKey="active.peso"
                        style="width: 34%"/>
     	           
     	<display:column property="username" escapeXml="true" sortable="true" titleKey="active.peso.fecha" style="width: 25%"
                        url="/userform?from=list" paramId="id" paramProperty="id"/>
        <%--<display:column property="fullName" escapeXml="true" sortable="true" titleKey="active.peso.altura"
                        style="width: 34%"/>  --%>
        
     </display:table>
     
</div>