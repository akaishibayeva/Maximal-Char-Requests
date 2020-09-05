import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'getMaxCharCount' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. 2D_INTEGER_ARRAY queries
     */

    public static List<Integer> getMaxCharCount(String s, List<List<Integer>> queries) {
        // queries is a n x 2 array where queries[i][0] and queries[i][1] represents x[i] and y[i] for the ith query.
        s = s.toLowerCase();
        List<Integer> output = new ArrayList<>();
        for (int i = 0; i < queries.size(); i++) {
            String intervalStr = s.substring(queries.get(i).get(0),queries.get(i).get(1)+1);

            int counter = 0;
            char temp = intervalStr.charAt(0);
            for (int j = 0; j < intervalStr.length();j++) {
                if (temp < intervalStr.charAt(j)) {
                    temp = intervalStr.charAt(j);
                    counter = 1;
                } else if (temp == intervalStr.charAt(j)) {
                    counter++;
                }
            }
            output.add(counter);
        }


        return output;
    }

}
//
//5
//        ddaaa
//        1
//        0 4

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> query = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                query.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = Result.getMaxCharCount(s, query);

        bufferedWriter.write(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
