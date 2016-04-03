package bean;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;

import dao.SetorDAO;
import dominio.Setor;

@ManagedBean
public class SetorBEAN {
	Setor setor = new Setor();
	 
	List setores = new ArrayList(); 
 
	//construtor
	public SetorBEAN(){
		setores = new SetorDAO().listar();
		setor = new Setor();
	}
 
	//Métodos dos botões 
	public void cadastrar(ActionEvent actionEvent){
		new SetorDAO().salvar(setor);
		setores = new SetorDAO().listar();
		setor = new Setor();
	}
 
	public void alterar(ActionEvent actionEvent){
		new SetorDAO().alterar(setor);
		setores = new SetorDAO().listar();
		setor = new Setor();
	}
	public void excluir(ActionEvent actionEvent){
		new SetorDAO().excluir(setor);
		setores = new SetorDAO().listar();
		setor = new Setor();
	}
 
	//getters and setters
	public Setor getPessoa() {
		return setor;
	}
 
	public void setPessoa(Setor pessoa) {
		this.setor = pessoa;
	}
 
	public List getPessoas() {
		return setores;
	}
 
	public void setPessoas(List pessoas) {
		this.setores = pessoas;
	}
}
