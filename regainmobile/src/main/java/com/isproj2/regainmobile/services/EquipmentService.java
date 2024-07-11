package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.EquipmentDTO;

public interface EquipmentService {
    EquipmentDTO createEquipment(EquipmentDTO equipmentDTO);
    EquipmentDTO updateEquipment(Integer equipmentId, EquipmentDTO equipmentDTO);
    void deleteEquipment(Integer equipmentId);
    EquipmentDTO getEquipmentById(Integer equipmentId);
    List<EquipmentDTO> getAllEquipments();
}
