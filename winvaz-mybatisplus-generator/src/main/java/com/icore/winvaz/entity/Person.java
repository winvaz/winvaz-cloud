package com.icore.winvaz.entity;

import java.sql.Blob;

import com.icore.winvaz.winvazmybatisplusgenerator.generator.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author MybatisPlusGenerator
 * @since 2021-05-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="Person对象", description="")
public class Person extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别")
    private Blob sex;


}
