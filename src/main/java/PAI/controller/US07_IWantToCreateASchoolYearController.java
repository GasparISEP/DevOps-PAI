package PAI.controller;

import PAI.VOs.Date;
import PAI.VOs.Description;
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

    public boolean addSchoolYear (String descriptionInfo, String startDateInfo, String endDateInfo) throws Exception {

        Description description = new Description(descriptionInfo);
        Date startDate = new Date(startDateInfo);
        Date endDate = new Date(endDateInfo);
        _schoolYearRepo.addSchoolYear(description, startDate, endDate);

        return true;
    }
}