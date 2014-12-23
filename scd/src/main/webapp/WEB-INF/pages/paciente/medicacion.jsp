<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>



<div class="col-sm-10">
    <h2><fmt:message key="userList.medicacion"/></h2>

    

     
     <display:table name="prescripcionList" cellspacing="0" cellpadding="0" requestURI=""
                   defaultsort="1" id="prescripcionList" pagesize="25" class="table table-condensed table-striped table-hover" export="true">
     	<display:column property="medicamento.nombreComercial" escapeXml="true" sortable="true" titleKey="active.medicacion" style="width: 25%"
                        url="/userform?from=list" paramId="id" paramProperty="id"/>
        <display:column property="descripcion" escapeXml="true" sortable="true" titleKey="active.observacion"
                        style="width: 34%"/>
     	
     </display:table>
     
</div>