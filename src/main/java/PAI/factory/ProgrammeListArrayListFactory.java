package PAI.factory;

import PAI.domain.Programme;

import java.util.ArrayList;

public class ProgrammeListArrayListFactory implements ProgrammeListArrayListFactoryInterface{
    public ArrayList<Programme> newProgrammeArrayList() {
        return new ArrayList<>();
    }
}
