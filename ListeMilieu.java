
/**
 * Cette classe est une structure de donnée. Elle contient deux liste chainée. La liste supérieur est en ordre
 * croissant. La liste inférieur est en ordre décroissant. Aucun élément n'est en double dans la listeMilieu.
 * Les éléments les plus "grands" sont placé dans la liste supérieur. Les plus "petits" dans la liste inférieure.
 * Puisqu'un certain ordre est respecté, les objets contenus doivent être comparables.
 * La taille de la liste inférieur peut être au plus plus grande d'un élément que la taille de la liste supérieure.
 */
public class ListeMilieu< E extends Comparable< E > > {

    private ListeChaine <E> listeSuperieur= new ListeChaine<>();
    private ListeChaine <E> listeInferieur= new ListeChaine<>();
    public ListeMilieu(){
    }

    /**
     * Permet de diviser une liste milieu.
     * Cela a pour effet de créer une nouvelle liste milieu ou la liste
     * supérieur de this constituera la nouvelle listeMilieu. La liste inférieure
     * de this sera réduit pour constituer une nouvelle liste supérieure.
     *
     * @return Une nouvelle ListeMilieu.
     */
    public ListeMilieu<E> diviser() {
        ListeMilieu<E> nouvelleListeMilieu = new ListeMilieu<>();
        nouvelleListeMilieu.listeSuperieur = this.listeSuperieur;
        this.listeSuperieur=new ListeChaine<>();
        while(nouvelleListeMilieu.listeSuperieur.getTaille() > nouvelleListeMilieu.listeInferieur.getTaille()){
            nouvelleListeMilieu.equiliber();
        }
        while(this.listeSuperieur.getTaille()+1<this.listeInferieur.getTaille()){
            this.equiliber();
        }
        return nouvelleListeMilieu;
    }

    /**
     * Permet d'insérer une valeur dans la listeMilieu.
     * Cette valeur sera insérée dans la liste supérieur
     * si elle est supérieure à la valeur minimale de la liste supérieure.
     * Sinon elle sera insérée dans la liste inférieure.
     * Cette méthode s'assure de ne pas insérer de doublon.
     *
     * @param valeur
     */
    public void inserer( E valeur ) {
        if(!this.contient(valeur)) {
            if (listeInferieur.getHead() != null && valeur.compareTo(milieu()) > 0) {
                listeSuperieur.inserer(valeur, true);
            } else {
                listeInferieur.inserer(valeur, false);
            }
            this.equiliber();
        }
    }


    /**
     * Acceder à l'élément milieu de la liste.
     * Par définition le premier élément de la liste inférieure.
     *
     * @return Valeur du premier chainon de la liste inférieure.
     */
    public E milieu() {
        return listeInferieur.getHead().getElement();
    }

    /**
     * Acceder au minimum de la liste milieu.
     * C'est le dernier élément de la liste inférieure.
     *
     * @return valeur du dernier chainon de la liste inférieure.
     */
    public E minima() {
        Chainon <E> temp = listeInferieur.getHead();
        while(temp.getSuivant() != null){
            temp = temp.getSuivant();
        }

        return temp.getElement();
    }

    /**
     * Acceder au maximum de la liste milieu.
     * C'est le dernier élément de la liste supérieur si cette dernière existe, sinon c'est le milieu.
     *
     * @return valeur du dernier chainon de la liste supérieure
     */
    public E maxima() {
        ListeChaine<E> liste = listeSuperieur.getHead()!=null?listeSuperieur:listeInferieur;
        Chainon<E> chainonTemp = liste.getHead();

        if (liste == listeSuperieur) {
            while (chainonTemp.getSuivant() != null) {
                chainonTemp = chainonTemp.getSuivant();
            }
        }
        return chainonTemp.getElement();
    }

    /**
     * Permet de supprimer une valeur de la listeMilieu si elle y est présente.
     *
     * @param valeur à supprimer.
     */
    public void supprimer(E valeur) {

        if(listeInferieur.getHead() != null) {
            ListeChaine<E> liste = (valeur.compareTo(milieu()) > 0) ? listeSuperieur : listeInferieur;
            Chainon<E> chainonTemp = liste.getHead();
            boolean elementTrouve = false;

            //cas ou la valeur est la tête d'une liste.
            if (chainonTemp.getElement().compareTo(valeur) == 0) {
                liste.setHead(chainonTemp.getSuivant());
                elementTrouve = true;
            }

            while (!elementTrouve && chainonTemp.getSuivant() != null) {
                if (chainonTemp.getSuivant().getElement().compareTo(valeur) == 0) {
                    chainonTemp.setSuivant(chainonTemp.getSuivant().getSuivant());
                    elementTrouve = true;
                }
                chainonTemp = chainonTemp.getSuivant();
            }

            if (elementTrouve) {
                liste.setTaille(liste.getTaille() - 1);
                this.equiliber();
            }
        }
    }

    /**
     * Retourne la taille de la liste milieu.
     * Ceci représente le nombre total d'élément des listes inférieur et supérieur.
     */
    public int taille() {
        return listeInferieur.getTaille()+ listeSuperieur.getTaille();
    }

    /**
     * Cette méthode permet d'équilibrer une listeMilieu.
     * Après insertion, division ou suppression, la listeMilieu peut être déséquilibrée.
     * C'est à dire que la condition suivante n'est pas respectée : la taille de la liste inférieur
     * est égale à la taille de la liste supérieure (moins un élément optionnellement).
     */
    public void equiliber(){
        if(listeSuperieur.getTaille() > listeInferieur.getTaille()){
            Chainon<E> temp = new Chainon<>(listeSuperieur.getHead().getElement(),listeInferieur.getHead());
            listeInferieur.setHead(temp);
            listeSuperieur.setHead(listeSuperieur.getHead().getSuivant());
            listeSuperieur.setTaille(listeSuperieur.getTaille()-1);
            listeInferieur.setTaille(listeInferieur.getTaille()+1);
        }
        else if(listeSuperieur.getTaille()+1< listeInferieur.getTaille() ){
            Chainon<E> temp = new Chainon<>(listeInferieur.getHead().getElement(),listeSuperieur.getHead());
            listeSuperieur.setHead(temp);
            listeInferieur.setHead(listeInferieur.getHead().getSuivant());
            listeInferieur.setTaille(listeInferieur.getTaille()-1);
            listeSuperieur.setTaille(listeSuperieur.getTaille()+1);
        }
    }

    /**
     * Permet de vérifier si un élément est présent dans la listeMilieu.
     *
     * @param valeur à chercher dans la listeMilieu.
     * @return Vrai si l'élément a été trouvé, faux sinon.
     */
    public boolean contient(E valeur){
        boolean resultat = false;
        if(listeInferieur.getHead() != null ){
            ListeChaine<E> liste = valeur.compareTo(milieu())>0?listeSuperieur:listeInferieur;
            resultat = liste.contient(valeur);
        }
        return resultat;
    }

    @Override
    public String toString(){
        String resultat = "";
        if(listeSuperieur != null){
            resultat+=listeSuperieur.toString()+"\n";
        }
        if(listeInferieur != null){
            resultat +=listeInferieur.toString()+"\n";
        }
        return resultat;
    }

}
