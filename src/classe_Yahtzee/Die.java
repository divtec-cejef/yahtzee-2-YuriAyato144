package classe_Yahtzee;

public class Die {
    private int value;

    public Die() {
        roll();
    }

    public void roll() {
        this.value = (int) (Math.random() * 6) + 1;
    }

    public int getValue() {
        return value;
    }
}