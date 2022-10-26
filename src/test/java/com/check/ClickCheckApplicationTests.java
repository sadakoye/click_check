package com.check;

import com.check.common.controller.DictController;
import com.check.common.pojo.dto.DictAddDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClickCheckApplicationTests {

    @Autowired
    DictController dictController;

    @Test
    void contextLoads() {
//        DictDto dictDto = new DictDto();
//        dictDto.setPageSize(1);
//        Result list = dictController.list(dictDto);
//        System.out.println(list);

        DictAddDto addDto = new DictAddDto();
        addDto.setDictGroup("TEST_GROUP");
        addDto.setDictKey("TEST_KEY_2");
        addDto.setDictValue("TEST_VALUE_2");
        System.out.println(dictController.add(addDto));
    }

    @Test
    void saveUser() {

    }

}
