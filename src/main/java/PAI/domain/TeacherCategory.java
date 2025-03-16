package PAI.domain;

import java.util.Objects;

/**
 * Represents a category of a teacher with a name attribute.
 */
public class TeacherCategory {

    private String _name;

    /**
     * Constructs a TeacherCategory with the specified category name.
     *
     * @param categoryName the name of the teacher category
     * @throws Exception if the category name is null or empty
     */
    public TeacherCategory (String categoryName)  throws Exception {
        if (!isNameValid(categoryName)) {
            throw new Exception("Name must be non-empty string.");
        } 
        else {
            this._name = categoryName;
        }
    }

    /**
     * Validates the given category name.
     *
     * @param categoryName the name to validate
     * @return true if the name is non-null and not blank, false otherwise
     */
    private boolean isNameValid(String categoryName) {
        if (categoryName == null || categoryName.isBlank()) {
            return false;
        }
        return true;
    }

    /**
     * Retrieves the name of the teacher category.
     *
     * @return the category name
     */
    public String getName() {
        return _name;
    }

    /**
     * Checks if this TeacherCategory is equal to another object.
     *
     * The equality check is based on the name attribute rather than the entire object.
     *
     * @param obj the object to compare
     * @return true if the names are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TeacherCategory that = (TeacherCategory) obj;
        return Objects.equals(_name, that._name); // Compare names
    }

    /**
     * Computes the hash code for this TeacherCategory based on the name attribute.
     *
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(_name); // Hash based on name
    }

}
