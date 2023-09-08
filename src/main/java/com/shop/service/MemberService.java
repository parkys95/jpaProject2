package com.shop.service;

import com.shop.dto.MemberUpdateFormDto;
import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(email);

        if (member == null) {
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

    /**
     * 비밀번호 일치 확인
     **/
    @ResponseBody
    public boolean checkPassword(Member member, String checkPassword) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember == null) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
        String realPassword = member.getPassword(); // 실제 회원의 암호를 가져옵니다.
        boolean matches = passwordEncoder.matches(checkPassword, realPassword); // 입력한 비밀번호와 암호화된 비밀번호를 비교합니다.
        System.out.println(matches);
        return matches;
    }

    @Transactional
    public void updateMember(@Valid MemberUpdateFormDto memberUpdateFormDto) {

        Member member = new Member();
        // MemberFormDto에서 필요한 정보를 가져와서 existingMember에 설정합니다.
        member.setId(memberUpdateFormDto.getId());
        member.setEmail(memberUpdateFormDto.getEmail());
        member.setPassword(passwordEncoder.encode(memberUpdateFormDto.getPassword()));
        member.setName(memberUpdateFormDto.getName());
        member.setAddress(memberUpdateFormDto.getAddress());
        member.setRole(memberUpdateFormDto.getRole());



        // 회원 정보를 저장합니다.
        memberRepository.save(member);
    }


    @Transactional
    public void deleteMember(String loginId) {
        // loginId를 사용하여 회원을 조회합니다.
        Member member = memberRepository.findByEmail(loginId);

        if (member == null) {
            throw new UsernameNotFoundException("회원을 찾을 수 없습니다.");
        }

        // 회원을 삭제합니다.
        memberRepository.delete(member);
    }


}