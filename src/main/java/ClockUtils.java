import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ClockUtils {
    private static ArrayDeque<Integer> mainQueue = new ArrayDeque<>();
    private static ArrayDeque<Integer> minutesQueue = new ArrayDeque<>();
    private static ArrayDeque<Integer> fiveMinutesQueue = new ArrayDeque<>();
    private static ArrayDeque<Integer> hoursQueue = new ArrayDeque<>();

    private static int counterSum = 0;
    private static int counterDays = 0;
    private static int counterMin = 0;
    private static int counter5 = 0;
    private static int counterHours = 0;

    private static double startTime;

    private static ArrayDeque<Integer> createQueue(int balls) {
        mainQueue.clear();
        IntStream.rangeClosed(1, balls).forEach(mainQueue::add);
        return mainQueue;
    }

    public static void clock(int balls) {
        System.out.println(String.format("\nLet's count how much days it will take for %s balls to return queue to the initial ordering\n", balls));

        startTimerAndCreateQueue(balls);
        List<Integer> list = new ArrayList<>(mainQueue);

        do {
            function();
        }
        while (!list.equals(getListFromQueue()));

        System.out.println(String.format("%s balls cycle after %s days", balls, counterDays));
        printTimeAndDropVariables();
    }

    public static void clock(int balls, int minutes) {
        System.out.println(String.format("\nLet's see the state of %s balls after %s minutes\n", balls, minutes));

        startTimerAndCreateQueue(balls);

        while (counterSum < minutes) {
            function();
        }
        hoursQueue.poll();

        JsonQueuesModel jsonQueuesModel = new JsonQueuesModel(mainQueue, minutesQueue, fiveMinutesQueue, hoursQueue);
        Gson gson = new Gson();
        System.out.println(gson.toJson(jsonQueuesModel));

        printTimeAndDropVariables();
    }

    private static void function() {
        if (minutesQueue.size() < 4) {
            minutesQueue.add(Objects.requireNonNull(mainQueue.poll()));
            counterMin++;
        } else {
            if (fiveMinutesQueue.size() < 11) {
                IntStream.rangeClosed(1, 4).forEach(i ->
                        mainQueue.add(Objects.requireNonNull(minutesQueue.pollLast())));
                fiveMinutesQueue.add(Objects.requireNonNull(mainQueue.poll()));
                counterMin = 0;
                counter5++;
            } else {
                if (hoursQueue.size() < 12) {
                    IntStream.rangeClosed(1, minutesQueue.size()).forEach(i ->
                            mainQueue.add(Objects.requireNonNull(minutesQueue.pollLast())));
                    IntStream.rangeClosed(1, fiveMinutesQueue.size()).forEach(i ->
                            mainQueue.add(Objects.requireNonNull(fiveMinutesQueue.pollLast())));
                    hoursQueue.add(Objects.requireNonNull(mainQueue.poll()));
                    countHours();
                } else {
                    IntStream.rangeClosed(1, minutesQueue.size()).forEach(i -> mainQueue.add(Objects.requireNonNull(minutesQueue.pollLast())));
                    IntStream.rangeClosed(1, fiveMinutesQueue.size()).forEach(i -> mainQueue.add(Objects.requireNonNull(fiveMinutesQueue.pollLast())));
                    IntStream.rangeClosed(1, 11).forEach(i -> mainQueue.add(Objects.requireNonNull(hoursQueue.pollLast())));
                    mainQueue.add(Objects.requireNonNull(mainQueue.poll()));
                    countHours();
                }
            }
        }
        counterSum = counterMin + counter5 * 5 + counterHours * 60 + counterDays * 24 * 60;
    }

    private static void startTimerAndCreateQueue(int balls) {
        if (balls < 27 || balls > 127) {
            throw new IllegalArgumentException("Valid numbers of balls are in the range 27 to 127. Please enter the valid number");
        }
        startTime = System.currentTimeMillis();
        mainQueue = createQueue(balls);
        hoursQueue.add(0);
    }

    private static void printTimeAndDropVariables() {
        double result = System.currentTimeMillis() - startTime;
        System.out.println(String.format("Completed in %s milliseconds (%s seconds)", result, result / 1000));
        dropVariables();
    }

    private static void dropVariables() {
        counterMin = 0;
        counter5 = 0;
        counterHours = 0;
        counterDays = 0;
        counterSum = 0;
        Stream.of(minutesQueue, fiveMinutesQueue, hoursQueue).forEach(ArrayDeque::clear);
    }

    private static void countHours() {
        counterMin = 0;
        counter5 = 0;
        counterHours++;
        if (counterHours == 24) {
            counterDays++;
            counterHours = 0;
        }
    }

    private static List<Integer> getListFromQueue() {
        return new ArrayList<>(mainQueue);
    }

}
