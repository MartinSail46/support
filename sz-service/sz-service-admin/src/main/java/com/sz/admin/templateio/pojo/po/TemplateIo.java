package com.sz.admin.templateio.pojo.po;

import com.alibaba.excel.annotation.ExcelProperty;
import com.mybatisflex.annotation.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import com.sz.mysql.EntityChangeListener;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
/**
* <p>
* 磁盘IO读写情况(iostat -d)
* </p>
*
* @author sz-admin
* @since 2024-07-03
*/
@Data
@Table(value = "template_io", onInsert = EntityChangeListener.class, onUpdate = EntityChangeListener.class)
@Schema(description = "磁盘IO读写情况(iostat -d)")
public class TemplateIo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
    @Schema(description ="流水号")
    private Long id;

    @Schema(description ="关联的模版ID")
    private String templateId;

    @Schema(description =  "关联的模版名称")
    private String templateName;

    @Schema(description ="设备")
    private String device;

    @Schema(description ="每秒事务处理量")
    private String tps;

    @Schema(description ="每秒从设备读取的数据（KB）")
    private String kbReadS;

    @Schema(description ="每秒从设备写入的数据（KB）")
    private String kbWriteS;

    @Schema(description ="读取的总数据量")
    private String kbRead;

    @Schema(description ="写入的总数据量")
    private String kbWrite;

    @Schema(description ="删除标识")
    private String delFlag;

    @Schema(description ="乐观锁")
    private Integer revision;

    @Schema(description ="创建人")
    private String createdId;

    @Schema(description ="创建时间")
    private LocalDateTime createdTime;

    @Schema(description ="更新人")
    private String updatedId;

    @Schema(description ="更新时间")
    private LocalDateTime updatedTime;

}