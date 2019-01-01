package m;

import com.snail.service.m.UserService;
import com.snail.web.m.vo.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by liutao on 2018/12/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-config.xml"}) //加载配置文件
public class UserServiceTest {
    @Autowired
    UserService userManager;



    @Test
    public void testUserService() {
        UserVo vo = new UserVo();

        vo.setUserName("liutao");
        vo.setPassword("index.jspl");
        vo.setAliasName("snail");
        vo.setEmail("index-jsp@hotmail.com");
        vo.setPhone("13902101490");
        vo.setCreateTime(new Date());
        vo.setStart(0);

        userManager.saveUser(vo);
    }

}