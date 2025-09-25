package classe_Yahtzee;

public class Player {
    private final String nom;
    private final Scorecard scorecard;

    public Player(String nom) {
        this.nom = nom;
        this.scorecard = new Scorecard();
    }

    public String getNom() {
        return nom;
    }

    public Scorecard getScorecard() {
        return scorecard;
    }
}