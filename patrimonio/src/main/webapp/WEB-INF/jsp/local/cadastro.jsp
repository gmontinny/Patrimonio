<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<script type="text/javascript" src="<%= request.getContextPath() %>/assets/js/cadastro/local.js"></script>
<script>
	jQuery(document).ready(function() {
		local.init();
	});   	
</script>
    

<div class="content" style="margin-top: 20px;">

	<form action="" name="frm_local" id="frm_local" method="post" >
		<input type="hidden" name="codigo" id="codigo"/>
		<input type="hidden" name="acao" id="acao" value="${ativar}"/>
		<div class="panel panel-flat">
			<div class="panel-heading">
				<h5 class="panel-title">Cadastro de Local</h5>
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
							<div class="col-md-4">	
								<div class="form-group">
									<label>Secretaria:</label>
									<select data-placeholder="Secretaria" name="secretaria" id="secretaria" class="select" >
										<option></option>
									</select>
								</div>
							</div>
							<div class="col-md-4">	
								<div class="form-group">
									<label>Setor:</label>
									<select data-placeholder="Setor" name="setor" id="setor" class="select" disabled="disabled">
										<option></option>
									</select>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Descrição Local:</label>
									<input type="text" class="form-control" style="text-transform: uppercase;" name="descricaolocal" id="descricaolocal" placeholder="Entre com a descrição do local">
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
						<table class="table datatable-responsive" id="grid-local">
							<thead>
								<tr>
									<th>Secretaria</th>
									<th>Setor</th>
									<th>Local</th>
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