package com.project.splitwise.dto;

import com.project.splitwise.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {
    private String groupName;
    private String description;
    private Currency defaultCurrency;
}
