package com.ironhack.security.services.impl;

import com.ironhack.security.enums.House;
import com.ironhack.security.enums.Wing;
import com.ironhack.security.models.HouseAssignment;
import com.ironhack.security.repositories.HouseAssignmentRepository;
import com.ironhack.security.services.interfaces.HouseAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseAssignmentServiceImpl implements HouseAssignmentService {

    @Autowired
    private HouseAssignmentRepository houseAssignmentRepository;

    public List<HouseAssignment> getAllHouseAssignmentByHouse(House house) {
        return houseAssignmentRepository.findAllHouseAssignmentByHouse(house);
    }

    public List<HouseAssignment> getAllHouseAssignmentByWing(Wing wing) {
        return houseAssignmentRepository.findAllHouseAssignmentByWing(wing);
    }

    public HouseAssignment saveHouseAssignment(HouseAssignment houseAssignment) {
        /*if (houseAssignment.getId() != null && houseAssignmentRepository.existsById(houseAssignment.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este id ya existe en base de datos");
        }*/
        houseAssignment.setId(null);
        return houseAssignmentRepository.save(houseAssignment);
    }
}
