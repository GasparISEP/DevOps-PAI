package PAI.domain;

public class DepartmentDoubleEqualsTrue extends Department {
    public DepartmentDoubleEqualsTrue (String acronym, String name) throws Exception {
        super(acronym, name);
    }

    @Override
    public boolean equals (Object obj) {
        return true;
    }
}
