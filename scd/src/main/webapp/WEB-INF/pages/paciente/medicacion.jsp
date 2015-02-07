<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>


<div class="container-fluid">
    <div class="col-md-10">
      <div class="row">
        <div class="col-md-2">
            <h2>Medicacion recetada</h2>
            <h3>${fecha}</h3>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6">
             <display:table name="prescripcionesList" cellspacing="0" cellpadding="0" requestURI=""
                           defaultsort="1" id="prescripcion" pagesize="25"
                           class="table table-condensed table-striped table-hover" export="true">
                <display:column property="medicamento.nombreComercial" escapeXml="true" sortable="true" titleKey="Nombre Comercial"
                                style="width: 25%"/>
                <display:column property="medicamento.nombreGenerico" escapeXml="true" sortable="true" titleKey="Nombre Generico"
                                        style="width: 25%"/>
                <display:column property="medicamento.presentacion" escapeXml="true" sortable="true" titleKey="Presentacion"
                                style="width: 25%"/>
                <display:column property="descripcion" escapeXml="true" sortable="true" titleKey="Receta"
                                        style="width: 25%"/>
                <display:setProperty name="export.pdf" value="true" />
                <display:setProperty name="export.excel" value="false" />
                <display:setProperty name="export.xml" value="false" />
                <display:setProperty name="export.csv" value="false" />
             </display:table>
        </div>
      </div>
    </div>
</div>