<%-- 
    Document   : menu_left
    Created on : 27 mars 2010, 11:37:22
    Author     : Lou
Menu de gauche à inclure
--%>
<%@page import="qcm.models.User" %>
<%
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                request.setAttribute("errorMessage", "Vous n'êtes pas connecté");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
                <div id="navigation">
                </div>


                <div id="navigation">
                    <ul class="menu_gauche">
                        <a href="<%= request.getContextPath() %>/passerQuestionnaire/index.html"><li>Passer un questionnaire</li></a>
                        <a href="<%= request.getContextPath() %>/mesResultats/index.html"><li>Mes résultats</li></a>
            <%
                if (user.isCreator() || user.isAdmin()) {
            %>
                        <a href="<%= request.getContextPath() %>/creerQuestionnaire/index.html"><li>Créer un questionnaire</li></a>
                        <a href="<%= request.getContextPath() %>/mesQuestionnaires/index.html"><li>Mes questionnaires</li></a>
            <% }%>
                    </ul>
            <%
                if (user.isAdmin()) {
            %>
                    <ul class="menu_gauche"><h5>Admin</h5>
                        <a href="Admin?action=gererThemes"><li>Thèmes</li></a>
                        <a href="Admin?action=gererNiveaux"><li>Niveau</li></a>
                        <a href="Admin?action=gererQuestionnaires"><li>Questionnaires</li></a>
                        <a href="Admin?action=gererUtilisateurs"><li>Utilisateurs</li></a>
                    </ul>
            <% }%>
                </div>
