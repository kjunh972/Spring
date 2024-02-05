package Hello.HelloSpring.repository;

import Hello.HelloSpring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRespository {
    //회원 리포지토리 메모리 구현체
    /**
     * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
     **/

    private static Map<Long, Member> store = new HashMap<>();
    //Map은 인터페이스이다. Key와 Value의 쌍으로 이루어진 데이터의 집합이다.
    //인터페이스를 구현하기 위해선 구현 클래스가 필요하다.
    //Map의 구현 클래스로는 Hashtable, HashMap, SortedMap 등이 있다.
    //Map을 쓰는이유는 매번 클래스를 만들기 귀찮으니까.
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //id가 NULL일 가능성이 있어서 Optional.ofNullable로 감싼다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        //회원 저장소(store)에서 모든 회원을 가져와서 Stream으로 변환한 뒤 처리한다.
        return store.values().stream()
                //Stream에서 회원을 하나씩 가져와서 조건을 적용한다.
                .filter(member -> member.getName().equals(name))
                //회원의 이름이 주어진 이름과 일치하는지 검사한다. (람다표현식 사용)
                //->는 람다 표현식의 일부분으로서 람다 화살표 연산자다.
                //람다 표현식은 일반적으로 매개변수 리스트, ->, 표현식 또는 코드 블록으로 구성된다.
                //이때 ->는 매개변수와 람다의 바디를 구분짓는 역할을 합니다.
                //매개변수 member를 받고, 이를 이용하여 해당 멤버의 이름과 주어진 name을 비교하는 람다 표현식이다.
                //이 람다 표현식은 filter() 메서드에 전달되어, 주어진 조건에 따라 스트림에서 필터링을 수행한다.
                //조건에 해당하는 회원이 존재하면 해당 회원을 반환한다.
                .findAny(); // 임의의 요소를 찾아 Optional로 반환한다.
    }


    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //새로 생성된 ArrayList를 메서드의 반환 값으로 반환한다.
        //이렇게 함으로써 이 메서드를 호출하면 회원 저장소에 있는 모든 회원을 포함한 리스트를 얻을 수 있다.
    }
}
