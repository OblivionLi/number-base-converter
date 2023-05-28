package converter;

import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner;

    UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            String conversionOption = this.getFirstLevelOption();
            if (conversionOption.equals("/exit")) {
                break;
            }

            String[] conversionOptionParts = conversionOption.split(" ");

            while (true) {
                String inputToBeConversed = this.getSecondLevelOption(conversionOptionParts[0], conversionOptionParts[1]);
                if (inputToBeConversed.equals("/back")) {
                    break;
                }

                this.getResults(conversionOptionParts, inputToBeConversed);
            }
        }
    }

    private void getResults(String[] optionParts, String valueToConvert) {
        Conversion c = new Conversion(Integer.parseInt(optionParts[0]), Integer.parseInt(optionParts[1]), valueToConvert);
        System.out.println("Conversion result: " + c.getConversionResult().toLowerCase());
    }

    private String getFirstLevelOption() {
        System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
        return this.scanner.nextLine();
    }

    private String getSecondLevelOption(String sourceBase, String targetBase) {
        System.out.print("Enter number in base " + sourceBase + " to convert to base " + targetBase + " (To go back type /back) ");
        return this.scanner.nextLine();
    }
}
