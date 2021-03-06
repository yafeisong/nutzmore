package org.nutz.integration.dubbo;

import com.alibaba.dubbo.config.ServiceConfig;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.meta.IocObject;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

public class DubboManager {

    private static final Log log = Logs.get();

    protected Ioc ioc;

    protected Map<String, IocObject> iobjs;

    protected String beanName;

    public void _init() {
        this.init();
    }

    public void init() {
        log.debug("dubbo obj count=" + iobjs.size());
        for (Entry<String, IocObject> en : new HashSet<>(iobjs.entrySet())) {
            if (en.getValue().getType().isAssignableFrom(AnnotationBean.class)) {
                ioc.get(AnnotationBean.class, en.getKey());
            }
        }
        for (Entry<String, IocObject> en : iobjs.entrySet()) {
            if (ServiceConfig.class.isAssignableFrom(en.getValue().getType()))
                ioc.get(ServiceConfig.class, en.getKey());
        }
    }

    public void depose() {

    }

    public void setIoc(Ioc ioc) {
        this.ioc = ioc;
    }
}
