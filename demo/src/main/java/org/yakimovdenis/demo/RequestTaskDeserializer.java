package org.yakimovdenis.demo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.yakimovdenis.demo.model.IntObject;
import org.yakimovdenis.demo.model.RequestTask;
import org.yakimovdenis.demo.model.StringObject;

import java.io.IOException;

public class RequestTaskDeserializer extends StdDeserializer<RequestTask> {


    protected RequestTaskDeserializer() {
        super(RequestTask.class);
    }

    @Override
    public RequestTask deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode bodyNode = node.get("body");
        JsonNode targetNode = bodyNode.get("data");
        if (targetNode.isNumber()) {
            return new IntObject(targetNode.asInt());
        } else {
            return new StringObject(targetNode.asText());
        }
//        int id = (Integer) ((IntNode) node.get("id")).numberValue();
    }
}
