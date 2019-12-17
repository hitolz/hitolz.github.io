//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.netflix.appinfo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@JsonRootName("leaseInfo")
public class LeaseInfo {
    public static final int DEFAULT_LEASE_RENEWAL_INTERVAL = 30;
    public static final int DEFAULT_LEASE_DURATION = 90;
    // client端续约的间隔周期
    private int renewalIntervalInSecs;
    // client端需要设定的租约的有效时长
    private int durationInSecs;
    // server端设置的该租约第一次注册的时间
    private long registrationTimestamp;
    // server端设置的该租约最后一次续约的时间
    private long lastRenewalTimestamp;
    // server端设置的该租约被剔除的时间
    private long evictionTimestamp;
    // server端设置的该服务实例标记为up的时间
    private long serviceUpTimestamp;

    private LeaseInfo() {
        this.renewalIntervalInSecs = 30;
        this.durationInSecs = 90;
    }

    @JsonCreator
    public LeaseInfo(@JsonProperty("renewalIntervalInSecs") int renewalIntervalInSecs, @JsonProperty("durationInSecs") int durationInSecs, @JsonProperty("registrationTimestamp") long registrationTimestamp, @JsonProperty("lastRenewalTimestamp") Long lastRenewalTimestamp, @JsonProperty("renewalTimestamp") long lastRenewalTimestampLegacy, @JsonProperty("evictionTimestamp") long evictionTimestamp, @JsonProperty("serviceUpTimestamp") long serviceUpTimestamp) {
        this.renewalIntervalInSecs = 30;
        this.durationInSecs = 90;
        this.renewalIntervalInSecs = renewalIntervalInSecs;
        this.durationInSecs = durationInSecs;
        this.registrationTimestamp = registrationTimestamp;
        this.evictionTimestamp = evictionTimestamp;
        this.serviceUpTimestamp = serviceUpTimestamp;
        if (lastRenewalTimestamp == null) {
            this.lastRenewalTimestamp = lastRenewalTimestampLegacy;
        } else {
            this.lastRenewalTimestamp = lastRenewalTimestamp;
        }

    }

    public long getRegistrationTimestamp() {
        return this.registrationTimestamp;
    }

    @JsonProperty("lastRenewalTimestamp")
    public long getRenewalTimestamp() {
        return this.lastRenewalTimestamp;
    }

    public long getEvictionTimestamp() {
        return this.evictionTimestamp;
    }

    public long getServiceUpTimestamp() {
        return this.serviceUpTimestamp;
    }

    public int getRenewalIntervalInSecs() {
        return this.renewalIntervalInSecs;
    }

    public int getDurationInSecs() {
        return this.durationInSecs;
    }

    public static final class Builder {
        @XStreamOmitField
        private LeaseInfo result = new LeaseInfo();

        private Builder() {
        }

        public static LeaseInfo.Builder newBuilder() {
            return new LeaseInfo.Builder();
        }

        public LeaseInfo.Builder setRegistrationTimestamp(long ts) {
            this.result.registrationTimestamp = ts;
            return this;
        }

        public LeaseInfo.Builder setRenewalTimestamp(long ts) {
            this.result.lastRenewalTimestamp = ts;
            return this;
        }

        public LeaseInfo.Builder setEvictionTimestamp(long ts) {
            this.result.evictionTimestamp = ts;
            return this;
        }

        public LeaseInfo.Builder setServiceUpTimestamp(long ts) {
            this.result.serviceUpTimestamp = ts;
            return this;
        }

        public LeaseInfo.Builder setDurationInSecs(int d) {
            if (d <= 0) {
                this.result.durationInSecs = 90;
            } else {
                this.result.durationInSecs = d;
            }

            return this;
        }

        public LeaseInfo.Builder setRenewalIntervalInSecs(int i) {
            if (i <= 0) {
                this.result.renewalIntervalInSecs = 30;
            } else {
                this.result.renewalIntervalInSecs = i;
            }

            return this;
        }

        public LeaseInfo build() {
            return this.result;
        }
    }
}
