package com.jawnho.douyuspringboot.entity.po;

import lombok.Data;
import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "liveroom")
public class LiveRoomPo {

    @Id
    @GeneratedValue
    private Long roomid;

    @Column(name = "douyu_online",columnDefinition = "varchar(255)")
    private String douyu_online;
}
