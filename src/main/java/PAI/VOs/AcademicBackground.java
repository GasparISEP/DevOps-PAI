package PAI.VOs;

public class AcademicBackground {

    private final String _description;

    public AcademicBackground(String description) throws IllegalArgumentException {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description must not be empty.");
        }
        else
            this._description = description;
    }

    public String getAcademicBackground() {return _description;}
}
