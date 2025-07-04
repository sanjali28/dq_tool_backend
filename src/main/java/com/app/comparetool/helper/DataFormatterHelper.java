package com.app.comparetool.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataFormatterHelper {

    private static final Logger log = LogManager.getLogger(DataFormatterHelper.class);

    public Map<String,String> formatDataToPipeSeparatedString(List<String> primaryKeyForColumn, String[] listOfAllColumns, List<Map<String, Object>> dataForTable){

        try{
            Map<String,String> convertedMap = new HashMap<>();

            if(primaryKeyForColumn.size() == 1){
                log.info("Single primary key found");

                String primaryKey = primaryKeyForColumn.get(0);

                for(Map<String,Object> row : dataForTable){

                    StringBuilder columnData = new StringBuilder();
                    for(String column : listOfAllColumns){
                        columnData.append(row.get(column)).append("|");
                    }

                    convertedMap.put(row.get(primaryKey).toString(),columnData.toString());
                }
            }
            else {
                log.info("Composite primary key found");
            }


            return convertedMap;
        } catch (Exception e){
            throw new RuntimeException("Error while formatting the data",e);
        }

    }
}
