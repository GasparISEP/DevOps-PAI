package PAI.VOs;

import PAI.ddd.ValueObject;

public class UniqueNumber implements ValueObject {

    private final int _uniqueNumber;

    public UniqueNumber(int uniqueNumber) {

        if(isUniqueNumberValid(uniqueNumber))
            _uniqueNumber = uniqueNumber;
        else
            throw new IllegalArgumentException("Invalid unique number");

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

        if (!(object instanceof UniqueNumber))
            return false;

        UniqueNumber other = (UniqueNumber) object;
        return this._uniqueNumber == other._uniqueNumber;
    }

    public int getUniqueNumber () {
        return _uniqueNumber;
    }
}
