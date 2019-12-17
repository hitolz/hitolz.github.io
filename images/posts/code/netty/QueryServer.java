/**
 * 数据查询server 21004
 *
 * @author hitol
 * @date 2019/7/26 2:22 PM
 */
@Component
public class QueryServer extends HttpsServer {

    @Value("${query.server.https.port}")
    int queryProt;

    @Override
    public void start() {
        start(queryProt);
    }
}
