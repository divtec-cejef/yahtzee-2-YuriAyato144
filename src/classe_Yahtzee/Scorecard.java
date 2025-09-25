package classe_Yahtzee;

import java.util.ArrayList;
import java.util.List;

public class Scorecard {
    private final List<ScoreEntry> entrer;

    public Scorecard() {
        entrer = new ArrayList<>();
        for (Category category : Category.values()) {
            entrer.add(new ScoreEntry(category));
        }
    }

    public void enregistrerScore(Category category, int score) {
        for (ScoreEntry entry : entrer) {
            if (entry.getCategory() == category && !entry.isUsed()) {
                entry.setScore(score);
                break;
            }
        }
    }

    public List<Category> getCategoriesDisponibles() {
        List<Category> disponibles = new ArrayList<>();
        for (ScoreEntry entry : entrer) {
            if (!entry.isUsed()) {
                disponibles.add(entry.getCategory());
            }
        }
        return disponibles;
    }

    public int getScoreTotal() {
        int total = 0;
        for (ScoreEntry entry : entrer) {
            if (entry.isUsed()) {
                total += entry.getScore();
            }
        }
        return total;
    }

    public List<ScoreEntry> getEntries() {
        return new ArrayList<>(entrer); // Copie défensive
    }
}