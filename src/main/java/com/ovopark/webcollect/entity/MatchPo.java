package com.ovopark.webcollect.entity;
/**
 * 从属表
 */
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName(value = "c_match")
public class MatchPo implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /** 赛事*/
    private String saiShi ;
    /** 赛事时间 */
    private Date matchData ;
    /** 队伍1*/
    private String team1  ;
    /** 队伍1分数*/
    private Integer teamScore1 ;
    /** 队伍2分数*/
    private Integer teamScore2 ;
    /** 队伍2*/
    private String team2;
    /** 胜负*/
    private String winOrLose ;
    /** 初盘1*/
    private Double startOffer1;
    /** 初盘2*/
    private Double startOffer2;
    /** 初盘3*/
    private Double startOffer3;
    /** 终盘1*/
    private Double finalOffer1 ;
    /** 终盘2*/
    private Double finalOffer2 ;
    /** 终盘3*/
    private Double finalOffer3 ;
    /** 主表外键*/
    private Long main_id;

    public Long getMain_id() {
        return main_id;
    }

    public void setMain_id(Long main_id) {
        this.main_id = main_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaiShi() {
        return saiShi;
    }

    public void setSaiShi(String saiShi) {
        this.saiShi = saiShi;
    }

    public Date getMatchData() {
        return matchData;
    }

    public void setMatchData(Date matchData) {
        this.matchData = matchData;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public Integer getTeamScore1() {
        return teamScore1;
    }

    public void setTeamScore1(Integer teamScore1) {
        this.teamScore1 = teamScore1;
    }

    public Integer getTeamScore2() {
        return teamScore2;
    }

    public void setTeamScore2(Integer teamScore2) {
        this.teamScore2 = teamScore2;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getWinOrLose() {
        return winOrLose;
    }

    public void setWinOrLose(String winOrLose) {
        this.winOrLose = winOrLose;
    }

    public Double getStartOffer1() {
        return startOffer1;
    }

    public void setStartOffer1(Double startOffer1) {
        this.startOffer1 = startOffer1;
    }

    public Double getStartOffer2() {
        return startOffer2;
    }

    public void setStartOffer2(Double startOffer2) {
        this.startOffer2 = startOffer2;
    }

    public Double getStartOffer3() {
        return startOffer3;
    }

    public void setStartOffer3(Double startOffer3) {
        this.startOffer3 = startOffer3;
    }

    public Double getFinalOffer1() {
        return finalOffer1;
    }

    public void setFinalOffer1(Double finalOffer1) {
        this.finalOffer1 = finalOffer1;
    }

    public Double getFinalOffer2() {
        return finalOffer2;
    }

    public void setFinalOffer2(Double finalOffer2) {
        this.finalOffer2 = finalOffer2;
    }

    public Double getFinalOffer3() {
        return finalOffer3;
    }

    public void setFinalOffer3(Double finalOffer3) {
        this.finalOffer3 = finalOffer3;
    }

    @Override
    public String toString() {
        return "MatchPo{" +
                "id=" + id +
                ", saiShi='" + saiShi + '\'' +
                ", matchData=" + matchData +
                ", team1='" + team1 + '\'' +
                ", teamScore1=" + teamScore1 +
                ", teamScore2=" + teamScore2 +
                ", team2='" + team2 + '\'' +
                ", winOrLose='" + winOrLose + '\'' +
                ", startOffer1=" + startOffer1 +
                ", startOffer2=" + startOffer2 +
                ", startOffer3=" + startOffer3 +
                ", finalOffer1=" + finalOffer1 +
                ", finalOffer2=" + finalOffer2 +
                ", finalOffer3=" + finalOffer3 +
                '}';
    }
}
