package impl.negocio;

import java.util.Date;

import core.IStrategy;
import dominio.EntidadeDominio;
import dominio.Setor;

public class ComplementarDtCadastro implements IStrategy {

	public String processar(Setor setor) {		
		
		
		if(setor !=null){
			Date data = new Date();	
			setor.setDtCadastro(data);
		}else{
			return "Entidade: "+ setor.getClass().getCanonicalName()+" nula!";
		}
		return null;
	}

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
