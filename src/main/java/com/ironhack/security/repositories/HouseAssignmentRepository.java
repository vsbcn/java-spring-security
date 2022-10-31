package com.ironhack.security.repositories;

import com.ironhack.security.enums.House;
import com.ironhack.security.enums.Wing;
import com.ironhack.security.models.HouseAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseAssignmentRepository extends JpaRepository<HouseAssignment, Integer> {
    List<HouseAssignment> findAllHouseAssignmentByHouse(House house);

    List<HouseAssignment> findAllHouseAssignmentByWing(Wing wing);
    //List<HouseAssignment> findByHouse(House house);
}
