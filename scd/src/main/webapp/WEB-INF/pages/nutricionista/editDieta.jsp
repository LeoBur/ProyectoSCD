<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="userProfile.title"/></title>

    <link rel="stylesheet" href="/resources/demos/style.css">
    <link rel="stylesheet" href="/styles/style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base}/styles/style.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/master/build/css/bootstrap-datetimepicker.min.css">
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.1/css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen" href="content/pygments-manni.css">


    <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

  	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js" type="text/javascript"></script>
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/localization/messages_es.js" type="text/javascript"></script>
	<script src="http://jquery.bassistance.de/validate/additional-methods.js" type="text/javascript"></script>
	<script src="/scripts/validator.js" type="text/javascript"></script>
    <script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>

    <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.4/moment.min.js"></script>

    <script type="text/javascript" src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/master/src/js/bootstrap-datetimepicker.js"></script>
    <script>
      	$(function() {
        	$( "#tabs" ).tabs();
      	});
    </script>

    <script type="text/javascript">
            $(document).ready(function(){
                        var next = 1;
                        $(".add-more").click(function(e){
                            e.preventDefault();
                            var options = $("#options").val();
                            var optionsMomento = $("#optionsMomentos").val();
                            var addto = "#fieldsLunes" + next;
                            var addRemove = "#fieldsLunes" + (next);
                            next = next + 1;
                            var newIn = '<div id="fieldsLunes'+ next +'"><div class="row"><div class="col-sm-6 form-group"><label for="medicine.title" class="control-label">Nombre del alimento</label><div cssclass="form-control"><select id="nombreAlimentoLunes[]" name="nombreAlimentoLunes[]" class="form-control"><option value="NONE" selected="selected">--- Seleccione ---</option>'+options+'</select></div></div><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Momento Del D\u00EDa</label><span class="required">*</span><div cssclass="form-control"><select id="momentoAlimentoLunes[]" name="momentoAlimentoLunes[]" class="form-control"><option value="NONE" selected="selected">--- Seleccione ---</option>'+optionsMomento+'</select><label for="nombreAlimentoLunes2" generated="true" class="error"></label></div></div></div>   <div class="row"><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Cantidad del Alimento</label><span class="required">*</span><div cssclass="form-control"><input type="text" id="cantidadAlimentoLunes'+ next +'" name="cantidadAlimentoLunes'+ next +'" class="form-control"><label for="cantidadAlimentoLunes'+ next +'" generated="true" class="error"></label></div></div><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Observaciones</label><span class="required">*</span><div cssclass="form-control"><input type="text" id="observacionAlimentoLunes'+ next +'" name="observacionAlimentoLunes'+ next +'" class="form-control"><label for="observacionAlimentoLunes'+ next +'" generated="true" class="error"></label></div></div></div></div>';
                            var newInput = $(newIn);
                            var removeBtn = '<button id="remove' + (next - 1) + '" class="btn btn-danger remove-me" >-</button></div><div id="fieldsLunes'+ (next-1) +'">';
                            var removeButton = $(removeBtn);
                            $(addto).after(newInput);
                            $(addRemove).after(removeButton);
                            $("#fieldsLunes" + next).attr('data-source',$(addto).attr('data-source'));
                            $("#count").val(next);

                                $('.remove-me').click(function(e){
                                    e.preventDefault();
                                    var fieldNum = this.id.substr(this.id.lastIndexOf("e")+1);
                                    var fieldID = "#fieldsLunes" + fieldNum;
                                    $(this).remove();
                                    $(fieldID).remove();
                                });
                        });
            });
    </script>

    <script type="text/javascript">
                $(document).ready(function(){
                            var next = 10;
                            $(".add-more2").click(function(e){
                                e.preventDefault();
                                var options = $("#options").val();
                                var optionsMomento = $("#optionsMomentos").val();
                                var addto = "#fieldsMartes" + next;
                                var addRemove = "#fieldsMartes" + (next);
                                next = next + 1;
                                var newIn = '<div id="fieldsMartes'+ next +'"><div class="row"><div class="col-sm-6 form-group"><label for="medicine.title" class="control-label">Nombre del alimento</label><div cssclass="form-control"><select id="nombreAlimentoMartes[]" name="nombreAlimentoMartes[]" class="form-control"><option value="NONE" selected="selected">--- Seleccione ---</option>'+options+'</select></div></div><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Momento Del D\u00EDa</label><span class="required">*</span><div cssclass="form-control"><select id="momentoAlimentoMartes[]" name="momentoAlimentoMartes[]" class="form-control"><option value="NONE" selected="selected">--- Seleccione ---</option>'+optionsMomento+'</select><label for="nombreAlimentoMartes2" generated="true" class="error"></label></div></div></div>  <div class="row"><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Cantidad del Alimento</label><span class="required">*</span><div cssclass="form-control"><input type="text" id="cantidadAlimentoMartes'+ next +'" name="cantidadAlimentoMartes'+ next +'" class="form-control"><label for="cantidadAlimentoMartes'+ next +'" generated="true" class="error"></label></div></div><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Observaciones</label><span class="required">*</span><div cssclass="form-control"><input type="text" id="observacionAlimentoMartes'+ next +'" name="observacionAlimentoMartes'+ next +'" class="form-control"><label for="observacionAlimentoMartes'+ next +'" generated="true" class="error"></label></div></div></div></div>';
                                var newInput = $(newIn);
                                var removeBtn = '<button id="remove' + (next - 1) + '" class="btn btn-danger remove-me" >-</button></div><div id="fieldsMartes'+ (next-1) +'">';
                                var removeButton = $(removeBtn);
                                $(addto).after(newInput);
                                $(addRemove).after(removeButton);
                                $("#fieldsMartes" + next).attr('data-source',$(addto).attr('data-source'));
                                $("#count").val(next);

                                    $('.remove-me').click(function(e){
                                        e.preventDefault();
                                        var fieldNum = this.id.substr(this.id.lastIndexOf("e")+1);
                                        var fieldID = "#fieldsMartes" + fieldNum;
                                        $(this).remove();
                                        $(fieldID).remove();
                                    });
                            });
                });
    </script>

    <script type="text/javascript">
                    $(document).ready(function(){
                                var next = 20;
                                $(".add-more3").click(function(e){
                                    e.preventDefault();
                                    var options = $("#options").val();
                                    var optionsMomento = $("#optionsMomentos").val();
                                    var addto = "#fieldsMiercoles" + next;
                                    var addRemove = "#fieldsMiercoles" + (next);
                                    next = next + 1;
                                    var newIn = '<div id="fieldsMiercoles'+ next +'"><div class="row"><div class="col-sm-6 form-group"><label for="medicine.title" class="control-label">Nombre del alimento</label><div cssclass="form-control"><select id="nombreAlimentoMiercoles[]" name="nombreAlimentoMiercoles[]" class="form-control"><option value="NONE" selected="selected">--- Seleccione ---</option>'+options+'</select></div></div><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Momento Del D\u00EDa</label><span class="required">*</span><div cssclass="form-control"><select id="momentoAlimentoMiercoles[]" name="momentoAlimentoMiercoles[]" class="form-control"><option value="NONE" selected="selected">--- Seleccione ---</option>'+optionsMomento+'</select><label for="nombreAlimentoMiercoles2" generated="true" class="error"></label></div></div></div>   <div class="row"><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Cantidad del Alimento</label><span class="required">*</span><div cssclass="form-control"><input type="text" id="cantidadAlimentoMiercoles'+ next +'" name="cantidadAlimentoMiercoles'+ next +'" class="form-control"><label for="cantidadAlimentoMiercoles'+ next +'" generated="true" class="error"></label></div></div><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Observaciones</label><span class="required">*</span><div cssclass="form-control"><input type="text" id="observacionAlimentoMiercoles'+ next +'" name="observacionAlimentoMiercoles'+ next +'" class="form-control"><label for="observacionAlimentoMiercoles'+ next +'" generated="true" class="error"></label></div></div></div></div>';
                                    var newInput = $(newIn);
                                    var removeBtn = '<button id="remove' + (next - 1) + '" class="btn btn-danger remove-me" >-</button></div><div id="fieldsMiercoles'+ (next-1) +'">';
                                    var removeButton = $(removeBtn);
                                    $(addto).after(newInput);
                                    $(addRemove).after(removeButton);
                                    $("#fieldsMiercoles" + next).attr('data-source',$(addto).attr('data-source'));
                                    $("#count").val(next);

                                        $('.remove-me').click(function(e){
                                            e.preventDefault();
                                            var fieldNum = this.id.substr(this.id.lastIndexOf("e")+1);
                                            var fieldID = "#fieldsMiercoles" + fieldNum;
                                            $(this).remove();
                                            $(fieldID).remove();
                                        });
                                });
                    });
        </script>

    <script type="text/javascript">
                    $(document).ready(function(){
                                var next = 30;
                                $(".add-more4").click(function(e){
                                    e.preventDefault();
                                    var options = $("#options").val();
                                    var optionsMomento = $("#optionsMomentos").val();
                                    var addto = "#fieldsJueves" + next;
                                    var addRemove = "#fieldsJueves" + (next);
                                    next = next + 1;
                                    var newIn = '<div id="fieldsJueves'+ next +'"><div class="row"><div class="col-sm-6 form-group"><label for="medicine.title" class="control-label">Nombre del alimento</label><div cssclass="form-control"><select id="nombreAlimentoJueves[]" name="nombreAlimentoJueves[]" class="form-control"><option value="NONE" selected="selected">--- Seleccione ---</option>'+options+'</select></div></div><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Momento Del D\u00EDa</label><span class="required">*</span><div cssclass="form-control"><select id="momentoAlimentoJueves[]" name="momentoAlimentoJueves[]" class="form-control"><option value="NONE" selected="selected">--- Seleccione ---</option>'+optionsMomento+'</select><label for="nombreAlimentoJueves2" generated="true" class="error"></label></div></div></div>   <div class="row"><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Cantidad del Alimento</label><span class="required">*</span><div cssclass="form-control"><input type="text" id="cantidadAlimentoJueves'+ next +'" name="cantidadAlimentoJueves'+ next +'" class="form-control"><label for="cantidadAlimentoJueves'+ next +'" generated="true" class="error"></label></div></div><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Observaciones</label><span class="required">*</span><div cssclass="form-control"><input type="text" id="observacionAlimentoJueves'+ next +'" name="observacionAlimentoJueves'+ next +'" class="form-control"><label for="observacionAlimentoJueves'+ next +'" generated="true" class="error"></label></div></div></div></div>';
                                    var newInput = $(newIn);
                                    var removeBtn = '<button id="remove' + (next - 1) + '" class="btn btn-danger remove-me" >-</button></div><div id="fieldsJueves'+ (next-1) +'">';
                                    var removeButton = $(removeBtn);
                                    $(addto).after(newInput);
                                    $(addRemove).after(removeButton);
                                    $("#fieldsMartes" + next).attr('data-source',$(addto).attr('data-source'));
                                    $("#count").val(next);

                                        $('.remove-me').click(function(e){
                                            e.preventDefault();
                                            var fieldNum = this.id.substr(this.id.lastIndexOf("e")+1);
                                            var fieldID = "#fieldsJueves" + fieldNum;
                                            $(this).remove();
                                            $(fieldID).remove();
                                        });
                                });
                    });
        </script>

    <script type="text/javascript">
                    $(document).ready(function(){
                                var next = 40;
                                $(".add-more5").click(function(e){
                                    e.preventDefault();
                                    var options = $("#options").val();
                                    var optionsMomento = $("#optionsMomentos").val();
                                    var addto = "#fieldsViernes" + next;
                                    var addRemove = "#fieldsViernes" + (next);
                                    next = next + 1;
                                    var newIn = '<div id="fieldsViernes'+ next +'"><div class="row"><div class="col-sm-6 form-group"><label for="medicine.title" class="control-label">Nombre del alimento</label><div cssclass="form-control"><select id="nombreAlimentoViernes[]" name="nombreAlimentoViernes[]" class="form-control"><option value="NONE" selected="selected">--- Seleccione ---</option>'+options+'</select></div></div><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Momento Del D\u00EDa</label><span class="required">*</span><div cssclass="form-control"><select id="momentoAlimentoViernes[]" name="momentoAlimentoViernes[]" class="form-control"><option value="NONE" selected="selected">--- Seleccione ---</option>'+optionsMomento+'</select><label for="nombreAlimentoViernes2" generated="true" class="error"></label></div></div></div>   <div class="row"><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Cantidad del Alimento</label><span class="required">*</span><div cssclass="form-control"><input type="text" id="cantidadAlimentoViernes'+ next +'" name="cantidadAlimentoViernes'+ next +'" class="form-control"><label for="cantidadAlimentoViernes'+ next +'" generated="true" class="error"></label></div></div><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Observaciones</label><span class="required">*</span><div cssclass="form-control"><input type="text" id="observacionAlimentoViernes'+ next +'" name="observacionAlimentoViernes'+ next +'" class="form-control"><label for="observacionAlimentoViernes'+ next +'" generated="true" class="error"></label></div></div></div></div>';
                                    var newInput = $(newIn);
                                    var removeBtn = '<button id="remove' + (next - 1) + '" class="btn btn-danger remove-me" >-</button></div><div id="fieldsViernes'+ (next-1) +'">';
                                    var removeButton = $(removeBtn);
                                    $(addto).after(newInput);
                                    $(addRemove).after(removeButton);
                                    $("#fieldsViernes" + next).attr('data-source',$(addto).attr('data-source'));
                                    $("#count").val(next);

                                        $('.remove-me').click(function(e){
                                            e.preventDefault();
                                            var fieldNum = this.id.substr(this.id.lastIndexOf("e")+1);
                                            var fieldID = "#fieldsViernes" + fieldNum;
                                            $(this).remove();
                                            $(fieldID).remove();
                                        });
                                });
                    });
        </script>

    <script type="text/javascript">
                $(document).ready(function(){
                            var next = 50;
                            $(".add-more6").click(function(e){
                                e.preventDefault();
                                var options = $("#options").val();
                                var optionsMomento = $("#optionsMomentos").val();
                                var addto = "#fieldsSabado" + next;
                                var addRemove = "#fieldsSabado" + (next);
                                next = next + 1;
                                var newIn = '<div id="fieldsSabado'+ next +'"><div class="row"><div class="col-sm-6 form-group"><label for="medicine.title" class="control-label">Nombre del alimento</label><div cssclass="form-control"><select id="nombreAlimentoSabado[]" name="nombreAlimentoSabado[]" class="form-control"><option value="NONE" selected="selected">--- Seleccione ---</option>'+options+'</select></div></div><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Momento Del D\u00EDa</label><span class="required">*</span><div cssclass="form-control"><select id="momentoAlimentoSabado[]" name="momentoAlimentoSabado[]" class="form-control"><option value="NONE" selected="selected">--- Seleccione ---</option>'+optionsMomento+'</select><label for="nombreAlimentoSabado2" generated="true" class="error"></label></div></div></div>   <div class="row"><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Cantidad del Alimento</label><span class="required">*</span><div cssclass="form-control"><input type="text" id="cantidadAlimentoSabado'+ next +'" name="cantidadAlimentoSabado'+ next +'" class="form-control"><label for="cantidadAlimentoSabado'+ next +'" generated="true" class="error"></label></div></div><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Observaciones</label><span class="required">*</span><div cssclass="form-control"><input type="text" id="observacionAlimentoSabado'+ next +'" name="observacionAlimentoSabado'+ next +'" class="form-control"><label for="observacionAlimentoSabado'+ next +'" generated="true" class="error"></label></div></div></div></div>';
                                var newInput = $(newIn);
                                var removeBtn = '<button id="remove' + (next - 1) + '" class="btn btn-danger remove-me" >-</button></div><div id="fieldsSabado'+ (next-1) +'">';
                                var removeButton = $(removeBtn);
                                $(addto).after(newInput);
                                $(addRemove).after(removeButton);
                                $("#fieldsSabado" + next).attr('data-source',$(addto).attr('data-source'));
                                $("#count").val(next);

                                    $('.remove-me').click(function(e){
                                        e.preventDefault();
                                        var fieldNum = this.id.substr(this.id.lastIndexOf("e")+1);
                                        var fieldID = "#fieldsSabado" + fieldNum;
                                        $(this).remove();
                                        $(fieldID).remove();
                                    });
                            });
                });
    </script>

