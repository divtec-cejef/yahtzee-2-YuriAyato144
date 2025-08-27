/*
Projet : jeu du Yahtzee
Auteur : Aedan Bélet
Date : 20.08.2025
 */

import java.util.Scanner;

public class YahtzeeProcedural {

    /**
     * Affiche du text dans la console et demande à l'utilisateur s'il veut relancer.
     *
     * @return le ou les dés que l'utilisateur veut relancer.
     */
    public static int[] demandeRelancementDe() {
        System.out.println("Quels dés voulez-vous relancer ? (0 pour finir)");
        Scanner scanner = new Scanner(System.in);
        String ligne = scanner.nextLine().trim();

        if (ligne.equals("0") || ligne.isEmpty()) {
            return new int[0]; // Aucun dé à relancer
        }

        String[] parties = ligne.split(" ");
        int[] index = new int[parties.length];
        for (int i = 0; i < parties.length; i++) {
            index[i] = Integer.parseInt(parties[i]) - 1;
        }
        return index;
    }

    /**
     * Lance un dé qui retourne un nombre aléatoire.
     *
     * @return un nombre aléatoire de 1 à nombre de face défini.
     */
    public static int lancerDe() {
        int nombreFace = 6;

        return (int) (Math.random() * nombreFace) + 1;
    }

    /**
     * Liste contenant le nombre de dé au nombre aléatoire.
     *
     * @return les différent
     */
    public static int[] lancerPlusieursDe() {
        int[] nombreDe = new int[5];
        for (int lancer = 0; lancer < nombreDe.length; lancer++) {
            nombreDe[lancer] = lancerDe();
        }
        return nombreDe;
    }

    /**
     * Permet d'afficher plus proprement les jet de dés.
     *
     * @param listeDe garde en mémoire la liste des jets pour les afficher dans la console.
     */
    public static void afficherDe(int[] listeDe) {
        for (int afficher = 0; afficher < listeDe.length; afficher++) {
            System.out.print("Jet " + (afficher + 1) + " : " + listeDe[afficher] + "\n");
        }
    }

    /**
     * Permet de relancer le ou les dés sélectionné par l'utilisateur.
     *
     * @param des permet de garde en mémoire les dés.
     *
     * @param indexsRelancer permet de savoir quel index relancer
     */
    public static void relancerDe(int[] des, int[] indexsRelancer) {
        for (int i = 0; i < indexsRelancer.length; i++) {
            int index = indexsRelancer[i];
            if (index >= 0 && index < des.length) {
                des[index] = lancerDe(); // Relancer le dé à la bonne position
            }
        }
    }

    public static void main(String[] args) {
        int[] des = lancerPlusieursDe();
        afficherDe(des);

        for (int tour = 1; tour <= 2; tour++) {
            int[] relance = demandeRelancementDe();
            if (relance.length == 0) {
                break; // L'utilisateur ne veut plus relancer
            }

            relancerDe(des, relance);
            System.out.println("\nJet après relance " + tour + " :");
            afficherDe(des);
        }

        System.out.println("\nJet final :");
        afficherDe(des);
    }
}