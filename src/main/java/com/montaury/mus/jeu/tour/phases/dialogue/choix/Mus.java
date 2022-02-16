package com.montaury.mus.jeu.tour.phases.dialogue.choix;

import com.montaury.mus.jeu.tour.phases.dialogue.Dialogue;

public class Mus extends Choix {
    public Mus() {
        super(TypeChoix.MUS);
    }

    @Override
    public Dialogue.Deroulement influerSur(Dialogue.Deroulement deroulement) {
        return null;
    }
}
