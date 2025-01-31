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
        if(schoolYearExists(newSchoolYear)){
            throw new Exception("School year already exists.");
        }
        // Add the school year to the list
        _schoolYearList.add(newSchoolYear);
        return true;
    }

    public boolean schoolYearExists(SchoolYear schoolYear){
        if(schoolYear==null){
            return false;
        }
        for (SchoolYear existingSchoolYear : _schoolYearList) {
            if (existingSchoolYear.isSameSchoolYear(schoolYear)) {
                return true;
            }
        }
        return false;
    }

    public SchoolYear getLatestSchoolYear() {

        if (_schoolYearList.isEmpty())
            return null;

        SchoolYear mostRecentSchoolYear = _schoolYearList.get(0);
        for (int i = 0; i < _schoolYearList.size(); i++) {
            if (mostRecentSchoolYear.getEndDate().isBefore(_schoolYearList.get(i).getEndDate()))
                mostRecentSchoolYear = _schoolYearList.get(i);
        }
        return mostRecentSchoolYear;
    }
}