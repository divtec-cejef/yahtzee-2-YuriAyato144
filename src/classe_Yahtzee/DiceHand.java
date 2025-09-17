package classe_Yahtzee;

public class DiceHand {
    private int[] nombreOccurences;

    public int[] nombreOccurences(int[] listeDes) {
        for (int nombre : listeDes) {
            nombreOccurences[nombre - 1]++;
        }
        return nombreOccurences;
    }

    public boolean unePaire(int[] listeDes) {
        int[] nombreOccurences = nombreOccurences(listeDes);
        for (int occurrence : nombreOccurences) {
            if (occurrence >= 2) {
                return true;
            }
        }
        return false;
    }

    public boolean deuxPaires(int[] listeDes) {
        int[] nombreOccurences = nombreOccurences(listeDes);
        int compteur = 0;
        for (int occurrence : nombreOccurences) {
            if (occurrence >= 2) {
                compteur++;
            }
        }
        return compteur >= 2;
    }

    public int brelan(int[] listeDes) {
        int[] nombreOccurences = nombreOccurences(listeDes);
        for (int i = 0; i < nombreOccurences.length; i++) {
            if (nombreOccurences[i] >= 3) {
                return (i + 1) * 3;
            }
        }
        return 0;
    }

    public int carre(int[] listeDes) {
        int[] nombreOccurences = nombreOccurences(listeDes);
        for (int i = 0; i < nombreOccurences.length; i++) {
            if (nombreOccurences[i] >= 4) {
                return (i + 1) * 4;
            }
        }
        return 0;
    }

    public boolean fullHouse(int[] listeDes) {
        int[] nombreOccurences = nombreOccurences(listeDes);
        boolean aDeux = false;
        boolean aTrois = false;
        for (int occurrence : nombreOccurences) {
            if (occurrence == 2) {
                aDeux = true;
            }
            if (occurrence == 3) {
                aTrois = true;
            }
        }
        return aDeux && aTrois;
    }

    public boolean petiteSuite(int[] listeDes) {
        boolean[] present = new boolean[6];
        for (int de : listeDes) {
            present[de - 1] = true;
        }
        return (present[0] && present[1] && present[2] && present[3]) ||
                (present[1] && present[2] && present[3] && present[4]) ||
                (present[2] && present[3] && present[4] && present[5]);
    }

    public boolean grandeSuite(int[] listeDes) {
        boolean[] present = new boolean[6];
        for (int de : listeDes) {
            present[de - 1] = true;
        }
        return (present[0] && present[1] && present[2] && present[3] && present[4]) ||
                (present[1] && present[2] && present[3] && present[4] && present[5]);
    }

    public boolean yahtzee(int[] listeDes) {
        int[] nombreOccurences = nombreOccurences(listeDes);
        for (int occurrence : nombreOccurences) {
            if (occurrence == 5) {
                return true;
            }
        }
        return false;
    }
}