package com.jawnho.douyuspringboot.entity.po;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "top")
public class LiveRoomTopPo {

    @Id
    @GeneratedValue
    @Column(name = "roomid",columnDefinition = "bigint(255)")
    private Long roomid;

    @Column(name = "ol",columnDefinition = "bigint(255)")
    private Long ol;
}
