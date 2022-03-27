package org.example.controllers;

import org.example.entities.Row;
import org.example.models.Columns;
import org.example.models.Values;
import org.example.services.HomeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
    @Mock
    private HomeService homeService;
    @InjectMocks
    private HomeController homeController;

    @Test
    public void getValues() {
        //given
        Columns columns = new Columns();
        columns.setSelectedColumn("kolumna1");

        List<Row> rowList1 = new ArrayList<>();
        List<Row> rowList2 = new ArrayList<>();
        Row row1 = new Row();
        row1.setId(1);
        row1.setKolumna1("wartość11");
        row1.setKolumna2("wartość12");
        row1.setKolumna3("wartość13");
        row1.setKolumna4("11");

        Row row2 = new Row();
        row2.setId(1);
        row2.setKolumna1("wartość21");
        row2.setKolumna2("wartość22");
        row2.setKolumna3("wartość23");
        row2.setKolumna4("21");

        rowList1.add(row1);
        rowList1.add(row2);
        rowList2.add(row1);
        rowList2.add(row2);

        Mockito.when(homeService.getUniqueValues(columns.getSelectedColumn())).thenReturn(rowList1);
        Mockito.when(homeService.getDuplicatedValues(columns.getSelectedColumn())).thenReturn(rowList2);

        //when
        Values values = homeController.getValues(columns);

        //then
        assertEquals(rowList1, values.getUniqueValues());
        assertEquals(rowList2, values.getDuplicatedValues());

    }
}