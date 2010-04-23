package qcm.persistences;

import java.sql.Connection;
import java.sql.ResultSet;
import org.junit.Test;
import qcm.models.Niveau;
import tools.QCMTestCase;
import static org.junit.Assert.*;

/**
 *
 * @author marya
 */
public class ModeleDAOTest extends QCMTestCase {

    /**
     * Test of getConnection method, of class ModeleDAO.
     */
    @Test
    public void testGetConnection() throws Exception {
        System.out.println("getConnection");
        Connection result = ModeleDAO.getConnection();
        assertTrue(result != null);
    }

    /**
     * Test of execute method, of class ModeleDAO.
     */
    @Test
    public void testExecute() throws Exception {
        System.out.println("execute");
        String sql = "SELECT id_niveau, libelle FROM niveau WHERE id_niveau = 2";
        ResultSet result = ModeleDAO.execute(sql);
        assertNotNull(result);
        assertTrue(result.next());
        Niveau expResult = new Niveau(2, "Avancé", 3, true);
        assertTrue(expResult.equals(new Niveau(result.getInt(1), result.getString(2), 3, true)));
    }

    /**
     * Test of selectById method, of class ModeleDAO.
     */
    @Test
    public void testSelectById() throws Exception {
        System.out.println("selectById");
        String sql = "SELECT libelle FROM questionnaire WHERE id_questionnaire = ?";
        int id = 1;
        ResultSet expResult = ModeleDAO.execute("SELECT libelle FROM questionnaire WHERE id_questionnaire = 1");
        ResultSet result = ModeleDAO.selectById(sql, id);
        assertNotNull(result);
        assertTrue(result.next());
        expResult.next();
        assertEquals(expResult.getString(1), result.getString(1));
    }
}
