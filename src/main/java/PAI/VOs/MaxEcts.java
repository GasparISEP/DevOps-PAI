package PAI.VOs;

public class MaxEcts {

    private final int _maxEcts;

    public MaxEcts(int maxEcts) {
        if (maxEcts <= 0 || maxEcts > 240) {
            throw new IllegalArgumentException("O valor de ECTS deve estar entre 1 e 240.");
        }
        _maxEcts = maxEcts;
    }

    public int getMaxEcts() {
            return _maxEcts;
        }

}
