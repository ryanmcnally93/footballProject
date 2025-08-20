package gameSetup;

import people.Footballer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FootballerFactory {

    private static final List<String> PHYSICAL_ATTRIBUTES = List.of("Sprint Speed", "Strength", "Agility", "Balance", "Acceleration", "Stamina");
    private static final List<String> TECHNICAL_ATTRIBUTES = List.of("Finishing", "Shot Power", "Long Shots", "Volleys", "Attacking Positioning", "Heading Accuracy", "Penalties", "Crossing", "Short Passing", "Long Passing", "Curve", "Ball Control", "Free Kick Accuracy", "Defensive Positioning", "Standing Tackle", "Sliding Tackle");
    private static final List<String> MENTAL_ATTRIBUTES = List.of("Vision", "Composure", "Reactions", "Interceptions");
    private static final List<String> GOALKEEPER_ATTRIBUTES = List.of("GK Diving", "GK Handling", "GK Kicking", "GK Positioning", "GK Reflexes", "GK 1-on-1");
    private static final List<String> MAIN_ATTRIBUTES = List.of("Rating", "Potential", "Wage", "Date Of Birth", "Height", "Weight", "Contract Length", "Form", "Morale", "Injury Proneness");
    // Dribbling = Agility, Balance, Ball Control, Composure, Reactions
    // Shooting = Finishing, Show Power, Long Shots, Volleys, Curve
    // Defending = Aggression, Defensive Positioning, Standing Tackle, Sliding Tackle, Interceptions
    // Passing = Vision, Crossing, Short Passing, Long Passing, Curve
    // Value = Potential, Rating, Age (Derived from DOB)

    private static final Map<String, Double> ST_WEIGHTS = Map.ofEntries(
            Map.entry(PHYSICAL_ATTRIBUTES.getFirst(), 0.90),  // "Sprint Speed"
            Map.entry(PHYSICAL_ATTRIBUTES.get(1), 0.7),  // "Strength"
            Map.entry(PHYSICAL_ATTRIBUTES.get(2), 0.70), // "Agility"
            Map.entry(PHYSICAL_ATTRIBUTES.get(3), 0.70), // "Balance"
            Map.entry(PHYSICAL_ATTRIBUTES.get(4), 0.85),  // "Acceleration"
            Map.entry(PHYSICAL_ATTRIBUTES.get(5), 1.00), // "Stamina"
            Map.entry(TECHNICAL_ATTRIBUTES.getFirst(), 1.10), // Finishing
            Map.entry(TECHNICAL_ATTRIBUTES.get(1), 1.00), // Shot Power
            Map.entry(TECHNICAL_ATTRIBUTES.get(2), 0.75), // Long Shots
            Map.entry(TECHNICAL_ATTRIBUTES.get(3), 0.60), // Volleys
            Map.entry(TECHNICAL_ATTRIBUTES.get(4), 1.10), // Attacking Positioning
            Map.entry(TECHNICAL_ATTRIBUTES.get(5), 0.70), // Heading
            Map.entry(TECHNICAL_ATTRIBUTES.get(6), 0.45), // Penalties
            Map.entry(TECHNICAL_ATTRIBUTES.get(7), 0.20), // Crossing
            Map.entry(TECHNICAL_ATTRIBUTES.get(8), 0.35), // Short Passing
            Map.entry(TECHNICAL_ATTRIBUTES.get(9), 0.15), // Long Passing
            Map.entry(TECHNICAL_ATTRIBUTES.get(10), 0.70), // Curve
            Map.entry(TECHNICAL_ATTRIBUTES.get(11), 0.80), // Ball Control
            Map.entry(TECHNICAL_ATTRIBUTES.get(12), 0.05), // Free Kick Accuracy
            Map.entry(TECHNICAL_ATTRIBUTES.get(13), 0.15), // Defensive Positioning
            Map.entry(TECHNICAL_ATTRIBUTES.get(14), 0.20), // Standing Tackle
            Map.entry(TECHNICAL_ATTRIBUTES.get(15), 0.20), // Sliding Tackle
            Map.entry(MENTAL_ATTRIBUTES.getFirst(), 0.50), // Vision
            Map.entry(MENTAL_ATTRIBUTES.get(1), 0.85), // Composure
            Map.entry(MENTAL_ATTRIBUTES.get(2), 0.70), // Reactions
            Map.entry(MENTAL_ATTRIBUTES.get(3), 0.20), // Interceptions
            Map.entry(GOALKEEPER_ATTRIBUTES.getFirst(), 0.00), // GK Diving
            Map.entry(GOALKEEPER_ATTRIBUTES.get(1), 0.00), // GK Handling
            Map.entry(GOALKEEPER_ATTRIBUTES.get(2), 0.00), // GK Kicking
            Map.entry(GOALKEEPER_ATTRIBUTES.get(3), 0.00), // GK Positioning
            Map.entry(GOALKEEPER_ATTRIBUTES.get(4), 0.00), // GK Reflexes
            Map.entry(GOALKEEPER_ATTRIBUTES.get(5), 0.00) // GK 1-on-1
    );

    private static final Map<String, Double> CAM_WEIGHTS = Map.of(
            PHYSICAL_ATTRIBUTES.getFirst(), 0.4,  // "Sprint Speed"
            PHYSICAL_ATTRIBUTES.get(1), 0.2,  // "Strength"
            PHYSICAL_ATTRIBUTES.get(2), 0.15, // "Agility"
            PHYSICAL_ATTRIBUTES.get(3), 0.05, // "Balance"
            PHYSICAL_ATTRIBUTES.get(4), 0.1,  // "Acceleration"
            PHYSICAL_ATTRIBUTES.get(5), 0.1 // "Stamina"
    );

//    private static final Map<String, Integer> MAIN_WEIGHTS = Map.of(
//            MAIN_ATTRIBUTES.get(3),19930610
//    );

    public static Footballer createPlayer(String position, String type, int desiredRating, int potential, String name) {
        Map<String, Integer> attributes = new HashMap<>();
        Map<String, Double> weights = getWeightsForPosition(position);

        final int minAttr = 1;
        final int maxAttr = 99;
        final int dropoff = 55;            // How far low-weight stats fall below desiredRating
        final int randomness = 5;          // ± variance

        Random random = new Random();

        for (Map.Entry<String, Double> entry : weights.entrySet()) {
            String attr = entry.getKey();
            double weight = weights.getOrDefault(attr, 0.0);

            // Calculate value around desiredRating based on importance
            double base = desiredRating - (1.0 - weight) * dropoff;
            int value = (int) Math.round(base + (random.nextInt(randomness * 2 + 1) - randomness));

            value = Math.max(minAttr, Math.min(value, maxAttr)); // Clamp

            value = applyTypeAdjustment(type, attr, value); // Optional
            attributes.put(attr, value);
        }

        int estimatedOverall = calculateOVR(attributes, weights);

        attributes.put(MAIN_ATTRIBUTES.get(0), estimatedOverall);
        attributes.put(MAIN_ATTRIBUTES.get(1), potential);
        attributes.put(MAIN_ATTRIBUTES.get(3), 19930610);

        return new Footballer(name, position, type, attributes);
    }

    private static Map<String, Double> getWeightsForPosition(String position) {
        switch (position.toUpperCase()) {
            case "ST":
                return ST_WEIGHTS;
            case "CAM":
                return CAM_WEIGHTS;
            default:
                throw new IllegalArgumentException("");
        }
    }

    private static int calculateOVR(Map<String, Integer> attributes, Map<String, Double> weights) {
        double weightedSum = 0.0;
        double totalWeight = 0.0;

        for (Map.Entry<String, Integer> entry : attributes.entrySet()) {
            String attr = entry.getKey();
            int value = entry.getValue();
            double weight = weights.getOrDefault(attr, 0.0);

            weightedSum += value * weight;
            totalWeight += weight;
        }

        if (totalWeight == 0) return 0; // Avoid division by zero
        return (int) Math.round(weightedSum / totalWeight);
    }

    private static int applyTypeAdjustment(String type, String attr, int base) {
        switch (type.toLowerCase()) {
            case "finisher":
                if (attr.equals("Finishing") || attr.equals("Positioning")) {
                    return Math.min(base + 10, 99);
                }
                break;
            case "target man":
                if (attr.equals("Physical")) return Math.min(base + 10, 99);
                break;
            case "playmaker":
                if (attr.equals("Passing")) return Math.min(base + 10, 99);
                break;
        }
        return base;
    }

}
