package springreactjunit.demo.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import springreactjunit.demo.model.XMLFileStructureDto;

import javax.naming.event.ObjectChangeListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@Service
public class XMLGenerator {

    private final Validation validation;

    @Value("${XML_DATE_FORMAT:dd/MM/yyyy}")
    private String xmlDateFormat;

    private String xmlPath = "C:\\users\\82003633\\Documents\\test";

    public XMLGenerator(Validation validation) {
        this.validation = validation;
    }


    public void initDependecies() {
        JsonNode jsonNode;
        ObjectMapper objectMapper;
        JSONArray jsonArray;
        JSONObject jsonObject;
        XmlMapper xmlMapper;
        ToXmlGenerator toXmlGenerator;
        String nonEmpty = JsonInclude.Include.NON_EMPTY.toString();
        JavaTimeModule javaTimeModule;

    }

    public String getDataFromJson(String path) {
        String text = null;
        try {
            File resource = new ClassPathResource(path).getFile();
            text = new String(Files.readAllBytes(resource.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;

    }

    public ResponseEntity<JsonNode> genXMLTrial() throws IOException {
        var om = new ObjectMapper();
        var json = getDataFromJson("data/Orders.json");
        JsonNode jsonNode = om.readTree(json);
        List<Object> objectList = Arrays.asList(om.readValue(json, JsonNode.class));
        System.out.println("JSON STRING - genXMLTrial() " + jsonNode.toString());
        System.out.println("JSON STRING - genXMLTrial() " + objectList.toString());
        genXMLTrial("Output", objectList);


        return new ResponseEntity<JsonNode>(jsonNode, HttpStatus.OK);
    }

    public String genXMLTrial(String filename, List<Object> objectList) throws JsonGenerationException, JsonMappingException, IOException {
        String stringXML = "";
        // Validation
        if (filename.isBlank() || filename == null) {
            return "Filename is empty";
        }
        if (objectList.isEmpty() || objectList == null) {
            return "ObjectList is empty";
        }
        try{
            XMLFileStructureDto xmlFile = new XMLFileStructureDto();

        }catch(Exception e){
            e.printStackTrace();
        }
        return stringXML;
    }

}
