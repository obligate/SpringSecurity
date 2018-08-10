/**
 * 
 */
package com.peter.web.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

/**
 * @author peter
 *
 */
public class WireMockServer {

	/**
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		configureFor(8089);
		removeAllMappings();
		mock("/order/1","01");
		
	}

	private static void mock(String url, String fileName) throws IOException {
		ClassPathResource resource = new ClassPathResource("mock/response/"+fileName+".txt");
		String content = FileUtils.readFileToString(resource.getFile(),"utf-8");
		stubFor(get(urlPathEqualTo(url)).willReturn(aResponse()
				.withBody(content).withStatus(200)
				.withHeader("Content-Type", "application/json;charset=UTF-8")));
	}

}
