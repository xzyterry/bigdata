package com.jawnho.douyuspringboot.entity.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbField {

    private String fid;
    private String fname;
    private String ftype;
    private String fcomment;

}
