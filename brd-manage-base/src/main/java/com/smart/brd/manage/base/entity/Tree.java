package com.smart.brd.manage.base.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Data
public class Tree {
    private Long id;                //组织id
    private String name;            //组织名称
//    private Long superid;           //父id
    private List<Tree> children=new ArrayList();    //子节点
}
