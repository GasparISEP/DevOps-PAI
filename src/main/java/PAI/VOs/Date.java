package PAI.VOs;

import java.time.LocalDate;

public class Date {
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

