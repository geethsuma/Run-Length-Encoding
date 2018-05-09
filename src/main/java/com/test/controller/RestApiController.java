package com.test.controller;

import java.time.Instant;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.data.RequestMessage;
import com.test.data.ResponseMessage;
import com.test.decode.impl.DecoderImpl;
import com.test.encode.impl.EncoderImpl;

@RestController
public class RestApiController {

	@RequestMapping(value = "/encode",
            method = RequestMethod.POST,
            consumes = {"application/json"},
            produces = {"application/json"})
	public ResponseMessage encode(@RequestBody RequestMessage message) {
		final Instant instantStart = Instant.now();
		
		final ResponseMessage response = new ResponseMessage();
		response.setText(new EncoderImpl().encode(message.getText()));
		response.setTime(Integer.toString(Instant.now().getNano() - instantStart.getNano()));
		
		return response;
		
	}
	
	@RequestMapping(value = "/decode",
            method = RequestMethod.POST,
            consumes = {"application/json"},
            produces = {"application/json"})
	public ResponseMessage encodeString(@RequestBody RequestMessage message) {
		final Instant instantStart = Instant.now();
		
		final ResponseMessage response = new ResponseMessage();
		response.setText(new DecoderImpl().decode(message.getText()));
		
		response.setTime(Integer.toString(Instant.now().getNano() - instantStart.getNano()));
		return response;
		
	}
	
	
}
