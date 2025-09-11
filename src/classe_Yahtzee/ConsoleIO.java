package classe_Yahtzee;

public class ConsoleIO {
    Round round = new Round();

    public void demandeRelancement() {
        int[] des = round.lancerPlusieursDe(); // Lancer 5 dés au début
        round.afficherDe(des); // Afficher les dés

        for (int i = 0; i < 2; i++) { // 2 relances possibles
            int[] relancerIndexes = round.demandeRelancementDe();
            if (relancerIndexes.length == 0) {
                break;
            }
            round.relancerDe(des, relancerIndexes);
            round.afficherDe(des);
        }
    }
}
