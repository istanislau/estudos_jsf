package testes;


import java.util.List;

import dao.SetorDAO;
import dominio.Setor;
import impl.negocio.ComplementarDtCadastro;

public class teste {

	public static void main(String[] args) {
		SetorDAO dao = new SetorDAO();
		Setor s = new Setor();
		ComplementarDtCadastro estra = new ComplementarDtCadastro();
		
		s.setNome("teste");
		s.setFlgAtivo(true);
		s.setRamal(6567);
		estra.processar(s);
		
		dao.salvar(s);
		
		s.setNome("teste2");
		s.setFlgAtivo(true);
		s.setRamal(4327);
		estra.processar(s);
		
		dao.salvar(s);
		
		s.setNome("teste3");
		s.setFlgAtivo(true);
		s.setRamal(2123);
		estra.processar(s);
		
		dao.salvar(s);
		
		s.setNome("teste4");
		s.setFlgAtivo(true);
		s.setRamal(1432);
		estra.processar(s);
		
		dao.salvar(s);
		

		List<Setor> setores = dao.listar();
		
		for (Setor setor : setores) {
			System.out.println("Id: " + setor.getId());
			System.out.println("Setor: " + setor.getNome());
			System.out.println("Ramal: " + setor.getRamal());
		}
	}
}
