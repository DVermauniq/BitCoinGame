package com.rwl.Bit_coin.entity;

import com.rwl.Bit_coin.enumm.QueryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Query {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long queryId;
    @Enumerated(EnumType.STRING)
    private QueryType queryType;
    private String description;
}
