/**
 * 
 */
var stack_custom_top = {"dir1": "down", "dir2": "right", "push": "top", "spacing1": 1};

jQuery.extend(jQuery.validator.messages, {
        required: "Este campo &eacute; requerido.",
        remote: "Por favor, corrija este campo.",
        email: "Por favor, forne&ccedil;a um endere&ccedil;o eletr&ocirc;nico v&aacute;lido.",
        url: "Por favor, forne&ccedil;a uma URL v&aacute;lida.",
        date: "Por favor, forne&ccedil;a uma data v&aacute;lida.",
        dateISO: "Por favor, forne&ccedil;a uma data v&aacute;lida (ISO).",
        dateDE: "Bitte geben Sie ein gültiges Datum ein.",
        number: "Por favor, forne&ccedil;a um n&uacute;mero v&aacute;lida.",
        numberDE: "Bitte geben Sie eine Nummer ein.",
        digits: "Por favor, forne&ccedil;a somente d&iacute;gitos.",
        creditcard: "Por favor, forne&ccedil;a um cart&atilde;o de cr&eacute;dito v&aacute;lido.",
        equalTo: "Por favor, forne&ccedil;a o mesmo valor novamente.",
        accept: "Por favor, forne&ccedil;a um valor com uma extens&atilde;o v&aacute;lida.",
        maxlength: jQuery.validator.format("Por favor, forne&ccedil;a n&atilde;o mais que {0} caracteres."),
        minlength: jQuery.validator.format("Por favor, forne&ccedil;a ao menos {0} caracteres."),
        rangelength: jQuery.validator.format("Por favor, forne&ccedil;a um valor entre {0} e {1} caracteres de comprimento."),
        range: jQuery.validator.format("Por favor, forne&ccedil;a um valor entre {0} e {1}."),
        max: jQuery.validator.format("Por favor, forne&ccedil;a um valor menor ou igual a {0}."),
        min: jQuery.validator.format("Por favor, forne&ccedil;a um valor maior ou igual a {0}.")
});

jQuery.validator.addMethod("verificaCPF", function(value, element) {
	value = value.replace('.','');
	value = value.replace('.','');
	cpf = value.replace('-','');
	while(cpf.length < 11) cpf = "0"+ cpf;
	var expReg = /^0+$|^1+$|^2+$|^3+$|^4+$|^5+$|^6+$|^7+$|^8+$|^9+$/;
	var a = [];
	var b = new Number;
	var c = 11;
	for (i=0; i<11; i++){
		a[i] = cpf.charAt(i);
		if (i < 9) b += (a[i] * --c);
	}
	if ((x = b % 11) < 2) { a[9] = 0 } else { a[9] = 11-x }
	b = 0;
	c = 11;
	for (y=0; y<10; y++) b += (a[y] * c--);
		if ((x = b % 11) < 2) { a[10] = 0; } else { a[10] = 11-x; }
		if ((cpf.charAt(9) != a[9]) || (cpf.charAt(10) != a[10]) || cpf.match(expReg)) return false;
			return true;
}, "CPF invalido !");

// Custom top position
function show_stack_custom_top(type,message) {
    var opts = {
        title: "Over here",
        text: "Check me out. I'm in a different stack.",
        width: "100%",
        cornerclass: "no-border-radius",
        addclass: "stack-custom-top bg-primary",
        stack: stack_custom_top
    };
    switch (type) {
        case 'error':
        opts.title = "Error";
        opts.text = message;
        opts.addclass = "stack-custom-top bg-danger";
        opts.type = "error";
        break;

        case 'info':
        opts.title = "Breaking News";
        opts.text = "Have you met Ted?";
        opts.addclass = "stack-custom-top bg-info";
        opts.type = "info";
        break;

        case 'success':
        opts.title = "Good News Everyone";
        opts.text = "I've invented a device that bites shiny metal asses.";
        opts.addclass = "stack-custom-top bg-success";
        opts.type = "success";
        break;
    }
    new PNotify(opts);
}


function exibeEsqueceuSenha(){
	$("#cadastro-esqueceu").show();
	$("#cadastro-login").hide();
}

function loading(element){
    $(element).block({
        message: '<i class="icon-spinner2 spinner"></i>',
        overlayCSS: {
            backgroundColor: '#fff',
            opacity: 0.8,
            cursor: 'wait'
        },
        css: {
            border: 0,
            padding: 0,
            backgroundColor: 'none'
        }
    });
}

