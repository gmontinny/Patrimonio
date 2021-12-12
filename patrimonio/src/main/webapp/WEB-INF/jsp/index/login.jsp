<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form action="" method="post" name="frm_login" id="frm_login">
	<div class="panel panel-body login-form">
		<div class="text-center">
			<div class="icon-object border-slate-300 text-slate-300"><i class="icon-reading"></i></div>
			<h5 class="content-group">Login para acesso ao sistema  <small class="display-block">Entre com as suas credências</small></h5>
		</div>

		<div class="form-group has-feedback has-feedback-left">
			<input type="text" class="form-control" name="cpf" id="cpf" placeholder="CPF">
			<div class="form-control-feedback">
				<i class="icon-user text-muted"></i>
			</div>
		</div>

		<div class="form-group has-feedback has-feedback-left">
			<input type="password" class="form-control" name="senha" id="senha" placeholder="Senha">
			<div class="form-control-feedback">
				<i class="icon-lock2 text-muted"></i>
			</div>
		</div>

		<div class="form-group">
			<button type="submit" class="btn bg-success-400 btn-block">Entrar <i class="icon-circle-right2 position-right"></i></button>
		</div>

		<div class="text-center">
			<a href="javascript:" id="linkEsqueceu">Esqueceu senha?</a>
		</div>
	</div>
</form>    