package com.ironhack.security.services.interfaces;

import com.ironhack.security.enums.House;
import com.ironhack.security.enums.Wing;
import com.ironhack.security.models.HouseAssignment;

import java.util.List;

public interface HouseAssignmentService {
    List<HouseAssignment> getAllHouseAssignmentByHouse(House house);

    List<HouseAssignment> getAllHouseAssignmentByWing(Wing wing);

    HouseAssignment saveHouseAssignment(HouseAssignment houseAssignment);
}
