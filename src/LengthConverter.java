import java.io.*;
import java.util.Scanner;


public class LengthConverter {
    static double delta = 0.0001;

    public static double kmToMile(double km){
        double result = 0.0;
        if( km >= 0.0){
           result = km * 0.621371192;
        }
        return result;
    }
    public static double mileToKm(double mile){
        double result = 0.0;
        if (mile >= 0.0){
            result = mile / 0.621371192;
        }
        return result;
    }

    public static boolean isDouble(String str){
        try {
            Double.parseDouble(str);
            return true;
        }
        catch (NumberFormatException numberFormatException) {
            throw new NumberFormatException("Argument is not of type Double" );
        }
    }
    public static void LengthConverterTest() throws IOException {
        FileWriter outputFile = new FileWriter("output.txt", true);
        try {
            FileReader kmToMileInputFile = new FileReader("kmToMile - input.txt");
            Scanner scanner = new Scanner(kmToMileInputFile);
            outputFile.write("Km To Mile - Output\n");

            while (scanner.hasNextLine()) {
                try {
                    String record = scanner.nextLine();
                    if(record.equals("")){
                        throw new IllegalArgumentException("Empty Line");
                    }
                    if (!record.contains(" ")) {
                        throw new IllegalArgumentException("No spaces between each element in the array");
                    }
                    String subArray = record.substring(1, record.length() - 1);
                    String[] temp = subArray.split(", ");
                    if (temp.length < 3) {
                        throw new IllegalArgumentException("One or all of the arguments is not passed");
                    }
                    if (temp.length > 3) {
                        throw new IllegalArgumentException("More values passed in the array");
                    }

                    if (isDouble(temp[1]) && isDouble(temp[2])) {
                        if (Double.parseDouble(temp[0]) < 0.0) {
                            throw new IllegalArgumentException("Id is negative");
                        }
                        if (Double.parseDouble(temp[1]) < 0.0) {
                            throw new IllegalArgumentException("Input for Km to Mile is negative");
                        }
                        if (Double.parseDouble(temp[2]) < 0.0) {
                            throw new IllegalArgumentException("Expected Output is negative");
                        }
                        String id = temp[0];
                        double inputKmToMile;
                        double expectedOutputKmToMile;
                        inputKmToMile = Double.parseDouble(temp[1]);
                        expectedOutputKmToMile = Double.parseDouble(temp[2]);
                        double kmToMileResult = kmToMile(inputKmToMile);
                        if (Math.abs(expectedOutputKmToMile - kmToMileResult) <= delta) {
                            outputFile.write("[" + id + ", Pass]\n");
                        } else {
                            outputFile.write("[" + id + ", Fail]\n");
                        }
                    }
                } catch (Exception exception) {
                    outputFile.write(exception + "\n");
                }
            }
            scanner.close();
        }
        catch(FileNotFoundException fileNotFoundException){
            outputFile.write("File is not found\n");
        }

        try {
            FileReader mileToKmInputFile = new FileReader("mileToKm - input.txt");
            Scanner scanner1 = new Scanner(mileToKmInputFile);
            outputFile.write("\nMile To Km - Output\n" );

            while(scanner1.hasNextLine()) {
                try{
                    String record = scanner1.nextLine();
                    if(record.equals("")){
                        throw new IllegalArgumentException("Empty Line");
                    }
                    if (!record.contains(" ")) {
                        throw new IllegalArgumentException("No spaces between each element in the array");
                    }

                    String subArray = record.substring(1, record.length() - 1);
                    String[] temp = subArray.split(", ");

                    if (temp.length < 3) {
                        throw new IllegalArgumentException("One or all of the arguments is not passed");
                    }
                    if (temp.length > 3) {
                        throw new IllegalArgumentException("More values passed in the array");
                    }

                    if (isDouble(temp[1]) && isDouble(temp[2])) {

                        if (Double.parseDouble(temp[0]) < 0.0) {
                            throw new IllegalArgumentException("Id is negative");
                        }
                        if (Double.parseDouble(temp[1]) < 0.0) {
                            throw new IllegalArgumentException("Input for Mile to Km is negative");
                        }
                        if (Double.parseDouble(temp[2]) < 0.0) {
                            throw new IllegalArgumentException("Expected Output is negative");
                        }

                        String id = temp[0];
                        double inputMileToKm = Double.parseDouble(temp[1]);
                        double expectedOutputMileToKm = Double.parseDouble(temp[2]);
                        double mileToKmResult = mileToKm(inputMileToKm);
                        if (Math.abs(expectedOutputMileToKm - mileToKmResult) <= delta) {
                            outputFile.write("[" + id + ", Pass]\n");
                        } else {
                            outputFile.write("[" + id + ", Fail]\n");
                        }
                    }

                }  catch (Exception exception) {
                    outputFile.write(exception + "\n");
                }
            }
            scanner1.close();
        }
        catch(FileNotFoundException fileNotFoundException){
            outputFile.write("File is not found\n");
        }
        outputFile.close();
    }

    public static void createAFile(){
        try {
            File myObj = new File("output.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

