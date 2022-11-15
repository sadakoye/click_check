package com.check;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.check.common.pojo.dto.BaseDto;
import com.check.common.util.DataUtils;
import com.check.common.util.RedisUtils;
import com.check.security.pojo.bean.User;
import com.check.system.controller.DictController;
import com.check.system.pojo.SysDict;
import com.check.system.pojo.dto.DictDto;
import com.check.system.service.DictService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ClickCheckApplicationTests {

    @Autowired
    DictController dictController;

    @Test
    void contextLoads() {

        Object user_admin = RedisUtils.getValue("user_admin");
        User user = new User();
        BeanUtils.copyProperties(user_admin, user);
        System.out.println(user);

    }

    @Autowired
    DictService dictService;

    @Test
    void query() {

        Map<String, List<Object>> eqMap = new HashMap<>(1);
        eqMap.put("DICT_KEY", Collections.singletonList("a"));
        eqMap.put("DICT_VALUE", Collections.singletonList(""));
        eqMap.put("DICT_GROUP", null);
//        QueryWrapper<SysDict> queryWrapper = DataUtils.baseQuery(eqMap, null);
//        List<SysDict> list = dictService.list(queryWrapper);
//        System.out.println(list);

    }


    @Test
    void test() {

        SysDict dict = new SysDict();
        dict.setDictKey("a");
        dict.setDictValue("");
        QueryWrapper<SysDict> query = DataUtils.query(dict, new BaseDto());
        List<SysDict> list = dictService.list(query);
        System.out.println(list);

    }


    @Test
    void test2() {

        DictDto dto = new DictDto();
        dto.setDictKey("a");
        SysDict dict = new SysDict();
        BeanUtils.copyProperties(dto, dict);


    }


}
