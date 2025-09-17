/*
Projet : jeu du Yahtzee
Auteur: Aedan Bélet
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
    private static int brelan(int[] listeDes) {
        int[] nombreOccurences = nombreOccurences(listeDes);
        for (int i = 0; i < nombreOccurences.length; i++) {
            if (nombreOccurences[i] >= 3) {
                return (i + 1) * 3;
            }
        }
        return 0;
    }

    /**
     * Permet de savoir si l'on a fait un carré.
     *
     * @param listeDes prend en compte la liste de dés lancé.
     * @return vrai ou faux selon les lancer de dés.
     */
    private static int carre(int[] listeDes) {
        int[] nombreOccurences = nombreOccurences(listeDes);
        for (int i = 0; i < nombreOccurences.length; i++) {
            if (nombreOccurences[i] >= 4) {
                return (i + 1) * 4;
            }
        }
        return 0;
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
        int[] memeFace = nombreOccurences(des);
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
                point = brelan(des);
                yield "Brelan : " + point + " pts";
            }
            case CARRE -> {
                point = carre(des);
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

    /**
     * Affiche uniquement les combinaisons encore disponibles avec numérotation correcte.
     *
     * @param des                   liste des dés.
     * @param combinaisonsUtilisees tableau pour savoir quelles combinaisons à été utiliser ou non.
     */
    private static void afficherCombinaisonsDisponibles(int[] des, boolean[] combinaisonsUtilisees) {
        System.out.println("\nCombinisons disponibles :");
        int numeroAffichage = 1;
        for (int i = 0; i < Combinaison.values().length; i++) {
            if (!combinaisonsUtilisees[i]) {
                Combinaison combinaisonScore = Combinaison.values()[i];
                System.out.println(numeroAffichage + ". " + score(combinaisonScore, des));
                numeroAffichage++;
            }
        }
    }

    /**
     * Convertit le numéro choisi par l'utilisateur en index réel de combinaison
     *
     * @param choixUtilisateur      numéro saisi par l'utilisateur
     * @param combinaisonsUtilisees Tableau indiquant quelles combinaison ont déjà été utilisées
     * @return le numéro saisi par l'utilisateur
     */
    private static int obtenirIndexCombinaison(int choixUtilisateur, boolean[] combinaisonsUtilisees) {
        int compteur = 0;
        for (int i = 0; i < combinaisonsUtilisees.length; i++) {
            if (!combinaisonsUtilisees[i]) {
                compteur++;
                if (compteur == choixUtilisateur) {
                    return i;
                }
            }
        }
        return -1; // Choix invalide
    }

    /**
     * Gère une manche complète avec les relances de dés.
     *
     * @return le tableau des dés finaux après les relances.
     */
    private static int[] jouerManche() {
        // Lancer initial des dés
        int[] des = lancerPlusieursDe();
        System.out.println("Lancer initial :");
        afficherDe(des);
        // Permettre jusqu'à 2 relances
        for (int tour = 1; tour <= 2; tour++) {
            int[] relance = demandeRelancementDe();
            if (relance.length == 0) {
                break; // Le joueur ne veut pas relancer
            }
            relancerDe(des, relance);
            System.out.println("\nJet après relance " + tour + " :");
            afficherDe(des);
        }
        return des;
    }

    /**
     * Vérifie s'il reste des combinaisons disponibles.
     *
     * @param combinaisonsUtilisees tableau indiquant les combinaisons déjà utilisées.
     * @return vrai s'il reste au moins une combinaison disponible.
     */
    private static boolean resteCombinaisonsDisponibles(boolean[] combinaisonsUtilisees) {
        for (boolean utilisee : combinaisonsUtilisees) {
            if (!utilisee) {
                return true;
            }
        }
        return false;
    }

    /**
     * Demande à l'utilisateur de choisir une combinaison parmi celles disponibles.
     *
     * @param des                   tableau des dés pour calculer les scores.
     * @param combinaisonsUtilisees tableau indiquant les combinaisons déjà utilisées.
     * @param scanner               scanner pour lire l'entrée utilisateur.
     * @return l'index réel de la combinaison choisie.
     */
    private static int choisirCombinaison(int[] des, boolean[] combinaisonsUtilisees, Scanner scanner) {
        // Afficher uniquement les combinaisons disponibles
        afficherCombinaisonsDisponibles(des, combinaisonsUtilisees);
        // Demander le choix de l'utilisateur avec validation
        int choix;
        int indexReel = -1;
        while (indexReel == -1) {
            System.out.print("Saisir le numéro de la combinaison désirée : ");
            try {
                choix = scanner.nextInt();
                indexReel = obtenirIndexCombinaison(choix, combinaisonsUtilisees);

                if (indexReel == -1) {
                    System.out.println("Choix invalide ! Veuillez choisir un numéro de la liste.");
                }
            } catch (Exception e) {
                System.out.println("Veuillez saisir un nombre valide.");
                scanner.nextLine(); // nettoyer le buffer
            }
        }
        return indexReel;
    }

    /**
     * Extrait les points d'une chaîne de score au format "Nom : X pts".
     *
     * @param resultatScore chaîne contenant le score formaté.
     * @return le nombre de points extraits.
     */
    private static int extrairePoints(String resultatScore) {
        int pointsGagnes = 0;
        String[] parties = resultatScore.split(" : ");
        if (parties.length > 1) {
            String pointsStr = parties[1].replace(" pts", "");
            pointsGagnes = Integer.parseInt(pointsStr);
        }
        return pointsGagnes;
    }

    /**
     * Affiche le résumé d'une manche avec les points gagnés et le score total.
     *
     * @param scoreTotal  score total actuel.
     * @param dernierTour permet de savoir si c'est le dernier tour ou non.
     */
    private static void resumeManche(int scoreTotal, int dernierTour) {
        if (dernierTour > 4) {
            System.out.println("\nScore total : " + scoreTotal + " points");
        } else {
            System.out.println("\nScore actuel : " + scoreTotal + " points");
        }
    }

    /**
     * Compte le nombre de combinaisons encore disponibles.
     *
     * @param combinaisonsUtilisees tableau indiquant les combinaisons déjà utilisées.
     * @return le nombre de combinaisons restantes.
     */
    private static int compterCombinaisonsRestantes(boolean[] combinaisonsUtilisees) {
        int combinaisonsRestantes = 0;
        for (boolean utilisee : combinaisonsUtilisees) {
            if (!utilisee) combinaisonsRestantes++;
        }
        return combinaisonsRestantes;
    }

    /**
     * Affiche le message de fin de partie avec le score final.
     *
     * @param scoreTotal score final du joueur.
     */
    private static void afficherFinPartie(int scoreTotal) {
        System.out.println("\nToutes les combinaisons ont été utilisées !");
        System.out.println("Score total final : " + scoreTotal + " points");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean[] combinaisonsUtilisees = new boolean[Combinaison.values().length];
        int scoreTotal = 0;
        int manche = 1;
        // Boucle "infini"
        do {
            System.out.println("\nManche " + manche + "\n");
            // Jouer une manche complète
            int[] des = jouerManche();
            // Vérifie s'il reste des combinaisons disponibles
            if (!resteCombinaisonsDisponibles(combinaisonsUtilisees)) {
                afficherFinPartie(scoreTotal);
                break;
            }
            // Choisir la combinaison
            int indexReel = choisirCombinaison(des, combinaisonsUtilisees, scanner);
            // Marquer la combinaison comme utilisée et calculer les points
            combinaisonsUtilisees[indexReel] = true;
            Combinaison combinaisonChoisie = Combinaison.values()[indexReel];
            String resultatScore = score(combinaisonChoisie, des);
            // Calculer et afficher les résultats
            int pointsGagnes = extrairePoints(resultatScore);
            scoreTotal += pointsGagnes;
            int combinaisonsRestantes = compterCombinaisonsRestantes(combinaisonsUtilisees);
            int dernierTour = manche;
            resumeManche(scoreTotal, dernierTour);
            manche++;
            System.out.println();
        } while (manche <= 5);
    }
}