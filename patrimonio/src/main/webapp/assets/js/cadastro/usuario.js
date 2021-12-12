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

function getEditar(id){

	var path = $("#path");

	jQuery.ajax({
		 type: "POST",
		 contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		 url: path.val() + "/usuario/editar",
		 cache: false,
		 dataType: 'json',
		 data: {
			 "codigo":id
		 },
	   	 beforeSend: function( ) {
	   		loading(".content");
		 },
		 success: function(msg){
			 $(".content").unblock();
			 $("#codigo").val(msg.idusuario);
			 $("#nomeusuario").val(msg.nomusuario);
			 $("#senhausuario").val(msg.senusuario);
 			 $("#cpfusuario").val(msg.cpfusuario); 			 
 			 $("#emailusuario").val(msg.emailusuario);
 			 $("#statususuario").val(msg.statususuario);
 			 $("#tipousuario").val(msg.tipousuario).trigger("change");
 			 		 
 			 
 			 alteraImagem(msg.imagemusuario);
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
	        		 url: path.val() + "/usuario/deletar",
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

function alteraImagem(data){
	var path = $("#path");
	
	$("#imgDefault").hide();
	$("#imgUsuario").show();
	$("#imgUsuario").attr("src",jQuery.isBlank(data) ? path.val() + "/assets/images/placeholder.jpg" : data);
	$("#dadosImagem").val(data);
}

function removeImagem(){
	$("#imgDefault").show();
	$("#imgUsuario").hide();
	$("#imgUsuario").attr("src","");
	$("#dadosImagem").val("");
}



function atualizaGrid(){
	
	var table = $('#grid-usuario').DataTable();
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

	$('#grid-usuario').DataTable({
        "ajax": {
            "url": path.val() + "/usuario/grid",
            "dataSrc": ""
        },
        columnDefs: [
	        {
	            width: '40%',
	            targets: [ 0 ]
	        },
	        {
	            width: '15%',
	            targets: [ 1 ],
	            className: "text-center"
	        },
	        {
	            width: '25%',
	            targets: [ 2 ],
	            className: "text-center"
	        },
	        {
	            width: '10%',
	            targets: [ 3 ],
	            className: "text-center",
	            render: function(data, type, full, meta){
	               if(type === 'display'){
	            	   if(data == 1){
	            		   data = "Ativo";
	            	   }else{
	            		   data = "Inativo";
	            	   }
	                  
	               }
	               return data;
		       }

	        },      
	        {
	            width: '10%',
	            targets: [ 4 ],
	            className: "text-center",
	            render: function(data, type, full, meta){
	               if(type === 'display'){
	            	   if(data == 1){
	            		   data = "Administrador";
	            	   }else if(data == 2){
	            		   data = "Operador";
	            	   }else{
	            		   data = "Inativo";
	            	   }
	                  
	               }
	               return data;
		       }

	        },
	        {
	            width: '10%',
	            targets: [ 5 ],
	            className: "text-center",
	            render: function(data, type, full, meta){
	               if(type === 'display'){
	                  data = '<a href="javascript:" data-popup="tooltip" title="Editar" class="text-default" onclick="getEditar('+data+');"><i class="icon-pencil7"></i></a>&nbsp;'
	                  + '<a href="javascript:" data-popup="tooltip" title="Remover" class="text-default" onclick="getDeletar('+data+');"><i class="icon-trash"></i></a>';
	               }
	               return data;
	            }

	        }

 		],
        "columns": [
            { "data": "nomusuario" },
            { "data": "cpfusuario" },
            { "data": "emailusuario" },
            { "data": "statususuario" },
            { "data": "tipousuario" },
            { "data": "idusuario" }
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

var usuario = function() {

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
	            	nomeusuario:{
	            		required: true
	            	},
	            	senhausuario: {
	                    minlength: 6,
	                    required: true
	                },	            	
	            	cpfusuario: {
	            		verificaCPF:true,
	                    required: true
	                },
	            	emailusuario: {
	            		email:true,
	                    required: true
	                },
	            	statususuario: {
	                    required: true
	                },
	            	tipousuario: {
	                    required: true
	                }
	                
	            },
	            submitHandler: function (form) {

					jQuery.ajax({
						 type: "POST",
						 contentType: "application/x-www-form-urlencoded;charset=UTF-8",
						 url: path.val() + "/usuario/salvar",
						 cache: false,
						 data: {
							 "usuario.idusuario":jQuery('input[name="codigo"]').val(),
							 "usuario.nomusuario":jQuery('input[name="nomeusuario"]').val().toUpperCase(),
							 "usuario.senusuario":jQuery('input[name="senhausuario"]').val(),
							 "usuario.cpfusuario":jQuery('input[name="cpfusuario"]').val(),
							 "usuario.emailusuario":jQuery('input[name="emailusuario"]').val().toLowerCase(),
							 "usuario.imagemusuario":jQuery('input[name="dadosImagem"]').val(),
							 "usuario.statususuario":jQuery('#statususuario').val(),
							 "usuario.tipousuario":jQuery('#tipousuario').val()
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

								clear_form_elements("#frm_usuario");
								
								$('#tipousuario').val(null).trigger('change');
								$('#statususuario').val(null).trigger('change');
								
								removeImagem();
								
								atualizaGrid();

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



	 var grid = function() {


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

		$('#grid-usuario').DataTable({
	        "ajax": {
	            "url": path.val() + "/usuario/grid",
	            "dataSrc": ""
	        },
	        columnDefs: [
		        {
		            width: '40%',
		            targets: [ 0 ]
		        },
		        {
		            width: '15%',
		            targets: [ 1 ],
		            className: "text-center"
		        },
		        {
		            width: '25%',
		            targets: [ 2 ],
		            className: "text-center"
		        },
		        {
		            width: '10%',
		            targets: [ 3 ],
		            className: "text-center",
		            render: function(data, type, full, meta){
		               if(type === 'display'){
		            	   if(data == 1){
		            		   data = "Ativo";
		            	   }else{
		            		   data = "Inativo";
		            	   }
		                  
		               }
		               return data;
			       }

		        },      
		        {
		            width: '10%',
		            targets: [ 4 ],
		            className: "text-center",
		            render: function(data, type, full, meta){
		               if(type === 'display'){
		            	   if(data == 1){
		            		   data = "Administrador";
		            	   }else if(data == 2){
		            		   data = "Operador";
		            	   }else{
		            		   data = "Inativo";
		            	   }
		                  
		               }
		               return data;
			       }

		        },
		        {
		            width: '10%',
		            targets: [ 5 ],
		            className: "text-center",
		            render: function(data, type, full, meta){
		               if(type === 'display'){
		                  data = '<a href="javascript:" data-popup="tooltip" title="Editar" class="text-default" onclick="getEditar('+data+');"><i class="icon-pencil7"></i></a>&nbsp;'
		                  + '<a href="javascript:" data-popup="tooltip" title="Remover" class="text-default" onclick="getDeletar('+data+');"><i class="icon-trash"></i></a>';
		               }
		               return data;
		            }

		        }

	 		],
	        "columns": [
	            { "data": "nomusuario" },
	            { "data": "cpfusuario" },
	            { "data": "emailusuario" },
	            { "data": "statususuario" },
	            { "data": "tipousuario" },
	            { "data": "idusuario" }
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
	
	var runMascara = function(){
		$('#cpfusuario').mask('000.000.000-00', {reverse: true});
	}

	return {
		init: function() {
			grid();
			runValidator();
			runSelect();
			runSituacao();
			runEvent();
			runMascara();
		}
	};

}();
