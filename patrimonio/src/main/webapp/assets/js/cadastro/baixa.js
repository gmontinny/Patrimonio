/**
 * 
 */

function getImprimir(id){
	var path = $("#path");
	var url = path.val() + "/baixaanalitico/pdf?codigo="+id
	window.open(url, '_blank');
}

function getImprimirBaixaSintetico(id){
	var path = $("#path");
	var url = path.val() + "/baixasintetico/pdf?codigo="+id
	window.open(url, '_blank');
}


function pesquisaSetor(id){
	var path = $("#path");
	
	if(id){
	    $('#setor').select2({
	        minimumResultsForSearch: Infinity,        
		    ajax: {
		        url: path.val() + "/setor/pesquisaSetorPorSecretaria?idsecretaria="+id,
		        dataType: "json",
		        type: "GET",
		        processResults: function (data) {
		            return {
		                results: $.map(data, function (item) {
		                    return {
		                        text: item.nomsetor,
		                        id: item.idsetor
		                    }
		                })
		            };
		        }
		    }	        
	    }).on("change", function(e) {	
			$('#local').select2('enable');
			pesquisaLocal(this.value);
	    });
	    
	}else{
		show_stack_custom_top('error','Secretaria não foi selecionada !');
	}
}

function pesquisaLocal(id){
	var path = $("#path");
	
	$("#btnConfirmaBaixaGeral").prop("disabled",true);
	
	if(id){
	    $('#local').select2({
	        minimumResultsForSearch: Infinity,        
		    ajax: {
		        url: path.val() + "/local/pesquisaLocalPorSetor?idsetor="+id,
		        dataType: "json",
		        type: "GET",
		        processResults: function (data) {
		            return {
		                results: $.map(data, function (item) {
		                    return {
		                        text: item.desclocal,
		                        id: item.idlocal
		                    }
		                })
		            };
		        }
		    }	        
	    }).on("change", function(e) {	
			if(this.value){				
				$("#btnConfirmaBaixaGeral").prop("disabled",false);	
			}else{
				$("#btnConfirmaBaixaGeral").prop("disabled",true);
			}			
	    });
	    
	}else{
		show_stack_custom_top('error','Setor não foi selecionada !');
	}
}

