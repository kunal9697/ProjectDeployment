package com.TheStaffSphere.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.TheStaffSphere.entity.Country;
import com.TheStaffSphere.entity.Employee;

@Repository
public class MainDAO {
	
	@Autowired
	SessionFactory factory;

	public String addCountry(Country c) {
		Session session=null;
		Transaction tx=null;
		String msg=null;
		try {	
		session=factory.openSession();
		tx=session.beginTransaction();
		session.persist(c);		
		tx.commit();
		msg="Coutry Addedd Successfully..";
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}		
		return msg;
	}

	public String updateCountry(Country c, int id) {
		
		Session session=null;
		Transaction tx=null;
		String msg=null;
		Country country=null;
		try {	
		session=factory.openSession();
		tx=session.beginTransaction();
		country=session.get(Country.class, id);
		country.setCname(c.getCname());
		session.merge(country);		
		
		tx.commit();
		msg="Coutry Updated Successfully..";
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}		
		return msg;
	}

	public String deleteCountry(int id) {
		
		Session session=null;
		Transaction tx=null;
		String msg=null;
		Country country=null;
		try {	
		session=factory.openSession();
		tx=session.beginTransaction();
		country=session.get(Country.class, id);
		
		session.remove(country);		
		tx.commit();
		msg="Coutry Deleted Successfully..";
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}		
		return msg;
	}

	public List<Country> getAllCountry() {
		Session session=null;
		Transaction tx=null;
		List<Country> list=null;
		String hqlQuery="from Country";
		try {	
			session=factory.openSession();
			tx=session.beginTransaction();
			Query<Country> query=session.createQuery(hqlQuery,Country.class);
			list=query.list();
			tx.commit();
			
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}		
		
		return list;
	}

	public Country getParticularCountryById(int id) {
		Session session=null;
		Transaction tx=null;
		
		Country country=null;
		try {	
		session=factory.openSession();
		tx=session.beginTransaction();
		country=session.get(Country.class, id);
		tx.commit();
		
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}		
		return country;
	}

	public String addEmployee(Employee emp) {
	    Session session = null;
	    Transaction tx = null;
	    String msg = null;
	    try {
	        session = factory.openSession();
	        tx = session.beginTransaction();
	        session.save(emp.getCountry());
	        session.save(emp);       
	        tx.commit();
	        msg = "Employee Added Successfully.";
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	            System.out.println("Transaction failed: " + e.getMessage()); // Log error
	        }
	        e.printStackTrace();
	        msg = "Error adding employee.";  // Set failure message here
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	    return msg;
	}
	public Employee login(Employee emp) {
		Session session=null;
		Transaction tx=null;
		Employee employee=null;
		String hqlQuery="from Employee where emailid=:emailid and mobileno=:mobileno";
		try {	
			session=factory.openSession();
			tx=session.beginTransaction();
			
			Query<Employee> query= session.createQuery(hqlQuery,Employee.class);
			query.setParameter("emailid", emp.getEmailid());
			query.setParameter("mobileno", emp.getMobileno());
			employee= query.uniqueResult();
			tx.commit();
			
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}		
		return employee;
	}

	public List<Employee> salaryRange(double startSal, double endSal) {
		Session session=null;
		Transaction tx=null;
		List<Employee> list=null;
		String hqlQuery="from Employee where salary between :startSal and :endSal";
		try {	
			session=factory.openSession();
			tx=session.beginTransaction();
			Query<Employee> query=session.createQuery(hqlQuery,Employee.class);
			query.setParameter("startSal", startSal);
			query.setParameter("endSal", endSal);
			list=query.list();
			tx.commit();
			
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}		
		
		return list;
	}

}