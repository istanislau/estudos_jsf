package bean;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import dao.SetorDAO;
import dominio.Setor;

@ManagedBean(name = "setorBEAN")
public class SetorBEAN {
	Setor setor;
	 
	List <Setor> setores = new ArrayList(); 
 
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
	public Setor getSetor() {
		return setor;
	}
 
	public void setSetor(Setor pessoa) {
		this.setor = pessoa;
	}
 
	public List getSetores() {
		return setores;
	}
 
	public void setSetores(List pessoas) {
		this.setores = pessoas;
	}
}
