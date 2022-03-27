package org.example.services;

import org.example.entities.Row;
import org.example.repositories.RowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HomeService {

    @Autowired
    private RowRepository rowRepository;

    public List<Row> getDuplicatedValues(String selectedColumn) {
        return rowRepository.findDuplicatedDataInColumn(selectedColumn);
    }

    public List<Row> getUniqueValues(String selectedColumn) {
        return rowRepository.findUniqueDataInColumn(selectedColumn);
    }

}
