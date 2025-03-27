package PAI.VOs;

import PAI.ddd.ValueObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;


public class Date implements ValueObject {

    private final LocalDate localDate;

    public Date(String date) {
        if (date == null || date.isBlank()) {
            throw new IllegalArgumentException("Date cannot be empty!");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            this.localDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use dd-MM-yyyy.");
        }
    }

    public Date(LocalDate localDate) {
        if (localDate == null) {
            throw new IllegalArgumentException("Date cannot be null!");
        }
        this.localDate = localDate;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return Objects.equals(localDate, date.localDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localDate);
    }
}