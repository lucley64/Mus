package com.montaury.mus.jeu.tour.phases.dialogue.choix;

import com.montaury.mus.jeu.tour.phases.dialogue.Dialogue;

public abstract class Mise extends Choix {
    protected Mise(TypeChoix type, int mise) {
        super(type, mise);
    }

    @Override
    public Dialogue.Deroulement influerSur(Dialogue.Deroulement deroulement) {
        return !deroulement.plusQUnParticipant() ? deroulement.basculerSurAdversaire(prochainsChoixPossibles()) : deroulement;
//        return deroulement.basculerSurAdversaire(prochainsChoixPossibles());
    }
}
