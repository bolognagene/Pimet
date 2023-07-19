package com.nohpe.pimet.utils.convert;

import com.nohpe.pimet.utils.entity.TT;
import com.nohpe.pimet.utils.entity.dto.TTDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TTConvert {

    TTConvert INSTANCE = Mappers.getMapper(TTConvert.class);

    // Entity ---> DTO
    TTDTO convertTTTO(TT tt);

    // DTO ---> Entity
    TT convertTT(TTDTO ttdto);
}
