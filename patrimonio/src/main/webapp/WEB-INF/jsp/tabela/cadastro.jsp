<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<script type="text/javascript" src="<%= request.getContextPath() %>/assets/js/cadastro/tabela.js"></script>
<script>
	jQuery(document).ready(function() {
		Tabela.init();
	});   	
</script>
    

<div class="content" style="margin-top: 20px;">

	<form action="" name="frm_tabela" id="frm_tabela" method="post" >
		<input type="hidden" name="codigo" id="codigo"/>
		<input type="hidden" name="acao" id="acao" value="${ativar}"/>
		<div class="panel panel-flat">
			<div class="panel-heading">
				<h5 class="panel-title">Cadastro de Tabela</h5>
				<div class="heading-elements">
					<div class="heading-btn">
						
						<button type="submit" class="btn btn-primary btn-float btn-float-lg">
							<i class="icon-floppy-disk"></i>							
						</button>
						
					</div>
				</div>				
			</div>

			<div id="error-alert" class="alert bg-danger alert-styled-right" style="display:none; margin-top: 20px; margin-left: 20px; margin-right: 20px;">										
				<span class="text-semibold">Problema !</span> 
				<div id="mensageProblema"></div>
		    </div>									
			
			<div id="success-alert" class="alert bg-success alert-styled-right" style="display:none; margin-top: 20px; margin-left: 20px; margin-right: 20px;">										
				<span class="text-semibold">Sucesso !</span> Seus dados foram gravados com sucesso.
		    </div>		
	
			<div class="panel-body">
				<div class="row">
					<div class="col-md-12">
						<fieldset>
							<legend class="text-semibold"><i class="icon-files-empty position-left"></i> Entrada de Dados</legend>
							<div class="col-md-6">
								<div class="form-group">
									<label>Nome Tabela:</label>
									<input type="text" class="form-control" name="nometabela" id="nometabela" placeholder="Entre com nome da tabela">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Descrição da Tabela:</label>
									<input type="text" class="form-control" name="descricaotabela" id="descricaotabela" placeholder="Entre com a descrição da tabela">
								</div>
							</div>	
							<div class="col-md-6">	
								<div class="form-group">
									<label>Tipo de Tabela:</label>
									<select data-placeholder="Tipo Tabela" name="tipotabela" id="tipotabela" class="select">
										<option></option>
										<option value="1">Administrador</option>
										<option value="2">Operacional</option>
										<option value="-1">Inativo</option>											
									</select>
								</div>
							</div>
							
							<div class="col-md-6">	
								<div class="form-group">
									<label>Ordem da Tabela:</label>
									<input type="number" class="form-control" name="ordemtabela" id="ordemtabela" min="0" max="999">
								</div>
							</div>							
							<div class="col-md-6">
								<div class="form-group">
									<label>URL da Tabela:</label>
									<input type="text" class="form-control" name="urltabela" id="urltabela" placeholder="Entre com a url da tabela">
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label>Icon da Tabela:</label>
									<input type="text" class="form-control" name="icontabela" id="icontabela" placeholder="Entre com a icon da tabela">
								</div>
							</div>		
									
	
						</fieldset>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-12">
						<fieldset>
							<legend class="text-semibold"><i class="icon-table position-left"></i> Dados da Grid</legend>
						</fieldset>	
						<table class="table datatable-responsive" id="grid-tabela">
							<thead>
								<tr>
									<th class="col-md-2">Nome</th>
									<th>Descrição</th>
									<th class="text-center col-md-2">Tipo Tabela</th>
									<th class="text-center col-md-1">Ordem Tabela</th>
									<th class="col-md-2">Icon Tabela</th>
									<th class="col-md-2">URL</th>
									<th class="text-center col-md-1">Ação</th>
								</tr>
							</thead>		
						</table>
					</div>
				</div>

			</div>
		</div>
	</form>
	
</div>    