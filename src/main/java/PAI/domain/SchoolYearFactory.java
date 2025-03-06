package PAI.domain;

public class SchoolYearFactory implements SchoolYearFactoryInterface {

    public SchoolYear createSchoolYear(String description, String startDate, String endDate) {

        if(description == null || description.isBlank())
            throw new IllegalArgumentException ("Description cannot be null or empty!");

        if(startDate == null || startDate.isBlank())
            throw new IllegalArgumentException ("Start Date cannot be null or empty!");

        if(endDate == null || endDate.isBlank())
            throw new IllegalArgumentException ("End Date cannot be null or empty!");

        return new SchoolYear(description, startDate, endDate);

    }
}
