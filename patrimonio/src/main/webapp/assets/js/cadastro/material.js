/**
 * 
 */

function montarSubGrupo(id){
	
    var path = $("#path");
    
	$('#subgrupo').select2({
        minimumResultsForSearch: Infinity,	       
	    ajax: {
	        url: path.val() + "/subgrupo/pesquisaSubGrupoPorGrupo?idgrupo="+id,
	        dataType: "json",
	        type: "GET",
	        processResults: function (data) {
	            return {
	                results: $.map(data, function (item) {
	                    return {
	                        text: item.descsubgrupo,
	                        id: item.idsubgrupo
	                    }
	                })
	            };
	        }
	    }	        
    });
	
}

function getEditar(id){

	var path = $("#path");

	jQuery.ajax({
		 type: "POST",
		 contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		 url: path.val() + "/material/editar",
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
			 $("#codigo").val(msg.idmaterial);
			 $("#descricaomaterial").val(msg.descmaterial);
			 
			 $('#grupo')
	 		    .empty() //empty select
	 		    .append($("<option/>") //add option tag in select
	 		        .val(msg.idgrupo) //set value for option to post it
	 		        .text(msg.descgrupo)) //set a text for show in select
	 		    .val(msg.idgrupo) //select option of select2
	 		    .trigger("change");			 
			 
			 $('#subgrupo').select2('enable');
			 $('#subgrupo')
	 		    .empty() //empty select
	 		    .append($("<option/>") //add option tag in select
	 		        .val(msg.idsubgrupo) //set value for option to post it
	 		        .text(msg.descsubgrupo)) //set a text for show in select
	 		    .val(msg.idsubgrupo) //select option of select2
	 		    .trigger("change");
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
	        		 url: path.val() + "/material/deletar",
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


function atualizaGrid(){
	
	var table = $('#grid-material').DataTable();
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

	$('#grid-material').DataTable({
        "ajax": {
            "url": path.val() + "/material/grid",
            "dataSrc": ""
        },
        columnDefs: [
	        {
	            width: '30%',
	            targets: [ 0 ]
	        },
	        {
	            width: '30%',
	            targets: [ 1 ],
	        },
	        {
	            width: '30%',
	            targets: [ 2 ],
	        },
	        {
	            width: '10%',
	            targets: [ 3 ],
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
        	{ "data": "descgrupo" },
            { "data": "descsubgrupo" },
            { "data": "descmaterial" },
            { "data": "idmaterial" }
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

var material = function() {

	jQuery.isBlank = function(obj){
		return(!obj || jQuery.trim(obj) === "");
	};


	 var runSelect = function() {

		    // Fixed width. Single select
	    $('#tipomaterial').select2({
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
	    
	    var path = $("#path");
	    
		$('#grupo').select2({
	        minimumResultsForSearch: Infinity,	       
		    ajax: {
		        url: path.val() + "/grupo/grid",
		        dataType: "json",
		        type: "GET",
		        processResults: function (data) {
		            return {
		                results: $.map(data, function (item) {
		                    return {
		                        text: item.descgrupo,
		                        id: item.idgrupo
		                    }
		                })
		            };
		        }
		    }	        
	    }).on("change", function(e) {	
			$('#subgrupo').select2('enable');
			montarSubGrupo(this.value);
	    });	    

	 }

	   var  runValidator = function(){
		   
		    var path = $("#path");
	        

	        $('#frm_material').validate({
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
	            	descricaomaterial:{
	            		required: true
	            	},
	            	subgrupo: {
	                    required: true
	                }
	                
	            },
	            submitHandler: function (form) {

					jQuery.ajax({
						 type: "POST",
						 contentType: "application/x-www-form-urlencoded;charset=UTF-8",
						 url: path.val() + "/material/salvar",
						 cache: false,
						 data: {
							 "material.idmaterial":jQuery('input[name="codigo"]').val(),
							 "material.descmaterial":jQuery('input[name="descricaomaterial"]').val().toUpperCase(),
							 "material.subgrupo.idsubgrupo":jQuery('#subgrupo').val()							 
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

								clear_form_elements("#frm_material");
								
								$('#grupo').val(null).trigger('change');
								$('#subgrupo').prop('disabled', true);
								
								
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

		$('#grid-material').DataTable({
	        "ajax": {
	            "url": path.val() + "/material/grid",
	            "dataSrc": ""
	        },
	        columnDefs: [
		        {
		            width: '30%',
		            targets: [ 0 ]
		        },	        	
		        {
		            width: '30%',
		            targets: [ 1 ]
		        },
		        {
		            width: '30%',
		            targets: [ 2 ],
		        },
		        {
		            width: '10%',
		            targets: [ 3 ],
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
	        	{ "data": "descgrupo" },
	            { "data": "descsubgrupo" },
	            { "data": "descmaterial" },
	            { "data": "idmaterial" }
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

	return {
		init: function() {
			grid();
			runValidator();
			runSelect();
			runSituacao();
		}
	};

}();
