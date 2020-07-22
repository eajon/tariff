package cn.csfz.tariff.service.impl;

import cn.csfz.tariff.mapper.TariffMapper;
import cn.csfz.tariff.model.Tariff;
import cn.csfz.tariff.service.ITariffService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TariffServiceImpl extends ServiceImpl<TariffMapper,Tariff> implements ITariffService{

}
