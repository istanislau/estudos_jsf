package impl.negocio;

import core.IStrategy;
import dominio.Autor;
import dominio.EntidadeDominio;

public class DadosObrigatoriosAutor implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Autor){
			Autor autor = (Autor)entidade;
			
			String nome = autor.getNome();
			String nacionalidade = autor.getNacionalidade();
			
			if(nome == null || nacionalidade == null){
				return "Nome e nacionalidade são de preenchimento obrigatório!";
			}
			
			if(nome.trim().equals("") || nacionalidade.trim().equals("")){
				return "Nome e nacionalidade são de preenchimento obrigatório!";
			}
			
		}else{
			return "Deve ser registrado um autor!";
		}
		return null;
	}
}
