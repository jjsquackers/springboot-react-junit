package springreactjunit.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;


public class JsonParser {

    public String getDataFromJsonFile(String path) {
        String text = null;
        try {
            File resource = new ClassPathResource(path).getFile();
            text = new String(Files.readAllBytes(resource.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;

    }

    public void parseJsonString() throws IOException {
        var om = new ObjectMapper();
        var json = getDataFromJsonFile("data/Orders.json");
        JsonNode jsonNode = om.readTree(json);
        List<Object> objectList = Arrays.asList(om.readValue(json, JsonNode.class));
        System.out.println("JSON STRING - genXMLTrial() " + jsonNode.toString());
        System.out.println("JSON STRING - genXMLTrial() " + objectList.toString());


    }
}
