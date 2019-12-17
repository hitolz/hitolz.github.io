@Produces({"application/xml", "application/json"})
public class InstanceResource {
    private static final Logger logger = LoggerFactory.getLogger(InstanceResource.class);
    private final PeerAwareInstanceRegistry registry;
    private final EurekaServerConfig serverConfig;
    private final String id;
    private final ApplicationResource app;

    InstanceResource(ApplicationResource app, String id, EurekaServerConfig serverConfig, PeerAwareInstanceRegistry registry) {
        this.app = app;
        this.id = id;
        this.serverConfig = serverConfig;
        this.registry = registry;
    }

    @GET
    public Response getInstanceInfo() {
        InstanceInfo appInfo = this.registry.getInstanceByAppAndId(this.app.getName(), this.id);
        if (appInfo != null) {
            logger.debug("Found: {} - {}", this.app.getName(), this.id);
            return Response.ok(appInfo).build();
        } else {
            logger.debug("Not Found: {} - {}", this.app.getName(), this.id);
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    /**
     * 服务租约
     * 续约不成功，返回404
     *
     * @param isReplication 是否复制模式
     * @param overriddenStatus 外界需要覆盖的状态值
     * @param status
     * @param lastDirtyTimestamp
     * @return
     */
    @PUT
    public Response renewLease(
            @HeaderParam("x-netflix-discovery-replication") String isReplication,
            @QueryParam("overriddenstatus") String overriddenStatus,
            @QueryParam("status") String status,
            @QueryParam("lastDirtyTimestamp") String lastDirtyTimestamp) {

        boolean isFromReplicaNode = "true".equals(isReplication);
        boolean isSuccess = this.registry.renew(this.app.getName(), this.id, isFromReplicaNode);

        if (!isSuccess) {
            logger.warn("Not Found (Renew): {} - {}", this.app.getName(), this.id);
            return Response.status(Status.NOT_FOUND).build();
        } else {
            Response response = null;
            if (lastDirtyTimestamp != null && this.serverConfig.shouldSyncWhenTimestampDiffers()) {
                response = this.validateDirtyTimestamp(Long.valueOf(lastDirtyTimestamp), isFromReplicaNode);
                if (response.getStatus() == Status.NOT_FOUND.getStatusCode()
                        && overriddenStatus != null
                        && !InstanceStatus.UNKNOWN.name().equals(overriddenStatus)
                        && isFromReplicaNode) {
                    this.registry.storeOverriddenStatusIfRequired(this.app.getAppName(), this.id, InstanceStatus.valueOf(overriddenStatus));
                }
            } else {
                response = Response.ok().build();
            }

            logger.debug("Found (Renew): {} - {}; reply status={}", new Object[]{this.app.getName(), this.id, response.getStatus()});
            return response;
        }
    }

    @PUT
    @Path("status")
    public Response statusUpdate(@QueryParam("value") String newStatus, @HeaderParam("x-netflix-discovery-replication") String isReplication, @QueryParam("lastDirtyTimestamp") String lastDirtyTimestamp) {
        try {
            if (this.registry.getInstanceByAppAndId(this.app.getName(), this.id) == null) {
                logger.warn("Instance not found: {}/{}", this.app.getName(), this.id);
                return Response.status(Status.NOT_FOUND).build();
            } else {
                boolean isSuccess = this.registry.statusUpdate(this.app.getName(), this.id, InstanceStatus.valueOf(newStatus), lastDirtyTimestamp, "true".equals(isReplication));
                if (isSuccess) {
                    logger.info("Status updated: {} - {} - {}", new Object[]{this.app.getName(), this.id, newStatus});
                    return Response.ok().build();
                } else {
                    logger.warn("Unable to update status: {} - {} - {}", new Object[]{this.app.getName(), this.id, newStatus});
                    return Response.serverError().build();
                }
            }
        } catch (Throwable var5) {
            logger.error("Error updating instance {} for status {}", this.id, newStatus);
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("status")
    public Response deleteStatusUpdate(@HeaderParam("x-netflix-discovery-replication") String isReplication, @QueryParam("value") String newStatusValue, @QueryParam("lastDirtyTimestamp") String lastDirtyTimestamp) {
        try {
            if (this.registry.getInstanceByAppAndId(this.app.getName(), this.id) == null) {
                logger.warn("Instance not found: {}/{}", this.app.getName(), this.id);
                return Response.status(Status.NOT_FOUND).build();
            } else {
                InstanceStatus newStatus = newStatusValue == null ? InstanceStatus.UNKNOWN : InstanceStatus.valueOf(newStatusValue);
                boolean isSuccess = this.registry.deleteStatusOverride(this.app.getName(), this.id, newStatus, lastDirtyTimestamp, "true".equals(isReplication));
                if (isSuccess) {
                    logger.info("Status override removed: {} - {}", this.app.getName(), this.id);
                    return Response.ok().build();
                } else {
                    logger.warn("Unable to remove status override: {} - {}", this.app.getName(), this.id);
                    return Response.serverError().build();
                }
            }
        } catch (Throwable var6) {
            logger.error("Error removing instance's {} status override", this.id);
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("metadata")
    public Response updateMetadata(@Context UriInfo uriInfo) {
        try {
            InstanceInfo instanceInfo = this.registry.getInstanceByAppAndId(this.app.getName(), this.id);
            if (instanceInfo == null) {
                logger.error("Cannot find instance while updating metadata for instance {}", this.id);
                return Response.serverError().build();
            } else {
                MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
                Set<Entry<String, List<String>>> entrySet = queryParams.entrySet();
                Map<String, String> metadataMap = instanceInfo.getMetadata();
                if (Collections.emptyMap().getClass().equals(metadataMap.getClass())) {
                    metadataMap = new ConcurrentHashMap();
                    Builder builder = new Builder(instanceInfo);
                    builder.setMetadata((Map) metadataMap);
                    instanceInfo = builder.build();
                }

                Iterator var9 = entrySet.iterator();

                while (var9.hasNext()) {
                    Entry<String, List<String>> entry = (Entry) var9.next();
                    ((Map) metadataMap).put(entry.getKey(), ((List) entry.getValue()).get(0));
                }

                this.registry.register(instanceInfo, false);
                return Response.ok().build();
            }
        } catch (Throwable var8) {
            logger.error("Error updating metadata for instance {}", this.id, var8);
            return Response.serverError().build();
        }
    }

    @DELETE
    public Response cancelLease(@HeaderParam("x-netflix-discovery-replication") String isReplication) {
        try {
            boolean isSuccess = this.registry.cancel(this.app.getName(), this.id, "true".equals(isReplication));
            if (isSuccess) {
                logger.debug("Found (Cancel): {} - {}", this.app.getName(), this.id);
                return Response.ok().build();
            } else {
                logger.info("Not Found (Cancel): {} - {}", this.app.getName(), this.id);
                return Response.status(Status.NOT_FOUND).build();
            }
        } catch (Throwable var3) {
            logger.error("Error (cancel): {} - {}", new Object[]{this.app.getName(), this.id, var3});
            return Response.serverError().build();
        }
    }

    /**
     * 校验lastDirtyTimestamp
     *
     * 如果lastDirtyTimestamp参数大于server本地instance的lastDirtyTimestamp值，则response返回404；
     * 如果是server本地大于lastDirtyTimestamp参数，不是replication模式则返回200，replication模式，则返回409 Conflict，让调用方同步自己的数据
     *
     * @param lastDirtyTimestamp 该instance在客户端最后一次被更新的时间戳
     * @param isReplication 复制模式
     * @return
     */
    private Response validateDirtyTimestamp(Long lastDirtyTimestamp, boolean isReplication) {
        InstanceInfo appInfo = this.registry.getInstanceByAppAndId(this.app.getName(), this.id, false);
        if (appInfo != null && lastDirtyTimestamp != null
                && !lastDirtyTimestamp.equals(appInfo.getLastDirtyTimestamp())) {
            Object[] args = new Object[]{this.id, appInfo.getLastDirtyTimestamp(), lastDirtyTimestamp, isReplication};
            if (lastDirtyTimestamp > appInfo.getLastDirtyTimestamp()) {
                logger.debug("Time to sync, since the last dirty timestamp differs - ReplicationInstance id : {},Registry : {} Incoming: {} Replication: {}", args);
                return Response.status(Status.NOT_FOUND).build();
            }

            if (appInfo.getLastDirtyTimestamp() > lastDirtyTimestamp) {
                if (isReplication) {
                    logger.debug("Time to sync, since the last dirty timestamp differs - ReplicationInstance id : {},Registry : {} Incoming: {} Replication: {}", args);
                    return Response.status(Status.CONFLICT).entity(appInfo).build();
                }

                return Response.ok().build();
            }
        }

        return Response.ok().build();
    }
}
