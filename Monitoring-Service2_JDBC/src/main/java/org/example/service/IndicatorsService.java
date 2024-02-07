package org.example.service;

import org.example.repository.IndicatorsRepository;

import java.sql.ResultSet;
import java.util.List;

public class IndicatorsService {

    private final IndicatorsRepository indicatorsRepository;

    public IndicatorsService(IndicatorsRepository indicatorsRepository) {
        this.indicatorsRepository = indicatorsRepository;
    }

    public void createIndicator(String newIndicator) {

    }

    public List<String> getAllIndicatorsList() {
        return indicatorsRepository.getAllIndicatorsList();
    }

    public boolean isRequiredInputIndicators() {
        return true;
    }

    public boolean isUserCanInputIndicator(String name) {
        return indicatorsRepository.isUserCanInputIndicator(name);
    }

    public void saveIndicator(String name, double value) {
        indicatorsRepository.saveIndicator(name, value);
    }

    public ResultSet getIndicatorsByLogin(String login) {
        return indicatorsRepository.getIndicatorsByLogin(login);
    }
}
