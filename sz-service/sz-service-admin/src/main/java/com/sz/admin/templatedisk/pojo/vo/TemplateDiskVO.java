package com.sz.admin.templatedisk.pojo.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

/**
 * <p>
 * TemplateDisk查询返回
 * </p>
 *
 * @author sz-admin
 * @since 2024-07-03
 */
@Data
@Schema(description = "TemplateDisk返回vo")
public class TemplateDiskVO {

    @ExcelIgnore
    @Schema(description =  "流水号")
    private Long id;

    @ExcelProperty(value = "关联的模版ID")
    @Schema(description =  "关联的模版ID")
    private String templateId;

    @ExcelProperty(value = "关联的模板名称")
    @Schema(description =  "关联的模版名称")
    private String templateName;

    @ExcelProperty(value = "文件系统")
    @Schema(description =  "文件系统")
    private String fileSystem;

    @ExcelProperty(value = "大小")
    @Schema(description =  "大小")
    private String size;

    @ExcelProperty(value = "已用")
    @Schema(description =  "已用")
    private String used;

    @ExcelProperty(value = "可用")
    @Schema(description =  "可用")
    private String available;

    @ExcelProperty(value = "已用（百分比）")
    @Schema(description =  "已用（百分比）")
    private String usedPercentage;

    @ExcelProperty(value = "挂载点")
    @Schema(description =  "挂载点")
    private String mountPoint;

    @ExcelProperty(value = "乐观锁")
    @Schema(description =  "乐观锁")
    private Integer revision;

    @ExcelProperty(value = "创建人")
    @Schema(description =  "创建人")
    private String createdId;

    @ExcelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description =  "创建时间")
    private LocalDateTime createdTime;

    @ExcelProperty(value = "更新人")
    @Schema(description =  "更新人")
    private String updatedId;

    @ExcelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description =  "更新时间")
    private LocalDateTime updatedTime;


}