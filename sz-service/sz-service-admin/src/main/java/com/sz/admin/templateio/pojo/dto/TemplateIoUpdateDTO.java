package com.sz.admin.templateio.pojo.dto;


import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * TemplateIo添加DTO
 * </p>
 *
 * @author sz-admin
 * @since 2024-07-03
 */
@Data
@Schema(description = "TemplateIo修改DTO")
public class TemplateIoUpdateDTO {

    @Schema(description =  "流水号")
    private Long id;

    @Schema(description =  "关联的模版ID")
    private String templateId;

    @Schema(description =  "关联的模版名称")
    private String templateName;

    @Schema(description =  "设备")
    private String device;

    @Schema(description =  "每秒事务处理量")
    private String tps;

    @Schema(description =  "每秒从设备读取的数据（KB）")
    private String kbReadS;

    @Schema(description =  "每秒从设备写入的数据（KB）")
    private String kbWriteS;

    @Schema(description =  "读取的总数据量")
    private String kbRead;

    @Schema(description =  "写入的总数据量")
    private String kbWrite;

    @Schema(description =  "乐观锁")
    private Integer revision;

    @Schema(description =  "创建人")
    private String createdId;

    @Schema(description =  "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

    @Schema(description =  "更新人")
    private String updatedId;

    @Schema(description =  "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;


}