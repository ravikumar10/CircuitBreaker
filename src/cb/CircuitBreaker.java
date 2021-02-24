package cb;

import java.util.Random;

public class CircuitBreaker {
    public int failureCounts;
    public int failureThreashold;
    public int windowSize;
    public long startTime;
    public CircuitBreaker(int failureThreashold, int windowSize) {
        this.failureCounts = 0;
        this.windowSize = windowSize;
        this.failureThreashold = failureThreashold;
        this.startTime = System.currentTimeMillis();
    }

    public void execute(){
        if(checkState()) {
            System.out.println("Circuit is Closed");
            boolean result = callRestApi();
            if (!result)
                updateFailures();
        } else {
            System.out.println("Circuit is Open");
        }
    }

    private boolean checkState() {
        long current = System.currentTimeMillis();
        long sec = (current - startTime) / 1000;
        System.out.println("StartTime : "+startTime+" , currentTime : "+current+" , differnce : "+sec+ " , Failure Count : "+failureCounts);
        if(sec >= windowSize){
            startTime = System.currentTimeMillis();
            failureCounts = 0;
            return true;
        } else {
            return failureCounts < failureThreashold;
        }
    }

    private void updateFailures(){
        long current = System.currentTimeMillis();
        long sec = (current - startTime) / 1000;
        System.out.println("StartTime : "+startTime+" , currentTime : "+current+" , differnce : "+sec+ " , Failure Count : "+failureCounts);
        if(sec >= windowSize){
            startTime = System.currentTimeMillis();
            failureCounts = 1;
        } else {
            failureCounts++;
        }
    }

    private boolean callRestApi(){
        System.out.println("Calling RestApi");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Random rd = new Random();
        return rd.nextBoolean();
    }
}
