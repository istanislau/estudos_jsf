package testes;


import java.util.List;

import dao.SetorDAO;
import dominio.Setor;

public class teste {

	public static void main(String[] args) {
		SetorDAO dao = new SetorDAO();
		Setor s = new Setor();

		List<Setor> setores = dao.listar();
		
		for (Setor setor : setores) {
			System.out.println("Id: " + setor.getId());
			System.out.println("Setor: " + setor.getNome());
			System.out.println("Ramal: " + setor.getRamal());
		}
	}
}
