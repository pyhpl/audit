package org.ljl.look.audit.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ConstConfig {

    /** audit state */
    public static final short WAITING_AUDIT_STATE = (short) 0;
    public static final short PASS_AUDIT_STATE = (short) 1;
    public static final short FAIL_AUDIT_STATE = (short) -1;

    public static final String WAITING_AUDIT_STATE_STR = "wait";

    /** valid */
    public static final short VALID = (short) 1;
    public static final short UNVALID = (short) 0;

    /** page info */
    public static final String PAGE_INFO_JSON_STR = "pageInfoJsonStr";
    public static final String PAGE_NUM = "pageNum";
    public static final String PAGE_SIZE = "pageSize";
    public static final String DEFAULT_PAGE_NUM = "1";
    public static final String DEFAULT_PAGE_SIZE = "10";

    /** rabbit mq */
    public static final String QUEUE_TOPIC_FOCUS = "queueTopicFocus";
    public static final String QUEUE_ACTIVITY_AUDIT = "queueActivityAudit";
    public static final String QUEUE_TOPIC_AUDIT = "queueTopicAudit";
}