function enviarSenha(email){
	
	var path = $("#path");
	
	jQuery.ajax({
		 type: "POST",
		 contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		 url: path.val() + "/index/resetaSenha",
		 cache: false,  
		 data: {	
			 "email":email			 							 							 
			 
		 },		 
	   	 beforeSend: function( ) {
	   		loading("#frm_esqueceu");	 
		 },				 
		 success: function(msg){
			
			$("#frm_esqueceu").unblock();
			 
			if($.trim(msg) == "OK"){
				
				swal({
					  type: 'success',
					  title: 'Resetar Senha',
					  text: 'Sua senha foi resetada, e foi encaminhada para o seu EMAIL!'
					});
				
				$("#email").val("");
				
			}else{
				
				swal({
					  type: 'error',
					  title: 'Resetar Senha',
					  text: 'Houve um problema ao enviar para seu EMAIL!'
					});					

			}	
		 }
	}).fail( function( jqXHR, textStatus, errorThrown ) {
       if (jqXHR.status === 0) {			    
           alert('Not connect: Verify Network.');
       } else if (jqXHR.status == 404) {
          location.href= path.val()+"/error/error404";
       } else if (jqXHR.status == 500) {
       	location.href= path.val()+"/error/error500";
       } else if (textStatus === 'parsererror') {
           alert('Requested JSON parse failed.');
       } else if (textStatus === 'timeout') {
           alert('Time out error.');
       } else if (textStatus === 'abort') {
           alert('Ajax request aborted.');
       } else {
           alert('Uncaught Error: ' + jqXHR.responseText);
       }

   });	 	 
	
}

