package Hello.HelloSpring.repository;

        import Hello.HelloSpring.domain.Member;
        import org.assertj.core.api.Assert;
        import org.assertj.core.api.Assertions;
        import org.junit.jupiter.api.Test;
public class MemoryMemberRespositoryTest {
    //회원 리포지토리 메모리 구현체 테스트

    // MemberRepository 인터페이스를 구현한 MemoryMemberRepository 객체 생성
    MemberRespository repository = new MemoryMemberRepository();

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
}

