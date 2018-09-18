package FileExtensionChecking;

import java.util.Map;

public class FileExtensionValidator {

    Map<String, String> handledExtensions;

    public FileExtensionValidator(Map handledExtensions) {
        this.handledExtensions = handledExtensions;
    }

    boolean checkExtension(byte[] fileBytes, String path) {
        String code = "";
        for (int i = 0; i < 10; i++) {
            code += String.valueOf(fileBytes[i]) + " ";
        }
        code.trim();
        return true;
    }
}
