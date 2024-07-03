package com.sz.admin.templatedisk.service;

import com.mybatisflex.core.service.IService;
import com.sz.admin.templatedisk.pojo.po.TemplateDisk;
import com.sz.core.common.entity.SelectIdsDTO;
import com.sz.core.common.entity.PageResult;
import java.util.List;

import com.sz.admin.templatedisk.pojo.dto.TemplateDiskCreateDTO;
import com.sz.admin.templatedisk.pojo.dto.TemplateDiskUpdateDTO;
import com.sz.admin.templatedisk.pojo.dto.TemplateDiskListDTO;
import com.sz.admin.templatedisk.pojo.vo.TemplateDiskVO;
import com.sz.admin.templatedisk.pojo.dto.TemplateDiskImportDTO;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

/**
 * <p>
 * 文件系统使用情况(df -h) Service
 * </p>
 *
 * @author sz-admin
 * @since 2024-07-03
 */
public interface TemplateDiskService extends IService<TemplateDisk> {

    void create(TemplateDiskCreateDTO dto);

    void update(TemplateDiskUpdateDTO dto);

    PageResult<TemplateDiskVO> page(TemplateDiskListDTO dto);

    List<TemplateDiskVO> list(TemplateDiskListDTO dto);

    void remove(SelectIdsDTO dto);

    TemplateDiskVO detail(Object id);

    void importExcel(MultipartFile file);

    void exportExcel(TemplateDiskListDTO dto, HttpServletResponse response);

}
