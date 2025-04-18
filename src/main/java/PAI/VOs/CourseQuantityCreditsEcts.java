package PAI.VOs;

import PAI.ddd.ValueObject;

import java.util.Objects;
import java.util.UUID;

public class CourseQuantityCreditsEcts implements ValueObject {

    private final double _quantityCreditsEcts;

    public CourseQuantityCreditsEcts(double quantityCreditsEcts) throws Exception{

        if(!isValidQuantityCreditsEcts(quantityCreditsEcts)){
            throw new IllegalArgumentException("quantityCreditsEcts can only have 1 decimal place");}
        this._quantityCreditsEcts = quantityCreditsEcts;
    }

    private boolean isValidQuantityCreditsEcts(double quantityCreditsEcts) throws Exception {
        if (hasMoreThanOneDecimalPlace(quantityCreditsEcts))
            return false;
        if (quantityCreditsEcts > 0 && quantityCreditsEcts <= 60)
            return true;
        throw new IllegalArgumentException("quantityCreditsEcts can only have a value between 1 and 60");
    }

    private  boolean hasMoreThanOneDecimalPlace(double value) {
        return (value * 10) % 1 != 0;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof CourseQuantityCreditsEcts))
            return false;
        CourseQuantityCreditsEcts quantityCreditsEctsTest = (CourseQuantityCreditsEcts) object;
        return Double.compare(_quantityCreditsEcts, quantityCreditsEctsTest._quantityCreditsEcts) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_quantityCreditsEcts);
    }

    public double getQuantity() { return _quantityCreditsEcts; }
}
