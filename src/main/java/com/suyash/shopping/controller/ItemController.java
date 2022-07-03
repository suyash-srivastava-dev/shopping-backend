package com.suyash.shopping.controller;

import com.suyash.shopping.dto.ItemCreateRequest;
import com.suyash.shopping.dto.ListOfItemCreate;
import com.suyash.shopping.dto.StandardResponse;
import com.suyash.shopping.model.Role;
import com.suyash.shopping.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/v1/api/item")
@AllArgsConstructor
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/create")
    public ResponseEntity<StandardResponse> createEmployee(@RequestBody ItemCreateRequest itemCreate)
    {
        return ResponseEntity.status(OK)
                .body(itemService.createItem(itemCreate));
    }
    @PostMapping("/create/list")
    public ResponseEntity<StandardResponse> createFromListOfItems(@RequestBody ListOfItemCreate items)
    {
        return ResponseEntity.status(OK)
                .body(itemService.createFromListOfItems(items.getData()));
    }
}
