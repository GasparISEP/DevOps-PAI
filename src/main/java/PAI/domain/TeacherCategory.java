package PAI.domain;

import java.util.Objects;

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

    public String getName() {
        return _name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TeacherCategory that = (TeacherCategory) obj;
        return Objects.equals(_name, that._name); // Compare names
    }

    @Override
    public int hashCode() {
        return Objects.hash(_name); // Hash based on name
    }

}
