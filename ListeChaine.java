/**
 * Classe qui représente une lise chainée avec différente méthodes associées à la structure.
 * La liste peut contenir tout objets dont la classe implémente Comparable puisque cette liste chainée
 * est trié donc a besoin de pouvoir comparer les objets contenus dans ses chainons.
 */
public class ListeChaine <E extends Comparable<E>> {

    private Chainon<E> head=null;
    private int taille=0;

    public ListeChaine(){
    }

    /**
     * Permet de spécifier le chainon en tête de liste.
     *
     * @param head le chainon qui sera placé à la tête de liste.
     */
    public void setHead(Chainon<E> head) {
        this.head = head;
    }

    /**
     * Permet d'obtenir la tête de liste
     * @return
     */
    public Chainon<E> getHead() {
        return head;
    }

    /**
     * Permet de régler la taille à la valeur souhaité
     * Attention : modifier cette valeur peut causer un disfonctionnement de la liste chainée.
     *
     * @param taille taille désirée.
     */
    protected void setTaille(int taille) {
        this.taille = taille;
    }

    /**
     * Permet d'obtenir la taille de la liste chainée.
     * @return
     */
    public int getTaille() {
        return taille;
    }

    /**
     * Permet d'insérer au bon endroit une valeur dans la liste chainée.
     * Cela a pour effet de garder la liste triée en tout temps.
     *
     * @param valeur qui doit être insérée dans la liste chainée.
     * @param croissance Permet de spécifier si la liste chainée est en ordre croissant ou non ( décroissant )
     */
    public void inserer(E valeur, boolean croissance){
        Chainon<E> temp = head;
        boolean valeurInseree = false;

        //Valeur insérée comme nouvelle tête de la file
        if(head == null || valeur.compareTo(temp.getElement())>0!=croissance){
            head = new Chainon<>(valeur,head);
            valeurInseree = true;
        }

        while(!valeurInseree){
            //la valeur est déjà présente dans la liste
            if(temp.getElement().compareTo(valeur)==0) {
                valeurInseree = true;
                taille --;
            }
            //la valeur est ajoutée à la fin de la liste
            else if(temp.getSuivant()==null){
                Chainon<E> nouveauChainon = new Chainon<>(valeur);
                temp.setSuivant(nouveauChainon);
                valeurInseree=true;
            }
            //la valeur est ajouté entre le début et la fin de la liste
            else if(valeur.compareTo(temp.getSuivant().getElement())>0 != croissance){
                Chainon<E> nouveauChainon = new Chainon<>(valeur,temp.getSuivant());
                temp.setSuivant(nouveauChainon);
                valeurInseree=true;
            }
            temp = temp.getSuivant();
        }
        taille+=1;
    }

    /**
     * Permet de savoir si une valeur est présente dans la liste chainée.
     *
     * @param valeur dont on souhaite savoir si elle est présente.
     * @return vrai si la valeur est présente dans la liste, faux sinon.
     */
    public boolean contient(E valeur){
        boolean elementTrouve = false;
        Chainon<E> temp = head;

        //cas ou la liste chainée est vide.
        if(head != null && temp.getElement().compareTo(valeur)==0){
            elementTrouve = true;
        }
        while(!elementTrouve && head != null && temp.getSuivant()!=null){
            temp = temp.getSuivant();
            if(temp.getElement().compareTo(valeur)==0){
                elementTrouve = true;
            }
        }

        return elementTrouve;
    }

    @Override
    public String toString(){
        Chainon<E> temp = head;
        if (temp ==null)return "null\n";
        String resultat ="| "+temp.getElement().toString()+" | ";
        while(temp.getSuivant() !=null){
            temp = temp.getSuivant();
            resultat+=temp.getElement().toString()+" |";
        }
        return resultat+" taille="+taille+"\n";
    }
}
