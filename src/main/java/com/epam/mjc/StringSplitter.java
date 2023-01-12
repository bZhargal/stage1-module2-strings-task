package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */


    public static List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        String[] delimArr = delimiters.toArray(new String[0]);
        List<String> res = List.of(source.split(delimArr[0]));

        for (int i = 1; i < delimArr.length; i++) {
            List<String> arr = new ArrayList<>();
            for (String re : res) {
                List<String> resSplit = List.of(re.split(delimArr[i]));
                for (String resSplitEl : resSplit) {
                    if (!resSplitEl.equals("")) {
                        arr.add(resSplitEl);
                    }
                }
            }

            res = arr;
        }
        return res;
    }

}
