/*
Projet : jeu du Yahtzee
Auteur : Aedan Bélet
Date : 20.08.2025
 */

import java.util.Scanner;

public class YahtzeeProcedural {
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

    public static void relancerDe() {
        afficherDe(lancerPlusieursDe());
        int[] listeDe = lancerPlusieursDe();
        for (int afficher = 0; afficher < lancerDe(); afficher++) {

            switch (lancerDe()) {

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
            }
        }
    }

    public static void main(String[] args) {
        afficherDe(lancerPlusieursDe());
        System.out.println("Quel jet voulez-vous relancer ? ");
        Scanner jetRelancer = new Scanner(System.in);
        System.out.println("Jet à relancer : " + jetRelancer.nextLine());
        System.out.println("Nouveaux jet : ");
        relancerDe();
    }
}

