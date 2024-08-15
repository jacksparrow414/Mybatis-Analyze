package com.example.mybatis.demomybatis.proxy;

import com.example.mybatis.demomybatis.fixture.CommonProxy;
import com.example.mybatis.demomybatis.reflect.LogInterceptor;
import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Test;

import java.io.File;

public class GenerateProxyWithByteBuddyTest {

    /**
     * 被代理类的构造方法最好是公共的，不要是default或者private的，否则在生成代理类的实例是会报
     * Caused by: java.lang.IllegalAccessError tried to access method 'void .<init>()' is in unnamed module of loader
     * 如果构造方法必须是default的，见 https://stackoverflow.com/a/60613399/24757740
     */
    @SneakyThrows
    @Test
    public void assertGenerateProxyWithByteBuddy() {
        CommonProxy commonProxy = new CommonProxy();
        CommonProxyInvocationHandler handler = new CommonProxyInvocationHandler(commonProxy);
        final CommonProxy proxy = new ByteBuddy()
                .subclass(CommonProxy.class)
                .method(ElementMatchers.named("test"))
//                使用InvocationHandlerAdapter的好处是，被代理类无需实现特定的接口，只需要提供InvocationHandler实现即可，这样可以很容易的将cglib代码迁移到jdk动态代理
                .intercept(InvocationHandlerAdapter.of(handler))
//                创建代理类
                .make()
//                将代理类加载进JVM中
                .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
        proxy.test();
    }

    /**
     * 不使用JDK动态代理实现切面，使用ByteBuddy本身提供的方式
     */
    @SneakyThrows
    @Test
    public void assertLogInterceptor() {
        final CommonProxy proxy = new ByteBuddy()
                .subclass(CommonProxy.class)
                .method(ElementMatchers.any())
                .intercept(MethodDelegation.to(new LogInterceptor()))
                .make()
                .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
        proxy.test();
        System.out.println(proxy.getName("name"));
    }

    @SneakyThrows
    @Test
    public void assertGenerateProxyClass() {
        File file = new File("proxy");
        file.mkdir();
        new ByteBuddy()
                .subclass(CommonProxy.class)
                .method(ElementMatchers.any())
                .intercept(MethodDelegation.to(new LogInterceptor()))
                .make()
                .saveIn(file);
    }
}
