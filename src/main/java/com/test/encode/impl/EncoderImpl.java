package com.test.encode.impl;

import static java.util.stream.Collectors.toList;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;

import com.test.encode.Encoder;

/**
 * The Class EncoderImpl is used to uncompress the provided String.
 * 
 * The expected format is as per the following example:
 * 
 * input: AABBBBBBCCCCCHHHHHHHHHHPP 
 * Encoded Output: AA{B;6}CCCCC{H;10}PP
 * 
 */
public class EncoderImpl implements Encoder {

	private static final int MAX_OCCURENCE = 5;

	/* (non-Javadoc)
	 * @see com.test.encode.Encoder#encode(java.lang.String)
	 */
	public String encode(String s) {
		if (StringUtils.isBlank(s)) {
			return s;
		}
		
		final List<String> strList = Arrays.asList(s.split(""));

		//Creates a sublist with a map of character and number of occurrence.
		final List<SimpleEntry<Integer, String>> finalList = createSubList(strList).stream()
				.map(l -> new SimpleEntry<>(l.size(), l.get(0))).collect(toList());

		//iterates map and encodes the characters which is repeated for more than 5 times.
		final List<String> updatedStr = finalList.stream().flatMap(e -> {
			if (e instanceof SimpleEntry) {

				final SimpleEntry<Integer, String> entry = (SimpleEntry<Integer, String>) e;

				if (entry.getKey() > MAX_OCCURENCE) {
					return Stream.of("{" + (String) entry.getValue() + ";" + entry.getKey() + "}");
				} else {
					return Collections.nCopies(entry.getKey(), entry.getValue()).stream();
				}
			}
			return Stream.of("");
		}).collect(toList());

		//Concatenates string values from list.
		final String result = updatedStr.stream().reduce("", String::concat);

		return result;
	}
	
	/**
	 * This method packs consecutive duplicates of list elements into sublists .
	 * 
	 * input: Arrays.asList("a", "a", "a", "a", "b", "c", "c", "a", "a", "d", "e", "e", "e", "e"))
 *     output: [["a","a","a","a"],["b"],["c","c"],["a","a"],["d"],["e","e","e","e"]]
	 *
	 * @param <T> the generic type
	 * @param list the list
	 * @return the list
	 */
	private <T> List<List<T>> createSubList(List<T> list) {

        T lastElement = null;
        final List<List<T>> packedList = new ArrayList<>();
        List<T> elements = new ArrayList<>();
        for (T el : list) {
            if (!Objects.equals(lastElement, el)) {
                elements = new ArrayList<>();
                packedList.add(elements);
            }
            elements.add(el);
            lastElement = el;
        }
        return packedList;
    }

}
