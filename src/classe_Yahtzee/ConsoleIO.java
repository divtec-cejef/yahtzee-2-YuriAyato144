package classe_Yahtzee;

public class ConsoleIO {
    Round round = new Round();

    public void demandeRelancement() {
        int[] des = round.lancerPlusieursDe(); // Lancer 5 dés au début
        System.out.println("Jet initial :");
        afficherDe(des);
        for (int i = 0; i < 2; i++) { // 2 relances possibles
            System.out.println("Quels dés voulez-vous relancer ? (0 pour finir)");
            int[] relancerIndexes = round.demandeRelancementDe();
            if (relancerIndexes.length == 0) {
                // Le joueur ne veut plus relancer, on arrête la boucle
                break;
            }
            // Relance uniquement les dés choisis
            round.relancerDe(des, relancerIndexes);
            // Affiche l’état actuel des dés
            System.out.println("\nJet " + (i + 2) + " :");
            afficherDe(des);
        }
    }

    public void afficherDe(int[] listeDe) {
        for (int i = 0; i < listeDe.length; i++) {
            System.out.println("Dé " + (i + 1) + " : " + listeDe[i]);
        }
    }
}
