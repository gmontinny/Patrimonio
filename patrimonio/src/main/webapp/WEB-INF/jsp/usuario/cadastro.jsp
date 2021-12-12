<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<script type="text/javascript" src="<%= request.getContextPath() %>/assets/js/cadastro/usuario.js"></script>
<script>
	jQuery(document).ready(function() {
		usuario.init();
	});   	
</script>
    

<div class="content" style="margin-top: 20px;">

	<form action="" name="frm_usuario" id="frm_usuario" method="post" >
		<input type="hidden" name="codigo" id="codigo"/>
		<input type="hidden" name="dadosImagem" id="dadosImagem"/>
		<input type="hidden" name="acao" id="acao" value="${ativar}"/>
		<input type="file" name="inputimagem" id="inputimagem" style="display: none;"/>
		<div class="panel panel-flat">
			<div class="panel-heading">
				<h5 class="panel-title">Cadastro de Usuario</h5>
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
								<div class="col-md-2">
									<div class="thumbnail">
										<div class="thumb">
											<img src="<%= request.getContextPath() %>/assets/images/placeholder.jpg" name="imgDefault" id="imgDefault" alt="">
											<img alt="" src="" style="display: none;" name="imgUsuario" id="imgUsuario">
											<div class="caption-overflow">
												<span>
													<a href="javascript:" id="btnOpenFile" class="btn btn-info btn-sm">Adicionar</a>
													<a href="javascript:" id="btnRemoveFile" class="btn btn-info btn-sm">Remover</a>
												</span>
											</div>
										</div>
									</div>
								</div>							
								<div class="col-md-10">							
									<div class="col-md-12">
										<div class="form-group">
											<label>Nome Usuario:</label>
											<input type="text" class="form-control" name="nomeusuario" id="nomeusuario" style="text-transform: uppercase;" placeholder="Entre com NOME do usuario">
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label>Senha do Usuario:</label>
											<input type="password" class="form-control" name="senhausuario" id="senhausuario"  placeholder="Entre com a SENHA do usuario">
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label>CPF do Usuario:</label>
											<input type="text" class="form-control" name="cpfusuario" id="cpfusuario" placeholder="Entre com CPF do Usuario">
										</div>
									</div>
								</div>
							</div>
							<div class="row">	
								<div class="col-md-4">
										<div class="form-group">
											<label>Email do Usuario:</label>
											<input type="text" class="form-control" name="emailusuario" id="emailusuario" style="text-transform: lowercase;" placeholder="Entre com EMAIL do Usuario">
										</div>
								</div>
									
								<div class="col-md-4">	
									<div class="form-group">
										<label>Tipo de usuario:</label>
										<select data-placeholder="Tipo usuario" name="tipousuario" id="tipousuario" class="select">
											<option></option>
											<option value="1">Administrador</option>
											<option value="2">Operacional</option>
											<option value="-1">Inativo</option>											
										</select>
									</div>
								</div>
								
								<div class="col-md-4">	
									<div class="form-group">
										<label>Status do usuario:</label>
										<select data-placeholder="Status do usuario" name="statususuario" id="statususuario" class="select">
											<option></option>
											<option value="1">Ativo</option>
											<option value="0">Inativo</option>											
										</select>
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
						<table class="table datatable-responsive" id="grid-usuario">
							<thead>
								<tr>
									<th>Nome</th>
									<th class="text-center col-md-1">CPF</th>
									<th>Email</th>
									<th class="text-center col-md-1">Status</th>
									<th class="text-center col-md-2">Tipo usuario</th>
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