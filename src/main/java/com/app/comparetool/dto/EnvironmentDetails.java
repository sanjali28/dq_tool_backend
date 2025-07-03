package com.app.comparetool.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EnvironmentDetails {

    private String env;
    private String databaseName;
    private String tableName;

}
