public class Validation {

    // Validates if the input is a valid double and returns an error message if not
    public String isDouble(String input, String fieldName) {

        if (input == null || input.trim().isEmpty()) {
            return fieldName + " cannot be empty.\n";
        }
        try {
            double value = Double.parseDouble(input);
            if (value > 0) {
                return "";
            } else {
                return fieldName + " must be a positive number.\n";
            }
        } catch (NumberFormatException e) {

            return fieldName + " must be a valid number.\n";
        }
    }
}
