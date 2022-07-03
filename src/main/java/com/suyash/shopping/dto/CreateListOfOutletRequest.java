package com.suyash.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateListOfOutletRequest {
    private List<CreateOutletRequest> bangloreOutletDetails;
}
