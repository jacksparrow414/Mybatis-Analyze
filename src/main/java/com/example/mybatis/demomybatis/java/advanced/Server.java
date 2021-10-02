package com.example.mybatis.demomybatis.java.advanced;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author jacksparrow414
 * @date 2021/10/1
 * 视频地址：https://www.youtube.com/watch?v=X-bL0S8b6C4
 */
public class Server {
    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(2001);
        try {
            registry.bind("hello", new RmiServer());
            System.err.println("Server ready");
        } catch (AlreadyBoundException e) {
            System.out.println("Server bind fail" + e.getMessage());
        }
    }
}
