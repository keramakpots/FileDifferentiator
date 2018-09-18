package FileExtensionChecking.exceptions;

public class OtherExtensionException extends Exception {

    private String extension;

    public OtherExtensionException(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
