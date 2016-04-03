package impl.negocio;

import dominio.Cliente;
import core.IStrategy;
import dominio.EntidadeDominio;

public class ValidadorCPF implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		
		if(entidade instanceof Cliente){
			Cliente c = (Cliente)entidade;
			
			if(c.getCpf().length() < 9){
				return "CPF deve conter 14 digitos!";
			}			
		}else{
			return "CPF não pode ser válidado, pois entidade não é um cliente!";
		}
		return null;
	}

}