function atualizaGrid(){
	
	$("#exibirPesquisaBaixa").hide();
	$("#exibeBotaoBaixa").hide();
	$("#exibeGrid").show();
	
	var table = $('#grid-baixa').DataTable();
	table.destroy();
	
    $.extend( $.fn.dataTable.defaults, {
        autoWidth: false,
        dom: '<"datatable-header"fl><"datatable-scroll"t><"datatable-footer"ip>',
        language: {
            search: '<span>Pesquisa:</span> _INPUT_',
            "sInfo":         "Mostrando de _START_ até _END_ de _TOTAL_ registros",
            lengthMenu: '<span>Exibir:</span> _MENU_',
            paginate: { 'first': 'First', 'last': 'Last', 'next': '&rarr;', 'previous': '&larr;' }
        },
        drawCallback: function () {
            $(this).find('tbody tr').slice(-3).find('.dropdown, .btn-group').addClass('dropup');
        },
        preDrawCallback: function() {
            $(this).find('tbody tr').slice(-3).find('.dropdown, .btn-group').removeClass('dropup');
        }
    });


    var path = $("#path");

	$('#grid-baixa').DataTable({
        "ajax": {
            "url": path.val() + "/baixa/grid",
            "dataSrc": ""
        },
        columnDefs: [
	        {
	           width: '10%',
	           targets: [ 0 ]
	        },
	        {
	           width: '10%',	
		       targets: [ 1 ]
		    },
	        {
			   targets: [ 2 ]
	        },	        
	        {
			   targets: [ 3 ]
	        },	        
	        {
		       targets: [ 4 ]
		    },	        
		    
	        {
	            width: '5%',
	            targets: [ 5 ],
	            className: "text-center",
	            render: function(data, type, full, meta){
	               if(type === 'display'){
	                  data = '<a href="javascript:" data-popup="tooltip" title="Imprimir" class="text-default" onclick="getImprimir('+data+');"><i class="icon-shredder"></i></a>&nbsp;'
	                  + '<a href="javascript:" data-popup="tooltip" title="Remover" class="text-default" onclick="getDeletar('+full.idbaixa+');"><i class="icon-trash"></i></a>';
	               }
	               return data;
	            }

	        }

 		],
        "columns": [
            { "data": "idpatrimonio" },
            { "data": "serialpatrimonio" },
            { "data": "nomsecretaria" },
            { "data": "nomsetor" },
            { "data": "desclocal" },
            { "data": "idlocal" }
        ],
		lengthMenu: [[10, 25, 50, -1], [10, 25, 50, "Todos"]]
	});


    // DOM positioning
    $('.datatable-dom-position').DataTable({
        dom: '<"datatable-header length-left"lp><"datatable-scroll"t><"datatable-footer info-right"fi>',
    });


    // Highlighting rows and columns on mouseover
    var lastIdx = null;
    var table = $('.datatable-highlight').DataTable();

    $('.datatable-highlight tbody').on('mouseover', 'td', function() {
        var colIdx = table.cell(this).index().column;

        if (colIdx !== lastIdx) {
            $(table.cells().nodes()).removeClass('active');
            $(table.column(colIdx).nodes()).addClass('active');
        }
    }).on('mouseleave', function() {
        $(table.cells().nodes()).removeClass('active');
    });


    // Columns rendering
    $('.datatable-columns').dataTable({
        columnDefs: [
            {
                // The `data` parameter refers to the data for the cell (defined by the
                // `data` option, which defaults to the column being worked with, in
                // this case `data: 0`.
                render: function (data, type, row) {
                    return data +' ('+ row[3]+')';
                },
                targets: 0
            },
            { visible: false, targets: [ 3 ] }
        ]
    });



    // External table additions
    // ------------------------------

    // Add placeholder to the datatable filter option
    $('.dataTables_filter input[type=search]').attr('placeholder','Tipo de Filtro...');


    // Enable Select2 select for the length option
    $('.dataTables_length select').select2({
        minimumResultsForSearch: Infinity,
        width: 'auto'
    });
	
}

