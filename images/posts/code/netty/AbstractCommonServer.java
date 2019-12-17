public abstract class AbstractCommonServer {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	ServerBootstrap serverBootstrap;

	EventLoopGroup bossGroup;

	EventLoopGroup workerGroup;

	public abstract ChannelInitializer getChannel();


	public void start(int port){
		int processorsNum = Runtime.getRuntime().availableProcessors();
		int processors = processorsNum * 2;
		bossGroup = new NioEventLoopGroup(processors);
		workerGroup = new NioEventLoopGroup(processors);
		serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(bossGroup,workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(getChannel())
				.option(ChannelOption.SO_BACKLOG,128)
				.childOption(ChannelOption.SO_KEEPALIVE,true);

		try {
			ChannelFuture future = serverBootstrap.bind(port).sync();
			logger.info("class [{}] started and listen on [{}]",getClass(),
					future.channel().localAddress());

		} catch (InterruptedException e) {
			logger.info("出现异常");
			close();
		}
	}

	/**
	 * 关闭服务器方法
	 */
	@PreDestroy
	public void close(){
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}
}
