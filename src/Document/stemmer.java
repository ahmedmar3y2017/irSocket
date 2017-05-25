/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class stemmer {

    public static List<String> stem(List<String> words, List<String> end_chars1, List<String> end_chars2) {
        int i;
        int j;
        List<String> result = new ArrayList<>();
        int count = 0;

        for (i = 0; i < words.size(); i++) {
            if ((words.get(i).endsWith("ied") || words.get(i).endsWith("ies"))) {
                for (j = 0; j < end_chars2.size(); j++) {
                    if (words.get(i).endsWith(end_chars2.get(j))) {
                        result.add(words.get(i).replaceAll(end_chars2.get(j), "y"));
                        count++;
                        break;
                    }
                }
            } else {
                int f = 0;
                for (j = 0; j < end_chars1.size(); j++) {
                    if (words.get(i).endsWith(end_chars1.get(j))) {
                        f = 1;
                        break;
                    }
                }
                if (f == 0) {
                    result.add(words.get(i));
                    count++;
                } else {
                    for (j = 0; j < end_chars1.size(); j++) {
                        if (words.get(i).endsWith(end_chars1.get(j))) {
                            result.add(words.get(i).replaceAll(end_chars1.get(j), ""));
                            count++;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> words_without_stopwords = new ArrayList<>();
        String[] x1 = {"playing", "eats", "transportation", "entertainmentment", "studied"};
        words_without_stopwords.addAll(Arrays.asList(x1));
        // System.out.println(words_without_stopwords);
        //
        List<String> end_char1 = new ArrayList<>();
        String[] x2 = {"ing", "ed", "s", "es", "ess", "esss", "able", "ation", "ation"};
        end_char1.addAll(Arrays.asList(x2));
        // System.out.println(end_char1);
        //
        List<String> end_char2 = new ArrayList<>();
        String[] x3 = {"ies", "ied"};
        end_char2.addAll(Arrays.asList(x3));
        // System.out.println(end_char2);
        //
        List<String> output = stem(words_without_stopwords, end_char1, end_char2);
        System.out.println(output);

    }

}
