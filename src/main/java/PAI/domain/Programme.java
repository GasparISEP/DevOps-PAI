package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class Programme {

    private String _name;
    private String _acronym;
    private int _quantityOfEcts;
    private int _quantityOfSemesters;
    private DegreeType _degreeType;
    private Department _department;
    private Teacher _programmeDirector;
    private ArrayList<Course> _courseList = new ArrayList<>();
    private List<Enrolment> _programmeEnrolment;
    private SemestersListOfAProgramme listOfSemesters = new SemestersListOfAProgramme();

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

        _programmeEnrolment = new ArrayList<>();

        createListOfSemesters();
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
    public boolean addCourse(int semester, Course course, CourseRepository courseRepository) throws Exception {

        if (!doesTheSemesterExistsInTheProgramme(semester))
            throw new IllegalArgumentException("Semester does not exist in the programme");
        if (!isTheCourseNotNull(course))
            throw new IllegalArgumentException("Course cannot be null");
        if (isTheCourseInTheSystem(course, courseRepository))
            semester -= 1;
        else
            throw new IllegalArgumentException("Course is not in system");

        int semesterWhereCourseIsPresent = 0;
        if (course.getDurationInSemester() == 2) {
            int count = 0;
            for (int i = 0; i < _quantityOfSemesters; i++){
                Semester newSemester = listOfSemesters.getSemester(i);
                ArrayList<Course> listCourses = newSemester.getAllCourses();
                if (listCourses.contains(course)) {
                    count++;
                    semesterWhereCourseIsPresent = i+1;
                }
            }
            if (count > 1)
                throw new IllegalArgumentException("The semester with anual duration already exists in two semesters");
        } else {
            for (int i = 0; i < _quantityOfSemesters; i++){
                Semester newSemester = listOfSemesters.getSemester(i);
                ArrayList<Course> listCourses = newSemester.getAllCourses();
                if (listCourses.contains(course))
                    throw new IllegalArgumentException("The course already exists in the programme");
            }
        }
        if (course.getDurationInSemester() == 2 && semesterWhereCourseIsPresent != 0 && (semesterWhereCourseIsPresent % 2 == 0) && semester + 1 != semesterWhereCourseIsPresent - 1)
            throw new IllegalArgumentException("This course can only be added to the previous semester");
        if (course.getDurationInSemester() == 2 && semesterWhereCourseIsPresent != 0 && (semesterWhereCourseIsPresent % 2 != 0) && semester + 1 != semesterWhereCourseIsPresent + 1)
            throw new IllegalArgumentException("This course can only be added to the next semester");

        Semester semesterToAddCourse = listOfSemesters.getSemester(semester);
        ArrayList<Course> listCourses = semesterToAddCourse.getAllCourses();
        double totalOfCreditsOfCoursesInTheSemester = semesterToAddCourse.sumOfCreditsOfAllCourses();
        if (totalOfCreditsOfCoursesInTheSemester + course.getQuantityCreditsEcts() > (double)_quantityOfEcts/_quantityOfSemesters)
            throw new IllegalArgumentException("Adding this course will surpass the limit credits ECTS for that Semester");
        listOfSemesters.getSemester(semester).addCourseToSemester(course);
        return true;
    }

    private boolean doesTheSemesterExistsInTheProgramme(int semester) throws Exception{
        if (semester > 0 && semester <= this._quantityOfSemesters)
            return true;
        return false;
    }

    private boolean isTheCourseNotNull (Course course){
        if (course == null)
            return false;
        return true;
    }

    private boolean isTheCourseInTheSystem(Course course, CourseRepository courseRepository){
        if (courseRepository.isCourseRegistered(course))
            return true;
        return false;
    }

    private void createListOfSemesters(){
        for (int i = 0; i < _quantityOfSemesters; i++){
            Semester newSemester = new Semester();
            listOfSemesters.addSemester(newSemester);
        }
    }

    public void newProgrammeDirector(Teacher teacherDirector) throws Exception {
        _programmeDirector = teacherDirector;
    }

    public boolean enrolStudentInProgramme (Student student, AccessMethod accessMethod, AccessMethodRepository amr) throws Exception {

        //Verify if access method exists in the access method repository
        if(!amr.isAccessMethodRegistered(accessMethod)) {
            throw new Exception("Access method cannot be found in the repository!");
        }

        //Verify if student is already enrolled in the programme
        for(Enrolment existingEnrolment : _programmeEnrolment) {
            if (existingEnrolment.isSameStudent(student))
                throw new Exception("Student is already enrolled in the programme!");
        }

        //Creates enrolment and adds it to the _programmeEnrolment list
        Enrolment enrolment = new Enrolment(student, accessMethod);

        _programmeEnrolment.add(enrolment);

        return true;
    }

}