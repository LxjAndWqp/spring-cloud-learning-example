package com.lidong.flink.stream.kafka;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
public class UserPvEntity {
    /**
     * 时间戳
      */
   	private Timestamp time;
    /**
     * 用户id
     */
   	private String userId;
    /**
     * pv
     */
   	private Long  pvcount;

    public UserPvEntity(Timestamp time, String userId, Long pvcount) {
        this.time = time;
        this.userId = userId;
        this.pvcount = pvcount;
    }


}
