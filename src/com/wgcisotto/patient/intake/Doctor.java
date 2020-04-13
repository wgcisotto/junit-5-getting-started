package com.wgcisotto.patient.intake;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Doctor {

    avery("Ralph Avery"),
    johnson("Beth Johnson"),
    murphy("Pat Murphy");

    @Getter
    private String name;

}
