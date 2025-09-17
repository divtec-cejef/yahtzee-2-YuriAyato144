package classe_Yahtzee;

public enum Category {
    UNE_PAIRE,
    DEUX_PAIRES,
    BRELAN,
    CARRE,
    FULL_HOUSE,
    PETITE_SUITE,
    GRANDE_SUITE,
    YAHTZEE;

    private static final Scorecard scoreCard = new Scorecard();

    public void afficherCombinaisonsDisponibles(int[] des, boolean[] combinaisonsUtilisees) {
        System.out.println("\nCombinaisons disponibles :");
        int numeroAffichage = 1;
        for (int i = 0; i < Category.values().length; i++) {
            if (!combinaisonsUtilisees[i]) {
                System.out.println(numeroAffichage + ". " + scoreCard.score(Category.values()[i], des));
                numeroAffichage++;
            }
        }
    }
}
