<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<script type="text/javascript" src="<%= request.getContextPath() %>/assets/js/cadastro/fornecedor.js"></script>
<script>
	jQuery(document).ready(function() {
		fornecedor.init();
	});   	
</script>
    

<div class="content" style="margin-top: 20px;">

	<form action="" name="frm_fornecedor" id="frm_fornecedor" method="post" >
		<input type="hidden" name="codigo" id="codigo"/>
		<input type="hidden" name="acao" id="acao" value="${ativa}"/>
		<div class="panel panel-flat">
			<div class="panel-heading">
				<h5 class="panel-title">Cadastro de Fornecedor</h5>
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
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Razão Social:</label>
										<input type="text" class="form-control" style="text-transform: uppercase;" name="razaosocial" id="razaosocial" placeholder="Entre com a Razão Social">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Nome Fantasia:</label>
										<input type="text" class="form-control" style="text-transform: uppercase;" name="nomefantasia" id="nomefantasia" placeholder="Entre com Nome Fantasia">
									</div>
								</div>							
								<div class="col-md-4">
									<div class="form-group">
										<label>CNPJ:</label>
										<input type="text" class="form-control"  name="cnpjfornecedor" id="cnpjfornecedor">
									</div>
								</div>
							</div>								
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Endereço:</label>
										<input type="text" class="form-control"  name="enderecofornecedor" id="enderecofornecedor">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Bairro:</label>
										<input type="text" class="form-control"  name="bairrofornecedor" id="bairrofornecedor">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Email:</label>
										<input type="text" class="form-control"  name="emailfornecedor" id="emailfornecedor" style="text-transform: lowercase;">
									</div>
								</div>							
							</div>
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Telefone:</label>
										<input type="text" class="form-control"  name="telefonefornecedor" id="telefonefornecedor">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Celular:</label>
										<input type="text" class="form-control"  name="celularfornecedor" id="celularfornecedor">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Contato:</label>
										<input type="text" class="form-control" style="text-transform: uppercase;"  name="contatofornecedor" id="contatofornecedor">
									</div>
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
						<table class="table datatable-responsive" id="grid-fornecedor">
							<thead>
								<tr>
									<th>Razão Social</th>
									<th>Nome Fantasia</th>
									<th class="text-center">CNPJ</th>
									<th>Email</th>
									<th class="text-center">Celular</th>
									<th>Contato</th>
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