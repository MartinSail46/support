package com.sz.admin.templateio.pojo.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.excel.annotation.ExcelProperty;
/**
 * <p>
 * TemplateIo导入DTO
 * </p>
 *
 * @author sz-admin
 * @since 2024-07-03
 */
@Data
@Schema(description = "TemplateIo导入DTO")
public class TemplateIoImportDTO {

    @ExcelProperty(value = "关联的模版ID")
    @Schema(description =  "关联的模版ID")
    private String templateId;

    @ExcelProperty(value = "关联的模板名称")
    @Schema(description =  "关联的模版名称")
    private String templateName;

    @ExcelProperty(value = "设备")
    @Schema(description =  "设备")
    private String device;

    @ExcelProperty(value = "每秒事务处理量")
    @Schema(description =  "每秒事务处理量")
    private String tps;

    @ExcelProperty(value = "每秒从设备读取的数据（KB）")
    @Schema(description =  "每秒从设备读取的数据（KB）")
    private String kbReadS;

    @ExcelProperty(value = "每秒从设备写入的数据（KB）")
    @Schema(description =  "每秒从设备写入的数据（KB）")
    private String kbWriteS;

    @ExcelProperty(value = "读取的总数据量")
    @Schema(description =  "读取的总数据量")
    private String kbRead;

    @ExcelProperty(value = "写入的总数据量")
    @Schema(description =  "写入的总数据量")
    private String kbWrite;

    @ExcelProperty(value = "乐观锁")
    @Schema(description =  "乐观锁")
    private Integer revision;

    @ExcelProperty(value = "创建人")
    @Schema(description =  "创建人")
    private String createdId;

    @Schema(description =  "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

    @ExcelProperty(value = "更新人")
    @Schema(description =  "更新人")
    private String updatedId;

    @Schema(description =  "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;


}