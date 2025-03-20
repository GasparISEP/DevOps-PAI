package PAI.repository;

import PAI.domain.SchoolYear;
import PAI.factory.SchoolYearFactory;
import PAI.factory.SchoolYearListFactory;

import java.time.LocalDate;
import java.util.List;


public class SchoolYearRepository {

    private List<SchoolYear> _schoolYearList;
    private SchoolYearFactory _schoolYearFactory;
    private SchoolYearListFactory _schoolYearListFactory;

    public SchoolYearRepository(SchoolYearFactory schoolYearFactory, SchoolYearListFactory schoolYearListFactory) {

        if (schoolYearFactory == null) {
            throw new IllegalArgumentException("SchoolYearFactory cannot be null");
        }
        if (schoolYearListFactory == null) {
            throw new IllegalArgumentException("SchoolYearListFactory cannot be null");
        }

        this._schoolYearList = schoolYearListFactory.newArrayList();
        this._schoolYearFactory = schoolYearFactory;
        this._schoolYearListFactory = schoolYearListFactory;
    }

    public boolean addSchoolYear(String description, String startDate, String endDate) throws Exception {

        SchoolYear newSchoolYear = _schoolYearFactory.createSchoolYear(description, startDate, endDate);

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
        return _schoolYearListFactory.copySchoolYearArrayList(_schoolYearList);
    }
}