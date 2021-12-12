/**
 * 
 */
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

function getBase64(file) {
	  return new Promise((resolve, reject) => {
	    const reader = new FileReader();
	    reader.readAsDataURL(file);
	    reader.onload = () => resolve(reader.result);
	    reader.onerror = error => reject(error);
	  });
}


function alteraImagem(data){
	var path = $("#path");
	$("#imgDefault").attr("src",jQuery.isBlank(data) ? path.val() + "/assets/images/placeholder.jpg" : data);
	$("#dadosImagem").val(data);
}

function removeImagem(){
	$("#imgDefault").show();
	$("#imgUsuario").hide();
	$("#imgUsuario").attr("src","");
	$("#dadosImagem").val("");
}


function redirect(){
	
	var path = $("#path");

	const swalWithBootstrapButtons = Swal.mixin({
		  confirmButtonClass: 'btn btn-success',		  
		  buttonsStyling: true,
		})

	swalWithBootstrapButtons({
		  title: 'Aviso',
		  text: "O sistema vai ser reiniciado!",
		  type: 'warning',		  
		  padding: '10px',
		  showCancelButton: false,
		  confirmButtonColor: '#3085d6',
		  confirmButtonText: 'Ok'
		}).then((result) => {
		  if (result.value) {
			  location.href = path.val() + "/autenticacao/logout";			  
		  }
	});	
}


var meucadastro = function() {

	jQuery.isBlank = function(obj){
		return(!obj || jQuery.trim(obj) === "");
	};


	 var runSelect = function() {

		    // Fixed width. Single select
	    $('#tipousuario').select2({
	        minimumResultsForSearch: Infinity,
	        width: 250
	    });

		$('.select').select2();

	    // Checkboxes, radios
	    $(".styled").uniform({ radioClass: 'choice' });

	    // File input
	    $(".file-styled").uniform({
	        fileButtonHtml: '<i class="icon-googleplus5"></i>',
	        wrapperClass: 'bg-warning'
	    });
	    


	 }

	   var  runValidator = function(){
		   
		    var path = $("#path");
	        

	        $('#frm_usuario').validate({
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
	            	novasenhausuario: {
	                    required: true
	                },
	                confirmasenhausuario: {
	                    required: true,
	                    equalTo: "#novasenhausuario"
	                }
	                
	                
	            },
	            submitHandler: function (form) {

					jQuery.ajax({
						 type: "POST",
						 contentType: "application/x-www-form-urlencoded;charset=UTF-8",
						 url: path.val() + "/usuario/meucadastro",
						 cache: false,
						 data: {
							 "codigo":jQuery('input[name="codigo"]').val(),							 
							 "novaSenha":jQuery('input[name="novasenhausuario"]').val(),
							 "imagem":jQuery('input[name="dadosImagem"]').val()
						 },
					   	 beforeSend: function( ) {
					   		loading(".content");
						 },
						 success: function(msg){

							 $(".content").unblock();

							if(!jQuery.isBlank(msg)){
								$("#error-alert").show();
								$("#success-alert").hide();
								jQuery("#mensageProblema").html(msg).show();
							}else{
								$("#error-alert").hide();
								$("#success-alert").show();
								$("#success-alert").fadeOut( 5000, function() {
								    $("#success-alert").hide();
								});
								
								redirect();
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


		};

	 
	var runSituacao = function(){
		var acao = $("#acao").val();
		var path = $("#path");
		if($.trim(acao) == "login"){
			location.href = path.val();
		}
	} 
	
	var runEvent = function(){
		  $("#inputimagem").on('change', function() {
				  var file = document.querySelector('#inputimagem').files[0];
				  getBase64(file).then(
				    data => alteraImagem(data)
				  );
		  });
		  
	    $( "#btnOpenFile" ).click(function() {
	    	$('#inputimagem').trigger('click'); 
	    });
	    
	    $( "#btnRemoveFile" ).click(function() {
	    	removeImagem();
	    });	    
	}
	

	return {
		init: function() {
			runValidator();
			runSelect();
			runSituacao();
			runEvent();
			
		}
	};

}();
