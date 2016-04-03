import javax.imageio.spi.ServiceRegistry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateUtil {
	private static SessionFactory factory;
	static {
		Configuration cfg = new Configuration(); // Nao preciso chamar .configure(), porque nao uso hibernate.cfg.xml, uso hibernate.properties
		cfg.addAnnotatedClass(LoginTO.class); // Aqui coloco as classes que tem as anotacoes
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
		factory = cfg.buildSessionFactory(serviceRegistry);
	}
	public static Session getSession() {
		return factory.openSession();
	}
}