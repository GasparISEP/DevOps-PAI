
package PAI.mapper.TeacherCareerProgression;

import PAI.VOs.*;
import PAI.domain.TeacherCareerProgression;
import PAI.factory.ITeacherCareerProgressionFactory;
import PAI.persistence.datamodel.TeacherCareerProgressionDataModel;
import PAI.persistence.datamodel.TeacherCareerProgressionIDDataModel;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherCareerProgressionMapperImplTest {

    private ITeacherCareerProgressionIDMapper idMapper = mock(ITeacherCareerProgressionIDMapper.class);

    @Test
    void shouldCreateDomainObject() {
        // arrange
        UUID teacherCareerProgressionID = UUID.randomUUID();
        UUID teacherCategoryId = UUID.randomUUID();
        int workingPercentage = 50;
        LocalDate date = LocalDate.of(2022, 4, 17);
        String teacherId = "ABC";

        TeacherCareerProgressionDataModel tcpDataModel = mock(TeacherCareerProgressionDataModel.class);

        ITeacherCareerProgressionFactory tcpFactory = mock(ITeacherCareerProgressionFactory.class);
        TeacherCareerProgressionMapperImpl mapper = new TeacherCareerProgressionMapperImpl(tcpFactory,idMapper);

        TeacherCareerProgression tcp = mock(TeacherCareerProgression.class);
        TeacherCareerProgressionIDDataModel tcpIDDataModel = mock (TeacherCareerProgressionIDDataModel.class);

        when(tcpDataModel.getId()).thenReturn(tcpIDDataModel);
        when(tcpIDDataModel.getIDValue()).thenReturn(teacherCareerProgressionID);
        when(tcpDataModel.getDate()).thenReturn(date);
        when(tcpDataModel.getTeacherCategoryId()).thenReturn(teacherCategoryId);
        when(tcpDataModel.getWorkingPercentage()).thenReturn(workingPercentage);
        when(tcpDataModel.getTeacherId()).thenReturn(teacherId);

        when(tcpFactory.createTeacherCareerProgressionFromDataModel(
             any(TeacherCareerProgressionID.class), any(Date.class), any(TeacherCategoryID.class), any(WorkingPercentage.class),any(TeacherID.class)
        )).thenReturn(tcp);

       try(MockedConstruction<TeacherCareerProgressionID> tcpIDConstructor = mockConstruction(TeacherCareerProgressionID.class);
           MockedConstruction<Date> dateConstructor = mockConstruction(Date.class);
           MockedConstruction<TeacherCategoryID> teacherCategoryIDConstructor = mockConstruction(TeacherCategoryID.class);
           MockedConstruction<WorkingPercentage> workingPercentageConstructor = mockConstruction(WorkingPercentage.class);
           MockedConstruction<TeacherAcronym> teacherAcronymConstructor = mockConstruction(TeacherAcronym.class);
           MockedConstruction<TeacherID> teacherIDConstructor = mockConstruction(TeacherID.class)) {

           //act
           TeacherCareerProgression domain = mapper.toDomain(tcpDataModel);

           // assert
           assertNotNull(domain);
       }
    }

    @Test
    void shouldCreateTCPDataModel() {
        // arrange
        UUID teacherCareerProgressionID = UUID.randomUUID();
        UUID teacherCategoryId = UUID.randomUUID();
        int workingPercentage = 50;
        LocalDate date = LocalDate.of(2022, 4, 17);
        String teacherId = "ABC";

        // domain objects
        TeacherCareerProgressionID domainTCPId = new TeacherCareerProgressionID(teacherCareerProgressionID);
        Date domainDate = new Date(date);
        TeacherCategoryID domainTeacherCategoryId = new TeacherCategoryID(teacherCategoryId);
        WorkingPercentage domainWorkingPercentage = new WorkingPercentage(workingPercentage);
        TeacherID domainTeacherID = new TeacherID(new TeacherAcronym(teacherId));

        TeacherCareerProgression domain = new TeacherCareerProgression(domainTCPId, domainDate, domainTeacherCategoryId,
                domainWorkingPercentage, domainTeacherID
        );

        // stub the ID‐mapper
        TeacherCareerProgressionIDDataModel mockedTcpID = new TeacherCareerProgressionIDDataModel(teacherCareerProgressionID);
        ITeacherCareerProgressionIDMapper tcpIDMapper   = mock(ITeacherCareerProgressionIDMapper.class);
        when(tcpIDMapper.domainToDataModel(domain.identity()))
                .thenReturn(mockedTcpID);

        TeacherCareerProgressionMapperImpl mapper = new TeacherCareerProgressionMapperImpl(
                mock(ITeacherCareerProgressionFactory.class),
                tcpIDMapper
        );

        // act
        TeacherCareerProgressionDataModel dataModel = mapper.toDataModel(domain);

        // assert — field-by-field
        assertNotNull(dataModel);
    }

}
