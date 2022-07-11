package springreactjunit.demo.model;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "Response")
public class XMLFileStructureDto {

    @JacksonXmlProperty(localName = "Header")
    private List<Object> header;

    @JacksonXmlProperty(localName = "Content")
    private List<Object> content;


}
