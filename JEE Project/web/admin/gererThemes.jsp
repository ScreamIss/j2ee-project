<%-- 
    Document   : gererThemes
    Created on : 13 avr. 2010, 14:24:26
    Author     : Lou
--%>

<%@page import="qcm.models.Theme"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h4>Gérer les thèmes des questionnaires</h4>
<jsp:include page="/scripts/errorViewHelper.jsp" />

<%
            List<Theme> themes = (ArrayList) request.getAttribute("themes");
            if (themes != null) {
%>
<table class="format">
    <tr>
        <th>Libellé</th>
        <th>Modifier</th>
        <th>Activité</th>
        <th>Contrôle</th>
    </tr>
    <%
                    for (Theme theme : themes) {
    %>
    <tr>
        <td><%= theme.getLibelle()%></td>
        <%
                                if (theme.getUtilisations() == 0) {
        %>
        <td class="centered">
            <a href="<%= request.getContextPath()%>/admin/themes/editer.html?id=<%= theme.getIdTheme()%>">Modifier
                <img src="<%= request.getContextPath()%>/img/edit_16.png" alt="Modifier le libellé du thème" />
            </a>
        </td>
        <td class="centered">
            <%
                                                                    if (theme.estActif()) {
                                                                        out.println("<span class='bon'>Actif</span>");
                                                                    } else {
                                                                        out.println("<span class='mauvais'>Inactif</span>");
                                                                    }
            %>
        </td>
        <td class="centered">
            <form method="post" action="<%= request.getContextPath()%>/admin/themes/controle.html">
                <input type="hidden" name="id" value="<%= theme.getIdTheme()%>" />
                <% if (theme.estActif()) {%>
                <input type="submit" value="Désactiver" /> <input type="hidden" name="est_actif" value="false" />
                <% } else {%>
                <input type="submit" value="Activer" /> <input type="hidden" name="est_actif" value="true" />
                <% }%>
            </form>
        </td>
        <% } else {%>
        <td class="centered" colspan="3"><small>Ce thème ne peut pas être modifié ou supprimé.</small></td>
        <% }%>
    </tr>
    <%
                    }
    %>
</table>
<%
            }
%>
