package com.ovopark.webcollect;

import com.ovopark.webcollect.entity.Bo.MainBo;
import com.ovopark.webcollect.entity.MainPo;
import com.ovopark.webcollect.entity.MatchPo;
import com.ovopark.webcollect.service.MainService;
import com.ovopark.webcollect.service.MatchService;
import com.ovopark.webcollect.templat.fiveSkit.FiveTemplat;
import com.ovopark.webcollect.utils.WebUtile;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class WebcollectApplicationTests {

  public static String HOST = "https://odds.500.com";
  public static String INDEX = "https://trade.500.com/jczq/";
  @Autowired
  private MainService mainService;
  @Autowired
  private MatchService matchService;

  @Test
  void contextLoads() throws IOException, InterruptedException {
    while (true) {
      //500的胜负主页
      String indexPage = WebUtile.doGet(INDEX);
      Document doc = Jsoup.parse(indexPage);
      Elements ous = doc.select(".td-data > a:last-child");
      int page = 1;
      //遍历每一个 ”欧“
      for (Element e : ous) {
        System.out.println("---------------------------index Page  " + page + "页");
        page++;
        String ouHref = e.attr("href");
        System.out.println(ouHref);
        //排除掉没有数据的情况
        if (!ouHref.contains("javascript")) {
          String ouIndex = FiveTemplat.doOu(ouHref);
          //System.out.println(ouIndex);
          Document tongDoc = Jsoup.parse(ouIndex);
          //获取所有的 "同"
          Elements elements = tongDoc.select("tr > td:nth-child(7) > a:nth-child(3)");
          for (Element tongEl : elements) {
            String href = tongEl.attr("href");
            System.out.println(HOST + href);
            MainBo mainBo = FiveTemplat.doTong(HOST + href, href.substring(href.indexOf("?")).replace("fixture", ""));
            List<MatchPo> matchPos = mainBo.getMatchPos();
            String tongPage = mainBo.getPageHtml();
            //System.out.println(tongPage);
            //-----------------------------主表 start -------------------
            MainPo mainPo = null;
            try {
              mainPo = new MainPo();
              Document tongDocument = Jsoup.parse(tongPage);
              mainPo.setCompanyName(tongDocument.select(".M_title h4").text());
              Double d1 = Double.valueOf(handleNum(tongDocument.select(".con_top2 > span:nth-child(1) > strong:nth-child(1)").text()));
              mainPo.setStart1(d1);
              Double d2 = Double.valueOf(tongDocument.select(".con_top2 > span:nth-child(1) > strong:nth-child(2)").text());
              mainPo.setStart2(d2);
              Double d3 = Double.valueOf(tongDocument.select(".con_top2 > span:nth-child(1) > strong:nth-child(3)").text());
              mainPo.setStart3(d3);
              mainPo.setTotalNum(mainBo.getTotalNum());
              mainPo.setMainWin(mainBo.getMainWin());
              mainPo.setMainFlat(mainBo.getMainFlat());
              mainPo.setMainLose(mainBo.getMainLose());
              mainPo.setWinRate(mainBo.getWinRate());
              mainPo.setFlatRate(mainBo.getFlatRate());
              mainPo.setLoseRate(mainBo.getLoseRate());

              //将数据推送到数据库
              int save = mainService.save(mainPo);
              if (save > 0) {
                System.out.println("主表数据插入成功！" + "\tid=" + mainPo.getId());
              } else {
                System.out.println("主表数据插入失败!" + "\tid=" + mainPo.getId());
              }
              for (MatchPo m : matchPos) {
                m.setStartOffer1(d1);
                m.setStartOffer2(d2);
                m.setStartOffer3(d3);
                m.setMain_id(mainPo.getId());
                int save1 = matchService.save(m);
                if (save1 > 0) {
                  System.out.println("从表数据插入成功!" + "\t 主表id=" + mainPo.getId());
                } else {
                  System.out.println("从表数据插入失败！" + "\t 主表id=" + mainPo.getId());
                }
              }
            } catch (Exception ex) {
              System.out.println(ex.getMessage());
              //System.out.println(mainPo.toString());
            }

            ///break;
            Thread.sleep(5000);
          }
          //break;
          //解析到同
          Thread.sleep(5000);
        }
      }
    }
  }

  public static String handleNum(String num){
    if(num.equals("") ){
      return "0";
    }
    return num;
  }
}
