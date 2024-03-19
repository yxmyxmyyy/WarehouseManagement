package com.api.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgDTO implements Serializable {
    @Schema(description = "在出库时间之前(晚于该时间)", example = "2022-01-01T12:00:00+08:00")
    private Date beforeTime;
    @Schema(description = "在出库时间之后(早于该时间)", example = "2022-01-01T12:00:00+08:00")
    private Date afterTime;
}


