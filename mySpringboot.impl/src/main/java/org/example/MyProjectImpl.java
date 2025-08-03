package org.example;

import org.apache.ibatis.annotations.Param;
import org.example.annotation.MethodExecutionAnnotation;
import org.example.dao.PersonDao;
import org.example.product.KafkaProduct;
import org.example.util.RedisClient;
import org.example.vo.PersonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
public class MyProjectImpl implements HelloWorldService {

    @Autowired
    PersonDao personDao;

    @Autowired
    RedisClient redisClient;

    @Autowired
    KafkaProduct kafkaProduct;

    @Override
    @GetMapping("/helloWorld")
    public String getHelleWorld() {
        return "hello world";
    }



    @GetMapping("/getPersonInfo")
    public List<PersonVo> getPersonInfo() {
        List<PersonVo> personVoList = personDao.queryAllPersonInfo();
        System.out.println(personVoList.size());
        return personVoList;
    }

    @Override
    @GetMapping("/getResultByKey")
    public String getResultByKey(@Param("key")String key) {
        return redisClient.getValueByKey(key);
    }

    @Override
    @GetMapping("/sendInfoToMQ")
    public void sendInfoToMQ(@Param("key")String key,@Param("value")String value) {
        kafkaProduct.sendIntoMQ(key, value);
    }

    @Override
    @MethodExecutionAnnotation("学习注解第一课")
    @GetMapping("/getAnnotationMethod1")
    public void getAnnotationMethod1(String arg1, String arg2) {
        System.out.println("execute getAnnotationMethod1");
    }

    @Override
    @MethodExecutionAnnotation(value = "学习注解第二课", logArguments = false)
    @GetMapping("/getAnnotationMethod2")
    public void getAnnotationMethod2(String arg1) {
        System.out.println("!!!!!!!!!!!!!!!!");
    }

    @Override
    @MethodExecutionAnnotation(value = "学习注解第三课", logArguments = false, logCaller = false)
    @GetMapping("/getAnnotationMethod3")
    public void getAnnotationMethod3() {
        System.out.println("-----------------");
    }

    @MethodExecutionAnnotation(value = "学习注解第四课", logArguments = false, logCaller = false)
    @GetMapping("/getAnnotationMethod4")
    public void getAnnotationMethod4(String arg1, String arg2) {
        System.out.println("execute getAnnotationMethod4");
    }
}
