package com.ovopark.webcollect.service.imp;

import com.ovopark.webcollect.entity.MainPo;
import com.ovopark.webcollect.mapper.MainMapper;
import com.ovopark.webcollect.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService {

  @Autowired
  private MainMapper mainMapper;
  @Override
  public int save(MainPo mainPo) {
    int insert = mainMapper.insert(mainPo);
    return insert;
  }

  @Override
  public MainPo getAll() {
    return null;
  }
}
