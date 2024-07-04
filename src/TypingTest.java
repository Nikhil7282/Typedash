import java.util.Scanner;

public class TypingTest {
    private String text;
    private int mode;
    private double typingSpeed;
    private int accuracy;

    public TypingTest(String text, int mode) {
        this.text = text;
        this.mode = mode;
        if (this.mode == 1) {
            typeFullText();
        } else {
            typeInMinute();
        }
    }

    private void displayResult() {
        System.out.println("Typing Speed: " + typingSpeed + " characters per second");
        System.out.println("Accuracy: " + accuracy + "%");
    }

    public void typeFullText() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Start typing the following text");
        long start = System.currentTimeMillis();
        System.out.println(this.text + "\n");
        String input;
        if (sc.hasNextLine()) {
            input = sc.nextLine();
        } else {
            System.out.println("No Input Provided");
            sc.close();
            return;
        }
        long end = System.currentTimeMillis();
        long timeTaken = (end - start) / 1000;
        int correct = 0;
        for (int i = 0; i < text.length(); i++) {
            if (i < input.length() && input.charAt(i) == text.charAt(i)) {
                correct++;
            }
        }
        accuracy = (int) ((double) correct / input.length() * 100);
        typingSpeed = (int) ((double) correct / timeTaken);

        sc.close();
        displayResult();
    }

    public void typeInMinute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Start typing the following text");
        System.out.println(text);
        TimerThread t = new TimerThread();
        t.start();

        String userInput = "";
        while (t.isAlive()) {
            if (sc.hasNextLine()) {
                userInput += sc.nextLine();
            }
        }
        sc.close();
        int correct = 0;
        for (int i = 0; i < userInput.length(); i++) {
            if (i < userInput.length() && userInput.charAt(i) == text.charAt(i)) {
                correct++;
            }
        }
        if (userInput.length() > 0) {
            accuracy = (int) ((double) correct / userInput.length() * 100);
        } else {
            accuracy = 0;
        }

        typingSpeed = (double) correct / 60;

        displayResult();
        System.exit(0);
    }
}
