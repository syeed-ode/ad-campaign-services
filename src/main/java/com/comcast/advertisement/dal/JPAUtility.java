package com.comcast.advertisement.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Author: syeedode
 * Date: 2/27/18
 */
public class JPAUtility {
    private static final EntityManagerFactory emFactory;

    static {
        // Extended persistence context
        // Entity manager is retrieved from a JNDI lookup
        emFactory = Persistence.createEntityManagerFactory("manager1");
    }

    public static EntityManager getEntityManager(){
        return emFactory.createEntityManager();
    }

    public static void close(){
        emFactory.close();
    }
}
