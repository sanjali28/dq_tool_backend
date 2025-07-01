package com.app.comparetool.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DatabaseDetails {

    private String env;
    private String databaseName;
    private String tableName;
    private String[] columnNames;

}