</head>

<div class="container">
<meta name="menu" content="UserMenu"/>

<c:set var="delObject" scope="request"><fmt:message key="userList.user"/></c:set>
<script type="text/javascript">var msgDelConfirm =
		"<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h3>Dieta a</h2>
    <h3>Recomendadar</h2>
</div>
<!-- Ac� comienzan los formularios -->

<div class="col-sm-7">
	<spring:bind path="dietaRecomendadaForm.*">
  		<c:if test="${not empty status.errorMessages}">
      		<div class="alert alert-danger alert-dismissable">
          	<a href="#" data-dismiss="alert" class="close">&times;</a>
          	<c:forEach var="error" items="${status.errorMessages}">
            	<c:out value="${error}" escapeXml="false"/><br/>
          	</c:forEach>
      		</div>
  		</c:if>
	</spring:bind>

	<form:form commandName="dietaRecomendadaForm" method="post" action="dieta" id="formulario" autocomplete="off" name="miFormulario"
           cssClass="well" onsubmit="return validateUser(this)">




                   <div class="form-group">
                       <div class="row">
                           <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                               <spring:bind path="dietaRecomendadaForm.fechaAlta">
                               <appfuse:label styleClass="control-label" key="paciente.fecha.desde"/>
                               <span class="required">*</span>
                                   <div class='input-group date' id='datetimepicker1'>
                                       <input type="text" name="fechaAlta" id="fechaAlta" class="form-control" readonly="readonly"
                                       placeholder="<fmt:message key="user.paciente.fecha"/>" value="${status.value}" maxlength="50"
                                       tabindex="5" data-date-format="DD/MM/YYYY">
                                       <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                       </span>
                                   </div>
                               </spring:bind>
                               <label for="fechaAlta" generated="true" class="error"></label>
                               <form:errors path="fechaAlta" cssClass="help-block"/>
                               <script type="text/javascript">
                                   $(function () {
                                       $('#datetimepicker1').datetimepicker({
                                       language: 'pt-BR',
                                       showToday: true,
                                       pickTime: false,
                                       viewMode: 'years',
                                       format: 'MM/YYYY',
                                       maxDate: new Date()
                                       });
                                   });
                               </script>
                           </div>

                       </div>
                   </div>
        <div id="tabs">
              		<ul>
                		<li><a href="#tabs-1">Lunes</a></li>
            		    <li><a href="#tabs-2">Martes</a></li>
            		    <li><a href="#tabs-3">Miercoles</a></li>
            		    <li><a href="#tabs-4">Jueves</a></li>
            		    <li><a href="#tabs-5">Viernes</a></li>
            		    <li><a href="#tabs-6">Sabado</a></li>
            		</ul>

        <c:forEach items="${lunes}" varStatus="gridRow">

                                                       <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                               <spring:bind path="lunes[${gridRow.index}].momentosDia[0].nombre">
                                                                                   <appfuse:label styleClass="control-label" key="user.paciente.momentoDia"/>
                                                                                   <form:select path="lunes[${gridRow.index}].momentosDia[0].nombre" cssClass="form-control">
                                                                                       <form:option value="NONE" label="--- Seleccione ---"/>
                                                                                       <form:options items="${momentoDias}"/>
                                                                                   </form:select>
                                                                               </spring:bind>
                                                                               <label for="momentoLunes1" generated="true" class="error"></label>
                                                                               <form:errors path="lunes[${gridRow.index}].momentosDia[0].nombre" cssClass="help-block"/>
                                                                           </div>
                                           		</c:forEach>

        <%--
        <c:forEach items="${dietaRecomendadaForm.diasDieta}" varStatus="gridRow">
        <div id="tabs-1">

            <input type="hidden" name="options" id="options" value="${options}"/>
            <input type="hidden" name="optionsMomentos" id="optionsMomentos" value="${optionsMomentos}"/>


            <c:set var="dia" value="${diasDieta}"/>

             <c:out value="${diasDieta}"/>

            <div id="fieldsLunes1">

                <div class="row">
                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        <spring:bind path="dietaRecomendadaForm.diasDieta[${gridRow.index}].momentosDia[0].comidas[0].alimento.nombre">
                            <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                            <form:select path="diasDieta[${gridRow.index}].momentosDia[0].comidas[0].alimento.nombre" cssClass="form-control">
                                <form:option value="NONE" label="--- Seleccione ---"/>
                                <form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
                            </form:select>
                        </spring:bind>
                        <label for="nombreAlimentoLunes1" generated="true" class="error"></label>
                        <form:errors path="diasDieta[${gridRow.index}].momentosDia[0].comidas[0].alimento.nombre" cssClass="help-block"/>
                    </div>
                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        <spring:bind path="dietaRecomendadaForm.diasDieta[${gridRow.index}].momentosDia[0].nombre">
                            <appfuse:label styleClass="control-label" key="user.paciente.momentoDia"/>
                            <form:select path="diasDieta[${gridRow.index}].momentosDia[0].nombre" cssClass="form-control">
                                <form:option value="NONE" label="--- Seleccione ---"/>
                                <form:options items="${momentoDias}"/>
                            </form:select>
                        </spring:bind>
                        <label for="momentoLunes1" generated="true" class="error"></label>
                        <form:errors path="diasDieta[${gridRow.index}].momentosDia[0].nombre" cssClass="help-block"/>
                    </div>
                </div>

                <div class="row">

                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                            <spring:bind path="dietaRecomendadaForm.diasDieta[${gridRow.index}].momentosDia[0].comidas[0].cantidad">
                                <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                <input type="text" name="cantidadLunes1" id="cantidadLunes1" class="form-control"
                                placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="${status.value}" maxlength="50"
                                tabindex="4">
                            </spring:bind>
                            <label for="cantidadLunes1" generated="true" class="error"></label>
                            <form:errors path="diasDieta[${gridRow.index}].momentosDia[0].comidas[0].cantidad" cssClass="help-block"/>
                        </div>
                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                          <spring:bind path="dietaRecomendadaForm.diasDieta[${gridRow.index}].momentosDia[0].comidas[0].observaciones">
                                <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                <input type="text" name="observacionesLunes1" id="observacionesLunes1" class="form-control"
                                placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="${status.value}" tabindex="12"/>
                          </spring:bind>
                    <label for="observacionesLunes1" generated="true" class="error"></label>
                    <form:errors path="diasDieta[${gridRow.index}].momentosDia[0].comidas[0].observaciones" cssClass="help-block"/>
                    </div>
                </div>
            </div>
            <button id="b1" class="btn add-more" type="button">+</button>

        </div>


        </c:forEach> --%>

                <%-- Fin del tabs6 --%>
        <%-- Fin del div tabs --%>
    </div>
            <br>
            <div class="form-group">
                <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false" tabindex="24">
                    <i class="icon-ok icon-white"></i>
                    <fmt:message key="button.save" />
                </button>
                <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true" tabindex="26">

                    <i class="icon-remove"></i>
                    <fmt:message key="button.cancel" />
                </button>
            </div>
	</form:form>
</div>
</div>

<c:set var="scripts" scope="request">
	<script type="text/javascript">
		// This is here so we can exclude the selectAll call when roles is hidden

		function onFormSubmit(theForm) {
			return validateUser(theForm);
		};
	</script>
	<script type="text/javascript">
		 $('button[name="cancel"]').click(function(e){
				e.preventDefault();
				window.location.href = "${ctx}/admin/endocrinologoList";
			});
	</script>
	</script>
</c:set>

<v:javascript formName="endocrinologoForm" staticJavascript="false" />
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
