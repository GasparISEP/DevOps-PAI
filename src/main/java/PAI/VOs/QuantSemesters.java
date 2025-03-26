package PAI.VOs;

public class QuantSemesters {

    private final int _quantSemesters;

    public QuantSemesters(int quantityOfSemesters) throws Exception {
        if(!isQuantityOfSemestersValid(quantityOfSemesters)) {
            throw new Exception("Insert a valid number of Semesters");
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
