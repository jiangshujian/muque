package ${packageName}.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

public class CustomObjectMapper extends ObjectMapper {

	public CustomObjectMapper() {
		CustomSerializerFactory factory = new CustomSerializerFactory();
		factory.addGenericMapping(Date.class, new SimpleDateJsonSerializer());
		this.setSerializationInclusion(Inclusion.NON_NULL);// 不输出null
		this.setSerializerFactory(factory);
	}

	private class SimpleDateJsonSerializer extends JsonSerializer<Date> {

		@Override
		public void serialize(Date value, JsonGenerator jgen,
				SerializerProvider provider) throws IOException,
				JsonProcessingException {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jgen.writeString(sdf.format(value));

		}
	}

}
