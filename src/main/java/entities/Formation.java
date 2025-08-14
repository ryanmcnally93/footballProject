package entities;

import java.util.Arrays;
import java.util.List;

public class Formation {

    private List<String> positionOrder;

    public Formation(int def, int mid, int att) {
        if (def + mid + att != 10) {
            System.out.println("Wrong number of outfield players were passed to Formation constructor");
            throw new IllegalArgumentException("Wrong number of outfield players were passed to Formation constructor");
        } else if (def == 4 && mid == 3) {
            positionOrder = Arrays.asList(
                    "GK", "RB", "CB1", "CB2",
                    "LB", "CM1", "CAM", "CM2", "RW", "ST", "LW");
        } else if (def == 4 && mid == 4) {
            positionOrder = Arrays.asList(
                    "GK", "RB", "CB1", "CB2",
                    "LB", "LM", "CM1", "CM2", "RM", "ST", "ST");
        } else {
            System.out.println("Formation not recognised");
            throw new IllegalArgumentException("Formation not recognised");
        }
    }

    public List<String> getPositionOrder() {
        return positionOrder;
    }

}
