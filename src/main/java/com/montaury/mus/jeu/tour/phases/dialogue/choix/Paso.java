package com.montaury.mus.jeu.tour.phases.dialogue.choix;

import com.montaury.mus.jeu.tour.phases.dialogue.Dialogue;

public class Paso extends Choix {
  public Paso() {
    super(TypeChoix.PASO);
  }

  @Override
  public Dialogue.Deroulement influerSur(Dialogue.Deroulement deroulement) {
    return deroulement;
  }
}
