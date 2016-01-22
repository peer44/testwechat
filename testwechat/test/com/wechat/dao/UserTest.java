package com.wechat.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wechat.entity.po.User;

public class UserTest {
	StandardServiceRegistry registry =  null;
	SessionFactory sessionFactory = null;
	Session session = null;
	Transaction transaction = null;
	
	@Before
	public void init() {
		registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		session = sessionFactory.openSession();
		//开始事务
		transaction = session.getTransaction();
		transaction.begin();
		
	}
	
	@After 
	public void destroy(){
		transaction.commit();
		session.close();
		sessionFactory.close();
		StandardServiceRegistryBuilder.destroy( registry );
	}

	@Test
	public void testSaveUser() {
		User user = new User();
		user.setUsername("刘德华");
		user.setPassword("andy");
		session.save(user);
		System.out.println("11");
	}

}
