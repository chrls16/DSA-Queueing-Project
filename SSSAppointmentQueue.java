import java.util.*;

public class SSSAppointmentQueue {
    static int morningCounter = 1;
    static int afternoonCounter = 51;

    static List<String> priorityQueue = new ArrayList<>();
    static List<String> regularQueue = new ArrayList<>();

    static Map<Character, String[]> pixelMap = new HashMap<>();

    public static void main(String[] args) {
        initPixelMap();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== SSS Appointment Scheduler ===");
            System.out.println("1. Book Morning Appointment (1-50)");
            System.out.println("2. Book Afternoon Appointment (51-100)");
            System.out.println("3. View Priority Queue");
            System.out.println("4. View Regular Queue");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // clear newline

            if (option == 1 || option == 2) {
                boolean isMorning = (option == 1);

                if ((isMorning && morningCounter > 50) || (!isMorning && afternoonCounter > 100)) {
                    System.out.println((isMorning ? "Morning" : "Afternoon") + " schedule is full.");
                    continue;
                }

                // === Get User Details ===
                System.out.println("\n=== Enter Appointment Details ===");
                System.out.print("Full Name: ");
                String name = scanner.nextLine();

                System.out.print("Age: ");
                int age = Integer.parseInt(scanner.nextLine());

                System.out.print("Address: ");
                String address = scanner.nextLine();

                System.out.print("Contact Number: ");
                String contact = scanner.nextLine();

                // === Purpose Choices ===
                System.out.println("Choose Purpose of Visit:");
                System.out.println("1. Contribution Inquiry");
                System.out.println("2. Loan Application");
                System.out.println("3. ID Application");
                System.out.println("4. Benefit Claim");
                System.out.println("5. Update Information");
                System.out.print("Enter option number (1-5): ");
                int purposeOption = scanner.nextInt();
                scanner.nextLine(); // consume newline

                String purpose = switch (purposeOption) {
                    case 1 -> "Contribution Inquiry";
                    case 2 -> "Loan Application";
                    case 3 -> "ID Application";
                    case 4 -> "Benefit Claim";
                    case 5 -> "Update Information";
                    default -> "Other";
                };

                System.out.print("Are you a Senior Citizen, PWD, Pregnant, or Sick? (yes/no): ");
                String specialCategory = scanner.nextLine().trim().toLowerCase();

                // === Determine if user qualifies for priority queue ===
                boolean isPriority = age >= 60 || specialCategory.equals("yes");
                String category = isPriority ? "Priority" : "Regular";

                // === Assign queue number ===
                String queueNum = isMorning
                        ? String.format("M%03d", morningCounter++)
                        : String.format("A%03d", afternoonCounter++);

                // === Store in correct queue ===
                String record = queueNum + " - " + name + " (" + category + ")";
                if (isPriority) {
                    priorityQueue.add(record);
                } else {
                    regularQueue.add(record);
                }

                // === Show confirmation ===
                System.out.println("\n=== Appointment Confirmed ===");
                System.out.println("Name: " + name);
                System.out.println("Age: " + age);
                System.out.println("Address: " + address);
                System.out.println("Contact: " + contact);
                System.out.println("Purpose: " + purpose);
                System.out.println("Category: " + category);
                System.out.println("Queue Number: " + queueNum);

                System.out.println("\n=== Your Queue Number ===");
                printPixelArt(queueNum);

            } else if (option == 3) {
                System.out.println("\n=== Priority Queue ===");
                if (priorityQueue.isEmpty()) {
                    System.out.println("No one in the priority queue yet.");
                } else {
                    for (String person : priorityQueue) {
                        System.out.println(person);
                    }
                }

            } else if (option == 4) {
                System.out.println("\n=== Regular Queue ===");
                if (regularQueue.isEmpty()) {
                    System.out.println("No one in the regular queue yet.");
                } else {
                    for (String person : regularQueue) {
                        System.out.println(person);
                    }
                }

            } else if (option == 5) {
                System.out.println("Thank you! Goodbye.");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }

    // Pixel Art Definitions
    static void initPixelMap() {
        pixelMap.put('0', new String[]{
            " ███ ",
            "█   █",
            "█   █",
            "█   █",
            " ███ "
        });
        pixelMap.put('1', new String[]{
            "  █  ",
            " ██  ",
            "  █  ",
            "  █  ",
            " ███ "
        });
        pixelMap.put('2', new String[]{
            "████ ",
            "    █",
            " ███ ",
            "█    ",
            "█████"
        });
        pixelMap.put('3', new String[]{
            "████ ",
            "    █",
            " ███ ",
            "    █",
            "████ "
        });
        pixelMap.put('4', new String[]{
            "█  █ ",
            "█  █ ",
            "█████",
            "   █ ",
            "   █ "
        });
        pixelMap.put('5', new String[]{
            "█████",
            "█    ",
            "████ ",
            "    █",
            "████ "
        });
        pixelMap.put('6', new String[]{
            " ███",
            "█    ",
            "████ ",
            "█   █",
            " ███ "
        });
        pixelMap.put('7', new String[]{
            "█████",
            "   █ ",
            "  █  ",
            " █   ",
            " █   "
        });
        pixelMap.put('8', new String[]{
            " ███ ",
            "█   █",
            " ███ ",
            "█   █",
            " ███ "
        });
        pixelMap.put('9', new String[]{
            " ███ ",
            "█   █",
            " ████",
            "    █",
            " ███ "
        });
        pixelMap.put('M', new String[]{
            "█   █",
            "██ ██",
            "█ █ █",
            "█   █",
            "█   █"
        });
        pixelMap.put('A', new String[]{
            " ███ ",
            "█   █",
            "█████",
            "█   █",
            "█   █"
        });
    }

    static void printPixelArt(String text) {
        for (int row = 0; row < 5; row++) {
            for (char c : text.toCharArray()) {
                String[] lines = pixelMap.get(c);
                if (lines != null) {
                    System.out.print(lines[row] + "  ");
                }
            }
            System.out.println();
        }
    }
}
