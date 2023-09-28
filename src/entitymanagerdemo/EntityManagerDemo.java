/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitymanagerdemo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Address;
import model.AddressTable;
import model.Customer;
import model.CustomerTable;

/**
 *
 * @author sarun
 */
public class EntityManagerDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        createData();
//        printAllCustomer();
        printCustomerByCity("Bangkok");
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public static void createData() {
        Customer customer1 = new Customer(1L, "John", "Henry", "jh@mail.com"); 
        Address address1 = new Address(1L, "123/4 Viphavadee Rd.", "Bangkok", "10900", "TH");
        Customer customer2 = new Customer(2L, "Marry", "Jane", "mj@mail.com"); 
        Address address2 = new Address(2L, "123/5 Viphavadee Rd.", "Bangkok", "10900", "TH");
        Customer customer3 = new Customer(3L, "Peter", "Parker", "pp@mail.com"); 
        Address address3 = new Address(3L, "543/21 Fake Rd.", "Nonthaburi", "20900", "TH");
        Customer customer4 = new Customer(4L, "Bruce", "Wayn", "bw@mail.com"); 
        Address address4 = new Address(4L, "678/90 Unreal Rd.", "Pathumthani", "30500", "TH");
        
        address1.setCustomerFk(customer1);
        customer1.setAddressId(address1); 
        address2.setCustomerFk(customer2);
        customer2.setAddressId(address2);
        address3.setCustomerFk(customer3);
        customer3.setAddressId(address3);
        address4.setCustomerFk(customer4);
        customer4.setAddressId(address4);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        try {
            em.persist(address1);
            em.persist(customer1);
            em.persist(address2);
            em.persist(customer2);
            em.persist(address3);
            em.persist(customer3);
            em.persist(address4);
            em.persist(customer4);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public static void printAllCustomer() {
        List<Customer> customers = CustomerTable.findAllCustomer();
        System.out.println("All customers");
        System.out.println("---------------------------");
        for (Customer cust : customers) {
            System.out.println("First Name: " + cust.getFirstname());
            System.out.println("Last Name: " + cust.getLastname());
            System.out.println("Email: " + cust.getEmail());
            System.out.println("Street: " + cust.getAddressId().getStreet());
            System.out.println("City: " + cust.getAddressId().getCity());
            System.out.println("Country: " + cust.getAddressId().getCountry());
            System.out.println("Zip Code: " + cust.getAddressId().getZipcode());
            System.out.println("---------------------------");
        };
    }
    
    public static void printCustomerByCity(String city) {
        List<Address> address = AddressTable.findAddressByCity(city);
        System.out.println("Customers in " + city + " city.");
        System.out.println("---------------------------");
        for (Address addr : address) {
            System.out.println("First Name: " + addr.getCustomerFk().getFirstname());
            System.out.println("Last Name: " + addr.getCustomerFk().getLastname());
            System.out.println("Email: " + addr.getCustomerFk().getEmail());
            System.out.println("Street: " + addr.getStreet());
            System.out.println("City: " + addr.getCity());
            System.out.println("Country: " + addr.getCountry());
            System.out.println("Zip Code: " + addr.getZipcode());
            System.out.println("---------------------------");
        };
    }
}
