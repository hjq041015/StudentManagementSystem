package main;

import service.StudentService;

import java.sql.SQLException;
import java.util.Scanner;

public class runApplication {
    public static  Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        init();
    }

    public static void init() {
        while(true) {
            System.out.println("欢迎来的简易版学生管理系统\n1:添加学生\n2:删除学生\n3:查询学生\n4:查询所有学生\n5:更新学生信息\n6:退出\n请选择你的需求:");
            function();
        }
    }

    public static void function()  {
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                StudentService.insert();
                break;
            case 2:
                System.out.println("请选择要删除学生的学号");
                StudentService.delete(scanner.nextInt());
                break;
            case 3:
                System.out.println("请选择要查询学生的学号");
                System.out.println(StudentService.selectById(scanner.nextInt()));
                break;
            case 4:
                System.out.println(StudentService.selectAll());
                break;
            case 5:
                StudentService.update(scanner.nextInt(), scanner.next(), scanner.nextInt() );
                break;
            case  6:
                System.exit(0);
            default:
                System.out.println("无效选项，请重新输入");
        }
    }
}
