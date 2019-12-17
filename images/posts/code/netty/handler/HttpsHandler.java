/**
 * @author hitol
 * @date 2019/7/21 8:10 PM
 */
@Component
@ChannelHandler.Sharable
public class HttpsHandler extends ChannelInitializer<SocketChannel> {

    @Autowired
    private HttpHandler httpHandler;

    private final SslContext sslContext;

    public HttpsHandler() {
        String keyStorePassword = "123456";
        InputStream keyStoreStream = this.getClass().getClassLoader().getResourceAsStream("security/ks.keystore");

        try {
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(keyStoreStream, keyStorePassword.toCharArray());

            keyManagerFactory.init(keyStore, keyStorePassword.toCharArray());

            sslContext = SslContextBuilder.forServer(keyManagerFactory).build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        SSLEngine sslEngine = sslContext.newEngine(ch.alloc());
        sslEngine.setUseClientMode(false);
        pipeline
                .addFirst("ssl", new SslHandler(sslEngine))
                .addLast("decoder", new HttpRequestDecoder())
                .addLast("encoder", new HttpResponseEncoder())
                .addLast("aggregator", new HttpObjectAggregator(512 * 1024))
                .addLast("handler", httpHandler);
        ;
    }

}
