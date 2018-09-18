package FileExtensionChecking;

import FileExtensionChecking.exceptions.NotHandledExtensionException;
import FileExtensionChecking.exceptions.OtherExtensionException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class FileExtensionValidator {

    Map<String, String> handledExtensions;

    public FileExtensionValidator(Map handledExtensions) {
        this.handledExtensions = handledExtensions;
    }

    boolean checkExtension(byte[] fileBytes, String path)
        throws NotHandledExtensionException, OtherExtensionException {
        String code = getBegginingByteCode(fileBytes);
        String extensionFromAPath = getExtensionFromPath(path);
        String extensionExpectedCode = handledExtensions.get(extensionFromAPath);
        if (extensionExpectedCode == null) {
            throw new NotHandledExtensionException();
        } else {
            if (code.equals(extensionExpectedCode)) {
                return true;
            } else {
                Optional<String> actualExtension = getActualExtension(extensionFromAPath);
                if (actualExtension.isPresent()) {
                    throw new OtherExtensionException(actualExtension.get());
                } else {
                    throw new NotHandledExtensionException();
                }
            }
        }
    }

    private Optional<String> getActualExtension(String extensionFromAPath) {
        return handledExtensions.entrySet()
            .stream()
            .filter(entry -> Objects.equals(entry.getValue(), extensionFromAPath))
            .map(Map.Entry::getKey)
            .findFirst();
    }

    private String getExtensionFromPath(String path) {
        int lastIndexOf = path.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return path.substring(lastIndexOf + 1);
    }

    private String getBegginingByteCode(byte[] fileBytes) {
        String code = "";
        for (int i = 0; i < 10; i++) {
            code += String.valueOf(fileBytes[i]) + " ";
        }
        return code.trim();
    }
}
