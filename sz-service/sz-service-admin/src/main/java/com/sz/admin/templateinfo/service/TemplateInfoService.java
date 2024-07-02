package com.sz.admin.templateinfo.service;

import com.mybatisflex.core.service.IService;
import com.sz.admin.templateinfo.pojo.po.TemplateInfo;
import com.sz.core.common.entity.SelectIdsDTO;
import com.sz.core.common.entity.PageResult;
import java.util.List;

import com.sz.admin.templateinfo.pojo.dto.TemplateInfoCreateDTO;
import com.sz.admin.templateinfo.pojo.dto.TemplateInfoUpdateDTO;
import com.sz.admin.templateinfo.pojo.dto.TemplateInfoListDTO;
import com.sz.admin.templateinfo.pojo.vo.TemplateInfoVO;
import com.sz.admin.templateinfo.pojo.dto.TemplateInfoImportDTO;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

/**
 * <p>
 * 模版信息表 Service
 * </p>
 *
 * @author sz-admin
 * @since 2024-07-02
 */
public interface TemplateInfoService extends IService<TemplateInfo> {

    void create(TemplateInfoCreateDTO dto);

    void update(TemplateInfoUpdateDTO dto);

    PageResult<TemplateInfoVO> page(TemplateInfoListDTO dto);

    List<TemplateInfoVO> list(TemplateInfoListDTO dto);

    void remove(SelectIdsDTO dto);

    TemplateInfoVO detail(Object id);

    void importExcel(MultipartFile file);

    void exportExcel(TemplateInfoListDTO dto, HttpServletResponse response);

}
