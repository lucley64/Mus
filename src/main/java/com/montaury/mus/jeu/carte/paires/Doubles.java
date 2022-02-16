package com.montaury.mus.jeu.carte.paires;

public class Doubles extends Paires {

    public static final int POINTS_BONUS = 3;
    private final PaireSimple meilleurePaire;
    private final PaireSimple moinsBonnePaire;

    public Doubles(PaireSimple paire1, PaireSimple paire2) {
        super(POINTS_BONUS);
        this.meilleurePaire = paire1.estMeilleureQue(paire2) ? paire1 : paire2;
        this.moinsBonnePaire = paire1.estMeilleureQue(paire2) ? paire2 : paire1;
    }

    @Override
    public boolean estMeilleureOuEgaleA(Paires autresPaires) {
        if (!(autresPaires instanceof Doubles)) {
            return true;
        }
        var autresDoubles = (Doubles) autresPaires;
        return meilleurePaire.estMeilleureQue(autresDoubles.meilleurePaire) ||
                meilleurePaire.estIdentiqueA(autresDoubles.meilleurePaire) &&
                        moinsBonnePaire.estMeilleureOuEgaleA(autresDoubles.moinsBonnePaire);
    }
}
