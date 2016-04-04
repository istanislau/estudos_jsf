package impl.controle;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aplicacao.Resultado;
import core.IFachada;
import core.IStrategy;
import dao.FuncionarioDAO;
import dao.IDAO;
import dao.MaquinaDAO;
import dao.SetorDAO;
import dominio.EntidadeDominio;
import dominio.Funcionario;
import dominio.Maquina;
import dominio.Setor;
import impl.negocio.ComplementarDtCadastro;
import impl.negocio.DadosObrigatoriosSetor;

public class Fachada implements IFachada {

	/** 
	 * Mapa de DAOS, será indexado pelo nome da entidade 
	 * O valor é uma instância do DAO para uma dada entidade; 
	 */
	private Map<String, IDAO> daos;
	
	/**
	 * Mapa para conter as regras de negócio de todas operações por entidade;
	 * O valor é um mapa que de regras de negócio indexado pela operação
	 */
	private Map<String, Map<String, List<IStrategy>>> rns;
	
	private Resultado resultado;
	
	
	public Fachada(){
		// Instânciando o Map de DAOS
		daos = new HashMap<String, IDAO>();
		
		// Intânciando o Map de Regras de Negócio
		rns = new HashMap<String, Map<String, List<IStrategy>>>();
		
		// Criando instâncias dos DAOs a serem utilizados
		SetorDAO setorDAO = new SetorDAO();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		MaquinaDAO maquinaDAO = new MaquinaDAO();
		
		 
		// Adicionando cada dao no MAP indexando pelo nome da classe
		daos.put(Setor.class.getName(), setorDAO);
		daos.put(Funcionario.class.getName(), funcionarioDAO);
		daos.put(Maquina.class.getName(), maquinaDAO);
		
		
		// Criando instâncias de regras de negócio a serem utilizados		
		DadosObrigatoriosSetor rnDadosObrigatoriosSetor = new DadosObrigatoriosSetor();
		ComplementarDtCadastro rnComplementarDtCadastro = new ComplementarDtCadastro();
		
		//--------------------------------------------- Regras de negocio Setor ---------------------------------------------
		// Cria lista de regras de negocios especificas por operação
		List<IStrategy> rnsSalvarSetor = new ArrayList<IStrategy>();
		List<IStrategy> rnsAlterarSetor = new ArrayList<IStrategy>();
		List<IStrategy> rnsListarSetor = new ArrayList<IStrategy>();
		List<IStrategy> rnsExcluirSetor = new ArrayList<IStrategy>();
		
		/* Lista com as regras de negocio de autor
		 * Operação: Salvar 
		 */		
		rnsSalvarSetor.add(rnDadosObrigatoriosSetor);
		rnsSalvarSetor.add(rnComplementarDtCadastro);
		
		/* Lista com as regras de negocio de autor
		 * Operação: Alterar 
		 */	
		rnsAlterarSetor.add(rnDadosObrigatoriosSetor);
		
		/* Lista com as regras de negocio de autor
		 * Operação: Consultar 
		 */	
		
		/* Lista com as regras de negocio de autor
		 * Operação: Escluir 
		 */	
		
		// Cria mapa com lista de regras de negocios especificas por operação de autor
		Map<String, List<IStrategy>> rnsSetor = new HashMap<String, List<IStrategy>>();
		rnsSetor.put("SALVAR", rnsSalvarSetor);
		rnsSetor.put("ALTERAR", rnsAlterarSetor);
		rnsSetor.put("CONSULTAR", rnsListarSetor);
		rnsSetor.put("EXCLUIR", rnsExcluirSetor);
		//--------------------------------------------- FIM Regras de negocio Autor ---------------------------------------------
		
		
			
		
		//--------------------------------------------- Indexação no mapa geral ---------------------------------------------
		rns.put(Setor.class.getName(), rnsSetor);
		
	}
	
	
	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		
		//Reserva o nome da classe para pesquisar no map
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "SALVAR");
		
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			dao.salvar(entidade);
			List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
			entidades.add(entidade);
			resultado.setEntidades(entidades);
		}else{
			resultado.setMsg(msg);						
		}
		
		return resultado;
	}

	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "ALTERAR");
	
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			dao.alterar(entidade);
			List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
			entidades.add(entidade);
			resultado.setEntidades(entidades);
		}else{
			resultado.setMsg(msg);					
		}		
		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "EXCLUIR");
		
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			dao.excluir(entidade);
			List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
			entidades.add(entidade);
			resultado.setEntidades(entidades);
		}else{
			resultado.setMsg(msg);
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "EXCLUIR");
		
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			resultado.setEntidades(dao.listar());
		}else{
			resultado.setMsg(msg);
		}
		return resultado;
	}
	
	@Override
	public Resultado visualizar(EntidadeDominio entidade) {
		resultado = new Resultado();
		resultado.setEntidades(new ArrayList<EntidadeDominio>(1));
		resultado.getEntidades().add(entidade);		
		return resultado;
	}

	
	private String executarRegras(EntidadeDominio entidade, String operacao){
		String nmClasse = entidade.getClass().getName();		
		StringBuilder msg = new StringBuilder();
		
		Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);
		
		
		if(regrasOperacao != null){
			List<IStrategy> regras = regrasOperacao.get(operacao);
			
			if(regras != null){
				for(IStrategy s: regras){			
					String m = s.processar(entidade);			
					
					if(m != null){
						msg.append(m);
						msg.append("\n");
					}			
				}	
			}	
		}
		
		if(msg.length()>0)
			return msg.toString();
		else
			return null;
	}
}
