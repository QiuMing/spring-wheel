import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;

/**
 * Created by Ming on 2016/1/26.
 * 初始化Activiti  的23 张表，先配置activiti.cfg.xml数据库的链接，在再数据库中创建一个名为activiti的数据库
 */
public class InitDB {
        public static void main(String[] args) {
            ProcessEngineConfiguration
                    .createProcessEngineConfigurationFromResourceDefault()
                    .setDatabaseSchemaUpdate(ProcessEngineConfigurationImpl.DB_SCHEMA_UPDATE_CREATE)
                    .buildProcessEngine();

    }
}
