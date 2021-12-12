<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<fieldset>
	<legend class="text-semibold"><i class="icon-grid position-left"></i>Lista de Patrimonio</legend>
	<div class="row">			
		<div class="col-md-4">
			<div class="form-group">
				<label>Nº Patrimônio:</label>
				<input type="text" class="form-control" name="numeropatrimonioimprimir" id="numeropatrimonioimprimir">
			</div>
		</div>
		<div class="col-md-4">
			<div class="form-group">
				<label>Nº Serial:</label>
				<input type="text" class="form-control" name="numeroserialimprimir" id="numeroserialimprimir" style="text-transform: uppercase;">
			</div>
		</div>	
		<div class="col-md-4">	
			<div class="form-group">
				<label>Situação:</label>
				<select data-placeholder="Situação" name="situacaoimprimir" id="situacaoimprimir" class="select">
					<option></option>
					<option value="4">DOADO</option>
					<option value="1">NOVO</option>
					<option value="2">USADO</option>
					<option value="3">QUEBRADO</option>
				</select>
			</div>
		</div>			
	</div>
	<div class="row">
		<div class="col-md-1">
			<div class="form-group">
				<label>Cod. Secretaria:</label>
				<input type="text" class="form-control"  name="codsecretariaimprimir" id="codsecretariaimprimir"/>
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="form-group">
				<label>Secretaria:</label>
				<div class="input-group">
					<input type="text" class="form-control"  name="secretariaimprimir" id="secretariaimprimir" readonly="readonly"/>
					<span class="input-group-btn">
						<button type="button" id="btnSecretariaImprimir" class="btn btn-primary">
							<i class="icon-menu7"></i>							
						</button>			
					</span>	
				</div>
			</div>
		</div>
		
		<div class="col-md-1">
			<div class="form-group">
				<label>Cod. Setor:</label>
				<input type="text" class="form-control"  name="codsetorimprimir" id="codsetorimprimir"/>
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="form-group">
				<label>Setor:</label>
				<div class="input-group">
					<input type="text" class="form-control"  name="setorimprimir" id="setorimprimir" readonly="readonly"/>
					<span class="input-group-btn">
						<button type="button" id="btnSetorImprimir" class="btn btn-primary">
							<i class="icon-menu7"></i>							
						</button>			
					</span>	
				</div>
			</div>
		</div>		
	
		<div class="col-md-1">
			<div class="form-group">
				<label>Cod. Local:</label>
				<input type="text" class="form-control"  name="codlocalimprimir" id="codlocalimprimir"/>
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="form-group">
				<label>Local:</label>
				<div class="input-group">
					<input type="text" class="form-control"  name="localimprimir" id="localimprimir" readonly="readonly"/>
					<span class="input-group-btn">
						<button type="button" id="btnLocalImprimir" class="btn btn-primary">
							<i class="icon-menu7"></i>							
						</button>			
					</span>	
				</div>
			</div>
		</div>
			
	</div>
	
	<div class="row">	
	
		<div class="col-md-1">
			<div class="form-group">
				<label>Cod. Grupo:</label>
				<input type="text" class="form-control"  name="codgrupoimprimir" id="codgrupoimprimir"/>
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="form-group">
				<label>Grupo:</label>
				<div class="input-group">
					<input type="text" class="form-control"  name="grupoimprimir" id="grupoimprimir" readonly="readonly"/>
					<span class="input-group-btn">
						<button type="button" id="btnGrupoImprimir" class="btn btn-primary">
							<i class="icon-menu7"></i>							
						</button>			
					</span>	
				</div>
			</div>
		</div>
		
		<div class="col-md-1">
			<div class="form-group">
				<label>Cod. SubGrupo:</label>
				<input type="text" class="form-control"  name="codsubgrupoimprimir" id="codsubgrupoimprimir"/>
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="form-group">
				<label>SubGrupo:</label>
				<div class="input-group">
					<input type="text" class="form-control"  name="subgrupoimprimir" id="subgrupoimprimir" readonly="readonly"/>
					<span class="input-group-btn">
						<button type="button" id="btnSubgrupoImprimir" class="btn btn-primary">
							<i class="icon-menu7"></i>							
						</button>			
					</span>	
				</div>
			</div>
		</div>		

		<div class="col-md-1">
			<div class="form-group">
				<label>Cod. Material:</label>
				<input type="text" class="form-control"  name="codmaterialimprimir" id="codmaterialimprimir"/>
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="form-group">
				<label>Material:</label>
				<div class="input-group">
					<input type="text" class="form-control"  name="materialimprimir" id="materialimprimir" readonly="readonly"/>
					<span class="input-group-btn">
						<button type="button" id="btnMaterialImprimir" class="btn btn-primary">
							<i class="icon-menu7"></i>							
						</button>			
					</span>	
				</div>
			</div>
		</div>	
		
	</div>


	<div class="row">	
	
		<div class="col-md-1">
			<div class="form-group">
				<label>Cod. Marca:</label>
				<input type="text" class="form-control"  name="codmarcaimprimir" id="codmarcaimprimir"/>
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="form-group">
				<label>Marca:</label>
				<div class="input-group">
					<input type="text" class="form-control"  name="marcaimprimir" id="marcaimprimir" readonly="readonly"/>
					<span class="input-group-btn">
						<button type="button" id="btnMarcaImprimir" class="btn btn-primary">
							<i class="icon-menu7"></i>							
						</button>			
					</span>	
				</div>
			</div>
		</div>
		
		<div class="col-md-1">
			<div class="form-group">
				<label>Cod. Modelo:</label>
				<input type="text" class="form-control"  name="codmodeloimprimir" id="codmodeloimprimir"/>
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="form-group">
				<label>Modelo:</label>
				<div class="input-group">
					<input type="text" class="form-control"  name="modeloimprimir" id="modeloimprimir" readonly="readonly"/>
					<span class="input-group-btn">
						<button type="button" id="btnModeloImprimir" class="btn btn-primary">
							<i class="icon-menu7"></i>							
						</button>			
					</span>	
				</div>
			</div>
		</div>		

		<div class="col-md-1">
			<div class="form-group">
				<label>Cod. Fornecedor:</label>
				<input type="text" class="form-control"  name="codfornecedorimprimir" id="codfornecedorimprimir"/>
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="form-group">
				<label>Fornecedor:</label>
				<div class="input-group">
					<input type="text" class="form-control"  name="fornecedorimprimir" id="fornecedorimprimir" readonly="readonly"/>
					<span class="input-group-btn">
						<button type="button" id="btnFornecedorImprimir" class="btn btn-primary">
							<i class="icon-menu7"></i>							
						</button>			
					</span>	
				</div>
			</div>
		</div>	
	</div>
	
	<div class="row">
		<button type="button" id="btnPesquisaPatrimonio" class="btn btn-primary btn-block">PESQUISAR</button>
	</div>
	
	<div class="row" id="tabela-patrimonio" style="display: none;">
		<div class="col-md-12">
				<table class="table datatable-selection-single">
					<thead>
						<tr>
			                <th>Patrimonio</th>
			                <th>Grupo</th>
			                <th>SubGrupo</th>
			                <th>Material</th>
			                <th>Marca</th>
			                <th>Modelo</th>
			                <th class="text-center col-md-1">Ação</th>
			            </tr>
					</thead>
					<tbody id="corpo-patrimonio">
			        </tbody>
			     </table>
					            
			
		</div>
	</div>
	
</fieldset>		