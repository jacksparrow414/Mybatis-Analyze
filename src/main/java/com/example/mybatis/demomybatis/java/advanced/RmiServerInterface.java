package com.example.mybatis.demomybatis.java.advanced;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * java RMI 提供远程调用的接口要继承Remote
 * @author jacksparrow414
 * @date 2021/10/1
 */
public interface RmiServerInterface extends Remote {
    
    /**
     * 所有提供RMI的方法，都要抛出RemoteException，否则会报
     * Exception "remote object implements illegal remote interface"?
     * 错误
     * @return
     * @throws RemoteException
     */
    String sayHello() throws RemoteException;
}
