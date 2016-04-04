package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import dominio.Funcionario;
import util.HibernateUtil;
import dao.AbstractDAO;

public class FuncionarioDAO extends AbstractDAO{

	private Session sessao;
	
	public List  listar(){
		sessao = HibernateUtil.getSessionFactory().openSession();

		try{
			Criteria cri = sessao.createCriteria(Funcionario.class);
			return cri.list();
		}finally{
			sessao.close();
		}
	}
}
