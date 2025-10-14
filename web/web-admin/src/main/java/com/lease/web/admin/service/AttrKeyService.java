package com.lease.web.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lease.model.entity.AttrKey;
import com.lease.web.admin.vo.attr.AttrKeyVo;

import java.util.List;

/**
 * @author  孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */
    
public interface AttrKeyService extends IService<AttrKey>{

	List<AttrKeyVo> listAttrInfo();
}
