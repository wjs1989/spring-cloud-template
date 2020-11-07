package com.wjs.lottery.lottery.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjs.lottery.lottery.entity.Lottery;
import com.wjs.lottery.lottery.mapper.LotteryMapper;
import com.wjs.lottery.lottery.service.LotteryService;
import org.springframework.stereotype.Service;

@Service
public class LotteryServiceImpl extends ServiceImpl<LotteryMapper, Lottery> implements LotteryService {
}
