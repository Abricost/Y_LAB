package org.example.service;

import org.example.repository.IndicatorsRepository;

public class IndicatorsService {

    private final IndicatorsRepository indicatorsRepository;

    public IndicatorsService(IndicatorsRepository indicatorsRepository) {
        this.indicatorsRepository = indicatorsRepository;
    }

    public void createIndicator(String newIndicator) {

    }
}
