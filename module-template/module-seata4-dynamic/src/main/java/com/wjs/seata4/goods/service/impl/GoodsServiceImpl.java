package com.wjs.seata4.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjs.seata4.auto.mapper.SqlExecuteMapper;
import com.wjs.seata4.config.dbsource.DatabaseContextHolder;
import com.wjs.seata4.config.dbsource.DynamicDataSource;
import com.wjs.seata4.goods.entity.Goods;
import com.wjs.seata4.goods.mapper.GoodsMapper;
import com.wjs.seata4.goods.service.GoodsService;
import com.wjs.seata4.member.entity.Member;
import com.wjs.seata4.member.mapper.MemberMapper;
import com.wjs.seata2.remote.feign.RemoteMemberService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * @author wenjs
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private RemoteMemberService remoteMemberService;

    @Autowired
    DynamicDataSource dynamicDataSource;

    @Autowired
    SqlExecuteMapper sqlExecuteMapper;

    @GlobalTransactional
    @Override
    public Long seckill(Long goodsId, Integer num) {

        System.out.println("+++++++++++++++++++++++++++++++++++");
        System.out.println(RootContext.getXID());

        String sql = "INSERT INTO `goods`(`id`, `category_id`, `title`, `description`, `total`, `number`, `price`, `cover`, `create_time`, `version`, `update_time`, `valid`) VALUES (1, 0, '我设置的11', NULL, 100, 90, 0.00, '10.204.125.109:18091:60037643756376064', '2020-10-15 15:07:24', 10, '2020-10-15 15:07:24', 1);";

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource((DataSource) dynamicDataSource.getDataSource("goods"));
        jdbcTemplate.execute(sql);


        sql = "INSERT INTO `member`.`user`(`id`, `name`, `goods_id`) VALUES (1, 'w', 1)";
        jdbcTemplate.setDataSource((DataSource) dynamicDataSource.getDataSource("member"));
        jdbcTemplate.execute(sql);

        if (1 != 2) {
            throw new RuntimeException();
        }
        return 1L;

    }

    @GlobalTransactional
    @Override
    public void dynamic() {
        String sql = "INSERT INTO `goods`(`id`, `category_id`, `title`, `description`, `total`, `number`, `price`, `cover`, `create_time`, `version`, `update_time`, `valid`) VALUES (1, 0, '我设置的11', NULL, 100, 90, 0.00, '10.204.125.109:18091:60037643756376064', '2020-10-15 15:07:24', 10, '2020-10-15 15:07:24', 1);";
        DatabaseContextHolder.setDatabaseType("goods");
        sqlExecuteMapper.exeDMLInsertSql(sql);

        sql = "INSERT INTO `member`.`user`(`id`, `name`, `goods_id`) VALUES (1, 'w', 1)";
        DatabaseContextHolder.setDatabaseType("member");
        sqlExecuteMapper.exeDMLInsertSql(sql);

    }
}
