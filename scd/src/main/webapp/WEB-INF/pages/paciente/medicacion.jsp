<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>


<div class="container-fluid">
    <div class="col-md-10">
      <div class="row">
        <div class="col-md-10">
            <h3>Medicamentos recetados ${fecha}</h3>
        </div>
      </div>
      <div class="row">
        <div class="col-md-10">
             <display:table name="prescripcionesList" cellspacing="0" cellpadding="0" requestURI=""
                           defaultsort="1" id="prescripcion" pagesize="20"
                           class="table table-condensed table-striped table-hover" export="true">
                <display:caption media="html"><h3>Medicamentos recetados</h3></display:caption>
                <display:caption media="pdf">Medicamentos recetados</display:caption>
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