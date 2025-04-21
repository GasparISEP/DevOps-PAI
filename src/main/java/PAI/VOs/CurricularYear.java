package PAI.VOs;

public class CurricularYear {
    private final int _curricularYear;

    public CurricularYear(int curricularYear) {
        if (!isCurricularYearPositive(curricularYear)) {
            throw new IllegalArgumentException("Curricular Year must be 1 or higher.");
        }
        this._curricularYear = curricularYear;
    }

    private boolean isCurricularYearPositive(int curricularYear) {
        return curricularYear >= 1;
    }

    public int toInt() {
        return _curricularYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurricularYear curricularYearValue = (CurricularYear) o;
        return _curricularYear == curricularYearValue._curricularYear;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(_curricularYear);
    }
}