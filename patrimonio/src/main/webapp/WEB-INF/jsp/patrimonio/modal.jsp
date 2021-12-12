<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Secretaria -->
<div id="modal_secretaria" class="modal fade">
	<div class="modal-dialog modal-lg ">
		<div class="modal-content">
			<div class="modal-header bg-success">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h6 class="modal-title">Secretaria</h6>
			</div>

			<div class="modal-body">
				<table class="table datatable-responsive" id="grid-secretaria">
					<thead>
						<tr>
							<th class="text-center col-md-1">Codigo</th>
							<th>Nome</th>
						</tr>
					</thead>		
				</table>				
			</div>
		</div>
	</div>
</div>
<!-- /Secretaria -->

<!-- Setor -->
<div id="modal_setor" class="modal fade">
	<div class="modal-dialog modal-lg ">
		<div class="modal-content">
			<div class="modal-header bg-success">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h6 class="modal-title">Setor</h6>
			</div>

			<div class="modal-body">
				<table class="table datatable-responsive" id="grid-setor">
					<thead>
						<tr>
							<th class="text-center col-md-1">Codigo</th>
							<th>Secretaria</th>
							<th>Setor</th>
						</tr>
					</thead>		
				</table>				
			</div>
		</div>
	</div>
</div>
<!-- /Setor -->

<!-- Local -->
<div id="modal_local" class="modal fade">
	<div class="modal-dialog modal-lg ">
		<div class="modal-content">
			<div class="modal-header bg-success">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h6 class="modal-title">Local</h6>
			</div>

			<div class="modal-body">
				<table class="table datatable-responsive" id="grid-local">
					<thead>
						<tr>
							<th class="text-center col-md-1">Codigo</th>
							<th>Secretaria</th>
							<th>Setor</th>
							<th>Local</th>
						</tr>
					</thead>		
				</table>				
			</div>
		</div>
	</div>
</div>
<!-- /Local -->

<!-- Grupo -->
<div id="modal_grupo" class="modal fade">
	<div class="modal-dialog modal-lg ">
		<div class="modal-content">
			<div class="modal-header bg-success">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h6 class="modal-title">Grupo</h6>
			</div>

			<div class="modal-body">
				<table class="table datatable-responsive" id="grid-grupo">
					<thead>
						<tr>
							<th class="text-center col-md-1">Codigo</th>
							<th>Grupo</th>
						</tr>
					</thead>		
				</table>				
			</div>
		</div>
	</div>
</div>
<!-- /Grupo -->

<!-- Subgrupo -->
<div id="modal_subgrupo" class="modal fade">
	<div class="modal-dialog modal-lg ">
		<div class="modal-content">
			<div class="modal-header bg-success">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h6 class="modal-title">SubGrupo</h6>
			</div>

			<div class="modal-body">
				<table class="table datatable-responsive" id="grid-subgrupo">
					<thead>
						<tr>
							<th class="text-center col-md-1">Codigo</th>
							<th>Grupo</th>
							<th>SubGrupo</th>
						</tr>
					</thead>		
				</table>				
			</div>
		</div>
	</div>
</div>
<!-- /Subgrupo -->

<!-- Material -->
<div id="modal_material" class="modal fade">
	<div class="modal-dialog modal-lg ">
		<div class="modal-content">
			<div class="modal-header bg-success">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h6 class="modal-title">Material</h6>
			</div>

			<div class="modal-body">
				<table class="table datatable-responsive" id="grid-material">
					<thead>
						<tr>
							<th class="text-center col-md-1">Codigo</th>							
							<th>Grupo</th>
							<th>SubGrupo</th>
							<th>Material</th>
						</tr>
					</thead>		
				</table>				
			</div>
		</div>
	</div>
</div>
<!-- /Material -->

<!-- Marca -->
<div id="modal_marca" class="modal fade">
	<div class="modal-dialog modal-lg ">
		<div class="modal-content">
			<div class="modal-header bg-success">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h6 class="modal-title">Marca</h6>
			</div>

			<div class="modal-body">
				<table class="table datatable-responsive" id="grid-marca">
					<thead>
						<tr>
							<th class="text-center col-md-1">Codigo</th>							
							<th>Marca</th>
						</tr>
					</thead>		
				</table>				
			</div>
		</div>
	</div>
</div>
<!-- /Marca -->

<!-- Modelo -->
<div id="modal_modelo" class="modal fade">
	<div class="modal-dialog modal-lg ">
		<div class="modal-content">
			<div class="modal-header bg-success">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h6 class="modal-title">Modelo</h6>
			</div>

			<div class="modal-body">
				<table class="table datatable-responsive" id="grid-modelo">
					<thead>
						<tr>
							<th class="text-center col-md-1">Codigo</th>							
							<th>Marca</th>
							<th>Modelo</th>
						</tr>
					</thead>		
				</table>				
			</div>
		</div>
	</div>
</div>
<!-- /Modelo -->

<!-- Fornecedor -->
<div id="modal_fornecedor" class="modal fade">
	<div class="modal-dialog modal-lg ">
		<div class="modal-content">
			<div class="modal-header bg-success">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h6 class="modal-title">Fornecedor</h6>
			</div>

			<div class="modal-body">
				<table class="table datatable-responsive" id="grid-fornecedor">
					<thead>
						<tr>
							<th class="text-center col-md-1">Codigo</th>							
							<th>CNPJ</th>
							<th>Razão Social</th>
						</tr>
					</thead>		
				</table>				
			</div>
		</div>
	</div>
</div>
<!-- /Fornecedor -->


<!-- Arquivo -->
<div id="modal_arquivo" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-success">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h6 class="modal-title">Arquivo</h6>
			</div>

			<div class="modal-body">
				<input type="hidden" name="file-arquivo" id="file-arquivo"/>
				<div class="form-group">
					<label>Descrição Arquivo:</label>
					<input type="text" class="form-control" style="text-transform: uppercase;" name="descricaoarquivo" id="descricaoarquivo" placeholder="Entre com a Descrição">
				</div>	
				<div class="text-right">
					<button type="button" class="btn btn-primary" onclick="atualizarArquivo();">Atualizar <i class="icon-arrow-right14 position-right"></i></button>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /Arquivo -->


<!-- Imprimir Relatorio -->
<div id="modal_imprimir" class="modal fade">
	<div class="modal-dialog ">
		<div class="modal-content">
			<div class="modal-header bg-success">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h6 class="modal-title">Imprimir</h6>
			</div>

			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="display-block text-semibold">Tipo de Relatório</label>
							<label class="radio-inline">
								<input type="radio" name="rdbImprimir" class="styled" value="1">
								Sintético
							</label>
		
							<label class="radio-inline">
								<input type="radio" name="rdbImprimir" class="styled" value="2">
								Simplificado
							</label>
						</div>
					</div>	
				</div>
				<div class="row">
					<button type="button" id="btnImprimirTipo" class="btn btn-primary btn-block">IMPRIMIR</button>
				</div>			
			</div>
		</div>
	</div>
</div>
<!-- /Fornecedor -->

