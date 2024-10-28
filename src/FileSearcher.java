// FileSearcher.java
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class that provides recursive file searching capabilities within directories.
 */
public class FileSearcher {
    private final boolean caseSensitive;
    private int totalMatches;
    private final List<File> matchedFiles;

    /**
     * Constructs a new FileSearcher with specified case sensitivity.
     *
     * @param caseSensitive whether the search should be case-sensitive
     */
    public FileSearcher(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
        this.totalMatches = 0;
        this.matchedFiles = new ArrayList<>();
    }

    /**
     * Searches for a file in the specified directory and its subdirectories.
     *
     * @param directoryPath the starting directory path
     * @param fileName      the name of the file to search for
     * @return List of File objects representing the matched files
     * @throws IllegalArgumentException if the directory path is invalid or inaccessible
     */
    public List<File> searchFile(String directoryPath, String fileName) {
        matchedFiles.clear();
        totalMatches = 0;

        File directory = new File(directoryPath);
        validateDirectory(directory);
        searchFileRecursively(directory, fileName);
        return matchedFiles;
    }

    private void searchFileRecursively(File directory, String fileName) {
        File[] files = directory.listFiles();
        
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isFile()) {
                String currentFileName = file.getName();
                boolean matches = caseSensitive 
                    ? currentFileName.equals(fileName)
                    : currentFileName.equalsIgnoreCase(fileName);

                if (matches) {
                    matchedFiles.add(file);
                    totalMatches++;
                }
            } else if (file.isDirectory()) {
                searchFileRecursively(file, fileName);
            }
        }
    }

    private void validateDirectory(File directory) {
        if (!directory.exists()) {
            throw new IllegalArgumentException("Directory does not exist: " + directory.getPath());
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Path is not a directory: " + directory.getPath());
        }
        if (!directory.canRead()) {
            throw new IllegalArgumentException("Directory is not readable: " + directory.getPath());
        }
    }

    public int getTotalMatches() {
        return totalMatches;
    }
}

