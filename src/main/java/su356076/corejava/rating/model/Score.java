package su356076.corejava.rating.model;

import lombok.Data;

@Data
public class Score {
    private String testScore;
    private String quizScore;
    private String labScore;
    private String projectScore;
    private String overallRating;
}
