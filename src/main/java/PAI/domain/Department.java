package PAI.domain;

public class Department {

    private String _name;
    private String _acronym;
    private Teacher _director;
    //private List<Teacher> _departmentTeachers = new ArrayList<>();
    //private List <Programme> _departmentProgramme;

    //constructor
    public Department(String acronym, String name) throws Exception {
        this._acronym = acronym;
        this._name = name;
    }

    public Department(String acronym, String name,Teacher teacherDirector) throws IllegalArgumentException {
        this._acronym = acronym;
        this._name = name;
        this._director = teacherDirector;
    }

}
