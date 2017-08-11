package webservice.utils;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
	private static final ObjectMapper mapper = new ObjectMapper();

	public static ObjectMapper getObjectMapper() {
		return mapper;
	}

	public <T> T buildObject(String json, Class<T> valueType) {
		try {
			return mapper.readValue(json, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String buildJson(Object map) {
		try {
			return mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
