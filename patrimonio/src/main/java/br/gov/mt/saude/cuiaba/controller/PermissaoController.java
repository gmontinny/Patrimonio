package br.gov.mt.saude.cuiaba.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.gov.mt.saude.cuiaba.component.UsuarioSessao;
import br.gov.mt.saude.cuiaba.component.Validacao;
import br.gov.mt.saude.cuiaba.dao.impl.PermissaoDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.TabelaDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.UsuarioDAOImpl;
import br.gov.mt.saude.cuiaba.model.Permissao;
import br.gov.mt.saude.cuiaba.model.Tabela;
import br.gov.mt.saude.cuiaba.model.Usuario;

@Controller
public class PermissaoController {
	private final Result result;
	private final PermissaoDAOImpl permissaodao;
	private final UsuarioDAOImpl usuariodao;
	private final UsuarioSessao sessao;
	private final TabelaDAOImpl tabeladao;
	private final Validacao validacao;

	@Inject
	public PermissaoController(Result result, PermissaoDAOImpl permissaodao, UsuarioDAOImpl usuariodao,
			UsuarioSessao sessao, TabelaDAOImpl tabeladao, Validacao validacao) {	
		this.result = result;
		this.permissaodao = permissaodao;
		this.usuariodao = usuariodao;
		this.sessao = sessao;
		this.tabeladao = tabeladao;
		this.validacao = validacao;
	}
	
	public PermissaoController() {
		this(null,null,null,null,null,null);
	}
	
	@Path("/permissao/cadastro")
	public void cadastro(){		
		if (!sessao.isLogged()) {
			result.include("ativar","login");
		}else{
			validacao.valida(0, "permissao").onErrorForwardTo(AdministrativoController.class).error();			
		}
	}
	
	@Post("/permissao/salvar")
	public void salvar(int idusuario, int[] tabela, int[] select, int[] insert, int[] update, int[] delete) {

		Usuario usuario = usuariodao.getById(Usuario.class, idusuario);

		if (tabela != null && tabela.length > 0) {

			int contadorselect = 0;
			int contadorinsert = 0;
			int contadorupdate = 0;
			int contadordelete = 0;

			int resultado = permissaodao.removePermissao(idusuario);

			for (int i = 0; i < tabela.length; i++) {

				int selecionar = 0;
				int inserir = 0;
				int atualizar = 0;
				int deletar = 0;

				if (select != null && select.length > 0)
					if (contadorselect < select.length) {
						if (tabela[i] == select[contadorselect]){
							selecionar = 1;
							contadorselect++;
						}	
					}

				if (insert != null && insert.length > 0)
					if (contadorinsert < insert.length) {
						if (tabela[i] == insert[contadorinsert]){
							inserir = 1;
							contadorinsert++;
						}	
					}

				if (update != null && update.length > 0)
					if (contadorupdate < update.length) {
						if (tabela[i] == update[contadorupdate])
						{	
							atualizar = 1;
							contadorupdate++;
						}	
					}

				if (delete != null && delete.length > 0)
					if (contadordelete < delete.length) {						
						if (tabela[i] == delete[contadordelete])
						{
							deletar = 1;
							contadordelete++;
						}	
						
					}

				Tabela tabelas = tabeladao.getById(Tabela.class, tabela[i]);
				Permissao permissao = new Permissao();

				permissao.setTabela(tabelas);
				permissao.setSelectpermissao(selecionar);
				permissao.setInsertpermissao(inserir);
				permissao.setUpdatepermissao(atualizar);
				permissao.setDeletepermissao(deletar);
				permissao.setUsuario(usuario);

				permissaodao.save(permissao);

			}

		}else{
			int resultado = permissaodao.removePermissao(idusuario);
		}


		result.nothing();
	}
	
	@Path("/permissao/listaTabela")
	public void listaTabela(int codigo) {
		Usuario usuario = usuariodao.getById(Usuario.class, codigo);
		
		List<Map<String, Object>> dados = new ArrayList<>();
		List<Object[]> objects = permissaodao.pesquisaPermissaoPorUsuario(usuario.getTipousuario(), usuario.getIdusuario());
		
		objects
		.stream()
		.filter(o -> o != null)
		.forEach(o -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idtabela", o[0]);
			resultado.put("desctabela", o[1]);
			resultado.put("ordtabela", o[2]);
			resultado.put("select", o[3]);
			resultado.put("insert", o[4]);
			resultado.put("update", o[5]);
			resultado.put("delete", o[6]);
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();
		
	}
	
}
