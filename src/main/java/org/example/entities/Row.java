package org.example.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="tabela_testowa")
public class Row {

    @Id
    private Integer id;
    private String kolumna1;
    private String kolumna2;
    private String kolumna3;
    private String kolumna4;
}
