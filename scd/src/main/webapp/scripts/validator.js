$(document).ready(function(){
	function validate() {
		$("#formulario").validate({
			rules:{
				nombre:{
					required:true
				},
				firstName:{
					required:true
				},
				lastName:{
					required:true
				},
				nombreGenerico:{
					required:true
				},
				nombreComercial:{
					required:true
				},
				presentacion:{
					required:true
				},
				sitio:{
					required:true,
					url:true
				},
				email:{
					required:true,
					email:true
				},
				password:{
					required:true,
					minlength:6
				},
				confirmPassword:{
					required:true,
					equalTo:"#password"
				},
				clave:{
					required:true,
					minlength:6
				},
				clave2:{
					required:true,
					equalTo:"#clave"
				},
				cancel:{
					
				},
				phoneNumber:{
					digits:true
				},
				matricula:{
					required:true,
					digits:true
				},
				calle:{
					required:true
				},
				numero:{
					required:true
				},
				dni:{
					required:true,
					number:true,
					minlength:7
				},
				sexo:{
					required:true
				},
				fch_nac:{
					required:true
				},
				provincia:{
					required:true
				},
				localidad:{
				}
			},
			success:function(etiqueta){
				console.log(etiqueta);
				$(etiqueta).addClass("valid");
			}
		});
	}

	validate();

	$("#formulario input").blur(function() {
		validate();
	});

	$("#formulario input").focusout(function()
	{
		if ( !$(this).val() ) {
			$(this).parent().find(".error").text("Error! El campo se encuentra vacio");
		}
	});
	
});