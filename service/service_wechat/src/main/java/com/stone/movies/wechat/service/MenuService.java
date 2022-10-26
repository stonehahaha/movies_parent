package com.stone.movies.wechat.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.movies.model.wechat.Menu;
import com.stone.movies.vo.wechat.MenuVo;

import java.util.List;

/**
 * <p>
 * 订单明细 订单明细 服务类
 * </p>
 *
 * @author stone
 * @since 2022-10-26
 */
public interface MenuService extends IService<Menu> {
    //获取所有一级菜单
    List<Menu> findMenuOneInfo();
    //获取所有菜单，按照一级和二级菜单封装
    List<MenuVo> findMenuInfo();
    //同步菜单
    void syncMenu();
    //删除菜单
    void removeMenu();
}
