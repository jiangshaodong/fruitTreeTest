package com.part.service.impl;

import com.part.entity.Order;
import com.part.entity.vo.OrderVo;
import com.part.dao.OrderDao;
import com.part.service.OrderService;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiangSD
 * @since 2019-10-17
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {
  
	@Autowired
    private OrderDao orderDao;
	@Override
	public Page<OrderVo> findOrderVo(Page page, Wrapper wrapper) {
		SqlHelper.fillWrapper(page, wrapper);
        page.setRecords(orderDao.findOrderVo(page, wrapper));
		return page;
	}
	
}
