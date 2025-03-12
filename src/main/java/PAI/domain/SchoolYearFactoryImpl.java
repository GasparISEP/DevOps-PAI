package PAI.domain;

public class SchoolYearFactoryImpl implements SchoolYearFactory {

    public SchoolYear createSchoolYear(String description, String startDate, String endDate) {

        return new SchoolYear(description, startDate, endDate);

    }
}
