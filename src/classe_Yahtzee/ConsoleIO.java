package classe_Yahtzee;

import java.util.List;
import java.util.Scanner;

public class ConsoleIO {
    private Scanner scanner;

    public ConsoleIO() {
        this.scanner = new Scanner(System.in);
    }

    public void afficherDes(DiceHand diceHand) {
        int[] values = diceHand.getValues();
        for (int i = 0; i < values.length; i++) {
            System.out.print("jet " +(i + 1) + " : " + values[i] + "\n");
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
        int[] indices = new int[parties.length];

        for (int i = 0; i < parties.length; i++) {
            try {
                int numero = Integer.parseInt(parties[i]);
                if (numero >= 1 && numero <= 5) {
                    indices[i] = numero - 1; // Convertir en index (0-4)
                } else {
                    System.out.println("Numéro invalide ignoré: " + numero);
                    indices[i] = -1; // Marquer comme invalide
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide ignorée: " + parties[i]);
                indices[i] = -1; // Marquer comme invalide
            }
        }

        return indices;
    }

    public void afficherScoresPossibles(DiceHand diceHand, List<Category> categoriesDisponibles) {
        System.out.println("\nScores possibles:");
        for (int i = 0; i < categoriesDisponibles.size(); i++) {
            Category categorie = categoriesDisponibles.get(i);
            int score = categorie.score(diceHand);
            System.out.println((i + 1) + ". " + categorie.getNom() + ": " + score + " pts");
        }
    }

    public Category demanderCategorie(List<Category> categoriesDisponibles) {
        System.out.println("Choisissez une catégorie ou appuyez sur Entrée pour la meilleure : ");
        String choix = scanner.nextLine().trim();

        if (choix.isEmpty()) {
            // Retourner la catégorie avec le meilleur score
            return trouverMeilleureCategorie(categoriesDisponibles);
        }

        try {
            int index = Integer.parseInt(choix) - 1;
            if (index >= 0 && index < categoriesDisponibles.size()) {
                return categoriesDisponibles.get(index);
            } else {
                System.out.println("Choix invalide, sélection automatique de la meilleure catégorie.");
                return trouverMeilleureCategorie(categoriesDisponibles);
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide, sélection automatique de la meilleure catégorie.");
            return trouverMeilleureCategorie(categoriesDisponibles);
        }
    }

    private Category trouverMeilleureCategorie(List<Category> categories) {
        // Pour cette implémentation simple, on retourne la première catégorie
        // Dans une version plus sophistiquée, on calculerait le meilleur score
        return categories.get(0);
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