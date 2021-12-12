<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<script type="text/javascript" src="<%= request.getContextPath() %>/assets/js/cadastro/transferencia.js"></script>
<script>
	jQuery(document).ready(function() {
		Transferencia.init();
	});   	
</script>
    

<form action="" name="frm_transferencia" id="frm_transferencia" method="post" >
<div class="content" style="margin-top: 20px;">

		<input type="hidden" name="codigo" id="codigo"/>
		<input type="hidden" name="numprocesso" id="numprocesso"/>
		<input type="hidden" name="idlocalOrigem" id="idlocalOrigem"/>		
 		<input type="hidden" name="acao" id="acao" value="${ativa}"/>
		<div class="panel panel-flat">
			<div class="panel-heading">
				<h5 class="panel-title">Cadastro de Transferência</h5>
				<div class="heading-elements">
					<div class="heading-btn">
						<button type="button" id="btnImprimirTransferenciaGeral" class="btn btn-primary btn-float btn-float-lg">
							<i class="icon-shredder"></i>							
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
							<div class="col-md-3">
								<div class="form-group">
									<label>Patrimonio:</label>
									<input type="text" class="form-control" name="numeropatrimonio" id="numeropatrimonio">
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Serial:</label>
									<input type="text" class="form-control" style="text-transform: uppercase;" name="numeroserial" id="numeroserial">
								</div>
							</div>
							<div class="col-md-4">
								<button type="button" class="btn btn-primary" id="btnPesquisar" style="margin-top: 25px;"><i class="icon-search4 position-left"></i> Pesquisar</button>								
							</div>
	
						</fieldset>
					</div>
				</div>
				
				<div id="exibirPesquisaTransferencia" style="display: none;">
					<div class="row" >
						<div class="col-md-12">
							<div class="table-responsive">
								<table class="table table-bordered">
									<tbody>
										<tr>
											<td class="col-md-4">
												<dl>
													<dt class="text-bold">Secretaria</dt>
													<dd id="nomesecretaria"></dd>
												</dl>
											</td>
											<td class="col-md-4">
												<dl>
													<dt class="text-bold">Setor</dt>
													<dd id="nomesetor"></dd>
												</dl>										
											</td>
											<td class="col-md-4">
												<dl>
													<dt class="text-bold">Local</dt>
													<dd id="nomelocal"></dd>
												</dl>										
											</td>
										</tr>
										<tr>
											<td class="col-md-4">
												<dl>
													<dt class="text-bold">Grupo</dt>
													<dd id="nomegrupo"></dd>
												</dl>
											</td>
											<td class="col-md-4">
												<dl>
													<dt class="text-bold">Sub Grupo</dt>
													<dd id="nomesubgrupo"></dd>
												</dl>										
											</td>
											<td class="col-md-4">
												<dl>
													<dt class="text-bold">Material</dt>
													<dd id="nomematerial"></dd>
												</dl>										
											</td>
										</tr>
										<tr>
											<td class="col-md-4">
												<dl>
													<dt class="text-bold">Marca</dt>
													<dd id="nomemarca"></dd>
												</dl>
											</td>
											<td class="col-md-4">
												<dl>
													<dt class="text-bold">Modelo</dt>
													<dd id="nomemodelo"></dd>
												</dl>										
											</td>
											<td class="col-md-4">
												<dl>
													<dt class="text-bold">Fornecedor</dt>
													<dd id="nomefornecedor"></dd>
												</dl>										
											</td>
										</tr>									
										<tr>
											<td class="col-md-4" colspan="3">
											   <dl>
											   		<dt class="text-bold">Imagem</dt>
											   		<dd>
														<table style="width: 200px">
															<tbody>
																<tr>
																	<td class="col-md-2">
																		<img width="200" id="imgPatrimonio" src="<%= request.getContextPath() %>/assets/images/placeholder.jpg">
																	</td>
																</tr>
															</tbody>
														</table>
													</dd>
												</dl>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<!-- /both borders -->					
						</div>
						<fieldset>
							<legend class="text-semibold">
								<i class="icon-file-text2 position-left"></i>
								Setor de Destino
							</legend>
							<div class="row">														
								<div class="col-md-4">
									<div class="form-group">
										<label>Secretaria:</label>
										<select data-placeholder="Selecione Secretaria" name="secretariaDestino" id="secretariaDestino" >
										</select>
									</div>								
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Setor:</label>
										<select data-placeholder="Selecione Setor" name="setorDestino" id="setorDestino" disabled="disabled" class="select">
										</select>
									</div>							
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Local:</label>
										<select data-placeholder="Selecione local" name="localDestino" id="localDestino" disabled="disabled" class="select">
										</select>
									</div>							
								</div>							
							</div>
						</fieldset>
						<div class="row">
							<div class="col-md-12">
								<button type="button" id="btnTransferencia" disabled class="btn btn-primary btn-block">TRANSFERIR</button>
							</div>
						</div>
					</div>
					<div class="row" style="margin-top: 10px;">
						<div class="col-md-12" id="exibeGrid" style="display: none;">
							<fieldset>
								<legend class="text-semibold"><i class="icon-table position-left"></i> Dados da Grid</legend>
							</fieldset>	
							<table class="table datatable-responsive" id="grid-transferencia">
								<thead>
									<tr>
										<th>Nº Patrimônio</th>
										<th>Serial</th>
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
		</div>
</form>

<!-- Modal Impressão Transferencia Sintetica -->
<jsp:include page="modal.jsp"/>	

 