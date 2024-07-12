package com.isproj2.regainmobile.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.dto.EquipmentDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.Category;
import com.isproj2.regainmobile.model.Equipment;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.CategoryRepository;
import com.isproj2.regainmobile.repo.EquipmentRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.EquipmentService;

@Service
public class EquipmentServiceImpl implements EquipmentService {

        @Autowired
        private EquipmentRepository equipmentRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private CategoryRepository categoryRepository;

        @Override
        public EquipmentDTO createEquipment(EquipmentDTO equipmentDTO) {
                User renter = userRepository.findById(equipmentDTO.getRenterID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Renter not found with id " + equipmentDTO.getRenterID()));

                Category categ = categoryRepository.findById(equipmentDTO.getCategoryID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Category not found with id " + equipmentDTO.getCategoryID()));

                Equipment equipment = new Equipment(equipmentDTO, renter, categ);
                equipmentRepository.save(equipment);
                return equipmentDTO;
        }

        @Override
        public EquipmentDTO updateEquipment(Integer equipmentId, EquipmentDTO equipmentDTO) {
                Equipment equipment = equipmentRepository.findById(equipmentId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Equipment not found with id " + equipmentId));

                User renter = userRepository.findById(equipmentDTO.getRenterID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Renter not found with id " + equipmentDTO.getRenterID()));

                Category categ = categoryRepository.findById(equipmentDTO.getCategoryID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Category not found with id " + equipmentDTO.getCategoryID()));

                equipment.setRenter(renter);
                equipment.setCategory(categ);
                equipment.setEquipmentName(equipmentDTO.getEquipmentName());
                equipment.setDescription(equipmentDTO.getDescription());
                equipment.setLocation(equipmentDTO.getLocation());
                equipment.setPrice(equipmentDTO.getPrice());

                equipmentRepository.save(equipment);
                return equipmentDTO;
        }

        @Override
        public void deleteEquipment(Integer equipmentId) {
                if (!equipmentRepository.existsById(equipmentId)) {
                        throw new ResourceNotFoundException("Equipment not found with id " + equipmentId);
                }
                equipmentRepository.deleteById(equipmentId);
        }

        @Override
        public EquipmentDTO getEquipmentById(Integer equipmentId) {
                Equipment equipment = equipmentRepository.findById(equipmentId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Equipment not found with id " + equipmentId));
                return new EquipmentDTO(equipment.getEquipmentID(), equipment.getRenter().getUserID(),
                                equipment.getEquipmentName(),
                                equipment.getDescription(), equipment.getLocation(),
                                equipment.getCategory().getCategoryID(),
                                equipment.getPrice());
        }

        @Override
        public List<EquipmentDTO> getAllEquipments() {
                return equipmentRepository.findAll().stream()
                                .map(equipment -> new EquipmentDTO(equipment.getEquipmentID(),
                                                equipment.getRenter().getUserID(),
                                                equipment.getEquipmentName(), equipment.getDescription(),
                                                equipment.getLocation(),
                                                equipment.getCategory().getCategoryID(), equipment.getPrice()))
                                .collect(Collectors.toList());
        }

}
