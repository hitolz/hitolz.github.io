/**
 * 数据传输server 端口31008
 *
 * @author hitol
 * @date 2019/7/20 10:29 AM
 */
@Component
public class TransServer extends HttpsServer {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${trans.server.https.port}")
    int transServerPort;

    @Override
    public void start() {
        start(transServerPort);
    }
}
