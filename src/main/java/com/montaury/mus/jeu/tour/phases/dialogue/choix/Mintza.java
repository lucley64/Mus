package com.montaury.mus.jeu.tour.phases.dialogue.choix;

import com.montaury.mus.jeu.tour.phases.dialogue.Dialogue;

public class Mintza extends Choix {
    public Mintza() {
        super(TypeChoix.MINTZA);
    }

    @Override
    public Dialogue.Deroulement influerSur(Dialogue.Deroulement deroulement) {
        return null;
    }
}
