package FileExtensionChecking;

import FileExtensionChecking.exceptions.NotHandledExtensionException;
import FileExtensionChecking.exceptions.OtherExtensionException;
import java.util.Map;
import java.util.Optional;

public class FileExtensionValidator {

    Map<String, String> handledExtensions;

    public FileExtensionValidator(Map handledExtensions) {
        this.handledExtensions = handledExtensions;
    }

    boolean checkExtension(byte[] fileBytes, String path)
        throws OtherExtensionException, NotHandledExtensionException {
        byte endOfFileByte = fileBytes[fileBytes.length - 1];
        String code = getBegginingByteCode(fileBytes);
        String extensionFromAPath = getExtensionFromPath(path);
        String codeByExtFromAPath = handledExtensions.get(extensionFromAPath);
        Optional<String> extActual = checkWhichBytesArePresentInMap(endOfFileByte, code);
        if (String.valueOf(endOfFileByte).equals("10") && extensionFromAPath
            .equals(extActual.get())) {
            return true;
        }
        if (!extActual.isPresent()) {
            throw new NotHandledExtensionException();
        } else {
            if (code.equals(codeByExtFromAPath) && extActual.get().equals(extensionFromAPath)) {
                return true;
            } else {
                Optional<String> extension = getActualExtension(extensionFromAPath);
                if (extension.isPresent()) {
                    throw new OtherExtensionException(extension.get());
                } else {
                    throw new NotHandledExtensionException();
                }
            }
        }
    }

    private Optional<String> checkWhichBytesArePresentInMap(byte endOfFileByte, String code) {
        Optional<String> extensionExpectedCode = null;
        if (getActualExtension(String.valueOf(endOfFileByte)).isPresent()) {
            extensionExpectedCode = getActualExtension(String.valueOf(endOfFileByte));
        } else if (getActualExtension(String.valueOf(code)).isPresent()) {
            extensionExpectedCode = getActualExtension(code);
        }
        return extensionExpectedCode;
    }

    private Optional<String> getActualExtension(String byteCode) {
        for (String key : handledExtensions.keySet()) {
            if (handledExtensions.get(key).equals(byteCode)) {
                return Optional.ofNullable(key);
            }
        }
        return Optional.empty();
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
        if (fileBytes.length > 6) {
            for (int i = 0; i < 6; i++) {
                code += String.valueOf(fileBytes[i]) + " ";
            }
        } else {
            for (int i = 0; i < fileBytes.length; i++) {
                code += String.valueOf(fileBytes[i]) + " ";
            }
        }
        return code.trim();
    }
}
