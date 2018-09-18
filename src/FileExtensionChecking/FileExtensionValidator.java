package FileExtensionChecking;

import java.io.IOException;
import java.util.Map;

public class FileExtensionValidator {

    Map<String, String> handledExtensions;

    public FileExtensionValidator(Map handledExtensions) {
        this.handledExtensions = handledExtensions;
    }

    boolean checkExtension(String fileBytes) throws IOException {
        System.out.println(fileBytes);
        return true;
    }
}
