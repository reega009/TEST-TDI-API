package com.testmasuk.testmasuktdi.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.testmasuk.testmasuktdi.dao.TennisDao;
import com.testmasuk.testmasuktdi.dto.TennisDTO;

@Service
@Transactional
public class TennisService {
    
    @Autowired
    private TennisDao dao;

    public List<TennisDTO.DataTennis> findAll() throws DataAccessException{
        return dao.findAll();
    }

    public Optional<TennisDTO.DataTennis> findById(BigDecimal id) throws DataAccessException{
        return dao.findById(id);
    }

    public TennisDTO.NilaiAwal nilaiAwal(TennisDTO.NilaiAwal value) throws DataAccessException{
        return dao.nilaiAwal(value);
    }
    
    public TennisDTO.updateSkorSementara updateSkorSementara(TennisDTO.updateSkorSementara value) throws DataAccessException{
        return dao.updateSkorSementara(value);
    }
    
    public void resetSkorSementara() throws DataAccessException{
        dao.resetSkorSementara();
    }

    public TennisDTO.updateSet updateSkorSet1(TennisDTO.updateSet value) throws DataAccessException{
        return dao.updateSkorSet1(value);
    }
    public TennisDTO.updateSet updateSkorSet2(TennisDTO.updateSet value) throws DataAccessException{
        return dao.updateSkorSet2(value);
    }
    public TennisDTO.updateSet updateSkorSet3(TennisDTO.updateSet value) throws DataAccessException{
        return dao.updateSkorSet3(value);
    }

    public TennisDTO.updateSkor updateSkor(TennisDTO.updateSkor value) throws DataAccessException{
        return dao.updateSkor(value);
    }

}
