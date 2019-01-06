package com.snail.model.m.mapper;

import com.snail.model.m.entity.MPermission;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 *
 * @author liutao
 * @date 2018/12/21
 */
@Component
public interface MPermissionMapper extends Mapper<MPermission> {
    List<MPermission> selectAllPermission();
}