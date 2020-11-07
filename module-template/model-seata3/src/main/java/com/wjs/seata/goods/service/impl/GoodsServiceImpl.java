package com.wjs.seata.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjs.seata.goods.entity.Goods;
import com.wjs.seata.goods.mapper.GoodsMapper;
import com.wjs.seata.goods.service.GoodsService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalLock;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wenjs
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;


//    @Autowired
//    private RemoteMemberService remoteMemberService;

    @GlobalTransactional
    @Override
    public Long seckill(Long goodsId, Integer num) {

        System.out.println("+++++++++++++++++++++++++++++++++++");
        System.out.println(RootContext.getXID());
        Goods goods = new Goods();
        goods.setTitle("我设置的11");
        String xid = RootContext.getXID();
        goods.setCover(xid);
        goodsMapper.insert(goods);

        if(1!=2){
            throw new RuntimeException();
        }

//        Member member = new Member();
//        member.setGoodsId(goodsId);
//        member.setName(xid);
//        memberMapper.insert(member);

        return goods.getId();

    }

    @GlobalLock
    @Override
    public void scekillRemote() {
        System.out.println("+++++++++++++++++++++++++++++++++++");
        System.out.println(RootContext.getXID());
        Goods goods = goodsMapper.selectById(1);
        goods.setTitle("我设置的11");
        String xid = RootContext.getXID();
        goods.setCover(xid);
        goods.setNumber(goods.getNumber() - 1);
        goodsMapper.updateById(goods);
        System.out.println(11);
        if(1 == 1){
            throw  new RuntimeException();
        }//

       //  remoteMemberService.createMemberInfo(goods.getId());
    }
    @GlobalTransactional
    @Override
    public void scekillRemote1() {
        System.out.println("+++++++++++++++++++++++++++++++++++");
        System.out.println(RootContext.getXID());
        Goods goods = goodsMapper.selectById(1);
        goods.setTitle("我设置的11");
        String xid = RootContext.getXID();
        goods.setCover(xid);
        goods.setNumber(goods.getNumber() - 1);
        goodsMapper.updateById(goods);
        System.out.println(11);
         
    }
}
