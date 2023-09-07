package com.shop.dto;

import com.shop.constant.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberUpdateFormDto {

    private Long id;

    private String email;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String password;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "주소는 필수 입력 값입니다.")
    private String address;

    private Role role;
<<<<<<< HEAD

    private boolean social;
=======
>>>>>>> f4606a3bc4ab0d9f9c75e328c690296c78097ab0
}