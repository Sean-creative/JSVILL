package com.sjs.jsvill;

import com.sjs.jsvill.dto.CalendarDTO;
import com.sjs.jsvill.entity.Calendar;
import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.repository.CalendarRepository;
import com.sjs.jsvill.service.calendar.CalendarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class MockitoTests {

    @Test
    @DisplayName("JUnit 사용 테스트")
    public void testSum() {
        int a = 10;
        int b = 20;
        int expectedSum = 32;
        int actualSum = a + b;
        assertEquals(expectedSum, actualSum);
    }

    //1. Mock - 모의 객체 생성
    //2. Stub (=가짜 함수) - 메서드의 반환 값을 미리 설정 (반환값자체를 제어)
    //3. Spy - 원본 객체의 행동을 유지하면서도 특정 메서드의 동작을 바꾸거나, 감시 (Spy는 Stub의 기능도 포함한다)
    // Spy는 상태를 가지고 있는 실제 객체를 복사해서 메소드들이 잘 되는지 + 메소드를 다르게 바꿔봐서 테스트
    //4. verify - 어떤 메소드가 정해진 횟수만큼, 특정 인자를 가지고 호출되었는지 확인할 수 있다.
    @Test
    @DisplayName("Stub 테스트")
    public void testStub() {
        // Mock 객체 생성
        List<String> mockList = mock(List.class);

        // Mock 객체의 동작 정의 : Stub - Mock 객체의 사이즈를 항상 10으로 반환하도록 예상동작을 설정합니다.
        // mockList에는 어떤 아이템도 넣어주지 않았는데, 그냥 이제부터 size 너는 10이야! 라고 말해주는거임
        when(mockList.size()).thenReturn(10);

        // Mock 객체의 메소드 호출
        int size = mockList.size();

        // 결과 확인
        assertEquals(10, size);
    }

    @Test
    @DisplayName("Spy 테스트")
    public void testSpy() {
        // 실제 객체 생성
        List<String> originalList = new ArrayList<>();

        // Spy 객체 생성 : Spy - 실제 객체의 원본을 유지하며 객체를 생성합니다.
        List<String> spyList = Mockito.spy(originalList);

        // Spy 객체의 메서드 동작 정의 : Spy - 이러한 객체에 값을 지정합니다.
        Mockito.doReturn("Mocked").when(spyList).get(0);

        // Spy 객체 사용
        String element = spyList.get(0); // "Mocked"를 반환

        // 결과 확인
        assertEquals("Mocked", element);
    }

    @Test
    @DisplayName("Verify 테스트")
    public void testVerify() {
        //예시 클래스
        class ExampleClass {
            public void methodA() {
                // 동작 A
            }

            public void methodB() {
                // 동작 B
            }
        }

        // Mockito를 사용한 Verification 예시
        ExampleClass mockExample = mock(ExampleClass.class);

        // 메소드 호출
        mockExample.methodA();
        mockExample.methodA();
        mockExample.methodB();

        // 메소드 호출 확인
        Mockito.verify(mockExample, times(2)).methodA();
        Mockito.verify(mockExample).methodB();

        // 메소드 호출 순서 확인
        InOrder inOrder = Mockito.inOrder(mockExample);
        inOrder.verify(mockExample, times(2)).methodA();
        inOrder.verify(mockExample).methodB();
    }


    @Mock
    private CalendarRepository calendarRepository;
    @InjectMocks
    private CalendarService calendarService;

    @Test
    @DisplayName("Mockito BDD 테스트")
    public void testMockito() {
        // Mockito를 사용한 when-then 구성
        // given (Mockito에서는 주로 when을 사용합니다.)
        when(calendarRepository.findAllByGroup(any())).thenReturn(Collections.emptyList());

        // when (실제 동작을 수행합니다. 예시로 여기서는 메소드를 호출합니다.)
        calendarRepository.findAllByGroup(any(Group.class));

        // then (검증 단계입니다.)
        assertThat(calendarRepository.findAllByGroup(any(Group.class))).isEmpty();
        verify(calendarRepository, times(2)).findAllByGroup(any());
    }

    @Test
    @DisplayName("BDDMockito BDD 테스트")
    public void testBDDMockito() {
        //given
        given(calendarRepository.findAllByGroup(any())).willReturn(Collections.emptyList());

        // when
        calendarRepository.findAllByGroup(any(Group.class));

        // then
        assertThat(calendarRepository.findAllByGroup(any(Group.class))).isEmpty();
        then(calendarRepository).should(times(2)).findAllByGroup(any());
    }

    @Test
    @DisplayName("BDDMockito BDD 테스트2")
    public void testBDDMockito2() {
        //Given: calendarRepository.findAllByGroup 메소드가 호출되었을 때 반환할 Calendar 객체 목록을 준비합니다.
        //       또한, 입력으로 사용될 groupRowid도 준비합니다.
        //When: getList 메소드를 호출합니다.
        //Then: 메소드의 반환값이 기대하는 CalendarDTO 목록과 일치하는지 확인합니다.
        //      또한, calendarRepository.findAllByGroup 메소드가 예상대로 호출되었는지 확인합니다.

        // Given
        Long groupRowid = 1L; // 예시 그룹 ID
        Group group = Group.builder().group_rowid(groupRowid).build();
        Calendar calendar1 = new Calendar(); // 적절한 생성자 또는 빌더를 사용하여 초기화
        Calendar calendar2 = new Calendar(); // 적절한 생성자 또는 빌더를 사용하여 초기화
        List<Calendar> mockCalendarList = Arrays.asList(calendar1, calendar2);
        given(calendarRepository.findAllByGroup(group)).willReturn(mockCalendarList);

        // When
        List<CalendarDTO> resultDTOList = calendarService.getList(groupRowid);

        // Then
        then(calendarRepository).should().findAllByGroup(group); // 리포지토리 메소드가 호출되었는지 확인
        assertEquals(2, resultDTOList.size()); // 반환된 DTO 목록의 크기 검증
        // 추가적으로, 반환된 DTO 목록의 내용이 기대하는 값과 일치하는지 검증할 수 있습니다.
        // 예: assertEquals(expectedValue, resultDTOList.get(0).getSomeProperty());
    }
}
