package com.crud.hibernate.CrudOperation;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.crud.hibernate.CrudOperation.entity.Customer;

/**
 * Sivakumar!
 *
 */
public class App 
{
	static Configuration config=null;
	static SessionFactory sf=null;
	static Session ses=null;
    static Transaction tx=null;
    
    public static void main( String[] args )
    {
        config = new Configuration();
        config.configure("hibernate.cfg.xml");
        sf = config.buildSessionFactory();
        
        String choice="y";
        while(choice.equals("y") || choice.equals("Y"))
        {
        	System.out.println("-------------------------");
        	System.out.println("1. Add Customer");
        	System.out.println("2. Get Customer Details");
        	System.out.println("3. Get All Custoer Details");
        	System.out.println("4. Update Customer Details");
        	System.out.println("5. Delete Customer Details");
        	System.out.println("--------------------------");
        	Scanner din = new Scanner(System.in);
        	System.out.println("Select your option");
        	int option = din.nextInt();
        	switch(option)
        	{
        	case 1:AddCustomer();
        		break;
        	case 2: getCustomerData();
        		break;
        	case 3: getAllCustomerDetails();
        		break;
        	case 4: updateCustomerDetails();
        		break;
        		
        	case 5: deleteCustomer();
        		break;
        	default : System.out.println("Selected option is Invalid");
        	}
        }
        
    }
    static void AddCustomer()
    {
    	ses = sf.openSession();
    	 tx = ses.beginTransaction();
    	Customer cus = new Customer();
    	//cus.setCusid(100);
    	Scanner din = new Scanner(System.in);
    	System.out.println("Enter Customer Name");
    	String name = din.next();
    	System.out.println("Enter Customer City");
    	String city = din.next();
    	System.out.println("Enter Custom State");
    	String state = din.next();
    	cus.setCusname(name);
    	cus.setCity(city);
    	cus.setState(state);
    	ses.save(cus);
    	tx.commit();
    	System.out.println("Customer details added successfully");
    	ses.close();
    }
    static void getCustomerData()
    {

    	ses = sf.openSession();
    	tx = ses.beginTransaction();
    	Scanner din = new Scanner(System.in);
    	System.out.println("Enter Customer ID");
    	int cusid = din.nextInt();
    	Customer c = ses.get(Customer.class, cusid);
    	if(c!=null)
    	{
    		System.out.println(c.getCusid());
    		System.out.println(c.getCusname());
    		System.out.println(c.getCity());
    		System.out.println(c.getState());
    		System.out.println("Customer Details");
    	}
    	else
    	{
    		System.out.println("Customer Not Found");
    	}
    	ses.close();
    }
    static void getAllCustomerDetails()
    {
    	ses = sf.openSession();
    	//tx = ses.beginTransaction();
    	List<Customer> list = ses.createQuery("from Customer",Customer.class).list();
    	for(Customer c:list)
    	{
    		System.out.println(c.getCusid()+"\t"+c.getCusname()+"\t"+c.getCity()+"\t"+c.getState());
    	}
    	ses.close();
    }
    
    static void updateCustomerDetails()
    {
    	ses = sf.openSession();
    	tx = ses.beginTransaction();
    	Scanner din = new Scanner(System.in);
    	System.out.println("Enter Customr ID");
    	int cusid = din.nextInt();
    	//String cname = din.next();
    	Customer c = ses.get(Customer.class, cusid);
    	if(c!=null)
    	{
    		System.out.println("Press 1. For Customer Name Change");
        	System.out.println("Press 2. For City Name Change");
        	System.out.println("Press 3. For State Name Change");
        	int option = din.nextInt();
        	switch(option)
        	{
        	case 1: System.out.println("Enter Customer Name to Change");
        			String cname = din.next();
        			c.setCusname(cname);
        			ses.save(c);
        			tx.commit();
        			System.out.println("Customer Name Updated Success");
        		break;
        	case 2:System.out.println("Enter Customer City to Change");
			String city = din.next();
			c.setCusname(city);
			ses.save(c);
			tx.commit();
			System.out.println("Customer City Updated Success");
        		break;
        	case 3:System.out.println("Enter Customer State to Change");
			String state = din.next();
			c.setCusname(state);
			ses.save(c);
			tx.commit();
			System.out.println("Customer State Updated Success");
        		break;
        	default : System.out.println("Invalid option for update");
        	}
        	ses.close();
    	}
    	else
    	{
    		System.out.println("Customer ID Not Found");
    	}
    	
    }
    static void deleteCustomer()
    {
    	ses = sf.openSession();
    	tx = ses.beginTransaction();
    	Scanner din = new Scanner(System.in);
    	System.out.println("Enter Customr ID");
    	int cusid = din.nextInt();
    	//String cname = din.next();
    	Customer c = ses.get(Customer.class, cusid);
    	if(c!=null)
    	{
    		ses.delete(c);
    		tx.commit();
    		System.out.println("Customer Record deleted Success");
    	}
    	else
    	{
    		System.out.println("Customer Record not deleted");
    	}
    	ses.close();
    }
}
