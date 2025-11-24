package com.academia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "institution_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionConfig {
    @Id
    private String id;

    private String institutionName;
    private String institutionLogo;
    private String academicYear;
    private String address;
    private String phone;
    private String email;
}
