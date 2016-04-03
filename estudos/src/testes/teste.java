package testes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.FuncionarioDAO;
import dao.MaquinaDAO;
import dao.SetorDAO;
import dominio.EntidadeDominio;
import dominio.Funcionario;
import dominio.Maquina;
import dominio.Setor;

public class teste {

	public static void main(String[] args) {
		SetorDAO dao = new SetorDAO();
		Setor s = new Setor();
		Funcionario f = new Funcionario();
		FuncionarioDAO fdao = new FuncionarioDAO();
		
		/*s.setNome("diretoria");
		s.setRamal(3357);
		s.setFlgAtivo(true);
		
		dao.Salvar(s);
		
		f.setNome("fabio");
		f.setSetor(s);
		
		fdao.Salvar(f);
		
		m.setPatrimonio("sadsadwe324");
		m.setFlgAtivo(true);
		m.setSetor(dao.r);
		mdao.Salvar(m);
		
		s.setNome("ti");
		*/
		
		
		f.setNome("Teste");
		f.setSetor(dao.getSetor("ti"));
		
		fdao.Salvar(f);
		
		
		/*b = dao.getSetor(s.getNome());
		System.out.println("ID: " + b.getId());
		System.out.println("Ramal: " + b.getRamal());
		System.out.println("Ativo: " + b.getFlgAtivo());*/
		
		/*List<Funcionario> f = fdao.Listar();
		
		for (Funcionario funcionario : f) {
			System.out.println(funcionario.getId());
			System.out.println(funcionario.getNome());
		}*/
		
		/*List<Setor> teste = dao.Listar();
		
	
		for (Setor setor : teste) {
			System.out.println("ID: " + setor.getId());
			System.out.println("Ramal: " + setor.getRamal());
			System.out.println("Ativo: " + setor.getFlgAtivo());
		}*/
	}
}
