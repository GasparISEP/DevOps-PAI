package PAI.domain;

public class TeacherCategory {

    private String _name;

    public TeacherCategory (String categoryName)  throws Exception {
        if (!isNameValid(categoryName)) {
            throw new Exception("Name must be non-empty string.");
        } 
        else {
            this._name = categoryName;
        }
    }

    private boolean isNameValid(String categoryName) {
        if (categoryName == null || categoryName.isBlank()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals (Object objectToCompare){

        if (!(objectToCompare instanceof TeacherCategory)) {
            return false;
        }
        TeacherCategory testCategory = (TeacherCategory) objectToCompare;

        if (_name.equals(testCategory._name)) {
            return true;
        }
        return false;
    }

}
