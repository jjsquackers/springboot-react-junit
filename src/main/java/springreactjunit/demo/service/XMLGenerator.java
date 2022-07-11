package springreactjunit.demo.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import springreactjunit.demo.controller.MainController;
import springreactjunit.demo.model.Orders;
import springreactjunit.demo.model.XMLFileStructureDto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class XMLGenerator {

//    private final Validation validation;

    @Value("${XML_DATE_FORMAT:dd/MM/yyyy}")
    private String xmlDateFormat;

    private String xmlPath = "C:\\Users\\82003633\\Documents\\test";


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

    public ResponseEntity<JsonNode> generateXML() throws IOException {
        var om = new ObjectMapper();
        var json = getDataFromJson("data/Sample.json");
        JsonNode jsonNode = om.readTree(json);
        List<Object> objectList = Arrays.asList(om.readValue(json, JsonNode.class));
        System.out.println("JSON STRING - genXMLTrial() " + jsonNode.toString());
        System.out.println("JSON STRING - genXMLTrial() " + objectList.toString());
        generateXML("Output", objectList);


        return new ResponseEntity<JsonNode>(jsonNode, HttpStatus.OK);
    }

    public String generateXML(String filename, List<Object> objectList) throws JsonGenerationException, JsonMappingException, IOException {
        String stringXML = "";
        // Validation
        if (filename.isBlank() || filename == null) {
            return "Filename is empty";
        }
        if (objectList.isEmpty() || objectList == null) {
            return "ObjectList is empty";
        }
        try {
            XMLFileStructureDto xmlFile = new XMLFileStructureDto();
//            MainController orders = new MainController();
            ;

            List<Object> header = new ArrayList<>();
            header.add("trial");
            header.add("AWESOME!!!!!!");
            header.add("HEHEHEHEH!!!!!!");
            JSONArray s = new JSONArray(objectList.toString());
            JSONObject tempJsonObject;
            for (int i = 0; i < s.length(); i++) {
                tempJsonObject = s.getJSONObject(i);
                System.out.println(tempJsonObject);
//                System.out.println(tempJsonObject.getString("custid"));
            }
            xmlFile.setHeader(header);
            xmlFile.setContent(objectList);

            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
//            xmlMapper.setDateFormat(new SimpleDateFormat(xmlDateFormat));
            xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            xmlMapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
            xmlMapper.registerModule(new JavaTimeModule());
            xmlMapper.writeValue(new File(getFilePath(filename)), xmlFile);

            stringXML = xmlMapper.writeValueAsString(xmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringXML;
    }

    private String getFilePath(String filename) {
        StringBuilder builder = new StringBuilder();
        builder.append(xmlPath);
        builder.append(File.separator);
        builder.append(DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));

        // Check Directory
        File directory = new File(builder.toString());
        if (!directory.exists()) {
            directory.mkdirs();
        }

        builder.append(File.separator);
        builder.append(filename);
        builder.append("_");
        builder.append(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now()));
        builder.append(".xml");


        return builder.toString();
    }


}
