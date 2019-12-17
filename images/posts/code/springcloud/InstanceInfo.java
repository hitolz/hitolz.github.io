//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.netflix.appinfo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.inject.ProvidedBy;
import com.netflix.appinfo.AmazonInfo.MetaDataKey;
import com.netflix.appinfo.DataCenterInfo.Name;
import com.netflix.appinfo.providers.Archaius1VipAddressResolver;
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider;
import com.netflix.appinfo.providers.VipAddressResolver;
import com.netflix.discovery.converters.Auto;
import com.netflix.discovery.provider.Serializer;
import com.netflix.discovery.util.StringCache;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ProvidedBy(EurekaConfigBasedInstanceInfoProvider.class)
@Serializer("com.netflix.discovery.converters.EntityBodyConverter")
@XStreamAlias("instance")
@JsonRootName("instance")
public class InstanceInfo {
    private static final String VERSION_UNKNOWN = "unknown";
    private static final Logger logger = LoggerFactory.getLogger(InstanceInfo.class);
    public static final int DEFAULT_PORT = 7001;
    public static final int DEFAULT_SECURE_PORT = 7002;
    public static final int DEFAULT_COUNTRY_ID = 1;
    // 实例id
    private volatile String instanceId;
    // 实例名称
    private volatile String appName;
    @Auto
    // 应用所属群组
    private volatile String appGroupName;
    // ip地址
    private volatile String ipAddr;
    private static final String SID_DEFAULT = "na";
    /** @deprecated */
    @Deprecated
    private volatile String sid;
    // 端口号
    private volatile int port;
    // https的端口号
    private volatile int securePort;
    @Auto
    // 应用实例的首页url
    private volatile String homePageUrl;
    @Auto
    // 应用实例的状态页url
    private volatile String statusPageUrl;
    @Auto
    // 应用实例的健康检查url
    private volatile String healthCheckUrl;
    @Auto
    // 应用实例健康检查的https的url
    private volatile String secureHealthCheckUrl;
    @Auto
    // 虚拟ip地址
    private volatile String vipAddress;
    @Auto
    // https的虚拟ip地址
    private volatile String secureVipAddress;

    // dataCenter
    private volatile DataCenterInfo dataCenterInfo;
    // 主机名
    private volatile String hostName;
    // 实例状态  UP、DOWN、STARTING、OUT_OF_SERVICE、UNKNOWN
    private volatile InstanceInfo.InstanceStatus status;
    // 外界需要强制覆盖的状态值
    private volatile InstanceInfo.InstanceStatus overriddenStatus;
    @XStreamOmitField
    private volatile boolean isInstanceInfoDirty;
    // 租约信息
    private volatile LeaseInfo leaseInfo;
    @Auto
    private volatile Boolean isCoordinatingDiscoveryServer;
    // 元数据
    @XStreamAlias("metadata")
    private volatile Map<String, String> metadata;
    @Auto
    private volatile Long lastUpdatedTimestamp;
    @Auto
    private volatile Long lastDirtyTimestamp;
    @Auto
    private volatile InstanceInfo.ActionType actionType;

    @XStreamOmitField
    private String statusPageRelativeUrl;
    @XStreamOmitField
    private String statusPageExplicitUrl;
    @XStreamOmitField
    private String healthCheckRelativeUrl;
    @XStreamOmitField
    private String healthCheckSecureExplicitUrl;
    @XStreamOmitField
    private String vipAddressUnresolved;
    @XStreamOmitField
    private String secureVipAddressUnresolved;
    @XStreamOmitField
    private String healthCheckExplicitUrl;
    /** @deprecated */
    @Deprecated
    private volatile int countryId;
    private volatile boolean isSecurePortEnabled;
    private volatile boolean isUnsecurePortEnabled;

    @Auto
    private volatile String asgName;
    private String version;

