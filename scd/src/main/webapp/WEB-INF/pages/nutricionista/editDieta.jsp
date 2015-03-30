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
                        var next = $("#totalLunes").val() - 1;
                        $(".add-more").click(function(e){
                            e.preventDefault();
                            var options = $("#options").val();
                            var optionsMomento = $("#optionsMomentos").val();
                            var addto = "#fieldsLunes" + next;
                            var addRemove = "#fieldsLunes" + (next);
                            next = next + 1;
                            var newIn = '<div id="fieldsLunes'+ next +'"><div class="row"><div class="col-sm-6 form-group"><label for="medicine.title" class="control-label">Nombre del alimento</label><div cssclass="form-control"><select id="nombreAlimentoLunes[]" name="nombreAlimentoLunes[]" class="form-control"><option value="NONE" selected="selected">--- Seleccione ---</option>'+options+'</select></div></div><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Momento Del D\u00EDa</label><span class="required">*</span><div cssclass="form-control"><select id="momentoAlimentoLunes[]" name="momentoAlimentoLunes[]" class="form-control"><option value="NONE" selected="selected">--- Seleccione ---</option>'+optionsMomento+'</select><label for="nombreAlimentoLunes2" generated="true" class="error"></label></div></div></div>   <div class="row"><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Cantidad del Alimento</label><span class="required">*</span><div cssclass="form-control"><input type="text" id="cantidadAlimentoLunes'+ next +'" name="cantidadAlimentoLunes'+ next +'" class="form-control"><label for="cantidadAlimentoLunes'+ next +'" generated="true" class="error"></label></div></div><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Observaciones</label><span class="required">*</span><div cssclass="form-control"><input type="text" id="observacionAlimentoLunes'+ next +'" name="observacionAlimentoLunes'+ next +'" class="form-control"><label for="observacionAlimentoLunes'+ next +'" generated="true" class="error"></label></div></div></div><button id="remove' + (next) + '" class="btn btn-danger remove-me" >-</button></div><div id="fieldsLunes'+ (next) +'"></div>';
                            var newInput = $(newIn);
                            var removeBtn = '<button id="remove' + (next) + '" class="btn btn-danger remove-me" >-</button></div><div id="fieldsLunes'+ (next) +'">';
                            var removeButton = $(removeBtn);
                            $(addto).after(newInput);
                            $("#fieldsLunes" + next).attr('data-source',$(addto).attr('data-source'));
                            $("#countLunes").val(next);

                                $('.remove-me').click(function(e){
                                    e.preventDefault();
                                    var fieldNum = this.id.substr(this.id.lastIndexOf("e")+1);
                                    var fieldID = "#fieldsLunes" + fieldNum;
                                    $(this).remove();
                                    $(fieldID).remove();
                                });
                        });
                        $('.remove-another').click(function(e){
                            e.preventDefault();
                            var fieldNum = this.id.substr(this.id.lastIndexOf("e")+1);
                            var fieldID = "#fieldsLunes" + fieldNum;
                            $(this).remove();
                            $(fieldID).remove();
                        });
            });
    </script>

    <script type="text/javascript">
                $(document).ready(function(){
                            var next = 14;
                            $(".add-moreMartes").click(function(e){
                                e.preventDefault();
                                var options = $("#options").val();
                                var optionsMomento = $("#optionsMomentos").val();
                                var addto = "#fieldsMartes" + next;
                                var addRemove = "#fieldsMartes" + (next);
                                next = next + 1;
                                var newIn = '<div id="fieldsMartes'+ next +'"><div class="row"><div class="col-sm-6 form-group"><label for="medicine.title" class="control-label">Nombre del alimento</label><div cssclass="form-control"><select id="nombreAlimentoMartes[]" name="nombreAlimentoMartes[]" class="form-control"><option value="NONE" selected="selected">--- Seleccione ---</option>'+options+'</select></div></div><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Momento Del D\u00EDa</label><span class="required">*</span><div cssclass="form-control"><select id="momentoAlimentoMartes[]" name="momentoAlimentoMartes[]" class="form-control"><option value="NONE" selected="selected">--- Seleccione ---</option>'+optionsMomento+'</select><label for="nombreAlimentoMartes2" generated="true" class="error"></label></div></div></div>  <div class="row"><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Cantidad del Alimento</label><span class="required">*</span><div cssclass="form-control"><input type="text" id="cantidadAlimentoMartes'+ next +'" name="cantidadAlimentoMartes'+ next +'" class="form-control"><label for="cantidadAlimentoMartes'+ next +'" generated="true" class="error"></label></div></div><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Observaciones</label><span class="required">*</span><div cssclass="form-control"><input type="text" id="observacionAlimentoMartes'+ next +'" name="observacionAlimentoMartes'+ next +'" class="form-control"><label for="observacionAlimentoMartes'+ next +'" generated="true" class="error"></label></div></div></div><button id="remove' + (next) + '" class="btn btn-danger remove-meMartes" >-</button></div><div id="fieldsMartes'+ (next) +'"></div>';
                                var newInput = $(newIn);
                                var removeBtn = '<button id="remove' + (next) + '" class="btn btn-danger remove-meMartes" >-</button></div><div id="fieldsMartes'+ (next) +'">';
                                var removeButton = $(removeBtn);
                                $(addto).after(newInput);
                                $("#fieldsMartes" + next).attr('data-source',$(addto).attr('data-source'));
                                $("#countMartes").val(next);

                                    $('.remove-meMartes').click(function(e){
                                        e.preventDefault();
                                        var fieldNum = this.id.substr(this.id.lastIndexOf("e")+1);
                                        var fieldID = "#fieldsMartes" + fieldNum;
                                        $(this).remove();
                                        $(fieldID).remove();
                                    });
                            });
                            $('.remove-anotherMartes').click(function(e){
                                                        e.preventDefault();
                                                        var fieldNum = this.id.substr(this.id.lastIndexOf("e")+1);
                                                        var fieldID = "#fieldsMartes" + fieldNum;
                                                        $(this).remove();
                                                        $(fieldID).remove();
                            });
                });
    </script>

    <script type="text/javascript">
                    $(document).ready(function(){
                                var next = 24;
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
                                var next = 34;
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
                                var next = 44;
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
                            var next = 54;
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

	<form:form commandName="dietaRecomendadaForm" method="post" action="editDieta" id="formulario" autocomplete="off" name="miFormulario"
           cssClass="well" onsubmit="return validateUser(this)">

                <spring:bind path="idDieta">
                    <input type="hidden" name="dni" id="dni" class="form-control" value="${status.value}"/>
                </spring:bind>

                <spring:bind path="Paciente.persona.dni">
                     <input type="hidden" name="dato" id="dato" class="form-control" value="${status.value}"/>
                </spring:bind>

                <input type="hidden" name="totalLunes" id="totalLunes" value="${totalLunes}"/>
                <input type="hidden" name="totalMartes" id="totalMartes" value="${totalMartes}"/>
                <input type="hidden" name="totalMiercoles" id="totalMiercoles" value="${totalMiercoles}"/>
                <input type="hidden" name="totalJueves" id="totalJueves" value="${totalJueves}"/>
                <input type="hidden" name="totalViernes" id="totalViernes" value="${totalViernes}"/>
                <input type="hidden" name="totalSabado" id="totalSabado" value="${totalSabado}"/>

                   <div class="form-group">
                       <div class="row">
                           <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                               <spring:bind path="dietaRecomendadaForm.fechaAlta">
                               <appfuse:label styleClass="control-label" key="paciente.fecha.desde"/>
                               <span class="required">*</span>
                                   <div class='input-group date' id='datetimepicker1'>
                                       <input type="text" name="fechaAlta" id="fechaAlta" class="form-control" readonly
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

                            <input type="hidden" name="options" id="options" value="${options}"/>
                            <input type="hidden" name="optionsMomentos" id="optionsMomentos" value="${optionsMomentos}"/>

                            <spring1:bind path="dietaRecomendadaForm.diaDietaLunes">
                                <input type="hidden" name="diaDietaLunes" id="diaDietaLunes" class="form-control" value="LUNES"/>
                            </spring1:bind>

                            <c:set var="countLunes" value="0" scope="page" />
                            <c:forEach items="${lunes}" var="ver" varStatus="gridRow">
                            <c:set var="countLunes" value="${countLunes + 1}" scope="page"/>
                            <div id="fieldsLunes${gridRow.index}">
                                <div class="row">
                                    <div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                        <spring1:bind path="dietaRecomendadaForm.nombreAlimentoLunes${gridRow.index + 1}">
                                            <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                            <select name="nombreAlimentoLunes${gridRow.index + 1}" id="nombreAlimentoLunes${gridRow.index + 1}" size="1" class="form-control" value="${status.value}">
                                            <option value="<c:out value="${ver.alimento}"/>"><c:out value="${ver.alimento}"/></option>
                                                <c:forEach items="${alimentoTotal}" var="item" >
                                                    <option
                                                    value="<c:out value="${item}"/>"><c:out value="${item}"/>
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </spring1:bind>
                                    <label for="nombreAlimentoLunes${gridRow.index + 1}" generated="true" class="error"></label>
                                    </div>
                                    <div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                        <spring1:bind path="dietaRecomendadaForm.momentoLunes${gridRow.index + 1}">
                                            <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                            <select name="momentoLunes${gridRow.index + 1}" id="momentoLunes${gridRow.index + 1}" size="1" class="form-control">
                                            <option value="<c:out value="${ver.momento}"/>"><c:out value="${ver.momento}"/></option>
                                                <c:forEach items="${momentoDias}" var="item" >
                                                    <option
                                                    value="<c:out value="${item}"/>"><c:out value="${item}"/>
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </spring1:bind>
                                        <label for="momentoLunes${gridRow.index + 1}" generated="true" class="error"></label>
                                    </div>
                                </div>

                                <div class="row">
                                        <div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                            <spring1:bind path="dietaRecomendadaForm.cantidadLunes${gridRow.index + 1}">
                                                <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                                <input type="text" name="cantidadLunes${gridRow.index + 1}" id="cantidadLunes${gridRow.index + 1}" class="form-control"
                                                placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="<c:out value="${ver.cantidad}"/>" maxlength="50"
                                                tabindex="4">
                                            </spring1:bind>
                                        <label for="nombreAlimentoLunes${gridRow.index + 1}" generated="true" class="error"></label>
                                        </div>
                                        <div class="col-md-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                            <spring1:bind path="dietaRecomendadaForm.observacionesLunes${gridRow.index + 1}">
                                                <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                                <input type="text" name="observacionesLunes${gridRow.index + 1}" id="observacionesLunes${gridRow.index + 1}" class="form-control"
                                                placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="<c:out value="${ver.observacion}"/>" tabindex="12"/>
                                            </spring1:bind>
                                        <label for="observacionesLunes${gridRow.index + 1}" generated="true" class="error"></label>
                                        </div>
                                </div>
                                <c:if test="${gridRow.index > 0}">
                                    <button id="remove${gridRow.index}" class="btn btn-danger remove-another" style="margin-top:-40px">-</button>
                                </c:if>
                            </div>
                            </c:forEach>
                            <button id="b1" class="btn add-more" type="button">+</button>
                        </div>

                        <div id="tabs-2">
                                <spring1:bind path="dietaRecomendadaForm.diaDietaMartes">
                                    <input type="hidden" name="diaDietaMartes" id="diaDietaMartes" class="form-control" value="MARTES"/>
                                </spring1:bind>

                                <c:set var="countMartes" value="0" scope="page" />
                                <c:forEach items="${martes}" var="ver" varStatus="gridRow">
                                <c:set var="countMartes" value="${countMartes + 1}" scope="page"/>
                                    <div id="fieldsMartes14">
                                                                <div class="row">
                                                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                        <spring1:bind path="dietaRecomendadaForm.nombreAlimentoMartes${gridRow.index + 1}">
                                                                            <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                                                            <select name="nombreAlimentoMartes${gridRow.index + 1}" id="nombreAlimentoMartes${gridRow.index + 1}" size="1" class="form-control">
                                                                            <option value="<c:out value="${ver.alimento}"/>"><c:out value="${ver.alimento}"/></option>
                                                                                <c:forEach items="${alimentoTotal}" var="item" >
                                                                                    <option
                                                                                    value="<c:out value="${item}"/>"><c:out value="${item}"/>
                                                                                    </option>
                                                                                </c:forEach>
                                                                            </select>
                                                                        </spring1:bind>
                                                                    <label for="nombreAlimentoMartes${gridRow.index + 1}" generated="true" class="error"></label>
                                                                    </div>
                                                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                        <spring1:bind path="dietaRecomendadaForm.momentoMartes${gridRow.index + 1}">
                                                                            <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                                                            <select name="momentoMartes${gridRow.index + 1}" id="momentoMartes${gridRow.index + 1}" size="1" class="form-control">
                                                                            <option value="<c:out value="${ver.momento}"/>"><c:out value="${ver.momento}"/></option>
                                                                                <c:forEach items="${momentoDias}" var="item" >
                                                                                    <option
                                                                                    value="<c:out value="${item}"/>"><c:out value="${item}"/>
                                                                                    </option>
                                                                                </c:forEach>
                                                                            </select>
                                                                        </spring1:bind>
                                                                        <label for="momentoMartes${gridRow.index + 1}" generated="true" class="error"></label>
                                                                    </div>
                                                                </div>

                                                                <div class="row">
                                                                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                            <spring1:bind path="dietaRecomendadaForm.cantidadMartes${gridRow.index + 1}">
                                                                                <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                                                                <input type="text" name="cantidadMartes${gridRow.index + 1}" id="cantidadMartes${gridRow.index + 1}" class="form-control"
                                                                                placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="<c:out value="${ver.cantidad}"/>" maxlength="50"
                                                                                tabindex="4">
                                                                            </spring1:bind>
                                                                        <label for="nombreAlimentoMartes${gridRow.index + 1}" generated="true" class="error"></label>
                                                                        </div>
                                                                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                            <spring1:bind path="dietaRecomendadaForm.observacionesMartes${gridRow.index + 1}">
                                                                                <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                                                                <input type="text" name="observacionesMartes${gridRow.index + 1}" id="observacionesMartes${gridRow.index + 1}" class="form-control"
                                                                                placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="<c:out value="${ver.observacion}"/>" tabindex="12"/>
                                                                            </spring1:bind>
                                                                        <label for="observacionesMartes${gridRow.index + 1}" generated="true" class="error"></label>
                                                                        </div>
                                                                </div>
                                              <c:if test="${gridRow.index > 0}">
                                                <button id="remove${gridRow.index}" class="btn btn-danger remove-anotherMartes" style="margin-top:-40px">-</button>
                                              </c:if>
                                    </div>
                                </c:forEach>
                            <button id="b2" class="btn add-moreMartes" type="button">+</button>
                        </div>
                    <div id="tabs-3">
                                <spring1:bind path="dietaRecomendadaForm.diaDietaMiercoles">
                                    <input type="hidden" name="diaDietaMiercoles" id="diaDietaMiercoles" class="form-control" value="MIERCOLES"/>
                                </spring1:bind>

                                <c:forEach items="${miercoles}" var="ver" varStatus="gridRow">
                                    <div id="fieldsMiercoles24">
                                                                <div class="row">
                                                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                        <spring1:bind path="dietaRecomendadaForm.nombreAlimentoMiercoles${gridRow.index + 1}">
                                                                            <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                                                            <select name="nombreAlimentoMiercoles${gridRow.index + 1}" id="nombreAlimentoMiercoles${gridRow.index + 1}" size="1" class="form-control">
                                                                            <option value="<c:out value="${ver.alimento}"/>"><c:out value="${ver.alimento}"/></option>
                                                                                <c:forEach items="${alimentoTotal}" var="item" >
                                                                                    <option
                                                                                    value="<c:out value="${item}"/>"><c:out value="${item}"/>
                                                                                    </option>
                                                                                </c:forEach>
                                                                            </select>
                                                                        </spring1:bind>
                                                                    <label for="nombreAlimentoMiercoles${gridRow.index + 1}" generated="true" class="error"></label>
                                                                    </div>
                                                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                        <spring1:bind path="dietaRecomendadaForm.momentoMiercoles${gridRow.index + 1}">
                                                                            <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                                                            <select name="momentoMiercoles${gridRow.index + 1}" id="momentoMiercoles${gridRow.index + 1}" size="1" class="form-control">
                                                                            <option value="<c:out value="${ver.momento}"/>"><c:out value="${ver.momento}"/></option>
                                                                                <c:forEach items="${momentoDias}" var="item" >
                                                                                    <option
                                                                                    value="<c:out value="${item}"/>"><c:out value="${item}"/>
                                                                                    </option>
                                                                                </c:forEach>
                                                                            </select>
                                                                        </spring1:bind>
                                                                        <label for="momentoMiercoles${gridRow.index + 1}" generated="true" class="error"></label>
                                                                    </div>
                                                                </div>

                                                                <div class="row">
                                                                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                            <spring1:bind path="dietaRecomendadaForm.cantidadMiercoles${gridRow.index + 1}">
                                                                                <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                                                                <input type="text" name="cantidadMiercoles${gridRow.index + 1}" id="cantidadMiercoles${gridRow.index + 1}" class="form-control"
                                                                                placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="<c:out value="${ver.cantidad}"/>" maxlength="50"
                                                                                tabindex="4">
                                                                            </spring1:bind>
                                                                        <label for="nombreAlimentoMiercoles${gridRow.index + 1}" generated="true" class="error"></label>
                                                                        </div>
                                                                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                            <spring1:bind path="dietaRecomendadaForm.observacionesMiercoles${gridRow.index + 1}">
                                                                                <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                                                                <input type="text" name="observacionesMiercoles${gridRow.index + 1}" id="observacionesMiercoles${gridRow.index + 1}" class="form-control"
                                                                                placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="<c:out value="${ver.observacion}"/>" tabindex="12"/>
                                                                            </spring1:bind>
                                                                        <label for="observacionesMiercoles${gridRow.index + 1}" generated="true" class="error"></label>
                                                                        </div>
                                                                </div>
                                    </div>
                                </c:forEach>
                            <button id="b3" class="btn add-more3" type="button">+</button>
                    </div>

                    <div id="tabs-4">
                                <spring1:bind path="dietaRecomendadaForm.diaDietaMiercolesJueves">
                                    <input type="hidden" name="diaDietaJueves" id="diaDietaJueves" class="form-control" value="JUEVES"/>
                                </spring1:bind>

                                <c:forEach items="${jueves}" var="ver" varStatus="gridRow">
                                    <div id="fieldsJueves34">
                                                                <div class="row">
                                                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                        <spring1:bind path="dietaRecomendadaForm.nombreAlimentoJueves${gridRow.index + 1}">
                                                                            <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                                                            <select name="nombreAlimentoJueves${gridRow.index + 1}" id="nombreAlimentoJueves${gridRow.index + 1}" size="1" class="form-control">
                                                                            <option value="<c:out value="${ver.alimento}"/>"><c:out value="${ver.alimento}"/></option>
                                                                                <c:forEach items="${alimentoTotal}" var="item" >
                                                                                    <option
                                                                                    value="<c:out value="${item}"/>"><c:out value="${item}"/>
                                                                                    </option>
                                                                                </c:forEach>
                                                                            </select>
                                                                        </spring1:bind>
                                                                    <label for="nombreAlimentoJueves${gridRow.index + 1}" generated="true" class="error"></label>
                                                                    </div>
                                                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                        <spring1:bind path="dietaRecomendadaForm.momentoJueves${gridRow.index + 1}">
                                                                            <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                                                            <select name="momentoJueves${gridRow.index + 1}" id="momentoJueves${gridRow.index + 1}" size="1" class="form-control">
                                                                            <option value="<c:out value="${ver.momento}"/>"><c:out value="${ver.momento}"/></option>
                                                                                <c:forEach items="${momentoDias}" var="item" >
                                                                                    <option
                                                                                    value="<c:out value="${item}"/>"><c:out value="${item}"/>
                                                                                    </option>
                                                                                </c:forEach>
                                                                            </select>
                                                                        </spring1:bind>
                                                                        <label for="momentoJueves${gridRow.index + 1}" generated="true" class="error"></label>
                                                                    </div>
                                                                </div>

                                                                <div class="row">
                                                                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                            <spring1:bind path="dietaRecomendadaForm.cantidadJueves${gridRow.index + 1}">
                                                                                <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                                                                <input type="text" name="cantidadJueves${gridRow.index + 1}" id="cantidadJueves${gridRow.index + 1}" class="form-control"
                                                                                placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="<c:out value="${ver.cantidad}"/>" maxlength="50"
                                                                                tabindex="4">
                                                                            </spring1:bind>
                                                                        <label for="nombreAlimentoJueves${gridRow.index + 1}" generated="true" class="error"></label>
                                                                        </div>
                                                                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                            <spring1:bind path="dietaRecomendadaForm.observacionesJueves${gridRow.index + 1}">
                                                                                <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                                                                <input type="text" name="observacionesJueves${gridRow.index + 1}" id="observacionesJueves${gridRow.index + 1}" class="form-control"
                                                                                placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="<c:out value="${ver.observacion}"/>" tabindex="12"/>
                                                                            </spring1:bind>
                                                                        <label for="observacionesJueves${gridRow.index + 1}" generated="true" class="error"></label>
                                                                        </div>
                                                                </div>
                                    </div>
                                </c:forEach>
                            <button id="b4" class="btn add-more4" type="button">+</button>
                    </div>

                    <div id="tabs-5">
                                <spring1:bind path="dietaRecomendadaForm.diaDietaMiercolesViernes">
                                    <input type="hidden" name="diaDietaViernes" id="diaDietaViernes" class="form-control" value="VIERNES"/>
                                </spring1:bind>

                                <c:forEach items="${viernes}" var="ver" varStatus="gridRow">
                                    <div id="fieldsViernes44">
                                                                <div class="row">
                                                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                        <spring1:bind path="dietaRecomendadaForm.nombreAlimentoViernes${gridRow.index + 1}">
                                                                            <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                                                            <select name="nombreAlimentoViernes${gridRow.index + 1}" id="nombreAlimentoViernes${gridRow.index + 1}" size="1" class="form-control">
                                                                            <option value="<c:out value="${ver.alimento}"/>"><c:out value="${ver.alimento}"/></option>
                                                                                <c:forEach items="${alimentoTotal}" var="item" >
                                                                                    <option
                                                                                    value="<c:out value="${item}"/>"><c:out value="${item}"/>
                                                                                    </option>
                                                                                </c:forEach>
                                                                            </select>
                                                                        </spring1:bind>
                                                                    <label for="nombreAlimentoViernes${gridRow.index + 1}" generated="true" class="error"></label>
                                                                    </div>
                                                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                        <spring1:bind path="dietaRecomendadaForm.momentoViernes${gridRow.index + 1}">
                                                                            <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                                                            <select name="momentoViernes${gridRow.index + 1}" id="momentoViernes${gridRow.index + 1}" size="1" class="form-control">
                                                                            <option value="<c:out value="${ver.momento}"/>"><c:out value="${ver.momento}"/></option>
                                                                                <c:forEach items="${momentoDias}" var="item" >
                                                                                    <option
                                                                                    value="<c:out value="${item}"/>"><c:out value="${item}"/>
                                                                                    </option>
                                                                                </c:forEach>
                                                                            </select>
                                                                        </spring1:bind>
                                                                        <label for="momentoViernes${gridRow.index + 1}" generated="true" class="error"></label>
                                                                    </div>
                                                                </div>

                                                                <div class="row">
                                                                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                            <spring1:bind path="dietaRecomendadaForm.cantidadViernes${gridRow.index + 1}">
                                                                                <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                                                                <input type="text" name="cantidadViernes${gridRow.index + 1}" id="cantidadViernes${gridRow.index + 1}" class="form-control"
                                                                                placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="<c:out value="${ver.cantidad}"/>" maxlength="50"
                                                                                tabindex="4">
                                                                            </spring1:bind>
                                                                        <label for="nombreAlimentoViernes${gridRow.index + 1}" generated="true" class="error"></label>
                                                                        </div>
                                                                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                            <spring1:bind path="dietaRecomendadaForm.observacionesViernes${gridRow.index + 1}">
                                                                                <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                                                                <input type="text" name="observacionesViernes${gridRow.index + 1}" id="observacionesViernes${gridRow.index + 1}" class="form-control"
                                                                                placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="<c:out value="${ver.observacion}"/>" tabindex="12"/>
                                                                            </spring1:bind>
                                                                        <label for="observacionesViernes${gridRow.index + 1}" generated="true" class="error"></label>
                                                                        </div>
                                                                </div>
                                    </div>
                                </c:forEach>
                            <button id="b5" class="btn add-more5" type="button">+</button>
                    </div>

                    <div id="tabs-6">
                                <spring1:bind path="dietaRecomendadaForm.diaDietaMiercolesSabado">
                                    <input type="hidden" name="diaDietaSabado" id="diaDietaSabado" class="form-control" value="SABADO"/>
                                </spring1:bind>

                                <c:forEach items="${sabado}" var="ver" varStatus="gridRow">
                                    <div id="fieldsSabado54">
                                                                <div class="row">
                                                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                        <spring1:bind path="dietaRecomendadaForm.nombreAlimentoSabado${gridRow.index + 1}">
                                                                            <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                                                            <select name="nombreAlimentoSabado${gridRow.index + 1}" id="nombreAlimentoSabado${gridRow.index + 1}" size="1" class="form-control">
                                                                            <option value="<c:out value="${ver.alimento}"/>"><c:out value="${ver.alimento}"/></option>
                                                                                <c:forEach items="${alimentoTotal}" var="item" >
                                                                                    <option
                                                                                    value="<c:out value="${item}"/>"><c:out value="${item}"/>
                                                                                    </option>
                                                                                </c:forEach>
                                                                            </select>
                                                                        </spring1:bind>
                                                                    <label for="nombreAlimentoSabado${gridRow.index + 1}" generated="true" class="error"></label>
                                                                    </div>
                                                                    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                        <spring1:bind path="dietaRecomendadaForm.momentoSabado${gridRow.index + 1}">
                                                                            <appfuse:label styleClass="control-label" key="user.nutritionist.nombreAlimento"/>
                                                                            <select name="momentoSabado${gridRow.index + 1}" id="momentoSabado${gridRow.index + 1}" size="1" class="form-control">
                                                                            <option value="<c:out value="${ver.momento}"/>"><c:out value="${ver.momento}"/></option>
                                                                                <c:forEach items="${momentoDias}" var="item" >
                                                                                    <option
                                                                                    value="<c:out value="${item}"/>"><c:out value="${item}"/>
                                                                                    </option>
                                                                                </c:forEach>
                                                                            </select>
                                                                        </spring1:bind>
                                                                        <label for="momentoSabado${gridRow.index + 1}" generated="true" class="error"></label>
                                                                    </div>
                                                                </div>

                                                                <div class="row">
                                                                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                            <spring1:bind path="dietaRecomendadaForm.cantidadSabado${gridRow.index + 1}">
                                                                                <appfuse:label styleClass="control-label" key="user.nutritionist.cantidadAlimento"/>
                                                                                <input type="text" name="cantidadSabado${gridRow.index + 1}" id="cantidadSabado${gridRow.index + 1}" class="form-control"
                                                                                placeholder="<fmt:message key="user.nutritionist.cantidadAlimento"/>" value="<c:out value="${ver.cantidad}"/>" maxlength="50"
                                                                                tabindex="4">
                                                                            </spring1:bind>
                                                                        <label for="nombreAlimentoSabado${gridRow.index + 1}" generated="true" class="error"></label>
                                                                        </div>
                                                                        <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                                                                            <spring1:bind path="dietaRecomendadaForm.observacionesSabado${gridRow.index + 1}">
                                                                                <appfuse:label styleClass="control-label" key="user.nutritionist.observaciones" />
                                                                                <input type="text" name="observacionesSabado${gridRow.index + 1}" id="observacionesSabado${gridRow.index + 1}" class="form-control"
                                                                                placeholder="<fmt:message key="user.nutritionist.observaciones"/>" value="<c:out value="${ver.observacion}"/>" tabindex="12"/>
                                                                            </spring1:bind>
                                                                        <label for="observacionesSabado${gridRow.index + 1}" generated="true" class="error"></label>
                                                                        </div>
                                                                </div>
                                    </div>
                                </c:forEach>
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
				var dato = $("#dato").val();
				window.location.href = "${ctx}/nutricionista/dietaList?search="+dato;
			});
	</script>
	</script>
</c:set>

<v:javascript formName="endocrinologoForm" staticJavascript="false" />
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
