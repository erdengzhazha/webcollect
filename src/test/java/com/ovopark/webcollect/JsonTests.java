package com.ovopark.webcollect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ovopark.webcollect.entity.MatchPo;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class JsonTests {

  @Test
  void contextLoads() throws IOException {

    Map<String, String> winOrLoseMap = new HashMap<>();
    winOrLoseMap.put("0","胜");
    winOrLoseMap.put("1","平");
    winOrLoseMap.put("2","负");
    MatchPo matchPo = new MatchPo();
    String json = "\n" +
            "\n" +
            "\n" +
            "{\"counts\":[4,2,4],\"match\":{\"381\":\"\\u5df4\\u4e59\",\"352\":\"\\u65e5\\u804c\\u4e59\",\"257\":\"\\u54e5\\u7532\",\"36\":\"\\u7f57\\u7532\"},\"row\":[[381,5949,\"#3CC43C\",\"2020-09-22\",931748,\"\\u535a\\u5854\\u5f17\\u6208SP\",0,1,\"\\u5e03\\u62c9\\u5e0c\\u5c14\\u4f69\\u6d1b\\u5854\\u65af\",2,\"1.73\",\"3.10\",\"5.50\",6,0,5713,8002],[352,5797,\"#5A9400\",\"2020-07-29\",926824,\"\\u798f\\u5188\\u9ec4\\u8702\",1,1,\"\\u7231\\u5a9bFC\",1,\"1.60\",\"3.50\",\"5.80\",0,2,808,4129],[257,5760,\"#012080\",\"2020-01-27\",887896,\"\\u5e15\\u7279\\u91cc\\u5965\\u5766\\u65af\",1,1,\"\\u4fa8\\u5361\\u5df4\",1,\"1.63\",\"3.60\",\"5.50\",0,6,6856,3362],[381,5241,\"#3CC43C\",\"2019-08-21\",801261,\"\\u535a\\u5854\\u5f17\\u6208SP\",2,1,\"\\u9686\\u5fb7\\u91cc\\u7eb3\",0,\"1.88\",\"3.20\",\"4.20\",0,2,5713,2242],[381,5241,\"#3CC43C\",\"2019-07-24\",801151,\"\\u8d39\\u53e4\\u57c3\\u4f26\\u65af\",0,1,\"\\u5df4\\u62c9\\u7eb3\",2,\"2.05\",\"3.00\",\"3.90\",0,4,1576,1584],[36,4879,\"#C98810\",\"2019-05-21\",790596,\"\\u8d6b\\u66fc\\u65bd\\u5854\\u7279\",0,2,\"\\u6ce2\\u56fe\\u68ee\\u5c3c\",2,\"1.83\",\"3.25\",\"4.40\",0,2,10206,5584],[381,5241,\"#3CC43C\",\"2019-05-05\",800967,\"\\u7ef4\\u591a\\u5229\\u4e9a\",2,1,\"\\u7ef4\\u62c9\\u8bfa\\u74e6\",0,\"2.10\",\"3.10\",\"3.50\",0,0,1589,2408],[36,4879,\"#C98810\",\"2019-03-03\",742863,\"\\u7ef4\\u6258\\u9c81\\u5eb7\\u65af\\u5766\\u8428\",1,0,\"\\u6ce2\\u56fe\\u68ee\\u5c3c\",0,\"1.73\",\"3.30\",\"5.00\",0,6,6411,5584],[36,4879,\"#C98810\",\"2019-02-16\",742849,\"\\u7ef4\\u6258\\u9c81\\u5eb7\\u65af\\u5766\\u8428\",0,1,\"CSMS\\u96c5\\u897f\",2,\"1.75\",\"3.30\",\"4.80\",0,6,6411,6948],[352,4718,\"#5A9400\",\"2018-10-13\",713621,\"\\u5927\\u5bab\\u677e\\u9f20\",1,0,\"\\u67a5\\u6728SC\",0,\"1.67\",\"3.30\",\"5.50\",0,2,2273,3834]]}";
    Map parse = (Map) JSON.parse(json);
    System.out.println(parse.get("counts"));
    JSONArray counts = JSON.parseArray(parse.get("counts").toString());
    Integer total = 0;
    for(int c=0;c<counts.size();c++){
      Integer integer = counts.getInteger(c);
      total = total + integer;
    }
    //总场次
    System.out.println("total"+total);
    System.out.println(parse.get("match"));
    Map matchMap = (Map) JSON.parse(parse.get("match").toString());
    String row =parse.get("row").toString();
    JSONArray objects = JSON.parseArray(row);
    for(int i=0;i<objects.size();i++){
      String o = objects.get(i).toString();
      JSONArray details = JSON.parseArray(o);
      String saiShi =matchMap.get( details.get(0).toString()).toString();
      matchPo.setSaiShi(saiShi);
      Date matchData = details.getDate(3);
      matchPo.setMatchData(matchData);
      String team1  = details.get(5).toString();
      matchPo.setTeam1(team1);
      Integer teamScore1 = details.getInteger(6);
      matchPo.setTeamScore1(teamScore1);
      Integer teamScore2 = details.getInteger(7);
      matchPo.setTeamScore2(teamScore2);
      String team2 = details.get(8).toString();
      matchPo.setTeam2(team2);
      String winOrLose =winOrLoseMap.get( details.get(9).toString());
      matchPo.setWinOrLose(winOrLose);
      Double finalOffer1 = details.getDouble(10);
      matchPo.setFinalOffer1(finalOffer1);
      Double finalOffer2 = details.getDouble(11);
      matchPo.setFinalOffer2(finalOffer2);
      Double finalOffer3 = details.getDouble(12);
      matchPo.setFinalOffer3(finalOffer3);
      System.out.println(matchPo.toString());
    }



  }

}
