
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private TransServer transServer;

    @Autowired
    private QueryServer queryServer;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        transServer.start();
        queryServer.start();
    }
}
