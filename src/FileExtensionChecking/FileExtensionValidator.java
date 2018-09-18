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
        String extActual = checkWhichBytesArePresentInMap(endOfFileByte, code);
        if (String.valueOf(endOfFileByte).equals("10") && extensionFromAPath.equals(extActual)) {
            return true;
        }
        if (extActual == null) {
            throw new NotHandledExtensionException();
        } else {
            if (code.equals(codeByExtFromAPath) && extActual.equals(extensionFromAPath)) {
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

    private String checkWhichBytesArePresentInMap(byte endOfFileByte, String code) {
        String extensionExpectedCode = null;
        if (getActualExtension(String.valueOf(endOfFileByte)).isPresent()) {
            extensionExpectedCode = handledExtensions
                .get(getActualExtension(String.valueOf(endOfFileByte)).get());
        } else if (getActualExtension(String.valueOf(code)).isPresent()) {
            extensionExpectedCode = handledExtensions
                .get(getActualExtension(code).get());
        }
        return extensionExpectedCode;
    }

    private Optional<String> getActualExtension(String byteCode) {
        for (String key : handledExtensions.keySet()) {
            if (handledExtensions.get(key).equals(byteCode)) {
                return Optional.ofNullable(handledExtensions.get(key));
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
