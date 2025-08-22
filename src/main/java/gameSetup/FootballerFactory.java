package gameSetup;

import people.Footballer;
import people.Goalkeeper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    // do these weight distributions reflect our OVR weights correctly? Or will we create a player with an under or overwhelming ovr?

    private static final Map<String, Double> GK_OVR_WEIGHTS = Map.ofEntries(
            Map.entry("GK Reflexes", 0.26),
            Map.entry("GK Diving", 0.23),
            Map.entry("GK Positioning", 0.20),
            Map.entry("GK Handling", 0.15),
            Map.entry("GK Kicking", 0.11),
            Map.entry(GOALKEEPER_ATTRIBUTES.get(5), 0.05)   // GK 1-on-1
    );

    private static final Map<String, Double> GK_STAT_WEIGHTS = Map.ofEntries(
            Map.entry(PHYSICAL_ATTRIBUTES.getFirst(), 0.02),  // Sprint Speed
            Map.entry(PHYSICAL_ATTRIBUTES.get(1), 0.70),      // Strength
            Map.entry(PHYSICAL_ATTRIBUTES.get(2), 0.03),      // Agility
            Map.entry(PHYSICAL_ATTRIBUTES.get(3), 0.65),      // Balance
            Map.entry(PHYSICAL_ATTRIBUTES.get(4), 0.02),      // Acceleration
            Map.entry(PHYSICAL_ATTRIBUTES.get(5), 0.70),      // Stamina
            Map.entry(TECHNICAL_ATTRIBUTES.getFirst(), 0.00), // Finishing
            Map.entry(TECHNICAL_ATTRIBUTES.get(1), 0.20),     // Shot Power
            Map.entry(TECHNICAL_ATTRIBUTES.get(2), 0.00),     // Long Shots
            Map.entry(TECHNICAL_ATTRIBUTES.get(3), 0.00),     // Volleys
            Map.entry(TECHNICAL_ATTRIBUTES.get(4), 0.00),     // Att. Positioning
            Map.entry(TECHNICAL_ATTRIBUTES.get(5), 0.00),     // Heading Accuracy
            Map.entry(TECHNICAL_ATTRIBUTES.get(6), 0.02),     // Penalties
            Map.entry(TECHNICAL_ATTRIBUTES.get(7), 0.00),     // Crossing
            Map.entry(TECHNICAL_ATTRIBUTES.get(8), 0.60),     // Short Passing
            Map.entry(TECHNICAL_ATTRIBUTES.get(9), 0.70),     // Long Passing
            Map.entry(TECHNICAL_ATTRIBUTES.get(10), 0.04),    // Curve
            Map.entry(TECHNICAL_ATTRIBUTES.get(11), 0.04),    // Ball Control
            Map.entry(TECHNICAL_ATTRIBUTES.get(12), 0.00),    // Free Kick Accuracy
            Map.entry(TECHNICAL_ATTRIBUTES.get(13), 0.00),    // Def. Positioning
            Map.entry(TECHNICAL_ATTRIBUTES.get(14), 0.02),    // Standing Tackle
            Map.entry(TECHNICAL_ATTRIBUTES.get(15), 0.00),    // Sliding Tackle
            Map.entry(MENTAL_ATTRIBUTES.getFirst(), 0.02),    // Vision
            Map.entry(MENTAL_ATTRIBUTES.get(1), 0.75),        // Composure
            Map.entry(MENTAL_ATTRIBUTES.get(2), 0.70),        // Reactions
            Map.entry(MENTAL_ATTRIBUTES.get(3), 0.00),        // Interceptions
            Map.entry(GOALKEEPER_ATTRIBUTES.getFirst(), 0.85),// GK Diving
            Map.entry(GOALKEEPER_ATTRIBUTES.get(1), 0.80),    // GK Handling
            Map.entry(GOALKEEPER_ATTRIBUTES.get(2), 0.70),    // GK Kicking
            Map.entry(GOALKEEPER_ATTRIBUTES.get(3), 0.80),    // GK Positioning
            Map.entry(GOALKEEPER_ATTRIBUTES.get(4), 0.90),    // GK Reflexes
            Map.entry(GOALKEEPER_ATTRIBUTES.get(5), 0.65)     // GK 1-on-1
    );

    private static final Map<String, Double> CB_OVR_WEIGHTS = Map.ofEntries(
            Map.entry("Standing Tackle", 0.18),
            Map.entry("Sliding Tackle", 0.13),
            Map.entry("Interceptions", 0.12),
            Map.entry("Strength", 0.10),
            Map.entry("Def. Positioning", 0.10),
            Map.entry("Heading Accuracy", 0.09),
            Map.entry("Jumping", 0.07),
            Map.entry("Reactions", 0.07),
            Map.entry("Composure", 0.06),
            Map.entry("Short Passing", 0.04),
            Map.entry("Aggression", 0.04)
    );

    private static final Map<String, Double> CB_STAT_WEIGHTS = Map.ofEntries(
    );

    private static final Map<String, Double> FULL_BACK_OVR_WEIGHTS = Map.ofEntries(
            Map.entry("Standing Tackle", 0.12),
            Map.entry("Sliding Tackle", 0.10),
            Map.entry("Interceptions", 0.09),
            Map.entry("Crossing", 0.10),
            Map.entry("Short Passing", 0.08),
            Map.entry("Ball Control", 0.08),
            Map.entry("Acceleration", 0.08),
            Map.entry("Sprint Speed", 0.08),
            Map.entry("Stamina", 0.07),
            Map.entry("Reactions", 0.07),
            Map.entry("Agility", 0.05),
            Map.entry("Balance", 0.04),
            Map.entry("Vision", 0.04)
    );

    private static final Map<String, Double> FULL_BACK_STAT_WEIGHTS = Map.ofEntries(
    );

    private static final Map<String, Double> CM_OVR_WEIGHTS = Map.ofEntries(
            Map.entry("Short Passing", 0.13),
            Map.entry("Vision", 0.12),
            Map.entry("Ball Control", 0.11),
            Map.entry("Long Passing", 0.10),
            Map.entry("Reactions", 0.09),
            Map.entry("Stamina", 0.08),
            Map.entry("Composure", 0.07),
            Map.entry("Interceptions", 0.06),
            Map.entry("Standing Tackle", 0.06),
            Map.entry("Dribbling", 0.05),
            Map.entry("Att. Positioning", 0.05),
            Map.entry("Agility", 0.04),
            Map.entry("Balance", 0.04)
    );

    private static final Map<String, Double> CM_STAT_WEIGHTS = Map.ofEntries(
    );

    private static final Map<String, Double> CAM_OVR_WEIGHTS = Map.ofEntries(
            Map.entry(PHYSICAL_ATTRIBUTES.get(3), 0.02),       // Balance
            Map.entry(MENTAL_ATTRIBUTES.getFirst(), 0.14),     // Vision
            Map.entry(TECHNICAL_ATTRIBUTES.get(8), 0.13),      // Short Passing
            Map.entry(TECHNICAL_ATTRIBUTES.get(11), 0.14),     // Ball Control
            Map.entry(TECHNICAL_ATTRIBUTES.get(4), 0.10),      // Att. Positioning
            Map.entry(TECHNICAL_ATTRIBUTES.get(2), 0.08),      // Long Shots
            Map.entry(TECHNICAL_ATTRIBUTES.getFirst(), 0.07),  // Finishing
            Map.entry(MENTAL_ATTRIBUTES.get(2), 0.08),         // Reactions
            Map.entry(MENTAL_ATTRIBUTES.get(1), 0.08),         // Composure
            Map.entry(TECHNICAL_ATTRIBUTES.get(9), 0.05),      // Long Passing
            Map.entry(PHYSICAL_ATTRIBUTES.get(4), 0.04),       // Acceleration
            Map.entry(TECHNICAL_ATTRIBUTES.get(10), 0.03),     // Curve
            Map.entry(PHYSICAL_ATTRIBUTES.get(2), 0.04)        // Agility
    );

    private static final Map<String, Double> CAM_STAT_WEIGHTS = Map.ofEntries(
    );

    private static final Map<String, Double> WINGER_OVR_WEIGHTS = Map.ofEntries(
            Map.entry(PHYSICAL_ATTRIBUTES.get(3), 0.032),       // Balance
            Map.entry(MENTAL_ATTRIBUTES.get(1), 0.032),         // Composure
            Map.entry(TECHNICAL_ATTRIBUTES.get(11), 0.172),     // Ball Control
            Map.entry(TECHNICAL_ATTRIBUTES.getFirst(), 0.10),   // Finishing
            Map.entry(TECHNICAL_ATTRIBUTES.get(4), 0.09),       // Att. Positioning
            Map.entry(TECHNICAL_ATTRIBUTES.get(8), 0.09),       // Short Passing
            Map.entry(TECHNICAL_ATTRIBUTES.get(7), 0.09),       // Crossing
            Map.entry(MENTAL_ATTRIBUTES.get(2), 0.102),         // Reactions
            Map.entry(PHYSICAL_ATTRIBUTES.get(4), 0.07),        // Acceleration
            Map.entry(PHYSICAL_ATTRIBUTES.getFirst(), 0.06),    // Sprint Speed
            Map.entry(MENTAL_ATTRIBUTES.getFirst(), 0.06),      // Vision
            Map.entry(TECHNICAL_ATTRIBUTES.get(2), 0.04),       // Long Shots
            Map.entry(PHYSICAL_ATTRIBUTES.get(2), 0.062)        // Agility
    );

    private static final Map<String, Double> WINGER_STAT_WEIGHTS = Map.ofEntries(
            Map.entry(PHYSICAL_ATTRIBUTES.getFirst(), 0.95),  // Sprint Speed
            Map.entry(PHYSICAL_ATTRIBUTES.get(1), 0.40),      // Strength
            Map.entry(PHYSICAL_ATTRIBUTES.get(2), 0.75),      // Agility
            Map.entry(PHYSICAL_ATTRIBUTES.get(3), 0.70),      // Balance
            Map.entry(PHYSICAL_ATTRIBUTES.get(4), 0.95),      // Acceleration
            Map.entry(PHYSICAL_ATTRIBUTES.get(5), 0.90),      // Stamina
            Map.entry(TECHNICAL_ATTRIBUTES.getFirst(), 0.70), // Finishing
            Map.entry(TECHNICAL_ATTRIBUTES.get(1), 0.60),     // Shot Power
            Map.entry(TECHNICAL_ATTRIBUTES.get(2), 0.45),     // Long Shots
            Map.entry(TECHNICAL_ATTRIBUTES.get(3), 0.30),     // Volleys
            Map.entry(TECHNICAL_ATTRIBUTES.get(4), 0.80),     // Att. Positioning
            Map.entry(TECHNICAL_ATTRIBUTES.get(5), 0.40),     // Heading Accuracy
            Map.entry(TECHNICAL_ATTRIBUTES.get(6), 0.30),     // Penalties
            Map.entry(TECHNICAL_ATTRIBUTES.get(7), 0.80),     // Crossing
            Map.entry(TECHNICAL_ATTRIBUTES.get(8), 0.70),     // Short Passing
            Map.entry(TECHNICAL_ATTRIBUTES.get(9), 0.50),     // Long Passing
            Map.entry(TECHNICAL_ATTRIBUTES.get(10), 0.75),    // Curve
            Map.entry(TECHNICAL_ATTRIBUTES.get(11), 0.80),    // Ball Control
            Map.entry(TECHNICAL_ATTRIBUTES.get(12), 0.50),    // Free Kick Accuracy
            Map.entry(TECHNICAL_ATTRIBUTES.get(13), 0.20),    // Def. Positioning
            Map.entry(TECHNICAL_ATTRIBUTES.get(14), 0.20),    // Standing Tackle
            Map.entry(TECHNICAL_ATTRIBUTES.get(15), 0.15),    // Sliding Tackle
            Map.entry(MENTAL_ATTRIBUTES.getFirst(), 0.70),    // Vision
            Map.entry(MENTAL_ATTRIBUTES.get(1), 0.85),        // Composure
            Map.entry(MENTAL_ATTRIBUTES.get(2), 0.80),        // Reactions
            Map.entry(MENTAL_ATTRIBUTES.get(3), 0.20),        // Interceptions
            Map.entry(GOALKEEPER_ATTRIBUTES.getFirst(), 0.00),// GK Diving
            Map.entry(GOALKEEPER_ATTRIBUTES.get(1), 0.00),    // GK Handling
            Map.entry(GOALKEEPER_ATTRIBUTES.get(2), 0.00),    // GK Kicking
            Map.entry(GOALKEEPER_ATTRIBUTES.get(3), 0.00),    // GK Positioning
            Map.entry(GOALKEEPER_ATTRIBUTES.get(4), 0.00),    // GK Reflexes
            Map.entry(GOALKEEPER_ATTRIBUTES.get(5), 0.00)     // GK 1-on-1
    );

    private static final Map<String, Double> STRIKER_OVR_WEIGHTS = Map.ofEntries(
            Map.entry(TECHNICAL_ATTRIBUTES.getFirst(), 0.20),   // Finishing
            Map.entry(TECHNICAL_ATTRIBUTES.get(4), 0.15),       // Att. Positioning
            Map.entry(TECHNICAL_ATTRIBUTES.get(1), 0.10),       // Shot Power
            Map.entry(TECHNICAL_ATTRIBUTES.get(11), 0.104),     // Ball Control
            Map.entry(MENTAL_ATTRIBUTES.get(2), 0.094),         // Reactions
            Map.entry(MENTAL_ATTRIBUTES.get(1), 0.084),         // Composure
            Map.entry(TECHNICAL_ATTRIBUTES.get(5), 0.07),       // Heading Accuracy
            Map.entry(TECHNICAL_ATTRIBUTES.get(3), 0.05),       // Volleys
            Map.entry(PHYSICAL_ATTRIBUTES.getFirst(), 0.05),    // Sprint Speed
            Map.entry(PHYSICAL_ATTRIBUTES.get(4), 0.04),        // Acceleration
            Map.entry(PHYSICAL_ATTRIBUTES.get(1), 0.03),        // Strength
            Map.entry(PHYSICAL_ATTRIBUTES.get(2), 0.024),       // Agility
            Map.entry(PHYSICAL_ATTRIBUTES.get(3), 0.004)        // Balance
    );

    private static final Map<String, Double> STRIKER_STAT_WEIGHTS = Map.ofEntries(
    );

    // Type is doing nothing for now
    public static Footballer createPlayer(String position, String type, int rating, int potential, String name, LocalDate dateOfBirth) {
        Map<String, Double> ovrWeights = getOvrWeightsForPosition(position); // Must total 1.0
        Map<String, Double> distWeights = getWeightsForPosition(position);   // For realism
        Map<String, Integer> attributes = new HashMap<>();
        Random random = new Random();

        double minBase = 15.0;
        double maxBase = 99.0;

        // 1. Generate baseline attributes from distWeights (importance)
        for (Map.Entry<String, Double> entry : distWeights.entrySet()) {
            String attr = entry.getKey();
            double weight = entry.getValue();

            // Map weight [0..1] to attribute range [minBase..maxBase]
            double baseVal = minBase + weight * (maxBase - minBase);

            // Add small randomness ±5
            double valWithNoise = baseVal + (random.nextDouble() * 10) - 5;

            int finalVal = clamp((int) Math.round(valWithNoise), 1, 99);
            attributes.put(attr, finalVal);
        }

        // 2. Calculate weighted OVR using RW_OVR_WEIGHTS
        double weightedOVR = 0;
        for (Map.Entry<String, Double> entry : ovrWeights.entrySet()) {
            String attr = entry.getKey();
            double weight = entry.getValue();

            int attrVal = attributes.getOrDefault(attr, (int) Math.round(minBase)); // fallback
            weightedOVR += attrVal * weight;
        }

        // 3. Calculate scale factor to match desired OVR exactly
        double scaleFactor = rating / weightedOVR;

        // 4. Scale attributes by scaleFactor to hit desired OVR exactly
        for (String attr : attributes.keySet()) {
            int scaledVal = clamp((int) Math.round(attributes.get(attr) * scaleFactor), 1, 99);
            attributes.put(attr, scaledVal);
        }

        int result = calculateOVR(attributes, ovrWeights);

        setMainAttributes(attributes, rating, potential, dateOfBirth);

        if (position.equals("GK")) {
            return new Goalkeeper(name, attributes);
        }

        return new Footballer(name, position, type, attributes);
    }

    private static void setMainAttributes(Map<String, Integer> attributes, int rating, int potential, LocalDate dateOfBirth) {
        // We can add wage, height, weight, contract length and injury proneness as parameters later
        attributes.put(MAIN_ATTRIBUTES.get(0), rating);
        attributes.put(MAIN_ATTRIBUTES.get(1), potential);
        attributes.put(MAIN_ATTRIBUTES.get(2), 150000);
        int formattedDate = Integer.parseInt(dateOfBirth.format(DateTimeFormatter.BASIC_ISO_DATE));
        attributes.put(MAIN_ATTRIBUTES.get(3), formattedDate);
        attributes.put(MAIN_ATTRIBUTES.get(4), 6);
        attributes.put(MAIN_ATTRIBUTES.get(5), 160);
        attributes.put(MAIN_ATTRIBUTES.get(6), 24);
        attributes.put(MAIN_ATTRIBUTES.get(9), 10);
    }

    // For checking our ratings later on
    private static int calculateOVR(Map<String, Integer> attributes, Map<String, Double> ovrWeights) {
        double total = 0.0;
        double weightSum = 0.0;

        for (Map.Entry<String, Double> entry : ovrWeights.entrySet()) {
            String attr = entry.getKey();
            double weight = entry.getValue();
            int value = attributes.getOrDefault(attr, 50); // Fallback

            total += value * weight;
            weightSum += weight;
        }

        if (weightSum == 0) return 50;
        return (int) Math.round(total / weightSum);
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(value, max));
    }

    private static Map<String, Double> getWeightsForPosition(String position) {
        return getStringDoubleMap(position, GK_STAT_WEIGHTS, CB_STAT_WEIGHTS, FULL_BACK_STAT_WEIGHTS, CM_STAT_WEIGHTS, CAM_STAT_WEIGHTS, WINGER_STAT_WEIGHTS, STRIKER_STAT_WEIGHTS);
    }

    private static Map<String, Double> getOvrWeightsForPosition(String position) {
        return getStringDoubleMap(position, GK_OVR_WEIGHTS, CB_OVR_WEIGHTS, FULL_BACK_OVR_WEIGHTS, CM_OVR_WEIGHTS, CAM_OVR_WEIGHTS, WINGER_OVR_WEIGHTS, STRIKER_OVR_WEIGHTS);
    }

    private static Map<String, Double> getStringDoubleMap(String position, Map<String, Double> gkStatWeights, Map<String, Double> cbStatWeights, Map<String, Double> fullBackStatWeights, Map<String, Double> cmStatWeights, Map<String, Double> camStatWeights, Map<String, Double> wingerStatWeights, Map<String, Double> strikerStatWeights) {
        return switch (position.toUpperCase()) {
            case "GK" -> gkStatWeights;
            case "CB1", "CB2" -> cbStatWeights;
            case "RB", "LB" -> fullBackStatWeights;
            case "CM1", "CM2" -> cmStatWeights;
            case "CAM" -> camStatWeights;
            case "RW", "LW" -> wingerStatWeights;
            case "ST" -> strikerStatWeights;
            default -> throw new IllegalArgumentException("");
        };
    }

    // For when we add different player types
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
