package classe_Yahtzee;

public class Round {
    private DiceHand diceHand;

    public Round() {
        this.diceHand = new DiceHand();
    }

    public DiceHand getDiceHand() {
        return diceHand;
    }

    public void lancerTousDes() {
        diceHand.rollAll();
    }

    public void relancerDes(int[] indices) {
        diceHand.rollSpecific(indices);
    }
}