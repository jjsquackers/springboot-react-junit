package springreactjunit.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    String orderNo;
    LocalDate date;
    String custid;
    List<Customer> customerList;

}
