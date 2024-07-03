package com.sz.admin.templateio.service;

import com.mybatisflex.core.service.IService;
import com.sz.admin.templateio.pojo.po.TemplateIo;
import com.sz.core.common.entity.SelectIdsDTO;
import com.sz.core.common.entity.PageResult;
import java.util.List;

import com.sz.admin.templateio.pojo.dto.TemplateIoCreateDTO;
import com.sz.admin.templateio.pojo.dto.TemplateIoUpdateDTO;
import com.sz.admin.templateio.pojo.dto.TemplateIoListDTO;
import com.sz.admin.templateio.pojo.vo.TemplateIoVO;
import com.sz.admin.templateio.pojo.dto.TemplateIoImportDTO;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

/**
 * <p>
 * 磁盘IO读写情况(iostat -d) Service
 * </p>
 *
 * @author sz-admin
 * @since 2024-07-03
 */
public interface TemplateIoService extends IService<TemplateIo> {

    void create(TemplateIoCreateDTO dto);

    void update(TemplateIoUpdateDTO dto);

    PageResult<TemplateIoVO> page(TemplateIoListDTO dto);

    List<TemplateIoVO> list(TemplateIoListDTO dto);

    void remove(SelectIdsDTO dto);

    TemplateIoVO detail(Object id);

    void importExcel(MultipartFile file);

    void exportExcel(TemplateIoListDTO dto, HttpServletResponse response);

}
