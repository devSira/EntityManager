/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Siraphob.B
 */
public class AddressTable {
    public static List<Address> findAddressByCity(String city) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Address.findByCity");
        query.setParameter("city", city);
        List<Address> addressList = (List<Address>) query.getResultList();
        em.close();
        return addressList;
    }
}
