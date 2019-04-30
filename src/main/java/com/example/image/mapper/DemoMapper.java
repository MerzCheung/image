package com.example.image.mapper;

import com.example.image.model.RGBModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DemoMapper {
    Integer add(RGBModel rgbModel);

    List<Map> selectImage(@Param("r") Integer r, @Param("g") Integer g, @Param("b") Integer b);
}
