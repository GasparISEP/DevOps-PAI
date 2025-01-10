package PAI.domain;

import java.util.ArrayList;

public class Programme {

    private String _name;
    private String _acronym;
    private int _quantityOfEcts;
    private int _quantityOfSemesters;
    private DegreeType _degreeType;
    private Department _department;
    private Teacher _programmeDirector;
    private ArrayList<Course> _courseList = new ArrayList<>();


    public Programme(String name, String acronym, int quantityOfEcts, int quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector) throws Exception {
        if (isNameInvalid(name)){ throw new IllegalArgumentException("Name must not be empty");}
        _name = name;

        if (isAcronymInvalid(acronym)){throw new IllegalArgumentException("Acronym must not be empty");}
        _acronym = acronym;

        if (isQuantityOfEctsInvalid(quantityOfEcts)){throw  new IllegalArgumentException("Insert a valid number of ECTS");}
        _quantityOfEcts = quantityOfEcts;

        if (isQuantityOfSemestersInvalid(quantityOfSemesters)){throw  new IllegalArgumentException("Insert a valid number of Semesters");}
        _quantityOfSemesters = quantityOfSemesters;

        if (degreeType==null){throw  new IllegalArgumentException("Insert a valid DegreeType");}
        _degreeType = degreeType;

        if (department== null){throw  new IllegalArgumentException("Insert a valid Department");}
        _department = department;

        if (programmeDirector== null){throw  new IllegalArgumentException("Insert a valid Programme Director");}
        _programmeDirector = programmeDirector;

    }


    private boolean isNameInvalid (String name){
        return name == null || name.isBlank() || !name.matches("^[A-Za-zÀ-ÖØ-öø-ÿ ]+$");
    }

    private boolean isAcronymInvalid (String acronym){
        return acronym == null || acronym.isBlank() || !acronym.matches("^[A-Za-zÀ-ÖØ-öø-ÿ ]+$");
    }

    private boolean isQuantityOfEctsInvalid (int quantityOfEcts){
        return quantityOfEcts <= 0 || quantityOfEcts > 30;
    }

    private boolean isQuantityOfSemestersInvalid (int quantityOfSemesters){
        return quantityOfSemesters <=0;
    }


    //method
    @Override
    public boolean equals(Object ObjectToCompare){
        if (this == ObjectToCompare)
            return true;

        if (!(ObjectToCompare instanceof Programme))
            return false;

        Programme ProgrameToBeCompared = (Programme) ObjectToCompare;

        if (this._acronym == ProgrameToBeCompared._acronym)
            return true;
        return false;
    }

    //Method to add Course
    public boolean addCourse(Course course) {
        if (_courseList.contains(course)) {
            return false;
        }
        _courseList.add(course);
        return true;
    }

    public void newProgrammeDirector(Teacher teacherDirector) throws Exception {
        _programmeDirector = teacherDirector;
    }

}