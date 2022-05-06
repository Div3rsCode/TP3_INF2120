
/**
 * Cette classe représente un chainon utilisé par {@see ListeChaine} pour créer une liste chianée.
 * Un chainon contient donc un élément et une référence au prochain chainon.
 * Un element peut-être n'importe quel objet grâce à la généricité.
 */
public class Chainon<E>  {

    private E element;
    private Chainon<E> suivant;

    public Chainon(E element, Chainon<E> suivant) {
        this.element = element;
        this.suivant = suivant;
    }

    public Chainon(E element){
        this.element = element;
        this.suivant = null;
    }

    /**
     * Permet d'obtenir l'élément du chainon.
     */
    public E getElement() {
        return element;
    }

    /**
     * Permet d'obtenir l'adresse du prochain chainon.
     */
    public Chainon<E> getSuivant() {
        return suivant;
    }

    /**
     * Permet de spécifier l'adresse du prochain chainon.
     */
    public void setSuivant(Chainon<E> suivant) {
        this.suivant = suivant;
    }

    @Override
    public String toString(){
        return element.toString();
    }

}