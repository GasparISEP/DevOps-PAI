package PAI.VOs;

public class QuantEcts {

    private final int _quantEcts;

    public QuantEcts(int quantityOfEcts) throws Exception {
        if (!isQuantEctsValid(quantityOfEcts)) {
            throw new Exception("Insert a valid number of ECTS");
        }
        _quantEcts = quantityOfEcts;
    }

    private boolean isQuantEctsValid (int quantityOfEcts){
        return quantityOfEcts > 0 && quantityOfEcts <= 30;
    }

    public int getQuantEcts() {
        return _quantEcts;
    }
}

