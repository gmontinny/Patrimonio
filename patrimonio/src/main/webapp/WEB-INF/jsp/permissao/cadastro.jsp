<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<script type="text/javascript" src="<%= request.getContextPath() %>/assets/js/cadastro/permissao.js"></script>
<script>
	jQuery(document).ready(function() {
		permissao.init();
	});   	
</script>
    

<div class="content" style="margin-top: 20px;">

	<form action="#" name="frm_permissao" id="frm_permissao" method="post">
		<input type="hidden" name="codigo" id="codigo"/>

		<div class="panel panel-flat">
			<div class="panel-heading">
				<h5 class="panel-title">Cadastro de Permissão</h5>
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
						<div class="col-md-6">
							<div class="form-group">
								<label>Usuario:</label>
								<select data-placeholder="Selecione Usuario" name="permissaousuario" id="permissaousuario" class="select">
								</select>
							</div>
						</div>								
					</div>					
					
					<div class="row">
						<div class="col-md-12">
							<div class="table-responsive" id="tabela" style="display: none">
									<div class="form-group">	
										<label class="checkbox-inline">
											<input type="checkbox" class="styled chkTodos"  name="chkTodos" id="chkTodos">
											Marcar Todos
										</label>
									</div>
									<table class="table">
										<thead>
											<tr class="bg-success-600">											
												<th>Tabela</th>
												<th class="text-center">Ordem</th>
												<th>Select</th>
												<th>Insert</th>	
												<th>Update</th>	
												<th>Delete</th>											
											</tr>
										</thead>
										<tbody id="corpo">
										</tbody>
									</table>								
							</div>
						</div>
					</div>



			</div>
		</div>
	</form>
	
</div>    