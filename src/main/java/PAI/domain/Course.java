package PAI.domain;

import java.util.ArrayList;

public class Course {

    private String _name;
    private String _acronym;
    private int _quantityOfEcts;
//  private ArrayList <Teacher> _teacher;
    private ArrayList <Semester> _semesterList= new ArrayList<>();

    public Course (String name, String acronym, int quantityOfEcts, Semester semester) throws Exception{
        if (!isValidName(name)) {
            throw new Exception("Invalid input");
        }
            _name = name;
            _acronym = acronym;
            _quantityOfEcts = quantityOfEcts;
            if (_semesterList.contains(semester)) {
                _semesterList.add(semester);
            }
    }

    private boolean isValidName(String courseName) {
        if (courseName == null || courseName.isBlank()) {
            return false;
        }
        return true;
    }
}
