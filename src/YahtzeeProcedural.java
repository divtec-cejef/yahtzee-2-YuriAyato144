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
        for (int index : indexsRelancer) {
            if (index >= 0 && index < des.length) {
                des[index] = lancerDe(); // Relancer le dé à la bonne position
            }
        }
    }

    /**
     * Savoir combien de dés on la même face.
     *
     * @param listeDes tableau pour garder en mémoire les faces des dés.
     * @return le nombre de même face.
     */
    private static int[] nombreOccurences(int[] listeDes) {
        int[] nombreOccurences = new int[6];
        for (int nombre : listeDes) {
            nombreOccurences[nombre - 1]++;
        }
        return nombreOccurences;
    }

    /**
     * Permet de savoir si l'on a fait une paire.
     *
     * @param listeDes prend en compte la liste de dés lancé.
     * @return vrai ou faux selon les lancer de dés.
     */
    private static boolean unePaire(int[] listeDes) {
        int[] nombreOccurences = nombreOccurences(listeDes);
        for (int occurrence : nombreOccurences) {
            if (occurrence >= 2) {
                return true;
            }
        }
        return false;
    }

    /**
     * Permet de savoir si l'on a fait une double paire.
     *
     * @param listeDes prend en compte la liste de dés lancé.
     * @return vrai ou faux selon les lancer de dés.
     */
    private static boolean deuxPaires(int[] listeDes) {
        int[] nombreOccurences = nombreOccurences(listeDes);
        int compteur = 0;
        for (int occurrence : nombreOccurences) {
            if (occurrence >= 2) {
                compteur++;
            }
        }
        return compteur >= 2;
    }

    /**
     * Permet de savoir si l'on a fait un brelan.
     *
     * @param listeDes prend en compte la liste de dés lancé.
     * @return vrai ou faux selon les lancer de dés.
     */
    private static boolean brelan(int[] listeDes) {
        int[] nombreOccurences = nombreOccurences(listeDes);
        for (int occurrence : nombreOccurences) {
            if (occurrence >= 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * Permet de savoir si l'on a fait un carré.
     *
     * @param listeDes prend en compte la liste de dés lancé.
     * @return vrai ou faux selon les lancer de dés.
     */
    private static boolean carre(int[] listeDes) {
        int[] nombreOccurences = nombreOccurences(listeDes);
        for (int occurrence : nombreOccurences) {
            if (occurrence >= 4) {
                return true;
            }
        }
        return false;
    }

    /**
     * Permet de savoir si l'on a fait un Full House.
     *
     * @param listeDes prend en compte la liste de dés lancé.
     * @return vrai ou faux selon les lancer de dés.
     */
    private static boolean fullHouse(int[] listeDes) {
        int[] nombreOccurences = nombreOccurences(listeDes);
        boolean aDeux = false;
        boolean aTrois = false;
        for (int occurrence : nombreOccurences) {
            if (occurrence == 2) {
                aDeux = true;
            }
            if (occurrence == 3) {
                aTrois = true;
            }
        }
        return aDeux && aTrois;
    }

    /**
     * Permet de savoir si l'on a fait une Petite suite.
     *
     * @param listeDes prend en compte la liste de dés lancé.
     * @return vrai ou faux selon les lancer de dés.
     */
    private static boolean petiteSuite(int[] listeDes) {
        boolean[] present = new boolean[6];
        for (int de : listeDes) {
            present[de - 1] = true;
        }
        return (present[0] && present[1] && present[2] && present[3]) ||
                (present[1] && present[2] && present[3] && present[4]) ||
                (present[2] && present[3] && present[4] && present[5]);
    }

    /**
     * Permet de savoir si l'on a fait une Grande suite.
     *
     * @param listeDes prend en compte la liste de dés lancé.
     * @return vrai ou faux selon les lancer de dés.
     */
    private static boolean grandeSuite(int[] listeDes) {
        boolean[] present = new boolean[6];
        for (int de : listeDes) {
            present[de - 1] = true;
        }
        // suite 1-2-3-4-5 ou 2-3-4-5-6
        return (present[0] && present[1] && present[2] && present[3] && present[4]) ||
                (present[1] && present[2] && present[3] && present[4] && present[5]);
    }

    /**
     * Permet de savoir si l'on a fait un Yahtzee.
     *
     * @param listeDes prend en compte la liste de dés lancé.
     * @return vrai ou faux selon les lancer de dés.
     */
    private static boolean yahtzee(int[] listeDes) {
        int[] nombreOccurences = nombreOccurences(listeDes);
        for (int occurrence : nombreOccurences) {
            if (occurrence == 5) {
                return true;
            }
        }
        return false;
    }

    /**
     * Enumeration pour les nom de combinaison.
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

    /**
     * Permet de compter le spoint selon les dés affiché.
     *
     * @param combinaison permet de savoir le nom de la combinaison.
     * @param des         permet de reprendre la liste de combinaison des dés.
     * @return les points avec un petit message pour savoir la combinaison.
     */
    private static String score(Combinaison combinaison, int[] des) {
        int point = 0;
        return switch (combinaison) {
            case UNE_PAIRE -> {
                if (unePaire(des)) point = 5;
                yield "Une paire : " + point + " pts";
            }
            case DEUX_PAIRES -> {
                if (deuxPaires(des)) point = 15;
                yield "Deux paires : " + point + " pts";
            }
            case BRELAN -> {
                if (brelan(des)) point = 20;
                yield "Brelan : " + point + " pts";
            }
            case CARRE -> {
                if (carre(des)) point = 30;
                yield "Carré : " + point + " pts";
            }
            case FULL_HOUSE -> {
                if (fullHouse(des)) point = 25;
                yield "Full house : " + point + " pts";
            }
            case PETITE_SUITE -> {
                if (petiteSuite(des)) point = 30;
                yield "Petite suite : " + point + " pts";
            }
            case GRANDE_SUITE -> {
                if (grandeSuite(des)) point = 40;
                yield "Grande suite : " + point + " pts";
            }
            case YAHTZEE -> {
                if (yahtzee(des)) point = 50;
                yield "Yahtzee : " + point + " pts";
            }
        };
    }


    public static void main(String[] args) {
        while (true) { // Boucle infinie ajoutée
            int[] des = lancerPlusieursDe();
            afficherDe(des);
            Scanner combinaison = new Scanner(System.in);
            boolean[] combinaisonsUtilisees = new boolean[Combinaison.values().length];

            int tour = 1;
            while (true) {
                int[] relance = demandeRelancementDe();
                if (relance.length == 0) {
                    break;
                }
                relancerDe(des, relance);
                System.out.println("\nJet après relance " + tour + " :");
                afficherDe(des);
                tour++;

                //   //     for (int tour = 1; tour <= 2; tour++) {
                //            int[] relance = demandeRelancementDe();
                //            if (relance.length == 0) {
                //                break;
                //            }
                //            relancerDe(des, relance);
                //            System.out.println("\nJet après relance " + tour + " :");
                //            afficherDe(des);
                //        }

                while (true) {
                    System.out.println("\nJet final :");
                    afficherDe(des);
                    int[] occurences = nombreOccurences(des);
                    System.out.println("\nFace de la même valeur :");
                    for (int i = 0; i < occurences.length; i++) {
                        System.out.println("Nombre " + (i + 1) + " : " + occurences[i] + " fois");
                    }
                    System.out.println("\nScore des combinaisons :");
                    //int compteur = 1;
                    for (int i = 0; i < Combinaison.values().length; i++) {
                        if (!combinaisonsUtilisees[i]) {
                            Combinaison combinaisonScore = Combinaison.values()[i];
                            System.out.println((i + 1) + ". " + score(combinaisonScore, des));
                        }
                    }
                    System.out.println("Saisir le numéro de la combinaison désiré : ");
                    int choix = combinaison.nextInt();  // L'utilisateur entre un numéro entre 1 et 8
                    if (choix >= 1 && choix <= Combinaison.values().length && !combinaisonsUtilisees[choix - 1]) {
                        combinaisonsUtilisees[choix - 1] = true;
                        System.out.println("Vous avez sélectionné : " + score(Combinaison.values()[choix - 1], des));
                        break; // Fin du tour
                    } else {
                        System.out.println("Choix invalide ou déjà utilisé. Veuillez réessayer.");
                    }

                    combinaison = new Scanner(System.in);
                    combinaison.nextLine();
                }
            }
        }
    }
}
