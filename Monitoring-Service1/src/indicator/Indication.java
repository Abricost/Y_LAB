package indicator;

import java.time.LocalDateTime;

public class Indication {

    String indicationName;
    double indicationValue;
    LocalDateTime indicationDate;

    public Indication(String indicationName, double indicationValue, LocalDateTime indicationDate) {
        this.indicationName = indicationName;
        this.indicationValue = indicationValue;
        this.indicationDate = indicationDate;
    }

    public String getIndicationName() {
        return indicationName;
    }

    public void setIndicationName(String indicationName) {
        this.indicationName = indicationName;
    }

    public double getIndicationValue() {
        return indicationValue;
    }

    public void setIndicationValue(double indicationValue) {
        this.indicationValue = indicationValue;
    }

    public LocalDateTime getIndicationDate() {
        return indicationDate;
    }

    public void setIndicationDate(LocalDateTime indicationDate) {
        this.indicationDate = indicationDate;
    }

    @Override
    public String toString() {
        return "Indication{" +
                "indicationName='" + indicationName + '\'' +
                ", indicationValue=" + indicationValue +
                ", indicationDate=" + indicationDate +
                '}';
    }
}
