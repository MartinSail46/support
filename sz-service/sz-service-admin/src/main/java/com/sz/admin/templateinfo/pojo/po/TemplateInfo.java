package com.sz.admin.templateinfo.pojo.po;

import com.mybatisflex.annotation.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import com.sz.mysql.EntityChangeListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
/**
* <p>
* 模版信息表
* </p>
*
* @author sz-admin
* @since 2024-07-02
*/
@Data
@Table(value = "template_info", onInsert = EntityChangeListener.class, onUpdate = EntityChangeListener.class)
@Schema(description = "模版信息表")
public class TemplateInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
    @Schema(description ="模版ID")
    private Long id;

    @Schema(description ="模版名称")
    private String name;

    @Schema(description ="所属银行")
    private String belong;

    @Schema(description ="巡检系统")
    private String system;

    @Schema(description ="状态")
    private String delFlag;

    @Schema(description ="巡检人员")
    private String userId;

    @Schema(description ="巡检开始日期")
    private LocalDate startDate;

    @Schema(description ="巡检结束日期")
    private LocalDate endDate;

    @Id
    @Schema(description ="乐观锁")
    private Long revision;

    @Schema(description ="创建人")
    private String createdId;

    @Schema(description ="创建时间")
    private LocalDateTime createdTime;

    @Schema(description ="更新人")
    private String updatedId;

    @Schema(description ="更新时间")
    private LocalDateTime updatedTime;

}