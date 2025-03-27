package PAI.VOs;

import PAI.ddd.ValueObject;

public class WorkingPercentage implements ValueObject {

    private final int _workingPercentage;

    public WorkingPercentage(int workingPercentage) {

        if(!isWorkingPercentageValid(workingPercentage))
            throw new IllegalArgumentException("Working Percentage must be a value between 0 and 100.");

        _workingPercentage = workingPercentage;

    }

    private boolean isWorkingPercentageValid(int workingPercentage) {

        return workingPercentage >= 0 && workingPercentage <= 100;
    }

}
