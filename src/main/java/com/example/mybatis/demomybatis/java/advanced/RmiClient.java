package com.example.mybatis.demomybatis.java.advanced;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author jacksparrow414
 * @date 2021/10/1
 */
public class RmiClient {
    
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(2001);
            RmiServerInterface hello = (RmiServerInterface) registry.lookup("hello");
//            RmiServerInterface hello = (RmiServerInterface) Naming.lookup("rmi://localhost:2001/hello");
            System.out.println("接收到服务器端的接口的返回信息是: " + hello.sayHello() +" "+ hello.getClass().getName());
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Connect Server fail"+ e.getMessage());
        }
    }
}
