package wasdev.sample.store;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import javax.persistence.Query;

import wasdev.sample.Visitor;
import wasdev.sample.model.Visit;


public class JPAVisitorStore implements VisitorStore {

	private EntityManagerFactory emf;

	 

    private EntityManager getEntityManager() {

        if (emf == null) {

			emf = Persistence.createEntityManagerFactory("GetStartedJava");

        }

        return emf.createEntityManager();

    }
    
    private EntityManager em = getEntityManager();

	@Override
	public Collection<Visitor> getAll() {
		// TODO Auto-generated method stub
		List<Visitor> visitors = new ArrayList<Visitor>();
	    Query query =  em.createNamedQuery("Visit.findAll");
		List <Visit> visits = query.getResultList();
		for (Visit visit : visits) {
			Visitor visitor = new Visitor();
			visitor.setName(visit.getFirstname());
			visitor.setId(visit.getVisitid());
			visitors.add(visitor);
		}
		return visitors;
	}


	@Override
	public Visitor persist(Visitor vi) {
		// TODO Auto-generated method stub
		Visitor visitor = new Visitor();
		visitor.setName(vi.getName());
		em.getTransaction().begin();
		Visit visit = new Visit();
		visit.setFirstname(vi.getName());
		visit.setVisited(new Timestamp(System.currentTimeMillis()));
		em.persist(visit);
		em.getTransaction().commit();
		visitor.setId(visit.getVisitid());
		return visitor;
	}


	@Override
	public int count() throws Exception {
		// TODO 
		return em.createNamedQuery("Visit.findAll").getResultList().size();
	}

}
