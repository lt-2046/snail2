package com.snail.model.m.mapper;

import com.snail.model.m.entity.MPermission;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 *
 * @author liutao
 * @date 2018/12/21
 */
@Repository
public interface MPermissionMapper extends Mapper<MPermission> {
    List<MPermission> selectAllPermission();
}