package PAI.domain;

import java.util.ArrayList;

public class Course {

    private String _name;
    private String _acronym;
    private int _quantityOfEcts;
    private int _semester;
    private ArrayList <Teacher> _teacher;

    public Course (String name, String acronym, int quantityOfEcts, int semester,  Teacher teacher) throws Exception{
        if (!isValidName(name) || !isValidAcronym(acronym) || !isValidQuantityOfEcts(quantityOfEcts) || !isValidSemester(semester)) {
            throw new Exception("Invalid input");
        }
            _name = name;
            _acronym = acronym;
            _quantityOfEcts = quantityOfEcts;
            _semester = semester;
    }

    private boolean isValidName(String courseName) {
        if (courseName == null || courseName.isBlank()) {
            return false;
        }
        return true;
    }

    private boolean isValidAcronym(String courseAcronym) {
        if (courseAcronym == null || courseAcronym.isBlank()) {
            return false;
        }
        return true;
    }

    private boolean isValidQuantityOfEcts(int quantityOfEcts) {
        if (quantityOfEcts <= 0 || quantityOfEcts > 180) {
            return false;
        }
        return true;
    }

    private boolean isValidSemester(int semester) {
        if (semester <= 0 || semester > 6) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object compare){
        if(this==compare) return true;
        if(!(compare instanceof Course)) return false;
        Course courseTest = (Course) compare;
        if(this._acronym.equals(courseTest._acronym))
            return true;
        return false;
    }
}
