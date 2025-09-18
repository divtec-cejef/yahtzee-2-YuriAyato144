package classe_Yahtzee;

public class ScoreEntry {
    private Category category;
    private int score;
    private boolean used;

    public ScoreEntry(Category category) {
        this.category = category;
        this.score = 0;
        this.used = false;
    }

    public void setScore(int score) {
        this.score = score;
        this.used = true;
    }

    public Category getCategory() {
        return category;
    }

    public int getScore() {
        return score;
    }

    public boolean isUsed() {
        return used;
    }
}