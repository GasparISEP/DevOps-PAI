package PAI.controller;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.repository.SchoolYearRepository;

public class US07_IWantToCreateASchoolYearController {

    private SchoolYearRepository _schoolYearRepo;

    // Constructor
    public US07_IWantToCreateASchoolYearController (SchoolYearRepository schoolYearRepo) {

        if (schoolYearRepo == null) {
            throw new IllegalArgumentException("School Year Repository must not be null.");
        }

        _schoolYearRepo = schoolYearRepo;
    }

    public boolean addSchoolYear (SchoolYearID schoolYearID,Description description, Date startDate, Date endDate) throws Exception {

        _schoolYearRepo.addSchoolYear(schoolYearID,description, startDate, endDate);

        return true;
    }
}