package sample.customer.biz.domain;

import lombok.Data;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

/**
 * Created by haruna on 2018/04/30.
 */
@Data
//Jaxb2RootElementHttpMessageConverterの時は↓↓↓を有効にする必要あり。
//@XmlRootElement
public class Customer implements java.io.Serializable{

    public Customer() {}

    public Customer(String name, String emailAddress, LocalDate birthday, Integer favariteNumber) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.birthday = birthday;
        this.favoriteNumber = favariteNumber;
    }

    private int id;

    @NotNull
    @Size(max=20)
    private String name;

    @NotNull
    @Pattern(regexp = ".+@.+")
    private String emailAddress;

    @NotNull
//    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate birthday;

    @Max(9)
    @Min(0)
    private Integer favoriteNumber;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmailAddress() {
//        return emailAddress;
//    }
//
//    public void setEmailAddress(String emailAddress) {
//        this.emailAddress = emailAddress;
//    }
//
//    public LocalDate getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthday(LocalDate birthday) {
//        this.birthday = birthday;
//    }
//
//    public Integer getFavoriteNumber() {
//        return favoriteNumber;
//    }
//
//    public void setFavoriteNumber(Integer favariteNumber) {
//        this.favoriteNumber = favariteNumber;
//    }


    @AssertFalse(message = "{errors.emailAddress.ng}")
    public boolean isNgEmail(){
        if(emailAddress == null) return false;
        return emailAddress.matches(".*@ng.foo.baz$");
    }

}

