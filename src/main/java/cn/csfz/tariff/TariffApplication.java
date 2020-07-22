package cn.csfz.tariff;

import cn.csfz.tariff.util.OkHttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"cn.csfz"})
public class TariffApplication {


    public static void main(String[] args) {
        SpringApplication.run(TariffApplication.class, args);
    }


}
