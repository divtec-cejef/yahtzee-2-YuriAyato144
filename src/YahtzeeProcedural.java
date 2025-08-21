/*
Projet : jeu du Yahtzee
Auteur : Aedan Bélet
Date : 20.08.2025
 */

import java.util.Scanner;

public class YahtzeeProcedural {


    public static int[] affichageConsole() {
        System.out.println("Quel jet voulez-vous relancer ? ");
        Scanner jetRelancer = new Scanner(System.in);
        String ligne = jetRelancer.nextLine();
        String[] parties = ligne.split(" ");
        int[] choix = new int[parties.length];
        for (int i = 0; i < parties.length; i++) {
            choix[i] = Integer.parseInt(parties[i]);
        }
        return choix;
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
        for (int afficher = 0; afficher < lancerPlusieursDe().length; afficher++) {
            System.out.print("Jet " + (afficher + 1) + " : " + listeDe[afficher] + "\n");
        }
    }

    public static void relancerDe(int[] listeDe) {
        int[] choix = affichageConsole();
        for (int i = 0; i < choix.length; i++) {
            switch (choix[i]) {
                case 1:
                    listeDe[0] = lancerDe();
                    break;
                case 2:
                    listeDe[1] = lancerDe();
                    break;
                case 3:
                    listeDe[2] = lancerDe();
                    break;
                case 4:
                    listeDe[3] = lancerDe();
                    break;
                case 5:
                    listeDe[4] = lancerDe();
                    break;
                default:
                    System.out.println("Numéro de dé invalide : " + choix[i]);
            }
        }
        System.out.println("Nouveaux jet : ");
        afficherDe(listeDe);
    }

    public static void main(String[] args) {
        int[] des = lancerPlusieursDe();
        afficherDe(des);
        affichageConsole();
        relancerDe(des);
    }
}

