package com.lidong.flink.stream.kafka;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
public class UserPvEntity {

   	private Timestamp time;
    private String userId;
    private Long  pvcount;

    public UserPvEntity(Timestamp time, String userId, Long pvcount) {
        this.time = time;
        this.userId = userId;
        this.pvcount = pvcount;
    }


}
