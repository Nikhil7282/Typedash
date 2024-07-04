import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("Select mode:");
        System.out.println("1. Type full text");
        System.out.println("2. Type in one minute");
        int mode = sc.nextInt();

        new TypingTest("This is a string and this works properly", mode);
        sc.close();
    }
}
