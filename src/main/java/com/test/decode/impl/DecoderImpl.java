package com.test.decode.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.test.decode.Decoder;

/**
 * The Class DecoderImpl is used to uncompress the provided String.
 * 
 * The expected format is as per the following example:
 * 
 * input: AA{B;6}CCCCC{H;10}PP
 * Decoded Output: AABBBBBBCCCCCHHHHHHHHHHPP
 * 
 */
public class DecoderImpl implements Decoder {

	/* (non-Javadoc)
	 * @see com.test.decode.Decoder#decode(java.lang.String)
	 */
	@Override
	public String decode(String s) {
		if (StringUtils.isBlank(s)) {
			return s;
		}

		/**
		 * The input string is split based on the ; character.#
		 * The number of occurrence(n) is extracted from the string to 
		 * copy the character n times to create the de-compressed string.
		 */
		final List<String> encodedStrList = Arrays.asList(s.split(";"));

		StringBuilder decodedStr = new StringBuilder();

		for (String str : encodedStrList) {
			if (Character.isDigit(str.charAt(0))) {
				final String extractDigits = extractDigits(str);
				final int repeatCount = Integer.valueOf(extractDigits).intValue();
				final char repeatChar = decodedStr.charAt(decodedStr.length() - 1);

				decodedStr.replace(decodedStr.length() - 2, decodedStr.length(),
						StringUtils.repeat(String.valueOf(repeatChar), repeatCount));
				decodedStr.append(str.substring(extractDigits.length() + 1));

			} else {
				decodedStr.append(str);
			}
		}

		return decodedStr.toString();
	}
	
	/**
	 * Extract consecutive digits from the beginning of a given string.
	 *
	 * @param src the input String
	 * @return the string
	 */
	private String extractDigits(String src) {

		if(StringUtils.isBlank(src)) {
			return "";
		}
		
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < src.length(); i++) {
            final char c = src.charAt(i);
            if (Character.isDigit(c)) {
                builder.append(c);
            } else {
                return builder.toString();
            }
        }
        return builder.toString();
    }

}
