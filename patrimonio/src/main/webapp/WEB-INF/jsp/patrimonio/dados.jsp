<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<fieldset>
	<legend class="text-semibold"><i class="icon-files-empty position-left"></i> Entrada de Dados</legend>
	<div class="row">			
		<div class="col-md-4">
			<div class="form-group">
				<label>Nº Patrimônio:</label>
				<div class="input-group">
					<input type="text" class="form-control" name="numeropatrimonio" id="numeropatrimonio">
					<span class="input-group-btn">
						<button class="btn btn-primary" id="btnGeraUUID" type="button">Gerar</button>
					</span>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="form-group">
				<label>Nº Serial:</label>
				<input type="text" class="form-control" name="numeroserial" id="numeroserial" style="text-transform: uppercase;">
			</div>
		</div>	
		<div class="col-md-4">	
			<div class="form-group">
				<label>Situação:</label>
				<select data-placeholder="Situação" name="situacao" id="situacao" class="select">
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
				<input type="text" class="form-control"  name="codsecretaria" id="codsecretaria"/>
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="form-group">
				<label>Secretaria:</label>
				<div class="input-group">
					<input type="text" class="form-control"  name="secretaria" id="secretaria" readonly="readonly"/>
					<span class="input-group-btn">
						<button type="button" id="btnSecretaria" class="btn btn-primary">
							<i class="icon-menu7"></i>							
						</button>			
					</span>	
				</div>
			</div>
		</div>
		
		<div class="col-md-1">
			<div class="form-group">
				<label>Cod. Setor:</label>
				<input type="text" class="form-control"  name="codsetor" id="codsetor"/>
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="form-group">
				<label>Setor:</label>
				<div class="input-group">
					<input type="text" class="form-control"  name="setor" id="setor" readonly="readonly"/>
					<span class="input-group-btn">
						<button type="button" id="btnSetor" class="btn btn-primary">
							<i class="icon-menu7"></i>							
						</button>			
					</span>	
				</div>
			</div>
		</div>		
	
		<div class="col-md-1">
			<div class="form-group">
				<label>Cod. Local:</label>
				<input type="text" class="form-control"  name="codlocal" id="codlocal"/>
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="form-group">
				<label>Local:</label>
				<div class="input-group">
					<input type="text" class="form-control"  name="local" id="local" readonly="readonly"/>
					<span class="input-group-btn">
						<button type="button" id="btnLocal" class="btn btn-primary">
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
				<input type="text" class="form-control"  name="codgrupo" id="codgrupo"/>
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="form-group">
				<label>Grupo:</label>
				<div class="input-group">
					<input type="text" class="form-control"  name="grupo" id="grupo" readonly="readonly"/>
					<span class="input-group-btn">
						<button type="button" id="btnGrupo" class="btn btn-primary">
							<i class="icon-menu7"></i>							
						</button>			
					</span>	
				</div>
			</div>
		</div>
		
		<div class="col-md-1">
			<div class="form-group">
				<label>Cod. SubGrupo:</label>
				<input type="text" class="form-control"  name="codsubgrupo" id="codsubgrupo"/>
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="form-group">
				<label>SubGrupo:</label>
				<div class="input-group">
					<input type="text" class="form-control"  name="subgrupo" id="subgrupo" readonly="readonly"/>
					<span class="input-group-btn">
						<button type="button" id="btnSubgrupo" class="btn btn-primary">
							<i class="icon-menu7"></i>							
						</button>			
					</span>	
				</div>
			</div>
		</div>		

		<div class="col-md-1">
			<div class="form-group">
				<label>Cod. Material:</label>
				<input type="text" class="form-control"  name="codmaterial" id="codmaterial"/>
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="form-group">
				<label>Material:</label>
				<div class="input-group">
					<input type="text" class="form-control"  name="material" id="material" readonly="readonly"/>
					<span class="input-group-btn">
						<button type="button" id="btnMaterial" class="btn btn-primary">
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
				<input type="text" class="form-control"  name="codmarca" id="codmarca"/>
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="form-group">
				<label>Marca:</label>
				<div class="input-group">
					<input type="text" class="form-control"  name="marca" id="marca" readonly="readonly"/>
					<span class="input-group-btn">
						<button type="button" id="btnMarca" class="btn btn-primary">
							<i class="icon-menu7"></i>							
						</button>			
					</span>	
				</div>
			</div>
		</div>
		
		<div class="col-md-1">
			<div class="form-group">
				<label>Cod. Modelo:</label>
				<input type="text" class="form-control"  name="codmodelo" id="codmodelo"/>
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="form-group">
				<label>Modelo:</label>
				<div class="input-group">
					<input type="text" class="form-control"  name="modelo" id="modelo" readonly="readonly"/>
					<span class="input-group-btn">
						<button type="button" id="btnModelo" class="btn btn-primary">
							<i class="icon-menu7"></i>							
						</button>			
					</span>	
				</div>
			</div>
		</div>		

		<div class="col-md-1">
			<div class="form-group">
				<label>Cod. Fornecedor:</label>
				<input type="text" class="form-control"  name="codfornecedor" id="codfornecedor"/>
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="form-group">
				<label>Fornecedor:</label>
				<div class="input-group">
					<input type="text" class="form-control"  name="fornecedor" id="fornecedor" readonly="readonly"/>
					<span class="input-group-btn">
						<button type="button" id="btnFornecedor" class="btn btn-primary">
							<i class="icon-menu7"></i>							
						</button>			
					</span>	
				</div>
			</div>
		</div>	
		
	</div>
	
	<div class="row">
		<div class="col-md-4">
			<div class="form-group">
				<label class="text-semibold">Foto Patrimônio:</label>
				<div class="media no-margin-top">
					<div class="media-left">
						<a href="#"><img src="<%= request.getContextPath()%>/assets/images/placeholder.jpg" id="imgUpload" style="width: 150px; height: 150px; border-radius: 2px;" alt=""></a>
					</div>

					<div class="media-body">
						<input type="file" id="filepatrimonio" class="file-styled">
						<span class="help-block">Formatos Aceitos: gif, png, jpg. Tamanho Maximo 2Mb</span>
					</div>
				</div>
			</div>	
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-12">
			<div class="checkbox">
				<label>
					<input type="checkbox" name="chkBaixa" id="chkBaixa" class="control-success" value="1">
					Baixar Patrimônio
				</label>
			</div>			
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
				<label>Observação:</label>
				<textarea rows="5" cols="5" name="obspatrimonio" id="obspatrimonio" class="form-control" placeholder="Entre com sua Observação"></textarea>
			</div>		
		</div>
	</div>

