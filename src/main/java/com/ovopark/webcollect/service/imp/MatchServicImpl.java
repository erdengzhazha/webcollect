package com.ovopark.webcollect.service.imp;

import com.ovopark.webcollect.entity.MatchPo;
import com.ovopark.webcollect.mapper.MatchMapper;
import com.ovopark.webcollect.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchServicImpl implements MatchService {
    @Autowired
    MatchMapper matchMapper;

    @Override
    public int save(MatchPo matchPo) {
        int insert = matchMapper.insert(matchPo);
        return insert;
    }
}
