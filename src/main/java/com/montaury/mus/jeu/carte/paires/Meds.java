package com.montaury.mus.jeu.carte.paires;

import com.montaury.mus.jeu.carte.ValeurCarte;

public class Meds extends Paires {

    private final ValeurCarte valeurCarte;

    public Meds(ValeurCarte valeurCarte) {
        super(2);
        this.valeurCarte = valeurCarte;
    }

    @Override
    public boolean estMeilleureOuEgaleA(Paires autresPaires) {
        return autresPaires instanceof PaireSimple ||
                autresPaires instanceof Meds && valeurCarte.estDeRangSuperieurOuEgalA(((Meds) autresPaires).valeurCarte);
    }
}
