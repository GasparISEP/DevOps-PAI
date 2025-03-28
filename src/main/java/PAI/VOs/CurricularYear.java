package PAI.VOs;

public class CurricularYear {
    private final int _curricularYear;

    public CurricularYear(int curricularYear, int totalYears) {
        if (!isCurricularYearPositive(curricularYear) || curricularYear > totalYears) {
            throw new IllegalArgumentException("Curricular Year must be 1 or higher.");
        }
        this._curricularYear = curricularYear;
    }

    public boolean isCurricularYearPositive(int curricularYear) {
        return curricularYear >= 1;
    }

    public int getCurricularYear() {
        return _curricularYear;
    }
}