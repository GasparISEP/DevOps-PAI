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

    public String getName() {
        return _name;
    }


    @Override
    public boolean equals (Object objectToCompare){

        if (!(objectToCompare instanceof TeacherCategory)) {
            return false;
        }
        TeacherCategory testCategory = (TeacherCategory) objectToCompare;

        return _name.equals(testCategory._name);
    }
//@Override
//public boolean equals(Object o) {
//    if (this == o) return true;
//    if (o == null || getClass() != o.getClass()) return false;
//    TeacherCategory that = (TeacherCategory) o;
//    return _name.equals(that._name);
//}

    @Override
    public int hashCode() {
        return _name.hashCode();
    }



}
