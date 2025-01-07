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
}
