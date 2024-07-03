package com.sz.admin.templateio.controller;


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
import com.sz.admin.templateio.service.TemplateIoService;
import com.sz.admin.templateio.pojo.dto.TemplateIoCreateDTO;
import com.sz.admin.templateio.pojo.dto.TemplateIoUpdateDTO;
import com.sz.admin.templateio.pojo.dto.TemplateIoListDTO;
import com.sz.admin.templateio.pojo.vo.TemplateIoVO;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletResponse;

/**
 * <p>
 * 磁盘IO读写情况(iostat -d) Controller
 * </p>
 *
 * @author sz-admin
 * @since 2024-07-03
 */
@Tag(name =  "磁盘IO读写情况(iostat -d)")
@RestController
@RequestMapping("template-io")
@RequiredArgsConstructor
public class TemplateIoController  {

    private final TemplateIoService templateIoService;

    @Operation(summary = "新增")
    @SaCheckPermission(value = "template.io.create", orRole = GlobalConstant.SUPER_ROLE)
    @PostMapping
    public ApiResult create(@RequestBody TemplateIoCreateDTO dto) {
        templateIoService.create(dto);
        return ApiResult.success();
    }

    @Operation(summary = "修改")
    @SaCheckPermission(value = "template.io.update", orRole = GlobalConstant.SUPER_ROLE)
    @PutMapping
    public ApiResult update(@RequestBody TemplateIoUpdateDTO dto) {
        templateIoService.update(dto);
        return ApiResult.success();
    }

    @Operation(summary = "删除")
    @SaCheckPermission(value = "template.io.remove", orRole = GlobalConstant.SUPER_ROLE)
    @DeleteMapping
    public ApiResult remove(@RequestBody SelectIdsDTO dto) {
        templateIoService.remove(dto);
        return ApiResult.success();
    }

    @Operation(summary = "列表查询")
    @SaCheckPermission(value = "template.io.query_table", orRole = GlobalConstant.SUPER_ROLE)
    @GetMapping
    public ApiResult<PageResult<TemplateIoVO>> list(TemplateIoListDTO dto) {
        return ApiPageResult.success(templateIoService.page(dto));
    }

    @Operation(summary = "详情")
    @SaCheckPermission(value = "template.io.query_table", orRole = GlobalConstant.SUPER_ROLE)
    @GetMapping("/{id}")
    public ApiResult<TemplateIoVO> detail(@PathVariable Object id) {
        return ApiResult.success(templateIoService.detail(id));
    }

    @Operation(summary = "导入")
    @Parameters({
      @Parameter(name = "file", description = "上传文件", schema = @Schema(type = "string", format = "binary"), required = true),
    })
    @SaCheckPermission(value = "template.io.import", orRole = GlobalConstant.SUPER_ROLE)
    @PostMapping("/import")
    public void importExcel(MultipartFile file) {
        templateIoService.importExcel(file);
    }

    @Operation(summary = "导出")
    @SaCheckPermission(value = "template.io.export", orRole = GlobalConstant.SUPER_ROLE)
    @PostMapping("/export")
    public void exportExcel(TemplateIoListDTO dto, HttpServletResponse response) {
        templateIoService.exportExcel(dto, response);
    }

}
