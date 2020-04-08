package unittest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockTest {

    @Mock
    List<String> mock;

    List spyList = spy(new LinkedList());

    @Test
    public void testMockVerify() {
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();


    }

    @Test
    public void testMockStubbing() {
        LinkedList mockedList = mock(LinkedList.class);
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenReturn(new RuntimeException());
        when(mockedList.get(anyInt())).thenReturn("element");
//        when(mockedList.get(argThat(isNotNull()))).thenReturn("element");

        System.out.println(mockedList.get(0));
        System.out.println(mockedList.get(1));
        System.out.println(mockedList.get(999));
        verify(mockedList).get(1);
        verify(mockedList, atLeast(1)).get(1);
    }

    @Test
    public void testMockWhen() {
        when(mock.get(0)).thenReturn("one").thenReturn("two");
        System.out.println(mock.get(0));
        System.out.println(mock.get(0));
        System.out.println(mock.size());
        doThrow(new RuntimeException()).when(mock).clear();
        mock.clear();

//        System.out.println(mockList.iterator().next());
//        System.out.println(mockList.iterator().next());


    }

    @Test
    public void testSpy() {
        when(spyList.size()).thenReturn(100);
        spyList.add("one");
        spyList.add("two");
        System.out.println(spyList.get(0));

        System.out.println(spyList.size());
    }

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Captor
    private ArgumentCaptor<List<String>> captor;
    @Test
    public void testArgumentCaptor(){
        List<String> asList = Arrays.asList("someElement_test", "someElement");
        final List<String> mockedList = mock(List.class);
        mockedList.addAll(asList);

        verify(mockedList).addAll(captor.capture()); // When verify,you can capture the arguments of the calling method
        final List<String> capturedArgument = captor.getValue();
        assertThat(capturedArgument, hasItem("someElement"));
    }

    @Test
    public void testAnswer() {
        when(mock.get(1)).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                return "called with arguements: " + args;
            }
        });
        System.out.println(mock.get(1));
    }


}
