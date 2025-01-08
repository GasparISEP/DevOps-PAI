package PAI.domain;

import java.util.ArrayList;

public class Course {

    private String _name;
    private String _acronym;
    private int _quantityOfEcts;
//  private ArrayList <Teacher> _teacher;
    private ArrayList <Semester> _semesterList= new ArrayList<>();

    public Course (String name, String acronym, int quantityOfEcts, Semester semester) throws Exception{
            _name = name;
            _acronym = acronym;
            _quantityOfEcts = quantityOfEcts;
            if (_semesterList.contains(semester)) {
                _semesterList.add(semester);
            }
    }
}
