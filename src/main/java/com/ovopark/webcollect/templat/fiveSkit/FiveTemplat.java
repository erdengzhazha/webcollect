package com.ovopark.webcollect.templat.fiveSkit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ovopark.webcollect.entity.Bo.MainBo;
import com.ovopark.webcollect.entity.MainPo;
import com.ovopark.webcollect.entity.MatchPo;
import com.ovopark.webcollect.utils.WebUtile;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.applet.Main;

import java.io.IOException;
import java.util.*;

/**
 * 500彩票的查询
 */
public class FiveTemplat {

  /**
   * 点击 同
   * @param url
   * @return
   * @throws IOException
   */
  public static MainBo doTong(String url,String phpUrl) throws IOException {
    //发送请求
    OkHttpClient okHttpClient = WebUtile.getOkHttpClient();
    Request requestTong = new Request.Builder()
      .url(url)
      .method("GET", null)
      .addHeader("authority", "odds.500.com")
      .addHeader("cache-control", "max-age=0")
      .addHeader("upgrade-insecure-requests", "1")
      .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36")
      .addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
      .addHeader("sec-fetch-site", "same-origin")
      .addHeader("sec-fetch-mode", "navigate")
      .addHeader("sec-fetch-user", "?1")
      .addHeader("sec-fetch-dest", "document")
      .addHeader("referer", "https://odds.500.com/fenxi/ouzhi-926965.shtml")
      .addHeader("accept-language", "zh-CN,zh;q=0.9")
      .addHeader("cookie", "seo_key=baidu%7C%7Chttps://www.baidu.com/link?url=yyVH_AVgypK-7vxJtqIczXus7meSoL_3TbspKWIZkeq&ck=3706.1.74.272.400.267.408.379&shh=www.baidu.com&sht=baiduhome_pg&wd=&eqid=820e9bc70000048b000000045f69e741; _jzqy=1.1600776007.1600776007.1.jzqsr=baidu.-; _jzqckmp=1; __utmz=63332592.1600777650.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utma=63332592.471122266.1600777650.1600786401.1600791517.4; __utmt=1; tgw_l7_route=6f87741a760244b10ae54436fccac88e; sdc_session=1600792046487; Hm_lvt_4f816d475bb0b9ed640ae412d6b42cab=1600776007,1600792047; _qzjc=1; _jzqa=1.551757972765640200.1600776007.1600791251.1600792047.5; _jzqc=1; __utmc=63332592; __utmb=63332592.7.10.1600791517; motion_id=1600792059241_0.38106994302629915; WT_FPC=id=undefined:lv=1600792059808:ss=1600791250305; sdc_userflag=1600791250315::1600792059815::9; _qzja=1.1375748568.1600777638771.1600791250504.1600792046612.1600792046612.1600792059856.0.0.0.25.5; _qzjb=1.1600791250504.7.0.0.0; _qzjto=7.2.0; _jzqb=1.2.10.1600792047.1; Hm_lpvt_4f816d475bb0b9ed640ae412d6b42cab=1600792060; CLICKSTRN_ID=112.80.55.34-1600776007.61854::02C278DADBD1657E1AA75E4C784F1FB1")
      .build();
    Response response1 = okHttpClient.newCall(requestTong).execute();
    ResponseBody body1 = response1.body();
    String gbk = new String(body1.bytes(), "gb2312");
    MainBo mainBo = new MainBo();
    System.out.println("https://odds.500.com/fenxi1/inc/ouzhi_sameajax.php" + phpUrl + "&mid=0");
    String s1 = doPhp("https://odds.500.com/fenxi1/inc/ouzhi_sameajax.php" + phpUrl + "&mid=0",url);
    // 从表的数据
    List<MatchPo> matchPoList = doDetail(s1);
    if(matchPoList!=null){
      //求得main(主表) 的赛场次数 以及比例
      mainBo = doTotal(s1,mainBo);
    }
    //mainBo.setPageHtml(gbk);
    //剩余的从表数据
    Document parse = Jsoup.parse(gbk);
    Elements elements1 = parse.select(".pub_table > tbody > tr > td:nth-child(5)");
    for (int i=0;i<elements1.size();i++){
      matchPoList.get(i).setStartOffer1(Double.parseDouble(elements1.get(i).text()));
    }
    Elements elements2 = parse.select(".pub_table > tbody > tr > td:nth-child(6)");
    for (int i=0;i<elements2.size();i++){
      matchPoList.get(i).setStartOffer2(Double.parseDouble(elements2.get(i).text()));
    }
    Elements elements3 = parse.select(".pub_table > tbody > tr > td:nth-child(7)");
    for (int i=0;i<elements3.size();i++){
      matchPoList.get(i).setStartOffer3(Double.parseDouble(elements3.get(i).text()));
    }
    mainBo.setMatchPos(matchPoList);
    mainBo.setPageHtml(gbk);
    response1.close();
    return mainBo;
  }


