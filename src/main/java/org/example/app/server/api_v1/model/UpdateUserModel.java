package org.example.app.server.api_v1.model;

import org.example.app.server.api_v1.db_connect.DbConnectInit;
import org.example.app.server.api_v1.entity.User;
import org.example.app.server.api_v1.repository.UserRepository;
import org.example.app.server.api_v1.utils.ActionAnswer;
import org.example.app.server.api_v1.utils.CreateUpdateParams;

public class UpdateUserModel {
    private final User user;

    public UpdateUserModel(User user, CreateUpdateParams createUpdateParams) {
        this.user = user;
        this.user.setFirstName(createUpdateParams.getFirstName());
        this.user.setLastName(createUpdateParams.getLastName());
        this.user.setEmail(createUpdateParams.getEmail());
        this.user.setPhone(createUpdateParams.getPhone());
    }

    public ActionAnswer<User> updateUser(DbConnectInit connection) {
        UserRepository userRepository = new UserRepository(connection);
        return userRepository.update(this.user);
    }

}
