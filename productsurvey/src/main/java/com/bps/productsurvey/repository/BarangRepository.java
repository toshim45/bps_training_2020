package com.bps.productsurvey.repository;

import com.bps.productsurvey.model.Barang;
import com.bps.productsurvey.model.BarangFilter;
import com.bps.productsurvey.model.PageFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BarangRepository {
    private static final Logger logger = LoggerFactory.getLogger(BarangRepository.class);

    static final String QueryAll = "SELECT b.id, b.nama, b.created_at, b.updated_at FROM barang b";
    static final String QueryCountAll = "SELECT COUNT(1) FROM barang b";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SurveyRepository surveyRepository;

    public List<Barang> findAll(BarangFilter filter){
        filter.composeWhereQuery();

        return jdbcTemplate.query(QueryAll+filter.getWhereClause(),filter.getWhereParameters(), (resultSet, rowNum) -> new Barang(
                resultSet.getInt("id"),
                resultSet.getString("nama"),
                resultSet.getDate("created_at"),
                resultSet.getDate("updated_at")
        ));
    }

    public void insert(Barang barang) {
        String query = "INSERT INTO barang (nama) VALUES (?)";
        int rows = jdbcTemplate.update(query, barang.getNama());
        logger.debug("store barangs: " + barang.getNama()+ " > " + rows + " affected");
    }

    @Transactional
    public void delete(int id) {
        String query = "DELETE FROM barang WHERE id = ?";
        surveyRepository.deleteByBarangId(id);
        int rows = jdbcTemplate.update(query, id);
        logger.debug("delete barang: " + id + " > " + rows + " affected");
    }

    public Page<Barang> search(BarangFilter barangFilter, PageFilter pageFilter) {
        barangFilter.composeWhereQuery();

        List<Barang> list = jdbcTemplate.query(
                QueryAll+barangFilter.getWhereClause()+pageFilter.getClause(),
                barangFilter.getWhereParameters(), (resultSet, rowNum) -> new Barang(
                resultSet.getInt("id"),
                resultSet.getString("nama"),
                resultSet.getDate("created_at"),
                resultSet.getDate("updated_at")
        ));

        int total = jdbcTemplate.queryForObject(
                QueryCountAll+barangFilter.getWhereClause(),
                barangFilter.getWhereParameters(), (resultSet, rowNum) -> resultSet.getInt(1));

        return new PageImpl<Barang>(list, pageFilter.getPageable(), total);
    }
}
