package FileExtensionChecking;

import FileExtensionChecking.util.Logger;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileBinaryConverter {

    public FileBinaryConverter() {
    }

    byte[] read(String inputFileName) throws IOException {
        Logger.log("Reading in binary file named : " + inputFileName);
        File file = new File(inputFileName);
        Logger.log("File size: " + file.length());
        byte[] result = new byte[(int) file.length()];
        try {
            InputStream input = null;
            try {
                int totalBytesRead = 0;
                input = new BufferedInputStream(new FileInputStream(file));
                while (totalBytesRead < result.length) {
                    int bytesRemaining = result.length - totalBytesRead;
                    int bytesRead = input.read(result, totalBytesRead, bytesRemaining);
                    if (bytesRead > 0) {
                        totalBytesRead = totalBytesRead + bytesRead;
                    }
                }
                Logger.log("Num bytes read: " + totalBytesRead);
            } finally {
                Logger.log("Closing input stream.");
                input.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.log("File not found.");
        }
        return result;
    }
}
