package com.lx.coupon.service.impl.sys;

import com.google.gson.Gson;
import com.lx.coupon.bean.SysUser;
import com.lx.coupon.service.sys.SysUserService;
import com.lx.coupon.util.RedisStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;


    @Autowired
    private RedisStringUtils redisStringUtils;

    @Autowired
    private Gson gson;

    @Override
    public SysUser login(String username, String password) {
        //1.数据库查询
        if(username==null ||password==null){
            return null;
        }
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("UserName",username);
        paramMap.put("Password",password);
        Map<String,String> res = sqlSessionTemplate.selectOne("SysUserRolesMapper.queryUserByUserName",
                paramMap);
        if(CollectionUtils.isEmpty(res)){
            return null;//没有查询到用户
        }else{
            SysUser user = convertDb2Bean(res);
            //更新数据库
          //  insertLoginRecord(user);
            //更新缓存
            updateCacheUser(user);
            return user;
        }
    }

    @Override
    public boolean userExistInCache(String token) {
        if(token == null || "".equals(token)){
            return false;
        }
        String value = redisStringUtils.get(RedisStringUtils.USER_CACHE_PREFIX+token);
        if(!StringUtils.isEmpty(value)){
            //重新激活
            redisStringUtils.setKey(RedisStringUtils.USER_CACHE_PREFIX+token,
                    value,EXPIRE_SECONDS);
            return true;
        }
        return false;
    }

    //更新缓存信息

    private final int EXPIRE_SECONDS = 60*60*24;
    private void updateCacheUser(SysUser user){
        //token 慕课网用SnowflakeldGeneator找不到
        user.setUniqueId(Long.parseLong(UUID.randomUUID().toString().replace("-","").substring(0,10)));

        redisStringUtils.setKey(RedisStringUtils.USER_CACHE_PREFIX+user.getUniqueId(),gson.toJson(user),EXPIRE_SECONDS);
    }

   /* private void updateDbUser(SysUser user){
        //更新javaBean对象的最新登录时间
        user.setLastLoginDateTime(DateFormatUtils.format(new Date(),"yyyyMMdd HH:mm:ss"));

        //保存到数据库
        String[] dateTime =  user.getLastLoginDateTime().split(" ");

        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("UserName",user.getUserName());
        paramMap.put("LastLoginDate",dateTime[0]);
        paramMap.put("LastLoginTime",dateTime[1]);
        sqlSessionTemplate.update("userMapper.updateUserLoginTime",paramMap);

    }*/

    private SysUser convertDb2Bean(Map<String,String> res){
        return new SysUser(res.get("username")
                ,res.get("lastlogindate"),res.get("lastlogintime"));
    }
}
