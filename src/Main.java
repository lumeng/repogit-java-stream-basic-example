import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

import java.lang.String;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;
import static java.util.stream.IntStream.range;

/**
 * Author: XXX XXX <XXX@gmail.com>
 * Date: 8/27/14 8:15 PM
 */
public class Main {

    public static void main(String[] args) {


        // Example 1
        // Sort strings by the second character.

        Random r = new Random();

        String[] array = new String[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = randomString(r, 'a', 'z', 10);
        }

        // SortBy[array, #[[3]]&]
        Arrays.sort(array, (a, b) -> a.charAt(1) - b.charAt(1));

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);

        }


        // Example 2
        // Generate random nucleic acid sequences and sort by initial nucleobase.

        HashMap<Integer, Character> nucleobases = new HashMap<> ();
        nucleobases.put(1, 'A');
        nucleobases.put(2, 'G');
        nucleobases.put(3, 'T');
        nucleobases.put(4, 'C');


        range(0, 100)
                // generate a stream containing random strings of length 10
                .mapToObj(i -> randomNucleicAcidSequence(new Random(), nucleobases, 10))
                // sort the elements in the stream to natural ordering
                .sorted()
                // group strings into sub-lists and wrap them into a stream
                .collect(groupingBy(name -> name.charAt(0)))
                // print each sub-list's common initial letter and the constituent strings
                .forEach((letter, names) -> System.out.println(letter
                        + "\n\t"
                        + names.stream().collect(joining("\n\t"))));
    }

    public static String randomString (Random r, char from, char to, int length) {
        return r.ints(from, to + 1).limit(length).mapToObj(
                x -> Character.toString((char)x)).collect(Collectors.joining());
    }

    public static String randomNucleicAcidSequence(Random r,
                                                   Map<Integer, Character> map,
                                                   int length) {
        return r.ints(1, 4).limit(length).mapToObj(
                x -> Character.toString(map.get(x))).collect(Collectors.joining());
    }


}
