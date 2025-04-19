package PAI.VOs;

import PAI.ddd.ValueObject;

public class QuantSemesters implements ValueObject {

    private final int _quantSemesters;

    public QuantSemesters(int quantityOfSemesters) {
        if(!isQuantityOfSemestersValid(quantityOfSemesters)) {
            throw new IllegalArgumentException("Insert a valid number of Semesters");
        }
        _quantSemesters = quantityOfSemesters;
    }

    private boolean isQuantityOfSemestersValid(int quantityOfSemesters) {
        return quantityOfSemesters > 0;
    }

    public int getQuantityOfSemesters(){
        return _quantSemesters;
    }
}
