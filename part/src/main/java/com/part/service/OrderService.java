package com.part.service;

import com.part.entity.Order;
import com.part.entity.vo.OrderVo;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiangSD
 * @since 2019-10-17
 */
public interface OrderService extends IService<Order> {
	
	Page<OrderVo> findOrderVo(Page page, Wrapper wrapper);
}
