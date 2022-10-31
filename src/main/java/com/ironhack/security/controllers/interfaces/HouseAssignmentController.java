package com.ironhack.security.controllers.interfaces;

import com.ironhack.security.enums.House;
import com.ironhack.security.enums.Wing;
import com.ironhack.security.models.HouseAssignment;

import java.util.List;

public interface HouseAssignmentController {
    List<HouseAssignment> getAllHouseAssignmentByHouse(House house);
    List<HouseAssignment> getAllHouseAssignmentByWing(Wing wing);
    HouseAssignment saveHouseAssignment(HouseAssignment houseAssignment);
}
