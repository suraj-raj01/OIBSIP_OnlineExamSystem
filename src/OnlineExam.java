import java.util.*;

public class OnlineExam {
    private String username;
    private String password;
    private boolean isLoggedIn;
    private int timeRemaining;
    private int questionCount;
    private int[] userAnswers;
    private int[] correctAnswers;

    public OnlineExam(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("Sucessfully You are registered!!");
        this.isLoggedIn = false;
        this.timeRemaining = 10;
        this.questionCount = 10;
        this.userAnswers = new int[questionCount];
        this.correctAnswers = new int[questionCount];

        for (int i = 0; i < questionCount; i++) {
            correctAnswers[i] = (int) Math.round(Math.random());
        }
    }

    public void login() {
        System.out.println("\nKindely LogIn To Give Exam ");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String inputUsername = scanner.nextLine();
        System.out.print("Password: ");
        String inputPassword = scanner.nextLine();
        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            isLoggedIn = true;
            System.out.println("Login successfully\n Best of Luck Dear");
        } else {
            System.out.println("Login failed. Please try again.");
        }
    }

    public void logout() {
        isLoggedIn = false;
        System.out.println("Logout successful.");
    }

    public void startExam() {
        if (!isLoggedIn) {
            System.out.println("Please login first.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("You have " + timeRemaining + " minutes to complete the exam.");
        for (int i = 0; i < questionCount; i++) {
            System.out.println("*******************************");
            System.out.println("*    Question " + (i + 1) + ":              *");
            System.out.println("*    a). Option 1             *");
            System.out.println("*    b). Option 2             *");
            System.out.println("*******************************");
            System.out.print("Your answer (1 or 2): ");
            int answer = scanner.nextInt();
            userAnswers[i] = answer;
        }

        System.out.println("Would you like to submit? \n1:Yes \n2:NO ");
        int n = scanner.nextInt();
        if (n == 1) {
            submitExam();
        } else {
            // Auto submit exam whent the time is over
            try {
                Thread.sleep(timeRemaining * 10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                submitExam();
            }

        }

    }

    public void submitExam() {
        if (!isLoggedIn) {
            System.out.println("Please login first.");
            return;
        }
        int score = 0;
        for (int i = 0; i < questionCount; i++) {
            if (userAnswers[i] == correctAnswers[i]) {
                score++;
            }
        }
        System.out.println("Your score is " + score + " out of " + questionCount + ".");
        System.out.println("Best of luck!");
        logout();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String UserName = sc.nextLine();
        System.out.print("Enter your password: ");
        String UserPassword = sc.nextLine();
        OnlineExam examSystem = new OnlineExam(UserName, UserPassword);
        examSystem.login();
        examSystem.startExam();
    }
}