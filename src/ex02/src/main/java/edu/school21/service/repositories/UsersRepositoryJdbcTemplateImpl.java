package edu.school21.service.repositories;

import edu.school21.service.models.User;
import edu.school21.service.models.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository<User> {
    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;
    private final String FIND_BY_ID_QUERY = "SELECT user_id, user_email, user_password FROM user_table WHERE user_id = ?";
    private final String FIND_BY_EMAIL_QUERY = "SELECT user_id, user_email, user_password FROM user_table WHERE user_email = ?";
    private final String FIND_ALL_QUERY = "SELECT user_id, user_email, user_password FROM user_table";
    private final String SAVE_QUERY = "INSERT INTO user_table(user_email, user_password) VALUES(?, ?)";
    private final String UPDATE_QUERY = "UPDATE user_table SET user_email = ?, user_password = ? WHERE user_id = ?";
    private final String DELETE_QUERY = "DELETE user_table WHERE user_id = ?";

    @Autowired
    public UsersRepositoryJdbcTemplateImpl(JdbcTemplate jdbcTemplate, UserMapper userMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, new Object[]{id}, userMapper);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL_QUERY, userMapper);
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update(SAVE_QUERY, entity.getEmail(), entity.getPassword());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(UPDATE_QUERY, entity.getEmail(), entity.getPassword(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_QUERY, id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try{
            User user = jdbcTemplate.queryForObject(FIND_BY_EMAIL_QUERY, new Object[]{email}, userMapper);
            return Optional.of(user);
        }catch (DataAccessException ex){
            return Optional.empty();
        }
    }
}
