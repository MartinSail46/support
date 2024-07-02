package com.sz.admin.templateinfo.pojo.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * TemplateInfo添加DTO
 * </p>
 *
 * @author sz-admin
 * @since 2024-07-02
 */
@Data
@Schema(description = "TemplateInfo添加DTO")
public class TemplateInfoCreateDTO {

   @Schema(description =  "模版名称")
   private String name;

   @Schema(description =  "所属银行")
   private String belong;

   @Schema(description =  "巡检系统")
   private String system;

   @Schema(description =  "巡检人员")
   private String userId;

   @Schema(description =  "巡检开始日期")
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private LocalDate startDate;

   @Schema(description =  "巡检结束日期")
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private LocalDate endDate;

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