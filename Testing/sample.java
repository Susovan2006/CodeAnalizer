{
    "ruleset": [
        {
            "ruleNo": 1,
            "ruleType":"SpringBoot",
            "ruleDescription": "Search if the application is a SpringBoot App",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["@SpringBootApplication","org.springframework.boot"],
            "isAlertEnabled": true
        },
        {
            "ruleNo": 2,
            "ruleType":"Spring MVC",
            "ruleDescription": "Search if the application is a Spring MVC App",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["spring-webmvc","WebApplicationContext","DispatcherServlet"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 3,
            "ruleType":"EJB",
            "ruleDescription": "Search if the application is an EJB App",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["javax.ejb"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 4,
            "ruleType":"Servlet",
            "ruleDescription": "Search if the application is a Java/Servlet App",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["javax.servlet"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 5,
            "ruleType":"Web Sphere",
            "ruleDescription": "Check if the application runs on Web Sphere",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["com.ibm.websphere","com.ibm.ws","WAS_HOME","websphere.application.name"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 6,
            "ruleType":"JBOSS",
            "ruleDescription": "Check if the application runs on JBOSS",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["org.jboss","jboss.server"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 7,
            "ruleType":"Tomcat",
            "ruleDescription": "Check if the application runs on Tomcat",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["org.apache.catalina","org.apache.tomcat"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 8,
            "ruleType":"WebLogic",
            "ruleDescription": "Check if the application runs on WebLogic",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["weblogic","jndi.WLInitialContextFactory"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 9,
            "ruleType":"DB2 ",
            "ruleDescription": "Check if the application is using DB2 as DB",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["ibm.db2","DB2DataSource","DB2Connection","db2jcc"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 10,
            "ruleType":"Oracle DB",
            "ruleDescription": "Check if the application is using Oracle as DB",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["oracle.jdbc.OracleDriver","jdbc:oracle"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 11,
            "ruleType":"Teradata DB",
            "ruleDescription": "Check if the application is using Teradata as DB",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["TeradataDataSource","TeradataConnection","terajdbc4","teradata"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 12,
            "ruleType":"Database TX",
            "ruleDescription": "Check if the application is using Database TX",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["rollback","setAutoCommit","commit","@Transactional","TransactionManager","UserTransaction"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 13,
            "ruleType":"Hibernate",
            "ruleDescription": "Check if the application is using Hibernate for DB Connection",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["org.hibernate","SessionFactory","hibernate-core"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 14,
            "ruleType":"JNDI",
            "ruleDescription": "Check if the application is using JNDI for DB Connection",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["javax.naming.InitialContext","java:comp/env","javax.sql.DataSource"],
            "isAlertEnabled": true
        },
        {
            "ruleNo": 15,
            "ruleType":"Kafka",
            "ruleDescription": "Check if the application is using Kafka",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["org.apache.kafka","KafkaProducer","KafkaConsumer","kafka-clients"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 16,
            "ruleType":"IBM MQ",
            "ruleDescription": "Check if the application is using IBM MQ",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["com.ibm.mq","MQQueue","MQTopic","MQQueueManager","mq-jms-spring-boot-starter","jms"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 17,
            "ruleType":"SOAP Axis",
            "ruleDescription": "Check if the application is using Axis for SOAP Calls",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["org.apache.axis","axis","wsdl2java"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 18,
            "ruleType":"SOAP cfx",
            "ruleDescription": "Check if the application is using cfx for SOAP Calls",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["org.apache.cxf","cxf-rt-frontend-jaxws"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 19,
            "ruleType":"REST API",
            "ruleDescription": "Check if the application is using REST API Calls",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["@RestController","@RequestMapping","@GetMapping","@PostMapping","RestTemplate","javax.ws.rs"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 20,
            "ruleType":"Spring Batch",
            "ruleDescription": "Check if the application has Spring Batch",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["@EnableBatchProcessing","org.springframework.batch.core","StepBuilderFactory","JobBuilderFactory"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 21,
            "ruleType":"Cron/Quartz",
            "ruleDescription": "Check if the application has Cron/Quartz Batch",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["@Scheduled","CronTrigger","org.quartz","CronScheduleBuilder","cron","ScheduledTaskRegistrar"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 22,
            "ruleType":"AutoSys",
            "ruleDescription": "Check if the application has Autosys Batch",
            "listOfSearchFileType": [".jil",".txt",".md"],
            "listOfSearchString": ["insert_job","Autosys"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 23,
            "ruleType":"File Handling",
            "ruleDescription": "Check if the application has File Handling",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["MultipartFile","FileInputStream","FileOutputStream","java.nio.file.Files","file.transferTo"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 23,
            "ruleType":"XLS Handling",
            "ruleDescription": "Check if the application has XLS file Operation",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["org.apache.poi","HSSFWorkbook","XSSFWorkbook","HSSFSheet","XSSFSheet"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 24,
            "ruleType":"PDF Handling",
            "ruleDescription": "Check if the application has PDF file Operation",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["com.itextpdf","PdfWriter","org.apache.pdfbox","net.sf.jasperreports","PDDocument"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 25,
            "ruleType":"email Handling",
            "ruleDescription": "Check if the application has email Capability",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["javax.mail","mail.smtp.host","mail.smtp.port","JavaMailSender","MimeMessage"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 26,
            "ruleType":"Redish Cache",
            "ruleDescription": "Check if the application is using Redish Cache",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["redis.clients.jedis","io.lettuce","RedisTemplate","@EnableCaching","Jedis"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 26,
            "ruleType":"ehcache Cache",
            "ruleDescription": "Check if the application is using ehcache Cache",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["net.sf.ehcache","EhCacheCacheManager"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 26,
            "ruleType":"Venafi/JKS",
            "ruleDescription": "Check if the application is using Venafi/JKS",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["com.venafi","KeyStore","CertStore","VcertClient","CertificateRequest","EnrollmentTransaction"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 27,
            "ruleType":"PingFed",
            "ruleDescription": "Check if the application has PingFed implementation",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["com.pingidentity","PingFederate"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 28,
            "ruleType":"Siteminder",
            "ruleDescription": "Check if the application has Siteminder implementation",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["com.netegrity.siteminder","SMSESSION"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 29,
            "ruleType":"NDM",
            "ruleDescription": "Check if the application is using NDM for File Transmission",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["com.ibm.connect.direct","NetDataMover","NDMFileTransfer","NDMConnection","NDMSession"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 30,
            "ruleType":"SFTP",
            "ruleDescription": "Check if the application is using SFTP for File Transmission",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["com.jcraft.jsch","ChannelSftp","org.apache.commons.net.ftp.FTPSClient","JSch"],
            "isAlertEnabled": false
        },
        {
            "ruleNo": 31,
            "ruleType":"Web Socket",
            "ruleDescription": "Check if the application is using Web Socket",
            "listOfSearchFileType": [".java",".xml",".properties",".json",".xml",".gradle",".jsp",".txt",".md"],
            "listOfSearchString": ["javax.websocket","org.eclipse.jetty.websocket","org.springframework.web.socket","@ServerEndpoint","@ClientEndpoint","WebSocketHandler"],
            "isAlertEnabled": false
        }
    ]
}