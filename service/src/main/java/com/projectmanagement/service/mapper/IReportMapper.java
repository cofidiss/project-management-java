package com.projectmanagement.service.mapper;

import com.projectmanagement.domain.model.PieChartDataModel;
import com.projectmanagement.service.DTO.PieChartDataDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.lang.reflect.Method;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IReportMapper {



    @Mapping(target = "label",
            expression = "java(mapLabel(pieChartDataModel, myEnum))")
    PieChartDataDto toPieChartDataDto(PieChartDataModel pieChartDataModel,
                                      @Context Class<? extends Enum<?>> myEnum);


    default String mapLabel(PieChartDataModel model, Class<? extends Enum<?>> enumType) {
        if (enumType != null && model.getLabel() != null) {
            try {
                // model.label sayısal ve ordinal
                int ordinalValue = Integer.parseInt(model.getLabel());

                Enum<?>[] constants = enumType.getEnumConstants();
                    return constants[ordinalValue].name(); // enum adı döner

            } catch (Exception e) {
                // model.label sayı değilse ignore
            }
            return "";
        }

        // Enum null veya eşleşme yoksa fallback
        return model.getLabel() ;
    }



    List<PieChartDataDto> toPieChartDataDtoList(List<PieChartDataModel> pieChartDataModel,
                                                @Context Class<? extends Enum<?>> myEnum);



}


