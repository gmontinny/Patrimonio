<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<script type="text/javascript" src="<%= request.getContextPath() %>/assets/js/cadastro/secretaria.js"></script>
<script>
	jQuery(document).ready(function() {
		secretaria.init();
	});   	
</script>
    

<div class="content" style="margin-top: 20px;">

	<form action="" name="frm_secretaria" id="frm_secretaria" method="post" >
		<input type="hidden" name="codigo" id="codigo"/>
		<input type="hidden" name="acao" id="acao" value="${ativar}"/>
		<div class="panel panel-flat">
			<div class="panel-heading">
				<h5 class="panel-title">Cadastro de Secretaria</h5>
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
									<label>Nome secretaria:</label>
									<input type="text" class="form-control" style="text-transform: uppercase;" name="nomesecretaria" id="nomesecretaria" placeholder="Entre com nome da secretaria">
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
						<table class="table datatable-responsive" id="grid-secretaria">
							<thead>
								<tr>
									<th>Nome</th>
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