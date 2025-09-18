package classe_Yahtzee;

import java.util.ArrayList;
import java.util.List;

public class Scorecard {
    private List<ScoreEntry> entries;

    public Scorecard() {
        entries = new ArrayList<>();
        for (Category category : Category.values()) {
            entries.add(new ScoreEntry(category));
        }
    }

    public void enregistrerScore(Category category, int score) {
        for (ScoreEntry entry : entries) {
            if (entry.getCategory() == category && !entry.isUsed()) {
                entry.setScore(score);
                break;
            }
        }
    }

//    public boolean estCategorieUtilisee(Category category) {
//        for (ScoreEntry entry : entries) {
//            if (entry.getCategory() == category) {
//                return entry.isUsed();
//            }
//        }
//        return false;
//    }

    public List<Category> getCategoriesDisponibles() {
        List<Category> disponibles = new ArrayList<>();
        for (ScoreEntry entry : entries) {
            if (!entry.isUsed()) {
                disponibles.add(entry.getCategory());
            }
        }
        return disponibles;
    }

    public int getScoreTotal() {
        int total = 0;
        for (ScoreEntry entry : entries) {
            if (entry.isUsed()) {
                total += entry.getScore();
            }
        }
        return total;
    }

    public List<ScoreEntry> getEntries() {
        return new ArrayList<>(entries); // Copie défensive
    }
}