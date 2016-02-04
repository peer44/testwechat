package com.wechat.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wechat.entity.po.Person;
import com.wechat.entity.po.User;

public class UserTest {
	StandardServiceRegistry registry =  null;
	SessionFactory sessionFactory = null;
	Session session = null;
	Transaction transaction = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
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
		user.setUsername("张学友");
		user.setPassword("jacky");
		user.setRegistDate(sdf.format(new Date()));
		File file = new File("D:"+File.separator+"ubuntu.png");
		String fileName = file.getName();
		String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
	    System.out.println(prefix);
		InputStream input = null;
		try {
			input = new FileInputStream(file);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Blob image = null;
		try {
			image = Hibernate.getLobCreator(session).createBlob(input,input.available());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setUserPic(image);
		
		session.save(user);
	}
	
	@Test
	public void testSavePerson() {
		Person p = new Person();
		p.setName("张学友");
		p.setAge(52);
		List<String> schools = new ArrayList<String>();
		schools.add("西安文理学院");
		schools.add("陕西师范大学");
		p.setSchools(schools);
		session.save(p);
		session.getTransaction().commit();
		//session.save(user);
	}
	@Test
	public void testLoadPerson() {
		Person p = session.load(Person.class, "8a89b2fe5277f748015277f7575b0001");
		System.out.println(p.getSchools().toString());
		System.out.println(p.getAge());
		//session.save(user);
	}

}
