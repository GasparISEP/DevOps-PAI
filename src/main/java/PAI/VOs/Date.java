package PAI.VOs;

import PAI.ddd.ValueObject;
import java.time.LocalDate;


public class Date  implements ValueObject {
    private final LocalDate localDate;

    public Date(LocalDate localDate) {
        if (localDate == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        this.localDate = localDate;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }
}

