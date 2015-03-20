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
        <spring:bind path="dni">
        	<input type="hidden" name="dni" id="dni" class="form-control" value="${status.value}"/>
        </spring:bind>
        <spring:bind path="name">
            <input type="hidden" name="name" id="name" class="form-control" value="${status.value}"/>
        </spring:bind>


        <div class="form-group">
            <div class="row">
                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                    <spring:bind path="fechaAlta">
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
        <div id="tabs-1">
            <spring:bind path="diaDietaLunes">
                <input type="hidden" name="diaDietaLunes" id="diaDietaLunes" class="form-control" value="LUNES"/>
            </spring:bind>
            <input type="hidden" name="options" id="options" value="${options}"/>
            <input type="hidden" name="optionsMomentos" id="optionsMomentos" value="${optionsMomentos}"/>
            <div id="fieldsLunes1">
                <div class="row">
                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        <spring:bind path="dietaRecomendadaForm.nombreAlimentoLunes1">
                            <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                            <form:select path="nombreAlimentoLunes1" cssClass="form-control">
                                <form:option value="NONE" label="--- Seleccione ---"/>
                                <form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
                            </form:select>
                        </spring:bind>
                        <label for="nombreAlimentoLunes1" generated="true" class="error"></label>
                        <form:errors path="nombreAlimentoLunes1" cssClass="help-block"/>
                    </div>
                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        <spring:bind path="dietaRecomendadaForm.momentoLunes1">
                            <appfuse:label styleClass="control-label" key="user.paciente.momentoDia"/>
                            <form:select path="momentoLunes1" cssClass="form-control">
                                <form:option value="NONE" label="--- Seleccione ---"/>
                                <form:options items="${momentoDias}"/>
                            </form:select>
                        </spring:bind>
                        <label for="momentoLunes1" generated="true" class="error"></label>
                        <form:errors path="momentoLunes1" cssClass="help-block"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        <spring:bind path="dietaRecomendadaForm.cantidadLunes1">
                            <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                            <input type="text" name="cantidadLunes1" id="cantidadLunes1" class="form-control"
                            placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="${status.value}" maxlength="50"
                            tabindex="4">
                        </spring:bind>
                    <label for="cantidadLunes1" generated="true" class="error"></label>
                    <form:errors path="cantidadLunes1" cssClass="help-block"/>
                    </div>
                     <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                          <spring:bind path="dietaRecomendadaForm.observacionesLunes1">
                                <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                <input type="text" name="observacionesLunes1" id="observacionesLunes1" class="form-control"
                                placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="${status.value}" tabindex="12"/>
                          </spring:bind>
                     <label for="observacionesLunes1" generated="true" class="error"></label>
                     <form:errors path="observacionesLunes1" cssClass="help-block"/>
                     </div>
                </div>
            </div>

            <button id="b1" class="btn add-more" type="button">+</button>
        </div>

        <div id="tabs-2">
            <div id="fieldsMartes10">
                    <spring:bind path="diaDietaMartes">
                        <input type="hidden" name="diaDietaMartes" id="diaDietaMartes" class="form-control" value="MARTES"/>
                    </spring:bind>
                    <div class="row">
                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                            <spring:bind path="dietaRecomendadaForm.nombreAlimentoMartes1">
                                <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                <form:select path="nombreAlimentoMartes1" cssClass="form-control">
                                	<form:option value="NONE" label="--- Seleccione ---"/>
                                    <form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
                                </form:select>
                            </spring:bind>
                            <label for="nombreAlimentoMartes1" generated="true" class="error"></label>
                            <form:errors path="nombreAlimentoMartes1" cssClass="help-block"/>
                        </div>
                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                            <spring:bind path="dietaRecomendadaForm.momentoMartes1">
                                <appfuse:label styleClass="control-label" key="user.paciente.momentoDia"/>
                                <form:select path="momentoMartes1" cssClass="form-control">
                                    <form:option value="NONE" label="--- Seleccione ---"/>
                                    <form:options items="${momentoDias}"/>
                                </form:select>
                            </spring:bind>
                            <label for="momentoMartes1" generated="true" class="error"></label>
                            <form:errors path="momentoMartes1" cssClass="help-block"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                            <spring:bind path="dietaRecomendadaForm.cantidadMartes1">
                                <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                <input type="text" name="cantidadMartes1" id="cantidadMartes1" class="form-control"
                                placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="${status.value}" maxlength="50"
                                tabindex="10">
                            </spring:bind>
                        <label for="cantidadMartes1" generated="true" class="error"></label>
                        <form:errors path="cantidadMartes1" cssClass="help-block"/>
                        </div>
                         <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                              <spring:bind path="dietaRecomendadaForm.observacionesMartes1">
                                    <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                    <input type="text" name="observacionesMartes1" id="observacionesMartes1" class="form-control"
                                    placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="${status.value}" tabindex="12"/>
                              </spring:bind>
                              <label for="observacionesMartes1" generated="true" class="error"></label>
                              <form:errors path="observacionesMartes1" cssClass="help-block"/>
                         </div>
                    </div>
            </div>
            <button id="b2" class="btn add-more2" type="button">+</button>
        </div>
        <%-- Fin del tabs2 --%>

        <div id="tabs-3">
            <div id="fieldsMiercoles20">
                            <spring:bind path="diaDietaMiercoles">
                                <input type="hidden" name="diaDietaMiercoles" id="diaDietaMiercoles" class="form-control" value="MIERCOLES"/>
                            </spring:bind>
                            <div class="row">
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    <spring:bind path="dietaRecomendadaForm.nombreAlimentoMiercoles1">
                                        <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                        <form:select path="nombreAlimentoMiercoles1" cssClass="form-control">
                                        	<form:option value="NONE" label="--- Seleccione ---"/>
                                            <form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
                                        </form:select>
                                    </spring:bind>
                                    <label for="nombreAlimentoMiercoles1" generated="true" class="error"></label>
                                    <form:errors path="nombreAlimentoMiercoles1" cssClass="help-block"/>
                                </div>
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    <spring:bind path="dietaRecomendadaForm.momentoMiercoles1">
                                        <appfuse:label styleClass="control-label" key="user.paciente.momentoDia"/>
                                        <form:select path="momentoMiercoles1" cssClass="form-control">
                                            <form:option value="NONE" label="--- Seleccione ---"/>
                                            <form:options items="${momentoDias}"/>
                                        </form:select>
                                    </spring:bind>
                                    <label for="momentoMiercoles1" generated="true" class="error"></label>
                                    <form:errors path="momentoMiercoles1" cssClass="help-block"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    <spring:bind path="dietaRecomendadaForm.cantidadMiercoles1">
                                        <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                        <input type="text" name="cantidadMiercoles1" id="cantidadMiercoles1" class="form-control"
                                        placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="${status.value}" maxlength="50"
                                        tabindex="4">
                                    </spring:bind>
                                <label for="cantidadMiercoles1" generated="true" class="error"></label>
                                <form:errors path="cantidadMiercoles1" cssClass="help-block"/>
                                </div>
                                 <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                      <spring:bind path="dietaRecomendadaForm.observacionesMiercoles1">
                                            <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                            <input type="text" name="observacionesMiercoles1" id="observacionesMiercoles1" class="form-control"
                                            placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="${status.value}" tabindex="12"/>
                                      </spring:bind>
                                      <label for="observacionesMiercoles1" generated="true" class="error"></label>
                                      <form:errors path="observacionesMiercoles1" cssClass="help-block"/>
                                 </div>
                            </div>
            </div>
            <button id="b3" class="btn add-more3" type="button">+</button>
        </div>
                <%-- Fin del tabs3 --%>

        <div id="tabs-4">
            <div id="fieldsJueves30">
                            <spring:bind path="diaDietaJueves">
                                <input type="hidden" name="diaDietaJueves" id="diaDietaJueves" class="form-control" value="JUEVES"/>
                            </spring:bind>
                            <div class="row">
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    <spring:bind path="dietaRecomendadaForm.nombreAlimentoJueves1">
                                        <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                        <form:select path="nombreAlimentoJueves1" cssClass="form-control">
                                        	<form:option value="NONE" label="--- Seleccione ---"/>
                                            <form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
                                        </form:select>
                                    </spring:bind>
                                    <label for="nombreAlimentoJueves1" generated="true" class="error"></label>
                                    <form:errors path="nombreAlimentoJueves1" cssClass="help-block"/>
                                </div>
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    <spring:bind path="dietaRecomendadaForm.momentoJueves1">
                                        <appfuse:label styleClass="control-label" key="user.paciente.momentoDia"/>
                                        <form:select path="momentoJueves1" cssClass="form-control">
                                            <form:option value="NONE" label="--- Seleccione ---"/>
                                            <form:options items="${momentoDias}"/>
                                        </form:select>
                                    </spring:bind>
                                    <label for="momentoJueves1" generated="true" class="error"></label>
                                    <form:errors path="momentoJueves1" cssClass="help-block"/>
                                </div>
                            </div>
                            <div class="row">
                                                            <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                <spring:bind path="dietaRecomendadaForm.cantidadJueves1">
                                                                    <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                                                    <input type="text" name="cantidadJueves1" id="cantidadJueves1" class="form-control"
                                                                    placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="${status.value}" maxlength="50"
                                                                    tabindex="4">
                                                                </spring:bind>
                                                            <label for="cantidadJueves1" generated="true" class="error"></label>
                                                            <form:errors path="cantidadJueves1" cssClass="help-block"/>
                                                            </div>
                                                             <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                  <spring:bind path="dietaRecomendadaForm.observacionesJueves1">
                                                                        <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                                                        <input type="text" name="observacionesJueves1" id="observacionesJueves1" class="form-control"
                                                                        placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="${status.value}" tabindex="12"/>
                                                                  </spring:bind>
                                                                  <label for="observacionesJueves1" generated="true" class="error"></label>
                                                                  <form:errors path="observacionesJueves1" cssClass="help-block"/>
                                                             </div>
                            </div>
            </div>
            <button id="b4" class="btn add-more4" type="button">+</button>
        </div>
                <%-- Fin del tabs4 --%>

        <div id="tabs-5">
            <div id="fieldsViernes40">
                            <spring:bind path="diaDietaViernes">
                                <input type="hidden" name="diaDietaViernes" id="diaDietaViernes" class="form-control" value="VIERNES"/>
                            </spring:bind>
                            <div class="row">
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    <spring:bind path="dietaRecomendadaForm.nombreAlimentoViernes1">
                                        <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                        <form:select path="nombreAlimentoViernes1" cssClass="form-control">
                                        	<form:option value="NONE" label="--- Seleccione ---"/>
                                            <form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
                                        </form:select>
                                    </spring:bind>
                                    <label for="nombreAlimentoViernes1" generated="true" class="error"></label>
                                    <form:errors path="nombreAlimentoViernes1" cssClass="help-block"/>
                                </div>
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    <spring:bind path="dietaRecomendadaForm.momentoViernes1">
                                        <appfuse:label styleClass="control-label" key="user.paciente.momentoDia"/>
                                        <form:select path="momentoViernes1" cssClass="form-control">
                                            <form:option value="NONE" label="--- Seleccione ---"/>
                                            <form:options items="${momentoDias}"/>
                                        </form:select>
                                    </spring:bind>
                                    <label for="momentoViernes1" generated="true" class="error"></label>
                                    <form:errors path="momentoViernes1" cssClass="help-block"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    <spring:bind path="dietaRecomendadaForm.cantidadViernes1">
                                        <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                        <input type="text" name="cantidadViernes1" id="cantidadViernes1" class="form-control"
                                        placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="${status.value}" maxlength="50"
                                        tabindex="4">
                                    </spring:bind>
                                <label for="cantidadViernes1" generated="true" class="error"></label>
                                <form:errors path="cantidadViernes1" cssClass="help-block"/>
                                </div>
                                 <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                      <spring:bind path="dietaRecomendadaForm.observacionesViernes1">
                                            <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                            <input type="text" name="observacionesViernes1" id="observacionesViernes1" class="form-control"
                                            placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="${status.value}" tabindex="12"/>
                                      </spring:bind>
                                      <label for="observacionesViernes1" generated="true" class="error"></label>
                                      <form:errors path="observacionesViernes1" cssClass="help-block"/>
                                 </div>
                            </div>
            </div>
            <button id="b5" class="btn add-more5" type="button">+</button>
        </div>
                <%-- Fin del tabs5 --%>

        <div id="tabs-6">
            <div id="fieldsSabado50">
                            <spring:bind path="diaDietaSabado">
                                <input type="hidden" name="diaDietaSabado" id="diaDietaSabado" class="form-control" value="SABADO"/>
                            </spring:bind>
                            <div class="row">
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    <spring:bind path="dietaRecomendadaForm.nombreAlimentoSabado1">
                                        <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                        <form:select path="nombreAlimentoSabado1" cssClass="form-control">
                                        	<form:option value="NONE" label="--- Seleccione ---"/>
                                            <form:options items="${alimentoList}" itemValue="nombre" itemLabel="nombre"/>
                                        </form:select>
                                    </spring:bind>
                                    <label for="nombreAlimentoSabado1" generated="true" class="error"></label>
                                    <form:errors path="nombreAlimentoSabado1" cssClass="help-block"/>
                                </div>
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    <spring:bind path="dietaRecomendadaForm.momentoSabado1">
                                        <appfuse:label styleClass="control-label" key="user.paciente.momentoDia"/>
                                        <form:select path="momentoSabado1" cssClass="form-control">
                                            <form:option value="NONE" label="--- Seleccione ---"/>
                                            <form:options items="${momentoDias}"/>
                                        </form:select>
                                    </spring:bind>
                                    <label for="momentoSabado1" generated="true" class="error"></label>
                                    <form:errors path="momentoSabado1" cssClass="help-block"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                    <spring:bind path="dietaRecomendadaForm.cantidadSabado1">
                                        <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                        <input type="text" name="cantidadSabado1" id="cantidadSabado1" class="form-control"
                                        placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="${status.value}" maxlength="50"
                                        tabindex="4">
                                    </spring:bind>
                                <label for="cantidadSabado1" generated="true" class="error"></label>
                                <form:errors path="cantidadSabado1" cssClass="help-block"/>
                                </div>
                                <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                      <spring:bind path="dietaRecomendadaForm.observacionesSabado1">
                                            <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                            <input type="text" name="observacionesSabado1" id="observacionesSabado1" class="form-control"
                                            placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="${status.value}" tabindex="12"/>
                                      </spring:bind>
                                 <label for="observacionesSabado1" generated="true" class="error"></label>
                                 <form:errors path="observacionesSabado1" cssClass="help-block"/>
                                 </div>
                            </div>
            </div>
            <button id="b6" class="btn add-more6" type="button">+</button>
        </div>
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
				window.location.href = "http://localhost:8080/nutricionista/pacienteList";
			});
	</script>
	</script>

    <script>
        $(function() {
            $('#dia').blur(function(){
                var txtVal =  $('#dia').val();
                if(isDate(txtVal)){
                    ;
                }
                else{
                    $(this).parent().find(".error").text("La fecha ingresada es incorrecta");
                    document.miFormulario.dia.value = "";
                    }
            });

        function isDate(txtDate)
        {
            var currVal = txtDate;
            if(currVal == '')
                return false;

            var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/; //Declare Regex
            var dtArray = currVal.match(rxDatePattern); // is format OK?

            if (dtArray == null)
                return false;

            //Checks for mm/dd/yyyy format.
            dtMonth = dtArray[3];
            dtDay= dtArray[1];
            dtYear = dtArray[5];

            if (dtMonth < 1 || dtMonth > 12)
                return false;
            else if (dtDay < 1 || dtDay> 31)
                return false;
            else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31)
                return false;
            else if (dtMonth == 2)
            {
                var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
                if (dtDay> 29 || (dtDay ==29 && !isleap))
                        return false;
            }
            return true;
        }

        });
    </script>

</c:set>

<v:javascript formName="endocrinologoForm" staticJavascript="false" />
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
