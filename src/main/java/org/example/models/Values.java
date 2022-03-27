package org.example.models;

import lombok.Data;
import org.example.entities.Row;

import java.util.List;

@Data
public class Values {
    private List<Row> uniqueValues;
    private List<Row> duplicatedValues;
}
