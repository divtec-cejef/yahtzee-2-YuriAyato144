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

    public int score(DiceHand diceHand) {
        return switch (this) {
            case UNE_PAIRE -> {
                int point = 0;
                if (diceHand.unePaire()) point = 5;
                yield point;
            }
            case DEUX_PAIRES -> {
                int point = 0;
                if (diceHand.deuxPaires()) point = 10;
                yield point;
            }
            case BRELAN -> {
                int point = 0;
                yield diceHand.brelan();
            }
            case CARRE -> {
                int point = 0;
                yield diceHand.carre();
            }
            case FULL_HOUSE -> {
                int point = 0;
                if (diceHand.fullHouse()) point = 25;
                yield point;
            }
            case PETITE_SUITE -> {
                int point = 0;
                if (diceHand.petiteSuite()) point = 30;
                yield point;
            }
            case GRANDE_SUITE -> {
                int point = 0;
                if (diceHand.grandeSuite()) point = 40;
                yield point;
            }
            case YAHTZEE -> {
                int point = 0;
                if (diceHand.yahtzee()) point = 50;
                yield point;
            }
        };
    }
}