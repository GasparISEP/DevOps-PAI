package PAI.VOs;

import PAI.ddd.DomainId;

public class StudentID implements DomainId {

    private final UniqueNumber _uniqueNumber;
    private final NIF _nif;

    public StudentID (UniqueNumber uniqueNumber, NIF nif) throws IllegalArgumentException {

        if (uniqueNumber == null)
            throw new IllegalArgumentException("Student's unique number cannot be null!");

        _uniqueNumber = uniqueNumber;

        if (nif == null)
            throw new IllegalArgumentException("NIF cannot be null!");

        _nif = nif;
    }


    @Override
    public boolean equals(Object object) {

        if (this == object)
            return true;

        if (object instanceof StudentID) {
            StudentID studentID = (StudentID) object;

            if (this._uniqueNumber == studentID._uniqueNumber && this._nif == studentID._nif)
                return true;
        }
        return false;
    }

    public UniqueNumber getUniqueNumber () {
        return _uniqueNumber;
    }

    public NIF getNIF () { return _nif; }

    public boolean isEquals (StudentID studentID) {
        return this.equals(studentID);
    }
}