package com.ovopark.webcollect.utils;

import okhttp3.OkHttpClient;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WebUtile
 * @author Xiu_Er 13813641925@163.com
 * @CreateTime 2020年9月22号 晚 7:14
 */
public class WebUtile {
  /**
   * 私有化本类
   */
  private WebUtile(){
  }

  private enum OkHttpEnumUtile{
    instance;
    private OkHttpClient client;
    OkHttpEnumUtile(){
      client = new OkHttpClient().newBuilder()
        .build();
    }
    private OkHttpClient init(){
      return client;
    }
  }
  //单利获取OkHttp
  public static OkHttpClient getOkHttpClient(){return OkHttpEnumUtile.instance.init();}


  private enum ApacheHttpEnumUtile{
    instance;
    private  PoolingHttpClientConnectionManager httpPool;
    ApacheHttpEnumUtile(){
      httpPool = new PoolingHttpClientConnectionManager();
      httpPool.setMaxTotal(200);
      httpPool.setDefaultMaxPerRoute(20);
    }
    private PoolingHttpClientConnectionManager init(){return httpPool;}
  }

  //单利获取线程池
  private static PoolingHttpClientConnectionManager getPoolHttpClient(){
    return ApacheHttpEnumUtile.instance.init();
  }

  //创建Http Get请求
  public static String doGet(String url){
    //从链接池中过去连接
    //System.out.println(getPoolHttpClient());
    //获取连接
    CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(getPoolHttpClient()).build();
    //声明访地址
    HttpGet get = new HttpGet(url);
    CloseableHttpResponse response =null;
    String content = "";
    try {
      try {
        response = httpClient.execute(get);

        //判断返回吗
        if (response.getStatusLine().getStatusCode() == 200) {
          //解析数据
          content = EntityUtils.toString(response.getEntity(), "UTF-8");
          return content;
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      } finally {
        response.close();
      }
    }catch (Exception e){
      System.out.println(e.getMessage());
    }
    return content;
  }

  //创建Http Get请求
  public static String sHtmlGet(String url){
    //获取连接
    CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(getPoolHttpClient()).build();
    RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(1000)
      // 创建连接的最长时间
      .setConnectionRequestTimeout(500) // 从连接池中获取到连接的最长时间
      .setSocketTimeout(10 * 1000) // 数据传输的最长时间
      .build();
    //声明访地址
    HttpGet get = new HttpGet(url);
    get.setHeader("authority", "odds.500.com");
    get.setHeader("cache-control", "max-age=0");
    get.setHeader("upgrade-insecure-requests", "1");
    get.setHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36");
    get.setHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
    get.setHeader("sec-fetch-site", "none");
    get.setHeader("sec-fetch-mode", "navigate");
    get.setHeader("sec-fetch-user", "?1");
    get.setHeader("sec-fetch-dest", "document");
    get.setHeader("accept-language", "zh-CN,zh;q=0.9");
    get.setHeader("cookie", "sdc_session=1600776006930; seo_key=baidu%7C%7Chttps://www.baidu.com/link?url=yyVH_AVgypK-7vxJtqIczXus7meSoL_3TbspKWIZkeq&ck=3706.1.74.272.400.267.408.379&shh=www.baidu.com&sht=baiduhome_pg&wd=&eqid=820e9bc70000048b000000045f69e741; _jzqa=1.551757972765640200.1600776007.1600776007.1600776007.1; _jzqc=1; _jzqy=1.1600776007.1600776007.1.jzqsr=baidu.-; _jzqckmp=1; Hm_lvt_4f816d475bb0b9ed640ae412d6b42cab=1600776007; _qzjc=1; __utma=63332592.471122266.1600777650.1600777650.1600777650.1; __utmc=63332592; __utmz=63332592.1600777650.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); motion_id=1600778110592_0.597656930089643; WT_FPC=id=undefined:lv=1600778877613:ss=1600777638589; Hm_lpvt_4f816d475bb0b9ed640ae412d6b42cab=1600778878; _qzja=1.1375748568.1600777638771.1600777638771.1600777638771.1600778744600.1600778877723.0.0.0.13.1; _qzjto=13.1.0; CLICKSTRN_ID=112.80.55.34-1600776007.61854::02C278DADBD1657E1AA75E4C784F1FB1");



    get.setConfig(config);
    CloseableHttpResponse response =null;
    String content = "";
    try {
      try {
        response = httpClient.execute(get);
        //response.setHeader("content-type","text/html");
        //判断返回吗
        if (response.getStatusLine().getStatusCode() == 200) {
          //解析数据
          content = EntityUtils.toString(response.getEntity(), "UTF-8");
          return content;
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      } finally {
        response.close();
      }
    }catch (Exception e){
      System.out.println(e.getMessage());
    }
    return content;
  }

}
