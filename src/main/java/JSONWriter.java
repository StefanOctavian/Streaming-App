import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;

public class JSONWriter {
    public void writeToStdout(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);

        try {
            OutputStream os = new ByteArrayOutputStream();
            mapper.writeValue(os, obj);
            System.out.println(os.toString());
        } catch (JsonGenerationException e) {
            System.out.println("Error generating JSON");
            e.printStackTrace();
        } catch (JsonMappingException e) {
            System.out.println("Error mapping JSON");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error writing JSON");
            e.printStackTrace();
        }
    }
}
