package su356076.corejava.rating.service;

import lombok.extern.log4j.Log4j2;
import su356076.corejava.rating.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static su356076.corejava.rating.model.Distributions.*;

@Log4j2
public class RatingStudentService {

    private final List<Assignment> assignments = new ArrayList<>();
    private final List<SubjectScore> result = new ArrayList<>();
    private final List<StudentScore> subjectResult = new ArrayList<>();

    public void add(Assignment assignment) {
        assignments.add(assignment);
    }

    public List<Assignment> findAssignmentByStudentName(String studentName) {
        return assignments.stream().filter(assignment -> assignment.getStudentName().equals(studentName)).collect(toList());
    }


    public List<SubjectScore> findRatingByStudentName(String studentName) {
        var assignmentMap = assignments.stream()
                .filter(assignment -> assignment.getStudentName().equals(studentName))
                .collect(groupingBy(Assignment::getSubject, groupingBy(Assignment::getAssignmentCategory)));
        assignmentMap.entrySet().stream().forEach(entry -> calculateScores(entry.getKey(),entry.getValue()));
        return result;
    }

    public List<StudentScore> findRatingsBySubjectName(String subjectName) {
        log.info("findRatingsBySubjectName called for {}", subjectName);
            var assignmentMap = assignments.stream()
                    .filter(assignment -> assignment.getSubject().equals(subjectName))
                    .collect(groupingBy(Assignment::getStudentName, groupingBy(Assignment::getAssignmentCategory)));
            assignmentMap.entrySet().stream().forEach(entry -> calculateScoresBySubject(entry.getKey(), entry.getValue()));
        return subjectResult;
    }


    private void calculateScores(String key, Map<Distributions, List<Assignment>> value) {
        log.info("Number of times calculate score called for key {}", key);
        SubjectScore subjectScore = new SubjectScore();
        subjectScore.setSubject(key);
        value.entrySet().forEach(entry -> calculateIndividualScores(entry.getKey(),entry.getValue(), subjectScore));
        subjectScore.setOverallRating(getOverallScore(subjectScore));
        result.add(subjectScore);
    }

    private void calculateScoresBySubject(String key, Map<Distributions, List<Assignment>> value) {
        log.info("Number of times calculate score called for key {}", key);
        StudentScore studentScore = new StudentScore();
        studentScore.setStudentName(key);
        value.entrySet().forEach(entry -> calculateIndividualScores(entry.getKey(),entry.getValue(), studentScore));
        studentScore.setOverallRating(getOverallScore(studentScore));
        subjectResult.add(studentScore);
    }

    private void calculateIndividualScores(Distributions key, List<Assignment> value, Score score) {
        log.info("calculateIndividualScores called for key {}", key);
        if(key == TEST){
            double testScore = 0;
            for(Assignment assignment : value){
                testScore += (TEST.weight/value.size())*assignment.getPoints();
            }
            testScore = testScore/100;
            score.setTestScore(String.valueOf(testScore));

        }else if (key == QUIZ){
            double quizScore = 0;
            for(Assignment assignment : value){
                quizScore += (QUIZ.weight/value.size())*assignment.getPoints();
            }
            quizScore = quizScore/100;
            score.setQuizScore(String.valueOf(quizScore));
        }else if (key == LAB_WORK){
            double labScore = 0;
            for(Assignment assignment : value){
                labScore += (LAB_WORK.weight/value.size())*assignment.getPoints();
            }
            labScore = labScore/100;
            score.setLabScore(String.valueOf(labScore));
        }else {
            double projectScore = 0;
            for(Assignment assignment : value){
                projectScore += (PROJECT.weight/value.size())*assignment.getPoints();
            }
            projectScore = projectScore/100;
            score.setProjectScore(String.valueOf(projectScore));
        }
    }

    private String getOverallScore(Score score){
        double testScore = score.getTestScore() != null ? Double.valueOf(score.getTestScore()) : Double.valueOf(0);
        double quizScore = score.getQuizScore() != null ? Double.valueOf(score.getQuizScore()) : Double.valueOf(0);
        double labScore = score.getLabScore() != null ? Double.valueOf(score.getLabScore()) : Double.valueOf(0);
        double projectScore = score.getProjectScore() != null ? Double.valueOf(score.getProjectScore()) : Double.valueOf(0);
        double overallScore = testScore+quizScore+labScore+projectScore;
        return String.valueOf(overallScore);
    }

}
