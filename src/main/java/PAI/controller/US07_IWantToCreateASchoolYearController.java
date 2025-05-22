package PAI.controller;

import PAI.dto.schoolYear.SchoolYearDTO;
import PAI.service.schoolYear.ISchoolYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class US07_IWantToCreateASchoolYearController {

    private final ISchoolYearService schoolYearService;

    // Constructor
    @Autowired
    public US07_IWantToCreateASchoolYearController (ISchoolYearService schoolYearService) {
        validateServiceArguments(schoolYearService);
        this.schoolYearService = schoolYearService;
    }

    private void validateServiceArguments(ISchoolYearService schoolYearService) {
        if (schoolYearService == null) {
            throw new IllegalArgumentException("Services cannot be null.");
        }
    }

    // Creates a new School Year
    public SchoolYearDTO addSchoolYear (String descriptionInfo, String startDateInfo, String endDateInfo) throws Exception {
        try {
            return schoolYearService.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid input: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new Exception("Failed to add school year: " + e.getMessage(), e);
        }
    }
}