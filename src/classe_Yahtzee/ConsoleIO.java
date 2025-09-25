package classe_Yahtzee;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class ConsoleIO {
    private final Scanner scanner;

    public ConsoleIO() {
        this.scanner = new Scanner(System.in);
    }

    public void afficherDes(DiceHand diceHand) {
        int[] values = diceHand.getValues();
        for (int i = 0; i < values.length; i++) {
            System.out.print("jet " + (i + 1) + " : " + values[i] + "\n");
        }
        System.out.println();
    }

    public int[] demanderRelancement() {
        System.out.println("Quels dés voulez-vous relancer ? (0 pour finir)");
        String ligne = scanner.nextLine().trim();
        if (ligne.isEmpty()) {
            return new int[0]; // Aucun dé à relancer
        }
        String[] parties = ligne.split("\\s+");
        List<Integer> indicesValides = new ArrayList<>();
        for (String partie : parties) {
            if (estNombreValide(partie)) {
                int numero = convertirEnNombre(partie);
                if (estDansPlage(numero, 1, 5)) {
                    indicesValides.add(numero - 1);
                } else {
                    System.out.println("Numéro invalide ignoré: " + numero);
                }
            } else {
                System.out.println("Entrée invalide ignorée: " + partie);
            }
        }
        return indicesValides.stream().mapToInt(Integer::intValue).toArray();
    }

    public void afficherScoresPossibles(DiceHand diceHand, List<Category> categoriesDisponibles) {
        System.out.println("\nScores possibles:");
        for (int i = 0; i < categoriesDisponibles.size(); i++) {
            Category categorie = categoriesDisponibles.get(i);
            int score = categorie.score(diceHand);
            System.out.println((i + 1) + ". " + categorie.getNom() + ": " + score + " pts");
        }
    }

    public Category demanderCategorie(List<Category> categoriesDisponibles, DiceHand diceHand) {
        System.out.println("Choisissez une catégorie ou appuyez sur 0 pour la meilleure : ");
        String choix = scanner.nextLine().trim();
        // Si l'entrée est égale à 0, choix automatique de la meilleure catégorie
        if (choix.equals("0")) {
            return trouverMeilleureCategorie(categoriesDisponibles, diceHand);
        }
        if (estNombreValide(choix)) {
            int index = convertirEnNombre(choix) - 1;
            if (estDansPlage(index, 0, categoriesDisponibles.size() - 1)) {
                return categoriesDisponibles.get(index);
            } else {
                System.out.println("Choix invalide, sélection automatique de la meilleure catégorie.");
                return trouverMeilleureCategorie(categoriesDisponibles, diceHand);
            }
        } else {
            System.out.println("Entrée invalide, sélection automatique de la meilleure catégorie.");
            return trouverMeilleureCategorie(categoriesDisponibles, diceHand);
        }
    }

    private boolean estNombreValide(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        // Vérifier si tous les caractères sont des chiffres
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private int convertirEnNombre(String str) {
        int resultat = 0;
        for (char c : str.toCharArray()) {
            resultat = resultat * 10 + (c - '0');
        }
        return resultat;
    }

    private boolean estDansPlage(int valeur, int min, int max) {
        return valeur >= min && valeur <= max;
    }

    private Category trouverMeilleureCategorie(List<Category> categories, DiceHand diceHand) {
        Category meilleureCategorie = categories.get(0);
        int meilleurScore = meilleureCategorie.score(diceHand);

        for (Category categorie : categories) {
            int scoreActuel = categorie.score(diceHand);
            if (scoreActuel > meilleurScore) {
                meilleurScore = scoreActuel;
                meilleureCategorie = categorie;
            }
        }
        return meilleureCategorie;
    }

    public void afficherFeuilleScore(Scorecard scorecard) {
        System.out.println("\n Feuille de score : ");
        for (ScoreEntry entry : scorecard.getEntries()) {
            if (entry.isUsed()) {
                System.out.println(entry.getCategory().getNom() + ": " + entry.getScore() + " pts");
            } else {
                System.out.println(entry.getCategory().getNom() + ": - pts");
            }
        }
        System.out.println("TOTAL: " + scorecard.getScoreTotal() + " pts");
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }
}