package PAI.service.teacherCareerProgression;

import PAI.VOs.*;
import PAI.assembler.teacherCareerProgression.ITeacherCareerProgressionInternalAssembler;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.repositoryInterfaces.teacherCareerProgression.ITeacherCareerProgressionRepository;

import PAI.domain.teacherCareerProgression.ITeacherCareerProgressionFactory;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryCommand;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryDTO;
import PAI.dto.teacherCareerProgression.UpdateTeacherWorkingPercentageCommand;
import PAI.exception.BusinessRuleViolationException;
import PAI.exception.NotFoundException;
import org.springframework.stereotype.Service;
import static PAI.utils.ValidationUtils.validateNotNull;

import java.util.List;
import java.util.Optional;

@Service
public class CreateTeacherCareerProgressionServiceImpl implements ICreateTeacherCareerProgressionService {
    private ITeacherCareerProgressionRepository _TCPrepository;
    private ITeacherCareerProgressionFactory _TCPfactory;
    private ITeacherRepository _teacherRepo;
    private ITeacherCareerProgressionInternalAssembler _internalAssembler;

    public CreateTeacherCareerProgressionServiceImpl(ITeacherCareerProgressionRepository teacherCareerProgressionRepository,
                                                     ITeacherCareerProgressionFactory teacherCareerProgressionFactory,
                                                     ITeacherRepository teacherRepository,
                                                     ITeacherCareerProgressionInternalAssembler internalAssembler) {

        _TCPrepository = validateNotNull(teacherCareerProgressionRepository, "Teacher Career Progression Repository Interface");
        _TCPfactory = validateNotNull (teacherCareerProgressionFactory, "Teacher Career Progression Factory Interface");
        _teacherRepo = validateNotNull(teacherRepository, "Teacher Repository Interface");
        _internalAssembler = validateNotNull(internalAssembler, "Teacher Career Progression Internal Assembler Interface");
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
    public UpdateTeacherCategoryDTO updateTeacherCategory(UpdateTeacherCategoryCommand command) throws Exception {

        Optional<TeacherCareerProgression> optionalTCP =
                _TCPrepository.findLastTCPFromTeacherID(command.teacherID());

        if (optionalTCP.isEmpty())
            throw new NotFoundException("This teacher has no previous career progression record. Please create one before attempting an update.");

        TeacherCareerProgression lastTeacherCareerProgression = optionalTCP.get();

        if(!lastTeacherCareerProgression.isLastDateEqualOrBeforeNewDate(command.date()))
            throw new BusinessRuleViolationException("The date must be equal to or later than the previous update.");

        if (lastTeacherCareerProgression.getTeacherCategoryID().equals(command.teacherCategoryID()))
            throw new BusinessRuleViolationException("The Teacher Category must be different to the previous update.");

        WorkingPercentage workingPercentage = lastTeacherCareerProgression.getWorkingPercentage();

        Optional<TeacherCareerProgression> teacherCareerProgression =
                createTeacherCareerProgression(command.date(), command.teacherCategoryID(), workingPercentage, command.teacherID());

        return _internalAssembler.toDTO(teacherCareerProgression.get());
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

    public List <UpdateTeacherCategoryDTO> getAllTeacherCareerProgression (){
        Iterable <TeacherCareerProgression> listTCP = _TCPrepository.findAll();

        return _internalAssembler.toDTOList(listTCP);
    }

    public UpdateTeacherCategoryDTO getTeacherCareerProgressionByID (TeacherCareerProgressionID id) {
        if (id == null){
            throw new IllegalArgumentException("Teacher Career Progression ID is required!");
        }

        Optional <TeacherCareerProgression> teacherCareerProgressionOpt = _TCPrepository.ofIdentity(id);

        if (teacherCareerProgressionOpt.isEmpty()){
            throw new NotFoundException("This teacher career progression id does not exist!");
        }

        return _internalAssembler.toDTO(teacherCareerProgressionOpt.get());
    }

}
