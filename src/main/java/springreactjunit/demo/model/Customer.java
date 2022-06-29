package springreactjunit.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    String custid;
    String fname;
    String lname;
    String address;
    String city;
    String state;
    String zip;

}