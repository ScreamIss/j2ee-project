package qcm.actions.passerQuestionnaire;

import java.sql.SQLException;
import qcm.services.ActionHelper;

/**
 * Action pour passer un questionnaire
 * @author marya
 */
public class PasserQuestionnaireIndexAction extends PasserQuestionnaireAction{

    public void execute() throws SQLException {
        
        ActionHelper.setAttributeNiveaux(request);
        ActionHelper.setAttributeThemes(request);
        setView("/passerQuestionnaire/choixQuestionnaire.jsp");
    }
}
