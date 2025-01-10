package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class SchoolYearRepository {

    private List<SchoolYear> _schoolYearList;

    public SchoolYearRepository() {
        this._schoolYearList = new ArrayList<>();
    }

    public boolean addSchoolYear(String description, String startDate, String endDate) throws Exception {

        SchoolYear newSchoolYear = new SchoolYear(description, startDate, endDate);

        // Check if the school year already exists in the list
        for (SchoolYear existingSchoolYear : _schoolYearList) {
            if (existingSchoolYear.isSameSchoolYear(newSchoolYear)) {
                throw new Exception("School year already exists.");
            }
        }
        // Add the school year to the list
        _schoolYearList.add(newSchoolYear);
        return true;
    }
}