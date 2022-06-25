package su356076.corejava.rating.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import su356076.corejava.rating.model.Assignment;
import su356076.corejava.rating.model.StudentScore;
import su356076.corejava.rating.model.SubjectScore;

import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;
import static su356076.corejava.rating.model.Distributions.*;

class RatingStudentServiceTest {

    private final RatingStudentService service = new RatingStudentService();

    @BeforeEach
    void setUp(){
        service.add(new Assignment(1, "Ananth", "Electro Fields", TEST, of(2016,7,21), 100));
        service.add(new Assignment(2, "Bhagath", "Electro Fields", TEST, of(2016,7,21), 78));
        service.add(new Assignment(3, "Chaya", "Electro Fields", TEST, of(2016,7,21), 68));
        service.add(new Assignment(4, "Esharath", "Electro Fields", TEST, of(2016,7,21), 87));
        service.add(new Assignment(5, "Bhagath", "Electro Fields", QUIZ, of(2016,7,21), 20));
        service.add(new Assignment(6, "Chaya", "Electro Fields", LAB_WORK, of(2016,7,23), 10));
        service.add(new Assignment(7, "Ananth", "Electro Fields", PROJECT, of(2016,7,24), 100));
        service.add(new Assignment(8, "Davanth", "Electro Fields", PROJECT, of(2016,7,24), 100));
        service.add(new Assignment(9, "Bhagath", "Electro Fields", QUIZ, of(2016,7,25), 50));
        service.add(new Assignment(10, "Ananth", "Electro Fields", QUIZ, of(2016,7,26), 100));
        service.add(new Assignment(11, "Bhagath", "Electro Fields", LAB_WORK, of(2016,7,27), 10));
        service.add(new Assignment(12, "Chaya", "Electro Fields", PROJECT, of(2016,7,28), 100));
        service.add(new Assignment(13, "Bhagath", "Electro Fields", PROJECT, of(2016,7,28), 100));
        service.add(new Assignment(14, "Ananth", "Computing Techniques", TEST, of(2016,7,29), 86));
        service.add(new Assignment(15, "Ananth", "Electro Fields", QUIZ, of(2016,7,29), 100));
        service.add(new Assignment(16, "Bhagath", "Computing Techniques", PROJECT, of(2016,7,30), 100));
        service.add(new Assignment(17, "Ananth", "Electro Fields", LAB_WORK, of(2016,7,30), 100));
        service.add(new Assignment(18, "Chaya", "Computing Techniques", QUIZ, of(2016,7,31), 20));
        service.add(new Assignment(19, "Ananth", "Electro Fields", TEST, of(2016,8,1), 100));
        service.add(new Assignment(20, "Chaya", "Electro Fields", TEST, of(2016,8,1), 92));
    }

    @Test
    void returnNoResultWhenSearchedNameDoesNotMatch(){
        List<Assignment> assignments = service.findAssignmentByStudentName("Student");
        assertThat(assignments).isEmpty();
    }

    @Test
    void findsAssignmentsWhenSearchedNameMatches(){
        List<Assignment> assignments = service.findAssignmentByStudentName("Ananth");
        assertThat(assignments).hasSize(7);
    }

    @Test
    void returnNoRatingsWhenSearchedNameDoesNotMatch(){
        List<SubjectScore> rating = service.findRatingByStudentName("Student");
        assertThat(rating).isEmpty();
    }

    @Test
    void findsRatingsWhenSearchedNameMatches(){
        SubjectScore computingScore = new SubjectScore();
        computingScore.setSubject("Computing Techniques");
        computingScore.setTestScore("34.4");
        computingScore.setQuizScore(null);
        computingScore.setLabScore(null);
        computingScore.setProjectScore(null);
        computingScore.setOverallRating("34.4");

        SubjectScore electroFields = new SubjectScore();
        electroFields.setSubject("Electro Fields");
        electroFields.setTestScore("40.0");
        electroFields.setQuizScore("20.0");
        electroFields.setLabScore("10.0");
        electroFields.setProjectScore("30.0");
        electroFields.setOverallRating("100.0");

        List<SubjectScore> expected = Arrays.asList(computingScore, electroFields);

        List<SubjectScore> rating = service.findRatingByStudentName("Ananth");

        assertThat(rating).hasSize(2).isEqualTo(expected);
    }

    @Test
    void findsRatingsWhenSearchedSubjectMatches(){
        StudentScore davanth = new StudentScore();
        davanth.setStudentName("Davanth");
        davanth.setTestScore(null);
        davanth.setQuizScore(null);
        davanth.setLabScore(null);
        davanth.setProjectScore("30.0");
        davanth.setOverallRating("30.0");

        StudentScore bhagath = new StudentScore();
        bhagath.setStudentName("Bhagath");
        bhagath.setTestScore("31.2");
        bhagath.setQuizScore("7.0");
        bhagath.setLabScore("1.0");
        bhagath.setProjectScore("30.0");
        bhagath.setOverallRating("69.2");

        StudentScore chaya = new StudentScore();
        chaya.setStudentName("Chaya");
        chaya.setTestScore("32.0");
        chaya.setQuizScore(null);
        chaya.setLabScore("1.0");
        chaya.setProjectScore("30.0");
        chaya.setOverallRating("63.0");

        StudentScore ananth = new StudentScore();
        ananth.setStudentName("Ananth");
        ananth.setTestScore("40.0");
        ananth.setQuizScore("20.0");
        ananth.setLabScore("10.0");
        ananth.setProjectScore("30.0");
        ananth.setOverallRating("100.0");

        StudentScore esharath = new StudentScore();
        esharath.setStudentName("Esharath");
        esharath.setTestScore("34.8");
        esharath.setQuizScore(null);
        esharath.setLabScore(null);
        esharath.setProjectScore(null);
        esharath.setOverallRating("34.8");

        List<StudentScore> expected = Arrays.asList(davanth, bhagath, chaya, ananth, esharath);
        List<StudentScore> ratings= service.findRatingsBySubjectName("Electro Fields");

        assertThat(ratings).hasSize(5).isEqualTo(expected);

    }


}