package com.stone.movies.order.service.impl;


import com.stone.movies.model.order.OrderDetail;
import com.stone.movies.order.mapper.OrderDetailMapper;
import com.stone.movies.order.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细 订单明细 服务实现类
 * </p>
 *
 * @author stone
 * @since 2022-10-25
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}
