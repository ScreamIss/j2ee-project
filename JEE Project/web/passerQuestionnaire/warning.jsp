<%@page import="qcm.models.Questionnaire" %>
<%@page import="qcm.models.Theme" %>
<%@page import="qcm.models.Niveau" %>

<h3>Attention : veuillez lire ceci avant de d�buter votre questionnaire</h3>
<div class="warning-qcm">

    <p><img id="warning-image" src="<%= request.getContextPath() %>/img/warning.gif" alt="Attention" height="40px" />Vous �tes sur le point de passer un questionnaire et il y a certains points sur lesquels nous nous sentons oblig�s de vous informer :</p>
    <ul>
        <li>Certains questionnaires ont une limite de temps, vous ne pourrez plus r�pondre aux questions pass� cette limite et votre note sera automatiquement calcul�e en fonction des r�ponses que vous aurez d�j� donn�es;</li>
        <li>Vous pouvez choisir de terminer le questionnaire � tout moment en cliquant sur le bouton "Terminer maintenant", le questionnaire s'arr�tera et votre note sera calcul�e;</li>
    </ul>
<%
    Questionnaire questionnaire = (Questionnaire) request.getAttribute("questionnaire");
    if (questionnaire != null) {

%>
    <div class="recapitule_questionnaire liste">
        <h5>R�capitul� du questionnaire</h5>
        <table>
            <tr>
                <td class="static">Titre</td>
                <td><%=questionnaire.getLibelle()%></td>
            </tr>
            <tr>
                <td class="static">Th�me</td>
                <td><%= ((Theme) request.getAttribute("theme")).getLibelle() %></td>
            </tr>
            <tr>
                <td class="static">Niveau</td>
                <td><%= ( (Niveau) request.getAttribute("niveau")).getLibelle() %></td>
            </tr>
            <tr>
                <td class="static">Nombre de questions</td>
                <td><%=questionnaire.getQuestions().size()%></td>
            </tr>
            <tr>
                <td class="static">Temps accord�</td>
                <td><%=questionnaire.getLimiteTemps()%> minutes</td>
            </tr>
        </table>
    </div>
    <%
                }
    %>


    <a href="<%= request.getContextPath() %>/passerQuestionnaire/index.html" class="button">Retour � la liste des questionnaires</a>

    <%
                if (request.getAttribute("userHasAlreadyPassedQuestionnaire") == null) {
    %>

    <form action="<%= request.getContextPath() %>/passerQuestionnaire/commencer.html" method="post" id="commencer_qcm_form">
        <input type="hidden" name="questionnaire" value="<%= questionnaire.getIdQuestionnaire()%>" />
        <input type="submit" value="Commencer" class="button" />
    </form>
    <%
                } else {
                    out.println("<p class='error' style='display:inline'>Vous avez d�j� pass� ce questionnaire</p>");
                }
    %>

</div>
              