package org.yearup.data.mysql;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component

public class MySqlCategoryDao implements CategoryDao {


    private final JdbcTemplate jdbcTemplate;


    public MySqlCategoryDao(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }


    @Override

    public List getAllCategories() {

        String sql = "SELECT * FROM categories";

        return jdbcTemplate.query(sql, this::mapRowToCategory);

    }


    @Override

    public Category getCategoryById(int categoryId) {

        String sql = "SELECT * FROM categories WHERE category_id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{categoryId}, this::mapRowToCategory);

    }


    @Override

    public Category createCategory(Category category) {

        String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";

        jdbcTemplate.update(sql, category.getName(), category.getDescription());

        return category; // assuming your Category object gets auto-assigned ID

    }


    @Override

    public void updateCategory(int categoryId, Category category) {

        String sql = "UPDATE categories SET name = ?, description = ? WHERE category_id = ?";

        jdbcTemplate.update(sql, category.getName(), category.getDescription(), categoryId);

    }


    @Override

    public void deleteCategory(int categoryId) {

        String sql = "DELETE FROM categories WHERE category_id = ?";

        jdbcTemplate.update(sql, categoryId);

    }


    private Category mapRowToCategory(ResultSet rs, int rowNum) throws SQLException {

        Category category = new Category();

        category.setCategoryId(rs.getInt("category_id"));

        category.setName(rs.getString("name"));

        category.setDescription(rs.getString("description"));

        return category;

    }

}
