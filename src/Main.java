// Main.java
import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Ensure at least two arguments are provided: directory path and file name.
        if (args.length < 2) {
            System.out.println("Usage: java Main <directory_path> <file_name> [case_sensitive]");
            return;
        }

        // Parse the command-line arguments.
        String directoryPath = args[0];
        String fileName = args[1];
        boolean caseSensitive = args.length > 2 && Boolean.parseBoolean(args[2]);

        try {
            // Create a new FileSearcher instance with the specified case sensitivity.
            FileSearcher searcher = new FileSearcher(caseSensitive);

            // Perform the search in the specified directory for the given file name.
            List<File> foundFiles = searcher.searchFile(directoryPath, fileName);

            // Display the results.
            if (foundFiles.isEmpty()) {
                System.out.println("No files found matching: " + fileName);
            } else {
                System.out.println("Found " + searcher.getTotalMatches() + " matching file(s):");
                for (File file : foundFiles) {
                    System.out.println(file.getAbsolutePath());
                }
            }
        } catch (IllegalArgumentException e) {
            // Handle any errors encountered during the search.
            System.err.println("Error: " + e.getMessage());
        }
    }
}
