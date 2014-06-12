package cupof;
// http://habrahabr.ru/post/72617/
//статически импортируем методы (для красоты и легкости кода)
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * Created by m.bova on 6/12/2014.
 */
public class CupOfMockitoTest {

    @Test
    public void behaviourTest() {
        //вот он - mock-объект (заметьте: List.class - это интерфейс)
        List mockedList = mock(List.class);

        //используем его
        mockedList.add("one");
        mockedList.clear();

        //проверяем, были ли вызваны методы add с параметром "one" и clear
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void mocksTest() {

        //Вы можете создавать mock для конкретного класса, не только для интерфейса
        LinkedList mockedList = mock(LinkedList.class);

        //stub'инг
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        //получим "first"
        System.out.println(mockedList.get(0));

        //получим RuntimeException
        //System.out.println(mockedList.get(1));

        //получим "null" ибо get(999) не был определен
        System.out.println(mockedList.get(999));
    }

    @Test
    public void callsNumberTest() {
        LinkedList mockedList = mock(LinkedList.class);

    //используем mock-объект
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        //по умолчанию проверка, что вызывался 1 раз ~ times(1)
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        //точное число вызовов
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        //никогда ~ never() ~ times(0)
        verify(mockedList, never()).add("never happened");

        //как минимум, как максимум
        verify(mockedList, atLeastOnce()).add("three times");
        //verify(mockedList, atLeast(2)).add("five times");
        verify(mockedList, atMost(5)).add("three times");
    }

    @Test
    public void spyTest() {
        List list = new LinkedList();
        List spy = spy(list);

        //опционально, определяем лишь метод size()
        when(spy.size()).thenReturn(100);

        //используем реальные методы
        spy.add("one");
        spy.add("two");

        //получим "one"
        System.out.println(spy.get(0));

        //метод size() нами переопределён - получим 100
        System.out.println(spy.size());

        //можем проверить
        verify(spy).add("one");
        verify(spy).add("two");

    }

}
