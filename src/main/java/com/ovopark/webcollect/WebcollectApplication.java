package com.ovopark.webcollect;
import com.ovopark.webcollect.entity.MainPo;
import com.ovopark.webcollect.service.MainService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan(value = "com.ovopark.webcollect.mapper")
public class WebcollectApplication {
  public static void main(String[] args) {

    SpringApplication.run(WebcollectApplication.class, args);
    //MainService mainService = (MainService)run.getBean("mainServiceImpl");
    //int save = mainService.save(new MainPo());
    //System.out.println(save);
  }

}
