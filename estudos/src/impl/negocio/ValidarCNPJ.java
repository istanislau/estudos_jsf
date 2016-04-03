package impl.negocio;

import core.IStrategy;
import dominio.Editora;
import dominio.EntidadeDominio;

public class ValidarCNPJ implements IStrategy {

	public String processar(EntidadeDominio entidade) {
		
		if(entidade instanceof Editora){
			Editora c = (Editora)entidade;
			
			if(c.getCnpj().length() < 14){
				return "CNPJ deve conter 14 digitos!";
			}			
		}else{
			return "CNPJ não pode ser válidado, pois entidade não é uma editora!";
		}
		return null;
	}

}
