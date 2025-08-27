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
        System.out.println("Quel jet voulez-vous relancer ? (0 pour finir)");
        Scanner jetRelancer = new Scanner(System.in);
        String ligne = jetRelancer.nextLine();
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
     * Permet de relancer le ou les dés que l'utilisateur à choisi.
     *
     * @param listeDe permet de garder en mémoire la nouvelle face du dé:
     */
    public static void relancerDe(int[] listeDe) {
        int[] choix = demandeRelancementDe();
        for (int i = 0; i < choix.length; i++) {
            int index = choix[i];
            if (index >= 0 && index < listeDe.length) {
                listeDe[index] = lancerDe();
            } else {
                System.out.println("Numéro de dé invalide : " + (index + 1));
            }
        }
        System.out.println("Nouveaux jets : ");
        afficherDe(listeDe);
    }

    public static void main(String[] args) {
        int[] des = lancerPlusieursDe();
        afficherDe(des);

        for (int tour = 1; tour <= 2; tour++) {
            relancerDe(des);
        }

        System.out.println("\nJet final :");

        afficherDe(des);
    }
}