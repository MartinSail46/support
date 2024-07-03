package com.sz.admin.templatedisk.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import com.sz.core.common.entity.ApiPageResult;
import com.sz.core.common.entity.ApiResult;
import com.sz.core.common.constant.GlobalConstant;

import com.sz.core.common.entity.PageResult;
import com.sz.core.common.entity.SelectIdsDTO;
import com.sz.admin.templatedisk.service.TemplateDiskService;
import com.sz.admin.templatedisk.pojo.dto.TemplateDiskCreateDTO;
import com.sz.admin.templatedisk.pojo.dto.TemplateDiskUpdateDTO;
import com.sz.admin.templatedisk.pojo.dto.TemplateDiskListDTO;
import com.sz.admin.templatedisk.pojo.vo.TemplateDiskVO;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletResponse;

/**
 * <p>
 * 文件系统使用情况(df -h) Controller
 * </p>
 *
 * @author sz-admin
 * @since 2024-07-03
 */
@Tag(name =  "文件系统使用情况(df -h)")
@RestController
@RequestMapping("template-disk")
@RequiredArgsConstructor
public class TemplateDiskController  {

    private final TemplateDiskService templateDiskService;

    @Operation(summary = "新增")
    @SaCheckPermission(value = "template.disk.create", orRole = GlobalConstant.SUPER_ROLE)
    @PostMapping
    public ApiResult create(@RequestBody TemplateDiskCreateDTO dto) {
        templateDiskService.create(dto);
        return ApiResult.success();
    }

    @Operation(summary = "修改")
    @SaCheckPermission(value = "template.disk.update", orRole = GlobalConstant.SUPER_ROLE)
    @PutMapping
    public ApiResult update(@RequestBody TemplateDiskUpdateDTO dto) {
        templateDiskService.update(dto);
        return ApiResult.success();
    }

    @Operation(summary = "删除")
    @SaCheckPermission(value = "template.disk.remove", orRole = GlobalConstant.SUPER_ROLE)
    @DeleteMapping
    public ApiResult remove(@RequestBody SelectIdsDTO dto) {
        templateDiskService.remove(dto);
        return ApiResult.success();
    }

    @Operation(summary = "列表查询")
    @SaCheckPermission(value = "template.disk.query_table", orRole = GlobalConstant.SUPER_ROLE)
    @GetMapping
    public ApiResult<PageResult<TemplateDiskVO>> list(TemplateDiskListDTO dto) {
        return ApiPageResult.success(templateDiskService.page(dto));
    }

    @Operation(summary = "详情")
    @SaCheckPermission(value = "template.disk.query_table", orRole = GlobalConstant.SUPER_ROLE)
    @GetMapping("/{id}")
    public ApiResult<TemplateDiskVO> detail(@PathVariable Object id) {
        return ApiResult.success(templateDiskService.detail(id));
    }

    @Operation(summary = "导入")
    @Parameters({
      @Parameter(name = "file", description = "上传文件", schema = @Schema(type = "string", format = "binary"), required = true),
    })
    @SaCheckPermission(value = "template.disk.import", orRole = GlobalConstant.SUPER_ROLE)
    @PostMapping("/import")
    public void importExcel(MultipartFile file) {
        templateDiskService.importExcel(file);
    }

    @Operation(summary = "导出")
    @SaCheckPermission(value = "template.disk.export", orRole = GlobalConstant.SUPER_ROLE)
    @PostMapping("/export")
    public void exportExcel(TemplateDiskListDTO dto, HttpServletResponse response) {
        templateDiskService.exportExcel(dto, response);
    }

}
