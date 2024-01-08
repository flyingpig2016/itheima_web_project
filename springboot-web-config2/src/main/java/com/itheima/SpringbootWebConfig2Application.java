package com.itheima;

import com.example.EnableHeaderConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan({"com.itheima", "com.example"})
//@Import({TokenParser.class}) //导入普通类
//@Import(HeaderConfig.class) //导入配置类
//@Import({MyImportSelector.class}) //导入ImportSelector接口实现类
@EnableHeaderConfig //相当于
public class SpringbootWebConfig2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebConfig2Application.class, args);
    }

//    //声明第三方bean
//    @Bean //将当前方法的返回值对象交给IOC容器管理, 成为IOC容器bean
//    public SAXReader saxReader(){
//        return new SAXReader();
//    }
}
