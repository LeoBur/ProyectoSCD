<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="userList.title"/></title>

    <!--Load the AJAX API-->
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">

          // Load the Visualization API and the piechart package.
          google.load('visualization', '1.0', {'packages':['corechart']});

          // Set a callback to run when the Google Visualization API is loaded.
          google.setOnLoadCallback(drawChart);

          // Callback that creates and populates a data table,
          // instantiates the pie chart, passes in the data and
          // draws it.
          function drawChart() {

            var rows = eval('('+'${data}'+')');

            // Create the data table.
            var data = new google.visualization.DataTable();
                  data.addColumn('string', 'Fecha');
                  data.addColumn('number', 'Mediciones');

                  data.addRows(rows);

                  var options = {
                    width: 1000,
                    height: 563,
                    hAxis: {
                      title: 'Time'
                    },
                    vAxis: {
                      title: 'Popularity'
                    },
                    backgroundColor: '#f1f8e9'
                  };

            // Instantiate and draw our chart, passing in some options.
            var chart = new google.visualization.LineChart(document.getElementById('ex1'));
            chart.draw(data, options);


          }
        </script>
</head>

<div class="container-fluid">
<meta name="menu" content="UserMenu"/>
    <div class="col-sm-10">
        <h2><fmt:message key="userList.ultimaMedicion"/></h2>


        <div id="ex1"></div>

    <%--
      <%
         <display:table name="userList" cellspacing="0" cellpadding="0" requestURI=""
                       defaultsort="1" id="users" pagesize="25" class="table table-condensed table-striped table-hover" export="true">
            <display:column property="username" escapeXml="true" sortable="true" titleKey="active.medicacion" style="width: 25%"
                            url="/userform?from=list" paramId="id" paramProperty="id"/>
            <display:column property="fullName" escapeXml="true" sortable="true" titleKey="active.unidad"
                            style="width: 34%"/>
            <display:column property="fullName" escapeXml="true" sortable="true" titleKey="active.fecha_Medicion"
                            style="width: 34%"/>
            <display:column property="fullName" escapeXml="true" sortable="true" titleKey="active.observacion"
                            style="width: 34%"/>
         </display:table>
      %>
    --%>
    </div>
</div>