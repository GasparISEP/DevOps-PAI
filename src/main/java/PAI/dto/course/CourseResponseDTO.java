package PAI.dto.course;

public class CourseResponseDTO {

    private String _acronym;
    private String _name;

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
