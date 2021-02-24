package cb;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        System.out.println("Enter \n1) Failure Threshold Count : ");
        Scanner sc = new Scanner(System.in);
        int failureThreasholdCount = Integer.parseInt(sc.nextLine());
        System.out.println("Enter \n2) Window Size [Time is sec] : ");
        int windowSizeInTime = Integer.parseInt(sc.nextLine());

        CircuitBreaker cb = new CircuitBreaker(failureThreasholdCount, windowSizeInTime);

        while(true)
            cb.execute();
    }
}
