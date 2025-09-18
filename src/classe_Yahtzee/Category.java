package classe_Yahtzee;

public enum Category {
    UNE_PAIRE("Une paire"),
    DEUX_PAIRES("Deux paires"),
    BRELAN("Brelan"),
    CARRE("Carré"),
    FULL_HOUSE("Full House"),
    PETITE_SUITE("Petite suite"),
    GRANDE_SUITE("Grande suite"),
    YAHTZEE("Yahtzee");

    private final String nom;

    Category(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public int score(DiceHand hand) {
        return switch (this) {
            case UNE_PAIRE -> hand.unePaire() ? 5 : 0;
            case DEUX_PAIRES -> hand.deuxPaires() ? 10 : 0;
            case BRELAN -> hand.brelan() ? hand.sommeDes() : 0;
            case CARRE -> hand.carre() ? hand.sommeDes() : 0;
            case FULL_HOUSE -> hand.fullHouse() ? 25 : 0;
            case PETITE_SUITE -> hand.petiteSuite() ? 30 : 0;
            case GRANDE_SUITE -> hand.grandeSuite() ? 40 : 0;
            case YAHTZEE -> hand.yahtzee() ? 50 : 0;
        };
    }
}