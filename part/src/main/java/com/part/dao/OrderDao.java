package com.part.dao;

import com.part.entity.Order;
import com.part.entity.vo.OrderVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

/**
 *
 * @author jiangSD
 * @since 2019-10-17
 */
public interface OrderDao extends BaseMapper<Order> {
	List<OrderVo> findOrderVo(RowBounds page, @Param("ew") Wrapper wrapper);
}