package com.restaurant.service;

import com.restaurant.model.TableModel;
import com.restaurant.repository.TableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableService {
    private final TableRepo tableRepo;

    @Autowired
    public TableService(TableRepo tableRepo) { this.tableRepo = tableRepo;}

    public void addTable(TableModel tableModel){tableRepo.save(tableModel);}

    public List<TableModel> getAllTables(){return  tableRepo.findAll();}

    public Optional<TableModel> getTableById(Long id){return tableRepo.findById(id);}

    public void updateTable(TableModel tableModel){tableRepo.save(tableModel);}
}
