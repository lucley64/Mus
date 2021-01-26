package com.montaury.mus.jeu.tour.phases.dialogue.choix;

import com.montaury.mus.jeu.tour.phases.dialogue.Dialogue;

public class Kanta extends Choix {
  public Kanta() {
    super(TypeChoix.KANTA);
  }

  @Override
  public Dialogue.Deroulement influerSur(Dialogue.Deroulement deroulement) {
    return Dialogue.Deroulement.termine();
  }
}
