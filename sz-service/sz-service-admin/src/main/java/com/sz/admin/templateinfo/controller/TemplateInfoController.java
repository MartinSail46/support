package com.sz.admin.templateinfo.controller;


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
import com.sz.admin.templateinfo.service.TemplateInfoService;
import com.sz.admin.templateinfo.pojo.dto.TemplateInfoCreateDTO;
import com.sz.admin.templateinfo.pojo.dto.TemplateInfoUpdateDTO;
import com.sz.admin.templateinfo.pojo.dto.TemplateInfoListDTO;
import com.sz.admin.templateinfo.pojo.vo.TemplateInfoVO;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletResponse;

/**
 * <p>
 * 模版信息表 Controller
 * </p>
 *
 * @author sz-admin
 * @since 2024-07-02
 */
@Tag(name =  "模版信息表")
@RestController
@RequestMapping("template-info")
@RequiredArgsConstructor
public class TemplateInfoController  {

    private final TemplateInfoService templateInfoService;

    @Operation(summary = "新增")
    @SaCheckPermission(value = "template.info.create", orRole = GlobalConstant.SUPER_ROLE)
    @PostMapping
    public ApiResult create(@RequestBody TemplateInfoCreateDTO dto) {
        templateInfoService.create(dto);
        return ApiResult.success();
    }

    @Operation(summary = "修改")
    @SaCheckPermission(value = "template.info.update", orRole = GlobalConstant.SUPER_ROLE)
    @PutMapping
    public ApiResult update(@RequestBody TemplateInfoUpdateDTO dto) {
        templateInfoService.update(dto);
        return ApiResult.success();
    }

    @Operation(summary = "删除")
    @SaCheckPermission(value = "template.info.remove", orRole = GlobalConstant.SUPER_ROLE)
    @DeleteMapping
    public ApiResult remove(@RequestBody SelectIdsDTO dto) {
        templateInfoService.remove(dto);
        return ApiResult.success();
    }

    @Operation(summary = "列表查询")
    @SaCheckPermission(value = "template.info.query_table", orRole = GlobalConstant.SUPER_ROLE)
    @GetMapping
    public ApiResult<PageResult<TemplateInfoVO>> list(TemplateInfoListDTO dto) {
        return ApiPageResult.success(templateInfoService.page(dto));
    }

    @Operation(summary = "详情")
    @SaCheckPermission(value = "template.info.query_table", orRole = GlobalConstant.SUPER_ROLE)
    @GetMapping("/{id}")
    public ApiResult<TemplateInfoVO> detail(@PathVariable Object id) {
        return ApiResult.success(templateInfoService.detail(id));
    }

    @Operation(summary = "导入")
    @Parameters({
      @Parameter(name = "file", description = "上传文件", schema = @Schema(type = "string", format = "binary"), required = true),
    })
    @SaCheckPermission(value = "template.info.import", orRole = GlobalConstant.SUPER_ROLE)
    @PostMapping("/import")
    public void importExcel(MultipartFile file) {
        templateInfoService.importExcel(file);
    }

    @Operation(summary = "导出")
    @SaCheckPermission(value = "template.info.export", orRole = GlobalConstant.SUPER_ROLE)
    @PostMapping("/export")
    public void exportExcel(TemplateInfoListDTO dto, HttpServletResponse response) {
        templateInfoService.exportExcel(dto, response);
    }

}
