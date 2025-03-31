package PAI.VOs;

public class CourseQuantityCreditsEcts {

    private final double _quantityCreditsEcts;

    public CourseQuantityCreditsEcts(double quantityCreditsEcts) throws Exception{

        if(!isValidQuantityCreditsEcts(quantityCreditsEcts)){
            throw new IllegalArgumentException("quantityCreditsEcts can only have a value between 1 and 60");}
        this._quantityCreditsEcts = quantityCreditsEcts;
    }

    private boolean isValidQuantityCreditsEcts(double quantityCreditsEcts) throws Exception {
        if (quantityCreditsEcts > 0 && quantityCreditsEcts <= 60)
            return true;
        return false;
    }
}
