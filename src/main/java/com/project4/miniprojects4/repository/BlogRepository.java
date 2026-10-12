
package com.project4.miniprojects4.repository;

import com.project4.miniprojects4.model.Blog;
import com.project4.miniprojects4.model.BlogStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog> findByApprovedTrue();

    List<Blog> findByStatusAndApprovedTrue(BlogStatus status);

    Blog findBySlug(String slug);

    List<Blog> findByTitleContainingIgnoreCase(String title);

    List<Blog> findDistinctByCategories_IdAndStatusAndApprovedTrue(
            Long categoryId,
            BlogStatus status
    );

    List<Blog> findByCategories_IdIn(List<Long> categoryIds);

    List<Blog> findBySlugContainingIgnoreCaseAndStatusAndApprovedTrue(
            String slug, BlogStatus status);

    @Query("""
    SELECT b
    FROM Blog b
    WHERE LOWER(b.slug) LIKE LOWER(CONCAT('%', :slug, '%'))
      AND b.status = :status
      AND b.approved = true
      AND (
          SELECT COUNT(DISTINCT c.id)
          FROM Blog b2
          JOIN b2.categories c
          WHERE b2.id = b.id
            AND c.id IN :categoryIds
      ) = :categoryCount
    """)
    List<Blog> searchBySlugAndAllCategories(
            @Param("slug") String slug,
            @Param("status") BlogStatus status,
            @Param("categoryIds") List<Long> categoryIds,
            @Param("categoryCount") long categoryCount
    );

    @Query("""
    SELECT DISTINCT b
    FROM Blog b
    JOIN b.tags t
    WHERE b.status = :status
      AND b.approved = true
      AND LOWER(t.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
    List<Blog> searchByTag(
            @Param("keyword") String keyword,
            @Param("status") BlogStatus status
    );
}

