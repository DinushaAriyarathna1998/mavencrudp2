
import StudentPackage.dao.StudentDBUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan({"StudentPackage"})
@PropertySource("classpath:application.properties")
public class StudentConfig implements InitializingBean {


    @Autowired
    Environment env;

    @Value( "${db.url}" )
    private String jdbcUrl;

    @Value( "${db.username}" )
    private String username;

    @Value( "${db.password}" )
    private String password;

    @Override
    public void afterPropertiesSet() throws Exception {
        setDatabaseConfig();
    }

    private void setDatabaseConfig() {

        StudentDBUtil.setUrl(jdbcUrl);
        StudentDBUtil.setUsername(username);
        StudentDBUtil.setPassword(password);
    }

}