function getBaixar(){

	var path = $("#path");

	const swalWithBootstrapButtons = Swal.mixin({
		  confirmButtonClass: 'btn btn-success',
		  cancelButtonClass: 'btn btn-danger',
		  buttonsStyling: true,
		})

	swalWithBootstrapButtons({
		  title: 'Avisos',
		  text: "Deseja baixar esse patrimônio!",
		  type: 'warning',		  
		  padding: '10px',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Sim, pode baixar!',
		  cancelButtonText: "Não, Cancelar!"
		}).then((result) => {
		  if (result.value) {
			  
	          jQuery.ajax({
	        		 type: "POST",
	        		 contentType: "application/x-www-form-urlencoded;charset=UTF-8",
	        		 url: path.val() + "/baixa/salvar",
	        		 cache: false,
	        		 data: {
	        			 "baixa.idpatrimonio":$("#numprocesso").val()
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
								
								$(window).scrollTop($('#error-alert').offset().top);
								
							}else{
								$("#error-alert").hide();
								$("#success-alert").show();
								$("#success-alert").fadeOut( 5000, function() {
								    $("#success-alert").hide();
								});

								clear_form_elements("#frm_grupo");
								
								Swal(
										'Baixado!',
										'Seus dados foram baixados.',
										'success'
								).then((result) => {
									if (result.value) {
										atualizaGrid();
									}
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
			  
			  
		  }else if (
				    // Read more about handling dismissals
				    result.dismiss === Swal.DismissReason.cancel
				  ) {
				    swalWithBootstrapButtons(
				      'Ação Cancelada!',
				      'Seus dados foram cancelados :)',
				      'error'
				    )
				  }
		})
	

}



function exibiPesquisaBaixa(){
	var path = $("#path");
	$("#exibirPesquisaBaixa").show();
	$("#exibeBotaoBaixa").show();	
	
	$.getJSON(path.val() + "/patrimonio/pesquisa",{"numeroPatrimonio" : $("#numeropatrimonio").val(), "serial":$("#numeroserial").val()}, function( data ) {
		$.each( data, function( key, val ) {
			$("#nomesecretaria").text(val.nomsecretaria);
			$("#nomesetor").text(val.nomsetor);
			$("#nomelocal").text(val.desclocal);
			$("#nomegrupo").text(val.descgrupo);
			$("#nomesubgrupo").text(val.descsubgrupo);
			$("#nomematerial").text(val.descmaterial);
			$("#nomemarca").text(val.descmarca);
			$("#nomemodelo").text(val.descmodelo);
			$("#nomefornecedor").text(val.razaosocialfornecedor);
			$("#numprocesso").val(val.idpatrimonio);
			$("#imgPatrimonio").attr("src",jQuery.isBlank(val.imgpatrimonio) ? path.val() + "/assets/images/placeholder.jpg": val.imgpatrimonio);
		});
	});	
}

function getDeletar(id){

	var path = $("#path");

	const swalWithBootstrapButtons = Swal.mixin({
		  confirmButtonClass: 'btn btn-success',
		  cancelButtonClass: 'btn btn-danger',
		  buttonsStyling: true,
		})

	swalWithBootstrapButtons({
		  title: 'Avisos',
		  text: "Deseja deletar esse dado!",
		  type: 'warning',		  
		  padding: '10px',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Sim, pode deletar!',
		  cancelButtonText: "Não, Cancelar!"
		}).then((result) => {
		  if (result.value) {
			  
	          jQuery.ajax({
	        		 type: "POST",
	        		 contentType: "application/x-www-form-urlencoded;charset=UTF-8",
	        		 url: path.val() + "/baixa/deletar",
	        		 cache: false,
	        		 data: {
	        			 "codigo":id
	        		 },
	        	   	 beforeSend: function( ) {
	        	   		loading(".content");
	        		 },
	        		 success: function(msg){
	        			 $(".content").unblock();
	        			 
	       			    Swal(
  					      'Deletado!',
  					      'Seus dados foram deletados.',
  					      'success'
  					    ).then((result) => {
  							  if (result.value) {
  								  atualizaGrid();
  							  }
  					    });


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
			  
			  
		  }else if (
				    // Read more about handling dismissals
				    result.dismiss === Swal.DismissReason.cancel
				  ) {
				    swalWithBootstrapButtons(
				      'Ação Cancelada!',
				      'Seus dados foram cancelados :)',
				      'error'
				    )
				  }
		})
	

}




var baixa = function() {

	jQuery.isBlank = function(obj){
		return(!obj || jQuery.trim(obj) === "");
	};


	 var runSelect = function() {
		 
			var path = $("#path");
			
			$('.select').select2();
			
		    $('#secretaria').select2({
		        minimumResultsForSearch: Infinity,	       
			    ajax: {
			        url: path.val() + "/secretaria/grid",
			        dataType: "json",
			        type: "GET",
			        processResults: function (data) {
			            return {
			                results: $.map(data, function (item) {
			                    return {
			                        text: item.nomsecretaria,
			                        id: item.idsecretaria
			                    }
			                })
			            };
			        }
			    }	        
		    }).on("change", function(e) {	
				$('#setor').select2('enable');
				pesquisaSetor(this.value);
		    });
		 
	 }


	 var grid = function() {
		 atualizaGrid();
	 }
	 
	var runSituacao = function(){
		var acao = $("#acao").val();
		var path = $("#path");
		if($.trim(acao) == "login"){
			location.href = path.val();
		}
	}
	
	 var runEvent = function(){
		$( "#btnPesquisar" ).click(function() {
			exibiPesquisaBaixa();
		});
		
		$("#btnBaixa").click(function() {
			getBaixar();
		});
		
		$("#btnImprimirBaixaGeral").click(function() {
			$("#modal_pesquisa").modal("show");
		});
		
		$("#btnConfirmaBaixaGeral").click(function() {
			var id = $("#local").val();
			getImprimirBaixaSintetico(id);
		});
		
		
	 }

	return {
		init: function() {
			grid();			
			runSelect();
			runSituacao();
			runEvent();
		}
	};

}();
