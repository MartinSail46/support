package com.sz.admin.templatedisk.pojo.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.sz.core.common.entity.PageQuery;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * TemplateDisk添加DTO
 * </p>
 *
 * @author sz-admin
 * @since 2024-07-03
 */
@Data
@Schema(description = "TemplateDisk查询DTO")
public class TemplateDiskListDTO extends PageQuery {

    @Schema(description =  "关联的模版ID")
    private String templateId;

    @Schema(description =  "文件系统")
    private String fileSystem;

    @Schema(description =  "大小")
    private String size;

    @Schema(description =  "已用")
    private String used;

    @Schema(description =  "可用")
    private String available;

    @Schema(description =  "已用（百分比）")
    private String usedPercentage;

    @Schema(description =  "挂载点")
    private String mountPoint;

    @Schema(description =  "乐观锁")
    private Integer revision;

    @Schema(description =  "创建人")
    private String createdId;

    @Schema(description =  "创建时间")
    private LocalDateTime createdTime;

    @Schema(description =  "更新人")
    private String updatedId;

    @Schema(description =  "更新时间")
    private LocalDateTime updatedTime;


}