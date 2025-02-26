package PAI.domain;

import java.time.LocalDate;

public class SchoolYearFactory {

    public SchoolYear createSchoolYear(String description, String startDate, String endDate) throws Exception {
        SchoolYear y1 = new SchoolYear(description, startDate, endDate);
        return  y1;
    }
}
