package org.example.repositories;

import org.example.entities.Row;

import java.util.List;

public interface RowRepository {
    List<Row> findDuplicatedDataInColumn(String columnName);

    List<Row> findUniqueDataInColumn(String columnName);
}
