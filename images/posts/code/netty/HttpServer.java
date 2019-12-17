/**
 * 开启http 端口
 *
 * @author hitol
 * @date 2019/7/26 11:52 AM
 */
public class HttpServer extends AbstractCommonServer {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${trans.server.http.port}")
    int transServerPort;

    @Autowired
    private HttpHandler httpHandler;

    public void start() {
        start(transServerPort);
    }

    public static void main(String[] args) {
        new TransServer().start(31008);
    }

    @Override
    public ChannelInitializer<SocketChannel> getChannel() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new HttpServerCodec());
                ch.pipeline().addLast(new HttpObjectAggregator(512 * 1024));

                ch.pipeline().addLast(httpHandler);
            }
        };
    }
}
