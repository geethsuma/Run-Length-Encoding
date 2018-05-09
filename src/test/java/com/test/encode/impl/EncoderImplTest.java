package com.test.encode.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.test.encode.Encoder;

public class EncoderImplTest {

	private Encoder encoder;
	
	@Before
	public void setup() {
		encoder = new EncoderImpl();
	}
	
	@Test
	public void encodeShouldNotCompressIfCharacterOccurenceIsLessThanFive() {
		List<String> inputList = Arrays.asList(new String[] {"AA", "AABBCC", "AABCDEFG", "A     B", "A    ", "    B"});
		
		for (String input : inputList) {
			assertEquals(input, encoder.encode(input));
		}
	}
	
	@Test
	public void encodeShouldCompressIfCharacterOccurenceIsMoreThanFive() {
		Map<String, String> inputList = new HashMap<>();
		
		inputList.put("AABBBBBBBCC", "AA{B;7}CC");
		inputList.put("AABBBBBBBCCHHHHHHHHHHHPP", "AA{B;7}CC{H;11}PP");
		inputList.put("AABBBBBBBCCHHHHHHHHHHHPPAAAAAAAAAA", "AA{B;7}CC{H;11}PP{A;10}");
		
		for (String key : inputList.keySet()) {
		    assertEquals(inputList.get(key), encoder.encode(key));
		}
		
	}
	
	@Test
	public void encodeShouldHandleEmptyAndNullInput() {
		assertNull(encoder.encode(null));
		assertEquals("", encoder.encode(""));
		assertEquals("   ", encoder.encode("   "));
	}
	
}
