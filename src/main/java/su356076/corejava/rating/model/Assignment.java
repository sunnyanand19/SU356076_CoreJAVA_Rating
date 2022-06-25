package su356076.corejava.rating.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Assignment {

    private final int id;
    private final String studentName;
    private final String subject;
    private final Distributions assignmentCategory;
    private final LocalDate dateOfSubmission;
    private final int points;

}
