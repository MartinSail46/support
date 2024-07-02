package com.sz.admin.templateinfo.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.sz.admin.templateinfo.mapper.TemplateInfoMapper;
import com.sz.admin.templateinfo.pojo.dto.TemplateInfoCreateDTO;
import com.sz.admin.templateinfo.pojo.dto.TemplateInfoImportDTO;
import com.sz.admin.templateinfo.pojo.dto.TemplateInfoListDTO;
import com.sz.admin.templateinfo.pojo.dto.TemplateInfoUpdateDTO;
import com.sz.admin.templateinfo.pojo.po.TemplateInfo;
import com.sz.admin.templateinfo.pojo.vo.TemplateInfoVO;
import com.sz.admin.templateinfo.service.TemplateInfoService;
import com.sz.core.common.entity.PageResult;
import com.sz.core.common.entity.SelectIdsDTO;
import com.sz.core.common.enums.CommonResponseEnum;
import com.sz.core.util.BeanCopyUtils;
import com.sz.core.util.PageUtils;
import com.sz.excel.core.ExcelResult;
import com.sz.excel.utils.ExcelUtils;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 模版信息表 服务实现类
 * </p>
 *
 * @author sz-admin
 * @since 2024-07-02
 */
@Service
@RequiredArgsConstructor
public class TemplateInfoServiceImpl extends ServiceImpl<TemplateInfoMapper, TemplateInfo> implements TemplateInfoService {
    @Override
    public void create(TemplateInfoCreateDTO dto) {
        TemplateInfo templateInfo = BeanCopyUtils.copy(dto, TemplateInfo.class);

        save(templateInfo);
    }

    @Override
    public void update(TemplateInfoUpdateDTO dto) {
        TemplateInfo templateInfo = BeanCopyUtils.copy(dto, TemplateInfo.class);
        QueryWrapper wrapper;
        // id有效性校验
        wrapper = QueryWrapper.create()
                .eq(TemplateInfo::getId, dto.getId())
                .eq(TemplateInfo::getRevision, dto.getRevision());
        CommonResponseEnum.INVALID_ID.assertTrue(count(wrapper) <= 0);
        // id有效性校验
        wrapper = QueryWrapper.create()
                .eq(TemplateInfo::getId, dto.getId())
                .eq(TemplateInfo::getRevision, dto.getRevision());
        CommonResponseEnum.INVALID_ID.assertTrue(count(wrapper) <= 0);

        saveOrUpdate(templateInfo);
    }

    @Override
    public PageResult<TemplateInfoVO> page(TemplateInfoListDTO dto) {
        Page<TemplateInfoVO> page = pageAs(PageUtils.getPage(dto), buildQueryWrapper(dto), TemplateInfoVO.class);
        return PageUtils.getPageResult(page);
    }

    @Override
    public List<TemplateInfoVO> list(TemplateInfoListDTO dto) {
        return listAs(buildQueryWrapper(dto), TemplateInfoVO.class);
    }

    @Override
    public void remove(SelectIdsDTO dto) {
        CommonResponseEnum.INVALID_ID.assertTrue(dto.getIds().isEmpty());
        removeByIds(dto.getIds());
    }

    @Override
    public TemplateInfoVO detail(Object id) {
        TemplateInfo templateInfo = getById((Serializable) id);
        CommonResponseEnum.INVALID_ID.assertNull(templateInfo);
        return BeanCopyUtils.copy(templateInfo, TemplateInfoVO.class);
    }

    @SneakyThrows
    @Override
    public void importExcel(MultipartFile file) {
        ExcelResult<TemplateInfoImportDTO> excelResult = ExcelUtils.importExcel(file.getInputStream(), TemplateInfoImportDTO.class, true);
        List<TemplateInfoImportDTO> list = excelResult.getList();
        List<String> errorList = excelResult.getErrorList();
        String analysis = excelResult.getAnalysis();
        System.out.println(" analysis : " + analysis);
    }

    @SneakyThrows
    @Override
    public void exportExcel(TemplateInfoListDTO dto, HttpServletResponse response) {
        List<TemplateInfoVO> list = list(dto);
        ServletOutputStream os = response.getOutputStream();
        ExcelUtils.exportExcel(list, "模版信息表", TemplateInfoVO.class, os);
    }

    private static QueryWrapper buildQueryWrapper(TemplateInfoListDTO dto) {
        QueryWrapper wrapper = QueryWrapper.create().from(TemplateInfo.class);
        wrapper.like(TemplateInfo::getName, dto.getName());
        wrapper.eq(TemplateInfo::getBelong, dto.getBelong());
        wrapper.eq(TemplateInfo::getSystem, dto.getSystem());
        wrapper.eq(TemplateInfo::getUserId, dto.getUserId());
        wrapper.eq(TemplateInfo::getStartDate, dto.getStartDate());
        wrapper.eq(TemplateInfo::getEndDate, dto.getEndDate());
        wrapper.eq(TemplateInfo::getCreatedId, dto.getCreatedId());
        wrapper.eq(TemplateInfo::getCreatedTime, dto.getCreatedTime());
        wrapper.eq(TemplateInfo::getUpdatedId, dto.getUpdatedId());
        wrapper.eq(TemplateInfo::getUpdatedTime, dto.getUpdatedTime());
        return wrapper;
    }
}