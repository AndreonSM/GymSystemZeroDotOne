package com.gymsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymsystem.exceptions.ResourceNotFoundException;
import com.gymsystem.model.Equipment;
import com.gymsystem.repository.EquipmentRepository;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    public Equipment getEquipmentById(Long equipmentId) {
        return equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment", "id", equipmentId));
    }

    public Equipment createEquipment(Equipment equipment) {
        // You might want to perform additional validation before saving
        return equipmentRepository.save(equipment);
    }

    public Equipment updateEquipment(Long equipmentId, Equipment updatedEquipment) {
        Equipment existingEquipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment", "id", equipmentId));

        // Update only the fields you want to allow
        existingEquipment.setName(updatedEquipment.getName());
        existingEquipment.setStatus(updatedEquipment.getStatus());
        existingEquipment.setLastMaintenanceDate(updatedEquipment.getLastMaintenanceDate());
        // Update other fields as needed

        return equipmentRepository.save(existingEquipment);
    }

    public void deleteEquipment(Long equipmentId) {
        if (equipmentRepository.existsById(equipmentId)) {
            equipmentRepository.deleteById(equipmentId);
        } else {
            throw new ResourceNotFoundException("Equipment", "id", equipmentId);
        }
    }
}
