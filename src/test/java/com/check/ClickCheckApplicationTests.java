package com.check;

import com.check.system.controller.DictController;
import com.check.common.util.RedisUtils;
import com.check.security.pojo.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
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

//        DictAddDto addDto = new DictAddDto();
//        addDto.setDictGroup("TEST_GROUP");
//        addDto.setDictKey("TEST_KEY_2");
//        addDto.setDictValue("TEST_VALUE_2");
//        System.out.println(dictController.add(addDto));

        Object user_admin = RedisUtils.getValue("user_admin");
        User user = new User();
        BeanUtils.copyProperties(user_admin, user);
        System.out.println(user);

    }

    @Test
    void saveUser() {

    }

}