    private InstanceInfo() {
        this.sid = "na";
        this.port = 7001;
        this.securePort = 7002;
        this.countryId = 1;
        this.isSecurePortEnabled = false;
        this.isUnsecurePortEnabled = true;
        this.status = InstanceInfo.InstanceStatus.UP;
        this.overriddenStatus = InstanceInfo.InstanceStatus.UNKNOWN;
        this.isInstanceInfoDirty = false;
        this.isCoordinatingDiscoveryServer = Boolean.FALSE;
        this.version = "unknown";
        this.metadata = new ConcurrentHashMap();
        this.lastUpdatedTimestamp = System.currentTimeMillis();
        this.lastDirtyTimestamp = this.lastUpdatedTimestamp;
    }

    @JsonCreator
    public InstanceInfo(@JsonProperty("instanceId") String instanceId, @JsonProperty("app") String appName, @JsonProperty("appGroupName") String appGroupName, @JsonProperty("ipAddr") String ipAddr, @JsonProperty("sid") String sid, @JsonProperty("port") InstanceInfo.PortWrapper port, @JsonProperty("securePort") InstanceInfo.PortWrapper securePort, @JsonProperty("homePageUrl") String homePageUrl, @JsonProperty("statusPageUrl") String statusPageUrl, @JsonProperty("healthCheckUrl") String healthCheckUrl, @JsonProperty("secureHealthCheckUrl") String secureHealthCheckUrl, @JsonProperty("vipAddress") String vipAddress, @JsonProperty("secureVipAddress") String secureVipAddress, @JsonProperty("countryId") int countryId, @JsonProperty("dataCenterInfo") DataCenterInfo dataCenterInfo, @JsonProperty("hostName") String hostName, @JsonProperty("status") InstanceInfo.InstanceStatus status, @JsonProperty("overriddenstatus") InstanceInfo.InstanceStatus overriddenStatus, @JsonProperty("overriddenStatus") InstanceInfo.InstanceStatus overriddenStatusAlt, @JsonProperty("leaseInfo") LeaseInfo leaseInfo, @JsonProperty("isCoordinatingDiscoveryServer") Boolean isCoordinatingDiscoveryServer, @JsonProperty("metadata") HashMap<String, String> metadata, @JsonProperty("lastUpdatedTimestamp") Long lastUpdatedTimestamp, @JsonProperty("lastDirtyTimestamp") Long lastDirtyTimestamp, @JsonProperty("actionType") InstanceInfo.ActionType actionType, @JsonProperty("asgName") String asgName) {
        this.sid = "na";
        this.port = 7001;
        this.securePort = 7002;
        this.countryId = 1;
        this.isSecurePortEnabled = false;
        this.isUnsecurePortEnabled = true;
        this.status = InstanceInfo.InstanceStatus.UP;
        this.overriddenStatus = InstanceInfo.InstanceStatus.UNKNOWN;
        this.isInstanceInfoDirty = false;
        this.isCoordinatingDiscoveryServer = Boolean.FALSE;
        this.version = "unknown";
        this.instanceId = instanceId;
        this.sid = sid;
        this.appName = StringCache.intern(appName);
        this.appGroupName = StringCache.intern(appGroupName);
        this.ipAddr = ipAddr;
        this.port = port == null ? 0 : port.getPort();
        this.isUnsecurePortEnabled = port != null && port.isEnabled();
        this.securePort = securePort == null ? 0 : securePort.getPort();
        this.isSecurePortEnabled = securePort != null && securePort.isEnabled();
        this.homePageUrl = homePageUrl;
        this.statusPageUrl = statusPageUrl;
        this.healthCheckUrl = healthCheckUrl;
        this.secureHealthCheckUrl = secureHealthCheckUrl;
        this.vipAddress = StringCache.intern(vipAddress);
        this.secureVipAddress = StringCache.intern(secureVipAddress);
        this.countryId = countryId;
        this.dataCenterInfo = dataCenterInfo;
        this.hostName = hostName;
        this.status = status;
        this.overriddenStatus = overriddenStatus == null ? overriddenStatusAlt : overriddenStatus;
        this.leaseInfo = leaseInfo;
        this.isCoordinatingDiscoveryServer = isCoordinatingDiscoveryServer;
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
        this.lastDirtyTimestamp = lastDirtyTimestamp;
        this.actionType = actionType;
        this.asgName = StringCache.intern(asgName);
        if (metadata == null) {
            this.metadata = Collections.emptyMap();
        } else if (metadata.size() == 1) {
            this.metadata = this.removeMetadataMapLegacyValues(metadata);
        } else {
            this.metadata = metadata;
        }

        if (sid == null) {
            this.sid = "na";
        }

    }