  public static String doPhp(String url,String header) throws IOException {
    OkHttpClient okHttpClient = WebUtile.getOkHttpClient();
    Request request = new Request.Builder()
      .url(url)
      .method("GET", null)
      .addHeader("authority", "odds.500.com")
      .addHeader("accept", "application/json, text/javascript, */*; q=0.01")
      .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36")
      .addHeader("x-requested-with", "XMLHttpRequest")
      .addHeader("sec-fetch-site", "same-origin")
      .addHeader("sec-fetch-mode", "cors")
      .addHeader("sec-fetch-dest", "empty")
      //.addHeader("referer", "https://odds.500.com/fenxi1/ouzhi_same.php?cid=1&win=1.95&draw=2.98&lost=3.50&fixtureid=926965")
      .addHeader("referer", header)
      .addHeader("accept-language", "zh-CN,zh;q=0.9")
      .addHeader("cookie", "seo_key=baidu%7C%7Chttps://www.baidu.com/link?url=yyVH_AVgypK-7vxJtqIczXus7meSoL_3TbspKWIZkeq&ck=3706.1.74.272.400.267.408.379&shh=www.baidu.com&sht=baiduhome_pg&wd=&eqid=820e9bc70000048b000000045f69e741; _jzqy=1.1600776007.1600776007.1.jzqsr=baidu.-; _jzqckmp=1; __utmz=63332592.1600777650.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); sdc_session=1600792046487; Hm_lvt_4f816d475bb0b9ed640ae412d6b42cab=1600776007,1600792047; _qzjc=1; _jzqc=1; __utmc=63332592; _jzqx=1.1600794605.1600794605.1.jzqsr=odds%2E500%2Ecom|jzqct=/fenxi/ouzhi-926965%2Eshtml.-; _jzqa=1.551757972765640200.1600776007.1600799747.1600803225.8; __utma=63332592.471122266.1600777650.1600799748.1600803236.7; __utmt=1; motion_id=1600805654287_0.8121065207683369; WT_FPC=id=undefined:lv=1600805658116:ss=1600803224631; sdc_userflag=1600803224638::1600805658130::11; _qzja=1.1375748568.1600777638771.1600799747203.1600803224806.1600805651964.1600805658233.0.0.0.43.8; _qzjb=1.1600803224806.11.0.0.0; _qzjto=25.5.0; _jzqb=1.11.10.1600803225.1; Hm_lpvt_4f816d475bb0b9ed640ae412d6b42cab=1600805658; __utmb=63332592.11.10.1600803236; CLICKSTRN_ID=112.80.55.34-1600776007.61854::02C278DADBD1657E1AA75E4C784F1FB1; tgw_l7_route=d39ecb7a3f33c29612cc32ef18b20fd9")
      .build();
    Response response = okHttpClient.newCall(request).execute();
    return response.body().string();
  }

