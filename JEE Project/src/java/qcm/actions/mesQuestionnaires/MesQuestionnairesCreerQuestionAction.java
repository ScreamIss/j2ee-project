/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qcm.actions.mesQuestionnaires;

import java.util.ArrayList;
import qcm.actions.EnseignantAction;
import qcm.exceptions.UnauthorizedActionException;
import qcm.models.Question;
import qcm.models.Questionnaire;
import qcm.models.Reponse;
import qcm.persistences.QuestionDAO;
import qcm.persistences.QuestionnaireDAO;
import qcm.services.ActionHelper;

/**
 *
 * @author marya
 */
public class MesQuestionnairesCreerQuestionAction extends EnseignantAction{

    public void execute() throws Exception {
        String libelleQuestion = (String) request.getParameter("libelleQuestion");
        if (libelleQuestion == null || libelleQuestion.trim().isEmpty()) {
            throw new UnauthorizedActionException("Merci d'entrer le libellé de votre question");
        } else if (request.getParameter("nbReponses") == null) {
            throw new UnauthorizedActionException("Le nombre de réponses ne correspond pas à ce qui est attendu");
        }
        int nbReponses = Integer.parseInt(request.getParameter("nbReponses"));
        if (nbReponses <= 0) {
            throw new UnauthorizedActionException("Le nombre de réponses ne correspond pas à ce qui est attendu");
        }
       Questionnaire newQuestionnaire = QuestionnaireDAO.getById(Integer.parseInt(request.getParameter("idQuestionnaire")));
        Question nouvelleQuestion = new Question(null, libelleQuestion, newQuestionnaire.getIdTheme(), ActionHelper.getIdUser(request), 0, new ArrayList<Reponse>());
        if (newQuestionnaire.getQuestions().contains(nouvelleQuestion)) {
            throw new UnauthorizedActionException("Cette question existe déjà pour ce questionnaire");
        }
        if(QuestionDAO.search(nouvelleQuestion) != null){
            throw new UnauthorizedActionException("Une question du même libelle et du même thème existe déjà");
        }
        String libelleReponse = null;
        String descriptifReponse = null;
        Integer noteReponse = 0;
        boolean estCorrecte = false;
        for(int i = 1 ; i<=nbReponses ; i++){
            libelleReponse = (String) request.getParameter("libelleReponse_" + i);
            descriptifReponse = (String) request.getParameter("descriptifReponse_" + i);
            noteReponse = Integer.parseInt(request.getParameter("noteReponse_" + i ));
            if(noteReponse == null){
                throw new UnauthorizedActionException("Merci de spécifier chaque note pour chaque réponse");
            }
            estCorrecte = request.getParameterValues("estCorrecteReponse_" + i) != null ;
            nouvelleQuestion.getReponses().add(new Reponse(null, libelleReponse, descriptifReponse, estCorrecte, noteReponse, null));
        }
        newQuestionnaire.addQuestion(nouvelleQuestion);
        QuestionnaireDAO.addQuestion(newQuestionnaire.getIdQuestionnaire(), nouvelleQuestion);
        request.setAttribute("questionnaire" , newQuestionnaire.getIdQuestionnaire());
        setView("/mesQuestionnaires/actionEffectue.jsp");
    }

}
