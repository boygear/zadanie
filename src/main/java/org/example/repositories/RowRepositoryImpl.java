package org.example.repositories;

import org.example.entities.Row;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class RowRepositoryImpl implements RowRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Row> findDuplicatedDataInColumn(String columnName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
        Root<Row> row = query.from(Row.class);
        Path<String> columnPath = row.get(columnName);
        query.select(columnPath).groupBy(columnPath).having(criteriaBuilder.greaterThan(criteriaBuilder.count(row), 1L));
        List<String> duplicatedValues = entityManager.createQuery(query).getResultList();

        return findRows(columnName, duplicatedValues);
    }

    @Override
    public List<Row> findUniqueDataInColumn(String columnName) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
        Root<Row> row = query.from(Row.class);
        Path<String> columnPath = row.get(columnName);
        query.select(columnPath).groupBy(columnPath).having(criteriaBuilder.equal(criteriaBuilder.count(row), 1L));
        List<String> uniqueValues = entityManager.createQuery(query).getResultList();

        return findRows(columnName, uniqueValues);
    }

    private List<Row> findRows(String columnName, List<String> values) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Row> rowQuery = criteriaBuilder.createQuery(Row.class);
        Root<Row> row = rowQuery.from(Row.class);

        if (columnName.equals("kolumna4")) {
            Path<Integer> columnPath = row.get(columnName);
            CriteriaBuilder.In<Integer> inClause = criteriaBuilder.in(columnPath);
            for (String value : values) {
                inClause.value(Integer.parseInt(value));
            }
            rowQuery.select(row).where(inClause);
        } else {
            Path<String> columnPath = row.get(columnName);
            CriteriaBuilder.In<String> inClause = criteriaBuilder.in(columnPath);
            for (String value : values) {
                inClause.value(value);
            }
            rowQuery.select(row).where(inClause);
        }

        return entityManager.createQuery(rowQuery).getResultList();
    }
}
