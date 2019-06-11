package com.jawnho.douyuspringboot.entity.po;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="tb_tb_sql")
public class TbSqlPo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "tb_id",columnDefinition = "varchar(255)")
    private String tbId;

    @Column(name = "tb_sql",columnDefinition = "text(1000)")
    private String tbSql;

    @Column(name = "tb_name",columnDefinition = "varchar(1000)")
    private String tbName;

    @Column(name = "isDelete")
    private Integer isDelete;

    @Column(name = "createTime",columnDefinition = "varchar(255)")
    private String createTime;

    @Column(name = "modifiedTime",columnDefinition = "varchar(255)")
    private String modifiedTime;
}
