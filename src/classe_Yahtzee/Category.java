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
Round round = new Round();
    private String score(Category combinaison, int[] des) {
        int point = 0;
        int[] memeFace = round.nombreOccurences(des);
        return switch (combinaison) {
            case UNE_PAIRE -> {
                if (unePaire(des)) point = 5;
                yield "Une paire : " + point + " pts";
            }
            case DEUX_PAIRES -> {
                if (deuxPaires(des)) point = 15;
                yield "Deux paires : " + point + " pts";
            }
            case BRELAN -> {
                point = brelan(des);
                yield "Brelan : " + point + " pts";
            }
            case CARRE -> {
                point = carre(des);
                yield "Carré : " + point + " pts";
            }
            case FULL_HOUSE -> {
                if (fullHouse(des)) point = 25;
                yield "Full house : " + point + " pts";
            }
            case PETITE_SUITE -> {
                if (petiteSuite(des)) point = 30;
                yield "Petite suite : " + point + " pts";
            }
            case GRANDE_SUITE -> {
                if (grandeSuite(des)) point = 40;
                yield "Grande suite : " + point + " pts";
            }
            case YAHTZEE -> {
                if (yahtzee(des)) point = 50;
                yield "Yahtzee : " + point + " pts";
            }
        };
    }

}