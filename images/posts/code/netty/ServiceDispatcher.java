/**
 * service调度
 *
 * @author hitol
 * @date 2019/7/20 5:51 PM
 */
@Component
public class ServiceDispatcher implements BeanFactoryAware {

    Logger logger = LoggerFactory.getLogger(getClass());

    private BeanFactory beanFactory = null;

    @Autowired
    private TblTxnInfCache txnInfCache;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public String doProcess(String reqJson) {
        CommonReq commonReq = JSONObject.parseObject(reqJson, CommonReq.class);
        checkReq(commonReq);

        Head head = commonReq.getHead();
        String txnNum = head.getTxnNum();
        String desSysId = head.getDesSysId();
        String reqSysId = head.getReqSysId();

        TblTxnInf tblTxnInf = txnInfCache.getCache(txnNum, desSysId, reqSysId);
        if (tblTxnInf == null) {
            throw new CommonException(ExceptionEnum.XXXX_XXXX);
        }

        String srvName = tblTxnInf.getSrvName();
        if (!beanFactory.containsBean(srvName)) {
            throw new CommonException(ExceptionEnum.XXXX_XXXX);
        }

        ICommonService service = (ICommonService) getBean(srvName);
        Object commonResp = service.doProcess(JSONObject.parseObject(reqJson,
                Object.class));
        return JSONObject.toJSONString(commonResp);
    }

    private Object getBean(String srvName) {
        return beanFactory.getBean(srvName);
    }

    /**
     * 对请求参数公共部分进行校验
     * Head中是否有参数缺失
     *
     * @param commonReq
     */
    private String checkReq(CommonReq commonReq) {
        String errorMsg = null;
        // TODO 校验
        return errorMsg;
    }

}
