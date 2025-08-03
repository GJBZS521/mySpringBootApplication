package org.example;

import org.example.vo.PersonVo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

public interface HelloWorldService {

    String getHelleWorld();

    List<PersonVo> getPersonInfo();

    String getResultByKey(String key);

    void sendInfoToMQ(String key, String value);

    void getAnnotationMethod1(String arg1, String arg2);

    void getAnnotationMethod2(String arg1);

    void getAnnotationMethod3();
}
