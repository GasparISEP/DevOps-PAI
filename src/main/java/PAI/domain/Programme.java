package PAI.domain;

import java.util.ArrayList;
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


    //US17 alteracao ao Equals
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Programme programme = (Programme) o;
        return _quantityOfEcts == programme._quantityOfEcts && _quantityOfSemesters == programme._quantityOfSemesters &&
                Objects.equals(_name, programme._name) && Objects.equals(_acronym, programme._acronym);
    }

    //Method to add Course
    public boolean addCourseToASemesterOfAProgramme(int semester, Course course) throws Exception {
        if (!doesTheSemesterExistsInTheProgramme(semester))
            throw new IllegalArgumentException("Semester does not exist in the programme");
        if (!isTheCourseNotNull(course))
            throw new IllegalArgumentException("Course cannot be null");

        semester -= 1;
        int semesterWhereCourseIsPresent = getSemesterWhereCourseIsPresent(course);
        if(!isPossibleToAddACourseInThisYear(course,semesterWhereCourseIsPresent,semester))
            throw new Exception("This course can not be added to this year");

        ListOfCoursesInASemester semesterToAddCourse = listOfSemesters.getSemester(semester);
        double totalOfCreditsOfCoursesInTheSemester = semesterToAddCourse.sumOfCreditsOfAllCourses();
        if (totalOfCreditsOfCoursesInTheSemester + course.getQuantityCreditsEcts() > (double)_quantityOfEcts/_quantityOfSemesters)
            throw new IllegalArgumentException("Adding this course will surpass the limit credits ECTS for that Semester");
        listOfSemesters.getSemester(semester).addCourseToSemester(course);
        return true;
    }

    private int getSemesterWhereCourseIsPresent (Course course) {
        int semesterWhereCourseIsPresent;
        if (course.getDurationInSemester() == 2)
            semesterWhereCourseIsPresent = getSemesterWhereAnualCourseIsPresent(course);
        else
            semesterWhereCourseIsPresent = getSemesterWhereSemesterCourseIsPresent(course);
        return semesterWhereCourseIsPresent;
    }

    private int getSemesterWhereAnualCourseIsPresent (Course course) {
        int count = 0;
        int semesterWhereCourseIsPresent = 0;
        for (int i = 0; i < _quantityOfSemesters; i++){
            ListOfCoursesInASemester newSemester = listOfSemesters.getSemester(i);
            ArrayList<Course> listCourses = newSemester.getAllCourses();
            if (listCourses.contains(course)) {
                count++;
                semesterWhereCourseIsPresent = i+1;
            }
        }
        if (count > 1)
            throw new IllegalArgumentException("The semester with anual duration already exists in two semesters");
        return semesterWhereCourseIsPresent;
    }

    private int getSemesterWhereSemesterCourseIsPresent (Course course) {
        for (int i = 0; i < _quantityOfSemesters; i++){
            ListOfCoursesInASemester newSemester = listOfSemesters.getSemester(i);
            ArrayList<Course> listCourses = newSemester.getAllCourses();
            if (listCourses.contains(course)) {
                throw new IllegalArgumentException("The course already exists in the programme");
            }
        }
        return 0;
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

    private void createListOfSemesters(){
        for (int i = 0; i < _quantityOfSemesters; i++){
            ListOfCoursesInASemester newSemester = new ListOfCoursesInASemester();
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

    private boolean isPossibleToAddACourseInThisYear (Course course, int semesterWhereCourseIsPresent, int semester) {
        if (course.getDurationInSemester() == 2 && semesterWhereCourseIsPresent != 0 ) {
            if ((semesterWhereCourseIsPresent % 2 == 0) && semester + 1 != semesterWhereCourseIsPresent - 1) {
                return false;
            }
            if ((semesterWhereCourseIsPresent % 2 != 0) && semester + 1 != semesterWhereCourseIsPresent + 1){
                return false;
            }
        }
        return true;
    }

    //US17
    public boolean isStudentEnrolled(Student student) {
        for (Enrolment enrolment : _programmeEnrolment) {
            if(enrolment.findStudentInEnrollments().equals(student)){
                return true;
            }
        }
        return false;
    }

    public boolean isInDepartment (Department department){
        return _department.equals(department);
    }
}