var Login = function() {
	   jQuery.isBlank = function(obj){
			return(!obj || jQuery.trim(obj) === "");
	   };
	   
	   var mascara = function() {
		   $('#cpf').mask('000.000.000-00', {reverse: true});
	   };
	   
	   var  runValidator = function(){
		    
		    var path = $("#path");
	        
	        $('#frm_login').validate({	        	
	            ignore: 'input[type=hidden], .select2-search__field, :hidden:not(#summernote),.note-editable.panel-body', // ignore hidden fields
	            errorClass: 'validation-error-label',
	            successClass: 'validation-valid-label',
	            highlight: function(element, errorClass) {
	                $(element).removeClass(errorClass);
	            },
	            unhighlight: function(element, errorClass) {
	                $(element).removeClass(errorClass);
	            },

	            // Different components require proper error label placement
	            errorPlacement: function(error, element) {

	                // Styled checkboxes, radios, bootstrap switch
	                if (element.parents('div').hasClass("checker") || element.parents('div').hasClass("choice") || element.parent().hasClass('bootstrap-switch-container') ) {
	                    if(element.parents('label').hasClass('checkbox-inline') || element.parents('label').hasClass('radio-inline')) {
	                        error.appendTo( element.parent().parent().parent().parent() );
	                    }
	                     else {
	                        error.appendTo( element.parent().parent().parent().parent().parent() );
	                    }
	                }

	                // Unstyled checkboxes, radios
	                else if (element.parents('div').hasClass('checkbox') || element.parents('div').hasClass('radio')) {
	                    error.appendTo( element.parent().parent().parent() );
	                }

	                // Input with icons and Select2
	                else if (element.parents('div').hasClass('has-feedback') || element.hasClass('select2-hidden-accessible')) {
	                    error.appendTo( element.parent() );
	                }

	                // Inline checkboxes, radios
	                else if (element.parents('label').hasClass('checkbox-inline') || element.parents('label').hasClass('radio-inline')) {
	                    error.appendTo( element.parent().parent() );
	                }

	                // Input group, styled file input
	                else if (element.parent().hasClass('uploader') || element.parents().hasClass('input-group')) {
	                    error.appendTo( element.parent().parent() );
	                }

	                else {
	                    error.insertAfter(element);
	                }
	            },
	            validClass: "validation-valid-label",
	            success: function(label) {
	                label.addClass("validation-valid-label").text("Success.");
					$( ".validation-valid-label" ).each(function( index ) {								
						$(this).remove();									
					});
	            },
	            rules: {
	            	cpf:{
	            		verificaCPF: true,
	            		required: true
	            	},
	            	senha: {
	                    minlength: 6,
	                    required: true
	                }
	            },
	            submitHandler: function (form) {
					jQuery.ajax({
						 type: "POST",
						 contentType: "application/x-www-form-urlencoded;charset=UTF-8",
						 url: path.val() + "/autenticacao/valida",
						 cache: false,  
						 data: {	
							 "cpf":jQuery('input[name="cpf"]').val(),
							 "senha":jQuery('input[name="senha"]').val()							 							 
							 
						 },		 
					   	 beforeSend: function( ) {
					   		loading("#frm_login");	 
						 },				 
						 success: function(msg){
							
							 $("#frm_login").unblock();
							 
							if(!jQuery.isBlank(msg)){	
								show_stack_custom_top('error',$.trim(msg));	
							}else{
								location.href = path.val() + "/autenticacao/index";	
							}	
						 }
					}).fail( function( jqXHR, textStatus, errorThrown ) {
				        if (jqXHR.status === 0) {			    
				            alert('Not connect: Verify Network.');
				        } else if (jqXHR.status == 404) {
				           location.href= path.val()+"/error/error404";
				        } else if (jqXHR.status == 500) {
				        	location.href= path.val()+"/error/error500";
				        } else if (textStatus === 'parsererror') {
				            alert('Requested JSON parse failed.');
				        } else if (textStatus === 'timeout') {
				            alert('Time out error.');
				        } else if (textStatus === 'abort') {
				            alert('Ajax request aborted.');
				        } else {
				            alert('Uncaught Error: ' + jqXHR.responseText);
				        }
	
				    });	 	 
	                
	                
	            }
	        });
	        
	        
	        $('#frm_esqueceu').validate({	        	
	            ignore: 'input[type=hidden], .select2-search__field, :hidden:not(#summernote),.note-editable.panel-body', // ignore hidden fields
	            errorClass: 'validation-error-label',
	            successClass: 'validation-valid-label',
	            highlight: function(element, errorClass) {
	                $(element).removeClass(errorClass);
	            },
	            unhighlight: function(element, errorClass) {
	                $(element).removeClass(errorClass);
	            },

	            // Different components require proper error label placement
	            errorPlacement: function(error, element) {

	                // Styled checkboxes, radios, bootstrap switch
	                if (element.parents('div').hasClass("checker") || element.parents('div').hasClass("choice") || element.parent().hasClass('bootstrap-switch-container') ) {
	                    if(element.parents('label').hasClass('checkbox-inline') || element.parents('label').hasClass('radio-inline')) {
	                        error.appendTo( element.parent().parent().parent().parent() );
	                    }
	                     else {
	                        error.appendTo( element.parent().parent().parent().parent().parent() );
	                    }
	                }

	                // Unstyled checkboxes, radios
	                else if (element.parents('div').hasClass('checkbox') || element.parents('div').hasClass('radio')) {
	                    error.appendTo( element.parent().parent().parent() );
	                }

	                // Input with icons and Select2
	                else if (element.parents('div').hasClass('has-feedback') || element.hasClass('select2-hidden-accessible')) {
	                    error.appendTo( element.parent() );
	                }

	                // Inline checkboxes, radios
	                else if (element.parents('label').hasClass('checkbox-inline') || element.parents('label').hasClass('radio-inline')) {
	                    error.appendTo( element.parent().parent() );
	                }

	                // Input group, styled file input
	                else if (element.parent().hasClass('uploader') || element.parents().hasClass('input-group')) {
	                    error.appendTo( element.parent().parent() );
	                }

	                else {
	                    error.insertAfter(element);
	                }
	            },
	            validClass: "validation-valid-label",
	            success: function(label) {
	                label.addClass("validation-valid-label").text("Success.");
					$( ".validation-valid-label" ).each(function( index ) {								
						$(this).remove();									
					});
	            },
	            rules: {
	            	email:{
	            		email: true,
	            		required: true
	            	}
	            },
	            submitHandler: function (form) {
	            	$.getJSON(path.val()+"/index/validaEmail",{"email" : $("#email").val()}, function( data ) {
	            		if(data.resultado){
	            			
	            			enviarSenha($("#email").val());
	            			
	            		}else{
	            			show_stack_custom_top('error',"Email não cadastrado");
	            		}
	            		//console.log(data.resultado);
	            	});	
	            }
	        });
	        
	        
	        
		};	 
	   

		var runEvent = function(){
			$( "#linkEsqueceu" ).click(function() {
				exibeEsqueceuSenha();
			});
		}
		
		return {
			init: function() {
				mascara(); 
				runValidator();
				runEvent();
			}
		};
	   
}();