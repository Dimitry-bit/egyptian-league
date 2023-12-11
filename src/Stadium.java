import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Stadium {
    private String[] stadiumNames;

    public Stadium(String[] stadiumNames) {
        this.stadiumNames = stadiumNames;
    }


    public void addStadium(String newName) {
        int currentLength = stadiumNames.length;
        stadiumNames = Arrays.copyOf(stadiumNames, currentLength + 1);
        stadiumNames[currentLength] = newName;
        System.out.println("'" + newName + "' added to the list of stadium names.");
    }

    public void updateStadium(int index, String updatedName) {
        if (index >= 0 && index < stadiumNames.length) {
            stadiumNames[index] = updatedName;
            System.out.println("Stadium name at index " + index + " updated to '" + updatedName + "'.");
        } else {
            System.out.println("Invalid index. Update failed.");
        }
    }

    public void deleteStadium(int index) {
        if (index >= 0 && index < stadiumNames.length) {
            String[] updatedStadiumNames = new String[stadiumNames.length - 1];
            for (int i = 0, j = 0; i < stadiumNames.length; i++) {
                if (i != index) {
                    updatedStadiumNames[j++] = stadiumNames[i];
                }
            }
            stadiumNames = updatedStadiumNames;
            System.out.println("Stadium at index " + index + " deleted.");
        } else {
            System.out.println("Invalid index. Deletion failed.");
        }
    }

    public boolean checkStadiumAvailability(int index, LocalDateTime searchedDate, ArrayList Date) {
        if (index >= 0 && index < stadiumNames.length) {
            for (int i = 0; i < Date.size(); i++) {
                if (searchedDate.isEqual((ChronoLocalDateTime<?>) Date.get(i)) && searchedDate.isBefore(LocalDateTime.now())) {
                    return false;
                }
            }
        }
        return true;
    }

    public void displayStadiumNames() {
        System.out.println("Stadium Names:");
        for (String name : stadiumNames) {
            System.out.println(name);
        }
    }
}

