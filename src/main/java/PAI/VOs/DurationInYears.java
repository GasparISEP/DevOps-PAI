package PAI.VOs;

public class DurationInYears {
    private final int _durationInYears;

    public DurationInYears (int quantSemester) throws Exception {
        if (!isQuantSemesterValid(quantSemester)) {
            throw new IllegalArgumentException("number of Semesters can't be smaller than 1");
        }
        this._durationInYears = calculateSemestersInYears(quantSemester);
    }

    public boolean isQuantSemesterValid (int quantSemester) {
        return quantSemester >= 1;
    }

    public int calculateSemestersInYears(int quantSemester) {
        if (quantSemester % 2 != 0) {
            quantSemester += 1;
        }
        return quantSemester / 2;
    }

    public int getDurationInYears() {
        return this._durationInYears;
    }
}
