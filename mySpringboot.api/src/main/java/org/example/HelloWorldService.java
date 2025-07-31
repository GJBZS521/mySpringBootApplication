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
}
