package tn.insat.client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import tn.insat.entities.Bidder;
import tn.insat.entities.Seller;

public class Program 
{
	public static void main( String[] args )
    {
		//Getting Session Factory
		Configuration configuration =  new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                 configuration.getProperties()).build();            

    	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    	
    	//Getting Session
		Session session = sessionFactory.openSession();
		
		//Beginning Transaction
		Transaction tx = session.beginTransaction();

		Bidder b = new Bidder();   
		b.setUserId("B100");
		b.setFirstName("Bidder FirstName");
		b.setLastName("Bidder LastName");
		b.setBidFrequency(12.5);
		
		session.save(b);
		Seller s = new Seller();   // Les initialisations autres que l'ID ne sont pas faites
        // Car le le focus est porté sur la structure de la BDD
         s.setUserId("S100");
         s.setFirstName("Seller FirstName");
 		s.setLastName("Seller LastName");
 		s.setCreditWorth(55.5);
 		s.setSellerCountry("TUNISIA");
		                            
		session.save(s);
		
		//Committing and closing session 
		tx.commit();
		session.close();
		
		// Fix the program termination by closing the sessionFactory
		sessionFactory.close();
		System.out.println("Fin de l'initialisation de la BDD ....");
    }
}
