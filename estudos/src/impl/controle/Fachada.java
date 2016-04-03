package impl.controle;

import impl.dao.AutorDAO;
import impl.dao.EditoraDAO;
import impl.dao.EnderecoDAO;
import impl.dao.TelefoneDAO;
import impl.negocio.ComplementarDtCadastro;
import impl.negocio.DadosObrigatoriosAutor;
import impl.negocio.DadosObrigatoriosEditora;
import impl.negocio.ValidarCNPJ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dominio.Autor;
import dominio.Editora;
import dominio.Endereco;
import core.IDAO;
import core.IFachada;
import core.IStrategy;
import aplicacao.Resultado;

import dominio.EntidadeDominio;

public class Fachada implements IFachada {

	/** 
	 * Mapa de DAOS, ser� indexado pelo nome da entidade 
	 * O valor � uma inst�ncia do DAO para uma dada entidade; 
	 */
	private Map<String, IDAO> daos;
	
	/**
	 * Mapa para conter as regras de neg�cio de todas opera��es por entidade;
	 * O valor � um mapa que de regras de neg�cio indexado pela opera��o
	 */
	private Map<String, Map<String, List<IStrategy>>> rns;
	
	private Resultado resultado;
	
	
	public Fachada(){
		// Inst�nciando o Map de DAOS
		daos = new HashMap<String, IDAO>();
		
		// Int�nciando o Map de Regras de Neg�cio
		rns = new HashMap<String, Map<String, List<IStrategy>>>();
		
		// Criando inst�ncias dos DAOs a serem utilizados
		AutorDAO autorDAO = new AutorDAO();
		EditoraDAO editoraDAO = new EditoraDAO();
		TelefoneDAO telefoneDAO = new TelefoneDAO();
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		
		 
		// Adicionando cada dao no MAP indexando pelo nome da classe
		daos.put(Autor.class.getName(), autorDAO);
		daos.put(Autor.class.getName(), editoraDAO);
		daos.put(Autor.class.getName(), telefoneDAO);
		daos.put(Autor.class.getName(), enderecoDAO);
		
		
		// Criando inst�ncias de regras de neg�cio a serem utilizados		
		DadosObrigatoriosAutor rnDadosObrigatoriosAutor = new DadosObrigatoriosAutor();
		DadosObrigatoriosEditora rnDadosObrigatoriosEditora = new DadosObrigatoriosEditora();
		ValidarCNPJ rnValidarCNPJ = new ValidarCNPJ();
		ComplementarDtCadastro rnComplementarDtCadastro = new ComplementarDtCadastro();
		
		//--------------------------------------------- Regras de negocio Autor ---------------------------------------------
		// Cria lista de regras de negocios especificas por opera��o
		List<IStrategy> rnsSalvarAutor = new ArrayList<IStrategy>();
		List<IStrategy> rnsAlterarAutor = new ArrayList<IStrategy>();
		List<IStrategy> rnsConsultarAutor = new ArrayList<IStrategy>();
		List<IStrategy> rnsExcluirAutor = new ArrayList<IStrategy>();
		
		/* Lista com as regras de negocio de autor
		 * Opera��o: Salvar 
		 */		
		rnsSalvarAutor.add(rnDadosObrigatoriosAutor);
		rnsSalvarAutor.add(rnComplementarDtCadastro);
		
		/* Lista com as regras de negocio de autor
		 * Opera��o: Alterar 
		 */	
		
		/* Lista com as regras de negocio de autor
		 * Opera��o: Consultar 
		 */	
		
		/* Lista com as regras de negocio de autor
		 * Opera��o: Escluir 
		 */	
		
		// Cria mapa com lista de regras de negocios especificas por opera��o de autor
		Map<String, List<IStrategy>> rnsAutor = new HashMap<String, List<IStrategy>>();
		rnsAutor.put("SALVAR", rnsSalvarAutor);
		rnsAutor.put("ALTERAR", rnsAlterarAutor);
		rnsAutor.put("CONSULTAR", rnsConsultarAutor);
		rnsAutor.put("EXCLUIR", rnsExcluirAutor);
		//--------------------------------------------- FIM Regras de negocio Autor ---------------------------------------------
		
		
		//--------------------------------------------- Regras de negocio Editora ---------------------------------------------
		// Cria lista de regras de negocios especificas por opera��o
		List<IStrategy> rnsSalvarEditora = new ArrayList<IStrategy>();
		List<IStrategy> rnsAlterarEditora = new ArrayList<IStrategy>();
		List<IStrategy> rnsConsultarEditora = new ArrayList<IStrategy>();
		List<IStrategy> rnsExcluirEditora = new ArrayList<IStrategy>();
		
		/* Lista com as regras de negocio de editora
		 * Opera��o: Salvar 
		 */
		rnsSalvarEditora.add(rnDadosObrigatoriosEditora);
		rnsSalvarEditora.add(rnValidarCNPJ);
		rnsSalvarEditora.add(rnComplementarDtCadastro);
		
		/* Lista com as regras de negocio de editora
		 * Opera��o: Alterar 
		 */
		
		/* Lista com as regras de negocio de editora
		 * Opera��o: Consultar 
		 */
		
		/* Lista com as regras de negocio de editora
		 * Opera��o: Excluir 
		 */
		
		// Cria mapa com lista de regras de negocios especificas por opera��o de autor
		Map<String, List<IStrategy>> rnsEditora = new HashMap<String, List<IStrategy>>();
		rnsEditora.put("SALVAR", rnsSalvarEditora);
		rnsEditora.put("ALTERAR", rnsAlterarEditora);
		rnsEditora.put("CONSULTAR", rnsConsultarEditora);
		rnsEditora.put("EXCLUIR", rnsExcluirEditora);
		//--------------------------------------------- FIM Regras de negocio Editora ---------------------------------------------
		
		
		//--------------------------------------------- Indexa��o no mapa geral ---------------------------------------------
		rns.put(Autor.class.getName(), rnsAutor);
		rns.put(Editora.class.getName(), rnsEditora);
		
	}
	
	
	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "SALVAR");
		
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.salvar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("N�o foi poss�vel realizar o registro!");				
			}
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
			try {
				dao.alterar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("N�o foi poss�vel realizar o registro!");				
			}
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
			try {
				dao.excluir(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("N�o foi poss�vel realizar o registro!");
				
			}
		}else{
			resultado.setMsg(msg);
		}
		return resultado;
	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "EXCLUIR");
		
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				resultado.setEntidades(dao.consultar(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("N�o foi poss�vel realizar a consulta!");
			}
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
