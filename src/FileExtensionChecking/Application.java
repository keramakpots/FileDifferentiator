package FileExtensionChecking;

import FileExtensionChecking.exceptions.NotHandledExtensionException;
import FileExtensionChecking.exceptions.OtherExtensionException;
import FileExtensionChecking.util.Logger;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Application {

    private final String PATH_TO_GIF_FILE = "testFiles/DanceGif.gif";
    private final String PATH_TO_GIF2_FILE = "testFiles/Loading_icon.gif";
    private final String PATH_TO_TXT_FILE = "testFiles/task.txt";
    private final String PATH_TO_TXT2_FILE = "testFiles/test.txt";
    private final String PATH_TO_JPG_FILE = "testFiles/małże jpg.jpg";
    private final String PATH_TO_JPG2_FILE = "testFiles/IMG.jpg";
    private final String PATH_TO_FILE_WITH_ERROR = "testFiles/małże.gif";

    public Map<String, String> addHandledExtensions() {
        Map<String, String> handledExtension = new HashMap<>();
        handledExtension.put("jpg", "-1 -40 -1 -32 0 16");
        handledExtension.put("gif", "71 73 70 56 57 97");
        handledExtension.put("txt", "10");
        return handledExtension;
    }

    public void run() {
        try {
            checkIfFileHasProperExtension(PATH_TO_TXT_FILE);
            checkIfFileHasProperExtension(PATH_TO_TXT2_FILE);
            checkIfFileHasProperExtension(PATH_TO_GIF_FILE);
            checkIfFileHasProperExtension(PATH_TO_GIF2_FILE);
            checkIfFileHasProperExtension(PATH_TO_JPG_FILE);
            checkIfFileHasProperExtension(PATH_TO_JPG2_FILE);
            checkIfFileHasProperExtension(PATH_TO_FILE_WITH_ERROR);
        } catch (OtherExtensionException e) {
            Logger.log("File has different extension than its stays. It's " + e.getExtension()
                + " extension");
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
            Logger.log("-----------");
            Logger.log(message);
            Logger.log("-----------");
        } catch (IOException e) {
            Logger.log(e);
        }
        return message;
    }
}
