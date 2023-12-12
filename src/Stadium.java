import java.time.LocalDateTime;
import java.util.ArrayList;

public class Stadium {
    private ArrayList<String> stadiumNames;
    protected LocalDateTime matchDate;

    public Stadium(ArrayList<String> stadiumNames) {
        this.stadiumNames = stadiumNames;
    }

    public boolean checkIndex(int index){
        if (index >= 0 && index < stadiumNames.size()){
            return true;
        }
        return false;
    }
    public void addStadium(String newName) {
        stadiumNames.add(newName);
    }

    public void updateStadium(int index, String updatedName) {
        if (checkIndex(index)) {
            stadiumNames.set(index, updatedName);
        }
    }

    public void deleteStadium(int index) {
        if (checkIndex(index)) {
            String deletedName = stadiumNames.remove(index);
        }
    }

    public boolean checkStadiumAvailability(LocalDateTime searchedDate){
        if (searchedDate.isEqual(matchDate) && searchedDate.isBefore(matchDate)){
            return false;
        }
        return true;
    }

}

