/**
 * 
 */

var corpo = $("#corpo-arquivo");


function uuidv4() {
	  return ([1e7]+-1e3+-4e3+-8e3+-1e11).replace(/[018]/g, c =>
	    (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
	  )
}

function getModalImprimir(){
	$("#modal_imprimir").modal("show");
}

function getImprimirSimplificado(){
	var path = $("#path");
	var url = path.val() + "/relatoriosimplificado/pdf?secretaria="+$("#codsecretariaimprimir").val() +
    "&setor="+$("#codsetorimprimir").val() +
    "&local="+$("#codlocalimprimir").val() +
    "&grupo="+$("#codgrupoimprimir").val() +
    "&subgrupo="+$("#codsubgrupoimprimir").val() +
    "&material="+$("#codmaterialimprimir").val() +
    "&marca="+$("#codmarcaimprimir").val() +
    "&modelo="+$("#codmodeloimprimir").val() +
    "&fornecedor="+$("#codfornecedorimprimir").val() +
    "&situacao="+$("#situacaoimprimir").val() +
    "&numeropatrimonio="+$("#numeropatrimonioimprimir").val() +
    "&numeroserial="+$("#numeroserialimprimir").val();
	window.open(url, '_blank');
}

function getImprimirSintetico(){
	var path = $("#path");
	var url = path.val() + "/relatoriosintetico/pdf?secretaria="+$("#codsecretariaimprimir").val() +
    "&setor="+$("#codsetorimprimir").val() +
    "&local="+$("#codlocalimprimir").val() +
    "&grupo="+$("#codgrupoimprimir").val() +
    "&subgrupo="+$("#codsubgrupoimprimir").val() +
    "&material="+$("#codmaterialimprimir").val() +
    "&marca="+$("#codmarcaimprimir").val() +
    "&modelo="+$("#codmodeloimprimir").val() +
    "&fornecedor="+$("#codfornecedorimprimir").val() +
    "&situacao="+$("#situacaoimprimir").val() +
    "&numeropatrimonio="+$("#numeropatrimonioimprimir").val() +
    "&numeroserial="+$("#numeroserialimprimir").val();
	window.open(url, '_blank');
}


function exibirBarraProgresso(data){
	
	var linha = $("<tr>");
	var barra = $("<td>");
	
	barra.prop("colspan","5");
	barra.html('<div class="progress progress-micro"> ' +
			'<div class="progress-bar progress-bar-danger">' +			
		    '</div> </div>');
	
	linha.append(barra);
	corpo.append(linha);
	
    var progress = parseInt(data.loaded / data.total * 100, 10);
    $('.progress .progress-bar').css(
        'width',
        progress + '%'
    );
    
}

function pesquisaPatrimonio(){
	
	var path = $("#path");
	
	var table = $('.datatable-selection-single').DataTable();
	table.destroy();
	
	$("#tabela-patrimonio").show();
	
    // Table setup
    // ------------------------------

    // Setting datatable defaults
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


    // Single row selection
    var patrimonio = $('.datatable-selection-single');
    
    patrimonio.DataTable({
        "ajax": {
            "url": path.val() + "/patrimonio/pesquisa?idsecretaria="+$("#codsecretariaimprimir").val() +
            "&idsetor="+$("#codsetorimprimir").val() +
            "&idlocal="+$("#codlocalimprimir").val() +
            "&idgrupo="+$("#codgrupoimprimir").val() +
            "&idsubgrupo="+$("#codsubgrupoimprimir").val() +
            "&idmaterial="+$("#codmaterialimprimir").val() +
            "&idmarca="+$("#codmarcaimprimir").val() +
            "&idmodelo="+$("#codmodeloimprimir").val() +
            "&fornecedor="+$("#codfornecedorimprimir").val() +
            "&situacao="+$("#situacaoimprimir").val() +
            "&numeroPatrimonio="+$("#numeropatrimonioimprimir").val() +
            "&serial="+$("#numeroserialimprimir").val(),
            "dataSrc": ""
        },
        columnDefs: [
	        {	            
	            targets: [ 0 ]
	        },
	        {	            
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
	            targets: [ 5 ]
	        },		        
	        {
	            width: '5%',
	            targets: [ 6 ],
	            className: "text-center",
	            render: function(data, type, full, meta){
	               if(type === 'display'){
	                  data = ' <ul class="icons-list"> ' +
							 ' <li class="dropdown"> ' +
						     ' <a href="#" class="dropdown-toggle" data-toggle="dropdown"> ' +
							 ' <i class="icon-menu9"></i> ' +
						     ' </a> ' +
						     ' <ul class="dropdown-menu dropdown-menu-right"> ' +
							 ' <li><a href="javascript:" onclick="getEditar(\''+full.idpatrimonio+'\')"><i class="icon-pencil7"></i>Editar</a></li> ' +
							 ' <li><a href="javascript:" onclick="getDeletar(\''+full.idpatrimonio+'\')"><i class="icon-bin"></i> Remover</a></li> ' +
							 ' <li><a href="javascript:" onclick="getImprimir(\''+full.idpatrimonio+'\')"><i class="icon-shredder"></i> Imprimir</a></li> ' +
						     ' </ul> '+
						     ' </li> ' +
						     ' </ul> ';
	               }
	               return data;
	            }

	        }

 		],
        "columns": [
            { "data": "idpatrimonio" },
            { "data": "descgrupo" },
            { "data": "descsubgrupo" },
            { "data": "descmaterial" },
            { "data": "descmarca" },
            { "data": "descmodelo" },
            { "data": "idpatrimonio" }
        ],
		lengthMenu: [[10, 25, 50, -1], [10, 25, 50, "Todos"]]
	});
    
    
    $('.datatable-selection-single tbody').on('click', 'tr', function() {
        if ($(this).hasClass('success')) {
            $(this).removeClass('success');
        }
        else {
        	$('.datatable-selection-single').DataTable().$('tr.success').removeClass('success');
            $(this).addClass('success');
        }
    });


    // Multiple rows selection
    $('.datatable-selection-multiple').DataTable();
    $('.datatable-selection-multiple tbody').on('click', 'tr', function() {
        $(this).toggleClass('success');
    });


    // Individual column searching with text inputs
    $('.datatable-column-search-inputs tfoot td').not(':last-child').each(function () {
        var title = $('.datatable-column-search-inputs thead th').eq($(this).index()).text();
        $(this).html('<input type="text" class="form-control input-sm" placeholder="Search '+title+'" />');
    });
    var table = $('.datatable-column-search-inputs').DataTable();
    table.columns().every( function () {
        var that = this;
        $('input', this.footer()).on('keyup change', function () {
            that.search(this.value).draw();
        });
    });


    // Individual column searching with selects
    $('.datatable-column-search-selects').DataTable({
        initComplete: function () {
            this.api().columns().every( function() {
                var column = this;
                var select = $('<select class="filter-select" data-placeholder="Filter"><option value=""></option></select>')
                    .appendTo($(column.footer()).not(':last-child').empty())
                    .on('change', function() {
                        var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );
 
                        column
                            .search( val ? '^'+val+'$' : '', true, false )
                            .draw();
                    });
 
                column.data().unique().sort().each( function (d, j) {
                    select.append('<option value="'+d+'">'+d+'</option>')
                });
            });
        }
    });



    // External table additions
    // ------------------------------

    // Add placeholder to the datatable filter option
    $('.dataTables_filter input[type=search]').attr('placeholder','Tipo de Pesquisa...');


    // Enable Select2 select for the length option
    $('.dataTables_length select').select2({
        minimumResultsForSearch: Infinity,
        width: 'auto'
    });


    // Enable Select2 select for individual column searching
    $('.filter-select').select2();	
	
}

