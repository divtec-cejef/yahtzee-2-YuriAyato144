package classe_Yahtzee;

public class ConsoleIO {
    Round round = new Round();

    public void demandeRelancement() {
        int[] des = round.lancerPlusieursDe(); // Lancer 5 dés au début
        System.out.println("Quels dés voulez-vous relancer ? (0 pour finir)");


        for (int i = 0; i < 2; i++) { // 2 relances possibles
            int[] relancerIndexes = round.demandeRelancementDe();
            if (relancerIndexes.length == 0) {
                System.out.print("Jet " + (round.afficher + 1) + " : " + round.listeDe[round.afficher] + "\n");
            }
            round.relancerDe(des, relancerIndexes);
            round.afficherDe(des);
        }
    }
}
