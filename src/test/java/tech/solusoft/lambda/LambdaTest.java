package tech.solusoft.lambda;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import tech.solusoft.lambda.body.LambdaBodyOut;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;



public class LambdaTest {

	@Test
	public void testSum() {
		APIGatewayProxyRequestEvent apiGatewayProxyInputEvent = new APIGatewayProxyRequestEvent();
		apiGatewayProxyInputEvent
			.withIsBase64Encoded(false)
			.withBody("{ \"arguments\": [2, 4, 6] }")
			.withHttpMethod("POST")
			.withResource("/sum");
		MicrolamJava19Demo lambda = new MicrolamJava19Demo();
		APIGatewayProxyResponseEvent result = lambda.handleRequest(apiGatewayProxyInputEvent, null);
		assertNotNull(result);
		assertEquals(200, result.getStatusCode().intValue());
		assertFalse(result.getIsBase64Encoded());
		
		Jsonb jsonb = JsonbBuilder.create();
		LambdaBodyOut lambdaBodyOut = jsonb.fromJson(result.getBody(), LambdaBodyOut.class);
		assertEquals(12, lambdaBodyOut.result);
	}

	@Test
	public void testMult() {
		APIGatewayProxyRequestEvent apiGatewayProxyInputEvent = new APIGatewayProxyRequestEvent();
		apiGatewayProxyInputEvent
			.withIsBase64Encoded(false)
			.withBody("{ \"arguments\": [2, 4, 6] }")
			.withHttpMethod("POST")
			.withResource("/mult");
		MicrolamJava19Demo lambda = new MicrolamJava19Demo();
		APIGatewayProxyResponseEvent result =  lambda.handleRequest(apiGatewayProxyInputEvent, null);
		assertNotNull(result);
		assertEquals(200, result.getStatusCode().intValue());
		assertEquals(false, result.getIsBase64Encoded());
		
		Jsonb jsonb = JsonbBuilder.create();
		LambdaBodyOut lambdaBodyOut = jsonb.fromJson(result.getBody(), LambdaBodyOut.class);
		assertEquals(48, lambdaBodyOut.result);
	}
	
}
