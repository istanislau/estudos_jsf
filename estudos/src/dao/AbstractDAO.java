package dao;

import org.hibernate.Session;

import dominio.EntidadeDominio;
import util.HibernateUtil;

public abstract class AbstractDAO implements IDAO{

	private Session sessao; 
	 
	public void salvar(EntidadeDominio obj){
		sessao = HibernateUtil.getSessionFactory().openSession();

		try{
			sessao.beginTransaction();
			sessao.save(obj);
			sessao.getTransaction().commit();

		}finally{
			sessao.close();

		}
	}

	public void alterar(EntidadeDominio obj){
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

	public void excluir(EntidadeDominio obj){
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
}
