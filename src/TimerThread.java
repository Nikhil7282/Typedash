public class TimerThread extends Thread {
    private final Runnable callback;

    TimerThread(Runnable cb) {
        this.callback = cb;
    }

    @Override
    public void run() {
        try {
            System.out.println("Timer Started");
            sleep(60000);
            System.out.println("\nTimed out\n");
            callback.run();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
