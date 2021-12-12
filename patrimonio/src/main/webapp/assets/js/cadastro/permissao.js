/**
 * 
 */


var tabela = [];
var select = [];
var insert = [];
var update = [];
var deleta = [];
var relatorios = [];

function adicionar(){
	
	tabela = [];
	
	$("input[name='chkTabela']:checked").each(function ()
	{	
		tabela.push($(this).val());
	});
	
	select = [];
	
	$("input[name='chkSelect']:checked").each(function ()
	{
		select.push($(this).val());
	});	
	
	insert = [];
	
	$("input[name='chkInsert']:checked").each(function ()
	{
		insert.push($(this).val());
	});		
	
	update = [];
	
	$("input[name='chkUpdate']:checked").each(function ()
	{
		update.push($(this).val());
	});
	
	deleta = [];

	$("input[name='chkDelete']:checked").each(function ()
	{
		deleta.push($(this).val());
	});
	
}


function getEnviar(){
	
	var path = $("#path");
	
	adicionar();
	
    jQuery.ajax({
  		 type: "POST",
  		 contentType: "application/x-www-form-urlencoded;charset=UTF-8",
  		 url: path.val() + "/permissao/salvar",
  		 cache: false,  
  		 data: {	
  			 "idusuario":jQuery('input[name="codigo"]').val(),
  			 "tabela":tabela,
  			 "select":select,
  			 "insert":insert,
  			 "update":update,
  			 "delete":deleta
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
				clear_form_elements("#frm_permissao");
				$("#permissaousuario").val(null).trigger('change');
				
				$("#tabela").hide();
				
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

function montarTabela(id){
	
	if (id != ""){
	
		$("#tabela").show();
		var path = $("#path");
		var corpo = $("#corpo");
		corpo.empty();
		
		$("#codigo").val(id);
		
		$.getJSON(path.val() + "/permissao/listaTabela",{"codigo" : id}, function( data ) {
			  $.each( data, function( key, val ) {
				  var linha = $("<tr>");
				  var nomeTabela = $("<td>");
				  var ordem = $("<td>");
				  var select = $("<td>");
				  var insert = $("<td>");
				  var update = $("<td>");
				  var deletar = $("<td>");
				  
				  var itemTabela = val.select == 1 ? "checked" : "";
				  var itemSelect = val.select == 1 ? "checked" : "";
				  var itemInsert = val.insert == 1 ? "checked" : "";
				  var itemUpdate = val.update == 1 ? "checked" : "";
				  var itemDelete = val.delete == 1 ? "checked" : "";
				  
				  
				  nomeTabela.html('	<div class="form-group"> ' +
						'<div class="checkbox"> ' +
							'<label> '+
								'<input type="checkbox" class="styled chkTabela" id="chkTabela " '+itemTabela+'  name="chkTabela" value="'+val.idtabela+'"> '  +
								val.desctabela +
							'</label> '+
						'</div> ' +
					'</div>');
				  
				  ordem.attr("class","text-center col-md-1");
				  ordem.text(val.ordtabela);
				  
				  
				  select.html('	<div class="form-group"> ' +
							'<div class="checkbox"> ' +
								'<label> '+
									'<input type="checkbox" class="styled chkSelect" id="chkSelect" '+itemSelect+'  name="chkSelect" value="'+val.idtabela+'"> ' +							
								'</label> '+
							'</div> ' +
						'</div>');
				  
				  insert.html('	<div class="form-group"> ' +
							'<div class="checkbox"> ' +
								'<label> '+
									'<input type="checkbox" class="styled chkInsert" id="chkInsert" '+itemInsert+'  name="chkInsert" value="'+val.idtabela+'"> ' +							
								'</label> '+
							'</div> ' +
						'</div>');
				  
				  
				  update.html('	<div class="form-group"> ' +
							'<div class="checkbox"> ' +
								'<label> '+
									'<input type="checkbox" class="styled chkUpdate" id="chkUpdate" '+itemUpdate+'  name="chkUpdate" value="'+val.idtabela+'"> ' +							
								'</label> '+
							'</div> ' +
						'</div>');	
				  
				  
				  deletar.html('	<div class="form-group"> ' +
							'<div class="checkbox"> ' +
								'<label> '+
									'<input type="checkbox" class="styled chkDelete" id="chkDelete" '+itemDelete+'  name="chkDelete" value="'+val.idtabela+'"> ' +							
								'</label> '+
							'</div> ' +
						'</div>');
	
				  linha.append(nomeTabela);
				  linha.append(ordem);
				  linha.append(select);
				  linha.append(insert);
				  linha.append(update);
				  linha.append(deletar);
				  
				  corpo.append(linha);
				  
				  $(".styled").uniform({ radioClass: 'choice' });
				  
			  });
		
		});
	}
}

var permissao = function() {
	
	
	jQuery.isBlank = function(obj){
		return(!obj || jQuery.trim(obj) === "");
	};	
	
	 var runSelect = function() {
		
		 var path = $("#path");
		 
		 $(".select").select2({			    
			 	minimumResultsForSearch: Infinity,
			    "language": "pt-BR",
			    ajax: {
			        url: path.val() + "/usuario/grid",
			        dataType: "json",
			        type: "GET",
			    
			        processResults: function (data) {
			            return {
			                results: $.map(data, function (item) {
			                    return {
			                        text: item.nomusuario,
			                        id: item.idusuario
			                    }
			                })
			            };
			        }
			    }
			}).on("change", function (e) {				
				montarTabela(this.value);
			});	 
		 
		 
	    // Checkboxes, radios
	    $(".styled").uniform({ radioClass: 'choice' });

	    // File input
	    $(".file-styled").uniform({
	        fileButtonHtml: '<i class="icon-googleplus5"></i>',
	        wrapperClass: 'bg-warning'
	    });
	    
	 }	
	
	var runCheckbox = function(){
	    $(".styled, .multiselect-container input").uniform({
	        radioClass: 'choice'
	    });
	}
	
	var runEvent = function(){
		$( "#frm_permissao" ).submit(function( event ) {
			getEnviar();
			event.preventDefault();
		});
		
	    $("#chkTodos").click(function(){	 
	    	if($(this).is(':checked')){
	    		$(".chkTabela").parent().addClass("checked");
	    		$(".chkSelect").parent().addClass("checked");
	    		$(".chkInsert").parent().addClass("checked");
	    		$(".chkUpdate").parent().addClass("checked");
	    		$(".chkDelete").parent().addClass("checked");
	    	}else{
	    		$(".chkTabela").parent().removeClass("checked");
	    		$(".chkSelect").parent().removeClass("checked");
	    		$(".chkInsert").parent().removeClass("checked");
	    		$(".chkUpdate").parent().removeClass("checked");
	    		$(".chkDelete").parent().removeClass("checked");	    		
	    	}
	    	
	    	$('input:checkbox').prop('checked', this.checked);  
	    	
	    	
	    });
		
	}

	return {
		init: function() {			
			runCheckbox();
			runSelect();
			runEvent();
		}
	};
 
}();