package com.dl.rs.webservices;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.dl.rs.utils.PersistItem;
import com.dl.rs.utils.SessionFactoryUtil;

@Path("/ws")
public class RestResource {

	@GET()
	@Path("/add")
	@Produces("text/plain")
	public String addPersistItem( @QueryParam("name") String name, @QueryParam("email") String email, @QueryParam("id") Long id, @QueryParam("address") String address, @QueryParam("phone") String phone ) {
	    
		
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		
		session.getTransaction().begin();
		
		PersistItem tPI = new PersistItem();
        tPI.setAddress(address);
        tPI.setEmail(email);
        tPI.setId(id);
        tPI.setName(name);
        tPI.setPhoneNumber(phone);
        
        Long genId = (Long) session.save(tPI);
        
        session.getTransaction().commit();
		
		return String.valueOf(genId);

	}
	
	@GET()
	@Path("/retrieve")
	@Produces("text/xml")
    public List<PersistItem> getAllItems() {

		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
        Criteria cb = session.createCriteria(PersistItem.class);
        cb.addOrder(Order.asc("id"));
        List<PersistItem> result = (List<PersistItem>) cb.list();
        session.getTransaction().commit();
        
        return result;
        
}
	
}
