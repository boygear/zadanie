package org.example.services;

import org.example.entities.Row;
import org.example.repositories.RowRepository;
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
public class HomeServiceTest {

    @Mock
    private RowRepository rowRepository;
    @InjectMocks
    private HomeService homeService;

    private List<Row> getRowList() {
        List<Row> rowList = new ArrayList<>();
        Row row1 = new Row();
        Row row2 = new Row();

        row1.setId(1);
        row1.setKolumna1("wartość11");
        row1.setKolumna2("wartość12");
        row1.setKolumna3("wartość13");
        row1.setKolumna4("11");

        row2.setId(1);
        row2.setKolumna1("wartość21");
        row2.setKolumna2("wartość22");
        row2.setKolumna3("wartość23");
        row2.setKolumna4("21");

        rowList.add(row1);
        rowList.add(row2);
        return rowList;
    }

    @Test
    public void getDuplicatedValues() {
        //given
        List<Row> rowList = getRowList();
        Mockito.when(rowRepository.findDuplicatedDataInColumn("kolumna1")).thenReturn(rowList);
        //when
        List<Row> result = homeService.getDuplicatedValues("kolumna1");
        //then
        Mockito.verify(rowRepository).findDuplicatedDataInColumn("kolumna1");
        assertEquals(result, rowList);
    }

    @Test
    public void getUniqueValues() {
        //given
        List<Row> rowList = getRowList();
        Mockito.when(rowRepository.findUniqueDataInColumn("kolumna1")).thenReturn(rowList);
        //when
        List<Row> result = homeService.getUniqueValues("kolumna1");
        //then
        Mockito.verify(rowRepository).findUniqueDataInColumn("kolumna1");
        assertEquals(result, rowList);
    }
}