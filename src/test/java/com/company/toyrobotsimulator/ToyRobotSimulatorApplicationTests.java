package com.company.toyrobotsimulator;

import com.company.toyrobotsimulator.model.web.InputCommand;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToyRobotSimulatorApplicationTests {
	@Autowired
	private TestRestTemplate testRestTemplate;

	@MockBean
	AppStartupRunner appStartupRunner; //Suppresses scanner in commandline feature

	private String targetUrl = "/toy-robot/rest/command";

	@Test
	public void testInputCommandSuccess() throws JsonProcessingException {
		String input = "MOVE PLACE 0,1,EAST REPORT";
		InputCommand command = new InputCommand(input);

		ResponseEntity<String> response = testRestTemplate.exchange(targetUrl, HttpMethod.POST,buildHttpEntity(command, getHeadersValues()),
				String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(JsonPath.parse(response.getBody()).read("$.report").toString()).isEqualTo("0,1,EAST");
	}

	private HttpEntity<String> buildHttpEntity(Object requestBodyObj, Map<String, String> headersValues )
			throws JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();

		headersValues.forEach(headers::add);

		String requestBody = new ObjectMapper().writeValueAsString(requestBodyObj);

		return new HttpEntity<>(requestBody, headers);
	}
	private Map<String, String> getHeadersValues(){
		return Stream.of(new AbstractMap.SimpleEntry<>("Content-Type", MediaType.APPLICATION_JSON_VALUE))
				.collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
	}
}
