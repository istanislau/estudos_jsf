package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;

import dominio.Maquina;
import util.HibernateUtil;

public class MaquinaDAO{
	private Session sessao; 
	 
	public void salvar(Maquina obj){
		sessao = HibernateUtil.getSessionFactory().openSession();

		try{
			sessao.beginTransaction();
			sessao.save(obj);
			sessao.getTransaction().commit();

		}finally{
			sessao.close();

		}
	}

	public void alterar(Maquina obj){
		sessao = HibernateUtil.getSessionFactory().openSession();

		try{
			sessao = HibernateUtil.getSessionFactory().openSession();
			sessao.beginTransaction();
			sessao.saveOrUpdate(obj);
			sessao.getTransaction().commit();
		}finally{
			sessao.close();

		}
	}

	public void excluir(Maquina obj){
		sessao = HibernateUtil.getSessionFactory().openSession();

		try{
			sessao = HibernateUtil.getSessionFactory().openSession();
			sessao.beginTransaction();
			sessao.delete(obj);
			sessao.getTransaction().commit();

		}finally{
			sessao.close();

		}
	}
	
	public List  listar(){
		sessao = HibernateUtil.getSessionFactory().openSession();

		try{
			Criteria cri = sessao.createCriteria(Maquina.class);
			return cri.list();
		}finally{
			sessao.close();
		}
	}
}
