package com.sz.admin.templateinfo.pojo.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.sz.excel.annotation.DictFormat;
import com.sz.excel.convert.ExcelDictConvert;

/**
 * <p>
 * TemplateInfo查询返回
 * </p>
 *
 * @author sz-admin
 * @since 2024-07-02
 */
@Data
@Schema(description = "TemplateInfo返回vo")
public class TemplateInfoVO {

    @ExcelIgnore
    @Schema(description =  "模版ID")
    private Long id;

    @ExcelProperty(value = "模版名称")
    @Schema(description =  "模版名称")
    private String name;

    @ExcelProperty(value = "所属银行")
    @Schema(description =  "所属银行")
    private String belong;

    @ExcelProperty(value = "巡检系统")
    @Schema(description =  "巡检系统")
    private String system;

    @ExcelProperty(value = "巡检人员")
    @Schema(description =  "巡检人员")
    private String userId;

    @ExcelProperty(value = "巡检开始日期")
    @Schema(description =  "巡检开始日期")
    private LocalDate startDate;

    @ExcelProperty(value = "巡检结束日期")
    @Schema(description =  "巡检结束日期")
    private LocalDate endDate;

    @ExcelIgnore
    @Schema(description =  "乐观锁")
    private Long revision;

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