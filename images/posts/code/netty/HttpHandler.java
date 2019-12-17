/**
 * @author hitol
 * @date 2019/7/21 9:53 PM
 */
@Component
@ChannelHandler.Sharable
public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private AsciiString contentType = HttpHeaderValues.TEXT_PLAIN;

    @Autowired
    private ServiceDispatcher serviceDispatcher;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        String reqJson = msg.content().toString(CharsetUtil.UTF_8);
        logger.info("request message:[{}]", reqJson);

//        String respJson = serviceDispatcher.doProcess(reqJson);
//        logger.info("response message:[{}]", respJson);

        String respJson = "success";

        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(respJson.getBytes()));


        HttpHeaders heads = response.headers();
        heads.add(HttpHeaderNames.CONTENT_TYPE, contentType + "; charset=UTF-8");
        heads.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        heads.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);

        ctx.write(response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.info("channelReadComplete");
        super.channelReadComplete(ctx);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}