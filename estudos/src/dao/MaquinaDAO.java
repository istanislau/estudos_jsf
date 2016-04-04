package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import dominio.Maquina;
import util.HibernateUtil;

public class MaquinaDAO extends AbstractDAO{
	private Session sessao; 
	
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
