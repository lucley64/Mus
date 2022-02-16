package com.montaury.mus.jeu.tour.phases.dialogue.choix;

import com.montaury.mus.jeu.tour.phases.dialogue.Dialogue;

public class Idoki extends Choix {
    public Idoki() {
        super(TypeChoix.IDOKI);
    }

    @Override
    public Dialogue.Deroulement influerSur(Dialogue.Deroulement deroulement) {
        return Dialogue.Deroulement.termine();
    }
}
