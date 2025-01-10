package PAI.domain;

import java.util.ArrayList;

public class SchoolYearRepository {

    private ArrayList<SchoolYear> schoolYearList;

    public SchoolYearRepository() {
        this.schoolYearList = new ArrayList<>();
    }

    public boolean addSchoolYear(String description, String startDate, String endDate) throws Exception {

        SchoolYear newSchoolYear = new SchoolYear(description, startDate, endDate);

        // Check if the school year already exists in the list
        for (SchoolYear existingSchoolYear : schoolYearList) {
            if (existingSchoolYear.isSameSchoolYear(newSchoolYear)) {
                throw new Exception("School year already exists.");
            }
        }
        // Add the school year to the list
        schoolYearList.add(newSchoolYear);
        return true;
    }
}