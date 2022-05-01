package com.sjs.jsvill.repository.jimmy;

import com.sjs.jsvill.entity.jimmy.MemberUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberUserRepository extends JpaRepository<MemberUser, Long> {
    MemberUser findByPhone(String phone);
    //phone과 pin으로 MemberUser가 있으면 가져오고, 없으면 가져오지 않는다
    MemberUser findByPhoneAndPin(String phone, String pin);
}
