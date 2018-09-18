package FileExtensionChecking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ApplicationTest {

    private final String PATH_TO_JPG_FILE = "/home/marek/kodowanie/Java/FileDifferentiator/testFiles/małże jpg.jpg";
    private final Application application = new Application();

    @BeforeAll
    void prepareTest() {
        application.addHandledExtensions();
    }

    @Test
    void testIfMethodForCheckingExtensionSaysTrueWhenIsProperExtension() {
        assertEquals("File " + PATH_TO_JPG_FILE + " has true extension",
            application.checkIfFileHasProperExtension(PATH_TO_JPG_FILE));
    }
}