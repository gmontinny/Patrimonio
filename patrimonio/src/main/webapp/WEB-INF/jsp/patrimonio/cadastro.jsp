<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<script type="text/javascript" src="<%= request.getContextPath() %>/assets/js/cadastro/patrimonio.js"></script>
<script>
	jQuery(document).ready(function() {
		patrimonio.init();
	});   	
</script>
    

<div class="content" style="margin-top: 20px;">

	<form action="" name="frm_patrimonio" id="frm_patrimonio" method="post" >		
		<input type="hidden" name="numeroarquivo" id="numeroarquivo"/>
		<input type="hidden" name="imagempatrimonio" id="imagempatrimonio"/>
		<input type="hidden" name="idarquivo" id="idarquivo"/>
		<input type="hidden" name="acao" id="acao" value="${ativa}"/>
		<div class="panel panel-flat">
			<div class="panel-heading">
				<h5 class="panel-title">Cadastro de Patrimonio</h5>
				<div class="heading-elements">
					<div class="heading-btn">
						
						<button type="button" id="btnGrid" class="btn btn-primary btn-float btn-float-lg" title="Grid">
							<i class="icon-grid"></i>							
						</button>						
						
						<button type="submit" id="btnSalvar" class="btn btn-primary btn-float btn-float-lg" title="Salvar">
							<i class="icon-floppy-disk"></i>							
						</button>
						
						<button type="button" id="btnLimpar" style="display: none;" class="btn btn-primary btn-float btn-float-lg" title="Limpar">
							<i class="icon-eraser3"></i>							
						</button>						
						
						<button type="button" id="btnImprimir" disabled style="display: none;" class="btn btn-primary btn-float btn-float-lg" title="Imprimir">
							<i class="icon-shredder"></i>							
						</button>
						
						<button type="button" id="btnCadastro" style="display: none;" class="btn btn-primary btn-float btn-float-lg" title="Cadastrar">
							<i class="icon-pencil7"></i>							
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
					<div class="col-md-12" id="dados">
						<jsp:include page="dados.jsp"/>
					</div>
					<div class="col-md-12" id="grid" style="display: none;">
						<jsp:include page="grid.jsp"/>
					</div>					
				</div>

			</div>
		</div>
	</form>
	
	<!-- Modals -->
			<jsp:include page="modal.jsp"/>
	<!-- /Modals -->	
	
</div>    