package com.loose.coupling;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LooseCouplingExample {
    public static void main(String[] args) {
        ApplicationContext context
            = new ClassPathXmlApplicationContext("applicationConstructorInjection.xml");
       UserManager userManagerWithDB = (UserManager) context.getBean("userManagerWithDB");
        userManagerWithDB.getUserInfo();


        /*
        UserDataProvider webServiceProvider = new WebServiceDataProvider();
        UserManager userManagerWithWS = new UserManager(webServiceProvider);
        System.out.println(userManagerWithWS.getUserInfo());

        UserDataProvider newDBProvider = new NewDatabaseProvider();
        UserManager userManagerWithNDB = new UserManager(newDBProvider);
        System.out.println(userManagerWithNDB.getUserInfo());

         */
    }
}

