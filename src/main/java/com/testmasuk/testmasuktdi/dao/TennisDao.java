package com.testmasuk.testmasuktdi.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.testmasuk.testmasuktdi.dto.TennisDTO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class TennisDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<TennisDTO.DataTennis> findAll() throws DataAccessException {

        String query = "select \n" +
                "t.id, t.n_player_a, t.n_player_b, t.skor_player_a , t.skor_player_b ,\n" +
                "ts.player_a as scoreSementaraPlayerA, ts.player_b as scoreSementaraPlayerB,\n" +
                "ts1.player_a as Set1A, ts1.player_b as Set1B,\n" +
                "ts1.player_a as Set2A, ts2.player_b as Set2B,\n" +
                "ts1.player_a as Set3A, ts3.player_b as Set3B\n" +
                "from tennis t \n" +
                "left join tennis_score ts on t.id_skor = ts.id\n" +
                "left join tennis_set_1 ts1 on t.id_set_1 = ts1.id \n" +
                "left join tennis_set_2 ts2 on t.id_set_2 = ts2.id \n" +
                "left join tennis_set_3 ts3 on t.id_set_3 = ts3.id order by t.id desc";
        return this.jdbcTemplate.query(query, new HashMap<>(), new RowMapper<TennisDTO.DataTennis>() {
            @Override
            public TennisDTO.DataTennis mapRow(ResultSet resultSet, int i) throws SQLException {
                TennisDTO.DataTennis tennis = new TennisDTO.DataTennis();
                tennis.setId(resultSet.getBigDecimal("id"));
                tennis.setN_player_a(resultSet.getString("n_player_a"));
                tennis.setN_player_b(resultSet.getString("n_player_b"));
                tennis.setSkor_player_a(resultSet.getInt("skor_player_a"));
                tennis.setSkor_player_b(resultSet.getInt("skor_player_b"));
                tennis.setScoreSementaraPlayerA(resultSet.getInt("scoreSementaraPlayerA"));
                tennis.setScoreSementaraPlayerB(resultSet.getInt("scoreSementaraPlayerB"));
                tennis.setSet1A(resultSet.getInt("Set1A"));
                tennis.setSet1B(resultSet.getInt("Set1B"));
                tennis.setSet2A(resultSet.getInt("Set2A"));
                tennis.setSet2B(resultSet.getInt("Set2B"));
                tennis.setSet3A(resultSet.getInt("Set3A"));
                tennis.setSet3B(resultSet.getInt("Set3B"));
                return tennis;
            }
        });
    }

    public Optional<TennisDTO.DataTennis> findById(BigDecimal id) throws DataAccessException {

        String query = "select \n" +
                "t.id, t.n_player_a, t.n_player_b, t.skor_player_a , t.skor_player_b ,\n" +
                "ts.player_a as scoreSementaraPlayerA, ts.player_b as scoreSementaraPlayerB,\n" +
                "ts1.player_a as Set1A, ts1.player_b as Set1B,\n" +
                "ts1.player_a as Set2A, ts2.player_b as Set2B,\n" +
                "ts1.player_a as Set3A, ts3.player_b as Set3B\n" +
                "from tennis t \n" +
                "left join tennis_score ts on t.id_skor = ts.id\n" +
                "left join tennis_set_1 ts1 on t.id_set_1 = ts1.id \n" +
                "left join tennis_set_2 ts2 on t.id_set_2 = ts2.id \n" +
                "left join tennis_set_3 ts3 on t.id_set_3 = ts3.id where t.id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        try {
            TennisDTO.DataTennis response =  this.jdbcTemplate.queryForObject(query, map, new BeanPropertyRowMapper<>(TennisDTO.DataTennis.class));
            return Optional.of(response);
        } catch (EmptyResultDataAccessException erdea) {
            if (log.isDebugEnabled())
                log.error("can't find id : {}", id, erdea);
            return Optional.empty();
        }
    }

    public TennisDTO.NilaiAwal nilaiAwal(TennisDTO.NilaiAwal value) throws DataAccessException {
        String query = "WITH set1 AS (\n" +
                "   \tINSERT INTO tennis_set_1\n" +
                "\t\t(player_a, player_b)\n" +
                "\tvalues\n" +
                "\t\t(0, 0)\n" +
                "  \tRETURNING id),\n" +
                "set2 AS (\n" +
                "  \tINSERT INTO tennis_set_2\n" +
                "\t\t(player_a, player_b)\n" +
                "\tvalues\n" +
                "\t\t(0, 0)\n" +
                "  \tRETURNING id),\n" +
                "set3 AS (\n" +
                "  \tINSERT INTO tennis_set_3\n" +
                "\t\t(player_a, player_b)\n" +
                "\tvalues\n" +
                "\t\t(0, 0)\n" +
                "  \tRETURNING id)\n" +
                "INSERT INTO tennis\n" +
                "(id_set_1, id_set_2, id_set_3, id_skor, n_player_a, n_player_b, skor_player_a, skor_player_b)\n" +
                "VALUES((select id from set1), (select id from set2), (select id from set3), 1, :playerA, :playerB, 0, 0);";

        MapSqlParameterSource map = new MapSqlParameterSource();
        KeyHolder keys = new GeneratedKeyHolder();
        map.addValue("playerA", value.getN_player_a());
        map.addValue("playerB", value.getN_player_b());
        this.jdbcTemplate.update(query, map, keys);
        value.setId((BigDecimal) keys.getKeys().get("id"));
        return value;
    }

    public TennisDTO.updateSkorSementara updateSkorSementara(TennisDTO.updateSkorSementara value) throws DataAccessException {
        String query = "UPDATE tennis_score SET id = 1, player_a = :playerA, player_b = :playerB where id=1";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("playerA", value.getPlayerA());
        map.addValue("playerB", value.getPlayerB());
        this.jdbcTemplate.update(query, map);
        return value;
    }
    
    public void resetSkorSementara() throws DataAccessException {
        String query = "UPDATE tennis_score SET id = 1, player_a = 0, player_b = 0 where id=1";
        this.jdbcTemplate.update(query, new HashMap<>());
    }
    
    public TennisDTO.updateSet updateSkorSet1(TennisDTO.updateSet value) throws DataAccessException {
        String query = "UPDATE tennis_set_1 SET player_a = :playerA, player_b = :playerB where id = :id;";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", value.getId());
        map.addValue("playerA", value.getPlayerA());
        map.addValue("playerB", value.getPlayerB());
        this.jdbcTemplate.update(query, map);
        return value;
    }

    public TennisDTO.updateSet updateSkorSet2(TennisDTO.updateSet value) throws DataAccessException {
        String query = "UPDATE tennis_set_2 SET player_a = :playerA, player_b = :playerB where id = :id;";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", value.getId());
        map.addValue("playerA", value.getPlayerA());
        map.addValue("playerB", value.getPlayerB());
        this.jdbcTemplate.update(query, map);
        return value;
    }

    public TennisDTO.updateSet updateSkorSet3(TennisDTO.updateSet value) throws DataAccessException {
        String query = "UPDATE tennis_set_3 SET player_a = :playerA, player_b = :playerB where id = :id;";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", value.getId());
        map.addValue("playerA", value.getPlayerA());
        map.addValue("playerB", value.getPlayerB());
        this.jdbcTemplate.update(query, map);
        return value;
    }

    public TennisDTO.updateSkor updateSkor(TennisDTO.updateSkor value) throws DataAccessException {
        String query = "UPDATE tennis SET skor_player_a= :skor_player_a, skor_player_b= :skor_player_b where id = :id;";
        
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", value.getId());
        map.addValue("skor_player_a", value.getSkor_player_a());
        map.addValue("skor_player_b", value.getSkor_player_b());
        this.jdbcTemplate.update(query, map);
        return value;
    }
}
