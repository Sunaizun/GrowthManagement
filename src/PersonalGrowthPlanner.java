import java.util.*;

abstract class Goal {
    protected String name;
    protected String description;
    protected int progress;
    protected int target;

    public Goal(String name, String description, int target) {
        this.name = name;
        this.description = description;
        this.target = target;
        this.progress = 0;
    }

    public void logProgress(int amount) {
        progress += amount;
        if (progress > target) progress = target;
        System.out.println("Progress logged for " + name + ": " + progress + "/" + target);
    }

    public abstract void displayGoalDetails();
}

class FinanceGoal extends Goal {
    public FinanceGoal(String description, int target) {
        super("Finance", description, target);
    }

    @Override
    public void displayGoalDetails() {
        System.out.println("[Finance Goal]");
        System.out.println("Description: " + description);
        System.out.println("Progress: " + progress + "/" + target);
    }
}

class StudyGoal extends Goal {
    public StudyGoal(String description, int target) {
        super("Study", description, target);
    }

    @Override
    public void displayGoalDetails() {
        System.out.println("[Study Goal]");
        System.out.println("Description: " + description);
        System.out.println("Progress: " + progress + "/" + target);
    }
}

public class PersonalGrowthPlanner {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Goal> goals = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Personal Growth Planner ===");
            System.out.println("1. Add Goal");
            System.out.println("2. Log Progress");
            System.out.println("3. View Goals");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addGoal();
                    break;
                case 2:
                    logProgress();
                    break;
                case 3:
                    viewGoals();
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addGoal() {
        System.out.println("\nChoose goal type:");
        System.out.println("1. Finance");
        System.out.println("2. Study");
        System.out.print("Enter choice: ");
        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter goal description: ");
        String description = scanner.nextLine();

        System.out.print("Enter target amount: ");
        int target = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (type) {
            case 1:
                goals.add(new FinanceGoal(description, target));
                break;
            case 2:
                goals.add(new StudyGoal(description, target));
                break;
            default:
                System.out.println("Invalid type. Goal not added.");
        }
    }

    private static void logProgress() {
        if (goals.isEmpty()) {
            System.out.println("No goals to update. Add a goal first.");
            return;
        }

        System.out.println("\nSelect a goal to update:");
        for (int i = 0; i < goals.size(); i++) {
            System.out.println((i + 1) + ". " + goals.get(i).name + " - " + goals.get(i).description);
        }

        System.out.print("Enter goal number: ");
        int goalIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (goalIndex < 0 || goalIndex >= goals.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        System.out.print("Enter progress amount: ");
        int progress = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        goals.get(goalIndex).logProgress(progress);
    }

    private static void viewGoals() {
        if (goals.isEmpty()) {
            System.out.println("No goals added yet.");
            return;
        }

        System.out.println("\nYour Goals:");
        for (Goal goal : goals) {
            goal.displayGoalDetails();
            System.out.println();
        }
    }
}