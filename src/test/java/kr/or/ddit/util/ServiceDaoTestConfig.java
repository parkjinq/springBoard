package kr.or.ddit.util;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
		"classpath:kr/or/ddit/config/spring/root-context.xml",
		"classpath:kr/or/ddit/config/spring/datasource-context_test.xml"
})

public class ServiceDaoTestConfig {
	
	@Resource(name="datasource")
	private DataSource datasource;
	
	@Before
	public void setUp() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("/kr/or/ddit/config/db/dbInit.sql"));
		populator.setContinueOnError(false);
		DatabasePopulatorUtils.execute(populator, datasource);
	}
	
	@Ignore
	@Test
	public void test() {
	}
}
