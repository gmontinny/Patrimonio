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

function getBase64(file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = error => reject(error);
  });
}

//Custom top position
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

function clear_form_elements(ele) {

    $(ele).find(':input').each(function() {
        switch(this.type) {
            case 'password':
            case 'select-multiple':
            case 'select-one':
            case 'text':
            case 'textarea':
                $(this).val('');
                break;
            case 'checkbox':
            case 'radio':
                this.checked = false;
        }
    });

    var block = $(this).parent().parent().parent().parent().parent();
    $(block).block({
        message: '<i class="icon-spinner2 spinner"></i>',
        overlayCSS: {
            backgroundColor: '#fff',
            opacity: 0.8,
            cursor: 'wait',
            'box-shadow': '0 0 0 1px #ddd'
        },
        css: {
            border: 0,
            padding: 0,
            backgroundColor: 'none'
        }
    });

    jQuery("#codigo").val("");
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

function cadastros(url){
	
	var path = $("#path");

	$('.dropdown-menu-right').children('li').removeClass('active');
	$('#menu-'+url).parent().addClass('active');
	
	jQuery.ajax({
			 type: "POST",
			 contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			 url: path.val() +"/"+url+"/cadastro",
			 cache: false,  
		   	 beforeSend: function( ) {
		   		loading(".page-content");	 
			 },				 
			 success: function(msg){			
				$(".page-content").unblock();
				
				var n = $.trim(msg.indexOf("Error")); 
				
				if(n != -1){	
					show_stack_custom_top('error',$.trim(msg));	
				}else{
					jQuery(".page-content").html(msg).show();
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

function dadosUsuarios(){
	
	var path = $("#path");
	
	jQuery.ajax({
			 type: "POST",
			 contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			 url: path.val() +"/administrativo/meucadastro",
			 cache: false,  
		   	 beforeSend: function( ) {
		   		loading(".page-content");	 
			 },				 
			 success: function(msg){			
				$(".page-content").unblock();
				
				var n = $.trim(msg.indexOf("Error")); 
				
				if(n != -1){	
					show_stack_custom_top('error',$.trim(msg));	
				}else{
					jQuery(".page-content").html(msg).show();
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


var Admin = function() {
   jQuery.isBlank = function(obj){
		return(!obj || jQuery.trim(obj) === "");
   };
   
   
	return {
		init: function() {
			
		}
	};
	   
}();	   
