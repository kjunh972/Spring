package Hello.HelloSpring.repository;

import Hello.HelloSpring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRespository {
    //회원 리포지토리 인터페이스

    Member save(Member member);
    Optional<Member> findById(Long id); //Optional은 findById나 findByName이 null일수 있는 상황에 감싸서 반환한다.
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
