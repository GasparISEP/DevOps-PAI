package PAI.VOs;

public class Semester {

    private final int _semester;

    public Semester(int semester) {
        if (!isSemesterValid(semester)) {
            throw new IllegalArgumentException("Invalid semester.");
        }
        this._semester = semester;
    }

    public int toInt() {
        return _semester;
    }

    private boolean isSemesterValid(int semester) {
        return semester >= 1 && semester <= 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semesterValue = (Semester) o;
        return _semester == semesterValue._semester;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(_semester);
    }
}