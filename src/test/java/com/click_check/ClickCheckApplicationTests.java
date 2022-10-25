package com.click_check;

import com.click_check.common.controller.DictController;
import com.click_check.common.pojo.bean.Result;
import com.click_check.common.pojo.dto.DictDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClickCheckApplicationTests {

    @Autowired
    DictController dictController;

    @Test
    void contextLoads() {
        DictDto dictDto = new DictDto();
        dictDto.setPageSize(1);
        Result list = dictController.list(dictDto);
        System.out.println(list);
    }

    @Test
    void saveUser() {

    }

}
