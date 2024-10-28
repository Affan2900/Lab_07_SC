// Create this in a separate file named FileSearcherTest.java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

class FileSearcherTest {
    private FileSearcher caseSensitiveSearcher;
    private FileSearcher caseInsensitiveSearcher;

    @BeforeEach
    void setUp() {
        caseSensitiveSearcher = new FileSearcher(true);
        caseInsensitiveSearcher = new FileSearcher(false);
    }

    @Test
    void testSearchNonExistentDirectory() {
        assertThrows(IllegalArgumentException.class,
            () -> caseSensitiveSearcher.searchFile("nonexistent/directory", "test.txt"));
    }

    @Test
    void testSearchFileFound(@TempDir Path tempDir) throws IOException {
        // Create test file
        File testFile = new File(tempDir.toFile(), "test.txt");
        assertTrue(testFile.createNewFile());

        // Test search
        List<File> results = caseSensitiveSearcher.searchFile(
            tempDir.toString(), "test.txt");
        
        assertEquals(1, results.size());
        assertTrue(results.contains(testFile));
    }

    @Test
    void testCaseInsensitiveSearch(@TempDir Path tempDir) throws IOException {
        File testFile = new File(tempDir.toFile(), "Test.txt");
        assertTrue(testFile.createNewFile());

        List<File> results = caseInsensitiveSearcher.searchFile(
            tempDir.toString(), "test.txt");
        
        assertEquals(1, results.size());
        assertEquals(testFile, results.get(0));
    }
}