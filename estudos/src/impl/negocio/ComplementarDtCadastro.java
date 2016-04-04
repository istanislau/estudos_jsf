package impl.negocio;

import java.util.Date;

import core.IStrategy;
import dominio.EntidadeDominio;

public class ComplementarDtCadastro implements IStrategy {

	@SuppressWarnings("null")
	@Override
	public String processar(EntidadeDominio entidade) {				
		
		if(entidade !=null){
			Date data = new Date();		
			entidade.setDtCadastro(data);
		}else{
			return "Entidade: "+ entidade.getClass().getCanonicalName()+" nula!";
		}
		return null;
	}
}
