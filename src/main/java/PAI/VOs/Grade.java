package PAI.VOs;

import PAI.ddd.ValueObject;

import java.util.Objects;

public class Grade implements ValueObject {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grade grade)) return false;
        return Double.compare(_value, grade._value) == 0;
    }

}
