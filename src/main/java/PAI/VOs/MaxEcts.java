package PAI.VOs;

import PAI.ddd.ValueObject;

public class MaxEcts implements ValueObject {

    private final int _maxEcts;

    public MaxEcts(int maxEcts) {
        if (maxEcts <= 0 || maxEcts > 240) {
            throw new IllegalArgumentException("The value of ECTS must be between 1 and 240.");
        }
        _maxEcts = maxEcts;
    }

    public int getMaxEcts() {
            return _maxEcts;
        }

}
