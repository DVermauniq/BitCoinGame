package com.rwl.Bit_coin.entity;


import lombok.*;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

    private String text;
    private String sender;
    private String receiver;

}