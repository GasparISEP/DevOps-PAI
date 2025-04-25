package PAI.service;

import PAI.factory.IProgrammeFactory;
import PAI.repository.programmeRepository.IProgrammeRepository;
import org.springframework.stereotype.Service;

@Service
public class ProgrammeService implements IProgrammeService {

    private final IProgrammeFactory _programmeFactory;
    private final IProgrammeRepository _programmeRepository;

    public ProgrammeService(IProgrammeFactory programmeFactory, IProgrammeRepository programmeRepository) {
        if (programmeFactory == null) {
            throw new IllegalArgumentException("Programme Factory cannot be null");
        }
        _programmeFactory = programmeFactory;

        if (programmeRepository == null) {
            throw new IllegalArgumentException("Programme Repository cannot be null");
        }
        _programmeRepository = programmeRepository;
    }
}
