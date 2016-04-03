package testes;

import dao.SetorDAO;
import dominio.Setor;

public class teste {

	public static void main(String[] args) {
		SetorDAO dao = new SetorDAO();
		Setor s = new Setor();
		
		s.setNome("teste");
		s.setRamal(3454);
		s.setFlgAtivo(true);
		
		dao.Salvar(s);

	}

}
