package com.mozipp.product.users.repository;

import com.mozipp.product.users.PetGroomingImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetGroomingImageRepository extends JpaRepository<PetGroomingImage, Integer> {
}