</fieldset>
<fieldset>
	<legend class="text-semibold"><i class="icon-upload position-left"></i> Uploads</legend>
	<input type="file" name="filearquivo" id="filearquivo" style="display: none">
	<div class="row">
		<div class="col-md-4">
			<div class="btn-group">
				<button type="button" class="btn bg-primary-400 btn-labeled"><b><i class="icon-file-upload"></i></b> Arquivos</button>
                  	<button type="button" class="btn bg-primary-400 dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
                  	<ul class="dropdown-menu dropdown-menu-right">
					<li><a href="javascript:" onclick="uploadArquivo();"><i class="icon-file-plus"></i> Upload Arquivo</a></li>
					<li><a href="javascript:" onclick="mergeArquivo();"><i class="icon-files-empty"></i> Juntar Arquivo</a></li>
				</ul>
			</div>			
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
				<div class="table-responsive">
					<table class="table" id="table-arquivo">
						<thead>
							<tr>
								<th>Descrição</th>
								<th>Arquivo</th>
								<th class="text-center col-md-1">Data</th>
								<th class="text-center col-md-1">Hora</th>
								<th class="text-center col-md-1"><i class="icon-menu-open2"></i></th>
							</tr>
						</thead>
						<tbody id="corpo-arquivo">
						</tbody>
					</table>
				</div>			
		</div>
	</div>
</fieldset>	