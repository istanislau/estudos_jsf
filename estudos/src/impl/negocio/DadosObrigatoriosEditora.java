package impl.negocio;

import core.IStrategy;
import dominio.Editora;
import dominio.EntidadeDominio;

public class DadosObrigatoriosEditora implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Editora){
			Editora editora = (Editora)entidade;
			
			String nome = editora.getNome();
			String cnpj = editora.getCnpj();
			
			if(nome == null || cnpj == null){
				return "Nome e cnpj s�o de preenchimento obrigat�rio!";
			}
			
			if(nome.trim().equals("") || cnpj.trim().equals("")){
				return "Nome e cnpj s�o de preenchimento obrigat�rio!";
			}
			
		}else{
			return "Deve ser registrado uma editora!";
		}
		return null;
	}
}
