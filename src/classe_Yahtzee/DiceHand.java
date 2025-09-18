package classe_Yahtzee;

public class DiceHand {
    private Die[] dice;

    public DiceHand() {
        dice = new Die[5];
        for (int i = 0; i < 5; i++) {
            dice[i] = new Die();
        }
    }

    public void rollAll() {
        for (Die die : dice) {
            die.roll();
        }
    }

    public void rollSpecific(int[] indices) {
        for (int index : indices) {
            if (index >= 0 && index < dice.length) {
                dice[index].roll();
            }
        }
    }

    public int[] getValues() {
        int[] values = new int[5];
        for (int i = 0; i < 5; i++) {
            values[i] = dice[i].getValue();
        }
        return values;
    }

    public int[] compterOccurrences() {
        int[] occurrences = new int[6]; // Index 0-5 pour faces 1-6
        int[] values = getValues();
        for (int value : values) {
            occurrences[value - 1]++;
        }
        return occurrences;
    }

    public boolean unePaire() {
        int[] occurrences = compterOccurrences();
        for (int occurrence : occurrences) {
            if (occurrence >= 2) {
                return true;
            }
        }
        return false;
    }

    public boolean deuxPaires() {
        int[] occurrences = compterOccurrences();
        int pairesCount = 0;
        for (int occurrence : occurrences) {
            if (occurrence >= 2) {
                pairesCount++;
            }
        }
        return pairesCount >= 2;
    }

    public boolean brelan() {
        int[] occurrences = compterOccurrences();
        for (int occurrence : occurrences) {
            if (occurrence >= 3) {
                return true;
            }
        }
        return false;
    }

    public boolean carre() {
        int[] occurrences = compterOccurrences();
        for (int occurrence : occurrences) {
            if (occurrence >= 4) {
                return true;
            }
        }
        return false;
    }

    public boolean fullHouse() {
        int[] occurrences = compterOccurrences();
        boolean aDeux = false;
        boolean aTrois = false;
        for (int occurrence : occurrences) {
            if (occurrence == 2) aDeux = true;
            if (occurrence == 3) aTrois = true;
        }
        return aDeux && aTrois;
    }

    public boolean petiteSuite() {
        boolean[] present = new boolean[6];
        int[] values = getValues();
        for (int value : values) {
            present[value - 1] = true;
        }
        return (present[0] && present[1] && present[2] && present[3]) ||
                (present[1] && present[2] && present[3] && present[4]) ||
                (present[2] && present[3] && present[4] && present[5]);
    }

    public boolean grandeSuite() {
        boolean[] present = new boolean[6];
        int[] values = getValues();
        for (int value : values) {
            present[value - 1] = true;
        }
        return (present[0] && present[1] && present[2] && present[3] && present[4]) ||
                (present[1] && present[2] && present[3] && present[4] && present[5]);
    }

    public boolean yahtzee() {
        int[] occurrences = compterOccurrences();
        for (int occurrence : occurrences) {
            if (occurrence == 5) {
                return true;
            }
        }
        return false;
    }

    public int sommeDes() {
        int somme = 0;
        for (int value : getValues()) {
            somme += value;
        }
        return somme;
    }
}