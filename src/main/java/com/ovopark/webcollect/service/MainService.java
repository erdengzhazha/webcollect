package com.ovopark.webcollect.service;

import com.ovopark.webcollect.entity.MainPo;
import org.springframework.stereotype.Service;
import sun.applet.Main;

/**
 * 主 表服务
 */
public interface MainService {

  /**
   * 保存数据
   * @param mainPo
   * @return
   */
  int save(MainPo mainPo);

  /**
   * 查询所有数据
   * @return
   */
  MainPo getAll();
}
