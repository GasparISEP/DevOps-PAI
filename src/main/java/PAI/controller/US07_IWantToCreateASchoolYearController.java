package PAI.controller;

import PAI.domain.SchoolYearRepository;

public class US07_IWantToCreateASchoolYearController {

    private SchoolYearRepository _schoolYearRepo;

    // Constructor
    public US07_IWantToCreateASchoolYearController (SchoolYearRepository schoolYearRepo) {

        if (schoolYearRepo == null) {
            throw new IllegalArgumentException("School Year Repository must not be null.");
        }

        _schoolYearRepo = schoolYearRepo;
    }

    public boolean addSchoolYear (String description, String startDate, String endDate) throws Exception {

        _schoolYearRepo.addSchoolYear(description, startDate, endDate);

        return true;
    }
}