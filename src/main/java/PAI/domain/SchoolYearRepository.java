package PAI.domain;

import java.time.LocalDate;
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

    public SchoolYear getCurrentSchoolYear() {

        if (_schoolYearList.isEmpty())
            return null;

        LocalDate today = LocalDate.now();

        for (int i = 0; i < _schoolYearList.size(); i++) {
            if (!today.isBefore(_schoolYearList.get(i).getStartDate()) && !today.isAfter(_schoolYearList.get(i).getEndDate()))
                return _schoolYearList.get(i);
        }
        return null;
    }

    public List<SchoolYear> getAllSchoolYears() {
        return new ArrayList<>(_schoolYearList);
    }
}