  /**
   * 点击 欧
   * @param ouHref
   * @return
   * @throws IOException
   */
  public static String doOu(String ouHref) throws IOException {
    //打开ouHref
    OkHttpClient client = WebUtile.getOkHttpClient();
    Request request = new Request.Builder()
      .url(ouHref)
      .method("GET", null)
      .addHeader("authority", "odds.500.com")
      .addHeader("cache-control", "max-age=0")
      .addHeader("upgrade-insecure-requests", "1")
      .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36")
      .addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
      .addHeader("sec-fetch-site", "none")
      .addHeader("sec-fetch-mode", "navigate")
      .addHeader("sec-fetch-user", "?1")
      .addHeader("sec-fetch-dest", "document")
      .addHeader("accept-language", "zh-CN,zh;q=0.9")
      .addHeader("cookie", "sdc_session=1600776006930; seo_key=baidu%7C%7Chttps://www.baidu.com/link?url=yyVH_AVgypK-7vxJtqIczXus7meSoL_3TbspKWIZkeq&ck=3706.1.74.272.400.267.408.379&shh=www.baidu.com&sht=baiduhome_pg&wd=&eqid=820e9bc70000048b000000045f69e741; _jzqa=1.551757972765640200.1600776007.1600776007.1600776007.1; _jzqc=1; _jzqy=1.1600776007.1600776007.1.jzqsr=baidu.-; _jzqckmp=1; Hm_lvt_4f816d475bb0b9ed640ae412d6b42cab=1600776007; _qzjc=1; __utma=63332592.471122266.1600777650.1600777650.1600777650.1; __utmc=63332592; __utmz=63332592.1600777650.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); motion_id=1600778110592_0.597656930089643; WT_FPC=id=undefined:lv=1600778877613:ss=1600777638589; Hm_lpvt_4f816d475bb0b9ed640ae412d6b42cab=1600778878; _qzja=1.1375748568.1600777638771.1600777638771.1600777638771.1600778744600.1600778877723.0.0.0.13.1; _qzjto=13.1.0; CLICKSTRN_ID=112.80.55.34-1600776007.61854::02C278DADBD1657E1AA75E4C784F1FB1")
      .build();
    Response response = client.newCall(request).execute();
    String ouPage = response.body().string();
    response.close();
    return ouPage;
  }



  public static List<MatchPo> doDetail(String json) {
    List<MatchPo> matchPoList = new ArrayList<>();
    Map<String, String> winOrLoseMap = new HashMap<>();
    winOrLoseMap.put("0", "胜");
    winOrLoseMap.put("1", "平");
    winOrLoseMap.put("2", "负");
    Map parse = (Map) JSON.parse(json);
    System.out.println(parse.get("match"));
    if(!parse.get("match").toString().equals("[]")) {
      Map matchMap = (Map) JSON.parse(parse.get("match").toString());
      String row = parse.get("row").toString();
      JSONArray objects = JSON.parseArray(row);
      for (int i = 0; i < objects.size(); i++) {
        MatchPo matchPo = new MatchPo();
        String o = objects.get(i).toString();
        JSONArray details = JSON.parseArray(o);
        //获取赛事
        String saiShi = matchMap.get(details.get(0).toString()).toString();
        matchPo.setSaiShi(saiShi);
        //获取赛事时间
        Date matchData = details.getDate(3);
        matchPo.setMatchData(matchData);
        //获取队伍1
        String team1 = details.get(5).toString();
        matchPo.setTeam1(team1);
        //获取队伍1的胜场数
        Integer teamScore1 = details.getInteger(6);
        matchPo.setTeamScore1(teamScore1);
        //获取队伍2的胜场数
        Integer teamScore2 = details.getInteger(7);
        matchPo.setTeamScore2(teamScore2);
        //获取队伍2
        String team2 = details.get(8).toString();
        matchPo.setTeam2(team2);
        //获取主场 结果
        String winOrLose = winOrLoseMap.get(details.get(9).toString());
        matchPo.setWinOrLose(winOrLose);
        //获取胜率
        Double finalOffer1 = details.getDouble(10);
        matchPo.setFinalOffer1(finalOffer1);
        //获取平率
        Double finalOffer2 = details.getDouble(11);
        matchPo.setFinalOffer2(finalOffer2);
        //获取输率
        Double finalOffer3 = details.getDouble(12);
        matchPo.setFinalOffer3(finalOffer3);
        //System.out.println(matchPo.toString());
        matchPoList.add(matchPo);
      }
      return matchPoList;
    }else{
      return null; //没有数据
    }

  }

  /**
   * 求得main 的赛场次数 以及比例
   * @param json
   * @return
   */
  public static MainBo doTotal(String json,MainBo mainBo){
    Map parse = (Map) JSON.parse(json);
    System.out.println(parse.get("counts"));
    JSONArray counts = JSON.parseArray(parse.get("counts").toString());
    Integer total = 0;
    for (int c = 0; c < counts.size(); c++) {
      Integer integer = counts.getInteger(c);
      if(c==0){
        mainBo.setMainWin(integer);
      }else if(c==1){
        mainBo.setMainFlat(integer);
      }else{
        mainBo.setMainLose(integer);
      }
      total = total + integer;
    }
    mainBo.setTotalNum(total);
    mainBo.setWinRate(mainBo.getMainWin()/total.doubleValue());
    mainBo.setFlatRate(mainBo.getMainFlat()/total.doubleValue());
    mainBo.setLoseRate(mainBo.getMainLose()/total.doubleValue());
    return mainBo;
  }

}

