package PAI.factory;

import PAI.domain.Programme;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeListArrayListFactory implements ProgrammeListArrayListFactoryInterface{
    public ArrayList<Programme> newProgrammeArrayList() {
        return new ArrayList<>();
    }

    @Override
    public List<Programme> copyProgrammeArrayList(List<Programme> list) {
        return List.of();
    }

}
