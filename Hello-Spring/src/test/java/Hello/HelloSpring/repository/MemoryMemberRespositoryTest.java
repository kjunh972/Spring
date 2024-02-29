package Hello.HelloSpring.repository;

import Hello.HelloSpring.domain.Member;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRespositoryTest {
    //회원 리포지토리 메모리 구현체 테스트

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    // 메소드가 하나 끝날때마다 어떠한 동작을 하는 콜백 메소드.
    // 이 메소드를 추가를 안하면 저장소에 member들이 저장되어있어서 오류가뜸. 그래서 메소드하나 끝날때마다 저장소에 있는 member들을 지울려고 메소드 추가.
    // 테스트는 서로 순서와 관계없이 서로 의존관계 없이 설계가 되어야함.
    public void afterEach() {
        // 테스트가 실행되고 끝날때마다 한번씩 저장소를 다 지워줌.
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring"); // Member 객체의 이름 설정

        // 회원 저장소에 회원 정보를 저장
        repository.save(member);

        // 회원 저장소에서 ID를 사용하여 회원 정보를 조회하고 결과를 변수에 저장
        Member result = repository.findById(member.getId()).get();
        //Assertions.assertEquals() 메서드는 JUnit 프레임워크에서 제공하는 메서드로, 두 값이 서로 같은지를 비교하는데 사용한다.
        //member(기대값)가 result(결과값)이 값으면 true.
        //Assertions.assertEquals(member, result); //이런 방법도 있다. 하지만 다른거 쓸꺼임.

        //위에 메소드랑 사용하는 기능을 같지만, assertThat() 메서드는 체이닝방식으로 사용되어 더 직관적이고 가독성이 좋은 코드를 작성할 수 있다.
        //member(기대값)가 result(결과값)이 값으면 true.
        //.isEqualTo(expectedValue) 메서드는 실제 값이 기대한 값과 동일한지를 확인하는 메서드입니다.
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        // 첫 번째 회원 객체 생성 및 저장
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // 두 번째 회원 객체 생성 및 저장
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // 이름이 "spring1"인 회원을 찾아 결과를 가져옴
        Member result = repository.findByName("spring1").get();
        // 결과와 기대한 회원(member1)이 동일한지 검증
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // 모든 회원을 조회하여 결과를 가져옴
        List<Member> result = repository.findAll();
        // 조회된 회원 리스트의 크기가 2인지를 검증
        //List<Member> result : 조회된 모든 회원 정보를 담기 위한 리스트이다. Member 타입의 객체들을 담을 수 있는 리스트이다.
        //repository : 회원 정보를 저장하고 있는 저장소를 가리키는 변수
        //findAll() : 저장소에서 모든 회원 정보를 조회하는 메서드를 호출. 이 메서드는 저장된 모든 회원을 가져와 리스트에 담아 반환한다.
        Assertions.assertThat(result.size()).isEqualTo(2);
    }

}

