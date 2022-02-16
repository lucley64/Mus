package com.montaury.mus.jeu.carte;

public enum ValeurCarte {
    AS(1),
    DEUX(2),
    TROIS(3),
    QUATRE(4),
    CINQ(5),
    SIX(6),
    SEPT(7),
    VALET(10),
    CAVALIER(11, 10),
    ROI(12, 10);

    private final int valeur;
    private final int valeurJeu;

    ValeurCarte(int valeur) {
        this(valeur, valeur);
    }

    ValeurCarte(int valeur, int valeurJeu) {
        this.valeur = valeur;
        this.valeurJeu = valeurJeu;
    }

    public int valeur() {
        return valeur;
    }

    public int valeurPourJeu() {
        return valeurJeu;
    }

    public Comparaison comparerAvec(ValeurCarte autre) {
        if (aUnRangSuperieurA(autre)) {
            return Comparaison.PLUS_GRANDE;
        }
        if (valeur() < autre.valeur()) {
            return Comparaison.PLUS_PETITE;
        }
        return Comparaison.MEME_RANG;
    }

    public boolean aUnRangSuperieurA(ValeurCarte autre) {
        return valeur > autre.valeur;
    }

    public boolean estDeMemeRangQue(ValeurCarte autre) {
        return valeur == autre.valeur;
    }

    public boolean estDeRangSuperieurOuEgalA(ValeurCarte autre) {
        return valeur >= autre.valeur;
    }

    public enum Comparaison {
        MEME_RANG(0), PLUS_GRANDE(1), PLUS_PETITE(-1);

        private final int valeurComparator;

        Comparaison(int valeurComparator) {
            this.valeurComparator = valeurComparator;
        }

        public int valeurComparator() {
            return valeurComparator;
        }
    }
}
