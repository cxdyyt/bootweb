package my.springboot.start.bootstudy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class MyApplicationTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void test1() {
        long result = jdbcTemplate.queryForObject("select count(1) from xjh1", Long.class);
        System.out.println(result);
    }
}
