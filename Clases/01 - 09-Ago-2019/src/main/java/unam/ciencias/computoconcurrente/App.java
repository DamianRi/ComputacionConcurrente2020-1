package unam.ciencias.computoconcurrente;


public class App implements Runnable {

    public void run() {
        System.out.println("Hello World");
    }

    public static void main(String[] args) throws InterruptedException {
        simpleExamples();
        loopingExample();
    }

    static void simpleExamples() throws InterruptedException {
        // Creates a thread using an instance of a class that implements runnable
        Thread t = new Thread(new App());
        // Creates a thread using an instance of a class that extends threads
        Thread t2 = new Example2();
        // Creates a thread using an instance of an ananimous class that implements runnable, in this case we use a
        // lambda that does the same
        Thread t3 = new Thread(() -> System.out.println("Hello World from thread 3"));

        System.out.println("Starting a new thread");

        // Starting threads
        t.start();
        t2.start();
        t3.start();

        System.out.println("Waiting for the thread to finish.");

        // Wait for threads to finish
        // Thread.sleep(1000);
        t.join();
        t2.join();
        t3.join();
    }

    static void loopingExample() throws InterruptedException {
        // Milliseconds to for for the thread to finish
        long elapsedTimeInMs = 8 * 1000;
        long startedTime = System.currentTimeMillis();

        SimpleThread.threadMessage("Creating looping thread.");
        Thread loopingThread = new Thread(new SimpleThread.MessageLoop());

        loopingThread.start();

        while(loopingThread.isAlive()) {
            SimpleThread.threadMessage("still waiting ...");

            // Wait looping thread finishes for up to ~1 second
            loopingThread.join(1000);

            long currentTime = System.currentTimeMillis();
            if(currentTime - startedTime >= elapsedTimeInMs && loopingThread.isAlive()) {
                SimpleThread.threadMessage("Enough wait");
                // interrupts the looping thread so that it finishes asap
                loopingThread.interrupt();
                // wait fot the looping thread to finish
                loopingThread.join();
            }
        }
    }
}

class Example2 extends Thread {

    @Override
    public void run() {
        System.out.println("Hello World example 2");
    }
}

class SimpleThread {

    public static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + ": " + message);
    }

    public static class MessageLoop implements Runnable {
        public void run() {
            String[] messages = {
                    "message 1",
                    "message 2",
                    "message 3",
                    "message 4",
                    "message 5",
                    "message 6",
                    "message 7",
                    "message 8"
            };

            try {
                for(String msg : messages) {
                    // Print message
                    threadMessage(msg);

                    // Sleep 3 seconds
                    Thread.sleep(3000);
                }
            }
            catch(InterruptedException ie) {
                // If this thread was interrupted then it prints an exit message
                threadMessage("I was interrupted.");
            }
        }
    }
}