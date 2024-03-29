package qcm.models;

/**
 * Classe Niveau, déterminant le niveau d'un questionnaire
 * @author Maria Rabarison et Lou Ferrand
 */
public class Niveau {

    private Integer idNiveau;
    private String libelle;
    private int utilisations;
    private boolean estActif;

    /**
     * Constructeur de niveau
     * @param idNiveau L'identifiant du niveau
     * @param libelle Le libelle du niveau
     */
    public Niveau(int idNiveau, String libelle, int utilisations, boolean estActif) {
        assert idNiveau > 0;
        assert libelle != null && !libelle.trim().isEmpty() : "Le Libelle ne doit être ni null ni vide";

        this.idNiveau = idNiveau;
        this.libelle = libelle;
        this.utilisations = utilisations;
        this.estActif = estActif;
        assert invariant();
    }

    public int getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(int idNiveau) {
        assert idNiveau > 0;
        this.idNiveau = idNiveau;
        assert invariant();
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        assert libelle != null && !libelle.trim().isEmpty() : "Le Libelle ne doit être ni null ni vide";
        this.libelle = libelle;
        assert invariant();
    }

    /**
     * Retourne le nombre de Questionnaires qui utilisent ce niveau
     * @return le nombre de questionnaires
     */
    public int getUtilisations() {
        return utilisations;
    }

    public boolean estActif() {
        return estActif;
    }

    public void setEstActif(boolean estActif) {
        this.estActif = estActif;
    }
    
    /**
     *
     * @return true si les invariants sont vérifiés, sinon s'arrête
     */
    protected boolean invariant() {
        assert getLibelle() != null && !getLibelle().trim().isEmpty() : "Le Libelle ne doit être ni null ni vide";
        assert getIdNiveau() > 0;
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Niveau other = (Niveau) obj;
        if ((this.libelle == null) ? (other.libelle != null) : !this.libelle.equals(other.libelle)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.libelle != null ? this.libelle.hashCode() : 0);
        return hash;
    }

    @Override
    /**
     * Permet d'avoir les informations sur le niveau
     */
    public String toString() {
        return " [ " + getLibelle() + " : " + getIdNiveau() + " ] ";
    }

}
