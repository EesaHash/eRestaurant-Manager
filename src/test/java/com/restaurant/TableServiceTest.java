package com.restaurant;


import com.restaurant.model.Promo;
import com.restaurant.model.Tables;
import com.restaurant.repository.TableRepository;
import com.restaurant.service.TableService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TableServiceTest {

    @Mock
    TableRepository tableRepository;

    @InjectMocks
    TableService tableService;

    @Test
    final void testGetAllTable(){
        Tables table1 = new Tables(11,5);
        Tables table2 = new Tables(12,5);
        List<Tables> listOfTable = new LinkedList<Tables>();
        listOfTable.add(table1);
        listOfTable.add(table2);

        when(tableRepository.findAll()).thenReturn(listOfTable);
        List<Tables> listOfTable2 = tableService.getAllTable();

        assertNotNull(listOfTable2);
    }

    @Test
    final void testAddTable(){
        Tables table1 = new Tables(11,5);
        List<Tables> listOfTable = new LinkedList<Tables>();
        listOfTable.add(table1);
        tableRepository.save(table1);
        when(tableRepository.findAll()).thenReturn(listOfTable);
        List<Tables> listOfTable2 = tableService.getAllTable();

        assertNotNull(listOfTable2);
    }

    @Test
    final void testRemoveTableById(){
        Tables table1 = new Tables(11,5);
        List<Tables> listOfTable = new LinkedList<Tables>();
        listOfTable.add(table1);
        tableRepository.delete(table1);
        when(tableRepository.findAll()).thenReturn(null);
        List<Tables> listOfTable2 = tableService.getAllTable();

        assertNull(listOfTable2);
    }
}
