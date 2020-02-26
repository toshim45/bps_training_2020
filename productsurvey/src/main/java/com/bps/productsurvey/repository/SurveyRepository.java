package com.bps.productsurvey.repository;

import com.bps.productsurvey.model.Survey;
import com.bps.productsurvey.model.SurveyModus;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SurveyRepository {
    private static final Logger logger = LoggerFactory.getLogger(SurveyRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    static final String QueryQualityOccurs =
            "SELECT s.barang_id, s.kualitas, COUNT(1) AS occurs" +
                    " FROM survey s" +
                    " GROUP BY s.barang_id, s.kualitas";
    static final String QueryQualityModusSummary =
            "SELECT b.nama, info_summary.kualitas, modus_summary.modus_occurs FROM" +
                    " (" +
                    "SELECT summary.barang_id, MAX(summary.occurs) AS modus_occurs" +
                    " FROM " +
                    "(" + QueryQualityOccurs + ") summary" +
                    " GROUP BY summary.barang_id" +
                    ") modus_summary" +
                    " JOIN (" + QueryQualityOccurs + ") info_summary" +
                    " ON info_summary.barang_id = modus_summary.barang_id" +
                    " AND info_summary.occurs = modus_summary.modus_occurs" +
                    " JOIN barang b" +
                    " ON modus_summary.barang_id = b.id";

    public void insert(Survey survey){
        String query = "INSERT INTO `survey` (`kualitas`, `barang_id`, `surveyor_id`) VALUES(?,?,?)";
        int rows = jdbcTemplate.update(query, survey.getKualitas(), survey.getBarangId(), survey.getSurveyorId());
        try {
            logger.debug("store survey: " + survey.toJsonString() + " > " + rows + " affected");
        } catch (JsonProcessingException e) {
            logger.warn(e.getMessage());
        }
    }

    public void deleteByBarangId(int barangId){
        String query = "DELETE FROM survey WHERE barang_id = ?";
        int rows = jdbcTemplate.update(query, barangId);
        logger.debug("delete survey#barangId " + barangId + " > " + rows + " affected");
    }

    public List<SurveyModus> calculateModus() {
        return jdbcTemplate.query(QueryQualityModusSummary, (resultSet, rowNum)-> new SurveyModus(
                resultSet.getString("nama"),
                resultSet.getString("kualitas"),
                resultSet.getInt("modus_occurs")
        ));
    }
}
