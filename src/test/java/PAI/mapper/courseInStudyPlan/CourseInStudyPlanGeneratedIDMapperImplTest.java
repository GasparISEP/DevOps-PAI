package PAI.mapper.courseInStudyPlan;

import PAI.VOs.CourseInStudyPlanGeneratedID;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanGeneratedIDDataModel;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseInStudyPlanGeneratedIDMapperImplTest {

    @Test
    void toDataModel_shouldMapCorrectly() {
        UUID uuid = UUID.randomUUID();
        CourseInStudyPlanGeneratedID domainId = new CourseInStudyPlanGeneratedID(uuid);
        CourseInStudyPlanGeneratedIDMapperImpl mapper = new CourseInStudyPlanGeneratedIDMapperImpl();

        CourseInStudyPlanGeneratedIDDataModel result = mapper.toDataModel(domainId);

        assertNotNull(result);
        assertEquals(uuid, result.getId());
    }

    @Test
    void toDomain_shouldMapCorrectly() {
        UUID uuid = UUID.randomUUID();
        CourseInStudyPlanGeneratedIDDataModel dataModel = new CourseInStudyPlanGeneratedIDDataModel(uuid);
        CourseInStudyPlanGeneratedIDMapperImpl mapper = new CourseInStudyPlanGeneratedIDMapperImpl();

        CourseInStudyPlanGeneratedID result = mapper.toDomain(dataModel);

        assertNotNull(result);
        assertEquals(uuid, result.getId());
    }

    @Test
    void toDataModel_shouldThrowExceptionIfDomainIdIsNull() {
        CourseInStudyPlanGeneratedIDMapperImpl mapper = new CourseInStudyPlanGeneratedIDMapperImpl();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                mapper.toDataModel(null)
        );

        assertEquals("Course In Study Plan Generated ID cannot be null.", ex.getMessage());
    }

    @Test
    void toDomain_shouldThrowExceptionIfDataModelIsNull() {
        CourseInStudyPlanGeneratedIDMapperImpl mapper = new CourseInStudyPlanGeneratedIDMapperImpl();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                mapper.toDomain(null)
        );

        assertEquals("Course In Study Plan Generated ID Data Model cannot be null.", ex.getMessage());
    }
}