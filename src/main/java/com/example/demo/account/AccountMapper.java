package com.example.demo.account;

import com.example.demo.account.domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "sid",target = "studentId")
    AccountDTO userToUserDto (Account account);
}
