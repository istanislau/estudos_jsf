package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
 
public class HibernateUtil {
 
    public static final SessionFactory session = buildSession();
 
    private static SessionFactory buildSession() {
 
    try{
    	
    	AnnotationConfiguration cfg = new AnnotationConfiguration();
         cfg.configure("hibernate.cfg.xml");
 
        return cfg.buildSessionFactory();
 
       }catch(Throwable b){
 
            System.out.println("Não deu \n" + b);
            throw new ExceptionInInitializerError();
       }
   }
 
   public static SessionFactory getSessionFactory(){
       return session;
   }
}