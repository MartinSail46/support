package com.sz.admin.templateio.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.sz.admin.templateio.service.TemplateIoService;
import com.sz.admin.templateio.pojo.po.TemplateIo;
import com.sz.admin.templateio.mapper.TemplateIoMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.sz.core.common.enums.CommonResponseEnum;
import com.sz.core.util.PageUtils;
import com.sz.core.util.BeanCopyUtils;
import com.sz.core.util.Utils;
import com.sz.core.common.entity.PageResult;
import com.sz.core.common.entity.SelectIdsDTO;
import java.io.Serializable;
import java.util.List;
import com.sz.admin.templateio.pojo.dto.TemplateIoCreateDTO;
import com.sz.admin.templateio.pojo.dto.TemplateIoUpdateDTO;
import com.sz.admin.templateio.pojo.dto.TemplateIoListDTO;
import com.sz.admin.templateio.pojo.dto.TemplateIoImportDTO;
import org.springframework.web.multipart.MultipartFile;
import com.sz.excel.core.ExcelResult;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import com.sz.excel.utils.ExcelUtils;
import lombok.SneakyThrows;

import com.sz.admin.templateio.pojo.vo.TemplateIoVO;

/**
 * <p>
 * 磁盘IO读写情况(iostat -d) 服务实现类
 * </p>
 *
 * @author sz-admin
 * @since 2024-07-03
 */
@Service
@RequiredArgsConstructor
public class TemplateIoServiceImpl extends ServiceImpl<TemplateIoMapper, TemplateIo> implements TemplateIoService {
    @Override
    public void create(TemplateIoCreateDTO dto){
        TemplateIo templateIo = BeanCopyUtils.copy(dto, TemplateIo.class);

        save(templateIo);
    }

    @Override
    public void update(TemplateIoUpdateDTO dto){
        TemplateIo templateIo = BeanCopyUtils.copy(dto, TemplateIo.class);
        QueryWrapper wrapper;
        // id有效性校验
        wrapper = QueryWrapper.create()
            .eq(TemplateIo::getId, dto.getId());
        CommonResponseEnum.INVALID_ID.assertTrue(count(wrapper) <= 0);

        saveOrUpdate(templateIo);
    }

    @Override
    public PageResult<TemplateIoVO> page(TemplateIoListDTO dto){
        Page<TemplateIoVO> page = pageAs(PageUtils.getPage(dto), buildQueryWrapper(dto), TemplateIoVO.class);
        return PageUtils.getPageResult(page);
    }

    @Override
    public List<TemplateIoVO> list(TemplateIoListDTO dto){
        return listAs(buildQueryWrapper(dto), TemplateIoVO.class);
    }

    @Override
    public void remove(SelectIdsDTO dto){
        CommonResponseEnum.INVALID_ID.assertTrue(dto.getIds().isEmpty());
        removeByIds(dto.getIds());
    }

    @Override
    public TemplateIoVO detail(Object id){
        TemplateIo templateIo = getById((Serializable) id);
        CommonResponseEnum.INVALID_ID.assertNull(templateIo);
        return BeanCopyUtils.copy(templateIo, TemplateIoVO.class);
    }

    @SneakyThrows
    @Override
    public void importExcel(MultipartFile file) {
        ExcelResult<TemplateIoImportDTO> excelResult = ExcelUtils.importExcel(file.getInputStream(), TemplateIoImportDTO.class, true);
        List<TemplateIoImportDTO> list = excelResult.getList();
        List<String> errorList = excelResult.getErrorList();
        String analysis = excelResult.getAnalysis();
        System.out.println(" analysis : " + analysis);
    }

    @SneakyThrows
    @Override
    public void exportExcel(TemplateIoListDTO dto, HttpServletResponse response) {
        List<TemplateIoVO> list = list(dto);
        ServletOutputStream os = response.getOutputStream();
        ExcelUtils.exportExcel(list, "磁盘IO读写情况(iostat -d)", TemplateIoVO.class, os);
    }

    private static QueryWrapper buildQueryWrapper(TemplateIoListDTO dto) {
        QueryWrapper wrapper = QueryWrapper.create().from(TemplateIo.class);
        wrapper.eq(TemplateIo::getTemplateId, dto.getTemplateId());
        wrapper.eq(TemplateIo::getDevice, dto.getDevice());
        wrapper.eq(TemplateIo::getTps, dto.getTps());
        wrapper.eq(TemplateIo::getKbReadS, dto.getKbReadS());
        wrapper.eq(TemplateIo::getKbWriteS, dto.getKbWriteS());
        wrapper.eq(TemplateIo::getKbRead, dto.getKbRead());
        wrapper.eq(TemplateIo::getKbWrite, dto.getKbWrite());
        wrapper.eq(TemplateIo::getRevision, dto.getRevision());
        wrapper.eq(TemplateIo::getCreatedId, dto.getCreatedId());
        wrapper.eq(TemplateIo::getCreatedTime, dto.getCreatedTime());
        wrapper.eq(TemplateIo::getUpdatedId, dto.getUpdatedId());
        wrapper.eq(TemplateIo::getUpdatedTime, dto.getUpdatedTime());
        return wrapper;
    }
}