package com.restaurant.service;

import com.restaurant.model.Category;
import com.restaurant.model.Tables;
import com.restaurant.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.util.List;
import java.util.Optional;

@Service
public class TableService {

    @Autowired
    TableRepository tableRepository;

    public List<Tables> getAllTable() {
        return tableRepository.findAll();
    }

    public void addTable(Tables table) {
        tableRepository.save(table);
    }

    public void removeTableById(int id) {
        tableRepository.deleteById(id);
    }

    public Optional<Tables> retrieveTableByID(int id) {
        return tableRepository.findById(id);
    }

}

