<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- Pesquisa -->
<div id="modal_pesquisa" class="modal fade">
	<div class="modal-dialog modal-lg ">
		<div class="modal-content">
			<div class="modal-header bg-success">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h6 class="modal-title">Transferência Sintética</h6>
			</div>

			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label>Secretaria:</label>
							<select data-placeholder="Selecione Secretaria" name="secretaria" id="secretaria" >
							</select>
						</div>						
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label>Setor:</label>
							<select data-placeholder="Selecione Setor" name="setor" id="setor" disabled="disabled" class="select">
							</select>
						</div>					
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label>Local:</label>
							<select data-placeholder="Selecione local" name="local" id="local" disabled="disabled" class="select">
							</select>
						</div>					
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<button type="button" name="btnConfirmaTransferenciaGeral" id="btnConfirmaTransferenciaGeral" class="btn btn-primary btn-block" disabled>IMPRIMIR</button>					
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /Pesquisa -->