package PAI.mapper.courseEditionEnrolment;

import PAI.VOs.CourseEditionEnrolmentGeneratedID;
import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.mapper.teacherCategory.ITeacherCategoryIDMapper;
import PAI.mapper.teacherCategory.ITeacherCategoryMapper;
import PAI.mapper.teacherCategory.TeacherCategoryMapperImpl;
import PAI.persistence.datamodel.courseEditionEnrolment.CourseEditionEnrolmentGeneratedIDDataModel;
import PAI.persistence.datamodel.teacherCategory.TeacherCategoryDataModel;
import PAI.persistence.datamodel.teacherCategory.TeacherCategoryIDDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseEditionEnrolmentGeneratedIDMapperImplTest {

    @Test
    void shouldReturnDomainFromDataModel() throws Exception {
        //Arrange
        CourseEditionEnrolmentGeneratedIDMapperImpl mapper = new CourseEditionEnrolmentGeneratedIDMapperImpl();
        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDataModelDouble = mock(CourseEditionEnrolmentGeneratedIDDataModel.class);

        when(ceeGeneratedIDDataModelDouble.getGeneratedID()).thenReturn(UUID.fromString("e3b7e64f-1f42-4626-8e75-f4c244fabf29"));

        //Act
        CourseEditionEnrolmentGeneratedID result = mapper.toDomain(ceeGeneratedIDDataModelDouble);

        //Assert
        assertEquals(UUID.fromString("e3b7e64f-1f42-4626-8e75-f4c244fabf29"), result.getCourseEditionEnrolmentGeneratedID());
    }

    @Test
    void shouldReturnDataModelFromDomain() throws Exception{
        //Arrange
        CourseEditionEnrolmentGeneratedIDMapperImpl mapper = new CourseEditionEnrolmentGeneratedIDMapperImpl();
        CourseEditionEnrolmentGeneratedID ceeGeneratedID = mock(CourseEditionEnrolmentGeneratedID.class);

        when(ceeGeneratedID.getCourseEditionEnrolmentGeneratedID()).thenReturn(UUID.fromString("e3b7e64f-1f42-4626-8e75-f4c244fabf29"));

        //Act
        CourseEditionEnrolmentGeneratedIDDataModel result = mapper.toDataModel(ceeGeneratedID);

        //Assert
        assertEquals("e3b7e64f-1f42-4626-8e75-f4c244fabf29", result.getGeneratedID().toString());
    }
}