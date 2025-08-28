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
    private static int[] demandeRelancementDe() {
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
    private static int lancerDe() {
        int nombreFace = 6;

        return (int) (Math.random() * nombreFace) + 1;
    }

    /**
     * Liste contenant le nombre de dé au nombre aléatoire.
     *
     * @return les différent
     */
    private static int[] lancerPlusieursDe() {
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
    private static void afficherDe(int[] listeDe) {
        for (int afficher = 0; afficher < listeDe.length; afficher++) {
            System.out.print("Jet " + (afficher + 1) + " : " + listeDe[afficher] + "\n");
        }
    }

    /**
     * Permet de relancer le ou les dés sélectionné par l'utilisateur.
     *
     * @param des            permet de garde en mémoire les dés.
     * @param indexsRelancer permet de savoir quel index relancer
     */
    private static void relancerDe(int[] des, int[] indexsRelancer) {
        for (int i = 0; i < indexsRelancer.length; i++) {
            int index = indexsRelancer[i];
            if (index >= 0 && index < des.length) {
                des[index] = lancerDe(); // Relancer le dé à la bonne position
            }
        }
    }

    /**
     * Savoir combien de dés on la même face.
     *
     * @param listeDés tableau pour garder en mémoire les faces des dés.
     * @return le nombre de même face
     */
    private static int[] nombreOccurences(int[] listeDés) {

        int[] nombreOccurences = new int[6];
        for (int nombre : listeDés) {
            nombreOccurences[nombre - 1]++;
        }
        return nombreOccurences;
    }


    private static boolean unePaire(int[] listeDes) {

        int[] nombreOccurences = nombreOccurences(listeDes);
        for (int occurrence : nombreOccurences) {
            if (occurrence >= 2) {
                return true;
            }
        }
        return false;
    }

    private static boolean deuxPaire(int[] listeDes) {

        int[] nombreOccurences = nombreOccurences(listeDes);
        for (int occurrence : nombreOccurences) {
            if (occurrence >= 4) {
                return true;
            }
        }
        return false;
    }


    private static boolean brelan(int[] listeDes) {

        int[] nombreOccurences = nombreOccurences(listeDes);
        for (int occurrence : nombreOccurences) {
            if (occurrence >= 4) {
                return true;
            }
        }
        return false;
    }

    /**
     * Enumeration  de la liste des combinaison possible.
     */
    enum Combinaison {
        UNE_PAIRE,
        DEUX_PAIRES,
        BRELAN,
        CARRE,
        FULL_HOUSE,
        PETITE_SUITE,
        GRANDE_SUITE,
        YAHTZEE
    }

    private static String score(Combinaison combinaison) {
        int point = 0;
        String score = switch (combinaison) {
            case Combinaison.UNE_PAIRE -> "Une paire : " + point + "pts";
            case Combinaison.DEUX_PAIRES -> "Deux paire : " + point + "pts";
            case Combinaison.BRELAN -> "Brelan : " + point + "pts";
            case Combinaison.CARRE -> "Carre : " + point + "pts";
            case Combinaison.FULL_HOUSE -> "Full house : " + point + "pts";
            case Combinaison.PETITE_SUITE -> "Petite suite : " + point + "pts";
            case Combinaison.GRANDE_SUITE -> "Grande suite : " + point + "pts";
            case Combinaison.YAHTZEE -> "Yahtzee : " + point + "pts";
        };
        return score;
    }

    public static void main(String[] args) {
        int[] des = lancerPlusieursDe();
        afficherDe(des);
        for (int tour = 1; tour <= 2; tour++) {
            int[] relance = demandeRelancementDe();
            if (relance.length == 0) {
                break;
            }
            relancerDe(des, relance);
            System.out.println("\nJet après relance " + tour + " :");
            afficherDe(des);
        }
        System.out.println("\nJet final :");
        afficherDe(des);
        System.out.println("\nFace de la même valeur :");
        for (int i = 0; i < nombreOccurences(des).length; i++) {
            System.out.println("Nombre " + (i + 1) + " : " + nombreOccurences(des)[i] + " fois");
        }
        System.out.println("\nScore des combinaisons :");
        for (Combinaison combinaisonScore : Combinaison.values()) {
            System.out.println(score(combinaisonScore));
        }
        if (unePaire(des)) {
            System.out.println("ceci est une paire!");
        }
        if (deuxPaire(des)) {
            System.out.println("ceci sont deux paires!");
        }

    }
}