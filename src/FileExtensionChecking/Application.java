package FileExtensionChecking;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Application {

    private final String PATH_TO_GIF_FILE = "/home/marek/kodowanie/Java/FileDifferentiator/testFiles/Loading_icon.gif";
    private final String PATH_TO_TXT_FILE = "/home/marek/kodowanie/Java/FileDifferentiator/testFiles/task.txt";
    private final String PATH_TO_JPG_FILE = "/home/marek/kodowanie/Java/FileDifferentiator/testFiles/małże jpg.jpg";
    private final String PATH_TO_FILE_WITH_ERROR = "/home/marek/kodowanie/Java/FileDifferentiator/testFiles/małże.gif";

    private Map<String, String> addHandledExtensions() {
        Map<String, String> handledExtension = new HashMap<>();
        handledExtension.put("jpg", "FF D8 FF E0 00 10 4A 46 49 46 00 01");
        handledExtension.put("gif", "47 49 46 38 37 61");
        handledExtension.put("txt", "FF D8 FF E0 00");
        return handledExtension;
    }

    public void run() {
        FileBinaryConverter fileBinaryConverter = new FileBinaryConverter();
        String fileBytes = fileBinaryConverter.read(PATH_TO_GIF_FILE).toString();
        FileExtensionValidator fileExtensionValidator = new FileExtensionValidator(
            addHandledExtensions());
        try {
            System.out.println(fileExtensionValidator.checkExtension(fileBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
