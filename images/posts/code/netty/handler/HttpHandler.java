/**
 * 收到请求的处理
 *
 * @author hitol
 * @date 2019/7/20 11:21 AM
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
//        ByteBuf in = (ByteBuf) msg;
        String reqJson = msg.content().toString(CharsetUtil.UTF_8);
        logger.info("收到请求消息:[{}]", reqJson);

        String respJson;
        try {
            respJson = serviceDispatcher.doProcess(reqJson);
            logger.info("响应信息:[{}]", respJson);
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(respJson.getBytes()));

            HttpHeaders heads = response.headers();
            heads.add(HttpHeaderNames.CONTENT_TYPE, contentType + "; charset=UTF-8");
            heads.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
            heads.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);

            ctx.write(response);
        } catch (Exception e) {
            logger.error("请求失败，异常信息:[{}]", e.getMessage());
        }
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
