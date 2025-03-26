package PAI.VOs;

public class Grade {
    private final double _value;

    public Grade(double value) throws Exception {
        if (!isGradeValid(value)){
            throw new IllegalArgumentException("Grade should be between 0 and 20");
        }
        _value = value;
    }

    private boolean isGradeValid (double value){
        return value >=0 && value <=20;
    }

    public double knowGrade() {
        return _value;
    }
}
