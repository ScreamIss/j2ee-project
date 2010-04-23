package qcm.persistences;

import org.junit.Test;
import static org.junit.Assert.*;
import qcm.models.Qcm;
import qcm.models.QuestionnairePasse;
import tools.QCMTestCase;

/**
 *
 * @author marya
 */
public class QcmDAOTest extends QCMTestCase {

    /**
     * Test of insert method, of class QcmDAO.
     */
    @Test
    public void testInsert() throws Exception {
        System.out.println("insert");
        Qcm qcm = new Qcm(4, 3);
        assertTrue(!qcm.estFini());
        QcmDAO.insert(qcm);
        assertTrue(qcm.estFini());
        System.out.println(QuestionnairePasseDAO.getByUser(3));
        assertTrue(QuestionnairePasseDAO.getByUser(3).contains(new QuestionnairePasse(4, 3)));
    }

    /**
     * Test of getIdContenu method, of class QcmDAO.
     */
    @Test
    public void testGetIdContenu() throws Exception {
        System.out.println("getIdContenu");
        int idQuestionnaire = 1;
        int idQuestion = 1;
        Integer expResult = 1;
        Integer result = QcmDAO.getIdContenu(idQuestionnaire, idQuestion);
        assertEquals(expResult, result);
    }
}
