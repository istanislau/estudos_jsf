package dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import dominio.EntidadeDominio;
import dominio.Setor;
import util.HibernateUtil;

public class SetorDAO extends AbstractDAO{
	private Session sessao;
	
	public List listar(){
		sessao = HibernateUtil.getSessionFactory().openSession();

		try{
			Criteria cri = sessao.createCriteria(Setor.class);
			return cri.list();
		}finally{
			sessao.close();
		}
	}
}
