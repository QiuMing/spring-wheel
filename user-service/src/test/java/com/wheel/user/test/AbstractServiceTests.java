package com.wheel.user.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Ming on 2015/11/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)  //实现spring先加载log4j后被Junit加载
@ContextConfiguration(locations =  {
        "classpath*:config/spring-mvc.xml" ,
        "classpath*:config/spring-mybatis.xml"
}
)
public abstract class AbstractServiceTests  extends AbstractTransactionalJUnit4SpringContextTests {


}
