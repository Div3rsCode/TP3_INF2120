/**
 *Jordan Wulleman
 * WULJ19129303
 * TP3_INF2120
 */

/**
 * Classe représentant une structure de donnée.
 * Cette classe est une liste chainée. Les éléments des chainons sont des {@see listeMilieu}.
 * Les différentes listeMilieu sont triées. C'est à dire que le maxima de la liste au chainon i est inférieur au minima
 * de la listeMilieu au chainon i+1.
 * Le nombre d'éléments d'une listeMilieu est au maximum
 * plus grand que le double du nombre de chainons de la listeIndex.
 */
public class ListeIndex<E extends Comparable< E > > {

    Chainon<ListeMilieu<E>> headList = new Chainon<>(new ListeMilieu<>());

    /**
     * Permet de vérifier si un élément est présent dans la listeIndex.
     * @param valeur à chercher dans la listeMilieu.
     * @return Vrai si l'élément a été trouvé, faux sinon.
     */
    public boolean contient( E valeur ) {
        Chainon<ListeMilieu<E>> temp = headList;
        boolean elementTrouve = false;

        //cas ou une seule listeMilieu est présente OU
        // la valeur recherchée ne peut être que dans la première listeMilieu
        if(temp.getSuivant() == null || valeur.compareTo(temp.getElement().maxima())<=0){
            elementTrouve = temp.getElement().contient(valeur);
        }

        while(!elementTrouve && temp.getSuivant() != null){
            temp = temp.getSuivant();
            if(valeur.compareTo(temp.getElement().maxima())<=0) {
                elementTrouve=temp.getElement().contient(valeur);
            }
        }
        return elementTrouve;
    }

    //Permet d'accéder au chainon à la position i dans la listeIndex.
    public ListeMilieu<E> get( int i ) {
        Chainon<ListeMilieu<E>> resultat = headList;
        if(i > nbrListe()) System.out.println("L'index demandé est trop grand");
        else{
            int j=0;
            while(j!=i){
                resultat = resultat.getSuivant();
                j++;
            }
        }
        return resultat.getElement();
    }

    /**
     * Cette méthode permet d'équilibrer une {@see listeMilieu}.
     * Après insertion, division ou suppression, la listeIndex peut être déséquilibrée.
     * C'est à dire que la condition suivante n'est pas respectée : le nombre d'éléments dans une listeMilieu est
     * au plus deux fois plus grand que le nombre de chainons dans la listeIndex.
     */
    public void equilibrer(Chainon<ListeMilieu<E>> listeMilieu){
        if(listeMilieu.getElement().taille() >2*nbrListe()){
            Chainon<ListeMilieu<E>> nouvelleListeMilieu =
                    new Chainon<>(listeMilieu.getElement().diviser(),listeMilieu.getSuivant());
            listeMilieu.setSuivant(nouvelleListeMilieu);
        }
    }

    /**
     * Permet d'insérer une valeur dans la listeIndex.
     * La valeur sera insérée dans la {@see listeMilieu}  dont la valeur est :
     * plus grande ou égale au minima et plus petite ou égale au maxima.
     * La valeur sera insérée de manière à ce que la listeMilieu reste ordonnée.
     *
     * @param valeur a insérer.
     */
    public void inserer( E valeur ) {
        Chainon<ListeMilieu<E>> temp = headList;
        boolean elementInsere = false;

        //cas ou la valeur est insérée dans la première listeMilieu.
        if(temp.getElement().taille()==0 || valeur.compareTo(temp.getElement().maxima())<=0){
            temp.getElement().inserer(valeur);
            elementInsere = true;
        }

        while (!elementInsere && temp.getSuivant()!= null){
            temp = temp.getSuivant();
            if(valeur.compareTo(temp.getElement().maxima())<=0){
                temp.getElement().inserer(valeur);
                elementInsere = true;
            }
        }
        if(!elementInsere){
            temp.getElement().inserer(valeur);
        }

        this.equilibrer(temp);
    }

    /**
     * Permet d'obtenir le nombre de {@see listeMilieu} dans la listeIndex.
     *
     * @return un entier représentant le nombre de chainon dans la liste.
     */
    public int nbrListe() {
        int resultat = 0;
        Chainon<ListeMilieu<E>> temp = headList;
        if(temp.getElement().taille()>0)resultat++;
        while(temp.getSuivant() !=null){
            temp = temp.getSuivant();
            if(temp.getElement().taille()>0)resultat++;
        }
        return resultat;
    }

    /**
     * Permet de supprimer une valeur dans la listeIndex.
     * Si cette valeur n'existe pas, la méthode ne fait rien.
     *
     * @param valeur a supprimer.
     */
    public void supprimer( E valeur ) {
        Chainon<ListeMilieu<E>> temp = headList;
        boolean elementInferieur = false;

        //cas ou la listeIndex ne contient aucun objet.
        if(temp.getElement().taille()==0) elementInferieur = true;
        //cas ou la première listeMilieu contient la valeur.
        else if(valeur.compareTo(temp.getElement().maxima())<=0){
            temp.getElement().supprimer(valeur);
            elementInferieur = true;
        }

        while(temp.getSuivant() !=null || !elementInferieur){
            temp=temp.getSuivant();
            if(valeur.compareTo(temp.getElement().maxima())<=0){
                temp.getElement().supprimer(valeur);
                elementInferieur = true;
            }
        }

    }

    /**
     * Permet d'obtenir la taille de la listeIndex.
     *
     * @return la somme des tailles individuelles de chaque {@see listeMilieu}.
     */
    public int taille() {
        int taille = headList.getElement().taille();
        Chainon<ListeMilieu<E>> temp = headList;
        while (temp.getSuivant()!= null){
            temp=temp.getSuivant();
            taille += temp.getElement().taille();
        }
        return taille;
    }

    @Override
    public String toString(){
        String resultat = headList.toString()+"listeMilieu 1 :\n";
        Chainon<ListeMilieu<E>> temp = headList;
        int i = 2;
        while (temp.getSuivant()!= null){
            temp = temp.getSuivant();
            resultat = temp.toString()+"listeMilieu "+i+" :\n"+resultat;
            i++;
        }
        return resultat;
    }
}
