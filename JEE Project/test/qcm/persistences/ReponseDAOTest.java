package qcm.persistences;

import org.junit.Test;
import static org.junit.Assert.*;
import qcm.models.Reponse;
import tools.QCMTestCase;

/**
 *
 * @author marya
 */
public class ReponseDAOTest extends QCMTestCase {

    /**
     * Test of getById method, of class ReponseDAO.
     */
    @Test
    public void testGetById() throws Exception {
        System.out.println("getById");
        int idReponse = 1;
        Reponse expResult = new Reponse(1, "Réponse 1", "Descriptif Réponse 1 Question 1", false, 0, 1);
        Reponse result = ReponseDAO.getById(idReponse);
        assertEquals(expResult, result);
    }
}
