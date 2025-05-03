package Service;

import Model.Book;
import Model.Result;

import java.sql.*;

public class DbService {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/first-jsp";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root123";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Result addBook(String name) throws SQLException {
        Connection connection = getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call add_book(?, ?, ?)}");
        callableStatement.setString(1, name);
        callableStatement.registerOutParameter(2, Types.VARCHAR);
        callableStatement.registerOutParameter(3, Types.BOOLEAN);
        callableStatement.execute();
        return Result.builder().message(callableStatement.getString(2)).success(callableStatement.getBoolean(3)).build();
    }

    public Result deleteBook(Integer id) throws SQLException {
        Connection connection = getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call delete_country(?,?,?)}");
        callableStatement.setInt(1, id);
        callableStatement.registerOutParameter(2, Types.VARCHAR);
        callableStatement.registerOutParameter(3, Types.BOOLEAN);
        callableStatement.execute();
        return Result.builder().message(callableStatement.getString(2)).success(callableStatement.getBoolean(3)).build();
    }

    public Result updateBook(Book book) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call update_country(?, ?, ?, ?)}");
        callableStatement.setInt(1, book.getId());
        callableStatement.setString(2, book.getTitle());

        callableStatement.registerOutParameter(3, Types.VARCHAR);
        callableStatement.registerOutParameter(4, Types.BOOLEAN);
        callableStatement.execute();
        return Result.builder().message(callableStatement.getString(3)).build();
    }
}
