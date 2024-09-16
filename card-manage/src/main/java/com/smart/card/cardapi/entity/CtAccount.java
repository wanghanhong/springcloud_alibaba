package com.smart.card.cardapi.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CtAccount implements Serializable {

    private static final long serialVersionUID = -97522868445947073L;

    private String userId;
    private String password;
    private String aSecret;
    private String paccount;

}
