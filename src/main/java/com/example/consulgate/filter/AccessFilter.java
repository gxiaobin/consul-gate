package com.example.consulgate.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.Arrays;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

;


/**
 * <过滤>
 *
 * @author Administrator
 */
public class AccessFilter extends ZuulFilter {
    private static final Logger logger = LogManager.getLogger(AccessFilter.class);
    //来源:后台管理系统
    //header携带字段
    private static final String HEADER_FLATFORM = "platform";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Value("${accessfilter.adminurl}")
    String adminUrl;
    @Value("${accessfilter.memberurl}")
    String memberUrl;
    public static List<String> adminUrls = new ArrayList<>();
    public static List<String> memberUrls = new ArrayList<>();

    @PostConstruct
    public void init(){
        String[] adminSplit = adminUrl.split(",");
//        adminUrls = Arrays.asList(adminSplit);
        String[] memberSplit = memberUrl.split(",");
//        memberUrls = Arrays.asList(memberSplit);
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();


        // 设置路径过滤--登录相关
        String requestUrl = request.getRequestURL().toString();
        if (requestUrl.contains("/images/") || requestUrl.contains("/wechat/getUnlimited")) {
            return null;
        }

        String platform = request.getHeader(HEADER_FLATFORM);
        // 微信小程序访问

        return null;
    }

    private void changeCtx(RequestContext ctx) {
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(401);
        ctx.getResponse().setCharacterEncoding("UTF-8");
    }
}
