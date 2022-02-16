package com.montaury.mus.jeu.tour.phases.dialogue.choix;

import com.montaury.mus.jeu.tour.phases.dialogue.Dialogue;

public class Tira extends Choix {
    public Tira() {
        super(TypeChoix.TIRA);
    }

    @Override
    public Dialogue.Deroulement influerSur(Dialogue.Deroulement deroulement) {
        return deroulement.retirerJoueurParlant();
    }
}
