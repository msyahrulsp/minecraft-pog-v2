package com.aetherwars.controller;

import com.aetherwars.model.Phase;

public class PhaseController {
    public static Phase currentPhase = Phase.DRAW;

    public static void setCurPhase(Phase phase) {
        currentPhase = phase;
    }
}
