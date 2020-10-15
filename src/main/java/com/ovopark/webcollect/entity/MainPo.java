package com.ovopark.webcollect.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 主 记录数据
 */
@TableName(value = "c_main")
public class MainPo implements Serializable {

  private static final long serialVersionUID = 1L;
  @TableId(value = "id",type = IdType.AUTO)
  private Long id;
  private String companyName;
  private Double start1;
  private Double start2;
  private Double start3;
  private Integer totalNum;
  private Integer mainWin;
  private Integer mainFlat;
  private Integer mainLose;
  private Double winRate;
  private Double flatRate;
  private Double loseRate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public Double getStart1() {
    return start1;
  }

  public void setStart1(Double start1) {
    this.start1 = start1;
  }

  public Double getStart2() {
    return start2;
  }

  public void setStart2(Double start2) {
    this.start2 = start2;
  }

  public Double getStart3() {
    return start3;
  }

  public void setStart3(Double start3) {
    this.start3 = start3;
  }

  public Integer getTotalNum() {
    return totalNum;
  }

  public void setTotalNum(Integer totalNum) {
    this.totalNum = totalNum;
  }

  public Integer getMainWin() {
    return mainWin;
  }

  public void setMainWin(Integer mainWin) {
    this.mainWin = mainWin;
  }

  public Integer getMainFlat() {
    return mainFlat;
  }

  public void setMainFlat(Integer mainFlat) {
    this.mainFlat = mainFlat;
  }

  public Integer getMainLose() {
    return mainLose;
  }

  public void setMainLose(Integer mainLose) {
    this.mainLose = mainLose;
  }

  public Double getWinRate() {
    return winRate;
  }

  public void setWinRate(Double winRate) {
    this.winRate = winRate;
  }

  public Double getFlatRate() {
    return flatRate;
  }

  public void setFlatRate(Double flatRate) {
    this.flatRate = flatRate;
  }

  public Double getLoseRate() {
    return loseRate;
  }

  public void setLoseRate(Double loseRate) {
    this.loseRate = loseRate;
  }

  @Override
  public String toString() {
    return "MainPo{" +
      "id=" + id +
      ", companyName='" + companyName + '\'' +
      ", start1=" + start1 +
      ", start2=" + start2 +
      ", start3=" + start3 +
      ", totalNum=" + totalNum +
      ", mainWin=" + mainWin +
      ", mainFlat=" + mainFlat +
      ", mainLose=" + mainLose +
      ", winRate=" + winRate +
      ", flatRate=" + flatRate +
      ", loseRate=" + loseRate +
      '}';
  }
}
