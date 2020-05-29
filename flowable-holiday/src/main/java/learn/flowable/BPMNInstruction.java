package learn.flowable;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;

public class BPMNInstruction {
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://localhost:3306/flowable?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC")
                .setJdbcUsername("root")
                .setJdbcPassword("123456")
                .setJdbcDriver("com.mysql.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                .buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        repositoryService
                .createDeployment()
                .addClasspathResource("FinancialReportProcess.bpmn20.xml")
                .tenantId("t1")
                .deploy();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        runtimeService.startProcessInstanceByKey("financialReport");

    }
}
