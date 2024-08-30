package service;

import model.Student;
import util.JDBCUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentService {
    public static void insert() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.getConnection();
            System.out.println("请输入要添加学生的姓名和年龄(以enter隔开)");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            int age = scanner.nextInt();

            String sql = "INSERT INTO info(name, age) values (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            int res = preparedStatement.executeUpdate();
            if (res > 0) {
                System.out.println("Insert success!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ArrayList<Student> selectAll() {
        ArrayList<Student> arrayList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet res = null;

        try {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT * FROM info";
            preparedStatement = connection.prepareStatement(sql);
            res = preparedStatement.executeQuery();

            while (res.next()) {
                arrayList.add(new Student(res.getInt(1), res.getString(2), res.getInt(3)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement, res);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return arrayList;
    }

    public static Student selectById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet res = null;
        Student student = null;

        try {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT * FROM info WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            res = preparedStatement.executeQuery();

            if (res.next()) {
                student = new Student(res.getInt(1), res.getString(2), res.getInt(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement, res);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return student;
    }

    public static boolean delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM info WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int res = preparedStatement.executeUpdate();
            if (res >0 ) {
                System.out.println("delete success!");
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;

    }

    public static boolean update(int id, String name, int age) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.getConnection();
            String sql = "UPDATE info SET name = ?, age = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setInt(3, id);
            int res = preparedStatement.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean update(int id, String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.getConnection();
            String sql = "UPDATE info SET name = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            int res = preparedStatement.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean update(int id, int age) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.getConnection();
            String sql = "UPDATE info SET age = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, age);
            preparedStatement.setInt(2, id);
            int res = preparedStatement.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
