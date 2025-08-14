package main.java.entities;

import java.util.Arrays;
import java.util.List;

public class Formation {

    private List<String> positionOrder;

    public Formation(int def, int mid, int att) {
        if (def + mid + att != 10) {
            System.out.println("INCORRECT FORMATION SET");
        } else if (def == 4 && mid == 3 && att == 3) {
            positionOrder = Arrays.asList(
                    "GK", "RB", "CB1", "CB2",
                    "LB", "CM1", "CAM", "CM2", "RW", "ST", "LW");
        }
    }

    public List<String> getPositionOrder() {
        return positionOrder;
    }

}