function removerArquivo(id){

	var path = $("#path");

	const swalWithBootstrapButtons = Swal.mixin({
		  confirmButtonClass: 'btn btn-success',
		  cancelButtonClass: 'btn btn-danger',
		  buttonsStyling: true,
		})

	swalWithBootstrapButtons({
		  title: 'Avisos',
		  text: "Deseja deletar esse arquivo!",
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
	        		 url: path.val() + "/arquivo/deletar",
	        		 cache: false,
	        		 data: {
	        			 "codigo":id
	        		 },
	        	   	 beforeSend: function( ) {
	        	   		
	        		 },
	        		 success: function(msg){
	      			    Swal(
						      'Deletado!',
						      'Seu arquivo foi deletado.',
						      'success'
						    ).then((result) => {
								  if (result.value) {
									  montarGridArquivo();
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

function editarArquivo(id){
	var path = $("#path");
	$("#idarquivo").val(id);
	$("#modal_arquivo").modal("show");
	
	$.getJSON(path.val() + "/arquivo/editar",{"codigo":id}, function( data ) {
		$("#descricaoarquivo").val(data.descarquivos);
		$("#file-arquivo").val(data.file);		
	});
}

function atualizarArquivo(){
	var path = $("#path");
	
    jQuery.ajax({
		 type: "POST",
		 contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		 url: path.val() + "/arquivo/salvar",
		 cache: false,
		 data: {
			 "arquivo.idarquivos":$("#idarquivo").val(),
			 "arquivo.file":$("#file-arquivo").val(),
			 "arquivo.numeroarquivo":$("#numeroarquivo").val(),
			 "arquivo.descarquivos":$("#descricaoarquivo").val().toUpperCase()
		 },
	   	 beforeSend: function( ) {
	   		
		 },
		 success: function(msg){			 
			  montarGridArquivo();
			  $("#descricaoarquivo").val("");
			  $("#modal_arquivo").modal("hide");
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

function mergeArquivo(){
	var path = $("#path");
	$.getJSON(path.val() + "/arquivo/mergeArquivo",{"numero":$("#numeroarquivo").val()}, function( data ) {
		if(data.resultado){
			montarGridArquivo();
		}
	});
}

function montarGridArquivo(){
	var path = $("#path");	
	corpo.empty();
	
	$.getJSON(path.val() + "/arquivo/listaArquivo",{"numero":$("#numeroarquivo").val()}, function( data ) {
		 $.each( data, function( key, val ) {
			 var linha = $("<tr>");
			 var descricao = $("<td>");
			 var arquivo = $("<td>");
			 var data = $("<td>");
			 var hora = $("<td>");
			 var acao = $("<td>");
			 
			 descricao.prop("class","editable");
			 descricao.text(val.descarquivos);
			 arquivo.text(val.file);
			 
			 data.prop("class","text-center col-md-1");
			 data.text(val.dataarquivos);
			 
			 hora.prop("class","text-center col-md-1");
			 hora.text(val.horaaquivos);
			 
			 acao.prop("class","text-center");
			 acao.html('<ul class="icons-list"> ' + 
					' <li><a href="javascript:" onclick="removerArquivo('+val.idarquivos+');"><i class="icon-bin"></i></a></li> ' +
					' <li><a href="javascript:" onclick="editarArquivo('+val.idarquivos+');"><i class="icon-pencil7"></i></a></li> ' +
					' <li><a href="'+path.val()+'/upload/arquivos/'+val.file+'" target="_blank"><i class="icon-eye"></i></a></li>/ul> ');
			 
			 linha.append(descricao);
			 linha.append(arquivo);
			 linha.append(data);
			 linha.append(hora);
			 linha.append(acao);
			 
			 corpo.append(linha);
			 
		  });

	});
}

function salvarArquivo(file){
	var path = $("#path");
	jQuery.ajax({
		 type: "POST",
		 contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		 url: path.val() + "/arquivo/salvar",
		 cache: false,  
		 data: {	
			 "arquivo.idarquivos":$("#idarquivo").val(),
			 "arquivo.numeroarquivo":$("#numeroarquivo").val(),
			 "arquivo.file":file
		 },		 
	   	 beforeSend: function( ) {
		 },				 
		 success: function(msg){
			 montarGridArquivo(); 
		 }
	});	
}

function exibeGeraArquivo(){
	var path = $("#path");
	
	$.getJSON(path.val() + "/geraArquivo/listaNumero", function( data ) {
		$("#numeroarquivo").val(data.idgera);
		montarGridArquivo();
	});
	
}

function atualizaGeraArquivo(){
	var path = $("#path");
	jQuery.ajax({
		 type: "POST",
		 contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		 url: path.val() + "/geraArquivo/salvar",
		 cache: false,  
		 data: {	
			 "geraarquivo.idgera":$("#numeroarquivo").val(),
			 "geraarquivo.statusgera":1
		 },		 
	   	 beforeSend: function( ) {
		 },				 
		 success: function(msg){
			 $("#numeroarquivo").val("");
			 geraNumeroArquivo(); 
		 }
	});		
}

function gravarGeraArquivo(){
	var path = $("#path");
	jQuery.ajax({
		 type: "POST",
		 contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		 url: path.val() + "/geraArquivo/salvar",
		 cache: false,  
		 data: {	
			 "geraarquivo.idgera":$("#numeroarquivo").val(),
			 "geraarquivo.statusgera":0
		 },		 
	   	 beforeSend: function( ) {
		 },				 
		 success: function(msg){
			 geraNumeroArquivo(); 
		 }
	});		
}

function geraNumeroArquivo(){
	var path = $("#path");
	
	$.getJSON(path.val() + "/geraArquivo/validaGeraArquivo", function( data ) {
		if(data.resultado){
			gravarGeraArquivo();
		}else{
			exibeGeraArquivo();
		}
	});
}

function uploadArquivo(){
	 $("#filearquivo").trigger('click');
}

function carregaGridFornecedor(){
	
	var table = $('#grid-fornecedor').DataTable();
	table.destroy();
	
	
	var path = $("#path");
	
	
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
	
    /*Grid Fornecedor */

	$('#grid-fornecedor').DataTable({
        "ajax": {
            "url": path.val() + "/fornecedor/grid",
            "dataSrc": ""
        },
        columnDefs: [
        	{
        		width: '10%',
        		targets: [ 0 ],
        		className: "text-center",
	            render: function(data, type, full, meta){
		               if(type === 'display'){
		                  data = '<a href="javascript:" class="text-default" onclick="exibirFornecedor('+data+');">'+(data).pad(5)+'</a>';
		               }
		               return data;
		        }	        		
        	
        	},
	        {
	            width: '10%',
	            targets: [ 1 ]
	        },
	        {
	            width: '80%',
	            targets: [ 2 ]
	        }
 		],
        "columns": [
            { "data": "idfornecedor" },
            { "data": "cnpjfornecedor" },
            { "data": "razaosocial" }
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



function carregaGridModelo(){
	
	var table = $('#grid-modelo').DataTable();
	table.destroy();
	
	
	var path = $("#path");
	
	
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
	
    /*Grid Modelo */
    
    var codigomarca = 0;
    
    if($('#dados').css('display') == 'none')
    {
    	codigomarca = $("#codmarcaimprimir").val();
    }else{
    	codigomarca = $("#codmarca").val();
    }

	$('#grid-modelo').DataTable({
        "ajax": {
            "url": path.val() + "/modelo/pesquisaModeloPorMarca?idmarca="+codigomarca,
            "dataSrc": ""
        },
        columnDefs: [
        	{
        		width: '10%',
        		targets: [ 0 ],
        		className: "text-center",
	            render: function(data, type, full, meta){
		               if(type === 'display'){
		                  data = '<a href="javascript:" class="text-default" onclick="exibirModelo('+data+');">'+(data).pad(5)+'</a>';
		               }
		               return data;
		        }	        		
        	
        	},
	        {
	            width: '45%',
	            targets: [ 1 ]
	        },
	        {
	            width: '45%',
	            targets: [ 2 ]
	        }
 		],
        "columns": [
            { "data": "idmodelo" },
            { "data": "descmarca" },
            { "data": "descmodelo" }
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

function carregaGridMarca(){
	
	var table = $('#grid-marca').DataTable();
	table.destroy();
	
	
	var path = $("#path");
	
	
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
	
    /*Grid Marca */

	$('#grid-marca').DataTable({
        "ajax": {
            "url": path.val() + "/marca/grid",
            "dataSrc": ""
        },
        columnDefs: [
        	{
        		width: '5%',
        		targets: [ 0 ],
        		className: "text-center",
	            render: function(data, type, full, meta){
		               if(type === 'display'){
		                  data = '<a href="javascript:" class="text-default" onclick="exibirMarca('+data+');">'+(data).pad(5)+'</a>';
		               }
		               return data;
		        }	        		
        	
        	},
	        {
	            width: '95%',
	            targets: [ 1 ]
	        }
	        
 		],
        "columns": [
            { "data": "idmarca" },
            { "data": "descmarca" }
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


function carregaGridMaterial(){
	
	var table = $('#grid-material').DataTable();
	table.destroy();
	
	
	var path = $("#path");
	
	
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
	
    /*Grid Material */
    
    var codigosubgrupo = 0;
    
    if($('#dados').css('display') == 'none')
    {
    	codigosubgrupo = $("#codsubgrupoimprimir").val();
    }else{
    	codigosubgrupo = $("#codsubgrupo").val();
    }

	$('#grid-material').DataTable({
        "ajax": {
            "url": path.val() + "/material/pesquisaMaterialPorSubGrupo?idsubgrupo="+codigosubgrupo,
            "dataSrc": ""
        },
        columnDefs: [
        	{
        		width: '5%',
        		targets: [ 0 ],
        		className: "text-center",
	            render: function(data, type, full, meta){
		               if(type === 'display'){
		                  data = '<a href="javascript:" class="text-default" onclick="exibirMaterial('+data+');">'+(data).pad(5)+'</a>';
		               }
		               return data;
		        }	        		
        	
        	},
	        {
	            width: '35%',
	            targets: [ 1 ]
	        },
	        {
	            width: '35%',
	            targets: [ 2 ]
	        },
	        {
	            width: '25%',
	            targets: [ 3 ]
	        }	        
	        
 		],
        "columns": [
            { "data": "idmaterial" },
            { "data": "descgrupo" },
            { "data": "descsubgrupo" },
            { "data": "descmaterial" }
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

function carregaGridSubGrupo(){
	
	var table = $('#grid-subgrupo').DataTable();
	table.destroy();
	
	
	var path = $("#path");
	
	
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
	
    /*Grid Subgrupo */
    
    var codigogrupo = 0;
    
    if($('#dados').css('display') == 'none')
    {
    	codigogrupo = $("#codgrupoimprimir").val();
    }else{
    	codigogrupo = $("#codgrupo").val();
    }

	$('#grid-subgrupo').DataTable({
        "ajax": {
            "url": path.val() + "/subgrupo/pesquisaSubGrupoPorGrupo?idgrupo="+codigogrupo,
            "dataSrc": ""
        },
        columnDefs: [
        	{
        		width: '10%',
        		targets: [ 0 ],
        		className: "text-center",
	            render: function(data, type, full, meta){
		               if(type === 'display'){
		                  data = '<a href="javascript:" class="text-default" onclick="exibirSubGrupo('+data+');">'+(data).pad(5)+'</a>';
		               }
		               return data;
		        }	        		
        	
        	},
	        {
	            width: '45%',
	            targets: [ 1 ]
	        },
	        {
	            width: '45%',
	            targets: [ 2 ]
	        }	        
 		],
        "columns": [
            { "data": "idsubgrupo" },
            { "data": "descgrupo" },
            { "data": "descsubgrupo" }
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



function carregaGridGrupo(){
	
	var table = $('#grid-grupo').DataTable();
	table.destroy();
	
	
	var path = $("#path");
	
	
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
	
    /*Grid Grupo */

	$('#grid-grupo').DataTable({
        "ajax": {
            "url": path.val() + "/grupo/grid",
            "dataSrc": ""
        },
        columnDefs: [
        	{
        		width: '5%',
        		targets: [ 0 ],
        		className: "text-center",
	            render: function(data, type, full, meta){
		               if(type === 'display'){
		                  data = '<a href="javascript:" class="text-default" onclick="exibirGrupo('+data+');">'+(data).pad(5)+'</a>';
		               }
		               return data;
		        }	        		
        	
        	},
	        {
	            width: '95%',
	            targets: [ 1 ]
	        }	        	
 		],
        "columns": [
            { "data": "idgrupo" },
            { "data": "descgrupo" }
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



function carregaGridLocal(){
	
	var table = $('#grid-local').DataTable();
	table.destroy();
	
	
	var path = $("#path");
	
	
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
	
    /*Grid Local */
    
    var codigosetor = 0;
    
    if($('#dados').css('display') == 'none')
    {
    	codigosetor = $("#codsetorimprimir").val();
    }else{
    	codigosetor = $("#codsetor").val();
    }

	$('#grid-local').DataTable({
        "ajax": {
            "url": path.val() + "/local/pesquisaLocalPorSetor?idsetor="+codigosetor,
            "dataSrc": ""
        },
        columnDefs: [
        	{
        		width: '5%',
        		targets: [ 0 ],
        		className: "text-center",
	            render: function(data, type, full, meta){
		               if(type === 'display'){
		                  data = '<a href="javascript:" class="text-default" onclick="exibirLocal('+data+');">'+(data).pad(5)+'</a>';
		               }
		               return data;
		        }	        		
        	
        	},
	        {
	            width: '35%',
	            targets: [ 1 ]
	        },	        	
	        {
	            width: '35%',
	            targets: [ 2 ]
	        },
	        {
	            width: '25%',
	            targets: [ 3 ]
	        }
 		],
        "columns": [
            { "data": "idlocal" },
            { "data": "nomsecretaria" },
            { "data": "nomsetor" },
            { "data": "desclocal" }
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

function carregaGridSetor(){
	
	var path = $("#path");
	
	var table = $('#grid-setor').DataTable();
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
    
    var codigosecretaria = 0;
    
    if($('#dados').css('display') == 'none')
    {
    	codigosecretaria = $("#codsecretariaimprimir").val();
    }else{
    	codigosecretaria = $("#codsecretaria").val();
    }
	
	
    /*Grid Setor */

	$('#grid-setor').DataTable({
        "ajax": {
            "url": path.val() + "/setor/pesquisaSetorPorSecretaria?idsecretaria="+codigosecretaria,
            "dataSrc": ""
        },
        columnDefs: [
        	{
        		width: '5%',
        		targets: [ 0 ],
        		className: "text-center",
	            render: function(data, type, full, meta){
		               if(type === 'display'){
		                  data = '<a href="javascript:" class="text-default" onclick="exibirSetor('+data+');">'+(data).pad(5)+'</a>';
		               }
		               return data;
		        }	        		
        	
        	},
	        {
	            width: '45%',
	            targets: [ 1 ]
	        },
	        {
	            width: '45%',
	            targets: [ 2 ]
	        }
 		],
        "columns": [
            { "data": "idsetor" },
            { "data": "nomsecretaria" },
            { "data": "nomsetor" }
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

function getImprimir(id){
	var path = $("#path");
	var url = path.val() + "/relatorioanalitico/pdf?numeropatrimonio="+id
	window.open(url, '_blank');
}

function getEditar(id){

	var path = $("#path");

	jQuery.ajax({
		 type: "POST",
		 contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		 url: path.val() + "/patrimonio/editar",
		 cache: false,
		 dataType: 'json',
		 data: {
			 "numeroPatrimonio":id
		 },
	   	 beforeSend: function( ) {
	   		loading(".content");
		 },
		 success: function(msg){
			 
			 $("#dados").show();
			 $("#grid").hide();
			 $("#btnGrid").show();
			 $("#btnSalvar").show();
			 $("#btnCadastro").hide();
			 $("#btnLimpar").hide();
			 $("#btnImprimir").hide();			 
			 
			 $(".content").unblock();
			 $("#numeropatrimonio").val(msg[0].idpatrimonio);
			 $("#numeroserial").val(msg[0].serialpatrimonio);
			 $('#situacao')
	 		    .val(msg[0].situacaopatrimonio) //select option of select2
	 		    .trigger("change");	
			 
			 $("#codsecretaria").val(msg[0].idsecretaria);
			 $("#secretaria").val(msg[0].nomsecretaria);
			 $("#codsetor").val(msg[0].idsetor);
			 $("#setor").val(msg[0].nomsetor);
			 $("#codlocal").val(msg[0].idlocal);
			 $("#local").val(msg[0].desclocal);
			 
			 $("#codgrupo").val(msg[0].idgrupo);
			 $("#grupo").val(msg[0].descgrupo);
			 $("#codsubgrupo").val(msg[0].idsubgrupo);
			 $("#subgrupo").val(msg[0].descsubgrupo);
			 $("#codmaterial").val(msg[0].idmaterial);
			 $("#material").val(msg[0].descmaterial);
			 
			 $("#codmarca").val(msg[0].idmarca);
			 $("#marca").val(msg[0].descmarca);
			 $("#codmodelo").val(msg[0].idmodelo);
			 $("#modelo").val(msg[0].descmodelo);
			 $("#codfornecedor").val(msg[0].idfornecedor);
			 $("#fornecedor").val(msg[0].razaosocialfornecedor);
			 
			 $("#numeroarquivo").val(msg[0].numeroarquivo);
			 $("#imgUpload").attr("src",msg[0].imgpatrimonio);
			 
			 if(msg[0].statusbaixa == 1){
				 $("#chkBaixa").parent().addClass("checked");
				 $("#chkBaixa").prop("checked",true);
			 }else{
				 $("#chkBaixa").parent().removeClass("checked");
				 $("#chkBaixa").prop("checked",false);
			 }

			 $("#obspatrimonio").val(msg[0].obspatrimonio);
			 
			 montarGridArquivo();

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
	        		 url: path.val() + "/patrimonio/deletar",
	        		 cache: false,
	        		 data: {
	        			 "numeroPatrimonio":id
	        		 },
	        	   	 beforeSend: function( ) {
	        	   		loading(".content");
	        		 },
	        		 success: function(msg){
	        			 $(".content").unblock();
	        			 
      			    Swal(
					      'Deletado!',
					      'Seu dado foi deletado.',
					      'success'
					    ).then((result) => {
							  if (result.value) {
								  pesquisaPatrimonio();
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


function iconFormat(icon) {
    var originalOption = icon.element;
    if (!icon.id) { return icon.text; }
    var $icon = "<i class='icon-" + $(icon.element).data('icon') + "'></i>" + icon.text;

    return $icon;
}

function exibirFornecedor(id){
	$("#modal_fornecedor").modal("hide");
	
	var path = $("#path");
	
	$.getJSON(path.val() + "/fornecedor/editar",{"codigo" : id}, function( data ) {
		if($('#dados').css('display') == 'none'){
			$("#codfornecedorimprimir").val(data.idfornecedor);
			$("#fornecedorimprimir").val(data.razaosocial);
		}else{
			$("#codfornecedor").val(data.idfornecedor);
			$("#fornecedor").val(data.razaosocial);
		}
	});	
	
}

function exibirModelo(id){
	$("#modal_modelo").modal("hide");
	
	var path = $("#path");
	var codigomarca = 0;
	
	if($('#dados').css('display') == 'none'){
		codigomarca = $("#codmarcaimprimir").val();
	}else{
		codigomarca = $("#codmarca").val();
	}
	
	$.getJSON(path.val() + "/modelo/pesquisaModeloPorCodigoEMarca",{"codigo" : id, "idmarca" : codigomarca}, function( data ) {
		if($('#dados').css('display') == 'none'){
			$("#codmodeloimprimir").val(data.idmodelo);
			$("#modeloimprimir").val(data.descmodelo);
		}else{
			$("#codmodelo").val(data.idmodelo);
			$("#modelo").val(data.descmodelo);
		}
	});	
	
}


function exibirMarca(id){
	$("#modal_marca").modal("hide");
	
	$("#modelo").val("");
	$("#codmodelo").val("");
	
	$("#modeloimprimir").val("");
	$("#codmodeloimprimir").val("");
	
	var path = $("#path");
	
	$.getJSON(path.val() + "/marca/editar",{"codigo" : id}, function( data ) {
		if($('#dados').css('display') == 'none'){
			$("#codmarcaimprimir").val(data.idmarca);
			$("#marcaimprimir").val(data.descmarca);
		}else{
			$("#codmarca").val(data.idmarca);
			$("#marca").val(data.descmarca);
		}
	});	
	
}

function exibirGrupo(id){
	$("#modal_grupo").modal("hide");
	
	$("#subgrupo").val("");
	$("#codsubgrupo").val("");
	
	$("#material").val("");
	$("#codmaterial").val("");
	
	$("#subgrupoimprimir").val("");
	$("#codsubgrupoimprimir").val("");
	
	$("#materialimprimir").val("");
	$("#codmaterialimprimir").val("");
	
	var path = $("#path");
	
	$.getJSON(path.val() + "/grupo/editar",{"codigo" : id}, function( data ) {
		if($('#dados').css('display') == 'none'){
			$("#codgrupoimprimir").val(data.idgrupo);
			$("#grupoimprimir").val(data.descgrupo);
		}else{
			$("#codgrupo").val(data.idgrupo);
			$("#grupo").val(data.descgrupo);
		}
	});	
	
}

function exibirSubGrupo(id){
	$("#modal_subgrupo").modal("hide");
	
	$("#material").val("");
	$("#codmaterial").val("");
	
	$("#materialimprimir").val("");
	$("#codmaterialimprimir").val("");
	
	var path = $("#path");
	var codigogrupo = 0;
	
	if($('#dados').css('display') == 'none'){
		codigogrupo = $("#codgrupoimprimir").val();
	}else{
		codigogrupo = $("#codgrupo").val();
	}
	
	$.getJSON(path.val() + "/subgrupo/pesquisaSubgrupoPorCodigoEGrupo",{"codigo" : id, "idgrupo" : codigogrupo}, function( data ) {
		if($('#dados').css('display') == 'none'){
			$("#codsubgrupoimprimir").val(data.idsubgrupo);
			$("#subgrupoimprimir").val(data.descsubgrupo);			
		}else{
			$("#codsubgrupo").val(data.idsubgrupo);
			$("#subgrupo").val(data.descsubgrupo);
		}
	});	
	
}

function exibirMaterial(id){
	$("#modal_material").modal("hide");
	
	var path = $("#path");
	var codigosubgrupo = 0;
	
	if($('#dados').css('display') == 'none'){
		codigosubgrupo = $("#codsubgrupoimprimir").val();
	}else{
		codigosubgrupo = $("#codsubgrupo").val();
	}

	
	$.getJSON(path.val() + "/material/pesquisaMaterialPorCodigoESubgrupo",{"codigo" : id, "idsubgrupo" : codigosubgrupo}, function( data ) {
		if($('#dados').css('display') == 'none'){
			$("#codmaterialimprimir").val(data.idmaterial);
			$("#materialimprimir").val(data.descmaterial);
		}else{
			$("#codmaterial").val(data.idmaterial);
			$("#material").val(data.descmaterial);
		}
	});	
	
}


function exibirLocal(id){
	$("#modal_local").modal("hide");
	
	var path = $("#path");
	var codigosetor = 0;
	
	if($('#dados').css('display') == 'none'){
		codigosetor = $("#codsetorimprimir").val();
	}else{
		codigosetor = $("#codsetor").val();
	}
	
	$.getJSON(path.val() + "/local/pesquisaLocalPorCodigoESetor",{"codigo" : id, "idsetor" : codigosetor}, function( data ) {
		if($('#dados').css('display') == 'none'){
			$("#codlocalimprimir").val(data.idlocal);
			$("#localimprimir").val(data.desclocal);
		}else{
			$("#codlocal").val(data.idlocal);
			$("#local").val(data.desclocal);
		}
	});	
	
}

function exibirSetor(id){
	$("#modal_setor").modal("hide");
	
	$("#local").val("");
	$("#codlocal").val("");
	
	$("#localimprimir").val("");
	$("#codlocalimprimir").val("");
	
	var path = $("#path");
	var codigosecretaria = 0;
	
	if($('#dados').css('display') == 'none'){
		codigosecretaria = $("#codsecretariaimprimir").val()
	}else{
		codigosecretaria = $("#codsecretaria").val();
	}
		
	
	$.getJSON(path.val() + "/setor/pesquisaSetorPorCodigoESecretaria",{"codigo" : id, "idsecretaria" : codigosecretaria}, function( data ) {
		if($('#dados').css('display') == 'none'){
			$("#codsetorimprimir").val(data.idsetor);
			$("#setorimprimir").val(data.nomsetor);
		}else{
			$("#codsetor").val(data.idsetor);
			$("#setor").val(data.nomsetor);
		}
	});	
	
}


function exibirSecretaria(id){
	$("#modal_secretaria").modal("hide");
	
	$("#setor").val("");
	$("#codsetor").val("");
	
	$("#local").val("");
	$("#codlocal").val("");
	
	$("#setorimprimir").val("");
	$("#codsetorimprimir").val("");
	
	$("#localimprimir").val("");
	$("#codlocalimprimir").val("");	
	
	var path = $("#path");
	
	$.getJSON(path.val() + "/secretaria/editar",{"codigo" : id}, function( data ) {
		if($('#dados').css('display') == 'none')
		{
			$("#codsecretariaimprimir").val(data.idsecretaria);
			$("#secretariaimprimir").val(data.nomsecretaria);
		}else{
			$("#codsecretaria").val(data.idsecretaria);
			$("#secretaria").val(data.nomsecretaria);
		}
	});	
	
}

function exibirImagem(data){
	$("#imgUpload").prop("src",data);
	$("#imagempatrimonio").val(data);
}

var patrimonio = function() {
	Number.prototype.pad = function(size) {
	    var s = String(this);
	    while (s.length < (size || 2)) {s = "0" + s;}
	    return s;
	}

	jQuery.isBlank = function(obj){
		return(!obj || jQuery.trim(obj) === "");
	};


	 var runSelect = function() {

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
	        

	        $('#frm_patrimonio').validate({
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
	            	numeropatrimonio:{
	            		required: true
	            	},
	            	situacao:{
	            		required: true
	            	},
	            	secretaria:{
	            		required: true
	            	},
	            	setor:{
	            		required: true
	            	},
	            	local:{
	            		required: true
	            	},
	            	grupo:{
	            		required: true
	            	},
	            	subgrupo:{
	            		required: true
	            	},
	            	material:{
	            		required: true
	            	},
	            	marca:{
	            		required: true
	            	},	            	
	            	modelo:{
	            		required: true
	            	},
	            	fornecedor:{
	            		required: true
	            	}		            	
	                
	            },
	            submitHandler: function (form) {

					jQuery.ajax({
						 type: "POST",
						 contentType: "application/x-www-form-urlencoded;charset=UTF-8",
						 url: path.val() + "/patrimonio/salvar",
						 cache: false,
						 data: {
							 "patrimonio.idpatrimonio":jQuery('input[name="numeropatrimonio"]').val(),
							 "patrimonio.serialpatrimonio":jQuery('input[name="numeroserial"]').val().toUpperCase(),
							 "patrimonio.situacaopatrimonio":jQuery('#situacao').val(),
							 "patrimonio.statusbaixa":jQuery('input[name="chkBaixa"]:checkbox:checked').val(),
							 "patrimonio.obspatrimonio":jQuery('#obspatrimonio').val(),
							 "patrimonio.material.idmaterial":jQuery('input[name="codmaterial"]').val(),
							 "patrimonio.modelo.idmodelo":jQuery('input[name="codmodelo"]').val(),
							 "patrimonio.fornecedor.idfornecedor":jQuery('input[name="codfornecedor"]').val(),
							 "patrimonio.numeroarquivo":jQuery('input[name="numeroarquivo"]').val(),
							 "patrimonio.local.idlocal":jQuery('input[name="codlocal"]').val(),
							 "patrimonio.imgpatrimonio":jQuery('input[name="imagempatrimonio"]').val()
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

								clear_form_elements("#frm_patrimonio");								
								
								$('#situacao').val(null).trigger('change');
								
								$("#chkBaixa").parent().removeClass("checked");
								$("#chkBaixa").prop('checked',false);
								
								$("#imgUpload").prop('src',path.val()+'/assets/images/placeholder.jpg');
								
								atualizaGeraArquivo();
								

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
	
	var runCheckBox = function(){
	    // Primary
	    $(".control-primary").uniform({
	        radioClass: 'choice',
	        wrapperClass: 'border-primary-600 text-primary-800'
	    });
	    

	    // Success
	    $(".control-success").uniform({
	        radioClass: 'choice',
	        wrapperClass: 'border-success-600 text-success-800'
	    });	  
	    
	    // Initialize with options
	    $(".select-icons").select2({
	        templateResult: iconFormat,
	        minimumResultsForSearch: Infinity,
	        templateSelection: iconFormat,
	        escapeMarkup: function(m) { return m; }
	    });	    
	    
	    // File input
	    $(".file-styled").uniform({
	        fileButtonClass: 'action btn bg-pink-400'
	    });
	    
	}
	
	var runEvent = function(){
		
		$("#btnSecretaria" ).click(function() {
			 $("#modal_secretaria").modal("show");
		});
		
		$("#btnSetor" ).click(function() {
			 $("#modal_setor").modal("show");
		});	
		
		$("#btnLocal" ).click(function() {
			 $("#modal_local").modal("show");
		});
		
		$("#btnGrupo" ).click(function() {
			 $("#modal_grupo").modal("show");
		});
		
		$("#btnSubgrupo" ).click(function() {
			 $("#modal_subgrupo").modal("show");
		});	
		
		$("#btnMaterial" ).click(function() {
			 $("#modal_material").modal("show");
		});	
		
		$("#btnMarca" ).click(function() {
			 $("#modal_marca").modal("show");
		});	
		
		$("#btnModelo" ).click(function() {
			 $("#modal_modelo").modal("show");
		});
		
		$("#btnFornecedor" ).click(function() {
			 $("#modal_fornecedor").modal("show");
		});	
		
		$("#btnImprimir" ).click(function() {
			getModalImprimir();
		});
		
		$("#btnImprimirTipo" ).click(function() {
			var opcao = $("input[name=rdbImprimir]:checked").val();
			if(opcao == 1){
				getImprimirSintetico();
			}else{
				getImprimirSimplificado();
			}
		});
		
		
		$("#btnGrid" ).click(function() {
			 $("#dados").hide();
			 $("#grid").show();
			 $("#btnGrid").hide();
			 $("#btnSalvar").hide();
			 $("#btnCadastro").show();
			 $("#btnLimpar").show();
			 $("#btnImprimir").show();
			 clear_form_elements("#frm_patrimonio");
			 $("#tabela-patrimonio").hide();
		});	
		
		$("#btnCadastro" ).click(function() {
			 $("#dados").show();
			 $("#grid").hide();
			 $("#btnGrid").show();
			 $("#btnSalvar").show();
			 $("#btnCadastro").hide();
			 $("#btnLimpar").hide();
			 $("#btnImprimir").hide();		
			 $("#btnImprimir").prop('disabled',true);
			 clear_form_elements("#frm_patrimonio");
		});
		
		$("#btnLimpar" ).click(function() {
			clear_form_elements("#frm_patrimonio");
			$('#situacao').val(null).trigger('change');
			$("#tabela-patrimonio").hide();
		});
		
		
		$("#btnPesquisaPatrimonio" ).click(function() {
			pesquisaPatrimonio();
		});
		
		$("#btnGeraUUID" ).click(function() {
			var uuid = uuidv4();
			$("#numeropatrimonio").val(uuid);
		});
		
		
		
		$('#modal_setor').on('shown.bs.modal', function () {
			  carregaGridSetor();
		});
		
		$('#modal_local').on('shown.bs.modal', function () {
			  carregaGridLocal();
		});
		
		$('#modal_grupo').on('shown.bs.modal', function () {
			  carregaGridGrupo();
		});
		
		$('#modal_subgrupo').on('shown.bs.modal', function () {
			  carregaGridSubGrupo();
		});	
		
		$('#modal_material').on('shown.bs.modal', function () {
			  carregaGridMaterial();
		});	
		
		$('#modal_marca').on('shown.bs.modal', function () {
			  carregaGridMarca();
		});			
		
		$('#modal_modelo').on('shown.bs.modal', function () {
			carregaGridModelo();
		});
		
		$('#modal_fornecedor').on('shown.bs.modal', function () {
			carregaGridFornecedor();
		});		
		
		$( "#codsecretaria" ).blur(function() {
			//console.log(this.value);
			exibirSecretaria(this.value);
		});		
		
		$( "#codsetor" ).blur(function() {			
			exibirSetor(this.value);
		});
		
		$( "#codlocal" ).blur(function() {			
			exibirLocal(this.value);
		});		

		$( "#codgrupo" ).blur(function() {
			//console.log(this.value);
			exibirGrupo(this.value);
		});	
		
		$( "#codsubgrupo" ).blur(function() {			
			exibirSubGrupo(this.value);
		});	
		
		$( "#codmaterial" ).blur(function() {			
			exibirMaterial(this.value);
		});
		
		$( "#codmarca" ).blur(function() {			
			exibirMarca(this.value);
		});	
		
		$( "#codmodelo" ).blur(function() {			
			exibirModelo(this.value);
		});	
		
		$( "#codfornecedor" ).blur(function() {			
			exibirFornecedor(this.value);
		});		

		geraNumeroArquivo();
		
		
	}
	
	var runDataTable = function(){
		
		var path = $("#path");
		
		
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

	    /*Grid Secretaria */

		$('#grid-secretaria').DataTable({
	        "ajax": {
	            "url": path.val() + "/secretaria/grid",
	            "dataSrc": ""
	        },
	        columnDefs: [
	        	{
	        		width: '5%',
	        		targets: [ 0 ],
	        		className: "text-center",
		            render: function(data, type, full, meta){
			               if(type === 'display'){
			                  data = '<a href="javascript:" class="text-default" onclick="exibirSecretaria('+data+');">'+(data).pad(5)+'</a>';
			               }
			               return data;
			        }	        		
	        	
	        	},
		        {
		            width: '95%',
		            targets: [ 1 ]
		        }

	 		],
	        "columns": [
	            { "data": "idsecretaria" },
	            { "data": "nomsecretaria" }
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
	
	var runUpload = function(){
		
		var path = $("#path");
		
		$("#filepatrimonio").change(function (){
			var file = document.querySelector("#filepatrimonio").files[0];
			getBase64(file).then(
			  data => exibirImagem(data)
			);
		});
		

	    $('#filearquivo').fileupload({
	        url: path.val()+"/UploadArquivo",
	        autoUpload: true,
	        dataType: 'json',
	        async: true,
	        done: function (e, data) {
	        	$.each(data.result, function (index, file) {		            	
	            	salvarArquivo(file.name);           	
	            });
	        },
	        progressall: function (e, data) {
	        	exibirBarraProgresso(data);
	        }
	    }).prop('disabled', !$.support.fileInput)
	        .parent().addClass($.support.fileInput ? undefined : 'disabled');	
		
		
	}
	
	var runPrint = function(){
		$("#btnSecretariaImprimir" ).click(function() {
			 $("#btnImprimir").prop('disabled',false);
			 $("#modal_secretaria").modal("show");
		});
		
		$("#btnSetorImprimir" ).click(function() {
			 $("#btnImprimir").prop('disabled',false);
			 $("#modal_setor").modal("show");
		});	
		
		$("#btnLocalImprimir" ).click(function() {
			$("#btnImprimir").prop('disabled',false);
			 $("#modal_local").modal("show");
		});
		
		$("#btnGrupoImprimir" ).click(function() {
			 $("#btnImprimir").prop('disabled',false);
			 $("#modal_grupo").modal("show");
		});
		
		$("#btnSubgrupoImprimir" ).click(function() {
			$("#btnImprimir").prop('disabled',false);
			 $("#modal_subgrupo").modal("show");
		});	
		
		$("#btnMaterialImprimir" ).click(function() {
			$("#btnImprimir").prop('disabled',false);
			 $("#modal_material").modal("show");
		});	
		
		$("#btnMarcaImprimir" ).click(function() {
			 $("#btnImprimir").prop('disabled',false);
			 $("#modal_marca").modal("show");
		});	
		
		$("#btnModeloImprimir" ).click(function() {
			 $("#btnImprimir").prop('disabled',false);
			 $("#modal_modelo").modal("show");
		});
		
		$("#btnFornecedorImprimir" ).click(function() {
			 $("#btnImprimir").prop('disabled',false);
			 $("#modal_fornecedor").modal("show");
		});	
		
		$( "#codsecretariaimprimir" ).blur(function() {
			//console.log(this.value);
			$("#btnImprimir").prop('disabled',false);
			exibirSecretaria(this.value);
		});		
		
		$( "#codsetorimprimir" ).blur(function() {
			$("#btnImprimir").prop('disabled',false);
			exibirSetor(this.value);
		});
		
		$( "#codlocalimprimir" ).blur(function() {	
			$("#btnImprimir").prop('disabled',false);
			exibirLocal(this.value);
		});		

		$( "#codgrupoimprimir" ).blur(function() {
			//console.log(this.value);
			$("#btnImprimir").prop('disabled',false);
			exibirGrupo(this.value);
		});	
		
		$( "#codsubgrupoimprimir" ).blur(function() {
			$("#btnImprimir").prop('disabled',false);
			exibirSubGrupo(this.value);
		});	
		
		$( "#codmaterialimprimir" ).blur(function() {
			$("#btnImprimir").prop('disabled',false);
			exibirMaterial(this.value);
		});
		
		$( "#codmarcaimprimir" ).blur(function() {	
			$("#btnImprimir").prop('disabled',false);
			exibirMarca(this.value);
		});	
		
		$( "#codmodeloimprimir" ).blur(function() {	
			$("#btnImprimir").prop('disabled',false);
			exibirModelo(this.value);
		});	
		
		$( "#codfornecedorimprimir" ).blur(function() {
			$("#btnImprimir").prop('disabled',false);
			exibirFornecedor(this.value);
		});		
		
		
	}

	return {
		init: function() {
			runValidator();
			runSelect();
			runSituacao();
			runCheckBox();
			runEvent();
			runPrint();
			runDataTable();
			runUpload();
		}
	};

}();
