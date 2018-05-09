package com.test.decode.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.test.decode.Decoder;

public class DecoderImplTest {
	
	private Decoder decoder;
	
	@Before
	public void setup() {
		decoder = new DecoderImpl();
	}
	
	@Test
	public void decodeShouldUnCompressCorrectly() {
		Map<String, String> inputList = new HashMap<>();
		
		inputList.put("AA{B;7}CC", "AABBBBBBBCC");
		inputList.put("AA{B;7}CC{H;11}PP", "AABBBBBBBCCHHHHHHHHHHHPP");
		inputList.put("AA{B;7}CC{H;11}PP{A;10}", "AABBBBBBBCCHHHHHHHHHHHPPAAAAAAAAAA");
		
		for (String key : inputList.keySet()) {
		    assertEquals(inputList.get(key), decoder.decode(key));
		}
		
	}
	
	@Test
	public void decodeShouldHandleEmptyAndNullInput() {
		assertNull(decoder.decode(null));
		assertEquals("", decoder.decode(""));
		assertEquals("   ", decoder.decode("   "));
	}


}
