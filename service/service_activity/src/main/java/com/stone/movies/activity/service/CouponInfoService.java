package com.stone.movies.activity.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.movies.model.activity.CouponInfo;
import com.stone.movies.model.activity.CouponUse;
import com.stone.movies.vo.activity.CouponUseQueryVo;

/**
 * <p>
 * 优惠券信息 服务类
 * </p>
 *
 * @author stone
 * @since 2022-10-26
 */
public interface CouponInfoService extends IService<CouponInfo> {
    //获取已经使用的分页列表
    IPage<CouponUse> selectCouponUsePage(Page<CouponUse> pageParam, CouponUseQueryVo couponUseQueryVo);
}
