package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class ExtentLoggingFilter implements Filter {

	private static final ObjectMapper mapper = new ObjectMapper();

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {

		// Log Request Metadata
		StringBuilder requestMeta = new StringBuilder();
		requestMeta.append("Method: ").append(requestSpec.getMethod()).append("\n");
		requestMeta.append("URI: ").append(requestSpec.getURI()).append("\n");

		if (requestSpec.getHeaders() != null) {
			requestMeta.append("Headers:\n").append(requestSpec.getHeaders().toString()).append("\n");
		}
		ExtentLogger.info("📤 Request Metadata:\n" + requestMeta);

		// Log Request Body (pretty if JSON)
		if (requestSpec.getBody() != null) {
			String bodyAsString = extractBodyAsString(requestSpec.getBody());
			if (isJson(bodyAsString)) {
				ExtentLogger.request(prettyJson(bodyAsString));
			} else {
				ExtentLogger.request(bodyAsString);
			}
		} else if (!requestSpec.getFormParams().isEmpty()) {
			// Capture form params as body
			ExtentLogger.request("Form Params: " + requestSpec.getFormParams().toString());
		} else if (!requestSpec.getRequestParams().isEmpty()) {
			// Capture query params (sometimes used instead of body)
			ExtentLogger.request("Query Params: " + requestSpec.getRequestParams().toString());
		}

		// Execute request
		Response response = ctx.next(requestSpec, responseSpec);

		// Log Response Status
		ExtentLogger.info("📥 Response Status: " + response.getStatusCode());

		// Log Response Body (pretty if JSON)
		String responseBody = response.asString();
		if (isJson(responseBody)) {
			ExtentLogger.response(prettyJson(responseBody));
		} else {
			ExtentLogger.response(responseBody);
		}

		return response;
	}

	// Helper to safely convert body
	private String extractBodyAsString(Object body) {
		if (body instanceof String) {
			return (String) body;
		} else if (body instanceof byte[]) {
			return new String((byte[]) body);
		} else if (body instanceof char[]) {
			return new String((char[]) body);
		} else {
			return body.toString();
		}
	}

	// Helper to check if string is JSON
	private boolean isJson(String str) {
		if (str == null)
			return false;
		str = str.trim();
		return (str.startsWith("{") && str.endsWith("}")) || (str.startsWith("[") && str.endsWith("]"));
	}

	// Helper to pretty print JSON
	private String prettyJson(String json) {
		try {
			Object parsed = mapper.readValue(json, Object.class);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(parsed);
		} catch (Exception e) {
			return json; // fallback if parsing fails
		}
	}
}
