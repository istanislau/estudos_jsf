package impl.negocio;

import core.IStrategy;
import dominio.EntidadeDominio;
import dominio.Setor;

public class DadosObrigatoriosSetor implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Setor){
			Setor setor = (Setor)entidade;
			
			String nome = setor.getNome();
			Integer ramal = setor.getRamal();
			
			if(nome == null || ramal == null){
				return "Nome e nacionalidade s�o de preenchimento obrigat�rio!";
			}
			
			if(nome.trim().equals("") || ramal.equals("")){
				return "Nome e nacionalidade s�o de preenchimento obrigat�rio!";
			}
			
		}else{
			return "Deve ser registrado um autor!";
		}
		return null;
	}
}
