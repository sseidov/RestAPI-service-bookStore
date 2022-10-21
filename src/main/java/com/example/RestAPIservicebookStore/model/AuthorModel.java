package com.example.RestAPIservicebookStore.model;

import com.example.RestAPIservicebookStore.entity.Author;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
@Getter
@Setter
public class AuthorModel {

    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    public static AuthorModel toModel(Author author){
        AuthorModel authorModel = new AuthorModel();
        authorModel.setFirstName(author.getFirstName());
        authorModel.setLastName(author.getLastName());
        authorModel.setDateOfBirth(author.getDateOfBirth());
        return authorModel;
    }
}
