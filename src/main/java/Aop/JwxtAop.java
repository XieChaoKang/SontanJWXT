package Aop;

import Util.Jwxt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;

import java.sql.SQLOutput;

/*
Aop注解  前往Application修改cookies有效期的时间  单位 秒
 */
@Aspect
public class JwxtAop {

    //cookies 时间长度
    private long cookiesTime;

    //cookies 有效期
    private long validity;


    /*
    后置通知 没操作一次 更新一次cookies有效期时间
     */
    @After("execution(* Service.JwxtServiceImpl.*(..))")
    public void SetCookiesValidity(){
        validity = System.currentTimeMillis()/1000+cookiesTime;
        System.out.println("重置cookies");
    }


    /*
    前置通知 检查cookies是否有效
     */
    @Before("execution(* Service.JwxtServiceImpl.*(..))")
    public boolean checkCookiesValidity(JoinPoint joinPoint){
        long time = System.currentTimeMillis()/1000;
        if (validity==0){
            validity = time;
        }
        if (time>validity){
            System.out.println("cookies:失效");
            Jwxt jwxt = (Jwxt) joinPoint.getTarget();
            jwxt.init();
            return false;
        }
        System.out.println("cookies:可用");
        return true;
    }


    public long getCookiesTime() {
        return cookiesTime;
    }

    public void setCookiesTime(long cookiesTime) {
        this.cookiesTime = cookiesTime;
    }

    public long getValidity() {
        return validity;
    }

    public void setValidity(long validity) {
        this.validity = validity;
    }
}
