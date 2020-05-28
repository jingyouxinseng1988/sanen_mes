package com.plc.platform.dto.request;

import lombok.Data;

@Data
public class SubMaterialInfo {

    private String materialName;
    private String materialCode;
    private Integer materialCount;
}
