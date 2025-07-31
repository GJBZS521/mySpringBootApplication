package org.example;

import org.apache.ibatis.annotations.Param;
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


}