    private Map<String, String> removeMetadataMapLegacyValues(Map<String, String> metadata) {
        if ("java.util.Collections$EmptyMap".equals(metadata.get("@class"))) {
            metadata.remove("@class");
        } else if ("java.util.Collections$EmptyMap".equals(metadata.get("class"))) {
            metadata.remove("class");
        }

        return metadata;
    }

    public InstanceInfo(InstanceInfo ii) {
        this.sid = "na";
        this.port = 7001;
        this.securePort = 7002;
        this.countryId = 1;
        this.isSecurePortEnabled = false;
        this.isUnsecurePortEnabled = true;
        this.status = InstanceInfo.InstanceStatus.UP;
        this.overriddenStatus = InstanceInfo.InstanceStatus.UNKNOWN;
        this.isInstanceInfoDirty = false;
        this.isCoordinatingDiscoveryServer = Boolean.FALSE;
        this.version = "unknown";
        this.instanceId = ii.instanceId;
        this.appName = ii.appName;
        this.appGroupName = ii.appGroupName;
        this.ipAddr = ii.ipAddr;
        this.sid = ii.sid;
        this.port = ii.port;
        this.securePort = ii.securePort;
        this.homePageUrl = ii.homePageUrl;
        this.statusPageUrl = ii.statusPageUrl;
        this.healthCheckUrl = ii.healthCheckUrl;
        this.secureHealthCheckUrl = ii.secureHealthCheckUrl;
        this.vipAddress = ii.vipAddress;
        this.secureVipAddress = ii.secureVipAddress;
        this.statusPageRelativeUrl = ii.statusPageRelativeUrl;
        this.statusPageExplicitUrl = ii.statusPageExplicitUrl;
        this.healthCheckRelativeUrl = ii.healthCheckRelativeUrl;
        this.healthCheckSecureExplicitUrl = ii.healthCheckSecureExplicitUrl;
        this.vipAddressUnresolved = ii.vipAddressUnresolved;
        this.secureVipAddressUnresolved = ii.secureVipAddressUnresolved;
        this.healthCheckExplicitUrl = ii.healthCheckExplicitUrl;
        this.countryId = ii.countryId;
        this.isSecurePortEnabled = ii.isSecurePortEnabled;
        this.isUnsecurePortEnabled = ii.isUnsecurePortEnabled;
        this.dataCenterInfo = ii.dataCenterInfo;
        this.hostName = ii.hostName;
        this.status = ii.status;
        this.overriddenStatus = ii.overriddenStatus;
        this.isInstanceInfoDirty = ii.isInstanceInfoDirty;
        this.leaseInfo = ii.leaseInfo;
        this.isCoordinatingDiscoveryServer = ii.isCoordinatingDiscoveryServer;
        this.metadata = ii.metadata;
        this.lastUpdatedTimestamp = ii.lastUpdatedTimestamp;
        this.lastDirtyTimestamp = ii.lastDirtyTimestamp;
        this.actionType = ii.actionType;
        this.asgName = ii.asgName;
        this.version = ii.version;
    }

