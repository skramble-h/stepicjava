package dbService.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by skramble.h
 */
public interface ResultHandler<T>{
    T handle(ResultSet resultSet) throws SQLException;
}