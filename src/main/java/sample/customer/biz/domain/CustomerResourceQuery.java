package sample.customer.biz.domain;

import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CustomerResourceQuery implements Serializable {

    @NonNull
    @Size(max=3)
    private String name;

    @NonNull
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private LocalDate birthday;

    public CustomerResourceQuery() {
    }

//    public CustomerResourceQuery(String name, LocalDate birthday) {
//        this.name = name;
//        this.birthday = birthday;
//    }

//    public CustomerResourceQuery() {
//    }
}
