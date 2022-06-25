package su356076.corejava.rating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import su356076.corejava.rating.model.Assignment;
import su356076.corejava.rating.model.StudentScore;
import su356076.corejava.rating.model.SubjectScore;
import su356076.corejava.rating.service.RatingStudentService;

import java.util.Scanner;

import static java.time.LocalDate.of;
import static su356076.corejava.rating.model.Distributions.*;
import static su356076.corejava.rating.model.Distributions.TEST;

@SpringBootApplication
public class Su356076CoreJavaRatingApplication {

	public static void main(String[] args) {
		SpringApplication.run(Su356076CoreJavaRatingApplication.class, args);
		final RatingStudentService studentService = new RatingStudentService();
		studentService.add(new Assignment(1, "Ananth", "Electro Fields", TEST, of(2016,7,21), 100));
		studentService.add(new Assignment(2, "Bhagath", "Electro Fields", TEST, of(2016,7,21), 78));
		studentService.add(new Assignment(3, "Chaya", "Electro Fields", TEST, of(2016,7,21), 68));
		studentService.add(new Assignment(4, "Esharath", "Electro Fields", TEST, of(2016,7,21), 87));
		studentService.add(new Assignment(5, "Bhagath", "Electro Fields", QUIZ, of(2016,7,21), 20));
		studentService.add(new Assignment(6, "Chaya", "Electro Fields", LAB_WORK, of(2016,7,23), 10));
		studentService.add(new Assignment(7, "Ananth", "Electro Fields", PROJECT, of(2016,7,24), 100));
		studentService.add(new Assignment(8, "Davanth", "Electro Fields", PROJECT, of(2016,7,24), 100));
		studentService.add(new Assignment(9, "Bhagath", "Electro Fields", QUIZ, of(2016,7,25), 50));
		studentService.add(new Assignment(10, "Ananth", "Electro Fields", QUIZ, of(2016,7,26), 100));
		studentService.add(new Assignment(11, "Bhagath", "Electro Fields", LAB_WORK, of(2016,7,27), 10));
		studentService.add(new Assignment(12, "Chaya", "Electro Fields", PROJECT, of(2016,7,28), 100));
		studentService.add(new Assignment(13, "Bhagath", "Electro Fields", PROJECT, of(2016,7,28), 100));
		studentService.add(new Assignment(14, "Ananth", "Computing Techniques", TEST, of(2016,7,29), 86));
		studentService.add(new Assignment(15, "Ananth", "Electro Fields", QUIZ, of(2016,7,29), 100));
		studentService.add(new Assignment(16, "Bhagath", "Computing Techniques", PROJECT, of(2016,7,30), 100));
		studentService.add(new Assignment(17, "Ananth", "Electro Fields", LAB_WORK, of(2016,7,30), 100));
		studentService.add(new Assignment(18, "Chaya", "Computing Techniques", QUIZ, of(2016,7,31), 20));
		studentService.add(new Assignment(19, "Ananth", "Electro Fields", TEST, of(2016,8,1), 100));
		studentService.add(new Assignment(20, "Chaya", "Electro Fields", TEST, of(2016,8,1), 92));

		System.out.println("Enter 1 for Student or 2 for Subject");
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			int choice = scanner.nextInt();
			System.out.println("Enter the name");
			String name = scanner.next();

			if (choice == 1){
				var subjectScores = studentService.findRatingByStudentName(name);
				System.out.println("Student: "+ name);
				System.out.printf("%-20s | ", "Subject");
				System.out.printf("%-10s | ", "Test Score");
				System.out.printf("%-10s | ", "Quiz Score");
				System.out.printf("%-10s | ", "Lab Score");
				System.out.printf("%-15s | ", "Project Score");
				System.out.printf("%-10s %n", "Overall Rating");
				for (SubjectScore score : subjectScores){
					System.out.printf("%-10s | ", score.getSubject());
					System.out.printf("%-10s | ", score.getTestScore());
					System.out.printf("%-10s | ", score.getQuizScore());
					System.out.printf("%-10s | ", score.getLabScore());
					System.out.printf("%-15s | ", score.getProjectScore());
					System.out.printf("%-10s %n", score.getOverallRating());
				}
			} else if (choice == 2){
				String subject = scanner.next();
				String subjectName = name+" "+subject;
				var studentScores = studentService.findRatingsBySubjectName(subjectName);
				System.out.println("Subject: "+ subjectName);
				System.out.printf("%-10s | ", "Student name");
				System.out.printf("%-10s | ", "Test Score");
				System.out.printf("%-10s | ", "Quiz Score");
				System.out.printf("%-10s | ", "Lab Score");
				System.out.printf("%-15s | ", "Project Score");
				System.out.printf("%-10s %n", "Overall Rating");
				for(StudentScore score: studentScores){
					System.out.printf("%-12s | ", score.getStudentName());
					System.out.printf("%-10s | ", score.getTestScore());
					System.out.printf("%-10s | ", score.getQuizScore());
					System.out.printf("%-10s | ", score.getLabScore());
					System.out.printf("%-15s | ", score.getProjectScore());
					System.out.printf("%-10s %n", score.getOverallRating());
				}
			} else {
				System.out.println("Invalid choice");
				System.exit(0);
			}
			System.exit(0);
		}

	}

}
