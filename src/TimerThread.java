public class TimerThread extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("Timer Started");
            sleep(10000);
            System.out.println("\n\nTimed out\n");
            System.out.println("Press Enter to view Result");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
