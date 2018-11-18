package kr.or.ddit.util;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
		"classpath:kr/or/ddit/config/spring/servlet-context.xml",
		"classpath:kr/or/ddit/config/spring/root-context.xml",
		"classpath:kr/or/ddit/config/spring/datasource-context_test.xml"
})
@WebAppConfiguration
public class ControllerTestConfig {
	@Autowired
	private WebApplicationContext ctx;
	
	protected MockMvc mockMvc;
	
	@Resource(name="datasource")
	private DataSource datasource;
	
	@Before
	public void setUp() {
		//데이터 초기화
		//delete query 호출 > sql 스크립트를 생행해주는 부분
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("/kr/or/ddit/config/db/dbInit.sql"));
		populator.setContinueOnError(false);
		DatabasePopulatorUtils.execute(populator, datasource);
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Ignore
	@Test
	public void test() {
	}
}
