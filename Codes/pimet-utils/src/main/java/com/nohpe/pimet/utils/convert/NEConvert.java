package com.nohpe.pimet.utils.convert;

import com.nohpe.pimet.utils.entity.NE;
import com.nohpe.pimet.utils.entity.dto.NEDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NEConvert {

    NEConvert INSTANCE = Mappers.getMapper(NEConvert.class);

    // Entity ---> DTO
    NEDTO convertNEDTO(NE ne);

    // DTO ---> Entity
    NE convertNE(NEDTO nedto);
}
