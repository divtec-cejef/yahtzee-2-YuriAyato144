package classe_Yahtzee;

import java.util.List;

public class Game {
    private Player player;
    private ConsoleIO consoleIO;

    public Game(ConsoleIO consoleIO) {
        this.consoleIO = consoleIO;
        this.player = new Player("Joueur 1");
    }

    public void jouerPartie() {
        // Une partie complète comprend 5 manches selon les contraintes
        for (int manche = 1; manche <= 5; manche++) {
            consoleIO.afficherMessage("\nManche " + manche + "\n");
            jouerManche();

            consoleIO.afficherMessage("Score total après manche " + manche + ": " +
                    player.getScorecard().getScoreTotal() + " pts");
        }

        consoleIO.afficherMessage("\nFin de la partie");
        consoleIO.afficherFeuilleScore(player.getScorecard());
    }

    private void jouerManche() {
        Round round = new Round();

        // Premier lancer
        round.lancerTousDes();
        consoleIO.afficherMessage("Premier lancer:");
        consoleIO.afficherDes(round.getDiceHand());

        // Jusqu'à 2 relances supplémentaires (3 lancers max total)
        for (int relance = 1; relance <= 2; relance++) {
            int[] desARelancer = consoleIO.demanderRelancement();
            if (desARelancer.length == 0) {
                break; // Le joueur ne veut plus relancer
            }

            round.relancerDes(desARelancer);
            consoleIO.afficherMessage("Lancer " + (relance + 1) + ":");
            consoleIO.afficherDes(round.getDiceHand());
        }

        // Afficher les scores possibles et demander le choix
        List<Category> categoriesDisponibles = player.getScorecard().getCategoriesDisponibles();

        if (categoriesDisponibles.isEmpty()) {
            consoleIO.afficherMessage("Toutes les catégories ont été utilisées!");
            return;
        }

        consoleIO.afficherScoresPossibles(round.getDiceHand(), categoriesDisponibles);
        Category categorieChoisie = consoleIO.demanderCategorie(categoriesDisponibles);

        // Calculer et enregistrer le score
        int score = categorieChoisie.score(round.getDiceHand());
        player.getScorecard().enregistrerScore(categorieChoisie, score);

        consoleIO.afficherMessage("Score obtenu: " + score + " pts pour " + categorieChoisie.getNom());
    }
}