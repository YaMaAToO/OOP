import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileReadWrite {
    public static void main(String[] args) {
        String inputFile = "src/input.txt";
        String outputFile = "output.txt";

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {

            int charCount = 0;
            int wordCount = 0;
            int lineCount = 0;
            boolean isWord = false;
            int character;

            StringBuilder content = new StringBuilder();

            while ((character = fis.read()) != -1) {
                content.append((char) character);
                charCount++;

                if (character == '\n') {
                    lineCount++;
                    isWord = false;
                } else if (character == ' ' || character == '\t') {
                    isWord = false;
                } else if (!isWord) {
                    wordCount++;
                    isWord = true;
                }
            }

            if (content.length() > 0 && content.charAt(content.length() - 1) != '\n') {
                lineCount++;
            }

            System.out.println("Кількість символів: " + charCount);
            System.out.println("Кількість слів: " + wordCount);
            System.out.println("Кількість рядків: " + lineCount);

            fos.write(content.toString().getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
