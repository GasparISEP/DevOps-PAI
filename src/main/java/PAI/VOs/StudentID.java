package PAI.VOs;

public class StudentID {

    private final int _uniqueNumber;

    public StudentID (int uniqueNumber) throws IllegalArgumentException {

        if (isUniqueNumberValid(uniqueNumber))
            _uniqueNumber = uniqueNumber;
        else
            throw new IllegalArgumentException("Student's unique number is invalid!");
    }

    private boolean isUniqueNumberValid (int uniqueNumber) {

        if (uniqueNumber <= 1000000 || uniqueNumber >= 2000000)
            return false;
        else
            return true;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object)
            return true;

        if (object instanceof StudentID) {
            StudentID studentID = (StudentID) object;

            if (this._uniqueNumber == studentID._uniqueNumber)
                return true;
        }
        return false;
    }
}