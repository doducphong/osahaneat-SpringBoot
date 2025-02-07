package com.phongdo.osahaneat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.phongdo.osahaneat.domain.entity.Restaurant;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Restaurant findRestaurantById(int id);

    @Query("SELECT r FROM restaurant r where lower(r.title) like lower(concat('%',:query,'%'))")
    List<Restaurant> findBySearchQuery(String query);
}
