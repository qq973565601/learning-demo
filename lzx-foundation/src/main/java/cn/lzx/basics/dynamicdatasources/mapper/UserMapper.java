package cn.lzx.basics.dynamicdatasources.mapper;

import cn.lzx.basics.dynamicdatasources.model.User;
import cn.lzx.basics.dynamicdatasources.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mapping(target = "username", source = "name")
    @Mapping(target = "sex", source = "gender")
    UserDto userToUserDto(User user);
}
