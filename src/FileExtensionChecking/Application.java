package FileExtensionChecking;

import FileExtensionChecking.exceptions.NotHandledExtensionException;
import FileExtensionChecking.exceptions.OtherExtensionException;
import FileExtensionChecking.util.Logger;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Application {

    private final String PATH_TO_GIF_FILE = "/home/marek/kodowanie/Java/FileDifferentiator/testFiles/Loading_icon.gif";
    private final String PATH_TO_TXT_FILE = "/home/marek/kodowanie/Java/FileDifferentiator/testFiles/task.txt";
    private final String PATH_TO_TXT2_FILE = "/home/marek/kodowanie/Java/FileDifferentiator/testFiles/test.txt";
    private final String PATH_TO_JPG_FILE = "/home/marek/kodowanie/Java/FileDifferentiator/testFiles/małże jpg.jpg";
    private final String PATH_TO_FILE_WITH_ERROR = "/home/marek/kodowanie/Java/FileDifferentiator/testFiles/małże.gif";

    public Map<String, String> addHandledExtensions() {
        Map<String, String> handledExtension = new HashMap<>();
        handledExtension.put("jpg", "FF D8 FF E0 00 10 4A 46 49 46 00 01");
        handledExtension.put("gif", "47 49 46 38 37 61");
        handledExtension.put("txt", "FF D8 FF E0 00");
        return handledExtension;
    }

    public void run() {
        try {
            checkIfFileHasProperExtension(PATH_TO_TXT_FILE);
            checkIfFileHasProperExtension(PATH_TO_TXT2_FILE);
            checkIfFileHasProperExtension(PATH_TO_GIF_FILE);
            checkIfFileHasProperExtension(PATH_TO_JPG_FILE);
            checkIfFileHasProperExtension(PATH_TO_FILE_WITH_ERROR);
        } catch (OtherExtensionException e) {
            Logger.log("File has different extension than its stays. It's " + e.getExtension()
                + "extemsion");
        } catch (NotHandledExtensionException e) {
            Logger.log("File extension is not supported");
        }
    }

    public String checkIfFileHasProperExtension(String path)
        throws OtherExtensionException, NotHandledExtensionException {
        FileBinaryConverter fileBinaryConverter = new FileBinaryConverter();
        String message = "";
        try {
            byte[] fileBytes = fileBinaryConverter.read(path);
            FileExtensionValidator fileExtensionValidator = new FileExtensionValidator(
                addHandledExtensions());
            fileExtensionValidator.checkExtension(fileBytes, path);
            message = "File " + path + " has true extension";
            Logger.log(message);
        } catch (IOException e) {
            Logger.log(e);
        }
        return message;
    }
}
