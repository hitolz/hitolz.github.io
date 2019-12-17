/**
 * 开启https端口
 *
 * @author hitol
 * @date 2019/7/26 11:51 AM
 */
public class HttpsServer extends AbstractCommonServer {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    int transServerPort;

    @Autowired
    private HttpsHandler httpsHandler;

    public void start() {
        start(transServerPort);
    }

    @Override
    public ChannelInitializer<SocketChannel> getChannel() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(httpsHandler);
            }
        };
    }
}
