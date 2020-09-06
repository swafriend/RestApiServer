package sample.customer.biz.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class ApiError implements Serializable {

    private String message;

    @JsonProperty("documentation_url")
    private String documentationUrl;

    @Data
    private static class Detail implements Serializable {
        private final String target;
        private final String message;

        public Detail(String target, String message) {
            this.target = target;
            this.message = message;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<Detail> details = new ArrayList<>();

    public void addDetail(String target, String message) {
        details.add(new Detail(target, message));
    }

    public List<Detail> getDetails() {
        return details;
    }





}
