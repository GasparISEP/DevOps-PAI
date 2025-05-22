package PAI.dto.course;

public class CourseResponseDTO {

    private final String _acronym;
    private final String _name;

    public CourseResponseDTO (String acronym, String name) {

        _acronym = acronym;
        _name = name;
    }

    public String getAcronym() {
        return _acronym;
    }

    public String getName() {
        return _name;
    }
}
