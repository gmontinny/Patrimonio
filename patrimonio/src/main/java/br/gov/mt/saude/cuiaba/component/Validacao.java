package br.gov.mt.saude.cuiaba.component;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.gov.mt.saude.cuiaba.dao.impl.PermissaoDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.TabelaDAOImpl;
import br.gov.mt.saude.cuiaba.model.Tabela;


@RequestScoped
public class Validacao {
	private final Validator validator;
	private final PermissaoDAOImpl permissaodao;
	private final TabelaDAOImpl tabeladao;
	private final UsuarioSessao sessao;
	
	@Inject
	public Validacao(Validator validator, PermissaoDAOImpl permissaodao, TabelaDAOImpl tabeladao, UsuarioSessao sessao) {
		this.validator = validator;
		this.permissaodao = permissaodao;
		this.tabeladao = tabeladao;
		this.sessao = sessao;
	}
	
	public Validacao() {
		this(null,null,null,null);
	}
	
	public Validator valida(int opcao, String tabelas) {
		
		Tabela tabela = tabeladao.pesquisaTabelaPorNome(tabelas);	
		
		if(opcao == 0){
			validator.ensure(permissaodao.validaSelect(sessao.getUsuario(), tabela),
					new SimpleMessage("validaSelect", "Error - Você não tem permissão para visualizar !"));			
		}else if (opcao == 1) {
			validator.ensure(permissaodao.validaInsert(sessao.getUsuario(), tabela),
					new SimpleMessage("validaInsert", "Error - Você não tem permissão de Insersão !"));
		} else if (opcao == 2) {
			validator.ensure(permissaodao.validaUpdate(sessao.getUsuario(), tabela),
					new SimpleMessage("validaUpdate", "Error - Você não tem permissão de Atualização !"));

		} else if (opcao == 3) {
			validator.ensure(permissaodao.validaDelete(sessao.getUsuario(), tabela),
					new SimpleMessage("validaDelete", "Error - Você não tem permissão de Deletar !"));

		}

		return validator;
	}

}
