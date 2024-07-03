package com.sz.admin.templatedisk.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.sz.admin.templatedisk.service.TemplateDiskService;
import com.sz.admin.templatedisk.pojo.po.TemplateDisk;
import com.sz.admin.templatedisk.mapper.TemplateDiskMapper;
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
import com.sz.admin.templatedisk.pojo.dto.TemplateDiskCreateDTO;
import com.sz.admin.templatedisk.pojo.dto.TemplateDiskUpdateDTO;
import com.sz.admin.templatedisk.pojo.dto.TemplateDiskListDTO;
import com.sz.admin.templatedisk.pojo.dto.TemplateDiskImportDTO;
import org.springframework.web.multipart.MultipartFile;
import com.sz.excel.core.ExcelResult;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import com.sz.excel.utils.ExcelUtils;
import lombok.SneakyThrows;

import com.sz.admin.templatedisk.pojo.vo.TemplateDiskVO;

/**
 * <p>
 * 文件系统使用情况(df -h) 服务实现类
 * </p>
 *
 * @author sz-admin
 * @since 2024-07-03
 */
@Service
@RequiredArgsConstructor
public class TemplateDiskServiceImpl extends ServiceImpl<TemplateDiskMapper, TemplateDisk> implements TemplateDiskService {
    @Override
    public void create(TemplateDiskCreateDTO dto){
        TemplateDisk templateDisk = BeanCopyUtils.copy(dto, TemplateDisk.class);

        save(templateDisk);
    }

    @Override
    public void update(TemplateDiskUpdateDTO dto){
        TemplateDisk templateDisk = BeanCopyUtils.copy(dto, TemplateDisk.class);
        QueryWrapper wrapper;
        // id有效性校验
        wrapper = QueryWrapper.create()
            .eq(TemplateDisk::getId, dto.getId());
        CommonResponseEnum.INVALID_ID.assertTrue(count(wrapper) <= 0);

        saveOrUpdate(templateDisk);
    }

    @Override
    public PageResult<TemplateDiskVO> page(TemplateDiskListDTO dto){
        Page<TemplateDiskVO> page = pageAs(PageUtils.getPage(dto), buildQueryWrapper(dto), TemplateDiskVO.class);
        return PageUtils.getPageResult(page);
    }

    @Override
    public List<TemplateDiskVO> list(TemplateDiskListDTO dto){
        return listAs(buildQueryWrapper(dto), TemplateDiskVO.class);
    }

    @Override
    public void remove(SelectIdsDTO dto){
        CommonResponseEnum.INVALID_ID.assertTrue(dto.getIds().isEmpty());
        removeByIds(dto.getIds());
    }

    @Override
    public TemplateDiskVO detail(Object id){
        TemplateDisk templateDisk = getById((Serializable) id);
        CommonResponseEnum.INVALID_ID.assertNull(templateDisk);
        return BeanCopyUtils.copy(templateDisk, TemplateDiskVO.class);
    }

    @SneakyThrows
    @Override
    public void importExcel(MultipartFile file) {
        ExcelResult<TemplateDiskImportDTO> excelResult = ExcelUtils.importExcel(file.getInputStream(), TemplateDiskImportDTO.class, true);
        List<TemplateDiskImportDTO> list = excelResult.getList();
        List<String> errorList = excelResult.getErrorList();
        String analysis = excelResult.getAnalysis();
        System.out.println(" analysis : " + analysis);
    }

    @SneakyThrows
    @Override
    public void exportExcel(TemplateDiskListDTO dto, HttpServletResponse response) {
        List<TemplateDiskVO> list = list(dto);
        ServletOutputStream os = response.getOutputStream();
        ExcelUtils.exportExcel(list, "文件系统使用情况(df -h)", TemplateDiskVO.class, os);
    }

    private static QueryWrapper buildQueryWrapper(TemplateDiskListDTO dto) {
        QueryWrapper wrapper = QueryWrapper.create().from(TemplateDisk.class);
        wrapper.eq(TemplateDisk::getTemplateId, dto.getTemplateId());
        wrapper.eq(TemplateDisk::getFileSystem, dto.getFileSystem());
        wrapper.eq(TemplateDisk::getSize, dto.getSize());
        wrapper.eq(TemplateDisk::getUsed, dto.getUsed());
        wrapper.eq(TemplateDisk::getAvailable, dto.getAvailable());
        wrapper.eq(TemplateDisk::getUsedPercentage, dto.getUsedPercentage());
        wrapper.eq(TemplateDisk::getMountPoint, dto.getMountPoint());
        wrapper.eq(TemplateDisk::getRevision, dto.getRevision());
        wrapper.eq(TemplateDisk::getCreatedId, dto.getCreatedId());
        wrapper.eq(TemplateDisk::getCreatedTime, dto.getCreatedTime());
        wrapper.eq(TemplateDisk::getUpdatedId, dto.getUpdatedId());
        wrapper.eq(TemplateDisk::getUpdatedTime, dto.getUpdatedTime());
        return wrapper;
    }
}