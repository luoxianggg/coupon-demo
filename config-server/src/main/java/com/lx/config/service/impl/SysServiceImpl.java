package com.lx.config.service.impl;

import com.google.gson.Gson;
import com.lx.config.bean.Param;
import com.lx.config.bean.SCCSystem;
import com.lx.config.bean.SCCUser;
import com.lx.config.service.SysService;
import com.lx.config.util.RedisStringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysServiceImpl implements SysService {


    @Autowired
    private RedisStringUtils redisStringUtils;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private Gson gson;

    @Override
    public SCCUser queryUserInfo(String token) {

        String value = redisStringUtils.get(RedisStringUtils.USER_CACHE_PREFIX+token);
        SCCUser sccUser = gson.fromJson(value,SCCUser.class);

        return sccUser;
    }

    @Override
    public List<SCCSystem> querySysInfo() {

        List<Map<String,Object>> objects = sqlSessionTemplate.selectList("sysMapper.querySysInfo");
        return convertdb2Obj(objects);
    }

    private List<SCCSystem> convertdb2Obj(List<Map<String,Object>> dbRes){

        if(CollectionUtils.isEmpty(dbRes)){
            return null;
        }

        List<SCCSystem> result = new ArrayList();
        dbRes.forEach(k ->{
            result.add(new SCCSystem(((Integer)k.get("sysid")).intValue(),k.get("sysname").toString()));
        });
        return result;
    }
    @Override
    public List<Param> queryParamInfo(String sysId, String envName) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("SysId",sysId);
        map.put("EnvName",envName);
        List<Map<String,Object>> res = sqlSessionTemplate.selectList("sysMpper.queryParamInfo", map);
        if(CollectionUtils.isEmpty(res)){
            return null;
        }
        List<Param> result = new ArrayList();

        res.forEach(k->{
            int id= ((Integer)k.get("id")).intValue();
            String paramKey = (String) k.get("paramKey");
            String paramValue = (String) k.get("paramValue") ;
            result.add(new Param(id,paramKey,paramValue));
        });
        return result;
    }

    @Override
    public int updateParam(String paramId, String paramValue) {

        Map<String,String> map = new HashMap<String,String>();
        map.put("Id",paramId);
        map.put("ParamValue",paramValue);
        int affectCount =sqlSessionTemplate.update("sysMapper.updateParam",map);


        return affectCount;
    }
}
