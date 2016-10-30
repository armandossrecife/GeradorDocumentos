/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author carlos.silva
 */
public class JPAUtil {

    private static EntityManager entityManager;
    private static EntityManagerFactory factory=null;

    public static EntityManager getEntityManager() {
        try {
            if (factory == null) {
                factory = Persistence.createEntityManagerFactory("SRSEDITORPU");
                entityManager = factory.createEntityManager();
            }
        } catch (Exception e) {
            //System.out.println("Erro: " + e.printStackTrace());
             e.printStackTrace();
        }
        
        return entityManager;
    }
}
