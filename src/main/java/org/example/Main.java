package org.example;

import org.example.controller.AdminController;
import org.example.controller.AuthController;
import org.example.controller.ProfileController;
import org.example.db.DataBase;
import org.example.db.InitDataBase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        DataBase.initTable();

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
          InitDataBase initDataBase = (InitDataBase) context.getBean("initDatabase");
          InitDataBase.adminInit();
          InitDataBase.addCompanyCard();

        AuthController authController = (AuthController) context.getBean("authController");
        authController.start();

        AdminController adminController = (AdminController) context.getBean("adminController");
        adminController.start();

        ProfileController profileController = (ProfileController) context.getBean("profileController");
        profileController.start();


    }
}
