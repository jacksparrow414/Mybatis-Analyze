package com.example.mybatis.demomybatis.java.advanced;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author jacksparrow414
 * @date 2021/10/1
 */
public class RmiServer extends UnicastRemoteObject implements RmiServerInterface{
    
    public RmiServer() throws RemoteException{
    }
    
    @Override
    public String sayHello() {
        return "hello, I'm Remote JVM Server";
    }
}
