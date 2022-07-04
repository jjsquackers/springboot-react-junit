package springreactjunit.demo.model;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "JUSTINE")
public class XMLFileStructureDto {

    @JacksonXmlProperty(localName = "HeaderXXXXX")
    private List<Object> header;

    @JacksonXmlProperty(localName = "ContentXXX")
    private List<Object> orders;


}
