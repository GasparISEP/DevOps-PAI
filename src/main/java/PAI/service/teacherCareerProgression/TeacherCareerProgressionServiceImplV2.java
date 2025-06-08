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
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryCommand;
import PAI.dto.teacherCareerProgression.UpdateTeacherWorkingPercentageCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TeacherCareerProgressionServiceImplV2 implements ITeacherCareerProgressionServiceV2 {
    private ITeacherCareerProgressionRepository _TCPrepository;
    private ITeacherCareerProgressionFactory _TCPfactory;
    private ITeacherRepository _teacherRepo;

    public TeacherCareerProgressionServiceImplV2(ITeacherCareerProgressionRepository teacherCareerProgressionRepository,
                                                 ITeacherCareerProgressionFactory teacherCareerProgressionFactory,
                                                 ITeacherRepository teacherRepository){
        _TCPrepository = teacherCareerProgressionRepository;
        _TCPfactory = teacherCareerProgressionFactory;
        _teacherRepo = teacherRepository;
    }

    @Override
    public Optional<TeacherCareerProgression> createTeacherCareerProgression(Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage wp, TeacherID teacherID) throws Exception {

        TeacherCareerProgression tcp = _TCPfactory.createTeacherCareerProgression(date, teacherCategoryID, wp, teacherID);

        if (_TCPrepository.containsOfIdentity(tcp.getID())) {
            return Optional.empty();
        }

        _TCPrepository.save(tcp);

        return Optional.of(tcp);
    }

    @Override
    public Optional<TeacherCareerProgression> updateTeacherCategory(UpdateTeacherCategoryCommand command) throws Exception {

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
    public Optional<TeacherCareerProgression> updateTeacherWorkingPercentageInTeacherCareerProgression(UpdateTeacherWorkingPercentageCommand command) throws Exception {
        if (!_teacherRepo.containsOfIdentity(command.teacherID()))
            return Optional.empty();
        Optional<TeacherCareerProgression> optionalTCP = _TCPrepository.findLastTCPFromTeacherID(command.teacherID());
        if (optionalTCP.isEmpty())
            return Optional.empty();
        TeacherCareerProgression lastTeacherCareerProgression = optionalTCP.get();

        if(!lastTeacherCareerProgression.isLastDateEqualOrBeforeNewDate(command.date()))
            return Optional.empty();
        TeacherCategoryID teacherCategoryID = lastTeacherCareerProgression.getTeacherCategoryID();
        if (lastTeacherCareerProgression.getWorkingPercentage().equals(command.workingPercentage()))
            return  Optional.empty();
        return createTeacherCareerProgression(command.date(), teacherCategoryID, command.workingPercentage(), command.teacherID());
    }
}
