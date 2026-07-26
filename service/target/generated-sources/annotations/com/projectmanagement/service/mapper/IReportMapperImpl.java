package com.projectmanagement.service.mapper;

import com.projectmanagement.domain.model.PieChartDataModel;
import com.projectmanagement.service.DTO.PieChartDataDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-26T15:44:26+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
@Component
public class IReportMapperImpl implements IReportMapper {

    @Override
    public PieChartDataDto toPieChartDataDto(PieChartDataModel pieChartDataModel, Class<? extends Enum<?>> myEnum) {
        if ( pieChartDataModel == null ) {
            return null;
        }

        PieChartDataDto pieChartDataDto = new PieChartDataDto();

        pieChartDataDto.setValue( pieChartDataModel.getValue() );

        pieChartDataDto.setLabel( mapLabel(pieChartDataModel, myEnum) );

        return pieChartDataDto;
    }

    @Override
    public List<PieChartDataDto> toPieChartDataDtoList(List<PieChartDataModel> pieChartDataModel, Class<? extends Enum<?>> myEnum) {
        if ( pieChartDataModel == null ) {
            return null;
        }

        List<PieChartDataDto> list = new ArrayList<PieChartDataDto>( pieChartDataModel.size() );
        for ( PieChartDataModel pieChartDataModel1 : pieChartDataModel ) {
            list.add( toPieChartDataDto( pieChartDataModel1, myEnum ) );
        }

        return list;
    }
}
