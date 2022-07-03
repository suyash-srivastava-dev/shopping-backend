package com.suyash.shopping.controller;

import com.suyash.shopping.dto.*;
import com.suyash.shopping.service.ItemService;
import com.suyash.shopping.service.OutletService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/api/outlet")
@CrossOrigin(origins = "http://localhost:4200/")
@AllArgsConstructor
public class OutletController {
    @Autowired
    private OutletService outletService;

    @PostMapping("/create")
    public ResponseEntity<StandardResponse> createEmployee(@RequestBody CreateOutletRequest outlet)
    {
        return ResponseEntity.status(OK)
                .body(outletService.createOutlet(outlet));
    }
    @PostMapping("/create/list")
    public ResponseEntity<StandardResponse> createFromListOfItems(@RequestBody CreateListOfOutletRequest outlets)
    {
        return ResponseEntity.status(OK)
                .body(outletService.createAllOutlet(outlets.getBangloreOutletDetails()));
    }
}
