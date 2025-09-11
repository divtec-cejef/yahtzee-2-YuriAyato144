package classe_Yahtzee;

public enum Category {
    UNE_PAIRE,
    DEUX_PAIRES,
    BRELAN, // Score variable
    CARRE,
    FULL_HOUSE,
    PETITE_SUITE,
    GRANDE_SUITE,
    YAHTZEE;
DiceHand diceHand = new DiceHand();
    public String score(Category combinaison, int[] des) {
        int point = 0;
        int[] memeFace = diceHand.nombreOccurences(des);
        return switch (combinaison) {
            case UNE_PAIRE -> {
                if (diceHand.unePaire(des)) point = 5;
                yield "Une paire : " + point + " pts";
            }
            case DEUX_PAIRES -> {
                if (diceHand.deuxPaires(des)) point = 15;
                yield "Deux paires : " + point + " pts";
            }
            case BRELAN -> {
                point = diceHand.brelan(des);
                yield "Brelan : " + point + " pts";
            }
            case CARRE -> {
                point = diceHand.carre(des);
                yield "Carré : " + point + " pts";
            }
            case FULL_HOUSE -> {
                if (diceHand.fullHouse(des)) point = 25;
                yield "Full house : " + point + " pts";
            }
            case PETITE_SUITE -> {
                if (diceHand.petiteSuite(des)) point = 30;
                yield "Petite suite : " + point + " pts";
            }
            case GRANDE_SUITE -> {
                if (diceHand.grandeSuite(des)) point = 40;
                yield "Grande suite : " + point + " pts";
            }
            case YAHTZEE -> {
                if (diceHand.yahtzee(des)) point = 50;
                yield "Yahtzee : " + point + " pts";
            }
        };
    }

}