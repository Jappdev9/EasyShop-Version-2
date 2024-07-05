package org.yearup.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;


@RestController

@RequestMapping("/categories")

@CrossOrigin(origins = "http://localhost:3000") // Adjust origin as per your frontend setup

public class CategoriesController {


    @Autowired

    private CategoryDao categoryDao;


    @Autowired

    private ProductDao productDao;


    /**

     * GET all categories.

     * @return List of all categories.

     */

    @GetMapping

    public List getAll() {

        return categoryDao.getAllCategories();

    }


    /**

     * GET a specific category by ID.

     * @param id The ID of the category to retrieve.

     * @return The category with the specified ID.

     */

    @GetMapping("/{id}")

    public ResponseEntity getById(@PathVariable int id) {

        Category category = categoryDao.getCategoryById(id);

        if (category != null) {

            return ResponseEntity.ok(category);

        } else {

            return ResponseEntity.notFound().build();

        }

    }


    /**

     * GET all products belonging to a specific category.

     * @param categoryId The ID of the category.

     * @return List of products in the specified category.

     */

    @GetMapping("/{categoryId}/products")

    public List getProductsById(@PathVariable int categoryId) {

        return productDao.getProductsByCategoryId(categoryId);

    }


    /**

     * Add a new category.

     * @param category The category object to add.

     * @return The added category object.

     */

    @PostMapping

    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity addCategory(@RequestBody Category category) {

        Category addedCategory = categoryDao.addCategory(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(addedCategory);

    }


    /**

     * Update an existing category.

     * @param id The ID of the category to update.

     * @param category The updated category object.

     * @return HTTP status indicating success or failure of the update operation.

     */

    @PutMapping("/{id}")

    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity updateCategory(@PathVariable int id, @RequestBody Category category) {

        category.setId(id); // Set the ID from the path variable into the category object

        boolean updated = categoryDao.updateCategory(category);

        if (updated) {

            return ResponseEntity.noContent().build();

        } else {

            return ResponseEntity.notFound().build();

        }

    }


    /**

     * Delete a category by ID.

     * @param id The ID of the category to delete.

     * @return HTTP status indicating success or failure of the delete operation.

     */

    @DeleteMapping("/{id}")

    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity deleteCategory(@PathVariable int id) {

        boolean deleted = categoryDao.deleteCategory(id);

        if (deleted) {

            return ResponseEntity.noContent().build();

        } else {

            return ResponseEntity.notFound().build();

        }

    }

}
