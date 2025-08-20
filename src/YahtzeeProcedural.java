import java.util.Arrays;

public class YahtzeeProcedural {
    /**
     * Lance un dé qui retourne un nombre aléatoire.
     * @return un nombre aléatoire de 1 à nombre de face défini.
     */
    public static int De (){
         int nombreFace = 6;

        return (int) (Math.random() * nombreFace) + 1;
    }

    /**
     * Liste contenant le nombre de dé au nombre aléatoire.
     * @return les différent
     */
    public static int[] tableau() {
       int[] nombreDe =  new int[5];
       for (int lancer = 0; lancer < nombreDe.length; lancer++) {
           nombreDe[lancer] = De();
       }
        return nombreDe;
    }

    public static void afficherDe(int[] listeDe){
        for (int afficher = 0; afficher < tableau().length; afficher++) {
            System.out.print("Jet " + (afficher + 1) + " : " +  listeDe[afficher] + "\n");
        }
    }

    public static void main(String[] args) {
        afficherDe(tableau());
    }

}