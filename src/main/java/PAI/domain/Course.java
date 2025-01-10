package PAI.domain;

public class Course {

    private String _name;
    private String _acronym;
    private int _quantityCreditsEcts;

    public Course (String name, String acronym, int quantityCreditsEcts) throws Exception{
        if (!isValidName(name) || !isValidAcronym(acronym) || !isValidQuantityOfEcts(quantityCreditsEcts)) {
            throw new Exception("Invalid input");
        }
            _name = name;
            _acronym = acronym;
            _quantityCreditsEcts = quantityCreditsEcts;
    }

    private boolean isValidName(String courseName) {
        if (courseName == null || courseName.isBlank()) {
            return false;
        }
        return true;
    }

    private boolean isValidAcronym(String courseAcronym) {
        if (courseAcronym == null || courseAcronym.isBlank() || !courseAcronym.matches("^[A-Z0-9]+$")) {
            return false;
        }
        return true;
    }

    private boolean isValidQuantityOfEcts(int quantityOfEcts) {
        if (quantityOfEcts > 0 && quantityOfEcts <= 60) {
            return true;
        }
    return false;
    }

    @Override
    public boolean equals(Object objectToCompare){
        if(this==objectToCompare) {
            return true;
        }
        if(!(objectToCompare instanceof Course)) {
            return false;
        }
        Course courseTest = (Course) objectToCompare;
        if(this._acronym.equals(courseTest._acronym)){
            return true;
        }
        return false;
    }
}
