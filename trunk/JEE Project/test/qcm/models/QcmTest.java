package qcm.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import qcm.persistences.QuestionDAO;
import qcm.persistences.QuestionnaireDAO;
import qcm.persistences.QuestionnairePasseDAO;
import tools.QCMTestCase;
import static org.junit.Assert.*;

/**
 *
 * @author marya
 */
public class QcmTest extends QCMTestCase {

    @Test
    public void testQcm() throws SQLException {
        System.out.println("Qcm");
        Qcm instance = new Qcm(1, 1);
        ArrayList<Question> expResult = new ArrayList<Question>();
        expResult.add(QuestionDAO.getById(1));
        expResult.add(QuestionDAO.getById(2));
        expResult.add(QuestionDAO.getById(3));
        ArrayList<Question> result = QuestionnaireDAO.getQuestionsById(instance.getQuestionnaire().getIdQuestionnaire());
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getQuestionSuivante method, of class Qcm.
     */
    @Test
    public void testGetQuestionCourante() throws SQLException {
        System.out.println("getQuestionCourante");
        Qcm instance = new Qcm(1, 1);
        int expResult = 1;
        int result = instance.getQuestionSuivante();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserReponse method, of class Qcm.
     */
    @Test
    public void testSetUserReponses() {
        System.out.println("setUserReponse");
        int idQuestionnaire = 1;
        int idQuestion = 1;
        Qcm instance = new Qcm(idQuestionnaire, idQuestion);
        List<Integer> reponses = new ArrayList<Integer>();
        reponses.add(1);
        reponses.add(2);
        instance.setUserReponses(idQuestion, reponses);
        assertTrue(instance.getUserReponses().get(idQuestion).containsAll(reponses));
        assertTrue(reponses.containsAll(instance.getUserReponses().get(idQuestion)));
    }

    /**
     *
     */
    @Test
    public void testGetNote() throws SQLException {
        System.out.println("getNote");
        int idQuestionnaire = 1;
        int idQuestion = 1;
        Qcm instance = new Qcm(idQuestionnaire, idQuestion);
        List<Integer> reponses = new ArrayList<Integer>();
        reponses.add(1);
        reponses.add(3);
        instance.setUserReponses(idQuestion, reponses);
        int expResult = 5;
        instance.setEstFini(true);
        int result = instance.getNote();
        assertEquals(expResult, result);
    }

    @Test
    public void testSave() throws SQLException {
        System.out.println("save");
        int idQuestionnaire = 4;
        int idUser = 1;
        Qcm instance = new Qcm(idQuestionnaire, idUser);
        List<Integer> reponses = new ArrayList<Integer>();
        reponses.add(14);
        reponses.add(15);
        instance.setUserReponses(21, reponses);
        System.out.println(instance.getQuestionSuivante());
        assert instance.invariant();
        instance.save();
        assertTrue(instance.estFini());
        assertTrue(instance.getNote() == 6);
        assertTrue(QuestionnaireDAO.getById(4).estPasse());
        assertTrue(QuestionnairePasseDAO.getByUser(idUser).contains(new QuestionnairePasse(4, 1)));
    }
}