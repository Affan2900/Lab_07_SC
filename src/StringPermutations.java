import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class StringPermutations {

    public static List<String> generatePermutations(String str) {
        List<String> result = new ArrayList<>();
        if (str == null || str.isEmpty()) {
            System.out.println("Error: Input string cannot be null or empty.");
            return result;
        }
        permute(str.toCharArray(), 0, result, new HashSet<>());
        return result;
    }

    private static void permute(char[] chars, int index, List<String> result, HashSet<String> uniquePermutations) {
        if (index == chars.length - 1) {
            String permutation = new String(chars);
            if (!uniquePermutations.contains(permutation)) {
                result.add(permutation);
                uniquePermutations.add(permutation);
            }
            return;
        }
        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            permute(chars, index + 1, result, uniquePermutations);
            swap(chars, index, i);  // backtrack
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to generate permutations: ");
        String input = scanner.nextLine();

        List<String> permutations = generatePermutations(input);

        System.out.println("Permutations:");
        for (String permutation : permutations) {
            System.out.println(permutation);
        }

        System.out.println("Total permutations: " + permutations.size());
        scanner.close();
    }
}
