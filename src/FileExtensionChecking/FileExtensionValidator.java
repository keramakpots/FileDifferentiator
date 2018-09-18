package FileExtensionChecking;

import java.io.File;
import java.util.Map;

public class FileExtensionValidator {

    Map<String, String> handledExtensions;

    public FileExtensionValidator(Map handledExtensions) {
        this.handledExtensions = handledExtensions;
    }

    boolean checkExtension(File file) {

        return true;
    }
}
