package com.duck.integration.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class DataCleanRequest implements Serializable {

    private String userGid;

    private Integer apiChannel;

    private Integer channelId;
}
