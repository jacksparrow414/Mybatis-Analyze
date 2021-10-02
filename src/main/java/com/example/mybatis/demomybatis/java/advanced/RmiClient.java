package com.example.mybatis.demomybatis.java.advanced;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author jacksparrow414
 * @date 2021/10/1
 */
public class RmiClient {
    
    public static void main(String[] args) {
        try {
            RmiServerInterface hello = (RmiServerInterface) Naming.lookup("rmi://localhost:2001/hello");
            System.out.println("接收到服务器端的接口的返回信息是: " + hello.sayHello() +" "+ hello.getClass().getName());
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            System.out.println("Connect Server fail"+ e.getMessage());
        }
    }
}
