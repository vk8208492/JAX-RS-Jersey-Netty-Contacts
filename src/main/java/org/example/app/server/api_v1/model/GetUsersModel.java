package org.example.app.server.api_v1.model;

import org.example.app.server.api_v1.db_connect.DbConnectInit;
import org.example.app.server.api_v1.entity.User;
import org.example.app.server.api_v1.repository.UserRepository;
import org.example.app.server.api_v1.utils.ActionAnswer;
import org.example.app.server.api_v1.utils.ReadParams;

import java.util.List;

public class GetUsersModel {
    private final int limit;
    private final int offset;
    private final List<String> excludeColumns;
    private final Long id;

    public GetUsersModel(ReadParams readParams) {
        this.limit = readParams.getLimit();
        this.offset = readParams.getOffset();
        this.excludeColumns = readParams.getExcludeColumns();
        this.id = readParams.getId();
    }


    public ActionAnswer<User> getUsers(DbConnectInit connection) {
        UserRepository userRepository = new UserRepository(connection);
        return userRepository.readAll(this.excludeColumns, this.limit, this.offset);
    }

    public ActionAnswer<User> getUser(DbConnectInit connection) {
        UserRepository userRepository = new UserRepository(connection);
        return userRepository.readById(this.id, this.excludeColumns);
    }

}
