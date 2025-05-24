package PAI.service.teacherCareerProgression;

import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.repositoryInterfaces.teacherCareerProgression.ITeacherCareerProgressionRepository;
import PAI.domain.repositoryInterfaces.teacherCategory.ITeacherCategoryRepository;
import PAI.domain.teacherCareerProgression.ITeacherCareerProgressionFactory;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.dto.teacherCareerProgression.ITeacherCareerProgressionAssembler;
import PAI.dto.teacherCareerProgression.TeacherCategoryUpdateResponseDTO;
import PAI.dto.teacherCareerProgression.TeacherWorkingPercentageUpdateDTO;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryCommand;

import java.util.Optional;

public class TeacherCareerProgressionServiceImplV2 implements ITeacherCareerProgressionServiceV2 {
    private ITeacherCareerProgressionRepository _TCPrepository;
    private ITeacherCareerProgressionFactory _TCPfactory;
    private ITeacherRepository _teacherRepo;
    private ITeacherCategoryRepository _teacherCategoryRepo;
    private ITeacherCareerProgressionAssembler _teacherCareerProgressionAssembler;

    public TeacherCareerProgressionServiceImplV2(ITeacherCareerProgressionRepository teacherCareerProgressionRepository, ITeacherCareerProgressionFactory teacherCareerProgressionFactory, ITeacherRepository teacherRepository, ITeacherCategoryRepository teacherCategoryRepository, ITeacherCareerProgressionAssembler teacherCareerProgressionAssembler){
        _TCPrepository = teacherCareerProgressionRepository;
        _TCPfactory = teacherCareerProgressionFactory;
        _teacherRepo = teacherRepository;
        _teacherCategoryRepo = teacherCategoryRepository;
        _teacherCareerProgressionAssembler = teacherCareerProgressionAssembler;

    }

    @Override
    public Optional<TeacherCareerProgression> createTeacherCareerProgression(Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage wp, TeacherID teacherID) throws Exception {
        if (date == null || teacherCategoryID == null || wp == null || teacherID == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }

        TeacherCareerProgression tcp = _TCPfactory.createTeacherCareerProgression(date, teacherCategoryID, wp, teacherID);

        if (_TCPrepository.containsOfIdentity(tcp.getID())) {
            return Optional.empty();
        }

        _TCPrepository.save(tcp);

        return Optional.of(tcp);
    }

    @Override
    public Optional<TeacherCareerProgression> updateTeacherCategoryInTeacherCareerProgression(UpdateTeacherCategoryCommand command) throws Exception {
        if (!_teacherRepo.containsOfIdentity(command.teacherID()) || !_teacherCategoryRepo.containsOfIdentity(command.teacherCategoryID()))
            return Optional.empty();
        Optional<TeacherCareerProgression> optionalTCP = _TCPrepository.findLastTCPFromTeacherID(command.teacherID());
        if (optionalTCP.isEmpty())
            return Optional.empty();
        TeacherCareerProgression lastTeacherCareerProgression = optionalTCP.get();

        if(!lastTeacherCareerProgression.isLastDateEqualOrBeforeNewDate(command.date()))
            return Optional.empty();
        WorkingPercentage workingPercentage = lastTeacherCareerProgression.getWorkingPercentage();
        if (lastTeacherCareerProgression.getTeacherCategoryID().equals(command.teacherCategoryID()))
            return  Optional.empty();
        return createTeacherCareerProgression(command.date(), command.teacherCategoryID(), workingPercentage, command.teacherID());
    }

    @Override
    public Optional<TeacherWorkingPercentageUpdateDTO> updateTeacherWorkingPercentageInTeacherCareerProgression(Date date, WorkingPercentage workingPercentage, TeacherID teacherAcronym) throws Exception {
        if (!_teacherRepo.containsOfIdentity(teacherAcronym))
            return Optional.empty();

        Optional<TeacherCareerProgression> optionalTCP = _TCPrepository.findLastTCPFromTeacherID(teacherAcronym);
        if (optionalTCP.isEmpty())
            return Optional.empty();

        TeacherCareerProgression lastTeacherCareerProgression = optionalTCP.get();

        if (!lastTeacherCareerProgression.isLastDateEqualOrBeforeNewDate(date))
            return Optional.empty();

        if(lastTeacherCareerProgression.getWorkingPercentage().equals(workingPercentage))
            return Optional.empty();

        TeacherCategoryID teacherCategoryID = lastTeacherCareerProgression.getTeacherCategoryID();
        Optional<TeacherCareerProgression> newTCP = createTeacherCareerProgression(date, teacherCategoryID, workingPercentage, teacherAcronym);

        return newTCP.map(teacherCareerProgression -> _teacherCareerProgressionAssembler.toTeacherWorkingPercentageUpdateDTO(teacherCareerProgression));

    }
}
