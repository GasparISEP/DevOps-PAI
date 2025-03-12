package PAI.domain;

public class Course {

    private final String _name;
    private final String _acronym;
    private final double _quantityCreditsEcts;
    private final int _durationCourseInSemester;

    public Course(String name, String acronym, double quantityOfEcts, int durationCourseInSemester) throws Exception {
        if (!isValidName(name))
            throw new IllegalArgumentException("Course name cannot be null or blank");
        if (!isValidAcronym(acronym))
            throw new IllegalArgumentException("Course acronym cannot be null or blank");
        if (!isValidQuantityCreditsEcts(quantityOfEcts))
            throw new IllegalArgumentException("quantityCredtisEcts can only have 1 decimal place");
        if (!isValidDurationCourseInSemester(durationCourseInSemester))
            throw new IllegalArgumentException("durationCourseInSemester can only have a value between 1 and 2");

        _name = name;
        _acronym = acronym;
        _quantityCreditsEcts = quantityOfEcts;
        _durationCourseInSemester = durationCourseInSemester;
    }

    private boolean isValidName(String courseName) throws Exception {
        if (courseName == null || courseName.isBlank())
            return false;
        return true;
    }

    private boolean isValidAcronym(String courseAcronym) throws Exception{
        if (courseAcronym == null || courseAcronym.isBlank())
            return false;
        if (!courseAcronym.matches("^[A-Z0-9]+$"))
            throw new IllegalArgumentException("The Course acronym must only contain capital letters and numbers");
        return true;
    }

    private boolean isValidQuantityCreditsEcts(double quantityCreditsEcts) throws Exception {
        if (hasMoreThanOneDecimalPlace(quantityCreditsEcts))
            return false;
        if (quantityCreditsEcts > 0 && quantityCreditsEcts <= 60)
            return true;
        throw new IllegalArgumentException("quantityCreditsEcts can only have a value between 1 and 60");
    }

    private boolean isValidDurationCourseInSemester(int durationCourseInSemester) throws Exception {
        if (durationCourseInSemester > 0 && durationCourseInSemester < 3)
            return true;
        return false;
    }

    private  boolean hasMoreThanOneDecimalPlace(double value) {
        return (value * 10) % 1 != 0;
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) {
            return true;
        }
        if (!(objectToCompare instanceof Course)) {
            return false;
        }
        Course courseTest = (Course) objectToCompare;
        if (this._acronym.equals(courseTest._acronym) || this._name.equals(courseTest._name)) {
            return true;
        }
        return false;
    }

    public double getQuantityCreditsEcts() {
        return _quantityCreditsEcts;
    }

    public int getDurationInSemester() {
        return _durationCourseInSemester;
    }

    public String getName() {
        return _name;
    }

    public String getAcronym() {
        return _acronym;
    }
}