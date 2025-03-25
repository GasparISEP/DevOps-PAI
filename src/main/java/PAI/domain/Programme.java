package PAI.domain;



import PAI.factory.*;
import PAI.repository.StudyPlan;

import java.util.List;
import java.util.Objects;

public class Programme {

    private String _name;
    private String _acronym;
    private int _quantityOfEcts;
    private int _quantityOfSemesters;
    private DegreeType _degreeType;
    private Department _department;
    private Teacher _programmeDirector;
    private List<Course> _courseList;
    private IProgrammeCourseListFactory _I_programmeCourseListFactory;
    private StudyPlan _studyPlan;

    public Programme(String name, String acronym, int quantityOfEcts, int quantityOfSemesters, DegreeType degreeType, Department department,
                     Teacher programmeDirector, IProgrammeCourseListFactory IProgrammeCourseListFactory, CourseInStudyPlanFactory courseInStudyPlanFactory, StudyPlanListFactory studyPlanListFactory, StudyPlanFactory studyPlanFactory, ICourseFactory ICourseFactory) {

        if (isNameInvalid(name)) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        _name = name;

        if (isAcronymInvalid(acronym)) {
            throw new IllegalArgumentException("Acronym must not be empty");
        }
        _acronym = acronym;

        if (isQuantityOfEctsInvalid(quantityOfEcts)) {
            throw new IllegalArgumentException("Insert a valid number of ECTS");
        }
        _quantityOfEcts = quantityOfEcts;

        if (isQuantityOfSemestersInvalid(quantityOfSemesters)) {
            throw new IllegalArgumentException("Insert a valid number of Semesters");
        }
        _quantityOfSemesters = quantityOfSemesters;

        if (degreeType == null) {
            throw new IllegalArgumentException("Insert a valid DegreeType");
        }
        _degreeType = degreeType;

        if (department == null) {
            throw new IllegalArgumentException("Insert a valid Department");
        }
        _department = department;

        if (programmeDirector == null) {
            throw new IllegalArgumentException("Insert a valid Programme Director");
        }

        _programmeDirector = programmeDirector;

        _I_programmeCourseListFactory = IProgrammeCourseListFactory;

        _courseList = _I_programmeCourseListFactory.createCourseList();

        _studyPlan = studyPlanFactory.newStudyPlan(courseInStudyPlanFactory, studyPlanListFactory, ICourseFactory);
    }

    private boolean isNameInvalid(String name) {
        return name == null || name.isBlank() || !name.matches("^[A-Za-zÀ-ÖØ-öø-ÿ ]+$");
    }

    private boolean isAcronymInvalid(String acronym) {
        return acronym == null || acronym.isBlank() || !acronym.matches("^[A-Za-zÀ-ÖØ-öø-ÿ ]+$");
    }

    private boolean isQuantityOfEctsInvalid(int quantityOfEcts) {
        return quantityOfEcts <= 0 || quantityOfEcts > 30;
    }

    private boolean isQuantityOfSemestersInvalid(int quantityOfSemesters) {
        return quantityOfSemesters <= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Programme programme = (Programme) o;
        return _quantityOfEcts == programme._quantityOfEcts && _quantityOfSemesters == programme._quantityOfSemesters &&
                Objects.equals(_name, programme._name) && Objects.equals(_acronym, programme._acronym);
    }

    //Wrapper for equals
    public boolean isEquals (Programme programme) {

        return this.equals(programme);
    }

    //Method to add Course
    public boolean addCourseToAProgramme(Course course) throws Exception {

        if (_courseList.contains(course)) {
            return false;
        }

        _courseList.add(course);
        return true;
    }

    public boolean newProgrammeDirector(Teacher teacherDirector) throws Exception {
        if (teacherDirector == null) return false;
        _programmeDirector = teacherDirector;
        return true;
    }


    public boolean isInDepartment(Department department) {
        return _department.equals(department);
    }

    public List<Course> getCourseList() {
        return _courseList;
    }

    public int getQuantityOfSemester() {
        return _quantityOfSemesters;
    }

    public int getQuantityOfEcts() {
        return _quantityOfEcts;
    }

    public StudyPlan getStudyPlan() {
        return _studyPlan;
    }

    public int calculateNumberOfYears(int quantityOfSemesters) {
        int numberOfYears;

        if (quantityOfSemesters % 2 != 0) {
            numberOfYears = (quantityOfSemesters + 1) / 2;
        }
        else {
            numberOfYears = quantityOfSemesters / 2;
        }
        return numberOfYears;
    }

    public boolean hasThisProgrammeName(String name) {return _name.equals(name);}

    public String getAcronym() {
        return _acronym;
    }

    public String getProgrammeName() {
        return _name;
    }
}