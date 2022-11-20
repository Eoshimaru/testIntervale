package com.intervale.crud.repository;

import com.intervale.crud.repository.entity.Media;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface MediaRepository extends PagingAndSortingRepository<Media, Long> {

    @Modifying
    @Query("DELETE FROM media WHERE " +
            "identifier = :identifier")
    void deleteByIdentifier(String identifier);

    Media findByIdentifier(String identifier);

    @Query("SELECT * FROM media WHERE " +
            "(:author is null or author = :author) " +
            "and (:themes is null or themes = :themes) " +
            "and (:publishingHouse is null or publishing_house = :publishingHouse) " +
            "and (:type is null or type = :type) " +
            "and (:startDate is null or print_date >= :startDate) " +
            "and (:endDate is null or print_date <= :endDate)")
    List<Media> findAll(String author, String themes, String publishingHouse, Timestamp startDate, Timestamp endDate, String type);
}
