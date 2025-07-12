import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

class Goal {
    String name;
    LocalTime time;

    Goal(String name, LocalTime time) {
        this.name = name;
        this.time = time;
    }
}

public class GoalReminder {
    public static void main(String[] args) {
        Scanner var = new Scanner(System.in);
        List<Goal> goals = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        System.out.println("Enter number of goals:");
        int n = Integer.parseInt(var.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.print("Enter goal name: ");
            String name = var.nextLine();

            System.out.print("Enter time for '" + name + "' (HH:mm): ");
            String timeInput = var.nextLine();

            try {
                LocalTime time = LocalTime.parse(timeInput, formatter);
                goals.add(new Goal(name, time));
            } catch (Exception e) {
                System.out.println("Invalid time format. Skipping this goal.");
            }
        }

        Timer timer = new Timer();

        for (Goal goal : goals) {
            long delay = Duration.between(LocalTime.now(), goal.time).toMillis();
            if (delay > 0) {
                timer.schedule(new TimerTask() {
                    public void run() {
                        System.out.println("🔔 Reminder: Time to " + goal.name + "!");
                    }
                }, delay);
            } else {
                System.out.println("Time for '" + goal.name + "' has already passed.");
            }
        }

        System.out.println("Reminders scheduled. App is running...");
    }
}