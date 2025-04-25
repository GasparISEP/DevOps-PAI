package PAI.mapper;

import PAI.VOs.*;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentFactory;
import PAI.persistence.datamodel.CourseEditionEnrolmentDataModel;
import PAI.persistence.datamodel.CourseEditionEnrolmentIDDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseEditionEnrolmentMapperImplTest {

    @Test
    void toDataModel_WithValidEnrolment_ShouldReturnDataModel() throws Exception {

        // arrange
        ICourseEditionEnrolmentIDMapper idMapperMock = mock(ICourseEditionEnrolmentIDMapper.class);
        ICourseEditionEnrolmentFactory factoryMock = mock(ICourseEditionEnrolmentFactory.class);
        CourseEditionEnrolmentMapperImpl mapper = new CourseEditionEnrolmentMapperImpl(idMapperMock, factoryMock);

        StudentID studentID = new StudentID(1234567);
        CourseEditionID courseEditionID = new CourseEditionID(mock(ProgrammeEditionID.class), mock(CourseInStudyPlanID.class));
        CourseEditionEnrolment enrolment = new CourseEditionEnrolment(studentID, courseEditionID);

        CourseEditionEnrolmentIDDataModel idDataModel = mock(CourseEditionEnrolmentIDDataModel.class);
        when(idMapperMock.toDataModel(any())).thenReturn(Optional.of(idDataModel));

        // act
        Optional<CourseEditionEnrolmentDataModel> result = mapper.toDataModel(enrolment);

        // assert
        assertTrue(result.isPresent());
        assertEquals(idDataModel, result.get().findId());
        assertEquals(enrolment.getEnrolmentDate(), result.get().findEnrolmentDate());
        assertEquals(enrolment.isEnrolmentActive(), result.get().isActive());

        verify(idMapperMock).toDataModel(enrolment.identity());
    }


    @Test
    void toDomain_WithValidDataModel_ShouldReturnEnrolment() throws Exception {
        // arrange
        ICourseEditionEnrolmentIDMapper idMapperMock = mock(ICourseEditionEnrolmentIDMapper.class);
        ICourseEditionEnrolmentFactory factoryMock = mock(ICourseEditionEnrolmentFactory.class);
        CourseEditionEnrolmentMapperImpl mapper = new CourseEditionEnrolmentMapperImpl(idMapperMock, factoryMock);

        CourseEditionEnrolmentDataModel dataModel = mock(CourseEditionEnrolmentDataModel.class);
        CourseEditionEnrolmentIDDataModel idDataModel = mock(CourseEditionEnrolmentIDDataModel.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseEditionEnrolmentID courseEditionEnrolmentID = new CourseEditionEnrolmentID(new StudentID(1234567), new CourseEditionID(programmeEditionID,courseInStudyPlanID));

        when(dataModel.findId()).thenReturn(idDataModel);
        when(idMapperMock.toDomain(idDataModel)).thenReturn(Optional.of(courseEditionEnrolmentID));
        when(dataModel.findEnrolmentDate()).thenReturn(LocalDate.now());
        when(dataModel.isActive()).thenReturn(true);

        StudentID studentID = courseEditionEnrolmentID.getStudentID();
        CourseEditionID courseEditionID = courseEditionEnrolmentID.getCourseEditionID();
        CourseEditionEnrolment enrolment = new CourseEditionEnrolment(studentID, courseEditionID);

        when(factoryMock.createWithEnrolmentDate(studentID, courseEditionID, LocalDate.now(), true)).thenReturn(enrolment);

        // act
        Optional<CourseEditionEnrolment> result = mapper.toDomain(dataModel);

        // assert
        assertTrue(result.isPresent());
        assertEquals(enrolment, result.get());
    }

}