    public int hashCode() {
        String id = this.getId();
        return id == null ? 31 : id.hashCode() + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            InstanceInfo other = (InstanceInfo)obj;
            String id = this.getId();
            if (id == null) {
                if (other.getId() != null) {
                    return false;
                }
            } else if (!id.equals(other.getId())) {
                return false;
            }

            return true;
        }
    }

    public String getInstanceId() {
        return this.instanceId;
    }

    @JsonProperty("app")
    public String getAppName() {
        return this.appName;
    }

    public String getAppGroupName() {
        return this.appGroupName;
    }

    public String getHostName() {
        return this.hostName;
    }

    /** @deprecated */
    @Deprecated
    public void setSID(String sid) {
        this.sid = sid;
        this.setIsDirty();
    }

    /** @deprecated */
    @JsonProperty("sid")
    @Deprecated
    public String getSID() {
        return this.sid;
    }

    @JsonIgnore
    public String getId() {
        if (this.instanceId != null && !this.instanceId.isEmpty()) {
            return this.instanceId;
        } else {
            if (this.dataCenterInfo instanceof UniqueIdentifier) {
                String uniqueId = ((UniqueIdentifier)this.dataCenterInfo).getId();
                if (uniqueId != null && !uniqueId.isEmpty()) {
                    return uniqueId;
                }
            }

            return this.hostName;
        }
    }

    @JsonProperty("ipAddr")
    public String getIPAddr() {
        return this.ipAddr;
    }

    @JsonIgnore
    public int getPort() {
        return this.port;
    }

    public InstanceInfo.InstanceStatus getStatus() {
        return this.status;
    }

    public InstanceInfo.InstanceStatus getOverriddenStatus() {
        return this.overriddenStatus;
    }

    public DataCenterInfo getDataCenterInfo() {
        return this.dataCenterInfo;
    }

    public LeaseInfo getLeaseInfo() {
        return this.leaseInfo;
    }

    public void setLeaseInfo(LeaseInfo info) {
        this.leaseInfo = info;
    }

    public Map<String, String> getMetadata() {
        return this.metadata;
    }

    /** @deprecated */
    @Deprecated
    public int getCountryId() {
        return this.countryId;
    }

    @JsonIgnore
    public int getSecurePort() {
        return this.securePort;
    }

    @JsonIgnore
    public boolean isPortEnabled(InstanceInfo.PortType type) {
        return type == InstanceInfo.PortType.SECURE ? this.isSecurePortEnabled : this.isUnsecurePortEnabled;
    }

    public long getLastUpdatedTimestamp() {
        return this.lastUpdatedTimestamp;
    }

    public void setLastUpdatedTimestamp() {
        this.lastUpdatedTimestamp = System.currentTimeMillis();
    }

    public String getHomePageUrl() {
        return this.homePageUrl;
    }

    public String getStatusPageUrl() {
        return this.statusPageUrl;
    }

    @JsonIgnore
    public Set<String> getHealthCheckUrls() {
        Set<String> healthCheckUrlSet = new LinkedHashSet();
        if (this.isUnsecurePortEnabled && this.healthCheckUrl != null && !this.healthCheckUrl.isEmpty()) {
            healthCheckUrlSet.add(this.healthCheckUrl);
        }

        if (this.isSecurePortEnabled && this.secureHealthCheckUrl != null && !this.secureHealthCheckUrl.isEmpty()) {
            healthCheckUrlSet.add(this.secureHealthCheckUrl);
        }

        return healthCheckUrlSet;
    }

    public String getHealthCheckUrl() {
        return this.healthCheckUrl;
    }

    public String getSecureHealthCheckUrl() {
        return this.secureHealthCheckUrl;
    }

    @JsonProperty("vipAddress")
    public String getVIPAddress() {
        return this.vipAddress;
    }

    public String getSecureVipAddress() {
        return this.secureVipAddress;
    }

    public Long getLastDirtyTimestamp() {
        return this.lastDirtyTimestamp;
    }

    public void setLastDirtyTimestamp(Long lastDirtyTimestamp) {
        this.lastDirtyTimestamp = lastDirtyTimestamp;
    }

    public synchronized InstanceInfo.InstanceStatus setStatus(InstanceInfo.InstanceStatus status) {
        if (this.status != status) {
            InstanceInfo.InstanceStatus prev = this.status;
            this.status = status;
            this.setIsDirty();
            return prev;
        } else {
            return null;
        }
    }

    public synchronized void setStatusWithoutDirty(InstanceInfo.InstanceStatus status) {
        if (this.status != status) {
            this.status = status;
        }

    }

    public synchronized void setOverriddenStatus(InstanceInfo.InstanceStatus status) {
        if (this.overriddenStatus != status) {
            this.overriddenStatus = status;
        }

    }

    @JsonIgnore
    public boolean isDirty() {
        return this.isInstanceInfoDirty;
    }

    public synchronized Long isDirtyWithTime() {
        return this.isInstanceInfoDirty ? this.lastDirtyTimestamp : null;
    }

    /** @deprecated */
    @Deprecated
    public synchronized void setIsDirty(boolean isDirty) {
        if (isDirty) {
            this.setIsDirty();
        } else {
            this.isInstanceInfoDirty = false;
        }

    }

    // 设置client最后一次更新时间
    public synchronized void setIsDirty() {
        this.isInstanceInfoDirty = true;
        this.lastDirtyTimestamp = System.currentTimeMillis();
    }

    public synchronized long setIsDirtyWithTime() {
        this.setIsDirty();
        return this.lastDirtyTimestamp;
    }

    public synchronized void unsetIsDirty(long unsetDirtyTimestamp) {
        if (this.lastDirtyTimestamp <= unsetDirtyTimestamp) {
            this.isInstanceInfoDirty = false;
        }

    }

    public void setIsCoordinatingDiscoveryServer() {
        String instanceId = this.getId();
        if (instanceId != null && instanceId.equals(ApplicationInfoManager.getInstance().getInfo().getId())) {
            this.isCoordinatingDiscoveryServer = Boolean.TRUE;
        } else {
            this.isCoordinatingDiscoveryServer = Boolean.FALSE;
        }

    }

    @JsonProperty("isCoordinatingDiscoveryServer")
    public Boolean isCoordinatingDiscoveryServer() {
        return this.isCoordinatingDiscoveryServer;
    }

    public InstanceInfo.ActionType getActionType() {
        return this.actionType;
    }

    public void setActionType(InstanceInfo.ActionType actionType) {
        this.actionType = actionType;
    }

    @JsonProperty("asgName")
    public String getASGName() {
        return this.asgName;
    }

    /** @deprecated */
    @Deprecated
    @JsonIgnore
    public String getVersion() {
        return this.version;
    }

    synchronized void registerRuntimeMetadata(Map<String, String> runtimeMetadata) {
        this.metadata.putAll(runtimeMetadata);
        this.setIsDirty();
    }

    public static String getZone(String[] availZones, InstanceInfo myInfo) {
        String instanceZone = availZones != null && availZones.length != 0 ? availZones[0] : "default";
        if (myInfo != null && myInfo.getDataCenterInfo().getName() == Name.Amazon) {
            String awsInstanceZone = ((AmazonInfo)myInfo.getDataCenterInfo()).get(MetaDataKey.availabilityZone);
            if (awsInstanceZone != null) {
                instanceZone = awsInstanceZone;
            }
        }

        return instanceZone;
    }

    public static enum ActionType {
        ADDED,
        MODIFIED,
        DELETED;

        private ActionType() {
        }
    }

    public static final class Builder {
        private static final String COLON = ":";
        private static final String HTTPS_PROTOCOL = "https://";
        private static final String HTTP_PROTOCOL = "http://";
        private final Function<String, String> intern;
        @XStreamOmitField
        private InstanceInfo result;
        @XStreamOmitField
        private final VipAddressResolver vipAddressResolver;
        private String namespace;

        private Builder(InstanceInfo result, VipAddressResolver vipAddressResolver, Function<String, String> intern) {
            this.vipAddressResolver = vipAddressResolver;
            this.result = result;
            this.intern = intern != null ? intern : StringCache::intern;
        }

        public Builder(InstanceInfo instanceInfo) {
            this(instanceInfo, InstanceInfo.Builder.LazyHolder.DEFAULT_VIP_ADDRESS_RESOLVER, (Function)null);
        }

        public static InstanceInfo.Builder newBuilder() {
            return new InstanceInfo.Builder(new InstanceInfo(), InstanceInfo.Builder.LazyHolder.DEFAULT_VIP_ADDRESS_RESOLVER, (Function)null);
        }

        public static InstanceInfo.Builder newBuilder(Function<String, String> intern) {
            return new InstanceInfo.Builder(new InstanceInfo(), InstanceInfo.Builder.LazyHolder.DEFAULT_VIP_ADDRESS_RESOLVER, intern);
        }

        public static InstanceInfo.Builder newBuilder(VipAddressResolver vipAddressResolver) {
            return new InstanceInfo.Builder(new InstanceInfo(), vipAddressResolver, (Function)null);
        }

        public InstanceInfo.Builder setInstanceId(String instanceId) {
            this.result.instanceId = instanceId;
            return this;
        }

        public InstanceInfo.Builder setAppName(String appName) {
            this.result.appName = (String)this.intern.apply(appName.toUpperCase(Locale.ROOT));
            return this;
        }

        public InstanceInfo.Builder setAppNameForDeser(String appName) {
            this.result.appName = appName;
            return this;
        }

        public InstanceInfo.Builder setAppGroupName(String appGroupName) {
            if (appGroupName != null) {
                this.result.appGroupName = (String)this.intern.apply(appGroupName.toUpperCase(Locale.ROOT));
            } else {
                this.result.appGroupName = null;
            }

            return this;
        }

        public InstanceInfo.Builder setAppGroupNameForDeser(String appGroupName) {
            this.result.appGroupName = appGroupName;
            return this;
        }

        public InstanceInfo.Builder setHostName(String hostName) {
            if (hostName != null && !hostName.isEmpty()) {
                String existingHostName = this.result.hostName;
                this.result.hostName = hostName;
                if (existingHostName != null && !hostName.equals(existingHostName)) {
                    this.refreshStatusPageUrl().refreshHealthCheckUrl().refreshVIPAddress().refreshSecureVIPAddress();
                }

                return this;
            } else {
                InstanceInfo.logger.warn("Passed in hostname is blank, not setting it");
                return this;
            }
        }

        public InstanceInfo.Builder setStatus(InstanceInfo.InstanceStatus status) {
            this.result.status = status;
            return this;
        }

        public InstanceInfo.Builder setOverriddenStatus(InstanceInfo.InstanceStatus status) {
            this.result.overriddenStatus = status;
            return this;
        }

        public InstanceInfo.Builder setIPAddr(String ip) {
            this.result.ipAddr = ip;
            return this;
        }

        /** @deprecated */
        @Deprecated
        public InstanceInfo.Builder setSID(String sid) {
            this.result.sid = sid;
            return this;
        }

        public InstanceInfo.Builder setPort(int port) {
            this.result.port = port;
            return this;
        }

        public InstanceInfo.Builder setSecurePort(int port) {
            this.result.securePort = port;
            return this;
        }

        public InstanceInfo.Builder enablePort(InstanceInfo.PortType type, boolean isEnabled) {
            if (type == InstanceInfo.PortType.SECURE) {
                this.result.isSecurePortEnabled = isEnabled;
            } else {
                this.result.isUnsecurePortEnabled = isEnabled;
            }

            return this;
        }

        /** @deprecated */
        @Deprecated
        public InstanceInfo.Builder setCountryId(int id) {
            this.result.countryId = id;
            return this;
        }

        public InstanceInfo.Builder setHomePageUrl(String relativeUrl, String explicitUrl) {
            String hostNameInterpolationExpression = "${" + this.namespace + "hostname}";
            if (explicitUrl != null) {
                this.result.homePageUrl = explicitUrl.replace(hostNameInterpolationExpression, this.result.hostName);
            } else if (relativeUrl != null) {
                this.result.homePageUrl = "http://" + this.result.hostName + ":" + this.result.port + relativeUrl;
            }

            return this;
        }

        public InstanceInfo.Builder setHomePageUrlForDeser(String homePageUrl) {
            this.result.homePageUrl = homePageUrl;
            return this;
        }

        public InstanceInfo.Builder setStatusPageUrl(String relativeUrl, String explicitUrl) {
            String hostNameInterpolationExpression = "${" + this.namespace + "hostname}";
            this.result.statusPageRelativeUrl = relativeUrl;
            this.result.statusPageExplicitUrl = explicitUrl;
            if (explicitUrl != null) {
                this.result.statusPageUrl = explicitUrl.replace(hostNameInterpolationExpression, this.result.hostName);
            } else if (relativeUrl != null) {
                this.result.statusPageUrl = "http://" + this.result.hostName + ":" + this.result.port + relativeUrl;
            }

            return this;
        }

        public InstanceInfo.Builder setStatusPageUrlForDeser(String statusPageUrl) {
            this.result.statusPageUrl = statusPageUrl;
            return this;
        }

        public InstanceInfo.Builder setHealthCheckUrls(String relativeUrl, String explicitUrl, String secureExplicitUrl) {
            String hostNameInterpolationExpression = "${" + this.namespace + "hostname}";
            this.result.healthCheckRelativeUrl = relativeUrl;
            this.result.healthCheckExplicitUrl = explicitUrl;
            this.result.healthCheckSecureExplicitUrl = secureExplicitUrl;
            if (explicitUrl != null) {
                this.result.healthCheckUrl = explicitUrl.replace(hostNameInterpolationExpression, this.result.hostName);
            } else if (this.result.isUnsecurePortEnabled) {
                this.result.healthCheckUrl = "http://" + this.result.hostName + ":" + this.result.port + relativeUrl;
            }

            if (secureExplicitUrl != null) {
                this.result.secureHealthCheckUrl = secureExplicitUrl.replace(hostNameInterpolationExpression, this.result.hostName);
            } else if (this.result.isSecurePortEnabled) {
                this.result.secureHealthCheckUrl = "https://" + this.result.hostName + ":" + this.result.securePort + relativeUrl;
            }

            return this;
        }

        public InstanceInfo.Builder setHealthCheckUrlsForDeser(String healthCheckUrl, String secureHealthCheckUrl) {
            if (healthCheckUrl != null) {
                this.result.healthCheckUrl = healthCheckUrl;
            }

            if (secureHealthCheckUrl != null) {
                this.result.secureHealthCheckUrl = secureHealthCheckUrl;
            }

            return this;
        }

        public InstanceInfo.Builder setVIPAddress(String vipAddress) {
            this.result.vipAddressUnresolved = (String)this.intern.apply(vipAddress);
            this.result.vipAddress = (String)this.intern.apply(this.vipAddressResolver.resolveDeploymentContextBasedVipAddresses(vipAddress));
            return this;
        }

        public InstanceInfo.Builder setVIPAddressDeser(String vipAddress) {
            this.result.vipAddress = (String)this.intern.apply(vipAddress);
            return this;
        }

        public InstanceInfo.Builder setSecureVIPAddress(String secureVIPAddress) {
            this.result.secureVipAddressUnresolved = (String)this.intern.apply(secureVIPAddress);
            this.result.secureVipAddress = (String)this.intern.apply(this.vipAddressResolver.resolveDeploymentContextBasedVipAddresses(secureVIPAddress));
            return this;
        }

        public InstanceInfo.Builder setSecureVIPAddressDeser(String secureVIPAddress) {
            this.result.secureVipAddress = (String)this.intern.apply(secureVIPAddress);
            return this;
        }

        public InstanceInfo.Builder setDataCenterInfo(DataCenterInfo datacenter) {
            this.result.dataCenterInfo = datacenter;
            return this;
        }

        public InstanceInfo.Builder setLeaseInfo(LeaseInfo info) {
            this.result.leaseInfo = info;
            return this;
        }

        public InstanceInfo.Builder add(String key, String val) {
            this.result.metadata.put(key, val);
            return this;
        }

        public InstanceInfo.Builder setMetadata(Map<String, String> mt) {
            this.result.metadata = mt;
            return this;
        }

        public InstanceInfo getRawInstance() {
            return this.result;
        }

        public InstanceInfo build() {
            if (!this.isInitialized()) {
                throw new IllegalStateException("name is required!");
            } else {
                return this.result;
            }
        }

        public boolean isInitialized() {
            return this.result.appName != null;
        }

        public InstanceInfo.Builder setASGName(String asgName) {
            this.result.asgName = (String)this.intern.apply(asgName);
            return this;
        }

        private InstanceInfo.Builder refreshStatusPageUrl() {
            this.setStatusPageUrl(this.result.statusPageRelativeUrl, this.result.statusPageExplicitUrl);
            return this;
        }

        private InstanceInfo.Builder refreshHealthCheckUrl() {
            this.setHealthCheckUrls(this.result.healthCheckRelativeUrl, this.result.healthCheckExplicitUrl, this.result.healthCheckSecureExplicitUrl);
            return this;
        }

        private InstanceInfo.Builder refreshSecureVIPAddress() {
            this.setSecureVIPAddress(this.result.secureVipAddressUnresolved);
            return this;
        }

        private InstanceInfo.Builder refreshVIPAddress() {
            this.setVIPAddress(this.result.vipAddressUnresolved);
            return this;
        }

        public InstanceInfo.Builder setIsCoordinatingDiscoveryServer(boolean isCoordinatingDiscoveryServer) {
            this.result.isCoordinatingDiscoveryServer = isCoordinatingDiscoveryServer;
            return this;
        }

        public InstanceInfo.Builder setLastUpdatedTimestamp(long lastUpdatedTimestamp) {
            this.result.lastUpdatedTimestamp = lastUpdatedTimestamp;
            return this;
        }

        public InstanceInfo.Builder setLastDirtyTimestamp(long lastDirtyTimestamp) {
            this.result.lastDirtyTimestamp = lastDirtyTimestamp;
            return this;
        }

        public InstanceInfo.Builder setActionType(InstanceInfo.ActionType actionType) {
            this.result.actionType = actionType;
            return this;
        }

        public InstanceInfo.Builder setNamespace(String namespace) {
            this.namespace = namespace.endsWith(".") ? namespace : namespace + ".";
            return this;
        }

        private static final class LazyHolder {
            private static final VipAddressResolver DEFAULT_VIP_ADDRESS_RESOLVER = new Archaius1VipAddressResolver();

            private LazyHolder() {
            }
        }
    }

    public static enum PortType {
        SECURE,
        UNSECURE;

        private PortType() {
        }
    }

    public static enum InstanceStatus {
        UP,
        DOWN,
        STARTING,
        OUT_OF_SERVICE,
        UNKNOWN;

        private InstanceStatus() {
        }

        public static InstanceInfo.InstanceStatus toEnum(String s) {
            if (s != null) {
                try {
                    return valueOf(s.toUpperCase());
                } catch (IllegalArgumentException var2) {
                    InstanceInfo.logger.debug("illegal argument supplied to InstanceStatus.valueOf: {}, defaulting to {}", s, UNKNOWN);
                }
            }

            return UNKNOWN;
        }
    }

    public static class PortWrapper {
        private final boolean enabled;
        private final int port;

        @JsonCreator
        public PortWrapper(@JsonProperty("@enabled") boolean enabled, @JsonProperty("$") int port) {
            this.enabled = enabled;
            this.port = port;
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public int getPort() {
            return this.port;
        }
    }
}
